package pt.isec.pa.apoio_poe.model.data.phase1.propostas;

import pt.isec.pa.apoio_poe.model.data.phase1.Aluno;
import pt.isec.pa.apoio_poe.model.data.phase1.Propostas;

public class EstProjAutoproposto extends Propostas {


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
        if (aluno != null){
            str.append("\nAluno: ");
            str.append("\n  N: "+aluno.getNumEstudante()+
                    "\n  Nome: "+aluno.getNome()+
                    "\n  Email: "+aluno.getEmail());
        }
        str.append("\n");
        return str.toString();
    }
}
