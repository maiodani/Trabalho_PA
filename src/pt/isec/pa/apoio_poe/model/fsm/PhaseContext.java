package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.PhasesData;
import pt.isec.pa.apoio_poe.model.CsvManager;
import pt.isec.pa.apoio_poe.model.data.phase1.Aluno;
import pt.isec.pa.apoio_poe.model.data.phase1.SiglaCurso;
import pt.isec.pa.apoio_poe.model.data.phase1.SiglaRamo;
import pt.isec.pa.apoio_poe.model.fsm.states.ConfigState;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PhaseContext {
    private PhasesData phasesData;
    private IPhaseState state;

    public PhaseContext(){
        phasesData = new PhasesData(0);//INICIA SEM NENHUMA FASE FECHADA
        state = PhaseState.CONFIG.createState(this,phasesData);
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

    public void addAluno(){
        List<String> data = CsvManager.readFile("alunos_v2.csv");
        List<Aluno> alunos = phasesData.getAlunos();
        if(data!=null){
            //System.out.println(data.size());
            if((data.size())%7==0){ //CADA LINHA TEM 7 VALORES
                for(int i=0;i<data.size();i+=7){
                    Aluno a = new Aluno(
                            Long.parseLong(data.get(i)),
                            data.get(i+1),
                            data.get(i+2),
                            SiglaCurso.parse(data.get(i+3)),
                            SiglaRamo.parse(data.get(i+4)),
                            Double.parseDouble(data.get(i+5)),
                            parseBoolean(data.get(i+6).replaceAll("\\s+","")));
                    System.out.println(a);
                    if(canBeAdded(a,alunos)==true){
                        alunos.add(a);
                    }else{
                        System.out.println("ALUNO COM DADOS INVALIDOS");
                    }
                }
                //System.out.println("TESTE");
            }
            //System.out.println("TESTE2");
        }else{
            System.out.println("NULL");
        }
    }

    private Boolean parseBoolean(String s){
        s = s.toLowerCase();
        switch (s){
            case "true": return Boolean.TRUE;
            case "false":return Boolean.FALSE;
            default: return null;
        }
    }

    private boolean canBeAdded(Aluno a, List<Aluno> alunos) {
        for(Aluno aux:alunos){//CHECK SE JA EXISTE
            if(aux.getNumEstudante()==a.getNumEstudante()){
                System.out.println("ALUNO COM O MESMO NUMERO JÁ REGISTADO");
                return false;
            }
        }

        if(a.getSiglaCurso()==null){//SIGLA ESTA ERRADA
            System.out.println("SIGLA DE CURSO NÃO RECONHECIDA");
            return false;
        }

        if(a.getSiglaRamo()==null){//SIGLA ESTA ERRADA
            System.out.println("SIGLA DE RAMO NÃO RECONHECIDA");
            return false;
        }

        if(a.getClassificacao()<0.0 || a.getClassificacao()>1.0){//VERIRICAR QUAL É O LIMITE
            System.out.println("CLASSIFICAÇÃO INVALIDA");
            return false;
        }

        if(a.getPodeAceder()==null){//VERIFICAR SEO CAMPO PODE ACEDER ESTA CORRETO
            System.out.println("PODE ACEDER INVALIDO");
            return false;
        }
        return true;
    }
}
