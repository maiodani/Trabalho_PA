package pt.isec.pa.apoio_poe.model.data;

import pt.isec.pa.apoio_poe.model.data.phase1.Aluno;
import pt.isec.pa.apoio_poe.model.data.phase1.Docente;
import pt.isec.pa.apoio_poe.model.data.phase1.Propostas;
import pt.isec.pa.apoio_poe.model.data.phase2.Candidatura;
import pt.isec.pa.apoio_poe.model.data.phase3.Empate;
import pt.isec.pa.apoio_poe.model.fsm.IPhaseState;
import pt.isec.pa.apoio_poe.model.fsm.PhaseState;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PhasesData implements Serializable {

    private static PhasesData instance;
    private int fechado; //Nº A IDENTIFICAR ATE QUE FASE ESTÁ FECHADO
    private List<Aluno> alunos;
    private List<Docente> docentes;
    private List<Propostas> propostas;
    private List<Candidatura> candidaturas;

    private PhaseState state;

    public PhasesData(int n){
        fechado=n;
        news();
        state = null;
    }

    public void copy (PhasesData other){
        news();
        fechado = other.fechado;
        alunos.addAll(other.alunos);
        docentes.addAll(other.docentes);
        propostas.addAll(other.propostas);
        candidaturas.addAll(other.candidaturas);
        state=other.state;
    }

    private void news(){
        alunos = new ArrayList();
        docentes = new ArrayList();
        propostas = new ArrayList();
        candidaturas = new ArrayList();
    }

    public static PhasesData getInstance(){
        if(instance==null){
            instance = new PhasesData(0);
        }
        return instance;
    }



    public int getFechado(){
        return fechado;
    }

    public void setFechado(int fechado){
        this.fechado=fechado;
    }

    public List getAlunos() {return alunos;}

    public List getDocentes() {
        return docentes;
    }

    public List getPropostas() {
        return propostas;
    }

    public void setPropostas(List propostas) {
        this.propostas = propostas;
    }

    public List getCandidaturas(){
        return candidaturas;
    }

    public PhaseState getState() {
        return state;
    }

    public void setState(PhaseState state) {
        this.state = state;
    }
}
