package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.PhasesData;

import java.io.*;

public class PhaseContext {
    private PhasesData phasesData;
    private IPhaseState state;

    static private String saveFile = "save.bin";

    public PhaseContext(){
        phasesData = new PhasesData(0);//INICIA SEM NENHUMA FASE FECHADA
        state = PhaseState.CONFIG.createState(this,phasesData);
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

    public int getFechado(){
        return phasesData.getFechado();
    }

    public String query(){
        return state.query();
    }
    public String export(){
        return state.export();
    }
    public String query(int n){
        return state.query(n);
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

}
