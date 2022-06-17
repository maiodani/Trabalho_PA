package pt.isec.pa.apoio_poe.ui.gui.Phase4UI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pt.isec.pa.apoio_poe.model.data.phase1.Aluno;
import pt.isec.pa.apoio_poe.model.data.phase1.Docente;
import pt.isec.pa.apoio_poe.model.data.phase1.Propostas;
import pt.isec.pa.apoio_poe.model.data.phase1.SiglaRamo;
import pt.isec.pa.apoio_poe.model.data.phase4.DadosDiversos;

import java.util.List;

public class Phase4ListarDadosDiversos extends VBox {
    TableView tableView;
    List<DadosDiversos> dd;
    public Button btnVoltar;
    public Label lbEstatisticas;
    public Phase4ListarDadosDiversos(List<DadosDiversos> dd){
        this.dd=dd;
        tableView = new TableView<>();
        lbEstatisticas = new Label();
        btnVoltar = new Button("VOLTAR");
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        tableView.setMinHeight(300);
        tableView.setMinWidth(500);
        tableView.setMaxHeight(300);
        tableView.setMaxWidth(500);
        TableColumn orientador = new TableColumn("DOCENTE");
        orientador.setMinWidth(250);
        orientador.setMaxWidth(250);
        TableColumn nOrientacoes = new TableColumn("Nº ORIENTAÇÕES");
        nOrientacoes.setMinWidth(250);
        nOrientacoes.setMaxWidth(250);
        if(dd!=null){
            ObservableList<DadosDiversos> data = FXCollections.observableArrayList(dd);
            orientador.setCellValueFactory(new PropertyValueFactory<DadosDiversos, Docente>("docente"));
            nOrientacoes.setCellValueFactory(new PropertyValueFactory<DadosDiversos, Integer>("n"));
            tableView.setItems(data);
        }

        tableView.getColumns().addAll(orientador,nOrientacoes);
        this.setSpacing(10);
        btnVoltar.setStyle("-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");

        this.setAlignment(Pos.CENTER);
        lbEstatisticas.setText(dd.get(0).getEstatisticas());
        lbEstatisticas.setStyle("-fx-background-color: #FFD23F;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;-fx-font-size: 15px");
        this.getChildren().addAll(tableView,lbEstatisticas,btnVoltar);
    }
    private void update() {
    }

    private void registerHandlers() {
    }


}
