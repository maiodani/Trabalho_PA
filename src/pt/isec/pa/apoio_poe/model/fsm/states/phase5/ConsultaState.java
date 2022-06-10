package pt.isec.pa.apoio_poe.model.fsm.states.phase5;

import pt.isec.pa.apoio_poe.model.CsvManager;
import pt.isec.pa.apoio_poe.model.data.PhasesData;
import pt.isec.pa.apoio_poe.model.data.phase1.Aluno;
import pt.isec.pa.apoio_poe.model.data.phase1.Docente;
import pt.isec.pa.apoio_poe.model.data.phase1.Propostas;
import pt.isec.pa.apoio_poe.model.data.phase2.Candidatura;
import pt.isec.pa.apoio_poe.model.data.Queries;
import pt.isec.pa.apoio_poe.model.data.phase5.Consulta;
import pt.isec.pa.apoio_poe.model.fsm.PhaseContext;
import pt.isec.pa.apoio_poe.model.fsm.PhaseState;
import pt.isec.pa.apoio_poe.model.fsm.PhaseStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class ConsultaState extends PhaseStateAdapter {
    public ConsultaState(PhaseContext context,PhasesData phasesData) {
        super(context,phasesData);
    }

    @Override
    public PhaseState getState() {
        return PhaseState.CONSULTA;
    }

    @Override
    public String query(Queries n) {
        StringBuilder str = new StringBuilder();
        List<Propostas> p;
        List<Candidatura> c;
        List<Aluno> a;
        List<Docente> d;
        switch (n){
            case ALUNOS_COM_PROPOSTA_ATRIBUIDA:
                p = phasesData.getPropostas();
                for(Propostas pa:p){
                    if(pa.isAtribuida()){
                        str.append(pa.getAluno());
                    }
                }
                break;
            case ALUNOS_SEM_PROPOSTA_ATRIBUIDA_COM_CANDIDATURA:
                c=phasesData.getCandidaturas();
                p = phasesData.getPropostas();
                a = phasesData.getAlunos();
                List<Aluno> alunosComCandidatura = new ArrayList<>();
                List<Aluno> alunosComProposta = new ArrayList<>();
                List<Aluno> alunosSemProposta = new ArrayList<>();
                for(Candidatura ca:c){
                    alunosComCandidatura.add(ca.getAluno());
                }
                for(Propostas pa:p){
                    if(pa.isAtribuida()){
                        alunosComProposta.add(pa.getAluno());
                    }
                }
                for(Aluno aa:a){
                    if(!alunosComProposta.contains(aa)){
                        alunosSemProposta.add(aa);
                    }
                }
                for (Aluno aa:alunosSemProposta){
                    str.append(aa.toString());
                }
                break;
            case PROPOSTAS_DISPONIVEIS:
                p=phasesData.getPropostas();
                for(Propostas pa:p){
                    if(!pa.isAtribuida()){
                        str.append(pa.toString());
                    }
                }
                break;
            case PROPOSTAS_ATRIBUIDAS:
                p=phasesData.getPropostas();
                for(Propostas pa:p){
                    if(pa.isAtribuida()){
                        str.append(pa.toString());
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
        }
        return str.toString();
    }

    @Override
    public String export(String nomeFicheiro) {
        List<Candidatura> candidaturas = phasesData.getCandidaturas();
        List<Aluno> alunos = phasesData.getAlunos();
        List<Propostas> propostas = phasesData.getPropostas();
        return CsvManager.writeFile(nomeFicheiro, Consulta.export(candidaturas,alunos,propostas));
    }
}
