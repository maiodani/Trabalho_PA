package pt.isec.pa.apoio_poe.model.fsm.states.phase3;

import pt.isec.pa.apoio_poe.model.data.PhasesData;
import pt.isec.pa.apoio_poe.model.data.phase1.Aluno;
import pt.isec.pa.apoio_poe.model.data.phase1.Propostas;
import pt.isec.pa.apoio_poe.model.data.phase2.Candidatura;
import pt.isec.pa.apoio_poe.model.data.phase3.Empate;
import pt.isec.pa.apoio_poe.model.fsm.PhaseContext;
import pt.isec.pa.apoio_poe.model.fsm.PhaseState;
import pt.isec.pa.apoio_poe.model.fsm.PhaseStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class EmpateState extends PhaseStateAdapter {

    public EmpateState(PhaseContext context,PhasesData phasesData) {
        super(context,phasesData);
    }

    @Override
    public String query() {
        Empate empate = phasesData.getEmpate();
        StringBuilder str = new StringBuilder();
        List<Aluno> alunos = empate.getAlunosEmpatados();
        List<Aluno> aux = new ArrayList<>();

        str.append("\nProposta: ").append(empate.getProposta());
        str.append("\nAlunos: ");

        double num = alunos.get(0).getClassificacao();
        aux.addAll(alunos);
        for (Aluno al : aux){
            if (num != al.getClassificacao()){
                alunos.remove(al);
            }
        }
        for (Aluno al : alunos){
            str.append(al.toString());
        }

        return str.toString();
    }

    @Override
    public String insert(int op) {
        Empate empate = phasesData.getEmpate();
        StringBuilder str = new StringBuilder();
        List<Aluno> alunos = empate.getAlunosEmpatados();
        List<Propostas> propostas = phasesData.getPropostas();
        int flag = 1;

        for (Propostas  p : propostas){
            if(p.getCodigoId().equals(empate.getProposta().getCodigoId())){
                for (Aluno al : alunos){
                    if(al.getNumEstudante() == op){
                        p.setAluno(al);
                        p.setAtribuida(true);
                        str.append("Aluno: ").append(al.getNumEstudante()).append(" escolhido para a proposta ").append(p.getCodigoId());
                        flag=0;
                    }
                }
                if(flag==1){
                    str.append("\nNumero do aluno inválido, abortar toda a operação");
                }
                changeState(PhaseState.AUTOMATICO);
            }
        }
        empate.setProposta(null);
        empate.getAlunosEmpatados().clear();
        return str.toString();
    }

    @Override
    public PhaseState getState() {
        return PhaseState.EMPATE;
    }


}
