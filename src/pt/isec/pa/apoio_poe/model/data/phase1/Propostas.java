package pt.isec.pa.apoio_poe.model.data.phase1;

public abstract class Propostas {
    protected SiglaRamo ramo;
    protected Aluno aluno;
    protected String titulo;
    protected String codigoId;


    public Propostas(Aluno aluno,SiglaRamo ramo,String titulo, String codigoId) {
        this.aluno=aluno;
        this.ramo = ramo;
        this.titulo = titulo;
        this.codigoId=codigoId;
    }
}
