package pt.isec.pa.apoio_poe.ui.gui.Phase1UI.GestaoPropostasUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import pt.isec.pa.apoio_poe.model.data.phase1.Aluno;
import pt.isec.pa.apoio_poe.model.data.phase1.Docente;
import pt.isec.pa.apoio_poe.model.data.phase1.Propostas;
import pt.isec.pa.apoio_poe.model.data.phase1.SiglaRamo;

import java.util.List;

public class GestaoPropostasListarPropostas extends VBox {
    TableView tableView;
    List<Propostas> p;
    public Button btnVoltar;
    public GestaoPropostasListarPropostas(List<Propostas> p){
        this.p=p;
        tableView = new TableView<>();
        btnVoltar = new Button("VOLTAR");
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        //System.out.println(d);
        tableView.setMinHeight(400);
        tableView.setMinWidth(1020);
        tableView.setMaxHeight(400);
        tableView.setMaxWidth(1020);
        TableColumn ramo = new TableColumn("RAMO(S)");
        ramo.setMinWidth(80);
        ramo.setMaxWidth(80);
        TableColumn aluno = new TableColumn("ALUNO");
        aluno.setMinWidth(250);
        aluno.setMaxWidth(250);
        TableColumn codigo = new TableColumn("CODIGO");
        codigo.setMinWidth(80);
        codigo.setMaxWidth(80);
        TableColumn titulo = new TableColumn("TITULO");
        titulo.setMinWidth(250);
        titulo.setMaxWidth(250);
        TableColumn orientador = new TableColumn("ORIENTADOR");
        orientador.setMinWidth(250);
        orientador.setMaxWidth(250);
        TableColumn confirmado = new TableColumn("CONFIRMADO");
        confirmado.setMinWidth(100);
        confirmado.setMaxWidth(100);
        if(p!=null){
            ObservableList<Propostas> data = FXCollections.observableArrayList(p);
            codigo.setCellValueFactory(new PropertyValueFactory<Propostas,String>("codigoId"));
            titulo.setCellValueFactory(new PropertyValueFactory<Propostas,String>("titulo"));
            ramo.setCellValueFactory(new PropertyValueFactory<Propostas,List<SiglaRamo>>("ramo"));
            aluno.setCellValueFactory(new PropertyValueFactory<Propostas, Aluno>("aluno"));
            orientador.setCellValueFactory(new PropertyValueFactory<Propostas, Docente>("orientador"));
            confirmado.setCellValueFactory(new PropertyValueFactory<Propostas, Boolean>("atribuida"));
            tableView.setItems(data);
        }

        tableView.getColumns().addAll(codigo,titulo,ramo,aluno,orientador,confirmado);

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
