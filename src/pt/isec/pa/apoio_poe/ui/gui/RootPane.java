package pt.isec.pa.apoio_poe.ui.gui;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import pt.isec.pa.apoio_poe.model.PhaseManager;

public class RootPane extends BorderPane {
    PhaseManager phaseManager;

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
        
    }

    private void createViews() {
        StackPane stackPane = new StackPane(
            new Phase1Menu(phaseManager),
            new GestaoAlunoMenu(phaseManager)
        );
        this.setCenter(stackPane);
    }
}
