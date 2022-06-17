package pt.isec.pa.apoio_poe.ui.gui.Phase1UI.GestaoDocenteUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GestaoDocenteInserirNovo extends BorderPane {
    Label lbNome,lbEmail;
    public Label lbErro;
    public TextField tfNome,tfEmail;

    public Button btn1,btnVoltar;

    public GestaoDocenteInserirNovo(){
        lbEmail = new Label("EMAIL: ");
        lbNome = new Label("NOME: ");
        lbErro = new Label("");
        tfNome = new TextField();
        tfEmail = new TextField();
        btn1 = new Button("PROCESSAR");
        btnVoltar = new Button("VOLTAR");
        createViews();
        registerHandlers();
        update();
    }
    private void createViews() {
        VBox form = new VBox();
        form.setAlignment(Pos.CENTER);

        HBox nomeHbox = new HBox();
        tfNome.setMinWidth(250);
        lbNome.setMinWidth(50);
        lbNome.setAlignment(Pos.CENTER_RIGHT);
        nomeHbox.setAlignment(Pos.CENTER);
        nomeHbox.getChildren().addAll(lbNome,tfNome);

        HBox emailHbox = new HBox();
        tfEmail.setMinWidth(250);
        lbEmail.setMinWidth(50);
        lbEmail.setAlignment(Pos.CENTER_RIGHT);
        emailHbox.setAlignment(Pos.CENTER);
        emailHbox.getChildren().addAll(lbEmail,tfEmail);


        btn1.setStyle("-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
        btnVoltar.setStyle("-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");

        HBox buttonsHBox = new HBox();
        buttonsHBox.getChildren().addAll(btn1,btnVoltar);
        buttonsHBox.setAlignment(Pos.CENTER);
        buttonsHBox.setSpacing(40);
        buttonsHBox.setPadding(new Insets(10,0,0,0));

        lbErro.setStyle("-fx-font-weight: bold");
        form.setMaxHeight(200);
        form.setMaxWidth(450);
        form.setStyle("-fx-background-color: #FFD23F;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
        form.getChildren().addAll(lbErro,nomeHbox,emailHbox,buttonsHBox);
        form.setSpacing(5);
        this.setCenter(form);
    }
    private void update() {
    }

    private void registerHandlers() {
    }


}
