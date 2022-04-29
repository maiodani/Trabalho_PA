package pt.isec.pa.apoio_poe.model.data.phase2;

import pt.isec.pa.apoio_poe.model.data.phase1.Aluno;

import java.util.List;

public class Candidatura {
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


}
