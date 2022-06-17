package pt.isec.pa.apoio_poe.model.fsm.states.phase5;

import pt.isec.pa.apoio_poe.model.CsvManager;
import pt.isec.pa.apoio_poe.model.data.PhasesData;
import pt.isec.pa.apoio_poe.model.data.phase1.Aluno;
import pt.isec.pa.apoio_poe.model.data.phase1.Docente;
import pt.isec.pa.apoio_poe.model.data.phase1.Propostas;
import pt.isec.pa.apoio_poe.model.data.phase1.SiglaRamo;
import pt.isec.pa.apoio_poe.model.data.phase1.propostas.Estagio;
import pt.isec.pa.apoio_poe.model.data.phase1.propostas.Projeto;
import pt.isec.pa.apoio_poe.model.data.phase2.Candidatura;
import pt.isec.pa.apoio_poe.model.data.Queries;
import pt.isec.pa.apoio_poe.model.data.phase4.DadosDiversos;
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
    public List<Integer> dadosPorRamo() {
        List<Propostas> p = phasesData.getPropostas();
        List<Integer> l = new ArrayList<>();
        int countDA=0,countRAS=0,countSI=0;
        for(Propostas propostas:p){
            if(propostas.getRamo()!=null){
                if(propostas.getRamo().contains(SiglaRamo.DA))countDA++;
                if(propostas.getRamo().contains(SiglaRamo.SI))countSI++;
                if(propostas.getRamo().contains(SiglaRamo.RAS))countRAS++;
            }
        }
        l.add(countDA);
        l.add(countSI);
        l.add(countRAS);
        return l;
    }

    @Override
    public List<DadosDiversos> dadosDiversos() {
        List<Propostas> p;
        List<Docente> d;
        List<DadosDiversos> dadosDiversos = new ArrayList<>();
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
            DadosDiversos dd = new DadosDiversos(dc,count);
            dadosDiversos.add(dd);
        }
        media= (double) total/d.size();
        StringBuilder str = new StringBuilder();
        str.append("Cada docente têm em media ").append(media).append(" orientações.");
        str.append("\nO menor némero de orietações de um docente é "+min);
        str.append("\nO maior número de orietações de um docente é "+max);
        DadosDiversos auxString=dadosDiversos.get(0);
        auxString.setEstatisticas(str.toString());
        dadosDiversos.set(0,auxString);
        return dadosDiversos;
    }
    @Override
    public List<Aluno> queryAluno(Queries q) {
        List<Propostas> p;
        List<Candidatura> c;
        List<Aluno> a;
        List<Docente> d;
        List<Aluno> auxA = new ArrayList<>();
        switch (q) {
            case ALUNOS_COM_PROPOSTA_ATRIBUIDA:
                p = phasesData.getPropostas();
                for (Propostas pa : p) {
                    if (pa.isAtribuida()) {
                        auxA.add(pa.getAluno());
                    }
                }
                break;
            case ALUNOS_SEM_PROPOSTA_ATRIBUIDA_COM_CANDIDATURA:
                c = phasesData.getCandidaturas();
                p = phasesData.getPropostas();
                a = phasesData.getAlunos();
                List<Aluno> alunosComCandidatura = new ArrayList<>();
                List<Aluno> alunosComProposta = new ArrayList<>();
                List<Aluno> alunosSemProposta = new ArrayList<>();
                for (Candidatura ca : c) {
                    alunosComCandidatura.add(ca.getAluno());
                }
                for (Propostas pa : p) {
                    if (pa.isAtribuida()) {
                        alunosComProposta.add(pa.getAluno());
                    }
                }
                for (Aluno aa : a) {
                    if (!alunosComProposta.contains(aa)) {
                        alunosSemProposta.add(aa);
                    }
                }
                for (Aluno aa : alunosSemProposta) {
                    auxA.add(aa);
                }
                break;
        }
        return auxA;
    }

    @Override
    public List<Propostas> queryPropostas(Queries q) {
        List<Propostas> p;
        List<Candidatura> c;
        List<Aluno> a;
        List<Docente> d;
        List<Propostas> auxP = new ArrayList<>();
        switch (q){
            case PROPOSTAS_DISPONIVEIS:
                p=phasesData.getPropostas();
                for(Propostas pa:p){
                    if(!pa.isAtribuida()){
                        auxP.add(pa);
                    }
                }
                break;
            case PROPOSTAS_ATRIBUIDAS:
                p=phasesData.getPropostas();
                for(Propostas pa:p){
                    if(pa.isAtribuida()){
                        auxP.add(pa);
                    }
                }
                break;
        }
        return auxP;
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
