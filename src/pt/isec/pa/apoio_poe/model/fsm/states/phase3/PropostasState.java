package pt.isec.pa.apoio_poe.model.fsm.states.phase3;

import pt.isec.pa.apoio_poe.model.data.PhasesData;
import pt.isec.pa.apoio_poe.model.data.phase1.Aluno;
import pt.isec.pa.apoio_poe.model.data.phase1.Propostas;
import pt.isec.pa.apoio_poe.model.data.phase1.propostas.EstProjAutoproposto;
import pt.isec.pa.apoio_poe.model.data.phase1.propostas.Projeto;
import pt.isec.pa.apoio_poe.model.data.phase2.Candidatura;
import pt.isec.pa.apoio_poe.model.fsm.PhaseContext;
import pt.isec.pa.apoio_poe.model.fsm.PhaseState;
import pt.isec.pa.apoio_poe.model.fsm.PhaseStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class PropostasState extends PhaseStateAdapter {
    public PropostasState(PhasesData phasesData, PhaseContext context) {
        super(phasesData, context);
    }

    @Override
    public String export() {
        return null;
    }

    @Override
    public PhaseState getState() {
        return PhaseState.PROPOSTAS;
    }

    @Override
    public boolean iniciar(int op) {
        switch (op){
            case 1:
                changeState(PhaseState.AUTOMATICO);break;
            case 2:
                changeState(PhaseState.MANUAL);break;
            default: return false;
        }
        return true;
    }

    @Override
    public boolean fecharFase() {
        changeState(PhaseState.ATRIBUICAO_ORIENTADORES);
        return true;
    }

    @Override
    public boolean voltar() {
        changeState(PhaseState.CANDIDATURA);
        return true;
    }
    @Override
    public String query(int n) {
        StringBuilder str = new StringBuilder();
        List<Aluno> a;
        List<Propostas> p;
        List<Candidatura> c;
        List<Propostas> paux;
        switch (n){
            case 0:
                p = phasesData.getPropostas();
                for (Propostas pr:p){
                    if(pr instanceof EstProjAutoproposto){
                        str.append(pr.getAluno().toString());
                    }
                }
                break;
            case 1:
                c = phasesData.getCandidaturas();
                for(Candidatura ca:c){
                    str.append(ca.getAluno().toString());
                }
                break;
            case 2:
                p = phasesData.getPropostas();
                //TODO PENSAR NISTO
                for(Propostas pa:p){

                }
                break;
            case 3:
                p = phasesData.getPropostas();
                a = phasesData.getAlunos();
                List<Aluno> al = new ArrayList<>();

                for(Propostas pa:p){
                    if(pa.getAluno()!=null){
                        al.add(pa.getAluno());
                    }
                }
                for(Aluno aluno:a){
                    if(!al.contains(aluno)){
                        str.append(aluno.toString());
                    }
                }
                break;
            case 4:
                p = phasesData.getPropostas();
                for(Propostas pa:p){
                    if(pa instanceof EstProjAutoproposto){
                        str.append(pa);
                    }
                }
                break;
            case 5:
                p = phasesData.getPropostas();
                for(Propostas pa:p){
                    if(pa instanceof Projeto){
                        str.append(pa);
                    }
                }
                break;
            case 6:
                c = phasesData.getCandidaturas();
                p = phasesData.getPropostas();
                paux= new ArrayList<>();
                for(Candidatura ca:c){
                    for(String s:ca.getCodigos()){
                        for(Propostas pro:p){
                            if(pro.getCodigoId().equals(s)){
                                if(!paux.contains(pro)){
                                    paux.add(pro);
                                }
                            }
                        }
                    }
                }

                for(Propostas aux:paux){
                    str.append(aux);
                }
                break;
            case 7:
                c = phasesData.getCandidaturas();
                p = phasesData.getPropostas();
                paux= new ArrayList<>();
                for(Candidatura ca:c){
                    for(String s:ca.getCodigos()){
                        for(Propostas pro:p){
                            if(pro.getCodigoId().equals(s)){
                                if(!paux.contains(pro)){
                                    paux.add(pro);
                                }
                            }
                        }
                    }
                }

                for(Propostas aux:p){
                    if(!paux.contains(aux)){
                        str.append(aux.toString());
                    }
                }
                break;
            case 8:
                p = phasesData.getPropostas();
                for(Propostas pa:p){
                    str.append(pa.toString());
                }
                break;
            default:
                break;
        }
        return str.toString();
    }
}
