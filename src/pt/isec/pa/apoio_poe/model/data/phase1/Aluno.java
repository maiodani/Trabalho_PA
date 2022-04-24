package pt.isec.pa.apoio_poe.model.data.phase1;

public class Aluno {
    private long numEstudante;
    private String nome;
    private String email;
    private SiglaCurso siglaCurso;
    private SiglaRamo siglaRamo;
    private double classificacao;
    private boolean podeAceder;

    public Aluno(long numEstudante, String nome, String email, SiglaCurso siglaCurso, SiglaRamo siglaRamo, double classificacao, boolean podeAceder) {
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

    public void setNumEstudante(long numEstudante) {
        this.numEstudante = numEstudante;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public SiglaCurso getSiglaCurso() {
        return siglaCurso;
    }

    public void setSiglaCurso(SiglaCurso siglaCurso) {
        this.siglaCurso = siglaCurso;
    }

    public SiglaRamo getSiglaRamo() {
        return siglaRamo;
    }

    public void setSiglaRamo(SiglaRamo siglaRamo) {
        this.siglaRamo = siglaRamo;
    }

    public double getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(double classificacao) {
        this.classificacao = classificacao;
    }

    public boolean isPodeAceder() {
        return podeAceder;
    }

    public void setPodeAceder(boolean podeAceder) {
        this.podeAceder = podeAceder;
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
