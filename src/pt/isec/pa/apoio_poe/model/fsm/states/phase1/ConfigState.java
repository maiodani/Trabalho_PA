package pt.isec.pa.apoio_poe.model.fsm.states.phase1;

import pt.isec.pa.apoio_poe.model.data.PhasesData;
import pt.isec.pa.apoio_poe.model.data.phase1.Aluno;
import pt.isec.pa.apoio_poe.model.data.phase1.Propostas;
import pt.isec.pa.apoio_poe.model.data.phase1.SiglaRamo;
import pt.isec.pa.apoio_poe.model.data.phase1.propostas.EstProjAutoproposto;
import pt.isec.pa.apoio_poe.model.data.phase1.propostas.Estagio;
import pt.isec.pa.apoio_poe.model.data.phase1.propostas.Projeto;
import pt.isec.pa.apoio_poe.model.fsm.PhaseContext;
import pt.isec.pa.apoio_poe.model.fsm.PhaseState;
import pt.isec.pa.apoio_poe.model.fsm.PhaseStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class ConfigState extends PhaseStateAdapter {
    public ConfigState(PhasesData phasesData, PhaseContext context) {
        super(phasesData, context);
    }


    @Override
    public PhaseState getState() {
        return PhaseState.CONFIG;
    }

    @Override
    public boolean iniciar(int op) {
        switch (op){
            case 1 -> changeState(PhaseState.GEST_ALUNO);
            case 2 -> changeState(PhaseState.GEST_PROFESSOR);
            case 3 -> changeState(PhaseState.GEST_PROPOSTA);
            default -> {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean avancar() {
        changeState(PhaseState.CANDIDATURA);
        return true;
    }

    private boolean podeFechar(){
        List<Aluno> alunos = phasesData.getAlunos();
        List<Propostas> propostas = phasesData.getPropostas();
        int nAlunosDA=0,nAlunosRAS=0,nAlunosSI=0,nProjetosDA=0,nProjetosRAS=0,nProjetosSI=0;
        for(Aluno a: alunos){
            switch(a.getSiglaRamo()){
                case DA : nAlunosDA++; break;
                case SI : nAlunosSI++; break;
                case RAS : nAlunosRAS++; break;
            }
        }

        for(Propostas p: propostas){
            List<SiglaRamo> ramos = new ArrayList<>();
            if(p instanceof Estagio){
                //System.out.println("ESTAGIO");
                Estagio aux = (Estagio) p;
                ramos=aux.getRamo();
            }else if (p instanceof Projeto) {
                  //  System.out.println("PROJETO");
                    Projeto aux = (Projeto) p;
                    ramos = aux.getRamo();
            }else if(p instanceof EstProjAutoproposto){
                continue;
            }
            for(SiglaRamo r:ramos){
                switch (r){
                    case RAS -> nProjetosRAS++;
                    case DA -> nProjetosDA++;
                    case SI -> nProjetosSI++;
                }
            }

        }
        //System.out.printf("\nNº ALUNOS DO RAMO: DA -> %d SI -> %d RAS -> %d",nAlunosDA,nAlunosSI,nAlunosRAS);
        //System.out.printf("\nNº PROJETOS DO RAMO: DA -> %d SI -> %d RAS -> %d",nProjetosDA,nProjetosSI,nProjetosRAS);

        if(nProjetosDA>=nAlunosDA && nProjetosSI>=nAlunosSI && nProjetosRAS>=nAlunosRAS){
            return true;
        }


        return false;
    }
    @Override
    public boolean fecharFase() {
        if(podeFechar()){
           phasesData.setFechado(1);
           return true;
        }

        changeState(PhaseState.CANDIDATURA);
        return false;
    }

    @Override
    public String export() {
        return null;
    }
}
