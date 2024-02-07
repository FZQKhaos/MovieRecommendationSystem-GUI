package dk.easv.presentation.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

public class MovieInfoController {

    @FXML
    private void onActionBackToMainWindow(ActionEvent actionEvent) {
            Stage thisStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            thisStage.close();
        }
}
