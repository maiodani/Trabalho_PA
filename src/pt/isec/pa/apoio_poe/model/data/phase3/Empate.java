package pt.isec.pa.apoio_poe.model.data.phase3;

import pt.isec.pa.apoio_poe.model.data.phase1.Aluno;
import pt.isec.pa.apoio_poe.model.data.phase1.Propostas;

import java.io.Serializable;
import java.util.List;

public class Empate implements Serializable {
    private List<Aluno> alunosEmpatados;
    private Propostas proposta;

    public List<Aluno> getAlunosEmpatados() {
        return alunosEmpatados;
    }

    public void setAlunosEmpatados(List<Aluno> alunosEmpatados) {
        this.alunosEmpatados = alunosEmpatados;
    }

    public Propostas getProposta() {
        return proposta;
    }

    public void setProposta(Propostas proposta) {
        this.proposta = proposta;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(alunosEmpatados.toString());
        str.append(proposta.toString());
        return str.toString();
    }
}
