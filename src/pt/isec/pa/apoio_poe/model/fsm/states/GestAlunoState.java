package pt.isec.pa.apoio_poe.model.fsm.states;

import pt.isec.pa.apoio_poe.model.CsvManager;
import pt.isec.pa.apoio_poe.model.data.PhasesData;
import pt.isec.pa.apoio_poe.model.data.phase1.Aluno;
import pt.isec.pa.apoio_poe.model.data.phase1.SiglaCurso;
import pt.isec.pa.apoio_poe.model.data.phase1.SiglaRamo;
import pt.isec.pa.apoio_poe.model.fsm.PhaseContext;
import pt.isec.pa.apoio_poe.model.fsm.PhaseState;
import pt.isec.pa.apoio_poe.model.fsm.PhaseStateAdapter;

import java.util.List;

public class GestAlunoState extends PhaseStateAdapter {
    public GestAlunoState(PhasesData phasesData, PhaseContext context) {
        super(phasesData, context);
    }
    @Override
    public void insert(){
        List<String> data = CsvManager.readFile("alunos_v2.csv");
        List<Aluno> alunos = phasesData.getAlunos();
        if(data!=null){
            if((data.size())%7==0){ //CADA LINHA TEM 7 VALORES
                for(int i=0;i<data.size();i+=7){
                    Aluno a = new Aluno(
                            Long.parseLong(data.get(i)),
                            data.get(i+1),
                            data.get(i+2),
                            SiglaCurso.parse(data.get(i+3)),
                            SiglaRamo.parse(data.get(i+4)),
                            Double.parseDouble(data.get(i+5)),
                            parseBoolean(data.get(i+6).replaceAll("\\s+",""))
                    );
                    System.out.println(a);
                    if(canBeAdded(a, alunos)){
                        alunos.add(a);
                    }else{
                        System.out.println("ALUNO COM DADOS INVALIDOS");
                    }
                }
            }
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
    @Override
    public String query() {
        List<Aluno> alunos = phasesData.getAlunos();
        StringBuilder str = new StringBuilder();
        for (Aluno al : alunos) {
            str.append("N: "+al.getNumEstudante()+
                    "\nNome: "+al.getNome()+
                    "\nEmail: "+al.getEmail()+
                    "\nCurso: "+al.getSiglaCurso()+
                    "\nRamo: "+al.getSiglaRamo()+
                    "\nClassificacao: "+al.getClassificacao()+
                    "\n\n");
        }
        return str.toString();
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

        if(a.getPodeAceder()==null){//VERIFICAR SE O CAMPO PODE ACEDER ESTA CORRETO
            System.out.println("PODE ACEDER INVALIDO");
            return false;
        }
        return true;
    }


    @Override
    public PhaseState getState() {
        return PhaseState.GEST_ALUNO;
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
