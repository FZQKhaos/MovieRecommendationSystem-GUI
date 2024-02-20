package dk.easv.presentation.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MovieInfoController {

    @FXML
    private Text txtTitle;
    @FXML
    private Text txtYear;
    @FXML
    private ImageView imgPoster;

    @FXML
    private void onActionBackToMainWindow(ActionEvent actionEvent) {
            Stage thisStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            thisStage.close();
    }

    public void setTxtTitle(String title) {
        txtTitle.setText(title);
    }

    public void setTxtYear(String year) {
        txtYear.setText(year);
    }

    public void setImgPoster(String URL) throws FileNotFoundException {
        Image newImage = new Image(new FileInputStream(URL));
        imgPoster.setFitWidth(554);
        imgPoster.setFitHeight(680);
        imgPoster.setPreserveRatio(false);

        imgPoster.setImage(newImage);
    }
}
