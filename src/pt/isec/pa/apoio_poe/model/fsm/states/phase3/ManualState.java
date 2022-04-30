package pt.isec.pa.apoio_poe.model.fsm.states.phase3;

import pt.isec.pa.apoio_poe.model.data.PhasesData;
import pt.isec.pa.apoio_poe.model.data.phase1.Aluno;
import pt.isec.pa.apoio_poe.model.data.phase1.Propostas;
import pt.isec.pa.apoio_poe.model.data.phase1.SiglaRamo;
import pt.isec.pa.apoio_poe.model.fsm.PhaseContext;
import pt.isec.pa.apoio_poe.model.fsm.PhaseState;
import pt.isec.pa.apoio_poe.model.fsm.PhaseStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class ManualState extends PhaseStateAdapter {
    public ManualState(PhaseContext context) {
        super(context);
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

        obterListas(propostasSemAlunos, alunosSemPropostas);

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

        obterListas(propostasSemAlunos, alunosSemPropostas);

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

    private void obterListas(List<Propostas> propostasSemAlunos, List<Aluno> alunosSemPropostas){
        List<Propostas> propostas = phasesData.getPropostas();
        List<Aluno> alunos = phasesData.getAlunos();
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
    }
    @Override
    public boolean voltar() {
        changeState(PhaseState.PROPOSTAS);
        return true;
    }
}
