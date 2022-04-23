package pt.isec.pa.apoio_poe.ui.text;

import pt.isec.pa.apoio_poe.model.fsm.PhaseContext;
import pt.isec.pa.apoio_poe.ui.text.utils.PAInput;

public class UI {
    private PhaseContext fsm;

    public UI(PhaseContext fsm) {
        this.fsm = fsm;
    }

    private  boolean finish = false;

    public void start(){
        while (!finish){
            switch (fsm.getState()){
                case CONFIG -> configUI();
                case GEST_ALUNO -> gestAlunoUI();
                case GEST_PROFESSOR -> gestProfessorUI();
                case GEST_PROPOSTA -> gestPropostasUI();
                default -> System.out.println("State inválido");
            }
        }
    }

    private void configUI(){
        System.out.println("Fase de Configuracao");
        switch (PAInput.chooseOption("Opcoes:", "Gestao Aluno", "Gestao Professor", "Gestao Propostas", "Quit")) {
            case 1 -> fsm.iniciar(1);
            case 2 -> fsm.iniciar(2);
            case 3 -> fsm.iniciar(3);
        }
    }
    private void gestAlunoUI(){
        System.out.println("Gestao Aluno");
        switch (PAInput.chooseOption("Opcoes:", "Insercao", "Consulta","Editar", "Eliminar", "Voltar")) {
            case 1 -> fsm.addAluno();
            //case 2 -> ;
            //case 3 -> ;
            //case 4 -> ;
            case 5 -> fsm.voltar();
        }
    }
    //TODO Fazer o resto de ui para os outros estados
    private void gestProfessorUI(){
        System.out.println("TOU GESTAO PROF");
    }
    private void gestPropostasUI(){
        System.out.println("TOU PROPOSTAS");
    }
}
