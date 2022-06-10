package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.PhasesData;
import pt.isec.pa.apoio_poe.model.data.Queries;

public abstract class PhaseStateAdapter implements IPhaseState {
    protected PhasesData phasesData;
    protected PhaseContext context;

    public PhaseStateAdapter(PhaseContext context,PhasesData phasesData){
        this.context= context;
        this.phasesData=phasesData;
    }

    protected void changeState(PhaseState newState) {
        context.changeState(newState.createState(context,phasesData));
    }

    @Override
    public boolean avancar() {
        return false;
    }

    @Override
    public String insert() {return null;}

    @Override
    public String insert(int op) {
        return null;
    }

    @Override
    public String export() {return null;}
    @Override
    public String export(String nomeFicheiro) {return null;}
    @Override
    public String query() {
        return null;
    }
    @Override
    public String query(Queries n) {
        return null;
    }
    @Override
    public boolean fecharFase() {
        return false;
    }

    @Override
    public boolean iniciar(int op){
        return false;
    }

    @Override
    public boolean voltar(){
        return false;
    }

    @Override
    public String insert(String ... options) {
        return null;
    }
    @Override
    public String insert(String nomeFicheiro) {
        return null;
    }
}
