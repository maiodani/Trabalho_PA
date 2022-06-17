package pt.isec.pa.apoio_poe.ui.gui.Phase2UI;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import pt.isec.pa.apoio_poe.model.PhaseManager;
import pt.isec.pa.apoio_poe.model.data.Queries;
import pt.isec.pa.apoio_poe.model.data.phase1.Aluno;
import pt.isec.pa.apoio_poe.model.data.phase2.Candidatura;
import pt.isec.pa.apoio_poe.model.fsm.PhaseState;
import pt.isec.pa.apoio_poe.ui.gui.Phase1UI.GestaoAlunoUI.GestaoAlunoInserirPorFicheiro;
import pt.isec.pa.apoio_poe.ui.gui.Phase1UI.GestaoAlunoUI.GestaoAlunoListar;
import pt.isec.pa.apoio_poe.ui.gui.Phase1UI.GestaoDocenteUI.GestaoDocenteExportar;
import pt.isec.pa.apoio_poe.ui.gui.Phase1UI.GestaoPropostasUI.GestaoPropostasListarEstagio;
import pt.isec.pa.apoio_poe.ui.gui.Phase1UI.GestaoPropostasUI.GestaoPropostasListarProjetos;
import pt.isec.pa.apoio_poe.ui.gui.Phase1UI.GestaoPropostasUI.GestaoPropostasListarPropostas;
import pt.isec.pa.apoio_poe.ui.gui.Phase1UI.GestaoPropostasUI.GestaoPropostasMensagemListar;
import pt.isec.pa.apoio_poe.ui.gui.Phase2UI.GestaoCandidaturaUI.*;

import java.util.ArrayList;
import java.util.List;

public class Phase2Menu extends BorderPane {
    PhaseManager phaseManager;
    Button btnInserir,btnConsulta,btnEditar,btnEliminar,btnListaAlunos,btnListaPropostas,btnExportar,btnAvancar,btnFecharFase,btnQuit,btnVoltar;
    HBox butoes;
    public Phase2Menu(PhaseManager phaseManager){
        this.phaseManager=phaseManager;
        btnInserir = new Button();
        btnConsulta = new Button();
        btnListaAlunos = new Button();
        btnListaPropostas = new Button();
        btnExportar = new Button();
        btnAvancar = new Button();
        btnQuit = new Button();
        btnFecharFase = new Button();
        btnVoltar = new Button();
        btnEditar = new Button();
        btnEliminar = new Button();
        butoes = new HBox();
        createViews();
        registerHandlers();
        update();
    }

    private void update() {
        if (phaseManager.getState() != PhaseState.CANDIDATURA) {
            this.setVisible(false);
            return;
        }
        if(phaseManager.getFechado()>1){
            btnEditar.setDisable(true);
            btnEliminar.setDisable(true);
            btnInserir.setDisable(true);
            btnFecharFase.setDisable(true);
        }
        this.setVisible(true);
    }

    private void registerHandlers() {
        phaseManager.addPropertyChangeListener(evt -> {
            update();
        });
        btnInserir.setOnAction(actionEvent -> {
            GestaoCandidaturaMensagens gcm = new GestaoCandidaturaMensagens("TIPO DE INSERÇÃO","POR FICHEIRO","NOVA CANDIDATURA");
            this.setCenter(gcm);
            gcm.btn1.setOnAction(actionEvent1 -> {
                GestaoCandidaturaInserirPorFicheiro gcipf = new GestaoCandidaturaInserirPorFicheiro();
                changeButtonsDisable(true);
                this.getChildren().remove(gcm);
                this.setCenter(gcipf);
                gcipf.btn1.setOnAction(actionEvent2 -> {
                    phaseManager.insert(gcipf.nomeFicheiro.getText());
                    changeButtonsDisable(false);
                    this.getChildren().remove(gcipf);
                });
                gcipf.btnVoltar.setOnAction(actionEvent2 -> {
                    changeButtonsDisable(false);
                    this.getChildren().remove(gcipf);
                });
                gcm.setVisible(false);
                this.getChildren().remove(gcm);
            });
            gcm.btn2.setOnAction(actionEvent1 -> {
                List<Aluno> aux = new ArrayList<>();
                for(Aluno aluno:phaseManager.queryAluno()){
                    aux.add(aluno);
                }
                GestaoCandidaturaInserirNovo gcin = new GestaoCandidaturaInserirNovo(aux,phaseManager.queryProposta(),phaseManager.queryCandidatura());
                changeButtonsDisable(true);
                gcin.btnProcessar.setOnAction(actionEvent2 -> {
                    if(gcin.cbNumEstudante.getSelectionModel().getSelectedIndex()>0){
                        Long nEstudante = Long.parseLong(gcin.cbNumEstudante.getSelectionModel().getSelectedItem());
                        List<Aluno> a = phaseManager.queryAluno();
                        Aluno al = null;
                        for(Aluno aluno:a)if(aluno.getNumEstudante()==nEstudante)al=aluno;
                        if(al!=null && !gcin.selected.isEmpty()){
                            Candidatura c = new Candidatura(al,gcin.selected);
                            phaseManager.insertCandidatura(c);
                        }
                    }
                    this.getChildren().remove(gcin);
                    changeButtonsDisable(false);
                });
                gcin.btnVoltar.setOnAction(actionEvent2 -> {
                    this.getChildren().remove(gcin);
                    changeButtonsDisable(false);
                });
                this.setCenter(gcin);
                this.getChildren().remove(gcm);
            });

        });
        btnConsulta.setOnAction(actionEvent -> {
            GestaoCandidaturaListar gcl = new GestaoCandidaturaListar(phaseManager.queryCandidatura());
            this.setCenter(gcl);
            changeButtonsDisable(true);
            gcl.btnVoltar.setOnAction(actionEvent1 -> {
                this.getChildren().remove(gcl);
                changeButtonsDisable(false);
            });
        });
        btnListaAlunos.setOnAction(actionEvent -> {
            GestaoCandidaturaMensagemListar gcml = new GestaoCandidaturaMensagemListar();
            this.setCenter(gcml);
            gcml.btn1.setOnAction(actionEvent1 -> {
                this.getChildren().remove(gcml);
                GestaoCandidaturaListarAlunos gcla = new GestaoCandidaturaListarAlunos(phaseManager.queryAluno(Queries.ALUNOS_COM_AUTOPROPOSTA));
                this.setCenter(gcla);
                changeButtonsDisable(true);
                gcla.btnVoltar.setOnAction(actionEvent2 -> {
                    this.getChildren().remove(gcla);
                    changeButtonsDisable(false);
                });
            });
            gcml.btn2.setOnAction(actionEvent1 -> {
                this.getChildren().remove(gcml);
                GestaoCandidaturaListarAlunos gcla = new GestaoCandidaturaListarAlunos(phaseManager.queryAluno(Queries.ALUNOS_COM_CANDIDATURA_REGISTADA));
                this.setCenter(gcla);
                changeButtonsDisable(true);
                gcla.btnVoltar.setOnAction(actionEvent2 -> {
                    this.getChildren().remove(gcla);
                    changeButtonsDisable(false);
                });
            });
            gcml.btn3.setOnAction(actionEvent1 -> {
                this.getChildren().remove(gcml);
                GestaoCandidaturaListarAlunos gcla = new GestaoCandidaturaListarAlunos(phaseManager.queryAluno(Queries.ALUNOS_SEM_CANDIDATURA));
                this.setCenter(gcla);
                changeButtonsDisable(true);
                gcla.btnVoltar.setOnAction(actionEvent2 -> {
                    this.getChildren().remove(gcla);
                    changeButtonsDisable(false);
                });
            });
        });
        btnListaPropostas.setOnAction(actionEvent -> {
            GestaoCandidaturaMensagemPropostas gcmp = new GestaoCandidaturaMensagemPropostas();
            this.setCenter(gcmp);
        });
        btnEditar.setOnAction(actionEvent -> {
            GestaoCandidaturaEditar gce = new GestaoCandidaturaEditar(phaseManager.queryAluno(),phaseManager.queryProposta(),phaseManager.queryCandidatura());
            this.setCenter(gce);
            changeButtonsDisable(true);
            gce.btnProcessar.setOnAction(actionEvent2 -> {
                if(gce.cbNumEstudante.getSelectionModel().getSelectedIndex()>0){
                    Long nEstudante = Long.parseLong(gce.cbNumEstudante.getSelectionModel().getSelectedItem());
                    List<Aluno> a = phaseManager.queryAluno();
                    Aluno al = null;
                    for(Aluno aluno:a)if(aluno.getNumEstudante()==nEstudante)al=aluno;
                    if(al!=null){
                        Candidatura c = new Candidatura(al,gce.selected);
                        phaseManager.editCandidatura(c);
                    }
                }
                this.getChildren().remove(gce);
                changeButtonsDisable(false);
            });
            gce.btnVoltar.setOnAction(actionEvent2 -> {
                this.getChildren().remove(gce);
                changeButtonsDisable(false);
            });
            this.setCenter(gce);
        });
        btnEliminar.setOnAction(actionEvent -> {
            GestaoCandidaturaApagar gca = new GestaoCandidaturaApagar(phaseManager.queryAluno(),phaseManager.queryProposta(),phaseManager.queryCandidatura());
            this.setCenter(gca);
            changeButtonsDisable(true);
            gca.btnProcessar.setOnAction(actionEvent2 -> {
                if(gca.cbNumEstudante.getSelectionModel().getSelectedIndex()>0){
                    phaseManager.deleteCandidatura(gca.cbNumEstudante.getSelectionModel().getSelectedItem());
                }
                this.getChildren().remove(gca);
                changeButtonsDisable(false);
            });
            gca.btnVoltar.setOnAction(actionEvent2 -> {
                this.getChildren().remove(gca);
                changeButtonsDisable(false);
            });
            this.setCenter(gca);
        });
        btnExportar.setOnAction(actionEvent -> {
            GestaoCandidaturaExportar gce = new GestaoCandidaturaExportar();
            gce.btn1.setOnAction(actionEvent1 -> {
                phaseManager.export(gce.nomeFicheiro.getText());
                this.getChildren().remove(gce);
                changeButtonsDisable(false);
            });
            gce.btnVoltar.setOnAction(actionEvent1 -> {
                this.getChildren().remove(gce);
                changeButtonsDisable(false);
            });
            this.setCenter(gce);
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

    private void createViews() {
        this.setMinWidth(1200);
        this.setMinHeight(600);
        this.setStyle("-fx-background-color: #3BCEAC");
        btnQuit.setText("QUIT");
        btnQuit.setMinWidth(80);
        btnQuit.setMaxWidth(80);
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
        btnInserir.setText("INSERIR");
        btnInserir.setMinHeight(50);
        btnInserir.setMinWidth(90);
        btnInserir.setMaxWidth(90);
        btnInserir.setStyle("-fx-font-size: 15px;-fx-font-weight: bold;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
        btnConsulta.setText("CONSULTAR");
        btnConsulta.setMinHeight(50);
        btnConsulta.setMinWidth(115);
        btnConsulta.setMaxWidth(115);
        btnConsulta.setStyle("-fx-font-size: 15px;-fx-font-weight: bold;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
        btnListaAlunos.setText("LISTAR ALUNOS");
        btnListaAlunos.setMinHeight(50);
        btnListaAlunos.setMinWidth(140);
        btnListaAlunos.setMaxWidth(140);
        btnListaAlunos.setStyle("-fx-font-size: 15px;-fx-font-weight: bold;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
        btnListaPropostas.setText("LISTAR PROPOSTAS");
        btnListaPropostas.setMinHeight(50);
        btnListaPropostas.setMinWidth(170);
        btnListaPropostas.setMaxWidth(170);
        btnListaPropostas.setStyle("-fx-font-size: 15px;-fx-font-weight: bold;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
        btnExportar.setText("EXPORTAR");
        btnExportar.setMinHeight(50);
        btnExportar.setMinWidth(105);
        btnExportar.setMaxWidth(105);
        btnExportar.setStyle("-fx-font-size: 15px;-fx-font-weight: bold;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
        btnVoltar.setText("VOLTAR");
        btnVoltar.setMinHeight(50);
        btnVoltar.setMinWidth(90);
        btnVoltar.setMaxWidth(90);
        btnVoltar.setStyle("-fx-font-size: 15px;-fx-font-weight: bold;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
        btnEliminar.setText("ELIMINAR");
        btnEliminar.setMinHeight(50);
        btnEliminar.setMinWidth(100);
        btnEliminar.setMaxWidth(100);
        btnEliminar.setStyle("-fx-font-size: 15px;-fx-font-weight: bold;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");
        btnEditar.setText("EDITAR");
        btnEditar.setMinHeight(50);
        btnEditar.setMinWidth(85);
        btnEditar.setMaxWidth(85);
        btnEditar.setStyle("-fx-font-size: 15px;-fx-font-weight: bold;-fx-border-radius: 15px;-fx-border-width: 2px;-fx-border-color: grey;-fx-background-radius: 15px;");


        /*this.setAlignment(Pos.CENTER);
        this.setSpacing(15);
        */
        //this.getChildren().addAll(btnGestAluno,btnGestProfessor,btnGestPropostas,btnAvancar,btnFecharFase,btnQuit);
        //butoes.setStyle("-fx-background-color: white");
        butoes.getChildren().addAll(btnInserir,btnConsulta,btnEditar,btnEliminar,btnListaAlunos,btnListaPropostas,btnExportar,btnVoltar,btnAvancar,btnFecharFase,btnQuit);
        this.setTop(butoes);
    }

    private void changeButtonsDisable(boolean d){
        if(phaseManager.getFechado()==1){
            btnAvancar.setDisable(d);
            btnQuit.setDisable(d);
            btnFecharFase.setDisable(d);
            btnConsulta.setDisable(d);
            btnInserir.setDisable(d);
            btnExportar.setDisable(d);
            btnListaAlunos.setDisable(d);
            btnListaPropostas.setDisable(d);
            btnVoltar.setDisable(d);
            btnEditar.setDisable(d);
            btnEliminar.setDisable(d);
        }else{
            btnConsulta.setDisable(d);
            btnListaAlunos.setDisable(d);
            btnListaPropostas.setDisable(d);
            btnVoltar.setDisable(d);
            btnExportar.setDisable(d);
            btnAvancar.setDisable(d);
            btnQuit.setDisable(d);
        }

    }
}
