package pt.isec.pa.apoio_poe.model.data.phase1;

public class Estagio extends Propostas{
    private String entidade;
    public Estagio(Docente orientador,Aluno aluno, SiglaRamo ramo, String titulo,String codigoId,String entidade) {
        super(orientador, aluno, ramo, titulo,codigoId);
        this.entidade=entidade;
    }
}
