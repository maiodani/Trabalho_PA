package pt.isec.pa.apoio_poe.model.data.phase1;

import pt.isec.pa.apoio_poe.model.data.phase1.propostas.EstProjAutoproposto;
import pt.isec.pa.apoio_poe.model.data.phase1.propostas.Estagio;
import pt.isec.pa.apoio_poe.model.data.phase1.propostas.Projeto;

import java.io.Serializable;
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


    public void setOrientador(Docente orientador) {
        this.orientador = orientador;
    }
}
