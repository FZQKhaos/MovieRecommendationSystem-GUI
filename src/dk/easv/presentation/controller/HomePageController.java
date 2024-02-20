package dk.easv.presentation.controller;

import dk.easv.entities.Movie;
import dk.easv.entities.User;
import dk.easv.presentation.model.AppModel;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

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
                button(movie, hboxRecommended);
            }
        }
    }

    private void button(Movie movie, HBox hboxToFill) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/FXML/MovieBox.fxml"));

        Button button = loader.load();
        MovieBoxController movieBoxController = loader.getController();

        String directoryPath = "resources/posters";

        File directory = new File(directoryPath);
        File[] files = directory.listFiles();

        String posterPath = null;
        if (files != null && files.length > 0) {
            Random random = new Random();
            File randomFile = files[random.nextInt(files.length)];
            posterPath = String.valueOf(randomFile);
            movieBoxController.setImage(posterPath);
        }
        movieBoxController.setLblTitle(movie.getTitle());
        movieBoxController.setLblYear(String.valueOf(movie.getYear()));
        hboxToFill.getChildren().addLast(button);
        addButtonClick(button,movie,posterPath);
    }

    private void addOtherLike() throws IOException {
        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            movies.add(model.getObsTopMovieNotSeen().get(i));
        }
        for (Movie movie: movies) {
            System.out.println(movie.getTitle() + " " + movie.getAverageRating());
            if (this.hboxOtherLike != null) {
                button(movie, hboxOtherLike);
            }
        }
    }

    private void addButtonClick(Button btn, Movie movie, String posterPath){
        btn.setOnAction(event -> {
            Parent root;
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/MovieInfo.fxml"));
                root = loader.load();
                MovieInfoController movieInfoController = loader.getController();
                movieInfoController.setTxtTitle(movie.getTitle());
                movieInfoController.setTxtYear(String.valueOf(movie.getYear()));
                movieInfoController.setImgPoster(posterPath);
                Stage primaryStage = new Stage();
                primaryStage.setScene(new Scene(root));
                primaryStage.setTitle("Movie Info");
                primaryStage.setResizable(false);
                primaryStage.getIcons().add(new Image("/ICONS/CuteOtter.png"));
                primaryStage.show();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
