package pt.isec.pa.apoio_poe.model.fsm.states.phase1;

import pt.isec.pa.apoio_poe.model.CsvManager;
import pt.isec.pa.apoio_poe.model.data.PhasesData;
import pt.isec.pa.apoio_poe.model.data.phase1.Aluno;
import pt.isec.pa.apoio_poe.model.data.phase1.Docente;
import pt.isec.pa.apoio_poe.model.data.phase1.Propostas;
import pt.isec.pa.apoio_poe.model.data.phase1.SiglaRamo;
import pt.isec.pa.apoio_poe.model.data.phase1.propostas.EstProjAutoproposto;
import pt.isec.pa.apoio_poe.model.data.phase1.propostas.Estagio;
import pt.isec.pa.apoio_poe.model.data.phase1.propostas.Projeto;
import pt.isec.pa.apoio_poe.model.fsm.PhaseContext;
import pt.isec.pa.apoio_poe.model.fsm.PhaseState;
import pt.isec.pa.apoio_poe.model.fsm.PhaseStateAdapter;

import java.util.ArrayList;
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
    public String insert() {
        String[][] data = CsvManager.readFile("propostas_v2.csv");
        List<Propostas> propostas = phasesData.getPropostas();
        StringBuilder str = new StringBuilder();
        Propostas p = null;
        if(data!=null){
            for(int i=0; i<data.length; i++) {
                if (data[i][0].equals("T1")) {
                    p = new Estagio(
                            temAluno(data[i]),
                            variosRamos(data[i][2]),
                            data[i][3],
                            data[i][1],
                            data[i][4]
                    );
                } else if (data[i][0].equals("T2")) {
                    p = new Projeto(
                            temAluno(data[i]),
                            adicionarProfessor(data[i][4]),
                            variosRamos(data[i][2]),
                            data[i][3],
                            data[i][1]
                    );
                } else if (data[i][0].equals("T3")) {
                    Aluno objeto = null;
                    if (data.length == 3){
                        List<Aluno> alunos = phasesData.getAlunos();
                        for(Aluno aluno : alunos){
                            if (Integer.parseInt(data[i][3]) == (aluno.getNumEstudante())) objeto = aluno;
                        }
                    }

                    p = new EstProjAutoproposto(
                            objeto,
                            data[i][2],
                            data[i][1]
                    );
                }
                if (canBeAdded(p, propostas, str)){
                    propostas.add(p);
                }

            }
        }
        str.append("\n");
        return str.toString();
    }

    private boolean canBeAdded(Propostas p, List<Propostas> propostas, StringBuilder str) {
        for (Propostas proposta : propostas){
            if (p.getCodigoId().equals(proposta.getCodigoId())) {
                str.append("\nCódigo da proposta ").append(p.getCodigoId()).append(" ja existe");
                return false;
            }
        }
        if (p instanceof Estagio){
            if(p.getRamo()==null){
                str.append("\nRamo inválido na proposta ").append(p.getCodigoId());
                return false;
            }
        } else if (p instanceof Projeto) {
            if(p.getRamo()==null){
                str.append("\nRamo inválido na proposta ").append(p.getCodigoId());
                return false;
            }
        } else if (p instanceof EstProjAutoproposto) {
            if (p.getAluno() == null){
                str.append("\nAluno não inserido para a proposta ").append(p.getCodigoId());
                return false;
            }
        }
        return true;
    }

    public String query() {
        List<Propostas> propostas = phasesData.getPropostas();
        StringBuilder str = new StringBuilder();
        for (Propostas proposta : propostas) {
            str.append("Codigo: "+proposta.getCodigoId()+
                    "\nTitulo: "+proposta.getTitulo());
            if(proposta instanceof Estagio){
                str.append("\nEmpresa: "+((Estagio) proposta).getEmpresa());
            } else if (proposta instanceof Projeto) {
                if(((Projeto) proposta).getOrientador() != null){
                    str.append("\nDocente: ");
                    str.append("\n  Nome: "+((Projeto) proposta).getOrientador().getNome()+
                            "\n  Email: "+((Projeto) proposta).getOrientador().getEmail());
                }
            }
            if (proposta.getAluno() != null){
                str.append("\nAluno: ");
                str.append("\n  N: "+proposta.getAluno().getNumEstudante()+
                        "\n  Nome: "+proposta.getAluno().getNome()+
                        "\n  Email: "+proposta.getAluno().getEmail());
            }
            if (proposta.getRamo()!= null){
                str.append("\nRamo: "+proposta.getRamo());
            }
            str.append("\n\n");
        }
        return str.toString();
    }

    private Docente adicionarProfessor(String email) {
        List<Docente> docentes = new ArrayList<>();
        for (Docente docente : docentes){
            if (docente.getEmail().equals(email)){
                return docente;
            }
        }
        return null;
    }

    private Aluno temAluno(String[] data){
        if (data.length == 6){
            List<Aluno> alunos = phasesData.getAlunos();
            for(Aluno aluno : alunos){
                if (Integer.parseInt(data[5]) == (aluno.getNumEstudante())){
                    return aluno;
                }
            }
        }
        return null;
    }

    private List<SiglaRamo> variosRamos(String ramos){
        SiglaRamo ramo;
        List<SiglaRamo> siglas = new ArrayList<>();
        String[] ramos_divididos = ramos.split("\\|");
        for(String s : ramos_divididos){

            ramo = SiglaRamo.parse(s);
            if(ramo==null){
                return null;
            }
            siglas.add(ramo);
        }
        return siglas;
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
