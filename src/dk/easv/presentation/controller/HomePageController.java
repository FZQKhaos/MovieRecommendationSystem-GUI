package dk.easv.presentation.controller;

import dk.easv.entities.Movie;
import dk.easv.entities.User;
import dk.easv.presentation.model.AppModel;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class HomePageController {

    private AppModel model;

    @FXML
    private HBox hboxRecommended;
    @FXML
    private HBox hboxOtherLike;

    public void setModel(AppModel model) throws IOException {
        this.model = model;
        model.loadData(model.getObsLoggedInUser());
        addRecommended();
        addOtherLike();
    }

    private void addRecommended() throws IOException {
        for (Movie movie : model.getObsTopMovieSeen()) {
            if (this.hboxRecommended != null) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/FXML/MovieBox.fxml"));

                Button button = loader.load();
                MovieBoxController movieBoxController = loader.getController();

                String directoryPath = "resources/posters";

                File directory = new File(directoryPath);
                File[] files = directory.listFiles();

                if (files != null && files.length > 0) {
                    Random random = new Random();
                    File randomFile = files[random.nextInt(files.length)];
                    movieBoxController.setImage(String.valueOf(randomFile));
                }
                movieBoxController.setLblTitle(movie.getTitle());
                movieBoxController.setLblYear(String.valueOf(movie.getYear()));
                hboxRecommended.getChildren().addLast(button);
            }
        }
    }
    private void addOtherLike() throws IOException {
        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            movies.add(model.getObsTopMovieNotSeen().get(i));
        }
        for (Movie movie: movies) {
            System.out.println(movie.getTitle() + " " + movie.getAverageRating());
            if (this.hboxOtherLike != null) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/FXML/MovieBox.fxml"));

                Button button = loader.load();
                MovieBoxController movieBoxController = loader.getController();

                String directoryPath = "resources/posters";

                File directory = new File(directoryPath);
                File[] files = directory.listFiles();

                if (files != null && files.length > 0) {
                    Random random = new Random();
                    File randomFile = files[random.nextInt(files.length)];
                    movieBoxController.setImage(String.valueOf(randomFile));
                }
                movieBoxController.setLblTitle(movie.getTitle());
                movieBoxController.setLblYear(String.valueOf(movie.getYear()));
                hboxOtherLike.getChildren().addLast(button);
            }
        }
    }
}
