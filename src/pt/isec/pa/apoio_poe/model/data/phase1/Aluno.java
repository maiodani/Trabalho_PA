package pt.isec.pa.apoio_poe.model.data.phase1;

import pt.isec.pa.apoio_poe.model.data.phase1.propostas.EstProjAutoproposto;
import pt.isec.pa.apoio_poe.model.data.phase1.propostas.Estagio;
import pt.isec.pa.apoio_poe.model.data.phase1.propostas.Projeto;
import pt.isec.pa.apoio_poe.model.fsm.states.phase3.IClassificacao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Aluno implements IClassificacao , Serializable {
    private long numEstudante;
    private String nome;
    private String email;
    private SiglaCurso siglaCurso;
    private SiglaRamo siglaRamo;
    private double classificacao;
    private Boolean podeAceder;

    public Aluno(long numEstudante, String nome, String email, SiglaCurso siglaCurso, SiglaRamo siglaRamo, double classificacao, Boolean podeAceder) {
        this.numEstudante = numEstudante;
        this.nome = nome;
        this.email = email;
        this.siglaCurso = siglaCurso;
        this.siglaRamo = siglaRamo;
        this.classificacao = classificacao;
        this.podeAceder = podeAceder;
    }

    @Override
    public boolean equals(Object al) {
        if (!(al instanceof Aluno)) return false;
        Aluno aluno = (Aluno) al;

        return numEstudante == aluno.numEstudante
                && nome.equals(aluno.nome)
                && email.equals(aluno.email)
                && siglaCurso == aluno.siglaCurso
                && siglaRamo == aluno.siglaRamo
                && classificacao == aluno.classificacao
                && podeAceder == aluno.podeAceder;
    }

    public long getNumEstudante() {
        return numEstudante;
    }
    public String getNome() {
        return nome;
    }
    public String getEmail() {
        return email;
    }
    public SiglaCurso getSiglaCurso() {
        return siglaCurso;
    }
    public SiglaRamo getSiglaRamo() {
        return siglaRamo;
    }
    @Override
    public double getClassificacao() {
        return classificacao;
    }
    public Boolean getPodeAceder() {
        return podeAceder;
    }

    public String exportar(){
        StringBuilder str = new StringBuilder();
        str.append(numEstudante)
                .append(",")
                .append(nome)
                .append(",")
                .append(email)
                .append(",")
                .append(SiglaCurso.parseString(siglaCurso))
                .append(",")
                .append(siglaRamo)
                .append(",")
                .append(classificacao)
                .append(",")
                .append(podeAceder)
                .append("\n");
        return str.toString();
    }
    static public boolean canBeAdded(Aluno a, List<Aluno> alunos, StringBuilder str,List<Docente> docentes) {
        for(Aluno aux:alunos){//CHECK SE JA EXISTE
            if(aux.getNumEstudante()==a.getNumEstudante()){
                str.append("\nAluno ").append(a.getNumEstudante()).append(" ja se encontra registrado");
                return false;
            }
            if (aux.getEmail().equals(a.getEmail())){
                str.append("\nO email do Aluno ").append(a.getNumEstudante()).append(" já existe");
                return false;
            }
        }

        for (Docente docente : docentes){
            if (docente.getEmail().equals(a.getEmail())){
                str.append("\nO email do Aluno ").append(a.getNumEstudante()).append(" já existe");
                return false;
            }
        }

        if(a.getSiglaCurso()==null){//SIGLA ESTA ERRADA
            str.append("\nSigla de curso do aluno ").append(a.getNumEstudante()).append(" não reconhecida");
            return false;
        }

        if(a.getSiglaRamo()==null){//SIGLA ESTA ERRADA
            str.append("\nSigla do ramo do aluno ") .append(a.getNumEstudante()).append(" não reconhecida");
            return false;
        }

        if(a.getClassificacao()<0.0 || a.getClassificacao()>1.0){//VERIRICAR QUAL É O LIMITE
            str.append("\nClassificação do aluno ").append(a.getNumEstudante()).append(" inválida");
            return false;
        }

        if(a.getPodeAceder()==null){//VERIFICAR SE O CAMPO PODE ACEDER ESTA CORRETO
            str.append("\nPermissões do aluno ").append(a.getNumEstudante()).append(" inválidas");
            return false;
        }
        return true;
    }

    static public boolean podeFechar(List<Aluno> alunos,List<Propostas> propostas){

        int nAlunosDA=0,nAlunosRAS=0,nAlunosSI=0,nProjetosDA=0,nProjetosRAS=0,nProjetosSI=0;
        for(Aluno a: alunos){
            switch(a.getSiglaRamo()){
                case DA : nAlunosDA++; break;
                case SI : nAlunosSI++; break;
                case RAS : nAlunosRAS++; break;
            }
        }

        for(Propostas p: propostas){
            List<SiglaRamo> ramos = new ArrayList<>();
            if(p instanceof Estagio){
                Estagio aux = (Estagio) p;
                ramos=aux.getRamo();
            }else if (p instanceof Projeto) {
                Projeto aux = (Projeto) p;
                ramos = aux.getRamo();
            }else if(p instanceof EstProjAutoproposto){
                continue;
            }
            for(SiglaRamo r:ramos){
                switch (r){
                    case RAS -> nProjetosRAS++;
                    case DA -> nProjetosDA++;
                    case SI -> nProjetosSI++;
                }
            }

        }
        if(nProjetosDA>=nAlunosDA && nProjetosSI>=nAlunosSI && nProjetosRAS>=nAlunosRAS){
            return true;
        }
        return false;
    }
    static public Aluno createAluno(String data[][]){
        Aluno a = null;
        for(int i=0;i<data.length; i++){
             a = new Aluno(
                    Long.parseLong(data[i][0]),
                    data[i][1],
                    data[i][2],
                    SiglaCurso.parse(data[i][3]),
                    SiglaRamo.parse(data[i][4]),
                    Double.parseDouble(data[i][5]),
                    parseBoolean(data[i][6].replaceAll("\\s+",""))
            );
        }
        return a;
    }

    static private Boolean parseBoolean(String s){
        s = s.toLowerCase();
        switch (s){
            case "true": return Boolean.TRUE;
            case "false":return Boolean.FALSE;
            default: return null;
        }
    }
    @Override
    public String toString() {
        return "\nN: " + getNumEstudante() +
                "\nNome: " + getNome() +
                "\nEmail: " + getEmail() +
                "\nCurso: " + getSiglaCurso() +
                "\nRamo: " + getSiglaRamo() +
                "\nClassificacao: " + getClassificacao() +
                "\n";
    }
}
