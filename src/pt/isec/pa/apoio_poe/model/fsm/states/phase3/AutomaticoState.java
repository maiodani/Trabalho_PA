package pt.isec.pa.apoio_poe.model.fsm.states.phase3;

import pt.isec.pa.apoio_poe.model.data.PhasesData;
import pt.isec.pa.apoio_poe.model.data.phase1.Aluno;
import pt.isec.pa.apoio_poe.model.data.phase1.Propostas;
import pt.isec.pa.apoio_poe.model.data.phase1.SiglaRamo;
import pt.isec.pa.apoio_poe.model.data.phase1.propostas.Estagio;
import pt.isec.pa.apoio_poe.model.data.phase2.Candidatura;
import pt.isec.pa.apoio_poe.model.data.phase3.Automatico;
import pt.isec.pa.apoio_poe.model.data.phase3.Empate;
import pt.isec.pa.apoio_poe.model.fsm.PhaseContext;
import pt.isec.pa.apoio_poe.model.fsm.PhaseState;
import pt.isec.pa.apoio_poe.model.fsm.PhaseStateAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AutomaticoState extends PhaseStateAdapter {
    public AutomaticoState(PhaseContext context,PhasesData phasesData) {
        super(context,phasesData);
    }

    @Override
    public String insert(int op) {
        StringBuilder str = new StringBuilder();
        List<Propostas> propostas = phasesData.getPropostas();
        List<Candidatura> candidaturas = phasesData.getCandidaturas();
        switch (op){
            case 1 -> Automatico.automaticoAtribuido(str, propostas);
            case 2 -> {
                if(phasesData.getFechado()>=2){
                    if(Automatico.automaticoNaoAtribuido(str, propostas,candidaturas)){
                        changeState(PhaseState.EMPATE);
                    }
                }str.append("\nFases anteriores não se encontram fechadas");
            }
            case 3 -> voltar();
        }
        return str.toString();
    }

    @Override
    public PhaseState getState() {
        return PhaseState.AUTOMATICO;
    }

    @Override
    public boolean voltar() {
        changeState(PhaseState.PROPOSTAS);
        return true;
    }
}
