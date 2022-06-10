package pt.isec.pa.apoio_poe.ui.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.isec.pa.apoio_poe.model.PhaseManager;

public class MainJFX extends Application {
    PhaseManager phaseManager;

    @Override
    public void init() throws Exception {
        phaseManager = new PhaseManager();
        super.init();
    }

    @Override
    public void start(Stage stage) throws Exception {
        RootPane root = new RootPane(phaseManager);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMinWidth(1200);
        stage.setMinHeight(600);
        stage.show();
    }
}
