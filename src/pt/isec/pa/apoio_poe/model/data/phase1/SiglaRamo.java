package pt.isec.pa.apoio_poe.model.data.phase1;

import java.io.Serializable;

public enum SiglaRamo implements Serializable {
    DA,
    RAS,
    SI;

    public static SiglaRamo parse(String s){
        s.replaceAll("\\s+","");
        switch (s){
            case "DA": return DA;
            case "RAS":return RAS;
            case "SI":return SI;
            default:return null;
        }
    }
    public static String parseString(SiglaRamo ramo){
        switch (ramo){
            case DA: return "DA";
            case SI: return "SI";
            case RAS: return "RAS";
            default: return null;
        }
    }
}
