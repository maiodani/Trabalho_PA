package pt.isec.pa.apoio_poe.model.data;

import pt.isec.pa.apoio_poe.model.data.phase1.Estagio;

import java.util.ArrayList;
import java.util.List;

public class PhasesData {
    private int fechado; //Nº A IDENTIFICAR ATE QUE FASE ESTÁ FECHADO
    private List alunos;
    private List docentes;
    private List propostas;

    public PhasesData(int n){
        fechado=n;
        alunos = new ArrayList();
        docentes = new ArrayList();
        propostas = new ArrayList();
    }

    public int getFechado(){
        return fechado;
    }

    public void setFechado(int fechado){
        this.fechado=fechado;
    }

    public List getAlunos() {return alunos;}

    public void setAlunos(List alunos) {
        this.alunos = alunos;
    }

    public List getDocentes() {
        return docentes;
    }

    public void setDocentes(List docentes) {
        this.docentes = docentes;
    }

    public List getPropostas() {
        return propostas;
    }

    public void setPropostas(List propostas) {
        this.propostas = propostas;
    }
}
