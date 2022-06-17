package pt.isec.pa.apoio_poe.ui.gui.Phase4UI;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import pt.isec.pa.apoio_poe.model.PhaseManager;
import pt.isec.pa.apoio_poe.model.data.Queries;
import pt.isec.pa.apoio_poe.model.fsm.PhaseState;
import pt.isec.pa.apoio_poe.ui.gui.Phase1UI.GestaoPropostasUI.*;
import pt.isec.pa.apoio_poe.ui.gui.Phase4UI.GestaoManualUI.GestaoManualAdicionar;
public class Phase4Menu extends BorderPane {
    PhaseManager phaseManager;
    Button btnAtribuicoesAutomatica,btnAtribuicoesManual,btnConsultar,btnEditar,btnEliminar,btnDadosDiversos,btnExportar,btnAvancar,btnFecharFase,btnQuit,btnVoltar;
    HBox butoes;
    public Phase4Menu(PhaseManager phaseManager){
        this.phaseManager=phaseManager;
        btnAtribuicoesAutomatica = new Button();
        btnAtribuicoesManual = new Button();
        btnConsultar = new Button();
        btnEditar = new Button();
        btnEliminar = new Button();
        btnDadosDiversos = new Button();
        btnExportar = new Button();
        btnAvancar = new Button();
        btnQuit = new Button();
        btnFecharFase = new Button();
        btnVoltar = new Button();
        butoes = new HBox();
        createViews();
        registerHandlers();
        update();
    }

    private void update() {
        if (phaseManager.getState() != PhaseState.ATRIBUICAO_ORIENTADORES) {
            this.setVisible(false);
            return;
        }

        this.setVisible(true);
    }

    private void registerHandlers() {
        phaseManager.addPropertyChangeListener(evt -> {
            update();
        });
        btnAtribuicoesAutomatica.setOnAction(actionEvent -> {
            phaseManager.insert();
        });
        btnAtribuicoesManual.setOnAction(actionEvent -> {
            disableButtons(true);
            GestaoManualAdicionar gma = new GestaoManualAdicionar(phaseManager.queryPropostaManual(),phaseManager.queryDocente());
            this.setCenter(gma);
            gma.btn1.setOnAction(actionEvent1 -> {
                if(gma.cbProposta.getSelectionModel().getSelectedIndex()>0 && gma.cbDocente.getSelectionModel().getSelectedIndex()>0){
                    phaseManager.insert(gma.cbProposta.getSelectionModel().getSelectedItem(),gma.cbDocente.getSelectionModel().getSelectedItem());
                }
                disableButtons(false);
                this.getChildren().remove(gma);
            });
            gma.btnVoltar.setOnAction(actionEvent1 -> {
                disableButtons(false);
                this.getChildren().remove(gma);
            });
        });
        btnConsultar.setOnAction(actionEvent -> {
            Phase4Listar pl = new Phase4Listar(phaseManager.queryProposta());
            this.setCenter(pl);
            changeButtonsDisable(true);
            pl.btnVoltar.setOnAction(actionEvent2 -> {
                this.getChildren().remove(pl);
                changeButtonsDisable(false);
            });
        });
        btnEditar.setOnAction(actionEvent -> {
            Phase4Editar pe = new Phase4Editar(phaseManager.queryProposta(),phaseManager.queryDocente());
            this.setCenter(pe);
            changeButtonsDisable(true);
            pe.btn1.setOnAction(actionEvent1 -> {
                if(pe.cbProposta.getSelectionModel().getSelectedIndex()>0 && pe.cbDocente.getSelectionModel().getSelectedIndex()>0){
                    phaseManager.editProposta(pe.cbProposta.getSelectionModel().getSelectedItem(),pe.cbDocente.getSelectionModel().getSelectedItem());
                }
                disableButtons(false);
                this.getChildren().remove(pe);
            });
            pe.btnVoltar.setOnAction(actionEvent2 -> {
                this.getChildren().remove(pe);
                changeButtonsDisable(false);
            });
        });
        btnEliminar.setOnAction(actionEvent -> {
            Phase4Eliminar pe = new Phase4Eliminar(phaseManager.queryProposta(),phaseManager.queryDocente());
            this.setCenter(pe);
            changeButtonsDisable(true);
            pe.btn1.setOnAction(actionEvent1 -> {
                if(pe.cbProposta.getSelectionModel().getSelectedIndex()>0 && pe.cbDocente.getSelectionModel().getSelectedIndex()>0){
                    phaseManager.deleteProposta(pe.cbProposta.getSelectionModel().getSelectedItem());
                }
                disableButtons(false);
                this.getChildren().remove(pe);
            });
            pe.btnVoltar.setOnAction(actionEvent2 -> {
                this.getChildren().remove(pe);
                changeButtonsDisable(false);
            });
        });
        btnDadosDiversos.setOnAction(actionEvent -> {

            Phase4MensagemListar pml = new Phase4MensagemListar();
            this.setCenter(pml);
            pml.btn1.setOnAction(actionEvent1 -> {
                this.getChildren().remove(pml);
                Phase4AlunoListar pal = new Phase4AlunoListar(phaseManager.queryAluno(Queries.ALUNOS_COM_PROPOSTAS_COM_DOCENTE));
                this.setCenter(pal);
                changeButtonsDisable(true);
                pal.btnVoltar.setOnAction(actionEvent2 -> {
                    this.getChildren().remove(pal);
                    changeButtonsDisable(false);
                });
            });

            pml.btn2.setOnAction(actionEvent1 -> {
                this.getChildren().remove(pml);
                Phase4AlunoListar pal = new Phase4AlunoListar(phaseManager.queryAluno(Queries.ALUNOS_COM_PROPOSTAS_SEM_DOCENTE));
                this.setCenter(pal);
                changeButtonsDisable(true);
                pal.btnVoltar.setOnAction(actionEvent2 -> {
                    this.getChildren().remove(pal);
                    changeButtonsDisable(false);
                });
            });
            pml.btn3.setOnAction(actionEvent1 -> {
                this.getChildren().remove(pml);
                Phase4ListarDadosDiversos pldd = new Phase4ListarDadosDiversos(phaseManager.dadosDiversos());
                this.setCenter(pldd);
                changeButtonsDisable(true);
                pldd.btnVoltar.setOnAction(actionEvent2 -> {
                    this.getChildren().remove(pldd);
                    changeButtonsDisable(false);
                });
            });
        });
        btnExportar.setOnAction(actionEvent -> {
            GestaoPhase4Exportar gpe = new GestaoPhase4Exportar();
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
        btnVoltar.setOnAction(actionEvent -> {
            phaseManager.voltar();
        });
        btnAvancar.setOnAction(actionEvent -> {
            phaseManager.avancar();
        });
        btnFecharFase.setOnAction(actionEvent -> {
            if(!phaseManager.fecharFase()){
            }
        });
        btnQuit.setOnAction(actionEvent -> {
            phaseManager.saveBin();
            Platform.exit();
        });
    }

    private void disableButtons(boolean b) {
        btnAtribuicoesAutomatica.setDisable(b);
        btnAtribuicoesManual.setDisable(b);
        btnConsultar.setDisable(b);
        btnEditar.setDisable(b);
        btnEliminar.setDisable(b);
        btnDadosDiversos.setDisable(b);
        btnExportar.setDisable(b);
        btnAvancar.setDisable(b);
        btnVoltar.setDisable(b);
        btnFecharFase.setDisable(b);
        btnQuit.setDisable(b);
    }

    private void createViews() {
        this.setMinWidth(1200);
        this.setMinHeight(600);
        this.setStyle("-fx-background-color: #3BCEAC");
        btnQuit.setText("QUIT");
        btnQuit.setMinWidth(70);
        btnQuit.setMaxWidth(70);
        btnQuit.setMinHeight(50);
        btnQuit.setStyle("-fx-font-size: 15px;-fx-font-weight: bold;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
        btnAvancar.setText("AVANÇAR");
        btnAvancar.setMinWidth(100);
        btnAvancar.setMaxWidth(100);
        btnAvancar.setMinHeight(50);
        btnAvancar.setStyle("-fx-font-size: 15px;-fx-font-weight: bold;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
        btnFecharFase.setText("FECHAR FASE");
        btnFecharFase.setMinHeight(50);
        btnFecharFase.setMinWidth(125);
        btnFecharFase.setMaxWidth(125);
        btnFecharFase.setStyle("-fx-font-size: 15px;-fx-font-weight: bold;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
        btnEditar.setText("EDITAR");
        btnEditar.setMinHeight(50);
        btnEditar.setMinWidth(80);
        btnEditar.setMaxWidth(80);
        btnEditar.setStyle("-fx-font-size: 15px;-fx-font-weight: bold;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
        btnConsultar.setText("CONSULTA");
        btnConsultar.setMinHeight(50);
        btnConsultar.setMinWidth(120);
        btnConsultar.setMaxWidth(120);
        btnConsultar.setStyle("-fx-font-size: 15px;-fx-font-weight: bold;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
        btnEliminar.setText("ELIMINAR");
        btnEliminar.setMinHeight(50);
        btnEliminar.setMinWidth(100);
        btnEliminar.setMaxWidth(100);
        btnEliminar.setStyle("-fx-font-size: 15px;-fx-font-weight: bold;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
        btnAtribuicoesAutomatica.setText("AUTOMATICO");
        btnAtribuicoesAutomatica.setMinHeight(50);
        btnAtribuicoesAutomatica.setMinWidth(130);
        btnAtribuicoesAutomatica.setMaxWidth(130);
        btnAtribuicoesAutomatica.setStyle("-fx-font-size: 15px;-fx-font-weight: bold;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
        btnAtribuicoesManual.setText("MANUAL");
        btnAtribuicoesManual.setMinHeight(50);
        btnAtribuicoesManual.setMinWidth(100);
        btnAtribuicoesManual.setMaxWidth(100);
        btnAtribuicoesManual.setStyle("-fx-font-size: 15px;-fx-font-weight: bold;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
        btnDadosDiversos.setText("DADOS DIVERSOS");
        btnDadosDiversos.setMinHeight(50);
        btnDadosDiversos.setMinWidth(170);
        btnDadosDiversos.setMaxWidth(170);
        btnDadosDiversos.setStyle("-fx-font-size: 15px;-fx-font-weight: bold;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
        btnExportar.setText("EXPORTAR");
        btnExportar.setMinHeight(50);
        btnExportar.setMinWidth(105);
        btnExportar.setMaxWidth(105);
        btnExportar.setStyle("-fx-font-size: 15px;-fx-font-weight: bold;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
        btnVoltar.setText("VOLTAR");
        btnVoltar.setMinHeight(50);
        btnVoltar.setMinWidth(100);
        btnVoltar.setMaxWidth(100);
        btnVoltar.setStyle("-fx-font-size: 15px;-fx-font-weight: bold;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");

        /*this.setAlignment(Pos.CENTER);
        this.setSpacing(15);
        */
        //this.getChildren().addAll(btnGestAluno,btnGestProfessor,btnGestPropostas,btnAvancar,btnFecharFase,btnQuit);
        //butoes.setStyle("-fx-background-color: white");
        butoes.getChildren().addAll(btnAtribuicoesAutomatica,btnAtribuicoesManual,btnConsultar,btnEditar,btnEliminar,btnDadosDiversos,btnExportar,btnAvancar,btnVoltar,btnFecharFase,btnQuit);
        this.setTop(butoes);
    }

    private void changeButtonsDisable(boolean d){
        if(phaseManager.getFechado()==3) {
            btnAvancar.setDisable(d);
            btnQuit.setDisable(d);
            btnFecharFase.setDisable(d);
            btnAtribuicoesAutomatica.setDisable(d);
            btnAtribuicoesManual.setDisable(d);
            btnDadosDiversos.setDisable(d);
            btnExportar.setDisable(d);
            btnEliminar.setDisable(d);
            btnEditar.setDisable(d);
            btnConsultar.setDisable(d);
            btnVoltar.setDisable(d);

        }else{
            btnAtribuicoesAutomatica.setDisable(d);
            btnVoltar.setDisable(d);
            btnExportar.setDisable(d);
            btnAvancar.setDisable(d);
        }
    }
}
