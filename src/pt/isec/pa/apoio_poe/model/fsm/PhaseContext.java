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

import java.io.*;
import java.util.List;

public class PhaseContext {
    private PhasesData phasesData;
    private IPhaseState state;

    static private String saveFile = "save.bin";

    public PhaseContext(){
        phasesData = new PhasesData(0);//INICIA SEM NENHUMA FASE FECHADA
        state = PhaseState.CONFIG.createState(this,phasesData);
    }

    public void setState(IPhaseState pd) {
        state = pd;
    }
    public PhasesData getPhasesData() {
        return phasesData;
    }

    public void saveBin(){
        phasesData.setState(state.getState());

        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {
            fos = new FileOutputStream(saveFile);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(phasesData);
            oos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String readBin(){
        StringBuilder str = new StringBuilder();
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(saveFile);
            ois = new ObjectInputStream(fis);
            phasesData.copy((PhasesData) ois.readObject());
            ois.close();
            state = phasesData.getState().createState(this,phasesData);
        } catch (FileNotFoundException e) {
            str.append("\nFicheiro não encontrado");
        } catch (IOException | ClassNotFoundException e) {
            str.append("\nOcorreu um erro a ler o ficheiro");
        }

        return str.toString();
    }

    public void changeState(IPhaseState newState){
        state = newState;
    }

    public PhaseState getState(){
        return state.getState();
    }

    public boolean voltar(){
        return state.voltar();
    }

    public boolean avancar(){
        return state.avancar();
    }

    public boolean fecharFase(){
        return state.fecharFase();
    }
    public boolean iniciar(int op){
        return state.iniciar(op);
    }
    public String insert(String nomeFicheiro){
        return state.insert(nomeFicheiro);
    }
    public int getFechado(){
        return phasesData.getFechado();
    }

    public String query(){
        return state.query();
    }
    public String export(){
        return state.export();
    }
    public String export(String nomeFicheiro){
        return state.export(nomeFicheiro);
    }
    public String query(Queries q){
        return state.query(q);
    };
    public String insert() {
        return state.insert();
    }

    public String insert(String ... options){
        return state.insert(options);
    }

    public String insert(int op) {
        return state.insert(op);
    }
    public String insertAluno(Aluno a){
        return state.insertAluno(a);
    }
    public List<Aluno> queryAluno(){
        return state.queryAluno();
    }
    public String editAluno(Aluno a){
        return state.editAluno(a);
    }
    public String deleteAluno(Long numEstudante){
        return state.deleteAluno(numEstudante);
    }
    public String insertDocente(Docente d){
        return state.insertDocente(d);
    }
    public List<Docente> queryDocente(){
        return state.queryDocente();
    }
    public String editDocente(Docente d){
        return state.editDocente(d);
    }
    public String deleteDocente(String email){
        return state.deleteDocente(email);
    }
    public String insertProposta(String [] dados){
        return state.insertProposta(dados);
    }
    public List<Propostas> queryProposta(){
        return state.queryProposta();
    }
    public String editProposta(String [] data ){
        return state.editProposta(data);
    }
    public String deleteProposta(String codigoId){
        return state.deleteProposta(codigoId);
    }
    public List<Candidatura> queryCandidatura(){return state.queryCandidatura();}
    public List<Aluno> queryAluno(Queries q){return state.queryAluno(q);}
    public List<Propostas> queryPropostas(Queries q){return state.queryPropostas(q);}
    public String insertCandidatura (Candidatura c){return state.insertCandidatura(c);}
    public String editCandidatura(Candidatura c){return state.editCandidatura(c);}
    public String deleteCandidatura(String numEstudante){return state.deleteCandidatura(numEstudante);}
    public List<Propostas> queryPropostaManual(){return state.queryPropostaManual();}
    public List<Aluno> queryAlunoManual(){return state.queryAlunoManual();}
    public String deleteAtribuicao(String codigoId){return state.deleteAtribuicao(codigoId);}
    public Propostas getEmpateProposta(){return state.getEmpateProposta();}
    public String editProposta(String codigo,String email){return state.editProposta(codigo,email);}
    public List<Aluno> getEmpateAlunos(){return state.getEmpateAlunos();}
    public List<DadosDiversos> dadosDiversos(){return state.dadosDiversos();}
    public List<Integer> dadosPorRamo(){return state.dadosPorRamo();}

}
