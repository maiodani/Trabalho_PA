package pt.isec.pa.apoio_poe.model.data.phase1;

public class Docente {
    private String nome;
    private String email;

    public Docente(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }
}
