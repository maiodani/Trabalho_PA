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

import java.util.ArrayList;
import java.util.List;

public class GestAlunoState extends PhaseStateAdapter {
    public GestAlunoState(PhaseContext context,PhasesData phasesData) {
        super(context,phasesData);
    }
    @Override
    public String insert(String nomeFicheiro){
        String data[][] = CsvManager.readFile(nomeFicheiro);
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
    public String insertAluno(Aluno a) {
        List<Aluno> al = phasesData.getAlunos();
        StringBuilder str=new StringBuilder();
        List<Docente> d = new ArrayList<>();
        if(Aluno.canBeAdded(a,al,str,d)){
            al.add(a);
        }
        return str.toString();
    }

    @Override
    public String editAluno(Aluno a) {
        List<Aluno> al = phasesData.getAlunos();
        int i=0;
        for(Aluno aluno:al){
            if(aluno.getNumEstudante()==a.getNumEstudante()){
                al.set(i,a);
                return "";
            }
            i++;
        }
        return "";
    }

    @Override
    public String deleteAluno(Long numEstudante) {
        List<Aluno> al = phasesData.getAlunos();
        for(Aluno aluno:al){
            if(aluno.getNumEstudante()==numEstudante){
                al.remove(aluno);
                return "";
            }
        }
        return "";
    }

    @Override
    public String query() {
        List<Aluno> alunos = phasesData.getAlunos();
        return Aluno.query(alunos);
    }

    @Override
    public List<Aluno> queryAluno() {
        List<Aluno> a = phasesData.getAlunos();
        if(a.isEmpty()){
            return null;
        }else{
            return a;
        }
    }

    @Override
    public String export(String nomeFicheiro) {
        List<Aluno> alunos = phasesData.getAlunos();
        return CsvManager.writeFile(nomeFicheiro, Aluno.export(alunos));
    }

    @Override
    public PhaseState getState() {
        return PhaseState.GEST_ALUNO;
    }


    @Override
    public boolean voltar() {
        changeState(PhaseState.CONFIG);
        return true;
    }
}
