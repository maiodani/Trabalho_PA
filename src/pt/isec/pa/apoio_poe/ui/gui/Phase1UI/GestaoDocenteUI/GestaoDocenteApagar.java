package pt.isec.pa.apoio_poe.ui.gui.Phase1UI.GestaoDocenteUI;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pt.isec.pa.apoio_poe.model.data.phase1.Docente;

import java.util.ArrayList;
import java.util.List;

public class GestaoDocenteApagar extends BorderPane {
    Label lbNome, lbEmail;
    public Label lbErro;
    public TextField  tfNome;

    public Button btn1,btnVoltar;
    public ComboBox<String> cbEmail;
    List<Docente> d;

    public GestaoDocenteApagar(List<Docente> d) {
        this.d=d;
        lbEmail = new Label("EMAIL: ");
        lbNome = new Label("NOME: ");
        lbErro = new Label("");
        tfNome = new TextField();
        btn1 = new Button("APAGAR");
        btnVoltar = new Button("VOLTAR");
        cbEmail = new ComboBox<>();
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        VBox form = new VBox();
        form.setAlignment(Pos.CENTER);
        HBox emailHbox = new HBox();

        cbEmail.setMinWidth(250);
        List<String> lE = new ArrayList<>();
        lE.add("");
        for(Docente docente:d){
            lE.add(docente.getEmail());
        }
        cbEmail.setItems(FXCollections
                .observableArrayList(lE));
        lbEmail.setMinWidth(100);
        lbEmail.setAlignment(Pos.CENTER_RIGHT);
        emailHbox.setAlignment(Pos.CENTER);
        emailHbox.getChildren().addAll(lbEmail, cbEmail);

        HBox nomeHbox = new HBox();
        tfNome.setMinWidth(250);
        lbNome.setMinWidth(100);
        lbNome.setAlignment(Pos.CENTER_RIGHT);
        nomeHbox.setAlignment(Pos.CENTER);
        nomeHbox.getChildren().addAll(lbNome, tfNome);

        HBox buttonsHBox = new HBox();
        buttonsHBox.getChildren().addAll(btn1, btnVoltar);
        buttonsHBox.setAlignment(Pos.CENTER);
        buttonsHBox.setSpacing(40);
        buttonsHBox.setPadding(new Insets(10, 0, 0, 0));

        lbErro.setStyle("-fx-font-weight: bold");
        form.setMaxHeight(300);
        form.setMaxWidth(450);
        form.setStyle("-fx-background-color: #FFD23F;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
        form.getChildren().addAll(lbErro, emailHbox,nomeHbox, buttonsHBox);
        form.setSpacing(5);
        btn1.setDisable(true);
        tfNome.setDisable(true);
        this.setCenter(form);
    }

    private void update() {
    }

    private void registerHandlers() {
        cbEmail.setOnAction(actionEvent -> {
            btn1.setDisable(false);
            if(!(cbEmail.getSelectionModel().getSelectedIndex()==0)){
                for(Docente docente:d){
                    if(cbEmail.getSelectionModel().getSelectedItem()==docente.getEmail()){
                        tfNome.setText(String.valueOf(docente.getNome()));
                    }
                }
            }else{
                tfNome.setText("");
                cbEmail.getSelectionModel().clearSelection();
            }
        });
    }
}
