package pt.isec.pa.apoio_poe.model.data.phase3;

import pt.isec.pa.apoio_poe.model.data.phase1.Aluno;
import pt.isec.pa.apoio_poe.model.data.phase1.Propostas;
import pt.isec.pa.apoio_poe.model.data.phase1.SiglaRamo;
import pt.isec.pa.apoio_poe.model.data.phase1.propostas.Estagio;
import pt.isec.pa.apoio_poe.model.data.phase2.Candidatura;
import pt.isec.pa.apoio_poe.model.fsm.PhaseState;
import pt.isec.pa.apoio_poe.model.fsm.states.phase3.IClassificacao;
import pt.isec.pa.apoio_poe.model.fsm.states.phase3.IClassificacaoComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Automatico {

    static public boolean automaticoNaoAtribuido(StringBuilder str, List<Propostas> propostas,List<Candidatura> candidaturas){
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

                        return true;
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
        return false;
    }

    static public void automaticoAtribuido(StringBuilder str, List<Propostas> propostas){
        for (Propostas p : propostas){
            if (!p.isAtribuida()){
                if (p.getAluno() != null){
                    p.setAtribuida(true);
                }
            }
        }
        str.append("\nPropostas pré-atribuidas confirmadas com suceso");
    }
}
