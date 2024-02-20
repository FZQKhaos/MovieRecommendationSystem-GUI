package dk.easv.presentation.controller;

import dk.easv.presentation.model.AppModel;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LogInController implements Initializable {
    @FXML private MFXPasswordField passwordField;
    @FXML private TextField userId;
    private AppModel model;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        model = new AppModel();
        userId.setText("Bezalel Simmel");
    }

    @FXML
    public void login(ActionEvent actionEvent) {
        model.loadUsers();
        model.loginUserFromUsername(userId.getText());
        if(model.getObsLoggedInUser()!=null){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/HomePage.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Movie Recommendation System");
            stage.setResizable(false);
            stage.getIcons().add(new Image("/icons/CuteOtter.png"));
            stage.show();

            //AppController controller = loader.getController();
            //controller.setModel(model);

            HomePageController controller = loader.getController();
            controller.setModel(model);

            Stage thisStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            thisStage.close();
            
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Could not load HomePage.fxml");
            alert.showAndWait();
        }

        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR, "Wrong username or password");
            alert.showAndWait();
        }
    }

    public void signUp(ActionEvent actionEvent) {
        System.out.println("Sign-Up");
    }

}
