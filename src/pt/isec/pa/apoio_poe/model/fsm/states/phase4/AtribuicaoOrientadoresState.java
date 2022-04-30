package pt.isec.pa.apoio_poe.model.fsm.states.phase4;

import pt.isec.pa.apoio_poe.model.data.PhasesData;
import pt.isec.pa.apoio_poe.model.data.phase1.Docente;
import pt.isec.pa.apoio_poe.model.data.phase1.Propostas;
import pt.isec.pa.apoio_poe.model.data.phase1.propostas.Projeto;
import pt.isec.pa.apoio_poe.model.fsm.PhaseContext;
import pt.isec.pa.apoio_poe.model.fsm.PhaseState;
import pt.isec.pa.apoio_poe.model.fsm.PhaseStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class AtribuicaoOrientadoresState extends PhaseStateAdapter {
    public AtribuicaoOrientadoresState(PhasesData phasesData, PhaseContext context) {
        super(phasesData, context);
    }

    @Override
    public PhaseState getState() {
        return PhaseState.ATRIBUICAO_ORIENTADORES;
    }

    @Override
    public String export() {
        return null;
    }

    @Override
    public boolean fecharFase() {
        changeState(PhaseState.CONSULTA);
        return true;
    }

    @Override
    public String insert() {
        StringBuilder str = new StringBuilder();
        List<Propostas> p = phasesData.getPropostas();
        for(Propostas pa:p){

            if(pa instanceof Projeto && pa.isAtribuida()){
                Projeto aux = (Projeto)pa;
                aux.setDocenteConfirmado(true);
                str.append("\nDocente "+pa.getOrientador().getNome()+" confirmado no projeto "+pa.getTitulo()+" como orientador");
            }
        }
        return str.toString();
    }

    @Override
    public String insert(String ... options) {
        StringBuilder str = new StringBuilder();
        List<Propostas> p = phasesData.getPropostas();
        Propostas propostas = null;
        List<Docente> d = phasesData.getDocentes();
        Docente docente = null;
        boolean aux=true;
        for(Propostas pa:p){
            System.out.println(pa.getCodigoId()+"|"+options[0]);
            if(pa.getCodigoId()==options[0]){
                System.out.println("TESTE");
                propostas=pa;
                aux=false;
                break;
            }
        }
        if(aux){
            str.append("\nProjeto com o codigo "+options[0]+" não encontrado");
            return str.toString();
        }
        aux=true;
        for(Docente dc:d){
            if(dc.getEmail()==options[1]){
                docente=dc;
                aux=false;
                break;
            }
        }
        if(aux){
            str.append("\nDocente com o email "+options[1]+" não encontrado");
            return str.toString();
        }

        propostas.setOrientador(docente);
        str.append("Docente "+docente.getNome()+" atribuido ao projeto "+propostas.getCodigoId());
        return str.toString();
    }

    @Override
    public String query() {
        StringBuilder str = new StringBuilder();
        List<Propostas> p = phasesData.getPropostas();
        for(Propostas pa: p){
            if(pa instanceof Projeto){
                Projeto aux = (Projeto) pa;
                if(aux.isDocenteConfirmado()){
                    str.append(pa.toString());
                }
            }else{
                if(pa.getOrientador()!=null){
                    str.append(pa.toString());
                }
            }
        }
        return str.toString();
    }

    @Override
    public boolean voltar() {
        changeState(PhaseState.PROPOSTAS);
        return true;
    }
}
