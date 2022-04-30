package pt.isec.pa.apoio_poe.ui.text;

import pt.isec.pa.apoio_poe.model.fsm.PhaseContext;
import pt.isec.pa.apoio_poe.ui.text.utils.PAInput;

import java.util.ArrayList;
import java.util.List;

public class UI {
    private PhaseContext fsm;
    public UI(PhaseContext fsm) {
        this.fsm = fsm;
        checkBin();
    }

    private void checkBin(){
        int op = PAInput.chooseOption("Carregar save? ", "Sim", "Não");
        if (op == 1){
            System.out.println(fsm.readBin());
        }
    }

    private boolean finish = false;

    private boolean empate = false;

    public void start() {
        while (!finish) {
            switch (fsm.getState()) {
                case CONFIG -> configUI();
                case GEST_ALUNO, GEST_PROFESSOR, GEST_PROPOSTA -> GestUI();
                case CANDIDATURA -> cadidaturaUI();
                case PROPOSTAS -> propostaUI();
                case AUTOMATICO -> propostaAutomaticoUI();
                case EMPATE -> propostaAutomaticoEmpateUI();
                case MANUAL -> propostaManualUI();
                case ATRIBUICAO_ORIENTADORES -> atribuicaoOrientadoresUI();
                case CONSULTA -> consultaUI();
                default -> System.out.println("State inválido");
            }
        }
    }

    private void consultaUI() {
        switch (PAInput.chooseOption("Fase de Consulta",
                "Lista de alunos com propostas atribuidas",
                "Lista de alunos sem propostas e com candidatura",
                "Propostas disponiveis",
                "Propostas atribuidas",
                "Estatisticas de orientações por docente",
                "Exportar",
                "Quit")
        ){
            case 1:
                System.out.println(fsm.query(1));
                break;
            case 2:
                System.out.println(fsm.query(2));
                break;
            case 3:
                System.out.println(fsm.query(3));
                break;
            case 4:
                System.out.println(fsm.query(4));
                break;
            case 5:
                System.out.println(fsm.query(5));
                break;
            case 6:
                fsm.export();
                break;
            case 7:
                fsm.saveBin();
                System.exit(1);
                break;
        }
    }

    private void atribuicaoOrientadoresUI() {
        switch (PAInput.chooseOption("Atribuição de Orientadores",
                "Associação automatica de docentes a projetos",
                "Atribuição manual",
                "Consultar atribuições",
                "Alterar atribuição",
                "Eliminar atribuição",
                "Dados diversos",
                "Exportar",
                "Voltar",
                "Fechar fase",
                "Quit")
        ){
            case 1:
                System.out.println(fsm.insert());
                break;
            case 2:
                String pro=PAInput.readString("Projeto que pretende atribuir Orientador -> ",true);
                String doc=PAInput.readString("Email do docente -> ",true);
                System.out.println(fsm.insert(pro,doc));
                break;
            case 3:
                System.out.println(fsm.query());
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                dadosDiversosUI();
                break;
            case 7:fsm.export();
                break;
            case 8:fsm.voltar();
                break;
            case 9:fsm.fecharFase();
                break;
            case 10:
                fsm.saveBin();
                System.exit(1);
                break;
        }
    }

    private void dadosDiversosUI() {
        switch (PAInput.chooseOption("Dados diversos",
                "Lista de estudantes com propostas atribuidas e orientador atribuido",
                "Lista de estudantes com propostas atribuidas mas sem orientador atribuido",
                "Estatisticas de orientações por docente"
                )
        ){
            case 1:
                System.out.println(fsm.query(1));
                break;
            case 2:
                System.out.println(fsm.query(2));
                break;
            case 3:
                System.out.println(fsm.query(3));
                break;
        }
    }

    private void propostaAutomaticoEmpateUI() {
        System.out.println(fsm.query());
        int op = PAInput.readInt("\nInsira o numero do aluno desejado: ");
        System.out.println(fsm.insert(op));
        empate = true;
    }

    private void propostaManualUI() {
        if(fsm.getFechado()>=2){
            switch (PAInput.chooseOption("Opções: ",
                    "Atribuição manual",
                    "Remoção manual",
                    "Voltar")
            ){
                case 1:
                    System.out.println(fsm.query());
                    System.out.println("Insira 0 caso precise de sair");
                    System.out.println(fsm.insert(PAInput.readString("\nInsira o ID da proposta: ", true), PAInput.readString("\nInsira o numero de aluno: ", true)));
                    break;
                case 2:
                    //META 2
                    break;
                case 3: fsm.voltar();break;
                default:break;
            }
        }else{
            if(fsm.getFechado()<2){
                System.out.println("Não é possivel porque fases anteriores estão abertas");

            }else{
                System.out.println("Fase ja se encontra fechada");
            }
            fsm.voltar();
        }
    }

    private void propostaAutomaticoUI() {
        System.out.println("Propostas Automaticas");
        if(empate){
            System.out.println(fsm.insert(2));
        }else{
            int op = PAInput.chooseOption("Opções: ",
                    "Atribuição automática de propostas com aluno associado",
                    "Atribuição automática de uma proposta disponível aos alunos que não possuem",
                    "Voltar");
            System.out.println(fsm.insert(op));
        }

        empate = false;
    }

    private void propostaUI() {
        System.out.println("Fase de Propostas");
        if (fsm.getFechado()<3){
            switch (PAInput.chooseOption("Opcoes:",
                    "Atribuições automática",
                    "Operações manuais",
                    "Lista de Alunos",
                    "Lista de Propostas",
                    "Exportar",
                    "Avançar",
                    "Voltar",
                    "Fechar Fase",
                    "Quit")) {
                case 1:
                    fsm.iniciar(1);//atribuição automatica
                    break;
                case 2:
                    fsm.iniciar(2);//atribuição manual
                    break;
                case 3:
                    listaAlunosUI();
                    break;
                case 4:
                    listasPropostasUI();
                    break;
                case 5:
                    fsm.export();
                    break;
                case 6:
                    fsm.avancar();
                    break;
                case 7:
                    fsm.voltar();
                    break;
                case 8:
                    fsm.fecharFase();
                    break;
                case 9:
                    fsm.saveBin();
                    System.exit(1);
                    break;
            }
        }else{
            switch (PAInput.chooseOption("Opcoes:",
                    "Atribuições automática",
                    "Lista de Alunos",
                    "Lista de Propostas",
                    "Exportar",
                    "Avançar",
                    "Voltar",
                    "Quit")) {
                case 1:
                    fsm.iniciar(1);//atribuição automatica
                    break;
                case 2:
                    listaAlunosUI();
                    break;
                case 3:
                    listasPropostasUI();
                    break;
                case 4:
                    fsm.export();
                    break;
                case 5:
                    fsm.avancar();
                    break;
                case 6:
                    fsm.voltar();
                    break;
                case 7:
                    fsm.saveBin();
                    System.exit(1);
                    break;
            }
        }
    }
    private void listaAlunosUI(){
        switch (PAInput.chooseOption("Listagem de alunos com :",
                "Autoproposta associada",
                "Candidatura já registada",
                "Propostas atribuida",
                "Sem proposta atribuida")
        ){
            case 1:
                System.out.println(fsm.query(0));break;
            case 2: System.out.println(fsm.query(1));break;
            case 3: System.out.println(fsm.query(2));break;
            case 4: System.out.println(fsm.query(3));break;
            default: break;
        }
    }

    private void cadidaturaUI() {
        System.out.println("Fase de Candidatura");
        if (fsm.getFechado()<2) {
            switch (PAInput.chooseOption("Opcoes:", "Inserção", "Consulta", "Listas de Alunos", "Listas de Propostas", "Exportar", "Avançar", "Voltar", "Fechar Fase", "Quit")){
                case 1 :
                    if (fsm.getFechado()<2){
                        System.out.println(fsm.insert());
                    }
                    break;
                case 2 :
                    System.out.println(fsm.query());
                    break;
                case 3:
                    switch (PAInput.chooseOption("Tipo de Consulta","Alunos com Autopropostas","Alunos com Candidaturas","Alunos sem Candidaturas","Voltar")){
                        case 1:System.out.println(fsm.query(1)); break;
                        case 2:System.out.println(fsm.query(2));break;
                        case 3:System.out.println(fsm.query(3));break;
                        default:break;
                    }
                    break;
                case 4:
                    System.out.println(listasPropostasUI());
                    break;
                case 5:fsm.export();
                    break;
                case 6:
                    fsm.avancar();
                    break;
                case 7:
                    fsm.voltar();
                    break;
                case 8 :
                    if(!fsm.fecharFase()){
                        System.out.println("Fase anterior não esta fechada, não pode avançar");
                    }
                    break;
                case 9 :
                    fsm.saveBin();
                    System.exit(1);break;
            }
        }else{
            switch (PAInput.chooseOption("Opcoes:", "Consulta", "Listas de Alunos", "Listas de Propostas", "Exportar", "Avançar", "Voltar", "Quit")) {
                case 1 :
                    System.out.println(fsm.query());
                    break;
                case 2:
                    switch (PAInput.chooseOption("Tipo de Consulta","Alunos com Autopropostas","Alunos com Candidaturas","Alunos sem Candidaturas","Voltar")){
                        case 1:System.out.println(fsm.query(1)); break;
                        case 2:System.out.println(fsm.query(2));break;
                        case 3:System.out.println(fsm.query(3));break;
                        default:break;
                    }
                    break;
                case 3:
                    System.out.println(listasPropostasUI());
                    break;
                case 4: fsm.export();
                    break;
                case 5:
                    fsm.avancar();
                    break;
                case 6:
                    fsm.voltar();
                    break;
                case 7 :
                    fsm.saveBin();
                    System.exit(1);break;
            }
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
                    if(listagem.equals("")){
                        System.out.println("SEM FILTROS");
                        listagem+=fsm.query(8);
                        return listagem;
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
        int op = 0;
        if(fsm.getFechado()!=0){
            op = PAInput.chooseOption("Opcoes:", "Gestao Aluno", "Gestao Professor", "Gestao Propostas","Avançar", "Quit");
        }else{
            op = PAInput.chooseOption("Opcoes:", "Gestao Aluno", "Gestao Professor", "Gestao Propostas", "Avançar", "Fechar Fase", "Quit");
        }
        switch (op) {
            case 1 : fsm.iniciar(1);break;
            case 2 : fsm.iniciar(2);break;
            case 3 : fsm.iniciar(3);break;
            case 4 : fsm.avancar();break;
            case 5 :
                if(fsm.getFechado()==0){
                    if(fsm.fecharFase()){
                        System.out.println("Fase Fechada");
                    }else{
                        System.out.println("NÃO HÁ PROJETOS SUFICIENTES PARA OS ALUNOS DISPONIVEIS");
                    }
                }else {
                    fsm.saveBin();
                    System.exit(1);break;
                }
                break;
            case 6 :
                fsm.saveBin();
                System.exit(1);break;
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
                case 1 -> System.out.println(fsm.query());
                case 2 -> fsm.voltar();
            }
        }
    }
}