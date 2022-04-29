package pt.isec.pa.apoio_poe.model.fsm.states.phase3;

import java.util.Comparator;

public class IClassificacaoComparator implements Comparator<IClassificacao> {
    @Override
    public int compare(IClassificacao o1, IClassificacao o2) {
        return Double.compare(o2.getClassificacao(), o1.getClassificacao());
    }
}
