package pt.isec.pa.apoio_poe.model.fsm.states.phase3;

import pt.isec.pa.apoio_poe.model.data.PhasesData;
import pt.isec.pa.apoio_poe.model.data.phase1.Aluno;
import pt.isec.pa.apoio_poe.model.data.phase1.Propostas;
import pt.isec.pa.apoio_poe.model.fsm.PhaseContext;
import pt.isec.pa.apoio_poe.model.fsm.PhaseState;
import pt.isec.pa.apoio_poe.model.fsm.PhaseStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class ManualState extends PhaseStateAdapter {
    public ManualState(PhasesData phasesData, PhaseContext context) {
        super(phasesData, context);
    }
    @Override
    public PhaseState getState() {
        return PhaseState.MANUAL;
    }

    @Override
    public String query() {
        StringBuilder str = new StringBuilder();
        List<Propostas> propostas = phasesData.getPropostas();
        List<Propostas> propostasSemAlunos = new ArrayList<>();
        List<Aluno> alunos = phasesData.getAlunos();
        List<Aluno> alunosSemPropostas = new ArrayList<>();
        alunosSemPropostas.addAll(alunos);
        for (Propostas p : propostas){
            if(p.getAluno()==null){
                propostasSemAlunos.add(p);
            }else{
                for (Aluno al : alunos){
                    if(al.equals(p.getAluno())){
                        alunosSemPropostas.remove(al);
                    }
                }
            }
        }

        str.append("\nPropostas:");
        for (Propostas p: propostasSemAlunos){
            str.append(p.toString());
        }

        str.append("\nAlunos:");
        for (Aluno al: alunosSemPropostas){
            str.append(al.toString());
        }

        return str.toString();
    }

    public boolean voltar() {
        changeState(PhaseState.PROPOSTAS);
        return true;
    }
}
