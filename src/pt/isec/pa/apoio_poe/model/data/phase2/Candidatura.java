package pt.isec.pa.apoio_poe.model.data.phase2;

import pt.isec.pa.apoio_poe.model.data.phase1.Aluno;
import pt.isec.pa.apoio_poe.model.data.phase1.SiglaCurso;

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

    public String exportar(){
        StringBuilder str = new StringBuilder();
        str.append(aluno.getNumEstudante());
        for (String s : codigos){
            str.append(",").append(s);
        }
        str.append("\n");
        return str.toString();
    }


}
