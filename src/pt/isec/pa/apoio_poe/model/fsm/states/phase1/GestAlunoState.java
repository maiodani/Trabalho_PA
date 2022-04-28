package pt.isec.pa.apoio_poe.model.fsm.states.phase1;

import pt.isec.pa.apoio_poe.model.CsvManager;
import pt.isec.pa.apoio_poe.model.data.PhasesData;
import pt.isec.pa.apoio_poe.model.data.phase1.Aluno;
import pt.isec.pa.apoio_poe.model.data.phase1.Docente;
import pt.isec.pa.apoio_poe.model.data.phase1.SiglaCurso;
import pt.isec.pa.apoio_poe.model.data.phase1.SiglaRamo;
import pt.isec.pa.apoio_poe.model.fsm.PhaseContext;
import pt.isec.pa.apoio_poe.model.fsm.PhaseState;
import pt.isec.pa.apoio_poe.model.fsm.PhaseStateAdapter;

import javax.print.Doc;
import java.util.List;

public class GestAlunoState extends PhaseStateAdapter {
    public GestAlunoState(PhasesData phasesData, PhaseContext context) {
        super(phasesData, context);
    }
    @Override
    public String insert(){
        String data[][] = CsvManager.readFile("alunos_v2.csv");
        StringBuilder str = new StringBuilder();
        List<Aluno> alunos = phasesData.getAlunos();
        if(data!=null){
            str.append("ERROS:");
            for(int i=0;i<data.length; i++){
                Aluno a = new Aluno(
                        Long.parseLong(data[i][0]),
                        data[i][1],
                        data[i][2],
                        SiglaCurso.parse(data[i][3]),
                        SiglaRamo.parse(data[i][4]),
                        Double.parseDouble(data[i][5]),
                        parseBoolean(data[i][6].replaceAll("\\s+",""))
                );

                if(canBeAdded(a, alunos, str)){
                    alunos.add(a);
                }
            }
        }else{
            str.append("\nFicheiro não possui informação");
        }
        str.append("\n");
        return str.toString();
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

    private boolean canBeAdded(Aluno a, List<Aluno> alunos, StringBuilder str) {
        for(Aluno aux:alunos){//CHECK SE JA EXISTE
            if(aux.getNumEstudante()==a.getNumEstudante()){
                str.append("\nAluno ").append(a.getNumEstudante()).append(" ja se encontra registrado");
                return false;
            }
            if (aux.getEmail().equals(a.getEmail())){
                str.append("\nO email do Aluno ").append(a.getNumEstudante()).append(" já existe");
                return false;
            }
        }
        List<Docente> docentes= phasesData.getDocentes();
        for (Docente docente : docentes){
            if (docente.getEmail().equals(a.getEmail())){
                str.append("\nO email do Aluno ").append(a.getNumEstudante()).append(" já existe");
                return false;
            }
        }

        if(a.getSiglaCurso()==null){//SIGLA ESTA ERRADA
            str.append("\nSigla de curso do aluno ").append(a.getNumEstudante()).append(" não reconhecida");
            return false;
        }

        if(a.getSiglaRamo()==null){//SIGLA ESTA ERRADA
            str.append("\nSigla do ramo do aluno ") .append(a.getNumEstudante()).append(" não reconhecida");
            return false;
        }

        if(a.getClassificacao()<0.0 || a.getClassificacao()>1.0){//VERIRICAR QUAL É O LIMITE
            str.append("\nClassificação do aluno ").append(a.getNumEstudante()).append(" inválida");
            return false;
        }

        if(a.getPodeAceder()==null){//VERIFICAR SE O CAMPO PODE ACEDER ESTA CORRETO
            str.append("\nPermissões do aluno ").append(a.getNumEstudante()).append(" inválidas");
            return false;
        }
        return true;
    }

    @Override
    public String export() {
        List<Aluno> alunos = phasesData.getAlunos();
        StringBuilder str = new StringBuilder();
        for (Aluno aluno : alunos){
            str.append(aluno.exportar());
        }
        str.deleteCharAt(str.length()-1);
        return CsvManager.writeFile("alunos_export.csv", str);
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
