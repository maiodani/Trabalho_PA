package pt.isec.pa.apoio_poe.ui.gui.Phase1UI.GestaoDocenteUI;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import pt.isec.pa.apoio_poe.model.PhaseManager;
import pt.isec.pa.apoio_poe.model.data.phase1.Docente;
import pt.isec.pa.apoio_poe.model.fsm.PhaseState;

public class GestaoDocenteMenu extends BorderPane {

    PhaseManager phaseManager;
    GestaoDocenteMensagens gdm;
    GestaoDocenteInserirPorFicheiro gdipf;
    GestaoDocenteInserirNovo gdin;
    Button btnInserir,btnConsulta,btnEditar,btnEliminar,btnExportar,btnVoltar;

    public GestaoDocenteMenu(PhaseManager phaseManager){
        this.phaseManager=phaseManager;
        btnConsulta = new Button();
        btnEditar = new Button();
        btnEliminar = new Button();
        btnExportar = new Button();
        btnInserir = new Button();
        btnVoltar = new Button();
        createViews();
        registerHandlers();
        update();
    }
    private void createViews() {
        this.setMinWidth(1200);
        this.setMinHeight(600);
        this.setStyle("-fx-background-color: #3BCEAC");
        btnConsulta.setText("CONSULTA");
        btnConsulta.setMinHeight(50);
        btnConsulta.setMinWidth(200);
        btnConsulta.setMaxHeight(200);
        btnConsulta.setStyle("-fx-font-size: 15px;-fx-font-weight: bold;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
        btnEditar.setText("EDITAR");
        btnEditar.setMinHeight(50);
        btnEditar.setMinWidth(200);
        btnEditar.setMaxHeight(200);
        btnEditar.setStyle("-fx-font-size: 15px;-fx-font-weight: bold;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
        btnEliminar.setText("ELIMINAR");
        btnEliminar.setMinHeight(50);
        btnEliminar.setMinWidth(200);
        btnEliminar.setMaxHeight(200);
        btnEliminar.setStyle("-fx-font-size: 15px;-fx-font-weight: bold;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
        btnExportar.setText("EXPORTAR");
        btnExportar.setMinHeight(50);
        btnExportar.setMinWidth(200);
        btnExportar.setMaxHeight(200);
        btnExportar.setStyle("-fx-font-size: 15px;-fx-font-weight: bold;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
        btnInserir.setText("INSERIR");
        btnInserir.setMinHeight(50);
        btnInserir.setMinWidth(200);
        btnInserir.setMaxHeight(200);
        btnInserir.setStyle("-fx-font-size: 15px;-fx-font-weight: bold;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
        btnVoltar.setText("VOLTAR");
        btnVoltar.setMinHeight(50);
        btnVoltar.setMinWidth(200);
        btnVoltar.setMaxHeight(200);
        btnVoltar.setStyle("-fx-font-size: 15px;-fx-font-weight: bold;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
        /*this.setAlignment(Pos.CENTER);
        this.setSpacing(15);
        this.getChildren().addAll(btnInserir,btnConsulta,btnEditar,btnEliminar,btnExportar,btnVoltar);
        */
        HBox butoes = new HBox();
        butoes.setMinWidth(800);
        //butoes.setStyle("-fx-background-color: white");
        butoes.getChildren().addAll(btnInserir,btnConsulta,btnEditar,btnEliminar,btnExportar,btnVoltar);
        this.setTop(butoes);
    }
    private void update() {
        if(phaseManager.getFechado()!=0){
            btnEditar.setDisable(true);
            btnEliminar.setDisable(true);
            btnInserir.setDisable(true);
            btnExportar.setDisable(true);
        }
        if (phaseManager.getState() != PhaseState.GEST_PROFESSOR) {
            this.setVisible(false);
            return;
        }
        this.setVisible(true);
    }

    private void registerHandlers() {
        //gam.setVisible(false);
        phaseManager.addPropertyChangeListener(evt -> {
            update();
        });
        btnVoltar.setOnAction(actionEvent -> {
            phaseManager.voltar();
        });
        btnInserir.setOnAction(actionEvent -> {
            gdm = new GestaoDocenteMensagens("TIPO DE INSERÇÃO","POR FICHEIRO","NOVO DOCENTE");
            this.setCenter(gdm);
            gdm.btn1.setOnAction(actionEvent1 -> {
                gdipf = new GestaoDocenteInserirPorFicheiro();
                changeButtonsDisable(true);
                this.getChildren().remove(gdm);
                this.setCenter(gdipf);
                gdipf.btn1.setOnAction(actionEvent2 -> {
                    phaseManager.insert(gdipf.nomeFicheiro.getText());
                    changeButtonsDisable(false);
                    this.getChildren().remove(gdipf);
                });
                gdipf.btnVoltar.setOnAction(actionEvent2 -> {
                    changeButtonsDisable(false);
                    this.getChildren().remove(gdipf);
                });
            });
            gdm.btn2.setOnAction(actionEvent1 -> {
                gdin = new GestaoDocenteInserirNovo();
                this.setCenter(gdin);
                gdin.btn1.setOnAction(actionEvent2 -> {
                    if(checkFieldsInsert(gdin)){
                        Docente d = new Docente(
                                gdin.tfNome.getText(),
                                gdin.tfEmail.getText()
                        );
                        String s = phaseManager.insertDocente(d);
                        this.getChildren().remove(gdin);
                    }else{
                        gdin.lbErro.setText("TODOS OS CAMPOS DEVEM SER PREENCHIDOS");
                    }
                    changeButtonsDisable(false);
                });
                changeButtonsDisable(true);

                gdin.btnVoltar.setOnAction(actionEvent2 -> {
                    this.getChildren().remove(gdin);
                    changeButtonsDisable(false);
                });
                /*
                gdin.setVisible(false);
                this.getChildren().remove(gdin);*/

            });
        });
        btnConsulta.setOnAction(actionEvent -> {
            GestaoDocenteListar gal = new GestaoDocenteListar(phaseManager.queryDocente());
            this.setCenter(gal);
            changeButtonsDisable(true);
            gal.btnVoltar.setOnAction(actionEvent1 -> {
                this.getChildren().remove(gal);
                if(phaseManager.getFechado()==0) {
                    changeButtonsDisable(false);
                }else{
                    btnConsulta.setDisable(false);
                    btnVoltar.setDisable(false);
                }
            });
        });
        btnEditar.setOnAction(actionEvent -> {
            if(phaseManager.queryDocente()!=null){
                GestaoDocenteEditar gde = new GestaoDocenteEditar(phaseManager.queryDocente());
                gde.btn1.setOnAction(actionEvent1 -> {
                    if(checkFieldsEdit(gde)){
                        //System.out.println(gain.cbCurso.getSelectionModel().getSelectedItem()+" "+gain.cbRamo.getSelectionModel().getSelectedItem());
                        Docente d = new Docente(
                                gde.tfNome.getText(),
                                gde.cbEmail.getSelectionModel().getSelectedItem()
                        );
                        String s = phaseManager.editDocente(d);
                        this.getChildren().remove(gde);
                        changeButtonsDisable(false);
                    }
                });
                gde.btnVoltar.setOnAction(actionEvent1 -> {
                    this.getChildren().remove(gde);
                    changeButtonsDisable(false);
                });
                this.setCenter(gde);
                changeButtonsDisable(true);
            }

        });
        btnEliminar.setOnAction(actionEvent -> {
            if(phaseManager.queryDocente()!=null) {
                GestaoDocenteApagar gda = new GestaoDocenteApagar(phaseManager.queryDocente());
                gda.btn1.setOnAction(actionEvent1 -> {
                    phaseManager.deleteDocente(gda.cbEmail.getSelectionModel().getSelectedItem());
                    this.getChildren().remove(gda);
                    changeButtonsDisable(false);
                });
                gda.btnVoltar.setOnAction(actionEvent1 -> {
                    this.getChildren().remove(gda);
                    changeButtonsDisable(false);
                });
                this.setCenter(gda);
                changeButtonsDisable(true);
            }
        });
        btnExportar.setOnAction(actionEvent -> {
            GestaoDocenteExportar gde = new GestaoDocenteExportar();
            gde.btn1.setOnAction(actionEvent1 -> {
                phaseManager.export(gde.nomeFicheiro.getText());
                this.getChildren().remove(gde);
                changeButtonsDisable(false);
            });
            gde.btnVoltar.setOnAction(actionEvent1 -> {
                this.getChildren().remove(gde);
                changeButtonsDisable(false);
            });
            this.setCenter(gde);
            changeButtonsDisable(true);
        });
    }

    private boolean checkFieldsInsert(GestaoDocenteInserirNovo gain) {
        if(gain.tfEmail.getText()=="") return false;
        if(gain.tfNome.getText()=="") return false;
        return true;
    }
    private boolean checkFieldsEdit(GestaoDocenteEditar gae) {
        if(gae.cbEmail.getSelectionModel().getSelectedItem()==null)return false;
        if(gae.tfNome.getText()=="") return false;
        return true;
    }
    private void changeButtonsDisable(boolean d){
        btnInserir.setDisable(d);
        btnVoltar.setDisable(d);
        btnConsulta.setDisable(d);
        btnEditar.setDisable(d);
        btnExportar.setDisable(d);
        btnEliminar.setDisable(d);
    }

}
