package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.Queries;
import pt.isec.pa.apoio_poe.model.data.phase1.Aluno;
import pt.isec.pa.apoio_poe.model.data.phase1.Docente;
import pt.isec.pa.apoio_poe.model.data.phase1.Propostas;
import pt.isec.pa.apoio_poe.model.data.phase1.propostas.EstProjAutoproposto;
import pt.isec.pa.apoio_poe.model.data.phase1.propostas.Estagio;
import pt.isec.pa.apoio_poe.model.data.phase1.propostas.Projeto;
import pt.isec.pa.apoio_poe.model.data.phase2.Candidatura;
import pt.isec.pa.apoio_poe.model.data.phase4.DadosDiversos;
import pt.isec.pa.apoio_poe.model.fsm.states.phase2.CadidaturaState;

import java.util.List;

public interface IPhaseState {

    PhaseState getState();
    String insert();
    String insert(String nomeFicheiro);
    String insert(int op);

    String insertAluno(Aluno a);
    List<Aluno> queryAluno();
    List<Aluno> queryAluno(Queries q);
    List<Propostas> queryPropostas(Queries q);
    String editAluno(Aluno a);
    String deleteAluno(Long numEstudante);
    String insertDocente(Docente d);
    List<Docente> queryDocente();
    String editDocente(Docente d);
    String deleteDocente(String email);
    String insertProposta (String [] data);
    List<Propostas> queryProposta();
    String editProposta(String [] data);
    String editProposta(String codigo,String email);
    String deleteProposta(String codigoId);
    String insert(String ... options);
    String insertCandidatura (Candidatura c);
    List<Candidatura> queryCandidatura();
    String editCandidatura(Candidatura c);
    String deleteCandidatura(String numEstudante);
    List<Propostas> queryPropostaManual();
    List<Aluno> queryAlunoManual();
    String deleteAtribuicao(String codigoId);
    Propostas getEmpateProposta();
    List<Aluno> getEmpateAlunos();
    String query();
    String export();
    String export(String nomeFicheiro);
    String query(Queries n);
    boolean fecharFase();
    boolean iniciar(int op);
    boolean avancar();
    boolean voltar();
    List<DadosDiversos> dadosDiversos();
    List<Integer> dadosPorRamo();

}
