package sample.view;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sample.controller.Controller;

import java.io.File;

public class View  extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("image" + File.separator + "home.png")));
        ViewNavigator.setStage(primaryStage);
        ViewNavigator.loadScene("Who Are You Renting From?", new MainScene());
    }

    @Override
    public void stop() throws Exception
    {
        Controller.getInstance().savaData();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
