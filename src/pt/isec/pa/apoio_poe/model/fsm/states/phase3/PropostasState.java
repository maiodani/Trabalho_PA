package pt.isec.pa.apoio_poe.model.fsm.states.phase3;

import pt.isec.pa.apoio_poe.model.data.PhasesData;
import pt.isec.pa.apoio_poe.model.fsm.PhaseContext;
import pt.isec.pa.apoio_poe.model.fsm.PhaseState;
import pt.isec.pa.apoio_poe.model.fsm.PhaseStateAdapter;

public class PropostasState extends PhaseStateAdapter {
    public PropostasState(PhasesData phasesData, PhaseContext context) {
        super(phasesData, context);
    }

    @Override
    public String export() {
        return null;
    }

    @Override
    public PhaseState getState() {
        return PhaseState.PROPOSTAS;
    }



    @Override
    public boolean fecharFase() {
        changeState(PhaseState.ATRIBUICAO_ORIENTADORES);
        return true;
    }

    @Override
    public boolean voltar() {
        changeState(PhaseState.CANDIDATURA);
        return true;
    }

}
