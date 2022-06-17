package pt.isec.pa.apoio_poe.model.fsm.states.phase1;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import pt.isec.pa.apoio_poe.model.CsvManager;
import pt.isec.pa.apoio_poe.model.data.PhasesData;
import pt.isec.pa.apoio_poe.model.data.phase1.*;
import pt.isec.pa.apoio_poe.model.data.phase1.propostas.*;
import pt.isec.pa.apoio_poe.model.fsm.PhaseContext;
import pt.isec.pa.apoio_poe.model.fsm.PhaseState;
import pt.isec.pa.apoio_poe.model.fsm.PhaseStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class GestPropostaState extends PhaseStateAdapter {
    public GestPropostaState(PhaseContext context,PhasesData phasesData) {
        super(context,phasesData);
    }

    @Override
    public PhaseState getState() {
        return PhaseState.GEST_PROPOSTA;
    }

    @Override
    public String insert(String nomeFicheiro) {
        String[][] data = CsvManager.readFile(nomeFicheiro);
        List<Propostas> propostas = phasesData.getPropostas();
        List<Aluno> alunos = phasesData.getAlunos();
        List<Docente> docentes = phasesData.getDocentes();
        StringBuilder str = new StringBuilder();
        if(data!=null){
            str.append("ERROS:");
            str = Propostas.createPropostas(data,alunos,docentes,propostas,str);
        }
        str.append("\n");
        return str.toString();
    }

    @Override
    public String insertProposta(String[] a) {
        List<Propostas> propostas = phasesData.getPropostas();
        List<Aluno> alunos = phasesData.getAlunos();
        List<Docente> docentes = phasesData.getDocentes();
        StringBuilder str = new StringBuilder();
        if(a!=null){
            str.append("ERROS:");
            str = Propostas.createProposta(a,alunos,docentes,propostas,str);
        }
        return str.toString();
    }

    @Override
    public String editProposta(String[] data) {
        List<Propostas> propostas = phasesData.getPropostas();
        List<Aluno> alunos = phasesData.getAlunos();
        List<Docente> docentes = phasesData.getDocentes();
        StringBuilder str = new StringBuilder();
        Propostas p = null;
        if (data[0].equals("T1")) {
            p = new Estagio(
                    Estagio.getAluno(Long.parseLong(data[5]),alunos),
                    Estagio.variosRamos(data[2]),
                    data[3],
                    data[1],
                    data[4]
            );
        } else if (data[0].equals("T2")) {
            p = new Projeto(
                    Projeto.getAluno(Long.parseLong(data[5]),alunos),
                    Projeto.adicionarProfessor(data[4],docentes),
                    Projeto.variosRamos(data[2]),
                    data[3],
                    data[1]
            );
        } else if (data[0].equals("T3")) {
            Aluno al = null;
            for(Aluno aluno : alunos){
                if (Long.parseLong(data[3]) == (aluno.getNumEstudante())) al = aluno;
            }
            p = new EstProjAutoproposto(
                    al,
                    data[2],
                    data[1]
            );
        }
        int i=0;
        for(Propostas prop:propostas){
            if(prop.getCodigoId().equalsIgnoreCase(p.getCodigoId())){
                propostas.set(i,p);
            }
            i++;
        }
        return "";
    }

    @Override
    public String deleteProposta(String codigoId) {
        List<Propostas> p = phasesData.getPropostas();
        for(Propostas pro:p){
            if(pro.getCodigoId().equalsIgnoreCase(codigoId)){
                p.remove(pro);
                return "";
            }
        }
        return "";
    }

    @Override
    public List<Docente> queryDocente() {
        List<Docente> docentes = phasesData.getDocentes();
        return docentes;
    }
    @Override
    public List<Aluno> queryAluno() {
        List<Aluno> a = phasesData.getAlunos();
        if(a.isEmpty()){
            return null;
        }else{
            return a;
        }
    }

    @Override
    public List<Propostas> queryProposta() {
        List<Propostas> propostas = phasesData.getPropostas();
        return propostas;
    }

    public String query() {
        List<Propostas> propostas = phasesData.getPropostas();
        return Propostas.query(propostas);
    }

    @Override
    public String export(String nomeFicheiro) {
        List<Propostas> propostas = phasesData.getPropostas();
        return CsvManager.writeFile(nomeFicheiro, Propostas.export(propostas));
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
