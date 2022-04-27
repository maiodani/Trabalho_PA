package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.PhasesData;

import java.util.Scanner;

public class PhaseContext {
    private PhasesData phasesData;
    private IPhaseState state;

    private Scanner sc;

    public PhaseContext(){
        phasesData = new PhasesData(0);//INICIA SEM NENHUMA FASE FECHADA
        state = PhaseState.CONFIG.createState(this,phasesData);
        sc = new Scanner(System.in);
    }

    public void changeState(IPhaseState newState){
        state = newState;
    }

    public PhaseState getState(){
        return state.getState();
    }

    public boolean voltar(){
        return state.voltar();
    }

    public boolean avancar(){
        return state.avancar();
    }

    public boolean fecharFase(){
        return state.fecharFase();
    }
    public boolean iniciar(int op){
        return state.iniciar(op);
    }

    public int getFechado(){
        return phasesData.getFechado();
    }

    public String query(){
        return state.query();
    };
    public String query(int n){
        return state.query(n);
    };
    public String insert() {
        return state.insert();
    }

}
