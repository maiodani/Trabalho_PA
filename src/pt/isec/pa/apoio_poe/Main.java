package pt.isec.pa.apoio_poe;

import javafx.application.Application;
import pt.isec.pa.apoio_poe.model.fsm.PhaseContext;
import pt.isec.pa.apoio_poe.ui.gui.MainJFX;
import pt.isec.pa.apoio_poe.ui.text.UI;

public class Main {
    public static void main(String[] args) {
        /*PhaseContext fsm = new PhaseContext();
        UI ui = new UI(fsm);
        ui.start();*/
        Application.launch(MainJFX.class,args);
    }
}