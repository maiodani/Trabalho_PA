package pt.isec.pa.apoio_poe.ui.gui.Phase3UI.GestaoAutomaticoUI;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import pt.isec.pa.apoio_poe.model.PhaseManager;
import pt.isec.pa.apoio_poe.model.fsm.PhaseState;

public class GestaoAutomaticoMenu extends BorderPane {
    PhaseManager phaseManager;
    Button btnAtribuicaoAutomatica1,btnAtribuicaoAutomatica2,btnVoltar;
    HBox butoes;
    public GestaoAutomaticoMenu(PhaseManager phaseManager){
        this.phaseManager=phaseManager;
        btnAtribuicaoAutomatica1 = new Button();
        btnAtribuicaoAutomatica2 = new Button();
        btnVoltar = new Button();
        butoes = new HBox();
        createViews();
        registerHandlers();
        update();
    }

    private void update() {
        if (phaseManager.getState() != PhaseState.AUTOMATICO) {
            this.setVisible(false);
            return;
        }
        if(phaseManager.empate==true){
            phaseManager.empate=false;
            btnAtribuicaoAutomatica2.fire();
        }
        this.setVisible(true);
    }

    private void registerHandlers() {
        phaseManager.addPropertyChangeListener(evt -> {update();});
        btnAtribuicaoAutomatica1.setOnAction(actionEvent -> {
            phaseManager.insert(1);
        });
        btnAtribuicaoAutomatica2.setOnAction(actionEvent -> {
            phaseManager.insert(2);
        });
        btnVoltar.setOnAction(actionEvent -> {
            phaseManager.voltar();
        });
    }

    private void createViews() {
        this.setMinWidth(1200);
        this.setMinHeight(600);
        this.setStyle("-fx-background-color: #3BCEAC");
        btnAtribuicaoAutomatica1.setText("PROPOSTAS COM ALUNO ASSOCIADO");
        btnAtribuicaoAutomatica1.setMinWidth(550);
        btnAtribuicaoAutomatica1.setMaxWidth(550);
        btnAtribuicaoAutomatica1.setMinHeight(50);
        btnAtribuicaoAutomatica1.setStyle("-fx-font-size: 15px;-fx-font-weight: bold;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
        btnAtribuicaoAutomatica2.setText("PROPOSTAS DISPONIVEIS AOS ALUNOS QUE NÃO POSSUEM");
        btnAtribuicaoAutomatica2.setMinWidth(550);
        btnAtribuicaoAutomatica2.setMaxWidth(550);
        btnAtribuicaoAutomatica2.setMinHeight(50);
        btnAtribuicaoAutomatica2.setStyle("-fx-font-size: 15px;-fx-font-weight: bold;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
        btnVoltar.setText("VOLTAR");
        btnVoltar.setMinWidth(100);
        btnVoltar.setMaxWidth(100);
        btnVoltar.setMinHeight(50);
        btnVoltar.setStyle("-fx-font-size: 15px;-fx-font-weight: bold;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
        butoes.getChildren().addAll(btnAtribuicaoAutomatica1,btnAtribuicaoAutomatica2,btnVoltar);
        this.setTop(butoes);
    }

    void disableButtons(boolean b){
        btnAtribuicaoAutomatica1.setDisable(b);
        btnAtribuicaoAutomatica2.setDisable(b);
        btnVoltar.setDisable(b);
    }
}