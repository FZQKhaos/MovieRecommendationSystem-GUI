package dk.easv.presentation.controller;

import dk.easv.entities.Movie;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {

    @FXML
    private HBox hboxRecommended;
    @FXML
    private HBox hboxOtherLike;

    private void addRecommended() throws IOException {
        Movie movie1 = new Movie(1,"Potter", 1922);
        Movie movie2 = new Movie(2,"Potter", 1922);
        Movie movie3 = new Movie(3,"Potter", 1922);
        Movie movie4 = new Movie(4,"Potter", 1922);
        Movie movie5 = new Movie(5,"Potter", 1922);
        Movie movie6 = new Movie(6,"Potter", 1922);
        Movie movie7 = new Movie(7,"Potter", 1922);

        List<Movie> recommended = new ArrayList<>();
        recommended.add(movie1);
        recommended.add(movie2);
        recommended.add(movie3);
        recommended.add(movie4);
        recommended.add(movie5);
        recommended.add(movie6);
        recommended.add(movie7);

        for (Movie movie : recommended) {
            if (this.hboxRecommended!= null) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/FXML/MovieBox.fxml"));

                Button button = loader.load();
                hboxRecommended.getChildren().add(0, button);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            addRecommended();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
