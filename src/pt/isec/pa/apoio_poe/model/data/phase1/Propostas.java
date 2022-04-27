package pt.isec.pa.apoio_poe.model.data.phase1;

import pt.isec.pa.apoio_poe.model.data.phase1.propostas.Projeto;

import java.util.List;

public abstract class Propostas {
    protected List<SiglaRamo> ramo;

    protected Aluno aluno;
    protected String titulo;
    protected String codigoId;


    public Propostas(Aluno aluno,String titulo, String codigoId) {
        this.aluno=aluno;
        this.titulo = titulo;
        this.codigoId=codigoId;
    }

    public List<SiglaRamo> getRamo() {
        return ramo;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getCodigoId() {
        return codigoId;
    }
}
