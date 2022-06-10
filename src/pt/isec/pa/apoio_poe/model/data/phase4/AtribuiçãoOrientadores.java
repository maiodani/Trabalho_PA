package pt.isec.pa.apoio_poe.model.data.phase4;

import pt.isec.pa.apoio_poe.model.data.phase1.Aluno;
import pt.isec.pa.apoio_poe.model.data.phase1.Docente;
import pt.isec.pa.apoio_poe.model.data.phase1.Propostas;
import pt.isec.pa.apoio_poe.model.data.phase1.propostas.Projeto;
import pt.isec.pa.apoio_poe.model.data.phase2.Candidatura;

import java.util.List;

public class AtribuiçãoOrientadores {

    static public StringBuilder insert(List<Propostas> p){
        StringBuilder str = new StringBuilder();
        for(Propostas pa:p){
            if(pa instanceof Projeto && pa.isAtribuida()){
                Projeto aux = (Projeto)pa;
                aux.setDocenteConfirmado(true);
                str.append("\nDocente "+pa.getOrientador().getNome()+" confirmado no projeto "+pa.getTitulo()+" como orientador");
            }
        }
        return str;
    }

    static public StringBuilder insert(List<Propostas> p,List<Docente> d,String ... options) {
        StringBuilder str = new StringBuilder();
        Propostas propostas = null;
        Docente docente = null;
        boolean aux=true;
        for(Propostas pa:p){
            if(pa.getCodigoId().equals(options[0])){
                if(pa.getOrientador()!=null){
                    str.append("\nProjeto já com Docente atribuido");
                    return str;
                }
                if(!pa.isAtribuida()){
                    str.append("\nProjeto sem aluno atribuido");
                    return str;
                }
                propostas=pa;
                aux=false;
                break;
            }
        }
        if(aux){
            str.append("\nProjeto com o codigo "+options[0]+" não encontrado");
            return str;
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
            return str;
        }

        propostas.setOrientador(docente);
        str.append("Docente "+docente.getNome()+" atribuido ao projeto "+propostas.getCodigoId());
        return str;
    }

    public static StringBuilder export(List<Candidatura> candidaturas,List<Aluno> alunos,List<Propostas> propostas){
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
