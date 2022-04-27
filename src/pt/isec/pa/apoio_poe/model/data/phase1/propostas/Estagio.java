package pt.isec.pa.apoio_poe.model.data.phase1.propostas;

import pt.isec.pa.apoio_poe.model.data.phase1.Aluno;
import pt.isec.pa.apoio_poe.model.data.phase1.Docente;
import pt.isec.pa.apoio_poe.model.data.phase1.Propostas;
import pt.isec.pa.apoio_poe.model.data.phase1.SiglaRamo;

import java.util.List;

public class Estagio extends Propostas {

    private String empresa;
    public Estagio(Aluno aluno, List<SiglaRamo> ramo, String titulo, String codigoId, String empresa) {
        super(aluno, titulo, codigoId);
        this.empresa = empresa;
        this.ramo = ramo;
    }

    public String getEmpresa() {
        return empresa;
    }
}
