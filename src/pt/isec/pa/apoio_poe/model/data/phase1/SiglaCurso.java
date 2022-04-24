package pt.isec.pa.apoio_poe.model.data.phase1;

public enum SiglaCurso {
    LEI,
    LEI_PL;

    public static SiglaCurso parse(String s){
        s.replaceAll("\\s+","");
        switch (s){
            case "LEI": return LEI;
            case "LEI-PL":return LEI_PL;
            default:return null;
        }
    }
}
