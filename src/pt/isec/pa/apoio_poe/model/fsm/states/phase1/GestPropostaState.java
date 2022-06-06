package pt.isec.pa.apoio_poe.model.fsm.states.phase1;

import pt.isec.pa.apoio_poe.model.CsvManager;
import pt.isec.pa.apoio_poe.model.data.PhasesData;
import pt.isec.pa.apoio_poe.model.data.phase1.*;
import pt.isec.pa.apoio_poe.model.data.phase1.propostas.*;
import pt.isec.pa.apoio_poe.model.fsm.PhaseContext;
import pt.isec.pa.apoio_poe.model.fsm.PhaseState;
import pt.isec.pa.apoio_poe.model.fsm.PhaseStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class GestPropostaState extends PhaseStateAdapter {
    public GestPropostaState(PhaseContext context,PhasesData phasesData) {
        super(context,phasesData);
    }

    @Override
    public PhaseState getState() {
        return PhaseState.GEST_PROPOSTA;
    }

    @Override
    public String insert() {
        String[][] data = CsvManager.readFile("propostas.csv");
        List<Propostas> propostas = phasesData.getPropostas();
        StringBuilder str = new StringBuilder();
        Propostas p = null;
        if(data!=null){
            str.append("ERROS:");
            for(int i=0; i<data.length; i++) {
                if (data[i][0].equals("T1")) {
                    List<Aluno> alunos = phasesData.getAlunos();
                    p = new Estagio(
                            Estagio.temAluno(data[i],alunos),
                            Estagio.variosRamos(data[i][2]),
                            data[i][3],
                            data[i][1],
                            data[i][4]
                    );
                } else if (data[i][0].equals("T2")) {
                    List<Docente> docentes = phasesData.getDocentes();
                    List<Aluno> alunos = phasesData.getAlunos();
                    p = new Projeto(
                            Projeto.temAluno(data[i],alunos),
                            Projeto.adicionarProfessor(data[i][4],docentes),
                            Projeto.variosRamos(data[i][2]),
                            data[i][3],
                            data[i][1]
                    );
                } else if (data[i][0].equals("T3")) {
                    Aluno al = null;
                    List<Aluno> alunos = phasesData.getAlunos();
                    for(Aluno aluno : alunos){
                        if (Integer.parseInt(data[i][3]) == (aluno.getNumEstudante())) al = aluno;
                    }
                    p = new EstProjAutoproposto(
                            al,
                            data[i][2],
                            data[i][1]
                    );
                }
                if (Propostas.canBeAdded(p, propostas, str)){
                    propostas.add(p);
                }

            }
        }
        str.append("\n");
        return str.toString();
    }
    public String query() {
        List<Propostas> propostas = phasesData.getPropostas();
        StringBuilder str = new StringBuilder();
        for (Propostas proposta : propostas) {
            str.append(proposta.toString());
        }
        return str.toString();
    }

    @Override
    public String export() {
        List<Propostas> propostas = phasesData.getPropostas();
        StringBuilder str = new StringBuilder();
        for (Propostas proposta : propostas){
            str.append(proposta.exportar());
        }
        if(str.length()!=0) {
            str.deleteCharAt(str.length() - 1);
        }
        return CsvManager.writeFile("propostas_export.csv", str);
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
