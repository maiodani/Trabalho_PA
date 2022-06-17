package pt.isec.pa.apoio_poe.model.fsm.states.phase3;

import pt.isec.pa.apoio_poe.model.data.PhasesData;
import pt.isec.pa.apoio_poe.model.data.phase1.Aluno;
import pt.isec.pa.apoio_poe.model.data.phase1.Propostas;
import pt.isec.pa.apoio_poe.model.data.phase1.SiglaRamo;
import pt.isec.pa.apoio_poe.model.data.phase3.Manual;
import pt.isec.pa.apoio_poe.model.fsm.PhaseContext;
import pt.isec.pa.apoio_poe.model.fsm.PhaseState;
import pt.isec.pa.apoio_poe.model.fsm.PhaseStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class ManualState extends PhaseStateAdapter {
    public ManualState(PhaseContext context,PhasesData phasesData) {
        super(context,phasesData);
    }
    @Override
    public PhaseState getState() {
        return PhaseState.MANUAL;
    }

    @Override
    public String query() {
        StringBuilder str = new StringBuilder();
        List<Aluno> alunos = phasesData.getAlunos();
        List<Propostas> propostas = phasesData.getPropostas();
        str= Manual.query(alunos,propostas,str);
        return str.toString();
    }

    @Override
    public List<Aluno> queryAlunoManual() {
        return phasesData.getAlunos();
    }
    @Override
    public String deleteAtribuicao(String codigo){
        List<Propostas> propostas=phasesData.getPropostas();
        for(Propostas p:propostas){
            if(p.getCodigoId()==codigo){
                p.setAluno(null);
            }
        }
        return "";
    }

    @Override
    public List<Propostas> queryPropostaManual() {
        List<Propostas> propostas = phasesData.getPropostas();
        List<Propostas> pro = new ArrayList<>();
        for(Propostas p:propostas){
            if(p.getAluno()!=null){
                pro.add(p);
            }
        }
        return pro;
    }

    @Override
    public List<Propostas> queryProposta() {
        List<Propostas> propostasSemAlunos = new ArrayList<>();
        List<Aluno> alunosSemPropostas = new ArrayList<>();
        List<Aluno> alunos = phasesData.getAlunos();
        List<Propostas> propostas = phasesData.getPropostas();
        Manual.obterListas(propostasSemAlunos, alunosSemPropostas,propostas,alunos);
        return propostasSemAlunos;
    }

    @Override
    public List<Aluno> queryAluno() {
        List<Propostas> propostasSemAlunos = new ArrayList<>();
        List<Aluno> alunosSemPropostas = new ArrayList<>();
        List<Aluno> alunos = phasesData.getAlunos();
        List<Propostas> propostas = phasesData.getPropostas();
        Manual.obterListas(propostasSemAlunos, alunosSemPropostas,propostas,alunos);
        return alunosSemPropostas;
    }

    @Override
    public String insert(String... options) {
        StringBuilder str = new StringBuilder();
        List<Aluno> alunos = phasesData.getAlunos();
        List<Propostas> propostas = phasesData.getPropostas();
        str=Manual.insert(alunos,propostas,str,options);
        return str.toString();
    }

    @Override
    public String insertProposta(String[] a) {
        return super.insertProposta(a);
    }

    @Override
    public boolean voltar() {
        changeState(PhaseState.PROPOSTAS);
        return true;
    }
}
