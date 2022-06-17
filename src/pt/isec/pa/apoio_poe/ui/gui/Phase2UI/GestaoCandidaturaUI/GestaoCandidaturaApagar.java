package pt.isec.pa.apoio_poe.ui.gui.Phase2UI.GestaoCandidaturaUI;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pt.isec.pa.apoio_poe.model.data.phase1.Aluno;
import pt.isec.pa.apoio_poe.model.data.phase1.Propostas;
import pt.isec.pa.apoio_poe.model.data.phase1.SiglaRamo;
import pt.isec.pa.apoio_poe.model.data.phase2.Candidatura;

import java.util.ArrayList;
import java.util.List;


public class GestaoCandidaturaApagar extends BorderPane {
    Label lbNumEstudante,lbPropostas;
    MenuButton menuButton;
    public ComboBox<String> cbNumEstudante;
    public Button btnVoltar,btnProcessar;
    List<Aluno> a;
    List<Propostas> p;
    List<Candidatura> c;
    List<Long> num;
    public List<String> selected;
    public GestaoCandidaturaApagar(List<Aluno> a, List<Propostas> p, List<Candidatura> c){
        this.a=a;
        this.p=p;
        this.c=c;
        num = new ArrayList<>();
        selected = new ArrayList<>();
        menuButton = new MenuButton();
        cbNumEstudante = new ComboBox<>();
        lbPropostas = new Label("CANDIDATURAS");
        lbNumEstudante = new Label("NUMERO ESTUDANTE");
        btnProcessar = new Button("PROCESSAR");
        btnVoltar = new Button("VOLTAR");
        createViews();
        registerHandlers();
        update();
    }

    private void update() {
        cbNumEstudante.setOnAction(actionEvent -> {
            menuButton.getItems().remove(0,menuButton.getItems().size());
            selected.clear();
            Long nEstu;
            if(cbNumEstudante.getSelectionModel().getSelectedIndex()>=1){
                nEstu = Long.parseLong(cbNumEstudante.getSelectionModel().getSelectedItem());
            }else{
                return;
            }
            SiglaRamo sr = null;
            for(Aluno aluno:a){
                if(aluno.getNumEstudante()==nEstu){
                    sr=aluno.getSiglaRamo();
                }
            }
            List<String> aux = new ArrayList<>();
            for(Candidatura candidatura:c){
                if(candidatura.getAluno().getNumEstudante()==nEstu) aux=candidatura.getCodigos();
            }

            for(Propostas propostas:p){
                if(propostas.getRamo().contains(sr)){
                    CheckBox cb0 = new CheckBox(propostas.getCodigoId());
                    CustomMenuItem item0 = new CustomMenuItem(cb0);
                    if(aux.contains(propostas.getCodigoId())){
                        cb0.setSelected(true);
                        selected.add(propostas.getCodigoId());
                        cb0.setDisable(true);
                    }
                    menuButton.getItems().addAll(item0);
                    menuButton.setDisable(false);
                }
            }
        });
    }

    private void registerHandlers() {

    }

    private void createViews() {
        this.setMinWidth(400);
        this.setMinHeight(200);
        this.setStyle("-fx-background-color: #3BCEAC");
        VBox form = new VBox();
        form.setAlignment(Pos.CENTER);
        HBox numeroHbox = new HBox();
        numeroHbox.setSpacing(10);
        List<String> lN = new ArrayList<>();
        lN.add("");
        for(Candidatura candidatura:c){
            lN.add(String.valueOf(candidatura.getAluno().getNumEstudante()));
        }
        cbNumEstudante.setItems(FXCollections
                .observableArrayList(lN));
        cbNumEstudante.setMinWidth(250);
        cbNumEstudante.setStyle("-fx-background-color: white;-fx-border-color: grey;-fx-border-width: 0.5px;fx-border-radius: 15px;");;
        lbNumEstudante.setMinWidth(100);
        lbNumEstudante.setAlignment(Pos.CENTER_RIGHT);
        numeroHbox.setAlignment(Pos.CENTER);
        numeroHbox.getChildren().addAll(lbNumEstudante, cbNumEstudante);

        menuButton.setDisable(true);

        HBox menuButtonHbox = new HBox();
        menuButtonHbox.setSpacing(30);
        lbPropostas.setMinWidth(100);
        lbPropostas.setAlignment(Pos.CENTER_RIGHT);
        menuButton.setMinWidth(250);
        menuButton.setStyle("-fx-background-color: white;-fx-border-color: grey;-fx-border-width: 0.5px;fx-border-radius: 15px;");
        menuButtonHbox.setAlignment(Pos.CENTER);
        menuButtonHbox.getChildren().addAll(lbPropostas, menuButton);

        btnProcessar.setStyle("-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
        btnVoltar.setStyle("-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");

        HBox buttonsHBox = new HBox();
        buttonsHBox.getChildren().addAll(btnProcessar, btnVoltar);
        buttonsHBox.setAlignment(Pos.CENTER);
        buttonsHBox.setSpacing(40);
        buttonsHBox.setPadding(new Insets(10, 0, 0, 0));

        form.setMaxHeight(200);
        form.setMaxWidth(450);
        form.setStyle("-fx-background-color: #FFD23F;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
        form.getChildren().addAll(numeroHbox,menuButtonHbox,buttonsHBox);
        form.setSpacing(5);
        this.setCenter(form);
        /*

        CheckBox cb1 = new CheckBox("y");
        CustomMenuItem item1 = new CustomMenuItem(cb1);
        menuButton.getItems().setAll(item0,item1);
        this.setCenter(menuButton);
        */
    }
}
