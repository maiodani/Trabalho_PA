package pt.isec.pa.apoio_poe.model.fsm.states.phase1;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import pt.isec.pa.apoio_poe.model.data.PhasesData;
import pt.isec.pa.apoio_poe.model.data.phase1.Aluno;
import pt.isec.pa.apoio_poe.model.data.phase1.Propostas;
import pt.isec.pa.apoio_poe.model.data.phase1.SiglaRamo;
import pt.isec.pa.apoio_poe.model.data.phase1.propostas.EstProjAutoproposto;
import pt.isec.pa.apoio_poe.model.data.phase1.propostas.Estagio;
import pt.isec.pa.apoio_poe.model.data.phase1.propostas.Projeto;
import pt.isec.pa.apoio_poe.model.fsm.PhaseContext;
import pt.isec.pa.apoio_poe.model.fsm.PhaseState;
import pt.isec.pa.apoio_poe.model.fsm.PhaseStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class ConfigState extends PhaseStateAdapter {
    public ConfigState(PhaseContext context,PhasesData phasesData) {
        super(context,phasesData);
    }


    @Override
    public PhaseState getState() {
        return PhaseState.CONFIG;
    }

    @Override
    public boolean iniciar(int op) {
        System.out.println("TESTE1212121");
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
        List<Aluno> alunos = phasesData.getAlunos();
        List<Propostas> propostas = phasesData.getPropostas();

        if(Aluno.podeFechar(alunos,propostas)) {
            phasesData.setFechado(1);
            changeState(PhaseState.CANDIDATURA);
            return true;
        }
        return false;
    }

}
