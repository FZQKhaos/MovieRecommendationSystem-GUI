package dk.easv.presentation.controller;

import dk.easv.entities.Movie;
import dk.easv.presentation.model.AppModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

        ObservableList<Movie> obsSearchedMovies = appModel.getObsSearchedMovies();

        for (Movie m : obsSearchedMovies) {
            if (this.hboxFirstResults != null) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/fxml/MovieBox.fxml"));


            }
        }
    }




}
