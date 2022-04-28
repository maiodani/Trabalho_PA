package pt.isec.pa.apoio_poe.ui.text;

import pt.isec.pa.apoio_poe.model.fsm.PhaseContext;
import pt.isec.pa.apoio_poe.ui.text.utils.PAInput;

import java.util.ArrayList;
import java.util.List;

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
        switch (PAInput.chooseOption("Opcoes:","Inserção","Consulta","Listas de Alunos","Listas de Propostas","Voltar", "Quit")) {
            case 1 :
                System.out.println(fsm.insert());break;
            case 2 :
                System.out.println(fsm.query());
                break;
            case 3:
                switch (PAInput.chooseOption("Tipo de Consulta","Alunos com Autopropostas","Alunos com Candidaturas","Alunos sem Candidaturas","Voltar")){
                    case 1:System.out.println(fsm.query(1)); break;
                    case 2:System.out.println(fsm.query(2));break;
                    case 3:System.out.println(fsm.query(3));break;
                    case 4:break;
                    default:break;
                }
                break;
            case 4:
                System.out.println(listasPropostasUI());
                break;
            case 5 :
                fsm.voltar();
                break;
            case 6 : System.exit(1);break;
        }
    }

    private String listasPropostasUI() {
        List<String> filtros = new ArrayList<>();
        List<Integer> escolhidas = new ArrayList<>();
        String listagem ="";
        filtros.add("Autopropostas de Alunos");
        filtros.add("Propostas de Docentes");
        filtros.add("Propostas com Candidaturas");
        filtros.add("Propostas sem Candidaturas");
        filtros.add("Acabar Filtragem");
        filtros.add("Voltar");
        while(true){
            switch (PAInput.chooseOption("Filtros",filtros)){
                case 1:
                    if(escolhidas.contains(1)){
                        System.out.println("Filtro "+filtros.get(1)+" já escolhido");
                    }else{
                        listagem+=fsm.query(4);
                        escolhidas.add(1);
                    }
                    break;
                case 2:
                    if(escolhidas.contains(2)){
                        System.out.println("Filtro "+filtros.get(2)+" já escolhido");
                    }else{
                        listagem+=fsm.query(5);
                        escolhidas.add(2);
                    }
                    break;
                case 3:
                    if(escolhidas.contains(3)){
                        System.out.println("Filtro "+filtros.get(3)+" já escolhido");
                    }else{
                        listagem+=fsm.query(6);
                        escolhidas.add(3);
                    }
                    break;
                case 4 :
                    if(escolhidas.contains(4)){
                        System.out.println("Filtro "+filtros.get(4)+" já escolhido");
                    }else{
                        listagem+=fsm.query(7);
                        escolhidas.add(4);
                    }
                    break;
                case 5:
                    if(listagem==""){
                        fsm.query(8);
                    }else {
                        return listagem;
                    }
                case 6:
                    return "";
            }
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