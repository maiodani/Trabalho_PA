package pt.isec.pa.apoio_poe.model.fsm.states.phase4;

import pt.isec.pa.apoio_poe.model.CsvManager;
import pt.isec.pa.apoio_poe.model.data.PhasesData;
import pt.isec.pa.apoio_poe.model.data.phase1.Aluno;
import pt.isec.pa.apoio_poe.model.data.phase1.Docente;
import pt.isec.pa.apoio_poe.model.data.phase1.Propostas;
import pt.isec.pa.apoio_poe.model.data.phase1.propostas.Projeto;
import pt.isec.pa.apoio_poe.model.data.phase2.Candidatura;
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
            if(pa.getCodigoId().equals(options[0])){
                if(pa.getOrientador()!=null){
                    str.append("\nProjeto já com Docente atribuido");
                    return str.toString();
                }
                if(!pa.isAtribuida()){
                    str.append("\nProjeto sem aluno atribuido");
                    return str.toString();
                }
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
            if(dc.getEmail().equals(options[1])){
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
    public String query(int n) {
        StringBuilder str = new StringBuilder();
        List<Propostas> p;
        List<Docente> d;
        switch (n){
            case 1:
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
            case 2 :
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
            case 3:
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
    public String export() {
        int i = 1;
        List<Candidatura> candidaturas = phasesData.getCandidaturas();
        List<Aluno> alunos = phasesData.getAlunos();
        List<Propostas> propostas = phasesData.getPropostas();
        StringBuilder str = new StringBuilder();
        for (Candidatura c : candidaturas){
            for (Aluno al :alunos){
                if(c.getAluno().equals(al)){
                    str.append(al.getNumEstudante());
                    for (String s : c.getCodigos()){
                        str.append(",").append(s);
                    }
                    for (Propostas p : propostas){
                        if(p.getAluno()!=null){
                            if(p.getAluno().equals(al)){
                                str.append(",").append(p.getCodigoId());
                                for (String s : c.getCodigos()){
                                    if (s.equalsIgnoreCase(p.getCodigoId())){
                                        str.append(",").append(i);
                                        break;
                                    }
                                    i++;
                                }
                                if(p.getOrientador()!=null){
                                    str.append(",").append(p.getOrientador().getEmail());
                                }
                            }
                        }
                    }
                    str.append("\n");
                }
            }
        }
        if(str.length()!=0) {
            str.deleteCharAt(str.length() - 1);
        }
        return CsvManager.writeFile("atribuicaoOrientadores_export.csv", str);
    }

    @Override
    public boolean voltar() {
        changeState(PhaseState.PROPOSTAS);
        return true;
    }
}
