package pt.isec.pa.apoio_poe.ui.gui.Phase1UI.GestaoAlunoUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import pt.isec.pa.apoio_poe.model.data.phase1.Aluno;
import pt.isec.pa.apoio_poe.model.data.phase1.SiglaCurso;
import pt.isec.pa.apoio_poe.model.data.phase1.SiglaRamo;

import java.util.List;

public class GestaoAlunoListar extends VBox {
    TableView tableView;
    List<Aluno> a;
    public Button btnVoltar;
    public  GestaoAlunoListar(List<Aluno> a){
        this.a=a;
        tableView = new TableView<>();
        btnVoltar = new Button("VOLTAR");
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        tableView.setMinHeight(400);
        tableView.setMinWidth(1000);
        tableView.setMaxHeight(400);
        tableView.setMaxWidth(700);
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
        if(a!=null){
            ObservableList<Aluno> data = FXCollections.observableArrayList(a);
            nEstudante.setCellValueFactory(new PropertyValueFactory<Aluno,Long>("numEstudante"));
            nome.setCellValueFactory(new PropertyValueFactory<Aluno,String>("nome"));
            email.setCellValueFactory(new PropertyValueFactory<Aluno,String>("email"));
            siglaCurso.setCellValueFactory(new PropertyValueFactory<Aluno,SiglaCurso>("siglaCurso"));
            siglaRamo.setCellValueFactory(new PropertyValueFactory<Aluno,SiglaRamo>("siglaRamo"));
            classificacao.setCellValueFactory(new PropertyValueFactory<Aluno,Double>("classificacao"));
            podeAceder.setCellValueFactory(new PropertyValueFactory<Aluno,Boolean>("podeAceder"));
            tableView.setItems(data);
        }

        tableView.getColumns().addAll(nEstudante,nome,email,siglaCurso,siglaRamo,classificacao,podeAceder);

        this.setSpacing(10);
        btnVoltar.setStyle("-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");

        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(tableView,btnVoltar);
    }
    private void update() {
    }

    private void registerHandlers() {
    }


}
