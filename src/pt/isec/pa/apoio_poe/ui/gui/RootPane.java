package pt.isec.pa.apoio_poe.ui.gui;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import pt.isec.pa.apoio_poe.model.PhaseManager;
import pt.isec.pa.apoio_poe.ui.gui.Phase1UI.GestaoAlunoUI.GestaoAlunoMenu;
import pt.isec.pa.apoio_poe.ui.gui.Phase2UI.Phase2Menu;
import pt.isec.pa.apoio_poe.ui.gui.Phase1UI.GestaoDocenteUI.GestaoDocenteMenu;
import pt.isec.pa.apoio_poe.ui.gui.Phase1UI.GestaoPropostasUI.GestaoPropostasMenu;
import pt.isec.pa.apoio_poe.ui.gui.Phase1UI.Phase1Menu;
import pt.isec.pa.apoio_poe.ui.gui.Phase3UI.GestaoAutomaticoUI.GestaoAutomaticoEmpate;
import pt.isec.pa.apoio_poe.ui.gui.Phase3UI.GestaoAutomaticoUI.GestaoAutomaticoMenu;
import pt.isec.pa.apoio_poe.ui.gui.Phase3UI.GestaoManualUI.GestaoManualMenu;
import pt.isec.pa.apoio_poe.ui.gui.Phase3UI.Phase3Menu;
import pt.isec.pa.apoio_poe.ui.gui.Phase4UI.Phase4Menu;
import pt.isec.pa.apoio_poe.ui.gui.Phase5UI.Phase5Menu;

public class RootPane extends BorderPane {
    PhaseManager phaseManager;
    MensagemSave mensagemSave = new MensagemSave();
    public RootPane(PhaseManager phaseManager) {
        this.phaseManager = phaseManager;
        this.setMinWidth(1200);
        this.setMinHeight(600);
        createViews();
        registerHandlers();
        update();
    }

    private void update() {
    }

    private void registerHandlers() {
        mensagemSave.btnNao.setOnAction(actionEvent -> {
            this.getChildren().remove(mensagemSave);
            StackPane stackPane = new StackPane(
                    new Phase1Menu(phaseManager),
                    new GestaoAlunoMenu(phaseManager),
                    new GestaoDocenteMenu(phaseManager),
                    new GestaoPropostasMenu(phaseManager),
                    new Phase2Menu(phaseManager),
                    new Phase3Menu(phaseManager),
                    new GestaoManualMenu(phaseManager),
                    new GestaoAutomaticoMenu(phaseManager),
                    new GestaoAutomaticoEmpate(phaseManager),
                    new Phase4Menu(phaseManager),
                    new Phase5Menu(phaseManager)
            );
            this.setCenter(stackPane);
        });
        mensagemSave.btnSim.setOnAction(actionEvent -> {
            this.getChildren().remove(mensagemSave);
            phaseManager.readBin();
            StackPane stackPane = new StackPane(
                    new Phase1Menu(phaseManager),
                    new GestaoAlunoMenu(phaseManager),
                    new GestaoDocenteMenu(phaseManager),
                    new GestaoPropostasMenu(phaseManager),
                    new Phase2Menu(phaseManager),
                    new Phase3Menu(phaseManager),
                    new GestaoManualMenu(phaseManager),
                    new GestaoAutomaticoMenu(phaseManager),
                    new GestaoAutomaticoEmpate(phaseManager),
                    new Phase4Menu(phaseManager),
                    new Phase5Menu(phaseManager)
            );
            this.setCenter(stackPane);
        });
    }

    private void createViews() {
        this.setCenter(mensagemSave);
    }
}
