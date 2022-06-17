package pt.isec.pa.apoio_poe.ui.gui.Phase1UI.GestaoAlunoUI;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class GestaoAlunoInserirNovo extends BorderPane {
    Label lbNumero,lbNome,lbEmail,lbCurso,lbRamo,lbClassificacao,lbAcederEstagio;
    public Label lbErro;
    public TextField tfNumero,tfNome,tfEmail,tfClassificacao;

    public Button btn1,btnVoltar;
    public RadioButton btnSim,btnNao;
    public ComboBox<String> cbCurso,cbRamo;

    public GestaoAlunoInserirNovo(){
        lbAcederEstagio = new Label("PODE ACEDER ESTAGIO? ");
        lbClassificacao = new Label("CLASSIFICAÇÃO: ");
        lbCurso = new Label("CURSO: ");
        lbEmail = new Label("EMAIL: ");
        lbNome = new Label("NOME: ");
        lbNumero = new Label("NÚMERO: ");
        lbRamo = new Label("RAMO: ");
        lbErro = new Label("");
        tfClassificacao = new TextField();
        tfNome = new TextField();
        tfEmail = new TextField();
        tfNumero = new TextField();
        btn1 = new Button("PROCESSAR");
        btnVoltar = new Button("VOLTAR");
        btnNao = new RadioButton();
        btnSim = new RadioButton();
        cbCurso = new ComboBox<>();
        cbRamo = new ComboBox<>();
        createViews();
        registerHandlers();
        update();
    }
    private void createViews() {
        VBox form = new VBox();
        form.setAlignment(Pos.CENTER);
        HBox numeroHBox = new HBox();
        tfNumero.setMinWidth(250);
        lbNumero.setMinWidth(100);
        lbNumero.setAlignment(Pos.CENTER_RIGHT);
        numeroHBox.setAlignment(Pos.CENTER);
        numeroHBox.getChildren().addAll(lbNumero,tfNumero);

        HBox nomeHbox = new HBox();
        tfNome.setMinWidth(250);
        lbNome.setMinWidth(100);
        lbNome.setAlignment(Pos.CENTER_RIGHT);
        nomeHbox.setAlignment(Pos.CENTER);
        nomeHbox.getChildren().addAll(lbNome,tfNome);

        HBox emailHbox = new HBox();
        tfEmail.setMinWidth(250);
        lbEmail.setMinWidth(100);
        lbEmail.setAlignment(Pos.CENTER_RIGHT);
        emailHbox.setAlignment(Pos.CENTER);
        emailHbox.getChildren().addAll(lbEmail,tfEmail);

        HBox cursoHbox = new HBox();
        List<String> lC= new ArrayList<>();lC.add("LEI");lC.add("LEI-LP");
        cbCurso.setItems(FXCollections
                .observableArrayList(lC));
        cbCurso.setMinWidth(250);
        cbCurso.setStyle("-fx-background-color: white;-fx-border-color: grey;-fx-border-width: 0.5px;fx-border-radius: 15px;");
        lbCurso.setMinWidth(100);
        lbCurso.setAlignment(Pos.CENTER_RIGHT);
        cursoHbox.setAlignment(Pos.CENTER);
        cursoHbox.getChildren().addAll(lbCurso,cbCurso);

        HBox ramoHBox = new HBox();
        List<String> lR= new ArrayList<>();lR.add("DA");lR.add("SI");lR.add("RAS");
        cbRamo.setItems(FXCollections
                .observableArrayList(lR));
        cbRamo.setMinWidth(250);
        cbRamo.setStyle("-fx-background-color: white;-fx-border-color: grey;-fx-border-width: 0.5px;fx-border-radius: 15px;");
        lbRamo.setMinWidth(100);
        lbRamo.setAlignment(Pos.CENTER_RIGHT);
        ramoHBox.setAlignment(Pos.CENTER);
        ramoHBox.getChildren().addAll(lbRamo,cbRamo);

        HBox classificacaoHbox = new HBox();
        tfClassificacao.setMinWidth(250);
        lbClassificacao.setMinWidth(100);
        lbClassificacao.setAlignment(Pos.CENTER_RIGHT);
        classificacaoHbox.setAlignment(Pos.CENTER);
        classificacaoHbox.getChildren().addAll(lbClassificacao,tfClassificacao);

        HBox podeAcederHBox = new HBox();
        btnSim.setText("Sim");
        btnSim.setMinWidth(125);
        btnNao.setText("Não");
        btnNao.setMinWidth(125);
        btn1.setStyle("-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
        btnVoltar.setStyle("-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
        lbAcederEstagio.setMinWidth(100);
        lbAcederEstagio.setAlignment(Pos.CENTER_RIGHT);
        podeAcederHBox.setAlignment(Pos.CENTER);
        podeAcederHBox.setSpacing(5);
        podeAcederHBox.getChildren().addAll(lbAcederEstagio,btnSim,btnNao);

        HBox buttonsHBox = new HBox();
        buttonsHBox.getChildren().addAll(btn1,btnVoltar);
        buttonsHBox.setAlignment(Pos.CENTER);
        buttonsHBox.setSpacing(40);
        buttonsHBox.setPadding(new Insets(10,0,0,0));

        lbErro.setStyle("-fx-font-weight: bold");
        form.setMaxHeight(300);
        form.setMaxWidth(450);
        form.setStyle("-fx-background-color: #FFD23F;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
        form.getChildren().addAll(lbErro,numeroHBox,nomeHbox,emailHbox,cursoHbox,ramoHBox,classificacaoHbox,podeAcederHBox,buttonsHBox);
        form.setSpacing(5);
        this.setCenter(form);
    }
    private void update() {
    }

    private void registerHandlers() {
        btnNao.setOnAction(actionEvent -> {
            btnSim.setSelected(false);
        });
        btnSim.setOnAction(actionEvent -> {
            btnNao.setSelected(false);
        });
    }


}
