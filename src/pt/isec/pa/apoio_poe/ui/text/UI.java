package pt.isec.pa.apoio_poe.ui.text;

import pt.isec.pa.apoio_poe.model.fsm.PhaseContext;
import pt.isec.pa.apoio_poe.ui.text.utils.PAInput;

public class UI {
    private PhaseContext fsm;

    public UI(PhaseContext fsm) {
        this.fsm = fsm;
    }

    private boolean finish = false;

    public void start() {
        while (!finish) {
            switch (fsm.getState()) {
                case CONFIG -> configUI();
                case GEST_ALUNO, GEST_PROFESSOR, GEST_PROPOSTA -> GestUI();
                case CANDIDATURA -> cadidaturaUI();
                default -> System.out.println("State inválido");
            }
        }
    }

    private void cadidaturaUI() {
        System.out.println("Fase de Candidatura");
        switch (PAInput.chooseOption("Opcoes:","Inserção","Consulta","Voltar", "Quit")) {
            case 1 :
                fsm.insert();break;
            case 2 :
                fsm.query();break;
            case 3 :
                fsm.voltar();
                break;
            case 4 : System.exit(1);break;
        }
    }

    private void configUI() {
        System.out.println("Fase de Configuracao");
        switch (PAInput.chooseOption("Opcoes:", "Gestao Aluno", "Gestao Professor", "Gestao Propostas","Avançar","Fechar Fase", "Quit")) {
            case 1 : fsm.iniciar(1);break;
            case 2 : fsm.iniciar(2);break;
            case 3 : fsm.iniciar(3);break;
            case 4 : fsm.avancar();break;
            case 5 :
                if(fsm.fecharFase()){
                    fsm.avancar();
                }else{
                    System.out.println("NÃO HÁ PROJETOS SUFICIENTES PARA OS ALUNOS DISPONIVEIS");
                }
                break;
            case 6 : System.exit(1);break;
        }
    }

    private void GestUI() {
        switch (fsm.getState()) {
            case GEST_ALUNO -> System.out.println("Gestao Aluno");
            case GEST_PROFESSOR -> System.out.println("Gestao Professor");
            case GEST_PROPOSTA -> System.out.println("Gestao Propostas");
        }
        if(fsm.getFechado()<=0) {
            switch (PAInput.chooseOption("Opcoes:", "Insercao", "Consulta", "Editar", "Eliminar", "Exportar", "Voltar")) {
                case 1 -> System.out.println(fsm.insert());
                case 2 -> System.out.println(fsm.query());
                //case 3 -> ;
                //case 4 -> ;
                case 5 -> System.out.println(fsm.export());
                case 6 -> fsm.voltar();
            }
        }else{
            switch (PAInput.chooseOption("Opcoes:","Consulta", "Voltar")) {
                case 1 -> fsm.query();
                case 2 -> fsm.voltar();
            }
        }
    }
}