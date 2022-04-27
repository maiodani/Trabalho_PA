package pt.isec.pa.apoio_poe.model.fsm;

public interface IPhaseState {

    PhaseState getState();
    String insert();
    String query();
    String query(int n);
    boolean fecharFase();
    boolean iniciar(int op);
    boolean avancar();
    boolean voltar();

}
