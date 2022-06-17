package pt.isec.pa.apoio_poe.ui.gui.Phase3UI;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import pt.isec.pa.apoio_poe.model.PhaseManager;
import pt.isec.pa.apoio_poe.model.data.Queries;
import pt.isec.pa.apoio_poe.model.data.phase1.Aluno;
import pt.isec.pa.apoio_poe.model.data.phase2.Candidatura;
import pt.isec.pa.apoio_poe.model.fsm.PhaseState;
import pt.isec.pa.apoio_poe.ui.gui.Phase2UI.GestaoCandidaturaUI.*;

import java.util.ArrayList;
import java.util.List;

public class Phase3Menu extends BorderPane {
    PhaseManager phaseManager;
    Button btnAtribuicoesAutomatica,btnOperaçoesManuais,btnListaDeAlunos,btnListaDePropostas,btnExportar,btnAvancar,btnFecharFase,btnQuit,btnVoltar;
    HBox butoes;
    public Phase3Menu(PhaseManager phaseManager){
        this.phaseManager=phaseManager;
        btnAtribuicoesAutomatica = new Button();
        btnOperaçoesManuais = new Button();
        btnListaDeAlunos = new Button();
        btnListaDePropostas = new Button();
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
        if (phaseManager.getState() != PhaseState.PROPOSTAS) {
            this.setVisible(false);
            return;
        }
        if(phaseManager.getFechado()>2){
            btnOperaçoesManuais.setDisable(true);
            btnFecharFase.setDisable(true);
        }
        this.setVisible(true);
    }

    private void registerHandlers() {
        phaseManager.addPropertyChangeListener(evt -> {
            update();
        });
        btnAtribuicoesAutomatica.setOnAction(actionEvent -> {
            phaseManager.iniciar(1);
        });
        btnOperaçoesManuais.setOnAction(actionEvent -> {
            phaseManager.iniciar(2);
        });
        btnExportar.setOnAction(actionEvent -> {
            GestaoPhase3Exportar gpe = new GestaoPhase3Exportar();
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
        btnListaDeAlunos.setOnAction(actionEvent -> {
            Phase3MensagemListar pml = new Phase3MensagemListar();
            this.setCenter(pml);
            pml.btn1.setOnAction(actionEvent1 -> {
                this.getChildren().remove(pml);
                Phase3ListarAlunos pla = new Phase3ListarAlunos(phaseManager.queryAluno(Queries.ALUNOS_COM_AUTOPROPOSTA));
                this.setCenter(pla);
                changeButtonsDisable(true);
                pla.btnVoltar.setOnAction(actionEvent2 -> {
                    this.getChildren().remove(pla);
                    changeButtonsDisable(false);
                });
            });
            pml.btn2.setOnAction(actionEvent1 -> {
                this.getChildren().remove(pml);
                Phase3ListarAlunos pla = new Phase3ListarAlunos(phaseManager.queryAluno(Queries.ALUNOS_COM_CANDIDATURA_REGISTADA));
                this.setCenter(pla);
                changeButtonsDisable(true);
                pla.btnVoltar.setOnAction(actionEvent2 -> {
                    this.getChildren().remove(pla);
                    changeButtonsDisable(false);
                });
            });
            pml.btn3.setOnAction(actionEvent1 -> {
                this.getChildren().remove(pml);
                Phase3ListarAlunos pla = new Phase3ListarAlunos(phaseManager.queryAluno(Queries.ALUNOS_SEM_CANDIDATURA));
                this.setCenter(pla);
                changeButtonsDisable(true);
                pla.btnVoltar.setOnAction(actionEvent2 -> {
                    this.getChildren().remove(pla);
                    changeButtonsDisable(false);
                });
            });
        });
        btnVoltar.setOnAction(actionEvent -> {
            phaseManager.voltar();
        });
        btnAvancar.setOnAction(actionEvent -> {
            phaseManager.avancar();
        });
        btnFecharFase.setOnAction(actionEvent -> {
            if(phaseManager.fecharFase()){
            }
        });
        btnQuit.setOnAction(actionEvent -> {
            phaseManager.saveBin();
            Platform.exit();
        });
    }

    private void createViews() {
        this.setMinWidth(1200);
        this.setMinHeight(600);
        this.setStyle("-fx-background-color: #3BCEAC");
        btnQuit.setText("QUIT");
        btnQuit.setMinWidth(100);
        btnQuit.setMaxWidth(100);
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
        btnListaDePropostas.setText("LISTA DE PROPOSTAS");
        btnListaDePropostas.setMinHeight(50);
        btnListaDePropostas.setMinWidth(200);
        btnListaDePropostas.setMaxWidth(200);
        btnListaDePropostas.setStyle("-fx-font-size: 15px;-fx-font-weight: bold;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
        btnListaDeAlunos.setText("LISTA DE ALUNOS");
        btnListaDeAlunos.setMinHeight(50);
        btnListaDeAlunos.setMinWidth(180);
        btnListaDeAlunos.setMaxWidth(180);
        btnListaDeAlunos.setStyle("-fx-font-size: 15px;-fx-font-weight: bold;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
        btnOperaçoesManuais.setText("MANUAL");
        btnOperaçoesManuais.setMinHeight(50);
        btnOperaçoesManuais.setMinWidth(140);
        btnOperaçoesManuais.setMaxWidth(140);
        btnOperaçoesManuais.setStyle("-fx-font-size: 15px;-fx-font-weight: bold;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
        btnAtribuicoesAutomatica.setText("AUTOMATICO");
        btnAtribuicoesAutomatica.setMinHeight(50);
        btnAtribuicoesAutomatica.setMinWidth(150);
        btnAtribuicoesAutomatica.setMaxWidth(150);
        btnAtribuicoesAutomatica.setStyle("-fx-font-size: 15px;-fx-font-weight: bold;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
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
        butoes.getChildren().addAll(btnAtribuicoesAutomatica,btnOperaçoesManuais,btnListaDeAlunos,btnListaDePropostas,btnExportar,btnAvancar,btnVoltar,btnFecharFase,btnQuit);
        this.setTop(butoes);
    }

    private void changeButtonsDisable(boolean d){
        if(phaseManager.getFechado()==2) {
            btnAvancar.setDisable(d);
            btnQuit.setDisable(d);
            btnFecharFase.setDisable(d);
            btnAtribuicoesAutomatica.setDisable(d);
            btnOperaçoesManuais.setDisable(d);
            btnExportar.setDisable(d);
            btnListaDeAlunos.setDisable(d);
            btnListaDePropostas.setDisable(d);
            btnVoltar.setDisable(d);
        }else{
            btnAtribuicoesAutomatica.setDisable(d);
            btnListaDeAlunos.setDisable(d);
            btnListaDePropostas.setDisable(d);
            btnVoltar.setDisable(d);
            btnExportar.setDisable(d);
            btnAvancar.setDisable(d);
        }
    }
}
