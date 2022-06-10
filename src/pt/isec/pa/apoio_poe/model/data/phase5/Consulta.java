package pt.isec.pa.apoio_poe.model.data.phase5;

import pt.isec.pa.apoio_poe.model.data.phase1.Aluno;
import pt.isec.pa.apoio_poe.model.data.phase1.Propostas;
import pt.isec.pa.apoio_poe.model.data.phase2.Candidatura;

import java.util.List;

public class Consulta {
    static public StringBuilder export(List<Candidatura> candidaturas,List<Aluno> alunos,List<Propostas> propostas) {
        int i = 1;
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
        return str;
    }
}
