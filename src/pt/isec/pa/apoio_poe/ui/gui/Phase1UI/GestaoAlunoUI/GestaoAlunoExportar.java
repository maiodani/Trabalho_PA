package pt.isec.pa.apoio_poe.ui.gui.Phase1UI.GestaoAlunoUI;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GestaoAlunoExportar  extends BorderPane {
    Label lbNomeFicheiro;
    public TextField nomeFicheiro;
    public Button btn1,btnVoltar;
    public GestaoAlunoExportar(){
        lbNomeFicheiro = new Label();
        nomeFicheiro = new TextField();
        btn1 = new Button();
        btnVoltar = new Button();
        createViews();
        registerHandlers();
        update();
    }

    private void update() {
    }

    private void registerHandlers() {
    }

    private void createViews() {
        this.setMinWidth(200);
        this.setMinHeight(150);
        this.setMaxHeight(150);
        this.setMaxWidth(200);
        this.setStyle("-fx-background-color: #FFD23F;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
        lbNomeFicheiro.setText("NOME DO FICHEIRO");
        nomeFicheiro.setText("");
        btn1.setStyle("-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
        btn1.setText("EXPORTAR");
        btnVoltar.setStyle("-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
        btnVoltar.setText("VOLTAR");
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);
        HBox buttons = new HBox();
        buttons.setSpacing(10);
        buttons.getChildren().addAll(btn1,btnVoltar);
        vBox.getChildren().addAll(lbNomeFicheiro,nomeFicheiro,buttons);
        hBox.getChildren().add(vBox);
        this.setCenter(hBox);
    }
}
