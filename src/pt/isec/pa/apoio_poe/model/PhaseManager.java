package pt.isec.pa.apoio_poe.model;

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
import pt.isec.pa.apoio_poe.model.fsm.IPhaseState;
import pt.isec.pa.apoio_poe.model.fsm.PhaseContext;
import pt.isec.pa.apoio_poe.model.fsm.PhaseState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.*;
import java.util.List;

public class PhaseManager {
    PhaseContext fsm;
    PropertyChangeSupport pcs;
    private IPhaseState state;
    public boolean empate;

    static private String saveFile = "save.bin";
    public PhaseManager(){
        fsm = new PhaseContext();
        pcs = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }
    public boolean avancar() {
        var b=fsm.avancar();
        pcs.firePropertyChange(null,null,null);
        return b;
    }

    public String insert() {
        String s=fsm.insert();
        pcs.firePropertyChange(null,null,null);
        return s;
    }

    public String insert(int op) {
        String s=fsm.insert(op);
        pcs.firePropertyChange(null,null,null);
        return s;
    }
    public String insertAluno(Aluno a) {
        String s = fsm.insertAluno(a);
        pcs.firePropertyChange(null,null,null);
        return s;
    }

    public String export() {
        String s=fsm.export();
        pcs.firePropertyChange(null,null,null);
        return s;
    }

    public String export(String nomeFicheiro) {
        String s=fsm.export(nomeFicheiro);
        pcs.firePropertyChange(null,null,null);
        return s;
    }
    public List<Aluno> queryAluno(){
        List<Aluno> a =fsm.queryAluno();
        pcs.firePropertyChange(null,null,null);
        return a;
    }
    public List<Aluno> queryAluno(Queries q){
        List<Aluno> a =fsm.queryAluno(q);
        pcs.firePropertyChange(null,null,null);
        return a;
    }
    public String query() {
        String s=fsm.query();
        pcs.firePropertyChange(null,null,null);
        return s;
    }

    public String query(Queries n) {
        String s=fsm.query(n);
        pcs.firePropertyChange(null,null,null);
        return s;
    }

    public boolean fecharFase() {
        boolean b=fsm.fecharFase();
        pcs.firePropertyChange(null,null,null);
        return b;
    }

    public boolean iniciar(int op){
        boolean b=fsm.iniciar(op);
        pcs.firePropertyChange(null,null,null);
        return b;
    }

    public boolean voltar(){
        boolean b=fsm.voltar();
        pcs.firePropertyChange(null,null,null);
        return b;
    }

    public String insert(String ... options) {
        String s=fsm.insert(options);
        pcs.firePropertyChange(null,null,null);
        return s;
    }

    public String insert(String nomeFicheiro) {
        String s=fsm.insert(nomeFicheiro);
        pcs.firePropertyChange(null,null,null);
        return s;
    }

    public String editAluno(Aluno a){
        String s=fsm.editAluno(a);
        pcs.firePropertyChange(null,null,null);
        return s;
    }
    public String deleteAluno(Long numEstudante){
        String s=fsm.deleteAluno(numEstudante);
        pcs.firePropertyChange(null,null,null);
        return s;
    }
    public String insertDocente(Docente d){
        String s=fsm.insertDocente(d);
        pcs.firePropertyChange(null,null,null);
        return s;
    }
    public List<Docente> queryDocente(){
        List<Docente> d = fsm.queryDocente();
        pcs.firePropertyChange(null,null,null);
        return d;
    }
    public String editDocente(Docente d){
        String s=fsm.editDocente(d);
        pcs.firePropertyChange(null,null,null);
        return s;
    }
    public String deleteDocente(String email){
        String s=fsm.deleteDocente(email);
        pcs.firePropertyChange(null,null,null);
        return s;
    }
    public PhaseState getState() {
        return fsm.getState();
    }
    public int getFechado(){
        return fsm.getFechado();
    }

    public String insertProposta(String [] dados) {
        String s=fsm.insertProposta(dados);
        pcs.firePropertyChange(null,null,null);
        return s;
    }

    public String insertProjeto(Projeto p) {
        String s=insertProjeto(p);
        pcs.firePropertyChange(null,null,null);
        return s;
    }
    public String insertCandidatura (Candidatura c){
        String s = fsm.insertCandidatura(c);
        pcs.firePropertyChange(null,null,null);
        return s;
    }
    public String editCandidatura(Candidatura c){
        String s = fsm.editCandidatura(c);
        pcs.firePropertyChange(null,null,null);
        return s;
    }
    public String deleteCandidatura(String numEstudante){
        String s = fsm.deleteCandidatura(numEstudante);
        pcs.firePropertyChange(null,null,null);
        return s;
    }
    public List<Propostas> queryPropostaManual(){
        List<Propostas> p =fsm.queryPropostaManual();
        pcs.firePropertyChange(null,null,null);
        return p;
    }
    public List<Aluno> queryAlunoManual(){
        List<Aluno> a =fsm.queryAlunoManual();
        pcs.firePropertyChange(null,null,null);
        return a;
    }
    public String deleteAtribuicao(String codigoId){
        String s = fsm.deleteAtribuicao(codigoId);
        pcs.firePropertyChange(null,null,null);
        return s;
    }
    public List<Propostas> queryProposta(){
        List<Propostas> p =fsm.queryProposta();
        pcs.firePropertyChange(null,null,null);
        return p;
    }
    public List<Candidatura> queryCandidatura(){
        List<Candidatura> c =fsm.queryCandidatura();
        pcs.firePropertyChange(null,null,null);
        return c;
    }
    public String editProposta(String [] data){
        String s=fsm.editProposta(data);
        pcs.firePropertyChange(null,null,null);
        return s;
    }
    public String deleteProposta(String codigoId){
        String s=fsm.deleteProposta(codigoId);
        pcs.firePropertyChange(null,null,null);
        return s;
    }
    public List<Propostas> queryPropostas(Queries q) {
        List<Propostas> p = fsm.queryPropostas(q);
        pcs.firePropertyChange(null,null,null);

        return p;
    }
    public Propostas getEmpateProposta(){
        Propostas p =fsm.getEmpateProposta();
        return p;
    }
    public List<Aluno> getEmpateAlunos(){
        return fsm.getEmpateAlunos();
    }
    public String editProposta(String codigo,String email){
        String s = fsm.editProposta(codigo,email);
        pcs.firePropertyChange(null,null,null);
        return s;
    }
    public List<DadosDiversos> dadosDiversos() {
        List<DadosDiversos> d = fsm.dadosDiversos();
        pcs.firePropertyChange(null,null,null);
        return d;
    }
    public List<Integer> dadosPorRamo(){
        List<Integer> l = fsm.dadosPorRamo();
        pcs.firePropertyChange(null,null,null);
        return l;
    }
    public void saveBin(){
        fsm.getPhasesData().setState(fsm.getState());

        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {
            fos = new FileOutputStream(saveFile);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(fsm.getPhasesData());
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
            fsm.getPhasesData().copy((PhasesData) ois.readObject());
            ois.close();
            state = fsm.getPhasesData().getState().createState(fsm,fsm.getPhasesData());
            fsm.setState(state);
        } catch (FileNotFoundException e) {
            str.append("\nFicheiro não encontrado");
        } catch (IOException | ClassNotFoundException e) {
            str.append("\nOcorreu um erro a ler o ficheiro");
        }

        pcs.firePropertyChange(null,null,null);
        return str.toString();
    }
}
