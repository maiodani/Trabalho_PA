package pt.isec.pa.apoio_poe.ui.gui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pt.isec.pa.apoio_poe.model.PhaseManager;
import pt.isec.pa.apoio_poe.model.fsm.PhaseState;

public class GestaoAlunoMenu extends BorderPane {

    PhaseManager phaseManager;
    Button btnInserir,btnConsulta,btnEditar,btnEliminar,btnExportar,btnVoltar;

    public GestaoAlunoMenu(PhaseManager phaseManager){
        this.phaseManager=phaseManager;
        btnConsulta = new Button();
        btnEditar = new Button();
        btnEliminar = new Button();
        btnExportar = new Button();
        btnInserir = new Button();
        btnVoltar = new Button();
        createViews();
        registerHandlers();
        update();
    }
    private void createViews() {
        this.setMinWidth(1200);
        this.setMinHeight(600);
        this.setStyle("-fx-background-color: #3BCEAC");
        btnConsulta.setText("CONSULTA");
        btnConsulta.setMinHeight(50);
        btnConsulta.setMinWidth(200);
        btnConsulta.setMaxHeight(200);
        btnConsulta.setStyle("-fx-font-size: 15px;-fx-font-weight: bold;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
        btnEditar.setText("EDITAR");
        btnEditar.setMinHeight(50);
        btnEditar.setMinWidth(200);
        btnEditar.setMaxHeight(200);
        btnEditar.setStyle("-fx-font-size: 15px;-fx-font-weight: bold;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
        btnEliminar.setText("ELIMINAR");
        btnEliminar.setMinHeight(50);
        btnEliminar.setMinWidth(200);
        btnEliminar.setMaxHeight(200);
        btnEliminar.setStyle("-fx-font-size: 15px;-fx-font-weight: bold;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
        btnExportar.setText("EXPORTAR");
        btnExportar.setMinHeight(50);
        btnExportar.setMinWidth(200);
        btnExportar.setMaxHeight(200);
        btnExportar.setStyle("-fx-font-size: 15px;-fx-font-weight: bold;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
        btnInserir.setText("INSERIR");
        btnInserir.setMinHeight(50);
        btnInserir.setMinWidth(200);
        btnInserir.setMaxHeight(200);
        btnInserir.setStyle("-fx-font-size: 15px;-fx-font-weight: bold;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
        btnVoltar.setText("VOLTAR");
        btnVoltar.setMinHeight(50);
        btnVoltar.setMinWidth(200);
        btnVoltar.setMaxHeight(200);
        btnVoltar.setStyle("-fx-font-size: 15px;-fx-font-weight: bold;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
        /*this.setAlignment(Pos.CENTER);
        this.setSpacing(15);
        this.getChildren().addAll(btnInserir,btnConsulta,btnEditar,btnEliminar,btnExportar,btnVoltar);
        */
        HBox butoes = new HBox();
        butoes.setMinWidth(800);
        //butoes.setStyle("-fx-background-color: white");
        butoes.getChildren().addAll(btnInserir,btnConsulta,btnEditar,btnEliminar,btnExportar,btnVoltar);
        this.setTop(butoes);
    }
    private void update() {

        if (phaseManager.getState() != PhaseState.GEST_ALUNO) {
            this.setVisible(false);
            return;
        }
        this.setVisible(true);
    }

    private void registerHandlers() {
        phaseManager.addPropertyChangeListener(evt -> {
            update();
        });
        btnVoltar.setOnAction(actionEvent -> {
            phaseManager.voltar();
        });
        btnInserir.setOnAction(actionEvent -> {

        });
    }


}
