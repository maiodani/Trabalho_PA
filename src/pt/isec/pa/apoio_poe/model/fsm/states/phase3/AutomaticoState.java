package pt.isec.pa.apoio_poe.model.fsm.states.phase3;

import pt.isec.pa.apoio_poe.model.data.PhasesData;
import pt.isec.pa.apoio_poe.model.data.phase1.Aluno;
import pt.isec.pa.apoio_poe.model.data.phase1.Propostas;
import pt.isec.pa.apoio_poe.model.data.phase1.SiglaRamo;
import pt.isec.pa.apoio_poe.model.data.phase1.propostas.Estagio;
import pt.isec.pa.apoio_poe.model.data.phase2.Candidatura;
import pt.isec.pa.apoio_poe.model.data.phase3.Empate;
import pt.isec.pa.apoio_poe.model.fsm.PhaseContext;
import pt.isec.pa.apoio_poe.model.fsm.PhaseState;
import pt.isec.pa.apoio_poe.model.fsm.PhaseStateAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AutomaticoState extends PhaseStateAdapter {
    public AutomaticoState(PhaseContext context) {
        super(context);
    }

    @Override
    public String insert(int op) {
        StringBuilder str = new StringBuilder();
        List<Propostas> propostas = phasesData.getPropostas();
        switch (op){
            case 1 -> automaticoAtribuido(str, propostas);
            case 2 -> {
                if(phasesData.getFechado()>=2) automaticoNaoAtribuido(str, propostas);str.append("\nFases anteriores não se encontram fechadas");
            }
            case 3 -> voltar();
        }
        return str.toString();
    }

    private void automaticoAtribuido(StringBuilder str, List<Propostas> propostas){
        for (Propostas p : propostas){
            if (!p.isAtribuida()){
                if (p.getAluno() != null){
                    p.setAtribuida(true);
                }
            }
        }
        str.append("\nPropostas pré-atribuidas confirmadas com suceso");
    }

    private void automaticoNaoAtribuido(StringBuilder str, List<Propostas> propostas){
        List<Candidatura> candidaturas = phasesData.getCandidaturas();
        List<Propostas> propostasSemAluno = new ArrayList<>();
        List<Aluno> aux = new ArrayList<>();
        int n = 0;
        for (Propostas p : propostas){
            if (p.getAluno()==null){
                propostasSemAluno.add(p);
            }
        }
        for (Propostas p : propostasSemAluno){
            List<Aluno> alunos = new ArrayList<>();
            for (Candidatura c : candidaturas){
                for (String s : c.getCodigos()){
                    if(p.getCodigoId().equals(s)){
                        alunos.add(c.getAluno());
                    }
                }
            }
            aux.addAll(alunos);
            for (Aluno al : aux){
                if(p instanceof Estagio){
                    if (!al.getPodeAceder()){
                        alunos.remove(al);
                        continue;
                    }
                }
                for (Propostas p2 : propostas){
                    if(al.equals(p2.getAluno())){
                        alunos.remove(al);
                    }
                }
                for (SiglaRamo ramo : p.getRamo()){
                    if (ramo == al.getSiglaRamo()){
                        n = 1;
                    }
                }
                if (n != 1){
                    alunos.remove(al);
                }
                n=0;
            }
            aux.clear();
            List <IClassificacao> lstTemp = new ArrayList<>();
            for (Aluno al : alunos){
                if(al instanceof IClassificacao){
                    lstTemp.add(al);
                }
            }
            alunos.clear();
            Collections.sort(lstTemp, new IClassificacaoComparator());
            for (IClassificacao a : lstTemp){
                Aluno al = (Aluno) a;
                alunos.add(al);
            }
            if (!alunos.isEmpty()){
                if(alunos.size()>1){
                    if(alunos.get(0).getClassificacao()==alunos.get(1).getClassificacao()){
                        str.append("\nEncontrada situação de empate");
                        Empate empate= Empate.getInstance();
                        empate.setAlunosEmpatados(alunos);
                        empate.setProposta(p);
                        changeState(PhaseState.EMPATE);
                        return;
                    }else{
                        p.setAluno(alunos.get(0));
                        str.append("\nAluno: ").append(p.getAluno().getNumEstudante()).append(" adicionado á proposta: ").append(p.getCodigoId());
                        p.setAtribuida(true);
                    }
                }else{
                    p.setAluno(alunos.get(0));
                    str.append("\nAluno: ").append(p.getAluno().getNumEstudante()).append(" adicionado á proposta: ").append(p.getCodigoId());
                    p.setAtribuida(true);
                }

            }else{
                str.append("\nA proposta: ").append(p.getCodigoId()).append(" não possui alunos que cumpram os requesitos");
            }
        }
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
