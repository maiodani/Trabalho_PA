package pt.isec.pa.apoio_poe.model.fsm.states.phase2;

import pt.isec.pa.apoio_poe.model.CsvManager;
import pt.isec.pa.apoio_poe.model.data.PhasesData;
import pt.isec.pa.apoio_poe.model.data.phase1.Aluno;
import pt.isec.pa.apoio_poe.model.data.phase1.Propostas;
import pt.isec.pa.apoio_poe.model.data.phase1.propostas.EstProjAutoproposto;
import pt.isec.pa.apoio_poe.model.data.phase1.propostas.Projeto;
import pt.isec.pa.apoio_poe.model.data.phase2.Candidatura;
import pt.isec.pa.apoio_poe.model.fsm.PhaseContext;
import pt.isec.pa.apoio_poe.model.fsm.PhaseState;
import pt.isec.pa.apoio_poe.model.fsm.PhaseStateAdapter;
import pt.isec.pa.apoio_poe.model.data.Queries;

import java.util.ArrayList;
import java.util.List;

public class CadidaturaState extends PhaseStateAdapter {
    public CadidaturaState(PhaseContext context,PhasesData phasesData) {
        super(context,phasesData);
    }

    @Override
    public PhaseState getState() {
        return PhaseState.CANDIDATURA;
    }

    @Override
    public boolean fecharFase() {
        if(phasesData.getFechado()==1){
            changeState(PhaseState.PROPOSTAS);
            phasesData.setFechado(2);
            return true;
        }
        return false;
    }

    @Override
    public boolean voltar() {
        changeState(PhaseState.CONFIG);
        return true;
    }

    @Override
    public boolean avancar() {
        changeState(PhaseState.PROPOSTAS);
        return true;
    }

    @Override
    public String query(Queries n) {
        StringBuilder str = new StringBuilder();
        List<Candidatura> c;
        List<Propostas> propostas;
        List<Propostas> pa;

        switch (n){
            case ALUNOS_COM_AUTOPROPOSTA:
                propostas = phasesData.getPropostas();

                for(Propostas p:propostas){
                    if(p instanceof EstProjAutoproposto){
                        str.append(p.getAluno().toString());
                    }
                }
                break;
            case ALUNOS_COM_CANDIDATURA_REGISTADA:
                c = phasesData.getCandidaturas();
                for(Candidatura ca:c){
                    str.append(ca.getAluno().toString());
                }
                break;
            case ALUNOS_SEM_CANDIDATURA:
                c = phasesData.getCandidaturas();
                List<Aluno> alTodos = phasesData.getAlunos();
                List<Aluno> al = new ArrayList<>();

                for(Candidatura ca:c){//TODOS OS ALUNOS QUE JÁ TEM CANDIDATURA
                    al.add(ca.getAluno());
                }

                propostas = phasesData.getPropostas();
                for(Propostas p:propostas){//TODOS OS ALUNOS QUE JA TEM AUTO PROPOSTAS (SUPONDO QUE ESTE TAMBEM SAO CONSIDERADOS QUE JÁ "TEM" CANDIDATURA
                    if(p instanceof EstProjAutoproposto){
                        al.add(p.getAluno());
                    }
                }

                boolean canAdd = true;
                for(Aluno a:alTodos){
                    for(Aluno b:al){
                        if(a.getNumEstudante()==b.getNumEstudante()){
                            canAdd=false;
                        }
                    }
                    if(canAdd){
                        str.append(a.toString());
                    }else{
                        canAdd=true;
                    }
                }
                break;
            case AUTOPROPOSTAS_DE_ALUNOS:
                propostas = phasesData.getPropostas();
                for(Propostas p:propostas){
                    if(p instanceof EstProjAutoproposto){
                        str.append(p);
                    }
                }
                break;
            case PROPOSTAS_DOCENTES:
                propostas = phasesData.getPropostas();
                for(Propostas p:propostas){
                    if(p instanceof Projeto){
                        str.append(p);
                    }
                }
                break;
            case PROPOSTAS_COM_CANDIDATURAS:
                c = phasesData.getCandidaturas();
                propostas = phasesData.getPropostas();
                pa= new ArrayList<>();
                for(Candidatura ca:c){
                    for(String s:ca.getCodigos()){
                        for(Propostas pro:propostas){
                            if(pro.getCodigoId().equals(s)){
                                if(!pa.contains(pro)){
                                    pa.add(pro);
                                }
                            }
                        }
                    }
                }

                for(Propostas aux:pa){
                    str.append(aux);
                }
                break;
            case PROPOSTAS_SEM_CANDIDATURAS:
                c = phasesData.getCandidaturas();
                propostas = phasesData.getPropostas();
                pa= new ArrayList<>();
                for(Candidatura ca:c){
                    for(String s:ca.getCodigos()){
                        for(Propostas pro:propostas){
                            if(pro.getCodigoId().equals(s)){
                                if(!pa.contains(pro)){
                                    pa.add(pro);
                                }
                            }
                        }
                    }
                }

                for(Propostas aux:propostas){
                    if(!pa.contains(aux)){
                        str.append(aux.toString());
                    }
                }
                break;
            case PROPOSTAS:
                propostas = phasesData.getPropostas();
                for(Propostas p:propostas){
                    str.append(p.toString());
                }
                break;
            default:
                break;

        }
        return str.toString();
    }

    @Override
    public String query() {
        List<Candidatura> candidaturas = phasesData.getCandidaturas();
        return Candidatura.query(candidaturas);
    }

    @Override
    public String insert(String nomeFicheiro) {
        String [][] data = CsvManager.readFile(nomeFicheiro);
        List<Candidatura> candidaturas = phasesData.getCandidaturas();
        StringBuilder str = new StringBuilder();
        List<Propostas> propostas = phasesData.getPropostas();
        List<Aluno> alunos = phasesData.getAlunos();
        if(data!=null){
            str.append("ERROS:");
            str = Candidatura.createCandidaturas(data,alunos,candidaturas,str,propostas);
        }
        return str.toString();
    }

    @Override
    public String export(String nomeFicheiro) {
        List<Candidatura> candidaturas = phasesData.getCandidaturas();
        return CsvManager.writeFile(nomeFicheiro, Candidatura.export(candidaturas));
    }
}
