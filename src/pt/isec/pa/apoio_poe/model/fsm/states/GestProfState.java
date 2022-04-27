package pt.isec.pa.apoio_poe.model.fsm.states;

import pt.isec.pa.apoio_poe.model.CsvManager;
import pt.isec.pa.apoio_poe.model.data.PhasesData;
import pt.isec.pa.apoio_poe.model.data.phase1.Aluno;
import pt.isec.pa.apoio_poe.model.data.phase1.Docente;
import pt.isec.pa.apoio_poe.model.fsm.PhaseContext;
import pt.isec.pa.apoio_poe.model.fsm.PhaseState;
import pt.isec.pa.apoio_poe.model.fsm.PhaseStateAdapter;

import java.util.List;

public class GestProfState extends PhaseStateAdapter{
    public GestProfState(PhasesData phasesData, PhaseContext context) {
        super(phasesData, context);
    }


    @Override
    public PhaseState getState() {
        return PhaseState.GEST_PROFESSOR;
    }

    @Override
    public void insert() {
        List<String> data = CsvManager.readFile("docentes_v2.csv");
        for (String s:data) System.out.println(s);
        List<Docente> docentes = phasesData.getDocentes();
        if(data!=null){
            for(int i=0;i<data.size();i+=2){
                Docente d = new Docente(
                        data.get(i),
                        data.get(i+1)
                );

                if(canBeAdded(d, docentes)){
                    docentes.add(d);
                }else{
                    System.out.println("ALUNO COM DADOS INVALIDOS");
                }
            }

        }else{
            System.out.println("NULL");
        }
    }
    private boolean canBeAdded(Docente d, List<Docente> docentes) {
        for(Docente a:docentes){
            System.out.println(a.getEmail());
            if(a.getEmail()==d.getEmail()) {
                return false;
            }
        }
        System.out.println("\n\n");
        return true;
    }
    @Override
    public String query() {
        List<Docente> docente = phasesData.getDocentes();
        StringBuilder str = new StringBuilder();
        for (Docente doc: docente) {
            str.append("\nNome: "+doc.getNome()+
                    "\nEmail: "+doc.getEmail()+
                    "\n\n");
        }
        return str.toString();
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
