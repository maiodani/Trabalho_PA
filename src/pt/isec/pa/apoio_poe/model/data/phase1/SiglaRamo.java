package pt.isec.pa.apoio_poe.model.data.phase1;

public enum SiglaRamo {
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
}
