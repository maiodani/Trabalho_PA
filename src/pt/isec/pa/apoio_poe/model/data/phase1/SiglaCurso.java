package pt.isec.pa.apoio_poe.model.data.phase1;

import java.io.Serializable;

public enum SiglaCurso implements Serializable {
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
    public static String parseString(SiglaCurso curso){
        switch (curso){
            case LEI : return "LEI";
            case LEI_PL: return "LEI-PL";
            default: return null;
        }
    }
}
