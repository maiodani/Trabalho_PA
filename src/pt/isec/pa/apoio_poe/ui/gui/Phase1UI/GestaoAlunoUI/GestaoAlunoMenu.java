package pt.isec.pa.apoio_poe.ui.gui.Phase1UI.GestaoAlunoUI;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import pt.isec.pa.apoio_poe.model.PhaseManager;
import pt.isec.pa.apoio_poe.model.data.phase1.Aluno;
import pt.isec.pa.apoio_poe.model.data.phase1.SiglaCurso;
import pt.isec.pa.apoio_poe.model.data.phase1.SiglaRamo;
import pt.isec.pa.apoio_poe.model.fsm.PhaseState;

public class GestaoAlunoMenu extends BorderPane {

    PhaseManager phaseManager;
    GestaoAlunoMensagens gam;
    GestaoAlunoInserirPorFicheiro gaipf;
    GestaoAlunoInserirNovo gain;
    Button btnInserir,btnConsulta,btnEditar,btnEliminar,btnExportar,btnVoltar;

    public GestaoAlunoMenu(PhaseManager phaseManager){
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
        if (phaseManager.getState() != PhaseState.GEST_ALUNO) {
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
            gam = new GestaoAlunoMensagens("TIPO DE INSERÇÃO","POR FICHEIRO","NOVO ALUNO");
            this.setCenter(gam);
            gam.btn1.setOnAction(actionEvent1 -> {
                gaipf = new GestaoAlunoInserirPorFicheiro();
                changeButtonsDisable(true);
                this.getChildren().remove(gam);
                this.setCenter(gaipf);
                gaipf.btn1.setOnAction(actionEvent2 -> {
                    phaseManager.insert(gaipf.nomeFicheiro.getText());
                    changeButtonsDisable(false);
                    this.getChildren().remove(gaipf);
                });
                gaipf.btnVoltar.setOnAction(actionEvent2 -> {
                    changeButtonsDisable(false);
                    this.getChildren().remove(gaipf);
                });
            });
            gam.btn2.setOnAction(actionEvent1 -> {
                gain = new GestaoAlunoInserirNovo();
                this.setCenter(gain);
                gain.btn1.setOnAction(actionEvent2 -> {
                    if(checkFieldsInsert(gain)){
                        boolean b;
                        if(gain.btnSim.isSelected()){
                            b=true;
                        }else{
                            b=false;
                        }
                        //System.out.println(gain.cbCurso.getSelectionModel().getSelectedItem()+" "+gain.cbRamo.getSelectionModel().getSelectedItem());
                        Aluno a = new Aluno(
                                Long.parseLong(gain.tfNumero.getText()),
                                gain.tfNome.getText(),
                                gain.tfEmail.getText(),
                                SiglaCurso.parse(gain.cbCurso.getSelectionModel().getSelectedItem()),
                                SiglaRamo.parse(gain.cbRamo.getSelectionModel().getSelectedItem()),
                                Double.parseDouble(gain.tfClassificacao.getText()),
                                b
                        );
                        String s = phaseManager.insertAluno(a);
                        this.getChildren().remove(gain);
                    }else{
                        gain.lbErro.setText("TODOS OS CAMPOS DEVEM SER PREENCHIDOS");
                    }
                });
                gain.btnVoltar.setOnAction(actionEvent2 -> {
                    this.getChildren().remove(gain);
                });
                gam.setVisible(false);
                this.getChildren().remove(gam);

            });
        });
        btnConsulta.setOnAction(actionEvent -> {
            GestaoAlunoListar gal = new GestaoAlunoListar(phaseManager.queryAluno());
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
            if(phaseManager.queryAluno()!=null){
                GestaoAlunoEditar gae = new GestaoAlunoEditar(phaseManager.queryAluno());
                gae.btn1.setOnAction(actionEvent1 -> {
                    if(checkFieldsEdit(gae)){
                        boolean b;
                        if(gae.btnSim.isSelected()){
                            b=true;
                        }else{
                            b=false;
                        }
                        //System.out.println(gain.cbCurso.getSelectionModel().getSelectedItem()+" "+gain.cbRamo.getSelectionModel().getSelectedItem());
                        Aluno a = new Aluno(
                                Long.parseLong(gae.cbNumero.getSelectionModel().getSelectedItem()),
                                gae.tfNome.getText(),
                                gae.tfEmail.getText(),
                                SiglaCurso.parse(gae.cbCurso.getSelectionModel().getSelectedItem()),
                                SiglaRamo.parse(gae.cbRamo.getSelectionModel().getSelectedItem()),
                                Double.parseDouble(gae.tfClassificacao.getText()),
                                b
                        );
                        String s = phaseManager.editAluno(a);
                        this.getChildren().remove(gae);
                        changeButtonsDisable(false);
                    }
                });
                gae.btnVoltar.setOnAction(actionEvent1 -> {
                    this.getChildren().remove(gae);
                    changeButtonsDisable(false);
                });
                this.setCenter(gae);
                changeButtonsDisable(true);
            }

        });
        btnEliminar.setOnAction(actionEvent -> {
            if(phaseManager.queryAluno()!=null) {
                GestaoAlunoApagar gaa = new GestaoAlunoApagar(phaseManager.queryAluno());
                gaa.btn1.setOnAction(actionEvent1 -> {
                    phaseManager.deleteAluno(Long.parseLong(gaa.cbNumero.getSelectionModel().getSelectedItem()));
                    this.getChildren().remove(gaa);
                    changeButtonsDisable(false);
                });
                gaa.btnVoltar.setOnAction(actionEvent1 -> {
                    this.getChildren().remove(gaa);
                    changeButtonsDisable(false);
                });
                this.setCenter(gaa);
                changeButtonsDisable(true);
            }
        });
        btnExportar.setOnAction(actionEvent -> {
            GestaoAlunoExportar gae = new GestaoAlunoExportar();
            gae.btn1.setOnAction(actionEvent1 -> {
                phaseManager.export(gae.nomeFicheiro.getText());
                this.getChildren().remove(gae);
                changeButtonsDisable(false);
            });
            gae.btnVoltar.setOnAction(actionEvent1 -> {
                this.getChildren().remove(gae);
                changeButtonsDisable(false);
            });
            this.setCenter(gae);
            changeButtonsDisable(true);
        });
    }

    private boolean checkFieldsInsert(GestaoAlunoInserirNovo gain) {
        if(gain.tfNumero.getText()=="") return false;
        if(gain.tfEmail.getText()=="") return false;
        if(gain.tfNome.getText()=="") return false;
        if(gain.tfClassificacao.getText()=="") return false;
        if(gain.cbCurso.getSelectionModel().getSelectedItem()==null)return false;
        if(gain.cbRamo.getSelectionModel().getSelectedItem()==null)return false;
        if(!gain.btnSim.isSelected() && !gain.btnNao.isSelected())return false;
        return true;
    }
    private boolean checkFieldsEdit(GestaoAlunoEditar gae) {
        if(gae.tfEmail.getText()=="") return false;
        if(gae.tfNome.getText()=="") return false;
        if(gae.tfClassificacao.getText()=="") return false;
        if(gae.cbCurso.getSelectionModel().getSelectedItem()==null)return false;
        if(gae.cbRamo.getSelectionModel().getSelectedItem()==null)return false;
        if(!gae.btnSim.isSelected() && !gae.btnNao.isSelected())return false;
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
