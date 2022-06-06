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
            for(int i=0;i<data.length;i++){
                Docente d = new Docente(
                        data[i][0],
                        data[i][1]
                );
                if(Docente.canBeAdded(d, docentes, str,alunos)){
                    docentes.add(d);
                }
            }
        }else{
            str.append("\nFicheiro não possui informação");
        }
        str.append("\n");
        return str.toString();
    }

    @Override
    public String query() {
        List<Docente> docentes = phasesData.getDocentes();
        StringBuilder str = new StringBuilder();
        for (Docente docente: docentes) {
            str.append(docente.toString());
        }
        return str.toString();
    }

    @Override
    public String export() {
        List<Docente> docentes = phasesData.getDocentes();
        StringBuilder str = new StringBuilder();
        for (Docente docente: docentes){
            str.append(docente.exportar());
        }
        if(str.length()!=0) {
            str.deleteCharAt(str.length() - 1);
        }
        return CsvManager.writeFile("docentes_export.csv", str);
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
