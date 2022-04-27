package pt.isec.pa.apoio_poe.model.data.phase1.propostas;
import pt.isec.pa.apoio_poe.model.data.phase1.Aluno;
import pt.isec.pa.apoio_poe.model.data.phase1.Docente;
import pt.isec.pa.apoio_poe.model.data.phase1.Propostas;
import pt.isec.pa.apoio_poe.model.data.phase1.SiglaRamo;

public class Projeto extends Propostas {
    private Docente orientador;


    public Projeto(Aluno aluno, Docente orientador, SiglaRamo ramo, String titulo, String codigoId) {
        super(aluno, ramo, titulo, codigoId);
        this.orientador = orientador;
    }
}
