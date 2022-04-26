package pt.isec.pa.apoio_poe.model.fsm.states;

import pt.isec.pa.apoio_poe.model.CsvManager;
import pt.isec.pa.apoio_poe.model.data.PhasesData;
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
        List<Docente> docentes = phasesData.getDocentes();
        if(data!=null){
            if((data.size())%7==0){ //CADA LINHA TEM 7 VALORES
                for(int i=0;i<data.size();i+=7){
                    Docente d = new Docente(
                            data.get(i),
                            data.get(i+1)
                    );
                    System.out.println(d);
                    //TODO COLOCAR AS VERIFICAÇÕES PARA OS PROFESSORES
                    docentes.add(d);
                    /*
                    if(canBeAdded(a, alunos)){
                        alunos.add(a);
                    }else{
                        System.out.println("ALUNO COM DADOS INVALIDOS");
                    }*/
                }
            }
        }else{
            System.out.println("NULL");
        }
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
