package pt.isec.pa.apoio_poe.model.fsm.states.phase1;

import pt.isec.pa.apoio_poe.model.CsvManager;
import pt.isec.pa.apoio_poe.model.data.PhasesData;
import pt.isec.pa.apoio_poe.model.data.phase1.Aluno;
import pt.isec.pa.apoio_poe.model.data.phase1.Docente;
import pt.isec.pa.apoio_poe.model.data.phase1.SiglaCurso;
import pt.isec.pa.apoio_poe.model.data.phase1.SiglaRamo;
import pt.isec.pa.apoio_poe.model.fsm.PhaseContext;
import pt.isec.pa.apoio_poe.model.fsm.PhaseState;
import pt.isec.pa.apoio_poe.model.fsm.PhaseStateAdapter;

import java.util.List;

public class GestAlunoState extends PhaseStateAdapter {
    public GestAlunoState(PhaseContext context,PhasesData phasesData) {
        super(context,phasesData);
    }
    @Override
    public String insert(){
        String data[][] = CsvManager.readFile("alunos.csv");
        StringBuilder str = new StringBuilder();
        List<Aluno> alunos = phasesData.getAlunos();
        List<Docente> docentes = phasesData.getDocentes();
        if(data!=null){
            str.append("ERROS:");
            Aluno a = Aluno.createAluno(data);
            if(Aluno.canBeAdded(a, alunos, str,docentes)){
                alunos.add(a);
            }
        }else{
            str.append("\nFicheiro não possui informação");
        }
        str.append("\n");
        return str.toString();
    }
    private Boolean parseBoolean(String s){
        s = s.toLowerCase();
        switch (s){
            case "true": return Boolean.TRUE;
            case "false":return Boolean.FALSE;
            default: return null;
        }
    }
    @Override
    public String query() {
        List<Aluno> alunos = phasesData.getAlunos();
        StringBuilder str = new StringBuilder();
        for (Aluno al : alunos) {
            str.append(al.toString());
        }
        return str.toString();
    }


    @Override
    public String export() {
        List<Aluno> alunos = phasesData.getAlunos();
        StringBuilder str = new StringBuilder();
        for (Aluno aluno : alunos){
            str.append(aluno.exportar());
        }
        if(str.length()!=0){
            str.deleteCharAt(str.length()-1);
        }
        return CsvManager.writeFile("alunos_export.csv", str);
    }

    @Override
    public PhaseState getState() {
        return PhaseState.GEST_ALUNO;
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
}
