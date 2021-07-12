/**
 *
 *  @author Korba Mateusz S21347
 *
 */

package web1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception{
    Parent root = FXMLLoader.load(getClass().getResource("../startScene/start.fxml"));
    primaryStage.setTitle("TPO2 s21347");
    primaryStage.setScene(new Scene(root, 950, 600));
    primaryStage.show();
  }

  public static void main(String[] args) {
    Service s = new Service("France");
    String weatherJson = s.getWeather("Paris");
    Double rate1 = s.getRateFor("USD");
    Double rate2 = s.getNBPRate();
    launch(args);
  }

}