package pt.isec.pa.apoio_poe.model.fsm.states.phase1;

import pt.isec.pa.apoio_poe.model.CsvManager;
import pt.isec.pa.apoio_poe.model.data.PhasesData;
import pt.isec.pa.apoio_poe.model.data.phase1.*;
import pt.isec.pa.apoio_poe.model.data.phase1.propostas.*;
import pt.isec.pa.apoio_poe.model.fsm.PhaseContext;
import pt.isec.pa.apoio_poe.model.fsm.PhaseState;
import pt.isec.pa.apoio_poe.model.fsm.PhaseStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class GestPropostaState extends PhaseStateAdapter {
    public GestPropostaState(PhaseContext context,PhasesData phasesData) {
        super(context,phasesData);
    }

    @Override
    public PhaseState getState() {
        return PhaseState.GEST_PROPOSTA;
    }

    @Override
    public String insert(String nomeFicheiro) {
        String[][] data = CsvManager.readFile(nomeFicheiro);
        List<Propostas> propostas = phasesData.getPropostas();
        List<Aluno> alunos = phasesData.getAlunos();
        List<Docente> docentes = phasesData.getDocentes();
        StringBuilder str = new StringBuilder();
        if(data!=null){
            str.append("ERROS:");
            str = Propostas.createPropostas(data,alunos,docentes,propostas,str);
        }
        str.append("\n");
        return str.toString();
    }
    public String query() {
        List<Propostas> propostas = phasesData.getPropostas();
        return Propostas.query(propostas);
    }

    @Override
    public String export(String nomeFicheiro) {
        List<Propostas> propostas = phasesData.getPropostas();
        return CsvManager.writeFile(nomeFicheiro, Propostas.export(propostas));
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
