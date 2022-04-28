package pt.isec.pa.apoio_poe.model.data.phase1.propostas;

import pt.isec.pa.apoio_poe.model.data.phase1.Aluno;
import pt.isec.pa.apoio_poe.model.data.phase1.Propostas;
import pt.isec.pa.apoio_poe.model.data.phase1.SiglaRamo;

import java.util.List;

public class Estagio extends Propostas {

    private String empresa;
    public Estagio(Aluno aluno, List<SiglaRamo> ramo, String titulo, String codigoId, String empresa) {
        super(aluno, titulo, codigoId);
        this.empresa = empresa;
        this.ramo = ramo;
    }

    public String exportar(){
        StringBuilder str = new StringBuilder();
        str.append("T1,")
                .append(codigoId)
                .append(",")
                .append(ramoString(ramo))
                .append(",")
                .append(titulo)
                .append(",")
                .append(empresa);
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
        str.append("\nEmpresa: "+empresa);
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

    public String getEmpresa() {
        return empresa;
    }
}
