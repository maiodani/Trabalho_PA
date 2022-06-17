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
import pt.isec.pa.apoio_poe.model.data.phase1.propostas.EstProjAutoproposto;
import pt.isec.pa.apoio_poe.model.data.phase1.propostas.Projeto;

import java.util.ArrayList;
import java.util.List;

public class GestaoPropostasListarAutoproposta extends VBox {
    TableView tableView;
    List<Propostas> p;
    List<EstProjAutoproposto> auto;
    public Button btnVoltar;
    public GestaoPropostasListarAutoproposta(List<Propostas> p){
        this.p=p;
        auto = new ArrayList<>();
        for(Propostas prop:p){
            if(prop instanceof EstProjAutoproposto){
                auto.add((EstProjAutoproposto) prop);
            }
        }
        tableView = new TableView<>();
        btnVoltar = new Button("VOLTAR");
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        //System.out.println(d);
        tableView.setMinHeight(400);
        tableView.setMinWidth(1150);
        tableView.setMaxHeight(400);
        tableView.setMaxWidth(1150);
        TableColumn codigo = new TableColumn("CODIGO");
        codigo.setMinWidth(100);
        codigo.setMaxWidth(100);
        TableColumn ramo = new TableColumn("RAMO(S)");
        ramo.setMinWidth(100);
        ramo.setMaxWidth(100);
        TableColumn aluno = new TableColumn("ALUNO");
        aluno.setMinWidth(250);
        aluno.setMaxWidth(250);
        TableColumn titulo = new TableColumn("TITULO");
        titulo.setMinWidth(250);
        titulo.setMaxWidth(250);
        TableColumn orientador = new TableColumn("ORIENTADOR");
        orientador.setMinWidth(250);
        orientador.setMaxWidth(250);
        if(p!=null){
            ObservableList<Propostas> data = FXCollections.observableArrayList(auto);
            codigo.setCellValueFactory(new PropertyValueFactory<Projeto,String>("codigoId"));
            titulo.setCellValueFactory(new PropertyValueFactory<Projeto,String>("titulo"));
            ramo.setCellValueFactory(new PropertyValueFactory<Projeto,List<SiglaRamo>>("ramo"));
            aluno.setCellValueFactory(new PropertyValueFactory<Projeto, Aluno>("aluno"));
            orientador.setCellValueFactory(new PropertyValueFactory<Projeto, Docente>("orientador"));
            tableView.setItems(data);
        }

        tableView.getColumns().addAll(codigo,titulo,ramo,aluno,orientador);

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
