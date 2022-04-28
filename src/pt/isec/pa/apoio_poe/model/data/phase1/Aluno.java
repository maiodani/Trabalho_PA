package pt.isec.pa.apoio_poe.model.data.phase1;

public class Aluno {
    private long numEstudante;
    private String nome;
    private String email;
    private SiglaCurso siglaCurso;
    private SiglaRamo siglaRamo;
    private double classificacao;
    private Boolean podeAceder;

    public Aluno(long numEstudante, String nome, String email, SiglaCurso siglaCurso, SiglaRamo siglaRamo, double classificacao, Boolean podeAceder) {
        this.numEstudante = numEstudante;
        this.nome = nome;
        this.email = email;
        this.siglaCurso = siglaCurso;
        this.siglaRamo = siglaRamo;
        this.classificacao = classificacao;
        this.podeAceder = podeAceder;
    }

    public long getNumEstudante() {
        return numEstudante;
    }
    public String getNome() {
        return nome;
    }
    public String getEmail() {
        return email;
    }
    public SiglaCurso getSiglaCurso() {
        return siglaCurso;
    }
    public SiglaRamo getSiglaRamo() {
        return siglaRamo;
    }
    public double getClassificacao() {
        return classificacao;
    }
    public Boolean getPodeAceder() {
        return podeAceder;
    }

    public String exportar(){
        StringBuilder str = new StringBuilder();
        str.append(numEstudante)
                .append(",")
                .append(nome)
                .append(",")
                .append(email)
                .append(",")
                .append(SiglaCurso.parseString(siglaCurso))
                .append(",")
                .append(siglaRamo)
                .append(",")
                .append(classificacao)
                .append(",")
                .append(podeAceder)
                .append("\n");
        return str.toString();
    }
    @Override
    public String toString() {
        return "Aluno{" +
                "numEstudante=" + numEstudante +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", siglaCurso=" + siglaCurso +
                ", siglaRamo=" + siglaRamo +
                ", classificacao=" + classificacao +
                ", podeAceder=" + podeAceder +
                '}';
    }
}
