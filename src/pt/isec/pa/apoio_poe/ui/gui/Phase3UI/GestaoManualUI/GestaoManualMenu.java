package pt.isec.pa.apoio_poe.ui.gui.Phase3UI.GestaoManualUI;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import pt.isec.pa.apoio_poe.model.PhaseManager;
import pt.isec.pa.apoio_poe.model.fsm.PhaseState;

public class GestaoManualMenu extends BorderPane {
    PhaseManager phaseManager;
    Button btnAtribuicaoManual,btnRemocaoManual,btnVoltar;
    HBox butoes;
    public GestaoManualMenu(PhaseManager phaseManager){
        this.phaseManager=phaseManager;
        btnAtribuicaoManual = new Button();
        btnRemocaoManual = new Button();
        btnVoltar = new Button();
        butoes = new HBox();
        createViews();
        registerHandlers();
        update();
    }

    private void update() {
        if (phaseManager.getState() != PhaseState.MANUAL) {
            this.setVisible(false);
            return;
        }
        if(phaseManager.getFechado()<2){
            disableButtons(true);
        }
        this.setVisible(true);
    }

    private void registerHandlers() {
        phaseManager.addPropertyChangeListener(evt -> {update();});
        btnAtribuicaoManual.setOnAction(actionEvent -> {
            disableButtons(true);
            GestaoManualAdicionar gma = new GestaoManualAdicionar(phaseManager.queryProposta(),phaseManager.queryAluno());
            this.setCenter(gma);
            gma.btn1.setOnAction(actionEvent1 -> {
                if(gma.cbProposta.getSelectionModel().getSelectedIndex()>0 && gma.cbAluno.getSelectionModel().getSelectedIndex()>0){
                    phaseManager.insert(gma.cbProposta.getSelectionModel().getSelectedItem(),gma.cbAluno.getSelectionModel().getSelectedItem());
                }
                disableButtons(false);
                this.getChildren().remove(gma);
            });
            gma.btnVoltar.setOnAction(actionEvent1 -> {
                disableButtons(false);
                this.getChildren().remove(gma);
            });
        });
        btnRemocaoManual.setOnAction(actionEvent -> {
            disableButtons(true);
            GestaoManualEliminar gme = new GestaoManualEliminar(phaseManager.queryPropostaManual(),phaseManager.queryAlunoManual());
            this.setCenter(gme);
            gme.btn1.setOnAction(actionEvent1 -> {
                if(gme.cbProposta.getSelectionModel().getSelectedIndex()>0){
                    phaseManager.deleteAtribuicao(gme.cbProposta.getSelectionModel().getSelectedItem());
                }
                disableButtons(false);
                this.getChildren().remove(gme);
            });
            gme.btnVoltar.setOnAction(actionEvent1 -> {
                disableButtons(false);
                this.getChildren().remove(gme);
            });
        });
        btnVoltar.setOnAction(actionEvent -> {
            phaseManager.voltar();
        });
    }

    private void createViews() {
        this.setMinWidth(1200);
        this.setMinHeight(600);
        this.setStyle("-fx-background-color: #3BCEAC");
        btnAtribuicaoManual.setText("ATRIBUIÇÃO MANUAL");
        btnAtribuicaoManual.setMinWidth(400);
        btnAtribuicaoManual.setMaxWidth(400);
        btnAtribuicaoManual.setMinHeight(50);
        btnAtribuicaoManual.setStyle("-fx-font-size: 15px;-fx-font-weight: bold;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
        btnRemocaoManual.setText("REMOÇÃO MANUAL");
        btnRemocaoManual.setMinWidth(400);
        btnRemocaoManual.setMaxWidth(400);
        btnRemocaoManual.setMinHeight(50);
        btnRemocaoManual.setStyle("-fx-font-size: 15px;-fx-font-weight: bold;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
        btnVoltar.setText("VOLTAR");
        btnVoltar.setMinWidth(400);
        btnVoltar.setMaxWidth(400);
        btnVoltar.setMinHeight(50);
        btnVoltar.setStyle("-fx-font-size: 15px;-fx-font-weight: bold;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
        butoes.getChildren().addAll(btnAtribuicaoManual,btnRemocaoManual,btnVoltar);
        this.setTop(butoes);
    }

    void disableButtons(boolean b){
        btnRemocaoManual.setDisable(b);
        btnAtribuicaoManual.setDisable(b);
        btnVoltar.setDisable(b);
    }
}