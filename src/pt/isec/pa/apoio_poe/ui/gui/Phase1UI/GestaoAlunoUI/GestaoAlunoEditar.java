package pt.isec.pa.apoio_poe.ui.gui.Phase1UI.GestaoAlunoUI;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pt.isec.pa.apoio_poe.model.data.phase1.Aluno;
import pt.isec.pa.apoio_poe.model.data.phase1.SiglaCurso;
import pt.isec.pa.apoio_poe.model.data.phase1.SiglaRamo;

import java.util.ArrayList;
import java.util.List;

public class GestaoAlunoEditar extends BorderPane {
    Label lbNumero, lbNome, lbEmail, lbCurso, lbRamo, lbClassificacao, lbAcederEstagio;
    public Label lbErro;
    public TextField  tfNome, tfEmail, tfClassificacao;

    public Button btn1, btnVoltar;
    public RadioButton btnSim, btnNao;
    public ComboBox<String> cbCurso, cbRamo,cbNumero;

    List<Aluno> a;

    public GestaoAlunoEditar(List<Aluno> a) {
        this.a=a;
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
        btn1 = new Button("PROCESSAR");
        btnVoltar = new Button("VOLTAR");
        btnNao = new RadioButton();
        btnSim = new RadioButton();
        cbCurso = new ComboBox<>();
        cbRamo = new ComboBox<>();
        cbNumero = new ComboBox<>();
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        VBox form = new VBox();
        form.setAlignment(Pos.CENTER);
        HBox numeroHBox = new HBox();
        cbNumero.setMinWidth(250);
        List<String> lN = new ArrayList<>();
        lN.add("");
        for(Aluno aluno:a){
            lN.add(String.valueOf(aluno.getNumEstudante()));
        }
        cbNumero.setItems(FXCollections
                .observableArrayList(lN));
        lbNumero.setMinWidth(100);
        lbNumero.setAlignment(Pos.CENTER_RIGHT);
        numeroHBox.setAlignment(Pos.CENTER);
        numeroHBox.getChildren().addAll(lbNumero, cbNumero);

        HBox nomeHbox = new HBox();
        tfNome.setMinWidth(250);
        lbNome.setMinWidth(100);
        lbNome.setAlignment(Pos.CENTER_RIGHT);
        nomeHbox.setAlignment(Pos.CENTER);
        nomeHbox.getChildren().addAll(lbNome, tfNome);

        HBox emailHbox = new HBox();
        tfEmail.setMinWidth(250);
        lbEmail.setMinWidth(100);
        lbEmail.setAlignment(Pos.CENTER_RIGHT);
        emailHbox.setAlignment(Pos.CENTER);
        emailHbox.getChildren().addAll(lbEmail, tfEmail);

        HBox cursoHbox = new HBox();
        List<String> lC = new ArrayList<>();
        lC.add("LEI");
        lC.add("LEI-LP");
        cbCurso.setItems(FXCollections
                .observableArrayList(lC));
        cbCurso.setMinWidth(250);
        cbCurso.setStyle("-fx-background-color: white;-fx-border-color: grey;-fx-border-width: 0.5px;fx-border-radius: 15px;");
        lbCurso.setMinWidth(100);
        lbCurso.setAlignment(Pos.CENTER_RIGHT);
        cursoHbox.setAlignment(Pos.CENTER);
        cursoHbox.getChildren().addAll(lbCurso, cbCurso);

        HBox ramoHBox = new HBox();
        List<String> lR = new ArrayList<>();
        lR.add("DA");
        lR.add("SI");
        lR.add("RAS");
        cbRamo.setItems(FXCollections
                .observableArrayList(lR));
        cbRamo.setMinWidth(250);
        cbRamo.setStyle("-fx-background-color: white;-fx-border-color: grey;-fx-border-width: 0.5px;fx-border-radius: 15px;");
        lbRamo.setMinWidth(100);
        lbRamo.setAlignment(Pos.CENTER_RIGHT);
        ramoHBox.setAlignment(Pos.CENTER);
        ramoHBox.getChildren().addAll(lbRamo, cbRamo);

        HBox classificacaoHbox = new HBox();
        tfClassificacao.setMinWidth(250);
        lbClassificacao.setMinWidth(100);
        lbClassificacao.setAlignment(Pos.CENTER_RIGHT);
        classificacaoHbox.setAlignment(Pos.CENTER);
        classificacaoHbox.getChildren().addAll(lbClassificacao, tfClassificacao);

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
        podeAcederHBox.getChildren().addAll(lbAcederEstagio, btnSim, btnNao);

        HBox buttonsHBox = new HBox();
        buttonsHBox.getChildren().addAll(btn1, btnVoltar);
        buttonsHBox.setAlignment(Pos.CENTER);
        buttonsHBox.setSpacing(40);
        buttonsHBox.setPadding(new Insets(10, 0, 0, 0));

        lbErro.setStyle("-fx-font-weight: bold");
        form.setMaxHeight(200);
        form.setMaxWidth(400);
        form.setStyle("-fx-background-color: #FFD23F;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
        form.getChildren().addAll(lbErro, numeroHBox, nomeHbox, emailHbox, cursoHbox, ramoHBox, classificacaoHbox, podeAcederHBox, buttonsHBox);
        form.setSpacing(5);
        changeDisableFields(true);
        this.setCenter(form);
    }

    private void update() {
    }

    private void registerHandlers() {
        cbNumero.setOnAction(actionEvent -> {
            changeDisableFields(false);
            if(!(cbNumero.getSelectionModel().getSelectedIndex()==0)){
                for(Aluno aluno:a){
                    if(Long.parseLong(cbNumero.getSelectionModel().getSelectedItem())==aluno.getNumEstudante()){
                        tfClassificacao.setText(String.valueOf(aluno.getClassificacao()));
                        tfEmail.setText(String.valueOf(aluno.getEmail()));
                        tfNome.setText(String.valueOf(aluno.getNome()));
                        cbCurso.getSelectionModel().select(SiglaCurso.parseString(aluno.getSiglaCurso()));
                        cbRamo.getSelectionModel().select(SiglaRamo.parseString(aluno.getSiglaRamo()));
                        if(aluno.getPodeAceder()){
                            btnSim.setSelected(true);
                        }else{
                            btnNao.setSelected(true);
                        }
                    }
                }
            }else{
                tfClassificacao.setText("");
                tfEmail.setText("");
                tfNome.setText("");
                cbCurso.getSelectionModel().clearSelection();
                cbRamo.getSelectionModel().clearSelection();
                btnNao.setSelected(false);
                btnSim.setSelected(false);
                changeDisableFields(true);
            }

        });
        btnNao.setOnAction(actionEvent -> {
            btnSim.setSelected(false);
        });
        btnSim.setOnAction(actionEvent -> {
            btnNao.setSelected(false);
        });
    }
    private void changeDisableFields(boolean b){
        tfClassificacao.setDisable(b);
        tfEmail.setDisable(b);
        tfNome.setDisable(b);
        cbCurso.setDisable(b);
        cbRamo.setDisable(b);
        btnNao.setDisable(b);
        btnSim.setDisable(b);
        btn1.setDisable(b);
    }

}

