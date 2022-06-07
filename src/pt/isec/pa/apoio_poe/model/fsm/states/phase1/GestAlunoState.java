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
            str=Aluno.createAlunos(data,alunos,str,docentes);

        }else{
            str.append("\nFicheiro não possui informação");
        }
        str.append("\n");
        return str.toString();
    }
    @Override
    public String query() {
        List<Aluno> alunos = phasesData.getAlunos();
        return Aluno.query(alunos);
    }


    @Override
    public String export() {
        List<Aluno> alunos = phasesData.getAlunos();
        return CsvManager.writeFile("alunos_export.csv", Aluno.export(alunos));
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
