package pt.isec.pa.apoio_poe.ui.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MensagemSave extends BorderPane {

    Button btnSim, btnNao;
    Label mensagem;
    public MensagemSave(){
        btnNao = new Button();
        btnSim = new Button();
        mensagem = new Label();
        createViews();
        registerHandlers();
        update();
    }

    private void update() {
    }

    private void registerHandlers() {
    }

    private void createViews() {
        this.setMinWidth(400);
        this.setMinHeight(400);
        this.setStyle("-fx-background-color: #3BCEAC");
        mensagem.setText("Pretende carregar um save?");
        mensagem.setStyle("-fx-font-size: 15px");
        btnSim.setText("SIM");
        btnSim.setStyle("-fx-background-color: #0EAD69;-fx-font-weight: bold;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: #0EAD69;-fx-background-radius: 15px;");
        btnSim.setMinWidth(75);
        btnNao.setText("NAO");
        btnNao.setStyle("-fx-background-color: #EE4266;-fx-font-weight: bold;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: #EE4266;-fx-background-radius: 15px;");
        btnNao.setMinWidth(75);
        VBox all = new VBox();
        HBox butoes = new HBox();
        all.setMaxHeight(100);
        all.setMaxWidth(225);
        all.setStyle("-fx-background-color: #FFD23F;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
        butoes.setPadding(new Insets(15,5,5,10));
        butoes.setSpacing(10);
        butoes.setAlignment(Pos.CENTER);
        butoes.getChildren().addAll(btnNao,btnSim);
        all.getChildren().addAll(mensagem,butoes);
        System.out.println(this.getHeight()+" "+this.getWidth());
        all.setAlignment(Pos.CENTER);
        all.setPadding(new Insets(0,0,0,0));
        this.setCenter(all);
    }
}
