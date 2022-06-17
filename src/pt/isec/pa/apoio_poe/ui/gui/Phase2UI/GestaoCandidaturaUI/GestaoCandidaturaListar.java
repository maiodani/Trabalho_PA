package pt.isec.pa.apoio_poe.ui.gui.Phase2UI.GestaoCandidaturaUI;

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
import pt.isec.pa.apoio_poe.model.data.phase2.Candidatura;

import java.util.List;

public class GestaoCandidaturaListar extends VBox {
    TableView tableView;
    List<Candidatura> c;
    public Button btnVoltar;
    public GestaoCandidaturaListar(List<Candidatura> c){
        this.c=c;
        tableView = new TableView<>();
        btnVoltar = new Button("VOLTAR");
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        tableView.setMinHeight(400);
        tableView.setMinWidth(500);
        tableView.setMaxHeight(400);
        tableView.setMaxWidth(500);
        TableColumn aluno = new TableColumn("ALUNO");
        aluno.setMinWidth(250);
        aluno.setMaxWidth(250);
        TableColumn codigos = new TableColumn("CODIGOS");
        codigos.setMinWidth(250);
        codigos.setMaxWidth(250);
        if(c!=null){
            ObservableList<Candidatura> data = FXCollections.observableArrayList(c);
            aluno.setCellValueFactory(new PropertyValueFactory<Candidatura,Aluno>("aluno"));
            codigos.setCellValueFactory(new PropertyValueFactory<Candidatura,List<String>>("codigos"));
            tableView.setItems(data);
        }

        tableView.getColumns().addAll(aluno,codigos);

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
