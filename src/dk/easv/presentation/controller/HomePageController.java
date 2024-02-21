package dk.easv.presentation.controller;

import dk.easv.entities.Movie;
import dk.easv.presentation.model.AppModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HomePageController {

    private AppModel model;

    @FXML
    private HBox hBoxMoviesYouLike;
    @FXML
    private HBox hBoxOtherLike;
    @FXML
    private Label lblName;
    @FXML
    private AnchorPane paneSidebar;
    @FXML
    private HBox hBoxAnchorPanes;
    @FXML
    private TextField txfSearchBar;
    private boolean visible = false;

    public void setModel(AppModel model) throws IOException {
        this.model = model;
        model.loadData(model.getObsLoggedInUser());
        addMoviesYouLike();
        addOtherLike();
        setupSidePanel();
        txfSearchBarListener();
    }

    private void addMoviesYouLike() throws IOException {
        for (Movie movie : model.getObsTopMovieSeen()) {
            if (this.hBoxMoviesYouLike != null) {
                movieBox(movie, hBoxMoviesYouLike);
            }
        }
    }
    
    private void addOtherLike() throws IOException {
        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            movies.add(model.getObsTopMovieNotSeen().get(i));
        }
        for (Movie movie: movies) {
            if (this.hBoxOtherLike != null) {
                movieBox(movie, hBoxOtherLike);
            }
        }
    }

    private void movieBox(Movie movie, HBox hBoxToFill) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/MovieBox.fxml"));

        Button button = loader.load();
        MovieBoxController movieBoxController = loader.getController();

        // Directory where all our posters are
        String directoryPath = "resources/posters";

        File directory = new File(directoryPath);
        File[] files = directory.listFiles();

        // Sets a random poster for each movie
        String posterPath = null;
        if (files != null && files.length > 0) {
            Random random = new Random();
            File randomFile = files[random.nextInt(files.length)];
            posterPath = String.valueOf(randomFile);
            movieBoxController.setImage(posterPath);
        }
        // Sets title and year for each movie
        movieBoxController.setLblTitle(movie.getTitle());
        movieBoxController.setLblYear(String.valueOf(movie.getYear()));
        // Sets user data for the button, used for search function
        button.setUserData(movieBoxController);

        hBoxToFill.getChildren().addLast(button);
        addButtonClick(button,movie,posterPath);
    }

    @FXML
    private void addButtonClick(Button btn, Movie movie, String posterPath){
        btn.setOnAction(event -> {
            Parent root;
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MovieInfo.fxml"));
                root = loader.load();
                MovieInfoController movieInfoController = loader.getController();

                // Sets data for the MovieInfo window
                movieInfoController.setTxtTitle(movie.getTitle());
                movieInfoController.setTxtYear(String.valueOf(movie.getYear()));
                double rating = Math.round(movie.getAverageRating()*10)/10.0;
                movieInfoController.setTxtRating(String.valueOf(rating));
                movieInfoController.setImgPoster(posterPath);

                Stage primaryStage = new Stage();
                primaryStage.setScene(new Scene(root));
                primaryStage.setTitle("Movie Info");
                primaryStage.setResizable(false);
                primaryStage.getIcons().add(new Image("/icons/CuteOtter.png"));
                primaryStage.show();
            }
            catch (IOException e) {
                System.out.println("You are not so smart, huh?");
            }
        });
    }
    private void setupSidePanel() {
        hBoxAnchorPanes.getChildren().remove(paneSidebar);
        lblName.setText(model.getObsLoggedInUser().getName());
    }

    @FXML
    private void onClickOpenAccount() {
        if (!visible) {
            hBoxAnchorPanes.getChildren().addFirst(paneSidebar);
            paneSidebar.setVisible(true);
            visible = true;
        }
        else {
            hBoxAnchorPanes.getChildren().remove(paneSidebar);
            paneSidebar.setVisible(false);
            visible = false;
        }
    }

    private void txfSearchBarListener(){
        txfSearchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            // Hide movies if it's title isn't in the text field
            searchForMovies(hBoxMoviesYouLike);
            searchForMovies(hBoxOtherLike);
        });
    }

    private void searchForMovies(HBox movieHBox){
        for (Node node : movieHBox.getChildren()){
            if (node instanceof  Button movieButton){
                MovieBoxController movieBoxController = (MovieBoxController) movieButton.getUserData();
                boolean visible = movieBoxController.getTitle().contains(txfSearchBar.getText().toLowerCase());

                movieButton.setVisible(visible);
                movieButton.setManaged(visible);
            }
        }
    }
}
