package pt.isec.pa.apoio_poe.model.data.phase1.propostas;

import pt.isec.pa.apoio_poe.model.data.phase1.Aluno;
import pt.isec.pa.apoio_poe.model.data.phase1.Propostas;

import java.io.Serializable;

public class EstProjAutoproposto extends Propostas implements Serializable {


    public EstProjAutoproposto(Aluno aluno, String titulo, String codigoId) {
        super(aluno,titulo, codigoId,null);
    }

    @Override
    public String exportar(){
        StringBuilder str = new StringBuilder();
        str.append("T3,")
                .append(codigoId)
                .append(",")
                .append(titulo);
        if (orientador!= null){
            str.append(",").append(orientador.getEmail());
        }
        if (aluno != null){
            str.append(",").append(aluno.getNumEstudante());
        }
        str.append("\n");
        return str.toString();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("\nCodigo: "+codigoId+
                "\nTitulo: "+titulo);
        if(orientador != null){
            str.append("\nDocente: ");
            str.append("\n  Nome: "+orientador.getNome()+
                    "\n  Email: "+orientador.getEmail());
        }
        if (aluno != null){
            str.append("\nAluno: ");
            str.append("\n  N: "+aluno.getNumEstudante()+
                    "\n  Nome: "+aluno.getNome()+
                    "\n  Email: "+aluno.getEmail());
        }
        if(atribuida){
            str.append("\nValidada");
        }else{
            str.append("\nNão Validada");
        }
        str.append("\n");
        return str.toString();
    }
}
