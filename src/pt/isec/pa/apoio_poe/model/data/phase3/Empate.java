package pt.isec.pa.apoio_poe.model.data.phase3;

import pt.isec.pa.apoio_poe.model.data.phase1.Aluno;
import pt.isec.pa.apoio_poe.model.data.phase1.Propostas;

import java.util.List;

public class Empate {
    private static Empate instance;
    private List<Aluno> alunosEmpatados;

    private Propostas proposta;

    public static Empate getInstance(){
        if(instance==null){
            instance = new Empate();
        }
        return instance;
    }

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
}
