package dk.easv.presentation.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class MovieBoxController {


    @FXML
    private ImageView imgPoster;

    @FXML
    private void btnBoxAction(ActionEvent actionEvent) throws IOException {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/MovieInfo.fxml"));
            root = loader.load();
            Stage primaryStage = new Stage();
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Movie Info");
            primaryStage.setResizable(false);
            primaryStage.getIcons().add(new Image("/CuteOtter.png"));
            primaryStage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
