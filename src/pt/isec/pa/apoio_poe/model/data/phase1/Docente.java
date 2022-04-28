package pt.isec.pa.apoio_poe.model.data.phase1;

public class Docente {
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
