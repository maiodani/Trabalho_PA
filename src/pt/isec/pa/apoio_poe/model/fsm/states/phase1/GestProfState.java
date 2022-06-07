package pt.isec.pa.apoio_poe.model.fsm.states.phase1;

import pt.isec.pa.apoio_poe.model.CsvManager;
import pt.isec.pa.apoio_poe.model.data.PhasesData;
import pt.isec.pa.apoio_poe.model.data.phase1.Aluno;
import pt.isec.pa.apoio_poe.model.data.phase1.Docente;
import pt.isec.pa.apoio_poe.model.fsm.PhaseContext;
import pt.isec.pa.apoio_poe.model.fsm.PhaseState;
import pt.isec.pa.apoio_poe.model.fsm.PhaseStateAdapter;

import java.util.List;

public class GestProfState extends PhaseStateAdapter{
    public GestProfState(PhaseContext context,PhasesData phasesData) {
        super(context,phasesData);
    }


    @Override
    public PhaseState getState() {
        return PhaseState.GEST_PROFESSOR;
    }

    @Override
    public String insert() {
        String[][] data = CsvManager.readFile("docentes.csv");
        StringBuilder str = new StringBuilder();
        List<Docente> docentes = phasesData.getDocentes();
        List<Aluno> alunos = phasesData.getAlunos();
        if(data!=null){
            str.append("ERROS:");
            str = Docente.createDocentes(data,docentes,str,alunos);
        }else{
            str.append("\nFicheiro não possui informação");
        }
        str.append("\n");
        return str.toString();
    }

    @Override
    public String query() {
        List<Docente> docentes = phasesData.getDocentes();
        return Docente.query(docentes);
    }

    @Override
    public String export() {
        List<Docente> docentes = phasesData.getDocentes();
        return CsvManager.writeFile("docentes_export.csv", Docente.export(docentes));
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
