package pt.isec.pa.apoio_poe.ui.gui.Phase2UI.GestaoCandidaturaUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GestaoCandidaturaMensagemListar extends BorderPane {
    public Button btn1, btn2,btn3;
    Label mensagem;

    public GestaoCandidaturaMensagemListar(){
        btn1 = new Button("ALUNOS COM AUTOPROPOSTA(S)");
        btn2 = new Button("ALUNOS COM CANDIDATURA(S)");
        btn3 = new Button("ALUNOS SEM CANDIDATURA(S)");
        mensagem = new Label("TIPO DE LISTAGEM");
        createViews();
        registerHandlers();
        update();
    }

    private void update() {
    }

    private void registerHandlers() {
    }

    private void createViews() {
        this.setMinWidth(800);
        this.setMinHeight(400);
        this.setStyle("-fx-background-color: #3BCEAC");
        mensagem.setStyle("-fx-font-size: 15px");
        btn1.setStyle("-fx-background-color: #0EAD69;-fx-font-weight: bold;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: #0EAD69;-fx-background-radius: 15px;");
        btn1.setMinWidth(250);
        btn2.setStyle("-fx-background-color: #EE4266;-fx-font-weight: bold;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: #EE4266;-fx-background-radius: 15px;");
        btn2.setMinWidth(250);
        btn3.setStyle("-fx-background-color: #0EAD69;-fx-font-weight: bold;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: #0EAD69;-fx-background-radius: 15px;");
        btn3.setMinWidth(250);
        VBox all = new VBox();
        HBox butoes = new HBox();
        all.setMaxHeight(150);
        all.setMaxWidth(350);
        all.setStyle("-fx-background-color: #FFD23F;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
        butoes.setPadding(new Insets(15,5,5,10));
        butoes.setSpacing(10);
        butoes.setAlignment(Pos.CENTER);
        butoes.getChildren().addAll(btn1,btn2,btn3);
        all.getChildren().addAll(mensagem,butoes);
        all.setAlignment(Pos.CENTER);
        all.setPadding(new Insets(0,0,0,0));
        this.setCenter(all);
    }
}