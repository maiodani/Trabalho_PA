package pt.isec.pa.apoio_poe.ui.gui.Phase5UI;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import pt.isec.pa.apoio_poe.model.PhaseManager;
import pt.isec.pa.apoio_poe.model.data.Queries;
import pt.isec.pa.apoio_poe.model.fsm.PhaseState;
import pt.isec.pa.apoio_poe.ui.gui.Phase4UI.*;
import pt.isec.pa.apoio_poe.ui.gui.Phase5UI.DadosAlunosUI.DadosAlunoListar;
import pt.isec.pa.apoio_poe.ui.gui.Phase5UI.DadosAlunosUI.DadosAlunoMensagemListar;
import pt.isec.pa.apoio_poe.ui.gui.Phase5UI.DadosDiversosUI.DadosDiversosListar;
import pt.isec.pa.apoio_poe.ui.gui.Phase5UI.DadosGraficosUI.DadosGraficoPorRamo;
import pt.isec.pa.apoio_poe.ui.gui.Phase5UI.DadosPropostasUI.DadosPropostaListar;
import pt.isec.pa.apoio_poe.ui.gui.Phase5UI.DadosPropostasUI.DadosPropostaMensagemListar;

public class Phase5Menu extends BorderPane {
    PhaseManager phaseManager;
    Button btnDadosAlunos,btnDadosPropostas,btnDadosDiversos,btnDadosGraficos,btnExportar,btnQuit;
    HBox butoes;
    public Phase5Menu(PhaseManager phaseManager){
        this.phaseManager=phaseManager;
        btnDadosAlunos = new Button();
        btnDadosPropostas = new Button();
        btnDadosDiversos = new Button();
        btnExportar = new Button();
        btnDadosGraficos = new Button();
        btnQuit = new Button();
        butoes = new HBox();
        createViews();
        registerHandlers();
        update();
    }

    private void update() {
        if (phaseManager.getState() != PhaseState.CONSULTA) {
            this.setVisible(false);
            return;
        }

        this.setVisible(true);
    }

    private void registerHandlers() {
        phaseManager.addPropertyChangeListener(evt -> {
            update();
        });
        btnDadosAlunos.setOnAction(actionEvent -> {
            DadosAlunoMensagemListar ml = new DadosAlunoMensagemListar();
            this.setCenter(ml);
            ml.btn1.setOnAction(actionEvent1 -> {
                this.getChildren().remove(ml);
                DadosAlunoListar dal = new DadosAlunoListar(phaseManager.queryAluno(Queries.ALUNOS_COM_PROPOSTA_ATRIBUIDA));
                this.setCenter(dal);
                dal.btnVoltar.setOnAction(actionEvent2 -> {
                    this.getChildren().remove(dal);
                });
            });
            ml.btn2.setOnAction(actionEvent1 -> {
                this.getChildren().remove(ml);
                DadosAlunoListar dal = new DadosAlunoListar(phaseManager.queryAluno(Queries.ALUNOS_SEM_PROPOSTA_ATRIBUIDA_COM_CANDIDATURA));
                this.setCenter(dal);
                dal.btnVoltar.setOnAction(actionEvent2 -> {
                    this.getChildren().remove(dal);
                });
            });
        });
        btnDadosPropostas.setOnAction(actionEvent -> {
            DadosPropostaMensagemListar dpml = new DadosPropostaMensagemListar();
            this.setCenter(dpml);
            dpml.btn1.setOnAction(actionEvent1 -> {
                this.getChildren().remove(dpml);
                DadosPropostaListar dpl = new DadosPropostaListar(phaseManager.queryPropostas(Queries.PROPOSTAS_DISPONIVEIS));
                this.setCenter(dpl);
                dpl.btnVoltar.setOnAction(actionEvent2 -> {
                    this.getChildren().remove(dpl);
                });
            });
            dpml.btn2.setOnAction(actionEvent1 -> {
                this.getChildren().remove(dpml);
                DadosPropostaListar dpl = new DadosPropostaListar(phaseManager.queryPropostas(Queries.PROPOSTAS_ATRIBUIDAS));
                this.setCenter(dpl);
                dpl.btnVoltar.setOnAction(actionEvent2 -> {
                    this.getChildren().remove(dpl);
                });
            });
        });
        btnDadosDiversos.setOnAction(actionEvent -> {
            DadosDiversosListar ddl = new DadosDiversosListar(phaseManager.dadosDiversos());
            this.setCenter(ddl);
            ddl.btnVoltar.setOnAction(actionEvent2 -> {
                this.getChildren().remove(ddl);
            });
        });
        btnDadosGraficos.setOnAction(actionEvent -> {
            DadosGraficoPorRamo dgpr = new DadosGraficoPorRamo(phaseManager.dadosPorRamo());
            this.setCenter(dgpr);
            dgpr.btnVoltar.setOnAction(actionEvent2 -> {
                this.getChildren().remove(dgpr);
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
        btnQuit.setOnAction(actionEvent -> {
            phaseManager.saveBin();
            Platform.exit();
        });
    }

    private void disableButtons(boolean b) {

        btnExportar.setDisable(b);
        btnQuit.setDisable(b);
    }

    private void createViews() {
        this.setMinWidth(1200);
        this.setMinHeight(600);
        this.setStyle("-fx-background-color: #3BCEAC");
        btnQuit.setText("QUIT");
        btnQuit.setMinWidth(200);
        btnQuit.setMaxWidth(200);
        btnQuit.setMinHeight(50);
        btnQuit.setStyle("-fx-font-size: 15px;-fx-font-weight: bold;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
        btnDadosAlunos.setText("DADOS ALUNOS");
        btnDadosAlunos.setMinWidth(200);
        btnDadosAlunos.setMaxWidth(200);
        btnDadosAlunos.setMinHeight(50);
        btnDadosAlunos.setStyle("-fx-font-size: 15px;-fx-font-weight: bold;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
        btnDadosPropostas.setText("DADOS PROPOSTAS");
        btnDadosPropostas.setMinWidth(200);
        btnDadosPropostas.setMaxWidth(200);
        btnDadosPropostas.setMinHeight(50);
        btnDadosPropostas.setStyle("-fx-font-size: 15px;-fx-font-weight: bold;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
        btnDadosDiversos.setText("DADOS DIVERSOS");
        btnDadosDiversos.setMinHeight(50);
        btnDadosDiversos.setMinWidth(200);
        btnDadosDiversos.setMaxWidth(200);
        btnDadosDiversos.setStyle("-fx-font-size: 15px;-fx-font-weight: bold;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
        btnExportar.setText("EXPORTAR");
        btnExportar.setMinHeight(50);
        btnExportar.setMinWidth(200);
        btnExportar.setMaxWidth(200);
        btnExportar.setStyle("-fx-font-size: 15px;-fx-font-weight: bold;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
        btnDadosGraficos.setText("DADOS EM GRAFICOS");
        btnDadosGraficos.setMinHeight(50);
        btnDadosGraficos.setMinWidth(200);
        btnDadosGraficos.setMaxWidth(200);
        btnDadosGraficos.setStyle("-fx-font-size: 15px;-fx-font-weight: bold;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");

        /*this.setAlignment(Pos.CENTER);
        this.setSpacing(15);
        */
        //this.getChildren().addAll(btnGestAluno,btnGestProfessor,btnGestPropostas,btnAvancar,btnFecharFase,btnQuit);
        //butoes.setStyle("-fx-background-color: white");
        butoes.getChildren().addAll(btnDadosAlunos,btnDadosPropostas,btnDadosDiversos,btnDadosGraficos,btnExportar,btnQuit);
        this.setTop(butoes);
    }

    private void changeButtonsDisable(boolean d){
        /*
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
        */

    }
}
