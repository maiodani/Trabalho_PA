package pt.isec.pa.apoio_poe.model.fsm.states.phase2;

import pt.isec.pa.apoio_poe.model.CsvManager;
import pt.isec.pa.apoio_poe.model.data.PhasesData;
import pt.isec.pa.apoio_poe.model.data.phase1.Aluno;
import pt.isec.pa.apoio_poe.model.data.phase1.Propostas;
import pt.isec.pa.apoio_poe.model.data.phase1.SiglaRamo;
import pt.isec.pa.apoio_poe.model.data.phase1.propostas.EstProjAutoproposto;
import pt.isec.pa.apoio_poe.model.data.phase1.propostas.Projeto;
import pt.isec.pa.apoio_poe.model.data.phase2.Candidatura;
import pt.isec.pa.apoio_poe.model.fsm.PhaseContext;
import pt.isec.pa.apoio_poe.model.fsm.PhaseState;
import pt.isec.pa.apoio_poe.model.fsm.PhaseStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class CadidaturaState extends PhaseStateAdapter {
    public CadidaturaState(PhasesData phasesData, PhaseContext context) {
        super(phasesData, context);
    }

    @Override
    public PhaseState getState() {
        return PhaseState.CANDIDATURA;
    }

    @Override
    public boolean fecharFase() {
        changeState(PhaseState.CANDIDATURA);
        return true;
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
    public String query(int n) {
        StringBuilder str = new StringBuilder();
        List<Candidatura> c;
        List<Propostas> propostas;
        List<Propostas> p;
        switch (n){
            case 1:
                propostas = phasesData.getPropostas();
                for(Propostas p:propostas){
                    if(p instanceof EstProjAutoproposto){
                        str.append(p.getAluno().toString());
                    }
                }
                break;
            case 2:
                 c = phasesData.getCandidaturas();
                for(Candidatura ca:c){
                    str.append(ca.getAluno().toString());
                }
                break;
            case 3:
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
            case 4:
                propostas = phasesData.getPropostas();
                for(Propostas p:propostas){
                    if(p instanceof EstProjAutoproposto){
                        str.append(p);
                    }
                }
                break;
            case 5:
                propostas = phasesData.getPropostas();
                for(Propostas p:propostas){
                    if(p instanceof Projeto){
                        str.append(p);
                    }
                }
                break;
            case 6:
                c = phasesData.getCandidaturas();
                propostas = phasesData.getPropostas();
                p= new ArrayList<>();
                for(Candidatura ca:c){
                    for(String s:ca.getCodigos()){
                        for(Propostas pro:propostas){
                            if(pro.getCodigoId().equals(s)){
                                if(!p.contains(pro)){
                                    p.add(pro);
                                }
                            }
                        }
                    }
                }

                for(Propostas aux:p){
                    str.append(aux);
                }
                break;
            case 7:
                c = phasesData.getCandidaturas();
                propostas = phasesData.getPropostas();
                p= new ArrayList<>();
                for(Candidatura ca:c){
                    for(String s:ca.getCodigos()){
                        for(Propostas pro:propostas){
                            if(pro.getCodigoId().equals(s)){
                                if(!p.contains(pro)){
                                    p.add(pro);
                                }
                            }
                        }
                    }
                }

                for(Propostas aux:propostas)
                    
                }

                break;
            case 8:
                break;
            default:
                break;

        }
        return str.toString();
    }

    @Override
    public String query() {
        List<Candidatura> candidaturas = phasesData.getCandidaturas();

        StringBuilder str = new StringBuilder();
        for (Candidatura c : candidaturas) {
            str.append("N: "+c.getAluno().getNumEstudante()+
                    "\nCodigos: "+c.getCodigos()+
                    "\n\n");
        }
        return str.toString();
        /*
        for(Candidatura c :candidaturas){
            System.out.println(c.getAluno()+" "+c.getCodigos());
        }*/
    }

    @Override
    public String insert() {
        String [][] data = CsvManager.readFile("candidaturas_v2.csv");
        List<Candidatura> candidaturas = phasesData.getCandidaturas();
        StringBuilder str = new StringBuilder();

        if(data!=null){
            str.append("ERROS:");
            for(int i=0;i<data.length;i++){
                Candidatura c = new Candidatura(
                        temAluno(data[i][0]),
                        getCodigosPropostas(data[i])
                );

                if(canAdd(c,candidaturas,str)){
                    candidaturas.add(c);
                }
            }
        }
        return str.toString();
    }

    @Override
    public String export() {
        return null;
    }

    private boolean canAdd(Candidatura c, List<Candidatura> candidaturas, StringBuilder str) {

        if(c.getAluno()==null) {
            str.append("\nAluno não foi encontrado.");
            return false;
        }
        if(c.getCodigos()==null){
            str.append("\nCandidatura do aluno ").append(c.getAluno().getNumEstudante()).append(" não tem propostas");
            return false;
        }
        List<Propostas> propostas = phasesData.getPropostas();

        for(Propostas p:propostas){
            for(String co:c.getCodigos()){
                if(p.getCodigoId().equals(co)){
                    if(p.getAluno()!=null){
                        str.append("\nCandidatura a Proposta ").append(c.getCodigos()).append(" já tem aluno associado.");
                        return false;
                    }
                }
            }
        }

        for(Propostas p:propostas){
            if(p.getAluno()!=null) {
                if (c.getAluno().getNumEstudante() == p.getAluno().getNumEstudante()) {
                    str.append("\nAluno ").append(c.getAluno().getNumEstudante()).append(" já tem autoproposta ou proposta.");
                    return false;
                }
            }
        }

        for(Candidatura ca :candidaturas){
            if(ca.getAluno().getNumEstudante()==c.getAluno().getNumEstudante()){
                str.append("\nAluno ").append(c.getAluno().getNumEstudante()).append(" já tem candidatura");
                return false;
            }
        }
        //TODO ACABAR RESTRICOES DE ERRO
        return true;
    }

    private List<String> getCodigosPropostas(String[] data) {
        List<String> aux = new ArrayList<>();
        if(data.length<=1)return null;
        for(int i=1;i< data.length;i++){
            aux.add(data[i]);
        }
        return aux;
    }

    private Aluno temAluno(String n){
        List<Aluno> alunos = phasesData.getAlunos();
        for(Aluno aluno : alunos){
            if (Integer.parseInt(n) == (aluno.getNumEstudante())){
                return aluno;
            }
        }
        return null;
    }
}
