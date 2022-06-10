package pt.isec.pa.apoio_poe.model;

import pt.isec.pa.apoio_poe.model.data.Queries;
import pt.isec.pa.apoio_poe.model.fsm.PhaseContext;
import pt.isec.pa.apoio_poe.model.fsm.PhaseState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class PhaseManager {
    PhaseContext fsm;
    PropertyChangeSupport pcs;
    static private String saveFile = "save.bin";
    public PhaseManager(){
        fsm = new PhaseContext();
        pcs = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
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
    public boolean avancar() {
        fsm.avancar();
        pcs.firePropertyChange(null,null,null);
        return true;
    }

    public String insert() {
        fsm.insert();
        pcs.firePropertyChange(null,null,null);
        return null;
    }

    public String insert(int op) {
        fsm.insert(op);
        pcs.firePropertyChange(null,null,null);
        return null;
    }

    public String export() {
        fsm.export();
        pcs.firePropertyChange(null,null,null);
        return null;
    }

    public String export(String nomeFicheiro) {
        fsm.export(nomeFicheiro);
        pcs.firePropertyChange(null,null,null);
        return null;
    }

    public String query() {
        fsm.query();
        pcs.firePropertyChange(null,null,null);
        return null;
    }

    public String query(Queries n) {
        fsm.query(n);
        pcs.firePropertyChange(null,null,null);
        return null;
    }

    public boolean fecharFase() {
        fsm.fecharFase();
        pcs.firePropertyChange(null,null,null);
        return false;
    }

    public boolean iniciar(int op){
        fsm.iniciar(op);
        pcs.firePropertyChange(null,null,null);
        return false;
    }

    public boolean voltar(){
        fsm.voltar();
        pcs.firePropertyChange(null,null,null);
        return false;
    }

    public String insert(String ... options) {
        fsm.insert(options);
        pcs.firePropertyChange(null,null,null);
        return null;
    }

    public String insert(String nomeFicheiro) {
        fsm.insert(nomeFicheiro);
        pcs.firePropertyChange(null,null,null);
        return null;
    }
    public PhaseState getState() {
        return fsm.getState();
    }
    public int getFechado(){
        return fsm.getFechado();
    }
}
