package dk.easv.presentation.controller;

import dk.easv.entities.Movie;
import dk.easv.presentation.model.AppModel;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {

    private AppModel model;

    @FXML
    private HBox hboxRecommended;
    @FXML
    private HBox hboxOtherLike;

    public void setModel(AppModel model) throws IOException {
        this.model = model;
        System.out.println(model.getObsLoggedInUser());
    }

    private void addRecommended() throws IOException {

        for (Movie ignored : model.getObsTopMovieSeen()) {
            if (this.hboxRecommended != null) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/FXML/MovieBox.fxml"));

                Button button = loader.load();
                hboxRecommended.getChildren().addFirst(button);
            }
        }

        /* Use this to get a random poster!!
        String directoryPath = "C:\\Users\\Jeppe\\Desktop\\MovieRecommendationSystem-GUI\\resources\\posters";

        File directory = new File(directoryPath);
        File[] files = directory.listFiles();

        if (files != null && files.length > 0) {
            Random random = new Random();
            File randomFile = files[random.nextInt(files.length)];
            System.out.println(randomFile);
        }
        */
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
