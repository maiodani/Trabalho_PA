package pt.isec.pa.apoio_poe.ui.gui.Phase3UI.GestaoManualUI;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pt.isec.pa.apoio_poe.model.data.phase1.Aluno;
import pt.isec.pa.apoio_poe.model.data.phase1.Propostas;

import java.util.ArrayList;
import java.util.List;


public class GestaoManualAdicionar extends BorderPane {
    Label lbProposta,lbAluno;
    ComboBox<String> cbProposta,cbAluno;
    Button btn1,btnVoltar;
    List<Propostas> p;
    List<Aluno> a;
    public GestaoManualAdicionar(List<Propostas> p, List<Aluno> a){
        this.a=a;
        this.p=p;
        lbAluno = new Label("ALUNO");
        lbProposta = new Label("PROPOSTA");
        cbAluno = new ComboBox<>();
        cbProposta = new ComboBox<>();
        btn1 = new Button("PROCESSAR");
        btnVoltar = new Button("VOLTAR");
        createViews();
        registerHandlers();
        update();
    }

    private void update() {
    }

    private void registerHandlers() {
    }

    private void createViews() {
        VBox form = new VBox();
        form.setAlignment(Pos.CENTER);

        HBox propostaHbox = new HBox();
        propostaHbox.setSpacing(10);
        List<String> lC= new ArrayList<>();
        lC.add("");
        for(Propostas propostas:p){
            lC.add(propostas.getCodigoId());
        }
        cbProposta.setItems(FXCollections
                .observableArrayList(lC));
        cbProposta.setMinWidth(250);
        cbProposta.setStyle("-fx-background-color: white;-fx-border-color: grey;-fx-border-width: 0.5px;fx-border-radius: 15px;");
        lbProposta.setMinWidth(100);
        lbProposta.setAlignment(Pos.CENTER_RIGHT);
        propostaHbox.setAlignment(Pos.CENTER);
        propostaHbox.getChildren().addAll(lbProposta,cbProposta);

        HBox alunoHbox = new HBox();
        alunoHbox.setSpacing(10);
        List<String> lA= new ArrayList<>();
        lA.add("");
        for(Aluno aluno:a){
            lA.add(String.valueOf(aluno.getNumEstudante()));
        }
        cbAluno.setItems(FXCollections
                .observableArrayList(lA));
        cbAluno.setMinWidth(250);
        cbAluno.setStyle("-fx-background-color: white;-fx-border-color: grey;-fx-border-width: 0.5px;fx-border-radius: 15px;");
        lbAluno.setMinWidth(100);
        lbAluno.setAlignment(Pos.CENTER_RIGHT);
        alunoHbox.setAlignment(Pos.CENTER);
        alunoHbox.getChildren().addAll(lbAluno,cbAluno);

        HBox buttonsHBox = new HBox();
        buttonsHBox.getChildren().addAll(btn1,btnVoltar);
        buttonsHBox.setAlignment(Pos.CENTER);
        buttonsHBox.setSpacing(40);
        buttonsHBox.setPadding(new Insets(10,0,0,0));

        form.setMaxHeight(200);
        form.setMaxWidth(450);
        form.setStyle("-fx-background-color: #FFD23F;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
        form.getChildren().addAll(propostaHbox,alunoHbox,buttonsHBox);
        form.setSpacing(5);
        this.setCenter(form);
    }
}
