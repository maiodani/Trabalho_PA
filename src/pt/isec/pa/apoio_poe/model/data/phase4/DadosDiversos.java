package pt.isec.pa.apoio_poe.model.data.phase4;

import pt.isec.pa.apoio_poe.model.data.phase1.Docente;

public class DadosDiversos {
    private Docente docente;
    private int n;
    private String estatisticas;

    public DadosDiversos(Docente docente,int n) {
        this.docente=docente;
        this.n=n;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public String getEstatisticas() {
        return estatisticas;
    }

    public void setEstatisticas(String estatisticas) {
        this.estatisticas = estatisticas;
    }
}
