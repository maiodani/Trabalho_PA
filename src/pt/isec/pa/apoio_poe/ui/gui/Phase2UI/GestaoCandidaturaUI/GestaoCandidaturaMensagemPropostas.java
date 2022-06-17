package pt.isec.pa.apoio_poe.ui.gui.Phase2UI.GestaoCandidaturaUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GestaoCandidaturaMensagemPropostas extends BorderPane {
    public RadioButton rb1,rb2,rb3,rb4;
    public Button btnVoltar;
    Label mensagem;
    
    public GestaoCandidaturaMensagemPropostas(){

        rb1 = new RadioButton("AUTOPROPOSTA(S) DE ALUNO(S)");
        rb2 = new RadioButton("PROPOSTA(S) DE DOCENTE(S)");
        rb3 = new RadioButton("PROPOSTA(S) COM CANDIDATURA");
        rb4 = new RadioButton("PROPOSTA(S) SEM CANDIDATURA");
        btnVoltar = new Button("VOLTAR");
        mensagem = new Label("TIPO DE PROPOSTAS");
        createViews();
        registerHandlers();
        update();
    }

    private void update() {
    }

    private void registerHandlers() {
    }

    private void createViews() {

        VBox all = new VBox();
        HBox butoes = new HBox();
        rb1.setStyle("-fx-font-weight: bold;");
        rb2.setStyle("-fx-font-weight: bold;");
        rb3.setStyle("-fx-font-weight: bold;");
        rb4.setStyle("-fx-font-weight: bold;");
        btnVoltar.setStyle("-fx-font-weight: bold;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-background-radius: 15px;");

        all.setMaxHeight(150);
        all.setMaxWidth(900);
        all.setStyle("-fx-background-color: #FFD23F;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
        butoes.setPadding(new Insets(15,5,5,10));
        butoes.setSpacing(10);
        butoes.setAlignment(Pos.CENTER);
        butoes.getChildren().addAll(rb1,rb2,rb3,rb4);
        all.getChildren().addAll(mensagem,butoes,btnVoltar);
        all.setAlignment(Pos.CENTER);
        all.setPadding(new Insets(0,0,0,0));
        this.setCenter(all);
    }
}
