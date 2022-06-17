package pt.isec.pa.apoio_poe.ui.gui.Phase5UI.DadosGraficosUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class DadosGraficoPorRamo extends HBox {

    public Button btnVoltar;
    public DadosGraficoPorRamo(List<Integer> i){
        btnVoltar = new Button("VOLTAR");
        btnVoltar.setStyle("-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
        PieChart pieChart = new PieChart();
        this.setMaxHeight(350);
        this.setMaxWidth(400);
        this.setMinHeight(350);
        this.setMinWidth(400);
        this.setStyle("-fx-background-color: #FFD23F;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("RAS", i.get(2)),
                        new PieChart.Data("SI", i.get(1)),
                        new PieChart.Data("DA", i.get(0)));
        final PieChart chart = new PieChart(pieChartData);
        pieChart.setData(pieChartData);
        pieChart.setTitle("DISTRIBUIÇÃO POR RAMO");
        VBox vBox = new VBox(pieChart,btnVoltar);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        this.getChildren().addAll(vBox);
    }
}
