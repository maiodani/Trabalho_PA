package pt.isec.pa.apoio_poe.model.fsm.states;

import pt.isec.pa.apoio_poe.model.data.PhasesData;
import pt.isec.pa.apoio_poe.model.fsm.PhaseContext;
import pt.isec.pa.apoio_poe.model.fsm.PhaseState;
import pt.isec.pa.apoio_poe.model.fsm.PhaseStateAdapter;

public class GestProfState extends PhaseStateAdapter{
    public GestProfState(PhasesData phasesData, PhaseContext context) {
        super(phasesData, context);
    }

    @Override
    public PhaseState getState() {
        return PhaseState.GEST_PROFESSOR;
    }

    @Override
    public boolean fecharFase() {
        changeState(PhaseState.CANDIDATURA);
        return true;
    }

    @Override
    public boolean voltar() {
        changeState(PhaseState.CONFIG);
        return true;
    }
}
