package dk.easv.presentation.controller;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MovieBoxController {


    @FXML
    private ImageView imgPoster;
    @FXML
    private Label lblTitle;
    @FXML
    private Label lblYear;

    public void setLblTitle(String title){
        lblTitle.setText(title);
    }

    public String getTitle() {
        return lblTitle.getText().toLowerCase();
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
