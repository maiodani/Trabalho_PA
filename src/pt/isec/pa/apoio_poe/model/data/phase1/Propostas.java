package pt.isec.pa.apoio_poe.model.data.phase1;

public abstract class Propostas {
    private Docente orientador;
    private SiglaRamo ramo;
    private Aluno aluno;
    private String titulo;
    private String codigoId;

    public Propostas(Docente orientador,Aluno aluno,SiglaRamo ramo,String titulo, String codigoId) {
        this.orientador = orientador;
        this.aluno=aluno;
        this.ramo = ramo;
        this.titulo = titulo;
        this.codigoId=codigoId;
    }
}
