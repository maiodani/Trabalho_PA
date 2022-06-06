package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.PhasesData;
import pt.isec.pa.apoio_poe.model.fsm.states.phase1.ConfigState;
import pt.isec.pa.apoio_poe.model.fsm.states.phase1.GestAlunoState;
import pt.isec.pa.apoio_poe.model.fsm.states.phase1.GestProfState;
import pt.isec.pa.apoio_poe.model.fsm.states.phase1.GestPropostaState;
import pt.isec.pa.apoio_poe.model.fsm.states.phase2.CadidaturaState;
import pt.isec.pa.apoio_poe.model.fsm.states.phase3.AutomaticoState;
import pt.isec.pa.apoio_poe.model.fsm.states.phase3.EmpateState;
import pt.isec.pa.apoio_poe.model.fsm.states.phase3.ManualState;
import pt.isec.pa.apoio_poe.model.fsm.states.phase3.PropostasState;
import pt.isec.pa.apoio_poe.model.fsm.states.phase4.AtribuicaoOrientadoresState;
import pt.isec.pa.apoio_poe.model.fsm.states.phase5.ConsultaState;

import java.io.Serializable;

public enum PhaseState implements Serializable {
    CONFIG,
    GEST_ALUNO,
    GEST_PROFESSOR,
    GEST_PROPOSTA,
    CANDIDATURA,
    PROPOSTAS,
    MANUAL,
    AUTOMATICO,
    EMPATE,
    ATRIBUICAO_ORIENTADORES,
    CONSULTA;

    IPhaseState createState(PhaseContext context,PhasesData phasesData){
        return switch (this){
            case CONFIG -> new ConfigState(context,phasesData);
            case GEST_ALUNO -> new GestAlunoState(context,phasesData);
            case GEST_PROFESSOR -> new GestProfState(context,phasesData);
            case GEST_PROPOSTA -> new GestPropostaState(context,phasesData);
            case CANDIDATURA -> new CadidaturaState(context,phasesData);
            case PROPOSTAS -> new PropostasState(context,phasesData);
            case MANUAL -> new ManualState(context,phasesData);
            case AUTOMATICO -> new AutomaticoState(context,phasesData);
            case EMPATE -> new EmpateState(context,phasesData);
            case ATRIBUICAO_ORIENTADORES -> new AtribuicaoOrientadoresState(context,phasesData);
            case CONSULTA -> new ConsultaState(context,phasesData);
        };
    }
}
