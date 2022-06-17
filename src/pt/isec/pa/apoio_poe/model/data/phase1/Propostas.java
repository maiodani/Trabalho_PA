package pt.isec.pa.apoio_poe.model.data.phase1;

import pt.isec.pa.apoio_poe.model.CsvManager;
import pt.isec.pa.apoio_poe.model.data.phase1.propostas.EstProjAutoproposto;
import pt.isec.pa.apoio_poe.model.data.phase1.propostas.Estagio;
import pt.isec.pa.apoio_poe.model.data.phase1.propostas.Projeto;
import pt.isec.pa.apoio_poe.model.data.phase2.Candidatura;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Propostas implements Serializable {
    protected List<SiglaRamo> ramo;
    protected Aluno aluno;
    protected String titulo;
    protected String codigoId;
    protected boolean atribuida;
    protected Docente orientador;


    public Propostas(Aluno aluno,String titulo, String codigoId,Docente docente) {
        this.aluno=aluno;
        this.titulo = titulo;
        this.codigoId=codigoId;
        this.orientador=docente;
        this.atribuida = false;
    }

    public String exportar(){
        return null;
    }

    //coloca ramos de maneira a poder ser exportados da mesma maneira que são importados
    protected String ramoString(List<SiglaRamo> ramos){
        StringBuilder str = new StringBuilder();
        for (SiglaRamo s : ramos){
            str.append(s).append("|");
        }
        str.deleteCharAt(str.length()-1);
        return str.toString();
    }

    @Override
    public String toString() {
        return null;
    }

    public List<SiglaRamo> getRamo() {
        return ramo;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getCodigoId() {
        return codigoId;
    }

    public boolean isAtribuida() {
        return atribuida;
    }

    public void setAtribuida(boolean atribuida) {
        this.atribuida = atribuida;
    }

    public Docente getOrientador() {
        return orientador;
    }

    static public StringBuilder createPropostas(String [][] data,List<Aluno> alunos,List<Docente> docentes,List<Propostas> propostas,StringBuilder str){
        Propostas p = null;
        for(int i=0; i<data.length; i++) {
            if (data[i][0].equals("T1")) {
                p = new Estagio(
                        Estagio.temAluno(data[i],alunos),
                        Estagio.variosRamos(data[i][2]),
                        data[i][3],
                        data[i][1],
                        data[i][4]
                );
            } else if (data[i][0].equals("T2")) {
                p = new Projeto(
                        Projeto.temAluno(data[i],alunos),
                        Projeto.adicionarProfessor(data[i][4],docentes),
                        Projeto.variosRamos(data[i][2]),
                        data[i][3],
                        data[i][1]
                );
            } else if (data[i][0].equals("T3")) {
                Aluno al = null;
                for(Aluno aluno : alunos){
                    if (Integer.parseInt(data[i][3]) == (aluno.getNumEstudante())) al = aluno;
                }
                p = new EstProjAutoproposto(
                        al,
                        data[i][2],
                        data[i][1]
                );
            }
            if (Propostas.canBeAdded(p, propostas, str)){
                propostas.add(p);
            }
        }
        return str;
    }
    static public StringBuilder createProposta(String [] data,List<Aluno> alunos,List<Docente> docentes,List<Propostas> propostas,StringBuilder str){
        Propostas p = null;
        if (data[0].equals("T1")) {
            p = new Estagio(
                    Estagio.getAluno(Long.parseLong(data[5]),alunos),
                    Estagio.variosRamos(data[2]),
                    data[3],
                    data[1],
                    data[4]
            );
        } else if (data[0].equals("T2")) {
            p = new Projeto(
                    Projeto.getAluno(Long.parseLong(data[5]),alunos),
                    Projeto.adicionarProfessor(data[4],docentes),
                    Projeto.variosRamos(data[2]),
                    data[3],
                    data[1]
            );
        } else if (data[0].equals("T3")) {
            Aluno al = null;
            for(Aluno aluno : alunos){
                if (Long.parseLong(data[3]) == (aluno.getNumEstudante())) al = aluno;
            }
            p = new EstProjAutoproposto(
                    al,
                    data[2],
                    data[1]
            );
        }
        if (Propostas.canBeAdded(p, propostas, str)){
            propostas.add(p);
        }
        return str;
    }

    static public boolean canBeAdded(Propostas p, List<Propostas> propostas, StringBuilder str) {
        for (Propostas proposta : propostas){
            if (p.getCodigoId().equals(proposta.getCodigoId())) {
                str.append("\nCódigo da proposta ").append(p.getCodigoId()).append(" ja existe");
                return false;
            }
            if (p.getAluno() != null && proposta.getAluno() != null){
                if(p.getAluno().getNumEstudante() == proposta.getAluno().getNumEstudante()){
                    str.append("\nAluno ja possui a proposta ").append(p.getCodigoId());
                    return false;
                }
            }
        }
        if (p instanceof Estagio){
            if(p.getRamo()==null){
                str.append("\nRamo inválido na proposta ").append(p.getCodigoId());
                return false;
            }
        } else if (p instanceof Projeto) {
            if(p.getRamo()==null){
                str.append("\nRamo inválido na proposta ").append(p.getCodigoId());
                return false;
            }
            if (p.getOrientador() == null){
                str.append("\nProfessor inválido para a proposta").append(p.getCodigoId());
                return false;
            }
        } else if (p instanceof EstProjAutoproposto) {
            if (p.getAluno() == null){
                str.append("\nAluno não inserido para a proposta ").append(p.getCodigoId());
                return false;
            }
        }
        return true;
    }

    static public String query(List<Propostas> propostas) {
        StringBuilder str = new StringBuilder();
        for (Propostas proposta : propostas) {
            str.append(proposta.toString());
        }
        return str.toString();
    }

    static public StringBuilder export(List<Propostas> propostas) {
        StringBuilder str = new StringBuilder();
        for (Propostas proposta : propostas){
            str.append(proposta.exportar());
        }
        if(str.length()!=0) {
            str.deleteCharAt(str.length() - 1);
        }
        return str;
    }
    static public boolean podeFecharFase3(List<Candidatura> c, List<Propostas> p){
        int count=0;
        List<Aluno> a = new ArrayList<>();
        for(Candidatura ca:c){
            a.add(ca.getAluno());
        }

        for(Propostas pa:p){
            if(pa.getAluno()!=null){
                if(a.contains(pa.getAluno())){
                    count++;
                }
            }
        }
        if(count==a.size()){
            return true;
        }else{
            return false;
        }
    }

    static public StringBuilder exportFase3(List<Candidatura> candidaturas,List<Aluno> alunos,List<Propostas> propostas){
        StringBuilder str = new StringBuilder();
        int i = 1;
        for (Candidatura c : candidaturas){
            for (Aluno al :alunos){
                if(c.getAluno().equals(al)){
                    str.append(al.getNumEstudante());
                    for (String s : c.getCodigos()){
                        str.append(",").append(s);
                    }
                    for (Propostas p : propostas){
                        if(p.getAluno()!=null){
                            if(p.getAluno().equals(al)){
                                str.append(",").append(p.getCodigoId());
                                for (String s : c.getCodigos()){
                                    if (s.equalsIgnoreCase(p.getCodigoId())){
                                        str.append(",").append(i);
                                        break;
                                    }
                                    i++;
                                }

                            }
                        }
                    }
                    str.append("\n");
                }
            }
        }
        if(str.length()!=0) {
            str.deleteCharAt(str.length() - 1);
        }
        return str;
    }
    public void setOrientador(Docente orientador) {
        this.orientador = orientador;
    }
}
