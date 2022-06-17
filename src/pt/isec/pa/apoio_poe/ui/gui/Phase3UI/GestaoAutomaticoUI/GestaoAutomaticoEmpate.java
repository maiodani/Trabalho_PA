package pt.isec.pa.apoio_poe.ui.gui.Phase3UI.GestaoAutomaticoUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pt.isec.pa.apoio_poe.model.PhaseManager;
import pt.isec.pa.apoio_poe.model.data.phase1.*;

import javafx.scene.control.TableView;
import pt.isec.pa.apoio_poe.model.fsm.PhaseState;

import java.util.List;

public class GestaoAutomaticoEmpate extends VBox {
    PhaseManager phaseManager;
    TableView tvAluno,tvProposta;
    Button btnAluno1,btnAluno2;

    public  GestaoAutomaticoEmpate(PhaseManager phaseManager){
        this.phaseManager=phaseManager;
        tvAluno = new TableView<>();
        tvProposta = new TableView<>();
        btnAluno1 = new Button();
        btnAluno2 = new Button();
        createViews();
        registerHandlers();
        update();
    }
    private void createViews() {
        this.setStyle("-fx-background-color: #3BCEAC");
        phaseManager.addPropertyChangeListener(evt -> {update();});
    }


    private void update() {
        if (phaseManager.getState() != PhaseState.EMPATE) {
            this.setVisible(false);
            return;
        }
        preencheDados();
        this.setVisible(true);
    }

    private void preencheDados() {
        List<Aluno> alunos = phaseManager.getEmpateAlunos();
        tvAluno.setMinHeight(200);
        tvAluno.setMinWidth(1000);
        tvAluno.setMaxHeight(200);
        tvAluno.setMaxWidth(700);
        TableColumn nEstudante = new TableColumn("Nº ESTUDANTE");
        nEstudante.setMinWidth(100);
        nEstudante.setMaxWidth(100);
        TableColumn nome = new TableColumn("NOME");
        nome.setMinWidth(250);
        nome.setMaxWidth(250);
        TableColumn email = new TableColumn("EMAIL");
        email.setMinWidth(200);
        email.setMaxWidth(200);
        TableColumn siglaCurso = new TableColumn("CURSO");
        siglaCurso.setMinWidth(100);
        siglaCurso.setMaxWidth(100);
        TableColumn siglaRamo = new TableColumn("RAMO");
        siglaRamo.setMinWidth(100);
        siglaRamo.setMaxWidth(100);
        TableColumn classificacao = new TableColumn("CLASSIFICAÇÃO");
        classificacao.setMinWidth(150);
        classificacao.setMaxWidth(150);
        TableColumn podeAceder = new TableColumn("PODE ACEDER");
        podeAceder.setMinWidth(100);
        podeAceder.setMaxWidth(100);
        ObservableList<Aluno> data = FXCollections.observableArrayList(alunos);
        nEstudante.setCellValueFactory(new PropertyValueFactory<Aluno, Long>("numEstudante"));
        nome.setCellValueFactory(new PropertyValueFactory<Aluno, String>("nome"));
        email.setCellValueFactory(new PropertyValueFactory<Aluno, String>("email"));
        siglaCurso.setCellValueFactory(new PropertyValueFactory<Aluno, SiglaCurso>("siglaCurso"));
        siglaRamo.setCellValueFactory(new PropertyValueFactory<Aluno, SiglaRamo>("siglaRamo"));
        classificacao.setCellValueFactory(new PropertyValueFactory<Aluno, Double>("classificacao"));
        podeAceder.setCellValueFactory(new PropertyValueFactory<Aluno, Boolean>("podeAceder"));
        tvAluno.setItems(data);
        tvAluno.getColumns().addAll(nEstudante,nome,email,siglaCurso,siglaRamo,classificacao,podeAceder);


        tvProposta.setMinHeight(100);
        tvProposta.setMinWidth(450);
        tvProposta.setMaxHeight(100);
        tvProposta.setMaxWidth(450);
        TableColumn ramo = new TableColumn("RAMO(S)");
        ramo.setMinWidth(100);
        ramo.setMaxWidth(100);
        TableColumn codigo = new TableColumn("CODIGO");
        codigo.setMinWidth(100);
        codigo.setMaxWidth(100);
        TableColumn titulo = new TableColumn("TITULO");
        titulo.setMinWidth(250);
        titulo.setMaxWidth(250);
        ObservableList<Propostas> data1 = FXCollections.observableArrayList(phaseManager.getEmpateProposta());
        codigo.setCellValueFactory(new PropertyValueFactory<Propostas,String>("codigoId"));
        titulo.setCellValueFactory(new PropertyValueFactory<Propostas,String>("titulo"));
        ramo.setCellValueFactory(new PropertyValueFactory<Propostas,List<SiglaRamo>>("ramo"));
        tvProposta.setItems(data1);

        tvProposta.getColumns().addAll(codigo,titulo,ramo);

        HBox buttonsHBox = new HBox();
        btnAluno1.setText(String.valueOf(alunos.get(0).getNumEstudante()));
        btnAluno1.setUserData(alunos.get(0).getNumEstudante());
        btnAluno2.setText(String.valueOf(alunos.get(1).getNumEstudante()));
        btnAluno2.setUserData(alunos.get(1).getNumEstudante());
        buttonsHBox.getChildren().addAll(btnAluno1, btnAluno2);
        buttonsHBox.setAlignment(Pos.CENTER);
        buttonsHBox.setSpacing(40);
        buttonsHBox.setPadding(new Insets(10, 0, 0, 0));

        this.setSpacing(10);
        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(tvProposta,tvAluno,buttonsHBox);
    }

    private void registerHandlers() {
        btnAluno1.setOnAction(actionEvent -> {
            Long aux =(Long) btnAluno1.getUserData();
            phaseManager.empate=true;
            phaseManager.insert(aux.intValue());
        });
        btnAluno2.setOnAction(actionEvent -> {
            Long aux =(Long) btnAluno2.getUserData();
            phaseManager.empate=true;
            phaseManager.insert(aux.intValue());
        });
    }


}
