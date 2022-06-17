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
import pt.isec.pa.apoio_poe.model.data.phase1.Propostas;
import pt.isec.pa.apoio_poe.model.data.phase1.SiglaRamo;
import pt.isec.pa.apoio_poe.model.data.phase1.propostas.EstProjAutoproposto;
import pt.isec.pa.apoio_poe.model.data.phase1.propostas.Estagio;
import pt.isec.pa.apoio_poe.model.data.phase1.propostas.Projeto;

import java.util.ArrayList;
import java.util.List;

public class GestaoPropostasApagar extends BorderPane {
    Label lbEstagio,lbAutoproposta,lbProjeto,lbCodigo,lbRamos,lbTitulo,lbEmpresa,lbDocente,lbAluno;
    public Label lbErro;
    TextField tfTitulo,tfEmpresa;
    public RadioButton rbEstagio,rbAutoprosposta,rbProjeto,rbRAS,rbDA,rbSI;
    public Button btn1,btnVoltar;

    ComboBox<String> cbNomeDocente,cbNumeroAluno,cbCodigo;

    List<Docente> d;
    List<Aluno> a;

    List<Propostas> p;
    VBox form;
    public GestaoPropostasApagar(List<Docente> d, List<Aluno> a, List<Propostas> p){
        this.a=a;
        this.d=d;
        this.p=p;
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
        cbCodigo = new ComboBox<>();
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
        setAllDisable(true);
        return form;
    }
    private void update() {
    }

    private void registerHandlers() {
        rbProjeto.setOnAction(actionEvent -> {
            clearAll();
            rbEstagio.setSelected(false);
            rbAutoprosposta.setSelected(false);
            this.getChildren().remove(form);
            projetoFields();
            setAllDisable(true);
        });

        rbEstagio.setOnAction(actionEvent -> {
            clearAll();
            rbProjeto.setSelected(false);
            rbAutoprosposta.setSelected(false);
            this.getChildren().remove(form);
            estagioFields();
            setAllDisable(true);
        });

        rbAutoprosposta.setOnAction(actionEvent -> {
            clearAll();
            rbProjeto.setSelected(false);
            rbEstagio.setSelected(false);
            this.getChildren().remove(form);
            autopropostaFields();
            setAllDisable(true);
        });

        cbCodigo.setOnAction(actionEvent -> {
            clearAll();
            String num = cbCodigo.getSelectionModel().getSelectedItem();
            Propostas aux =null;
            for(Propostas pro:p){
                if(pro.getCodigoId().equalsIgnoreCase(num))aux=pro;
            }
            if(aux instanceof Projeto){
                Projeto projeto = (Projeto) aux;
                if(!projeto.getRamo().isEmpty()){
                    if(projeto.getRamo().contains(SiglaRamo.DA)){
                        rbDA.setSelected(true);
                    }
                    if(projeto.getRamo().contains(SiglaRamo.RAS)){
                        rbRAS.setSelected(true);
                    }
                    if(projeto.getRamo().contains(SiglaRamo.SI)){
                        rbSI.setSelected(true);
                    }
                }
                tfTitulo.setText(projeto.getTitulo());
                if(projeto.getOrientador()!=null){
                    cbNomeDocente.getSelectionModel().select(projeto.getOrientador().getNome());
                }
                if(projeto.getAluno()!=null){
                    cbNumeroAluno.getSelectionModel().select(String.valueOf(projeto.getAluno().getNumEstudante()));
                }
            }
            if(aux instanceof Estagio){
                Estagio estagio = (Estagio) aux;
                if(!estagio.getRamo().isEmpty()){
                    if(estagio.getRamo().contains(SiglaRamo.DA)){
                        rbDA.setSelected(true);
                    }
                    if(estagio.getRamo().contains(SiglaRamo.RAS)){
                        rbRAS.setSelected(true);
                    }
                    if(estagio.getRamo().contains(SiglaRamo.SI)){
                        rbSI.setSelected(true);
                    }
                }
                tfTitulo.setText(estagio.getTitulo());
                tfEmpresa.setText(estagio.getEmpresa());
            }
            if(aux instanceof EstProjAutoproposto){
                EstProjAutoproposto auto = (EstProjAutoproposto) aux;
                tfTitulo.setText(auto.getTitulo());
                cbNumeroAluno.getSelectionModel().select(String.valueOf(auto.getAluno().getNumEstudante()));
            }
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
        List<String> lID = new ArrayList<>();
        lID.add("");
        for(Propostas pro:p){
            if(pro instanceof Projeto) lID.add(pro.getCodigoId());
        }
        cbCodigo.setItems(FXCollections
                .observableArrayList(lID));
        cbCodigo.setUserData(d);
        cbCodigo.setMinWidth(250);
        lbCodigo.setMinWidth(50);
        codigoHbox.getChildren().addAll(lbCodigo,cbCodigo);

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
        List<String> lID = new ArrayList<>();
        lID.add("");
        for(Propostas pro:p){
            if(pro instanceof EstProjAutoproposto) lID.add(pro.getCodigoId());
        }
        cbCodigo.setItems(FXCollections
                .observableArrayList(lID));
        cbCodigo.setUserData(d);
        cbCodigo.setMinWidth(250);
        lbCodigo.setMinWidth(50);
        codigoHbox.getChildren().addAll(lbCodigo,cbCodigo);

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
        List<String> lID = new ArrayList<>();
        lID.add("");
        for(Propostas pro:p){
            if(pro instanceof Estagio) lID.add(pro.getCodigoId());
        }
        cbCodigo.setItems(FXCollections
                .observableArrayList(lID));
        cbCodigo.setUserData(d);
        cbCodigo.setMinWidth(250);
        lbCodigo.setMinWidth(50);
        codigoHbox.getChildren().addAll(lbCodigo,cbCodigo);

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

    private void setAllDisable(boolean b){
        rbSI.setDisable(b);
        rbRAS.setDisable(b);
        rbDA.setDisable(b);
        tfTitulo.setDisable(b);
        cbNomeDocente.setDisable(b);
        cbNumeroAluno.setDisable(b);
        tfEmpresa.setDisable(b);
    }

    private void clearAll(){
        rbDA.setSelected(false);
        rbSI.setSelected(false);
        rbRAS.setSelected(false);
        tfTitulo.setText("");
        cbNomeDocente.getSelectionModel().clearSelection();
        cbNumeroAluno.getSelectionModel().clearSelection();
        tfEmpresa.setText("");

    }

}