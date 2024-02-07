package dk.easv.presentation.controller;

import dk.easv.presentation.model.AppModel;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;

public class SearchingPaneController {

    @FXML
    private HBox hboxFirstResults;
    @FXML
    private ScrollPane spSearchPane;

    private AppModel appModel;

    public SearchingPaneController() {
        appModel = new AppModel();


    }




}
