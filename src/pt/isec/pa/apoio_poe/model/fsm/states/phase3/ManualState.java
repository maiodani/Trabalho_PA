package pt.isec.pa.apoio_poe.model.fsm.states.phase3;

import pt.isec.pa.apoio_poe.model.data.PhasesData;
import pt.isec.pa.apoio_poe.model.fsm.PhaseContext;
import pt.isec.pa.apoio_poe.model.fsm.PhaseState;
import pt.isec.pa.apoio_poe.model.fsm.PhaseStateAdapter;

public class ManualState extends PhaseStateAdapter {
    public ManualState(PhasesData phasesData, PhaseContext context) {
        super(phasesData, context);
    }
    //META 2s
    @Override
    public PhaseState getState() {
        return PhaseState.MANUAL;
    }

    public boolean voltar() {
        changeState(PhaseState.PROPOSTAS);
        return true;
    }
}
