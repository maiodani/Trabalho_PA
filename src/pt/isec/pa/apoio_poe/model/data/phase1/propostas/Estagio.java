package pt.isec.pa.apoio_poe.model.data.phase1.propostas;

import pt.isec.pa.apoio_poe.model.data.phase1.Aluno;
import pt.isec.pa.apoio_poe.model.data.phase1.Propostas;
import pt.isec.pa.apoio_poe.model.data.phase1.SiglaRamo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Estagio extends Propostas implements Serializable {

    private String empresa;
    public Estagio(Aluno aluno, List<SiglaRamo> ramo, String titulo, String codigoId, String empresa) {
        super(aluno, titulo, codigoId,null);
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
        if (orientador!= null){
            str.append(",").append(orientador.getEmail());
        }
        if (aluno != null){
            str.append(",").append(aluno.getNumEstudante());
        }
        str.append("\n");
        return str.toString();
    }
    static public Aluno temAluno(String[] data,List<Aluno> alunos){
        if (data.length == 6){
            for(Aluno aluno : alunos){
                if (Integer.parseInt(data[5]) == (aluno.getNumEstudante())){
                    return aluno;
                }
            }
        }
        return null;
    }
    static public List<SiglaRamo> variosRamos(String ramos){
        SiglaRamo ramo;
        List<SiglaRamo> siglas = new ArrayList<>();
        String[] ramos_divididos = ramos.split("\\|");
        for(String s : ramos_divididos){

            ramo = SiglaRamo.parse(s);
            if(ramo==null){
                return null;
            }
            siglas.add(ramo);
        }
        return siglas;
    }
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("\nCodigo: "+codigoId+
                "\nTitulo: "+titulo);
        str.append("\nEmpresa: "+empresa);
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
        if(atribuida){
            str.append("\nValidada");
        }else{
            str.append("\nNão Validada");
        }
        str.append("\n");
        return str.toString();
    }

    public String getEmpresa() {
        return empresa;
    }
}
