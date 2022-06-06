package pt.isec.pa.apoio_poe.model.data.phase1;

import java.io.Serializable;
import java.util.List;

public class Docente implements Serializable {
    private String nome;
    private String email;

    public Docente(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public String exportar(){
        StringBuilder str = new StringBuilder();
        str.append(nome)
                .append(",")
                .append(email)
                .append("\n");
        return str.toString();
    }

    static public boolean canBeAdded(Docente d, List<Docente> docentes, StringBuilder str, List<Aluno> alunos) {
        for(Docente a:docentes){
            if(a.getEmail().equals(d.getEmail())) {
                str.append("\nEmail do docente ").append(d.getNome()).append(" ja existe");
                return false;
            }
        }
        for (Aluno aluno :alunos){
            if (aluno.getEmail().equals(d.getEmail())){
                str.append("\nEmail do docente ").append(d.getNome()).append(" ja existe");
                return false;
            }
        }
        return true;
    }
    @Override
    public String toString() {
        return ("\nNome: "+nome+
                "\nEmail: "+email+
                "\n");
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }
}
