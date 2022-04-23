package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.PhasesData;
import pt.isec.pa.apoio_poe.model.fsm.states.*;

public enum PhaseState {
    CONFIG,
    GEST_ALUNO,
    GEST_PROFESSOR,
    GEST_PROPOSTA,
    CANDIDATURA,
    PROPOSTAS,
    ATRIBUICAO_ORIENTADORES,
    CONSULTA;

    IPhaseState createState(PhaseContext context, PhasesData phasesData){
        return switch (this){
            case CONFIG -> new ConfigState(phasesData, context);
            case GEST_ALUNO -> new GestAlunoState(phasesData, context);
            case GEST_PROFESSOR -> new GestProfState(phasesData, context);
            case GEST_PROPOSTA -> new GestPropostaState(phasesData, context);
            case CANDIDATURA -> new CadidaturaState(phasesData, context);
            case PROPOSTAS -> new PropostasState(phasesData, context);
            case ATRIBUICAO_ORIENTADORES -> new AtribuicaoOrientadoresState(phasesData, context);
            case CONSULTA -> new ConsultaState(phasesData, context);
        };
    }
}
