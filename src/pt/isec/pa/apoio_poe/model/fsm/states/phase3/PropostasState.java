package pt.isec.pa.apoio_poe.model.fsm.states.phase3;

import pt.isec.pa.apoio_poe.model.CsvManager;
import pt.isec.pa.apoio_poe.model.data.PhasesData;
import pt.isec.pa.apoio_poe.model.data.Queries;
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
    public PropostasState(PhaseContext context, PhasesData phasesData) {
        super(context,phasesData);
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
        List<Candidatura> c = phasesData.getCandidaturas();
        List<Propostas> p = phasesData.getPropostas();
        if(Propostas.podeFecharFase3(c,p)){
            changeState(PhaseState.ATRIBUICAO_ORIENTADORES);
            phasesData.setFechado(3);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String query(Queries n) {
        StringBuilder str = new StringBuilder();
        List<Aluno> a;
        List<Propostas> p;
        List<Candidatura> c;
        List<Propostas> paux;
        System.out.println("TESTESTESTES");
        System.out.println(n);
        switch (n){
            case ALUNOS_COM_AUTOPROPOSTA:
                p = phasesData.getPropostas();
                for (Propostas pr:p){
                    if(pr instanceof EstProjAutoproposto){
                        str.append(pr.getAluno().toString());
                    }
                }
                break;
            case ALUNOS_COM_CANDIDATURA_REGISTADA:
                c = phasesData.getCandidaturas();
                for(Candidatura ca:c){
                    str.append(ca.getAluno().toString());
                }
                break;
            case ALUNOS_COM_PROPOSTA_ATRIBUIDA:
                p = phasesData.getPropostas();
                for(Propostas pa:p){
                    if(pa.isAtribuida()){
                        str.append(pa.getAluno());
                    }
                }
                break;
            case ALUNOS_SEM_PROPOSTA_ATRIBUIDA:
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
            case AUTOPROPOSTAS_DE_ALUNOS:
                p = phasesData.getPropostas();
                for(Propostas pa:p){
                    if(pa instanceof EstProjAutoproposto){
                        str.append(pa);
                    }
                }
                break;
            case PROPOSTAS_DOCENTES:
                p = phasesData.getPropostas();
                for(Propostas pa:p){
                    if(pa instanceof Projeto){
                        str.append(pa);
                    }
                }
                break;
            case PROPOSTAS_COM_CANDIDATURAS:
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
            case PROPOSTAS_SEM_CANDIDATURAS:
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
            case PROPOSTAS:
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


    @Override
    public boolean voltar() {
        changeState(PhaseState.CANDIDATURA);
        return true;
    }

    @Override
    public boolean avancar() {
        changeState(PhaseState.ATRIBUICAO_ORIENTADORES);
        return true;
    }

    @Override
    public String export(String nomeFicheiro) {
        List<Candidatura> candidaturas = phasesData.getCandidaturas();
        List<Aluno> alunos = phasesData.getAlunos();
        List<Propostas> propostas = phasesData.getPropostas();


        return CsvManager.writeFile(nomeFicheiro, Propostas.exportFase3(candidaturas,alunos,propostas));
    }
}
