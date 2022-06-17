package pt.isec.pa.apoio_poe.ui.gui.Phase1UI;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import pt.isec.pa.apoio_poe.model.PhaseManager;
import pt.isec.pa.apoio_poe.model.fsm.PhaseState;

public class Phase1Menu extends BorderPane {
    PhaseManager phaseManager;
    Button btnGestAluno,btnGestProfessor,btnGestPropostas,btnAvancar,btnQuit,btnFecharFase;

    int aux=0;
    HBox butoes;
    public Phase1Menu(PhaseManager phaseManager){
        this.phaseManager=phaseManager;
        btnAvancar = new Button();
        btnGestAluno = new Button();
        btnGestProfessor = new Button();
        btnGestPropostas = new Button();
        btnQuit = new Button();
        btnFecharFase = new Button();
        butoes = new HBox();
        createViews();
        registerHandlers();
        update();
    }

    private void update() {
        if(phaseManager.getFechado()!=0){
            btnFecharFase.setDisable(true);
        }
        if (phaseManager.getState() != PhaseState.CONFIG) {
            this.setVisible(false);
            return;
        }

        this.setVisible(true);
    }

    private void registerHandlers() {
        phaseManager.addPropertyChangeListener(evt -> {update();});
        btnGestAluno.setOnAction(actionEvent -> {
            phaseManager.iniciar(1);
        });
        btnGestProfessor.setOnAction(actionEvent -> {
            phaseManager.iniciar(2);
        });
        btnGestPropostas.setOnAction(actionEvent -> {
            phaseManager.iniciar(3);
        });
        btnAvancar.setOnAction(actionEvent -> {
            phaseManager.avancar();
        });
        btnFecharFase.setOnAction(actionEvent -> {
            if(phaseManager.getFechado()==0){
                if(phaseManager.fecharFase()){
                    btnFecharFase.setDisable(true);
                    //System.out.println("Fase Fechada");
                }else{
                }
            }else {
               // phaseManager.saveBin();
                //Platform.exit();
            }
        });
        btnQuit.setOnAction(actionEvent -> {
            phaseManager.saveBin();
            Platform.exit();
        });

    }

    private void createViews() {
        this.setMinWidth(1200);
        this.setMinHeight(600);
        this.setStyle("-fx-background-color: #3BCEAC");
        btnGestAluno.setText("GESTÃO ALUNO");
        btnGestAluno.setMinWidth(200);
        btnGestAluno.setMaxWidth(200);
        btnGestAluno.setMinHeight(50);
        btnGestAluno.setStyle("-fx-font-size: 15px;-fx-font-weight: bold;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
        btnGestProfessor.setText("GESTÃO PROFESSOR");
        btnGestProfessor.setMinWidth(200);
        btnGestProfessor.setMaxWidth(200);
        btnGestProfessor.setMinHeight(50);
        btnGestProfessor.setStyle("-fx-font-size: 15px;-fx-font-weight: bold;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
        btnGestPropostas.setText("GESTÃO PROPOSTA");
        btnGestPropostas.setMinWidth(200);
        btnGestPropostas.setMaxWidth(200);
        btnGestPropostas.setMinHeight(50);
        btnGestPropostas.setStyle("-fx-font-size: 15px;-fx-font-weight: bold;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
        btnQuit.setText("QUIT");
        btnQuit.setMinWidth(200);
        btnQuit.setMaxWidth(200);
        btnQuit.setMinHeight(50);
        btnQuit.setStyle("-fx-font-size: 15px;-fx-font-weight: bold;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
        btnAvancar.setText("AVANÇAR");
        btnAvancar.setMinWidth(200);
        btnAvancar.setMaxWidth(200);
        btnAvancar.setMinHeight(50);
        btnAvancar.setStyle("-fx-font-size: 15px;-fx-font-weight: bold;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
        btnFecharFase.setText("FECHAR FASE");
        btnFecharFase.setMinHeight(50);
        btnFecharFase.setMinWidth(200);
        btnFecharFase.setMaxWidth(200);
        btnFecharFase.setStyle("-fx-font-size: 15px;-fx-font-weight: bold;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
        if(phaseManager.getFechado()!=0){
            btnFecharFase.setDisable(true);
        }
        /*this.setAlignment(Pos.CENTER);
        this.setSpacing(15);
        */
        //this.getChildren().addAll(btnGestAluno,btnGestProfessor,btnGestPropostas,btnAvancar,btnFecharFase,btnQuit);
        //butoes.setStyle("-fx-background-color: white");
        butoes.getChildren().addAll(btnGestAluno,btnGestProfessor,btnGestPropostas,btnAvancar,btnFecharFase,btnQuit);
        this.setTop(butoes);
    }

    private void changeButtonsDisable(boolean d){
        btnAvancar.setDisable(d);
        btnQuit.setDisable(d);
        btnFecharFase.setDisable(d);
        btnGestPropostas.setDisable(d);
        btnGestAluno.setDisable(d);
        btnGestProfessor.setDisable(d);
    }
}
