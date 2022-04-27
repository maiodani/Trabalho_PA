package pt.isec.pa.apoio_poe.model.data.phase1.propostas;
import pt.isec.pa.apoio_poe.model.data.phase1.Aluno;
import pt.isec.pa.apoio_poe.model.data.phase1.Docente;
import pt.isec.pa.apoio_poe.model.data.phase1.Propostas;
import pt.isec.pa.apoio_poe.model.data.phase1.SiglaRamo;

import java.util.List;

public class Projeto extends Propostas {


    private Docente orientador;

    public Projeto(Aluno aluno, Docente orientador, List<SiglaRamo> ramo, String titulo, String codigoId) {
        super(aluno, titulo, codigoId);
        this.orientador = orientador;
        this.ramo = ramo;
    }

    public Docente getOrientador() {
        return orientador;
    }
}
