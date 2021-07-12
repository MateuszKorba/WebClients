package startScene;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class StartController implements Initializable {

    public static StartController startController;
    @FXML
    public Button clickButton;
    @FXML
    public TextField countryField;
    @FXML
    public TextField cityField;
    @FXML
    public TextField currencyField;

    public void confirmed(ActionEvent actionEvent) throws IOException {
        Stage stage = null;
        Parent root = null;
        if(actionEvent.getSource() == clickButton) {
            stage = (Stage) clickButton.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("/weatherScene/weather.fxml"));
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public String getCountryField() {
        return countryField.getText();
    }

    public String getCityField() {
        return cityField.getText();
    }

    public String getCurrencyField() {
        return currencyField.getText();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        startController = this;
    }
}
