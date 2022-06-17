package pt.isec.pa.apoio_poe.model.fsm.states.phase1;

import pt.isec.pa.apoio_poe.model.CsvManager;
import pt.isec.pa.apoio_poe.model.data.PhasesData;
import pt.isec.pa.apoio_poe.model.data.phase1.Aluno;
import pt.isec.pa.apoio_poe.model.data.phase1.Docente;
import pt.isec.pa.apoio_poe.model.fsm.PhaseContext;
import pt.isec.pa.apoio_poe.model.fsm.PhaseState;
import pt.isec.pa.apoio_poe.model.fsm.PhaseStateAdapter;

import java.util.ArrayList;
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
    public String insert(String nomeFicheiro) {
        String[][] data = CsvManager.readFile(nomeFicheiro);
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
    public String insertDocente(Docente d) {
        List<Docente> doc = phasesData.getDocentes();
        StringBuilder str=new StringBuilder();
        List<Aluno> alunos = phasesData.getAlunos();
        if(Docente.canBeAdded(d,doc,str,alunos)){
            doc.add(d);
        }
        return str.toString();
    }

    @Override
    public String editDocente(Docente d) {
        List<Docente> doc = phasesData.getDocentes();
        int i=0;
        for(Docente docente:doc){
            if(docente.getEmail()==d.getEmail()){
                doc.set(i,d);
                return "";
            }
            i++;
        }
        return "";
    }

    @Override
    public String deleteDocente(String email) {
        List<Docente> doc = phasesData.getDocentes();
        for(Docente docente:doc){
            if(docente.getEmail()==email){
                doc.remove(docente);
                return "";
            }
        }
        return "";
    }

    @Override
    public List<Docente> queryDocente() {
        List<Docente> docentes = phasesData.getDocentes();
        return docentes;
    }

    @Override
    public String query() {
        List<Docente> docentes = phasesData.getDocentes();
        return Docente.query(docentes);
    }

    @Override
    public String export(String nomeFicheiro) {
        List<Docente> docentes = phasesData.getDocentes();
        return CsvManager.writeFile(nomeFicheiro, Docente.export(docentes));
    }

    @Override
    public boolean voltar() {
        changeState(PhaseState.CONFIG);
        return true;
    }
}
