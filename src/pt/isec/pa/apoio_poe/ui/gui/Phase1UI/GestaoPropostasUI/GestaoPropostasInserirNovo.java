package pt.isec.pa.apoio_poe.ui.gui.Phase1UI.GestaoPropostasUI;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pt.isec.pa.apoio_poe.model.data.phase1.Aluno;
import pt.isec.pa.apoio_poe.model.data.phase1.Docente;

import java.util.ArrayList;
import java.util.List;

public class GestaoPropostasInserirNovo extends BorderPane {
    Label lbEstagio,lbAutoproposta,lbProjeto,lbCodigo,lbRamos,lbTitulo,lbEmpresa,lbDocente,lbAluno;
    public Label lbErro;
    TextField tfCodigo,tfTitulo,tfEmpresa;
    public RadioButton rbEstagio,rbAutoprosposta,rbProjeto,rbRAS,rbDA,rbSI;
    public Button btn1,btnVoltar;

    ComboBox<String> cbNomeDocente,cbNumeroAluno;

    List<Docente> d;
    List<Aluno> a;
    VBox form;
    public GestaoPropostasInserirNovo(List<Docente> d,List<Aluno> a){
        this.a=a;
        this.d=d;
        lbEstagio = new Label("ESTAGIO");
        lbAutoproposta = new Label("AUTOPROPOSTA");
        lbProjeto = new Label("PROJETO");
        lbErro = new Label("");
        lbCodigo = new Label("CODIGO: ");
        lbRamos = new Label("RAMOS: ");
        lbTitulo = new Label("TITULO: ");
        lbEmpresa = new Label("NOME DA EMPRESA: ");
        lbDocente = new Label("DOCENTE: ");
        lbAluno = new Label("ALUNO: ");
        tfEmpresa = new TextField();
        tfTitulo = new TextField();
        tfCodigo = new TextField();
        rbAutoprosposta = new RadioButton("AUTOPROPOSTA");
        rbEstagio = new RadioButton("ESTAGIO");
        rbProjeto = new RadioButton("PROJETO");
        rbDA = new RadioButton("DA");
        rbRAS = new RadioButton("RAS");
        rbSI = new RadioButton("SI");
        btn1 = new Button("PROCESSAR");
        btnVoltar = new Button("VOLTAR");
        cbNomeDocente = new ComboBox<>();
        cbNumeroAluno = new ComboBox<>();
        form = new VBox();
        createViews();
        registerHandlers();
        update();
    }
    private void createViews() {
        this.setCenter(createStart());
    }

    private VBox createStart(){
        form.setAlignment(Pos.CENTER);

        HBox tipoHbox = new HBox();
        tipoHbox.setSpacing(10);
        tipoHbox.setAlignment(Pos.CENTER);
        tipoHbox.getChildren().addAll(rbProjeto,rbEstagio,rbAutoprosposta);

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
        form.getChildren().addAll(lbErro,tipoHbox,buttonsHBox);
        form.setSpacing(5);
        return form;
    }
    private void update() {
    }

    private void registerHandlers() {
        rbProjeto.setOnAction(actionEvent -> {
            rbEstagio.setSelected(false);
            rbAutoprosposta.setSelected(false);
            this.getChildren().remove(form);
            projetoFields();
        });

        rbEstagio.setOnAction(actionEvent -> {
            rbProjeto.setSelected(false);
            rbAutoprosposta.setSelected(false);
            this.getChildren().remove(form);
            estagioFields();
        });

        rbAutoprosposta.setOnAction(actionEvent -> {
            rbProjeto.setSelected(false);
            rbEstagio.setSelected(false);
            this.getChildren().remove(form);
            autopropostaFields();
        });
    }
    private void projetoFields(){
        HBox buttonsHBox = new HBox();
        buttonsHBox.getChildren().addAll(btn1,btnVoltar);
        buttonsHBox.setAlignment(Pos.CENTER);
        buttonsHBox.setSpacing(40);
        buttonsHBox.setPadding(new Insets(10,0,0,0));

        HBox tipoHbox = new HBox();
        tipoHbox.setSpacing(10);
        tipoHbox.setAlignment(Pos.CENTER);
        tipoHbox.getChildren().addAll(rbProjeto,rbEstagio,rbAutoprosposta);

        HBox codigoHbox = new HBox();
        codigoHbox.setAlignment(Pos.CENTER);
        lbCodigo.setAlignment(Pos.CENTER_RIGHT);
        tfCodigo.setMinWidth(250);
        lbCodigo.setMinWidth(50);
        codigoHbox.getChildren().addAll(lbCodigo,tfCodigo);

        HBox ramosHbox = new HBox();
        ramosHbox.setSpacing(10);
        ramosHbox.setAlignment(Pos.CENTER);
        lbRamos.setMinWidth(50);
        ramosHbox.getChildren().addAll(lbRamos,rbDA,rbSI,rbRAS);

        HBox tituloHbox = new HBox();
        tituloHbox.setAlignment(Pos.CENTER);
        lbTitulo.setAlignment(Pos.CENTER_RIGHT);
        tfTitulo.setMinWidth(250);
        lbTitulo.setMinWidth(50);
        tituloHbox.getChildren().addAll(lbTitulo,tfTitulo);

        HBox nomeDocenteVbox = new HBox();
        cbNomeDocente.setMinWidth(240);
        List<String> lE = new ArrayList<>();
        lE.add("");
        for(Docente docente:d){
            lE.add(docente.getNome());
        }
        cbNomeDocente.setItems(FXCollections
                .observableArrayList(lE));
        cbNomeDocente.setUserData(d);
        lbDocente.setMinWidth(50);
        lbDocente.setAlignment(Pos.CENTER_RIGHT);
        nomeDocenteVbox.setAlignment(Pos.CENTER);
        nomeDocenteVbox.getChildren().addAll(lbDocente, cbNomeDocente);

        HBox numeroAluno = new HBox();
        cbNumeroAluno.setMinWidth(250);
        List<String> lA = new ArrayList<>();
        lA.add("");
        if(a!=null){
            for(Aluno aluno:a){
                lA.add(String.valueOf(aluno.getNumEstudante()));
            }
        }
        cbNumeroAluno.setItems(FXCollections
                .observableArrayList(lA));
        lbAluno.setMinWidth(50);
        lbAluno.setAlignment(Pos.CENTER_RIGHT);
        numeroAluno.setAlignment(Pos.CENTER);
        numeroAluno.getChildren().addAll(lbAluno, cbNumeroAluno);

        form.setMaxHeight(250);
        form.setMaxWidth(450);
        form.setStyle("-fx-background-color: #FFD23F;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
        form.getChildren().remove(0,form.getChildren().size());
        form.getChildren().addAll(lbErro,tipoHbox,codigoHbox,ramosHbox,tituloHbox,nomeDocenteVbox,numeroAluno,buttonsHBox);
        form.setSpacing(5);
        this.setCenter(form);
    }
    private void autopropostaFields(){
        HBox buttonsHBox = new HBox();
        buttonsHBox.getChildren().addAll(btn1,btnVoltar);
        buttonsHBox.setAlignment(Pos.CENTER);
        buttonsHBox.setSpacing(40);
        buttonsHBox.setPadding(new Insets(10,0,0,0));

        HBox tipoHbox = new HBox();
        tipoHbox.setSpacing(10);
        tipoHbox.setAlignment(Pos.CENTER);
        tipoHbox.getChildren().addAll(rbProjeto,rbEstagio,rbAutoprosposta);

        HBox codigoHbox = new HBox();
        codigoHbox.setAlignment(Pos.CENTER);
        lbCodigo.setAlignment(Pos.CENTER_RIGHT);
        tfCodigo.setMinWidth(250);
        lbCodigo.setMinWidth(50);
        codigoHbox.getChildren().addAll(lbCodigo,tfCodigo);

        HBox tituloHbox = new HBox();
        tituloHbox.setAlignment(Pos.CENTER);
        lbTitulo.setAlignment(Pos.CENTER_RIGHT);
        tfTitulo.setMinWidth(250);
        lbTitulo.setMinWidth(50);
        tituloHbox.getChildren().addAll(lbTitulo,tfTitulo);

        HBox numeroAluno = new HBox();
        cbNumeroAluno.setMinWidth(250);
        List<String> lE = new ArrayList<>();
        lE.add("");
        if(a!=null){
            for(Aluno aluno:a){
                lE.add(String.valueOf(aluno.getNumEstudante()));
            }
        }
        cbNumeroAluno.setItems(FXCollections
                .observableArrayList(lE));
        lbAluno.setMinWidth(50);
        lbAluno.setAlignment(Pos.CENTER_RIGHT);
        numeroAluno.setAlignment(Pos.CENTER);
        numeroAluno.getChildren().addAll(lbAluno, cbNumeroAluno);

        form.setMaxHeight(200);
        form.setMaxWidth(450);
        form.setStyle("-fx-background-color: #FFD23F;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
        form.getChildren().remove(0,form.getChildren().size());
        form.getChildren().addAll(lbErro,tipoHbox,codigoHbox,tituloHbox,numeroAluno,buttonsHBox);
        form.setSpacing(5);
        this.setCenter(form);
    }
    private void estagioFields(){
        HBox tipoHbox = new HBox();
        tipoHbox.setSpacing(10);
        tipoHbox.setAlignment(Pos.CENTER);
        tipoHbox.getChildren().addAll(rbProjeto,rbEstagio,rbAutoprosposta);

        HBox codigoHbox = new HBox();
        codigoHbox.setAlignment(Pos.CENTER);
        lbCodigo.setAlignment(Pos.CENTER_RIGHT);
        tfCodigo.setMinWidth(250);
        lbCodigo.setMinWidth(50);
        codigoHbox.getChildren().addAll(lbCodigo,tfCodigo);

        HBox ramosHbox = new HBox();
        ramosHbox.setSpacing(10);
        ramosHbox.setAlignment(Pos.CENTER);
        lbRamos.setMinWidth(50);
        ramosHbox.getChildren().addAll(lbRamos,rbDA,rbSI,rbRAS);

        HBox tituloHbox = new HBox();
        tituloHbox.setAlignment(Pos.CENTER);
        lbTitulo.setAlignment(Pos.CENTER_RIGHT);
        tfTitulo.setMinWidth(250);
        lbTitulo.setMinWidth(50);
        tituloHbox.getChildren().addAll(lbTitulo,tfTitulo);

        HBox empresaHbox = new HBox();
        empresaHbox.setAlignment(Pos.CENTER);
        lbEmpresa.setAlignment(Pos.CENTER_RIGHT);
        tfEmpresa.setMinWidth(185);
        lbEmpresa.setMinWidth(100);
        empresaHbox.getChildren().addAll(lbEmpresa,tfEmpresa);

        HBox buttonsHBox = new HBox();
        buttonsHBox.getChildren().addAll(btn1,btnVoltar);
        buttonsHBox.setAlignment(Pos.CENTER);
        buttonsHBox.setSpacing(40);
        buttonsHBox.setPadding(new Insets(10,0,0,0));

        form.setMaxHeight(250);
        form.setMaxWidth(450);
        form.setStyle("-fx-background-color: #FFD23F;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
        form.getChildren().remove(0,form.getChildren().size());
        form.getChildren().addAll(lbErro,tipoHbox,codigoHbox,ramosHbox,tituloHbox,empresaHbox,buttonsHBox);
        form.setSpacing(5);
        this.setCenter(form);
    }

}
