package pt.isec.pa.apoio_poe.model.fsm.states;

import pt.isec.pa.apoio_poe.model.CsvManager;
import pt.isec.pa.apoio_poe.model.data.PhasesData;
import pt.isec.pa.apoio_poe.model.data.phase1.Aluno;
import pt.isec.pa.apoio_poe.model.data.phase1.Propostas;
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
    public String query() {
        List<Candidatura> candidaturas = phasesData.getCandidaturas();

        for(Candidatura c :candidaturas){
            System.out.println(c.getAluno()+" "+c.getCodigos());
        }
        return "";
    }

    @Override
    public String insert() {
        String [][] data = CsvManager.readFile("candidaturas.csv");
        List<Candidatura> candidaturas = phasesData.getCandidaturas();
        StringBuilder str = new StringBuilder();
        for(int i=0;i<data.length;i++){
            for(int j=0;j<data[i].length;j++){
                System.out.print(data[i][j]+" ");
            }
            System.out.println();
        }

        if(data!=null){
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
        return "";
    }

    private boolean canAdd(Candidatura c, List<Candidatura> candidaturas,StringBuilder str) {
        if(c.getAluno()==null) {
            str.append("\nAluno ").append(c.getAluno().getNumEstudante()).append(" não foi encontrado.");
            return false;
        }
        List<Propostas> propostas = phasesData.getPropostas();
        for(Propostas p: propostas){
            if(p.getAluno().getNumEstudante()==c.getAluno().getNumEstudante()){
                str.append("\nAluno ").append(c.getAluno().getNumEstudante()).append(" já tem proposta.");
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
