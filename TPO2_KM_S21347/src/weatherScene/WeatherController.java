package weatherScene;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import web1.Service;
import web1.Weather;

import static startScene.StartController.startController;

public class WeatherController implements Initializable {

    @FXML
    public Label currencyValue1;
    @FXML
    public Label currencyValue2;
    @FXML
    public Label weathertValue;
    @FXML
    public WebView webView;
    @FXML
    public Label countryChoseName;
    @FXML
    public Button backButton;
    @FXML
    public Label cityChoseName;
    Service service;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        service = new Service(startController.countryField.getText());
        String cityName = startController.cityField.getText();
        String currencyName = startController.currencyField.getText();
        countryChoseName.setText(startController.countryField.getText());
        cityChoseName.setText(cityName);

        //Currency
        currencyValue1.setText("1 "+ service.getCurrency()+ " = " +service.getRateFor(currencyName.toUpperCase())+" "+ currencyName);
        currencyValue2.setText("1 "+service.getCurrency()+ " = " +service.getNBPRate() + " PLN");

        //Weather
        weathertValue.setText(new Weather(service.getWeather(service.getCity()), service.getCity(), service.getCountry()).toString());

        //webView
        WebEngine webEngine = webView.getEngine();
        webEngine.load("https://en.wikipedia.org/wiki/" + service.getCountry());
    }
    public void back(ActionEvent actionEvent) throws IOException {
        Stage stage = null;
        Parent root = null;
        if(actionEvent.getSource() == backButton) {
            stage = (Stage) backButton.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("/startScene/start.fxml"));
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
