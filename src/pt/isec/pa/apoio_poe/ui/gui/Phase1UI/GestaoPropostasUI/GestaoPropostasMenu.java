package pt.isec.pa.apoio_poe.ui.gui.Phase1UI.GestaoPropostasUI;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import pt.isec.pa.apoio_poe.model.PhaseManager;
import pt.isec.pa.apoio_poe.model.data.phase1.Docente;
import pt.isec.pa.apoio_poe.model.fsm.PhaseState;

import java.util.List;

public class GestaoPropostasMenu extends BorderPane {

    PhaseManager phaseManager;
    GestaoPropostasMensagens gpm;
    GestaoPropostasInserirPorFicheiro gpipf;
    GestaoPropostasInserirNovo gpin;
    Button btnInserir,btnConsulta,btnEditar,btnEliminar,btnExportar,btnVoltar;

    public GestaoPropostasMenu(PhaseManager phaseManager){
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
        if (phaseManager.getState() != PhaseState.GEST_PROPOSTA) {
            this.setVisible(false);
            return;
        }
        if(phaseManager.getFechado()!=0){
            btnEditar.setDisable(true);
            btnEliminar.setDisable(true);
            btnInserir.setDisable(true);
            btnExportar.setDisable(true);
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
            gpm = new GestaoPropostasMensagens("TIPO DE INSERÇÃO","POR FICHEIRO","NOVA PROPOSTA");
            this.setCenter(gpm);
            gpm.btn1.setOnAction(actionEvent1 -> {
                gpipf = new GestaoPropostasInserirPorFicheiro();
                changeButtonsDisable(true);
                this.getChildren().remove(gpm);
                this.setCenter(gpipf);
                gpipf.btn1.setOnAction(actionEvent2 -> {
                    phaseManager.insert(gpipf.nomeFicheiro.getText());
                    changeButtonsDisable(false);
                    this.getChildren().remove(gpipf);
                });
                gpipf.btnVoltar.setOnAction(actionEvent2 -> {
                    changeButtonsDisable(false);
                    this.getChildren().remove(gpipf);
                });
            });
            gpm.btn2.setOnAction(actionEvent1 -> {
                gpin = new GestaoPropostasInserirNovo(phaseManager.queryDocente(),phaseManager.queryAluno());
                this.setCenter(gpin);
                gpin.btn1.setOnAction(actionEvent2 -> {
                    String [] aux= new String[6];
                    if(gpin.rbProjeto.isSelected()){
                        //VERIFICAR OS CAMPOS
                        aux[0]="T2";
                        aux[1]=gpin.tfCodigo.getText();
                        String ramos ="";
                        int i=0;
                        if(gpin.rbDA.isSelected()){
                            ramos+="DA";
                            i++;
                        }
                        if(gpin.rbRAS.isSelected()){
                            if(i==0){
                                ramos+="RAS";
                                i++;
                            }else{
                                ramos+="|RAS";
                                i++;
                            }
                        }
                        if(gpin.rbSI.isSelected()){
                            if(i>0){
                                ramos+="|SI";
                            }else{
                                ramos+="SI";
                            }
                        }
                        aux[2]=ramos;
                        aux[3]=gpin.tfTitulo.getText();
                        for(Docente d :(List<Docente>)gpin.cbNomeDocente.getUserData()){
                            if(d.getNome().equalsIgnoreCase(gpin.cbNomeDocente.getSelectionModel().getSelectedItem())){
                                aux[4]=d.getEmail();
                                break;
                            }
                        }
                        aux[5]=gpin.cbNumeroAluno.getSelectionModel().getSelectedItem();
                        phaseManager.insertProposta(aux);
                    }
                    if(gpin.rbEstagio.isSelected()) {
                        //VERIFICAR OS CAMPOS
                        aux[0]="T1";
                        aux[1]=gpin.tfCodigo.getText();
                        String ramos ="";
                        int i=0;
                        if(gpin.rbDA.isSelected()){
                            ramos+="DA";
                            i++;
                        }
                        if(gpin.rbRAS.isSelected()){
                            if(i==0){
                                ramos+="RAS";
                                i++;
                            }else{
                                ramos+="|RAS";
                                i++;
                            }
                        }
                        if(gpin.rbSI.isSelected()){
                            if(i>0){
                                ramos+="|SI";
                            }else{
                                ramos+="SI";
                            }
                        }
                        aux[2]=ramos;
                        aux[3]=gpin.tfTitulo.getText();
                        aux[4]=gpin.tfEmpresa.getText();
                        aux[5]=gpin.cbNumeroAluno.getSelectionModel().getSelectedItem();
                        phaseManager.insertProposta(aux);
                    }
                    if(gpin.rbAutoprosposta.isSelected()){
                        //VERIFICAR OS CAMPOS
                        aux[0]="T3";
                        aux[1]=gpin.tfCodigo.getText();
                        aux[2]=gpin.tfTitulo.getText();
                        aux[3]=gpin.cbNumeroAluno.getSelectionModel().getSelectedItem();
                        phaseManager.insertProposta(aux);
                    }
                    this.getChildren().remove(gpin);
                    changeButtonsDisable(false);
                });
                changeButtonsDisable(true);
                gpin.btnVoltar.setOnAction(actionEvent2 -> {
                    this.getChildren().remove(gpin);
                    changeButtonsDisable(false);
                });
            });
        });
        btnConsulta.setOnAction(actionEvent -> {
            GestaoPropostasMensagemListar gpml = new GestaoPropostasMensagemListar();
            this.setCenter(gpml);
            gpml.btn1.setOnAction(actionEvent1 -> {
                this.getChildren().remove(gpml);
                GestaoPropostasListarPropostas galp = new GestaoPropostasListarPropostas(phaseManager.queryProposta());
                this.setCenter(galp);
                changeButtonsDisable(true);
                galp.btnVoltar.setOnAction(actionEvent2 -> {
                    this.getChildren().remove(galp);
                    if(phaseManager.getFechado()==0) {
                        changeButtonsDisable(false);
                    }else{
                        btnConsulta.setDisable(false);
                        btnVoltar.setDisable(false);
                    }
                });
            });
            gpml.btn2.setOnAction(actionEvent1 -> {
                this.getChildren().remove(gpml);
                GestaoPropostasListarEstagio gale = new GestaoPropostasListarEstagio(phaseManager.queryProposta());
                this.setCenter(gale);
                changeButtonsDisable(true);
                gale.btnVoltar.setOnAction(actionEvent2 -> {
                    this.getChildren().remove(gale);
                    changeButtonsDisable(false);
                });
            });
            gpml.btn3.setOnAction(actionEvent1 -> {
                this.getChildren().remove(gpml);
                GestaoPropostasListarProjetos galp = new GestaoPropostasListarProjetos(phaseManager.queryProposta());
                this.setCenter(galp);
                changeButtonsDisable(true);
                galp.btnVoltar.setOnAction(actionEvent2 -> {
                    this.getChildren().remove(galp);
                    changeButtonsDisable(false);
                });
            });
            gpml.btn4.setOnAction(actionEvent1 -> {
                this.getChildren().remove(gpml);
                GestaoPropostasListarAutoproposta gala = new GestaoPropostasListarAutoproposta(phaseManager.queryProposta());
                this.setCenter(gala);
                changeButtonsDisable(true);
                gala.btnVoltar.setOnAction(actionEvent2 -> {
                    this.getChildren().remove(gala);
                    changeButtonsDisable(false);
                });
            });
        });
        btnEditar.setOnAction(actionEvent -> {
            if(phaseManager.queryProposta()!=null){
                GestaoPropostasEditar gpe = new GestaoPropostasEditar(phaseManager.queryDocente(),phaseManager.queryAluno(),phaseManager.queryProposta());
                gpe.btn1.setOnAction(actionEvent1 -> {
                    String [] aux= new String[6];
                    if(gpe.rbProjeto.isSelected()){
                        //VERIFICAR OS CAMPOS
                        aux[0]="T2";
                        aux[1]=gpe.cbCodigo.getSelectionModel().getSelectedItem();
                        String ramos ="";
                        int i=0;
                        if(gpe.rbDA.isSelected()){
                            ramos+="DA";
                            i++;
                        }
                        if(gpe.rbRAS.isSelected()){
                            if(i==0){
                                ramos+="RAS";
                                i++;
                            }else{
                                ramos+="|RAS";
                                i++;
                            }
                        }
                        if(gpe.rbSI.isSelected()){
                            if(i>0){
                                ramos+="|SI";
                            }else{
                                ramos+="SI";
                            }
                        }
                        aux[2]=ramos;
                        aux[3]=gpe.tfTitulo.getText();
                        for(Docente d :(List<Docente>)gpe.cbNomeDocente.getUserData()){
                            if(d.getNome().equalsIgnoreCase(gpe.cbNomeDocente.getSelectionModel().getSelectedItem())){
                                aux[4]=d.getEmail();
                                break;
                            }
                        }
                        aux[5]=gpe.cbNumeroAluno.getSelectionModel().getSelectedItem();
                        phaseManager.editProposta(aux);
                    }
                    if(gpe.rbEstagio.isSelected()) {
                        //VERIFICAR OS CAMPOS
                        aux[0]="T1";
                        aux[1]=gpe.cbCodigo.getSelectionModel().getSelectedItem();
                        String ramos ="";
                        int i=0;
                        if(gpe.rbDA.isSelected()){
                            ramos+="DA";
                            i++;
                        }
                        if(gpe.rbRAS.isSelected()){
                            if(i==0){
                                ramos+="RAS";
                                i++;
                            }else{
                                ramos+="|RAS";
                                i++;
                            }
                        }
                        if(gpe.rbSI.isSelected()){
                            if(i>0){
                                ramos+="|SI";
                            }else{
                                ramos+="SI";
                            }
                        }
                        aux[2]=ramos;
                        aux[3]=gpe.tfTitulo.getText();
                        aux[4]=gpe.tfEmpresa.getText();
                        aux[5]=gpe.cbNumeroAluno.getSelectionModel().getSelectedItem();
                        phaseManager.editProposta(aux);
                    }
                    if(gpe.rbAutoprosposta.isSelected()){
                        //VERIFICAR OS CAMPOS
                        aux[0]="T3";
                        aux[1]=gpe.cbCodigo.getSelectionModel().getSelectedItem();
                        aux[2]=gpe.tfTitulo.getText();
                        aux[3]=gpe.cbNumeroAluno.getSelectionModel().getSelectedItem();
                        phaseManager.editProposta(aux);
                    }
                    this.getChildren().remove(gpe);
                    changeButtonsDisable(false);
                });
                gpe.btnVoltar.setOnAction(actionEvent1 -> {
                    this.getChildren().remove(gpe);
                    changeButtonsDisable(false);
                });
                this.setCenter(gpe);
                changeButtonsDisable(true);
            }

        });
        btnEliminar.setOnAction(actionEvent -> {
            if(phaseManager.queryDocente()!=null) {
                GestaoPropostasApagar gpa = new GestaoPropostasApagar(phaseManager.queryDocente(),phaseManager.queryAluno(),phaseManager.queryProposta());
                gpa.btn1.setOnAction(actionEvent1 -> {
                    phaseManager.deleteProposta(gpa.cbCodigo.getSelectionModel().getSelectedItem());
                    this.getChildren().remove(gpa);
                    changeButtonsDisable(false);
                });
                gpa.btnVoltar.setOnAction(actionEvent1 -> {
                    this.getChildren().remove(gpa);
                    changeButtonsDisable(false);
                });
                this.setCenter(gpa);
                changeButtonsDisable(true);
            }
        });
        btnExportar.setOnAction(actionEvent -> {
            GestaoPropostasExportar gpe = new GestaoPropostasExportar();
            gpe.btn1.setOnAction(actionEvent1 -> {
                phaseManager.export(gpe.nomeFicheiro.getText());
                this.getChildren().remove(gpe);
                changeButtonsDisable(false);
            });
            gpe.btnVoltar.setOnAction(actionEvent1 -> {
                this.getChildren().remove(gpe);
                changeButtonsDisable(false);
            });
            this.setCenter(gpe);
            changeButtonsDisable(true);
        });
    }

    private boolean checkFieldsInsert(GestaoPropostasInserirNovo gain) {
       // if(gain.tfEmail.getText()=="") return false;
        //if(gain.tfNome.getText()=="") return false;
        return true;
    }
    private boolean checkFieldsEdit(GestaoPropostasEditar gae) {
        /*
        if(gae.cbEmail.getSelectionModel().getSelectedItem()==null)return false;
        if(gae.tfNome.getText()=="") return false;

        */ return true;
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
