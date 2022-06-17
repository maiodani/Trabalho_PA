package pt.isec.pa.apoio_poe.ui.gui.Phase1UI.GestaoDocenteUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import pt.isec.pa.apoio_poe.model.data.phase1.Docente;

import java.util.List;

public class GestaoDocenteListar extends VBox {
    TableView tableView;
    List<Docente> d;
    public Button btnVoltar;
    public GestaoDocenteListar(List<Docente> d){
        this.d=d;
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
        TableColumn nome = new TableColumn("NOME");
        nome.setMinWidth(250);
        nome.setMaxWidth(250);
        TableColumn email = new TableColumn("EMAIL");
        email.setMinWidth(250);
        email.setMaxWidth(250);

        if(d!=null){
            ObservableList<Docente> data = FXCollections.observableArrayList(d);
            nome.setCellValueFactory(new PropertyValueFactory<Docente,String>("nome"));
            email.setCellValueFactory(new PropertyValueFactory<Docente,String>("email"));
            tableView.setItems(data);
        }

        tableView.getColumns().addAll(nome,email);

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
