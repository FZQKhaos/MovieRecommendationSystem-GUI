package dk.easv.presentation.controller;

import dk.easv.entities.Movie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MovieBoxController {


    @FXML
    private ImageView imgPoster;
    @FXML
    private Label lblTitle;
    @FXML
    private Label lblYear;

    @FXML
    private void btnBoxAction(ActionEvent actionEvent) throws IOException {

    }

    public void setLblTitle(String title){
        lblTitle.setText(title);
    }

    public void setLblYear(String year){
        lblYear.setText(year);
    }

    public void setImage(String URL) throws FileNotFoundException {
        Image newImage = new Image(new FileInputStream(URL));
        imgPoster.setFitWidth(200);
        imgPoster.setFitHeight(200);
        imgPoster.setPreserveRatio(false);

        imgPoster.setImage(newImage);
    }
}
