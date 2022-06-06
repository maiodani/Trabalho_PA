package pt.isec.pa.apoio_poe.model.data.phase3;

import pt.isec.pa.apoio_poe.model.data.phase1.Aluno;
import pt.isec.pa.apoio_poe.model.data.phase1.Propostas;

import java.util.List;

public class Manual {
    static public void obterListas(List<Propostas> propostasSemAlunos, List<Aluno> alunosSemPropostas,List<Propostas> propostas,List<Aluno> alunos){
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
}
