package pt.isec.pa.apoio_poe.model.fsm.states.phase4;

import pt.isec.pa.apoio_poe.model.CsvManager;
import pt.isec.pa.apoio_poe.model.data.PhasesData;
import pt.isec.pa.apoio_poe.model.data.Queries;
import pt.isec.pa.apoio_poe.model.data.phase1.Aluno;
import pt.isec.pa.apoio_poe.model.data.phase1.Docente;
import pt.isec.pa.apoio_poe.model.data.phase1.Propostas;
import pt.isec.pa.apoio_poe.model.data.phase1.propostas.Projeto;
import pt.isec.pa.apoio_poe.model.data.phase2.Candidatura;
import pt.isec.pa.apoio_poe.model.data.phase4.AtribuiçãoOrientadores;
import pt.isec.pa.apoio_poe.model.fsm.PhaseContext;
import pt.isec.pa.apoio_poe.model.fsm.PhaseState;
import pt.isec.pa.apoio_poe.model.fsm.PhaseStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class AtribuicaoOrientadoresState extends PhaseStateAdapter {
    public AtribuicaoOrientadoresState(PhaseContext context,PhasesData phasesData) {
        super(context,phasesData);
    }

    @Override
    public PhaseState getState() {
        return PhaseState.ATRIBUICAO_ORIENTADORES;
    }

    @Override
    public boolean fecharFase() {
        changeState(PhaseState.CONSULTA);
        return true;
    }

    @Override
    public String insert() {
        List<Propostas> p = phasesData.getPropostas();
        return AtribuiçãoOrientadores.insert(p).toString();
    }

    @Override
    public String insert(String ... options) {
        List<Propostas> p = phasesData.getPropostas();
        List<Docente> d = phasesData.getDocentes();
        return AtribuiçãoOrientadores.insert(p,d,options).toString();
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
    public String query(Queries n) {
        StringBuilder str = new StringBuilder();
        List<Propostas> p;
        List<Docente> d;
        switch (n){
            case ALUNOS_COM_PROPOSTAS_COM_DOCENTE:
                p = phasesData.getPropostas();
                for(Propostas pa:p){
                    if(pa instanceof Projeto){
                        Projeto aux = (Projeto) pa;
                        if(aux.isAtribuida() && aux.isDocenteConfirmado() && aux.getOrientador()!=null){
                            str.append(aux.getAluno().toString()+"Codigo do Projeto "+pa.getCodigoId()+"\n");
                        }
                    }else{
                        if(pa.isAtribuida() && pa.getOrientador()!=null){
                            str.append(pa.getAluno().toString()+"Codigo do Projeto "+pa.getCodigoId()+"\n");
                        }
                    }
                }
                break;
            case ALUNOS_COM_PROPOSTAS_SEM_DOCENTE :
                p = phasesData.getPropostas();
                for(Propostas pa:p){
                    if(pa instanceof Projeto){
                        Projeto aux = (Projeto) pa;
                        if(pa.isAtribuida() && !aux.isDocenteConfirmado()){
                            str.append(pa.getAluno().toString()+"Codigo do Projeto "+pa.getCodigoId()+"\n");
                        }
                    }else{
                        if(pa.isAtribuida() && pa.getOrientador()==null){
                            str.append(pa.getAluno().toString()+"Codigo do Projeto "+pa.getCodigoId()+"\n");
                        }
                    }
                }
                break;
            case ESTATISTICAS_POR_DOCENTE:
                double media;
                int count,aux=0,max=0,min=0,total=0;
                p = phasesData.getPropostas();
                d = phasesData.getDocentes();

                for(Propostas pa:p){
                    if(pa.isAtribuida() && pa.getOrientador()!=null){
                        total++;
                    }
                }
                for(Docente dc:d){
                    count=0;
                    for(Propostas pa:p){
                        if(pa.getOrientador()!=null)
                            if(dc.getEmail()==pa.getOrientador().getEmail()){
                                count++;
                            }
                    }
                    if(count>max){
                        max=count;
                    }
                    if(count<min){
                        min=count;
                    }
                    str.append("\nO docente "+dc.getNome()+" têm "+count+" orientações");
                }
                media= (double) total/d.size();
                str.append("\nCada docente têm em media ").append(media).append(" orientações.");
                str.append("\nO menor némero de orietações de um docente é "+min);
                str.append("\nO maior número de orietações de um docente é "+max);
                break;
            default:
                break;
        }
        return str.toString();
    }

    @Override
    public String export(String nomeFicheiro) {
        List<Candidatura> candidaturas = phasesData.getCandidaturas();
        List<Aluno> alunos = phasesData.getAlunos();
        List<Propostas> propostas = phasesData.getPropostas();
        return CsvManager.writeFile(nomeFicheiro, AtribuiçãoOrientadores.export(candidaturas,alunos,propostas));
    }

    @Override
    public boolean voltar() {
        changeState(PhaseState.PROPOSTAS);
        return true;
    }
}
