package pt.isec.pa.apoio_poe.model.fsm.states;

import pt.isec.pa.apoio_poe.model.CsvManager;
import pt.isec.pa.apoio_poe.model.data.PhasesData;
import pt.isec.pa.apoio_poe.model.data.phase1.Aluno;
import pt.isec.pa.apoio_poe.model.data.phase1.Docente;
import pt.isec.pa.apoio_poe.model.data.phase1.Propostas;
import pt.isec.pa.apoio_poe.model.data.phase1.SiglaRamo;
import pt.isec.pa.apoio_poe.model.data.phase1.propostas.Estagio;
import pt.isec.pa.apoio_poe.model.data.phase1.propostas.Projeto;
import pt.isec.pa.apoio_poe.model.fsm.PhaseContext;
import pt.isec.pa.apoio_poe.model.fsm.PhaseState;
import pt.isec.pa.apoio_poe.model.fsm.PhaseStateAdapter;

import java.util.List;

public class GestPropostaState extends PhaseStateAdapter {
    public GestPropostaState(PhasesData phasesData, PhaseContext context) {
        super(phasesData, context);
    }

    @Override
    public PhaseState getState() {
        return PhaseState.GEST_PROPOSTA;
    }

    @Override
    public void insert() {
        List<String> data = CsvManager.readFile("proposta_v2.csv");
        List<Propostas> propostas = phasesData.getPropostas();
        if(data!=null){
            if((data.size())%7==0){ //CADA LINHA TEM 7 VALORES
                for(int i=0;i<data.size();i+=7){
                    if (data.get(i).equals("T1")){
                        Propostas p = new Estagio(
                                temAluno(data.get(i+3)),
                                SiglaRamo.parse(data.get(i+1)),
                                data.get(i+2),
                                data.get(i)
                        );
                    } else if (data.get(i).equals("T2")) {
                        Propostas p = new Projeto(
                                temAluno(data.get(i+4)),
                                temProfessor(data.get(i+3)),
                                SiglaRamo.parse(data.get(i+1)),
                                data.get(i+2),
                                data.get(i)
                        );

                    } else if (data.get(i).equals("T3")) {
                        //TODO ACABAR ISTO
                    }
                    /*
                    Propostas p = new Propostas(
                            data.get(i),
                            data.get(i + 1)
                    ) {
                    };*/
                    System.out.println(d);
                    //TODO buscar o docente para enviar
                    propostas.add(d);
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

    private Aluno temAluno(String numero){
        if (!numero.equals(" ")){
            List<Aluno> alunos = phasesData.getAlunos();
            for (Aluno al : alunos){
                if (Integer.parseInt(numero) == al.getNumEstudante()){
                    return al;
                }
            }
        }
        return null;
    }

    private Docente temProfessor(String email){
        if (!email.equals(" ")){
            List<Docente> docentes = phasesData.getDocentes();
            for (Docente docente : docentes){
                if (email.equals(docente.getEmail())){
                    return docente;
                }
            }
        }
        return null;
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
