package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.PhasesData;
import pt.isec.pa.apoio_poe.model.data.Queries;
import pt.isec.pa.apoio_poe.model.data.phase1.Aluno;
import pt.isec.pa.apoio_poe.model.data.phase1.Docente;
import pt.isec.pa.apoio_poe.model.data.phase1.Propostas;
import pt.isec.pa.apoio_poe.model.data.phase1.propostas.EstProjAutoproposto;
import pt.isec.pa.apoio_poe.model.data.phase1.propostas.Estagio;
import pt.isec.pa.apoio_poe.model.data.phase1.propostas.Projeto;
import pt.isec.pa.apoio_poe.model.data.phase2.Candidatura;
import pt.isec.pa.apoio_poe.model.data.phase4.DadosDiversos;

import java.util.List;

public abstract class PhaseStateAdapter implements IPhaseState {
    protected PhasesData phasesData;
    protected PhaseContext context;

    public PhaseStateAdapter(PhaseContext context, PhasesData phasesData) {
        this.context = context;
        this.phasesData = phasesData;
    }

    protected void changeState(PhaseState newState) {
        context.changeState(newState.createState(context, phasesData));
    }

    @Override
    public boolean avancar() {
        return false;
    }

    @Override
    public String insert() {
        return null;
    }

    @Override
    public String insert(int op) {
        return null;
    }

    @Override
    public String export() {
        return null;
    }

    @Override
    public String export(String nomeFicheiro) {
        return null;
    }

    @Override
    public String query() {
        return null;
    }

    @Override
    public String query(Queries n) {
        return null;
    }

    @Override
    public boolean fecharFase() {
        return false;
    }

    @Override
    public boolean iniciar(int op) {
        return false;
    }

    @Override
    public boolean voltar() {
        return false;
    }

    @Override
    public String insert(String... options) {
        return null;
    }

    @Override
    public String insert(String nomeFicheiro) {
        return null;
    }

    @Override
    public String insertAluno(Aluno a) {
        return null;
    }

    @Override
    public List<Aluno> queryAluno() {
        return null;
    }

    @Override
    public String editAluno(Aluno a) {
        return null;
    }

    @Override
    public String deleteAluno(Long numEstudante) {
        return null;
    }

    @Override
    public String insertDocente(Docente d) {
        return null;
    }

    @Override
    public List<Docente> queryDocente() {
        return null;
    }

    @Override
    public String editDocente(Docente d) {
        return null;
    }

    @Override
    public String deleteDocente(String email) {
        return null;
    }

    @Override
    public String insertProposta(String[] a) {
        return null;
    }


    @Override
    public String editProposta(String[] data) {
        return null;
    }

    @Override
    public List<Propostas> queryProposta() {
        return null;
    }

    @Override
    public String deleteProposta(String codigoId) {
        return null;
    }

    @Override
    public List<Candidatura> queryCandidatura() {
        return null;
    }

    @Override
    public List<Aluno> queryAluno(Queries q) {
        return null;
    }

    @Override
    public List<Propostas> queryPropostas(Queries q) {
        return null;
    }

    @Override
    public String insertCandidatura(Candidatura c) {
        return null;
    }

    @Override
    public String editCandidatura(Candidatura c) {
        return null;
    }

    @Override
    public String deleteCandidatura(String numEstudante) {
        return null;
    }
    @Override
    public List<Propostas> queryPropostaManual(){
        return null;
    }
    @Override
    public List<Aluno> queryAlunoManual(){
        return null;
    }
    @Override
    public String deleteAtribuicao(String codigoId){
        return null;
    }
    @Override
    public Propostas getEmpateProposta(){
        return null;
    }
    @Override
    public List<Aluno> getEmpateAlunos(){return null;}
    @Override
    public String editProposta(String codigo,String email){return null;}
    @Override
    public List<DadosDiversos> dadosDiversos(){return null;}
    @Override
    public List<Integer> dadosPorRamo(){return null;}

}