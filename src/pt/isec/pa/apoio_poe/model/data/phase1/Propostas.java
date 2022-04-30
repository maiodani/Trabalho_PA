package pt.isec.pa.apoio_poe.model.data.phase1;

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

    public void setOrientador(Docente orientador) {
        this.orientador = orientador;
    }
}
