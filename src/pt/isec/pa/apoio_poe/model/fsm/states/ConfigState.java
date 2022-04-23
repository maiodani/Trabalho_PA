package pt.isec.pa.apoio_poe.model.fsm.states;

import pt.isec.pa.apoio_poe.model.data.PhasesData;
import pt.isec.pa.apoio_poe.model.fsm.PhaseContext;
import pt.isec.pa.apoio_poe.model.fsm.PhaseState;
import pt.isec.pa.apoio_poe.model.fsm.PhaseStateAdapter;

public class ConfigState extends PhaseStateAdapter {
    public ConfigState(PhasesData phasesData, PhaseContext context) {
        super(phasesData, context);
    }


    @Override
    public PhaseState getState() {
        return PhaseState.CONFIG;
    }

    @Override
    public boolean iniciar(int op) {
        switch (op){
            case 1 -> changeState(PhaseState.GEST_ALUNO);
            case 2 -> changeState(PhaseState.GEST_PROFESSOR);
            case 3 -> changeState(PhaseState.GEST_PROPOSTA);
            default -> {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean avancar() {
        changeState(PhaseState.CANDIDATURA);
        return true;
    }

    @Override
    public boolean fecharFase() {
        changeState(PhaseState.CONFIG);
        return true;
    }
}
