package co.edu.uniquindio.envios.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EnvioApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(EnvioApp.class.getResource("/inicio.fxml"));
        Parent parent = loader.load();

        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("Envíos");
        stage.setResizable(false);
        stage.show();

    }

    public static void main(String[] args) {
        launch(EnvioApp.class, args);
    }

}
