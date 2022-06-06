package pt.isec.pa.apoio_poe.model.fsm.states.phase3;

import pt.isec.pa.apoio_poe.model.data.PhasesData;
import pt.isec.pa.apoio_poe.model.data.phase1.Aluno;
import pt.isec.pa.apoio_poe.model.data.phase1.Propostas;
import pt.isec.pa.apoio_poe.model.data.phase1.SiglaRamo;
import pt.isec.pa.apoio_poe.model.data.phase3.Manual;
import pt.isec.pa.apoio_poe.model.fsm.PhaseContext;
import pt.isec.pa.apoio_poe.model.fsm.PhaseState;
import pt.isec.pa.apoio_poe.model.fsm.PhaseStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class ManualState extends PhaseStateAdapter {
    public ManualState(PhaseContext context,PhasesData phasesData) {
        super(context,phasesData);
    }
    @Override
    public PhaseState getState() {
        return PhaseState.MANUAL;
    }

    @Override
    public String query() {
        StringBuilder str = new StringBuilder();
        List<Propostas> propostasSemAlunos = new ArrayList<>();
        List<Aluno> alunosSemPropostas = new ArrayList<>();
        List<Aluno> alunos = phasesData.getAlunos();
        List<Propostas> propostas = phasesData.getPropostas();
        Manual.obterListas(propostasSemAlunos, alunosSemPropostas,propostas,alunos);

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

    @Override
    public String insert(String... options) {
        StringBuilder str = new StringBuilder();
        int pErro = 0, aErro = 0, rErro = 0;
        List<Propostas> propostasSemAlunos = new ArrayList<>();
        List<Aluno> alunosSemPropostas = new ArrayList<>();
        List<Aluno> alunos = phasesData.getAlunos();
        List<Propostas> propostas = phasesData.getPropostas();
        Manual.obterListas(propostasSemAlunos, alunosSemPropostas,propostas,alunos);

        for (Propostas p : propostasSemAlunos){
            if (p.getCodigoId().equalsIgnoreCase(options[0])){
                for (Aluno al : alunosSemPropostas){
                    if (al.getNumEstudante() == Integer.parseInt(options[1])){
                        for (SiglaRamo ramo : p.getRamo()){
                            if(ramo == al.getSiglaRamo()){
                                p.setAluno(al);
                                p.setAtribuida(true);
                                rErro = 1;
                            }
                        }
                        aErro = 1;
                    }
                }
                pErro = 1;
            }
        }
        if (pErro != 1){
            str.append("\nProposta inválida");
        }
        if (aErro != 1){
            str.append("\nAluno inválido");
        }
        if (rErro != 1){
            str.append("\nRamo incompativel");
        }
        return str.toString();
    }

    @Override
    public boolean voltar() {
        changeState(PhaseState.PROPOSTAS);
        return true;
    }
}
