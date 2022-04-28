package pt.isec.pa.apoio_poe.model.data.phase1.propostas;
import pt.isec.pa.apoio_poe.model.data.phase1.Aluno;
import pt.isec.pa.apoio_poe.model.data.phase1.Docente;
import pt.isec.pa.apoio_poe.model.data.phase1.Propostas;
import pt.isec.pa.apoio_poe.model.data.phase1.SiglaRamo;

import java.util.List;

public class Projeto extends Propostas {


    private Docente orientador;

    public Projeto(Aluno aluno, Docente orientador, List<SiglaRamo> ramo, String titulo, String codigoId) {
        super(aluno, titulo, codigoId);
        this.orientador = orientador;
        this.ramo = ramo;
    }


    @Override
    public String exportar(){
        StringBuilder str = new StringBuilder();
        str.append("T2,")
                .append(codigoId)
                .append(",")
                .append(ramoString(ramo))
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
        if (ramo != null){
            str.append("\nRamo: "+ramo);
        }
        str.append("\n");
        return str.toString();
    }

    public Docente getOrientador() {
        return orientador;
    }
}
