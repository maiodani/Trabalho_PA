package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.Queries;

public interface IPhaseState {

    PhaseState getState();
    String insert();
    String insert(String nomeFicheiro);
    String insert(int op);
    String insert(String ... options);
    String query();
    String export();
    String export(String nomeFicheiro);
    String query(Queries n);
    boolean fecharFase();
    boolean iniciar(int op);
    boolean avancar();
    boolean voltar();

}
