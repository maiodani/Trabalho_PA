package pt.isec.pa.apoio_poe.model.data.phase2;

import pt.isec.pa.apoio_poe.model.data.phase1.Aluno;
import pt.isec.pa.apoio_poe.model.data.phase1.Propostas;
import pt.isec.pa.apoio_poe.model.data.phase1.SiglaCurso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Candidatura implements Serializable {
    private Aluno aluno;
    private List<String> codigos;

    public Candidatura(Aluno aluno,List<String> codigos) {
        this.codigos = codigos;
        this.aluno = aluno;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public List<String> getCodigos() {
        return codigos;
    }

    public String exportar(){
        StringBuilder str = new StringBuilder();
        str.append(aluno.getNumEstudante());
        for (String s : codigos){
            str.append(",").append(s);
        }
        str.append("\n");
        return str.toString();
    }

    static public boolean canAdd(Candidatura c, List<Candidatura> candidaturas, StringBuilder str,List<Propostas> propostas) {

        if(c.getAluno()==null) {
            str.append("\nAluno não foi encontrado.");
            return false;
        }
        if(c.getCodigos()==null){
            str.append("\nCandidatura do aluno ").append(c.getAluno().getNumEstudante()).append(" não tem propostas");
            return false;
        }

        for(Propostas p:propostas){
            for(String co:c.getCodigos()){
                if(p.getCodigoId().equals(co)){
                    if(p.getAluno()!=null){
                        str.append("\nCandidatura a Proposta ").append(c.getCodigos()).append(" já tem aluno associado.");
                        return false;
                    }
                }
            }
        }

        for(Propostas p:propostas){
            if(p.getAluno()!=null) {
                if (c.getAluno().getNumEstudante() == p.getAluno().getNumEstudante()) {
                    str.append("\nAluno ").append(c.getAluno().getNumEstudante()).append(" já tem autoproposta ou proposta.");
                    return false;
                }
            }
        }

        for(Candidatura ca :candidaturas){
            if(ca.getAluno().getNumEstudante()==c.getAluno().getNumEstudante()){
                str.append("\nAluno ").append(c.getAluno().getNumEstudante()).append(" já tem candidatura");
                return false;
            }
        }
        return true;
    }
    static public List<String> getCodigosPropostas(String[] data) {
        List<String> aux = new ArrayList<>();
        if(data.length<=1)return null;
        for(int i=1;i< data.length;i++){
            aux.add(data[i]);
        }
        return aux;
    }


    static public Aluno temAluno(String n,List<Aluno> alunos){
        for(Aluno aluno : alunos){
            if (Integer.parseInt(n) == (aluno.getNumEstudante())){
                return aluno;
            }
        }
        return null;
    }
}
