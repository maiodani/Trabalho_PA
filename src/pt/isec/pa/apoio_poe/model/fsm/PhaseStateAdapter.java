package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.PhasesData;

public abstract class PhaseStateAdapter implements IPhaseState {
    protected PhasesData phasesData;
    protected PhaseContext context;

    public PhaseStateAdapter(PhasesData phasesData, PhaseContext context){
        this.context= context;
        this.phasesData=phasesData;
    }

    protected void changeState(PhaseState newState) {
        context.changeState(newState.createState(context, phasesData));
    }

    @Override
    public boolean avancar() {
        return false;
    }

    @Override
    public void insert() {}

    @Override
    public String query() {
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


}
