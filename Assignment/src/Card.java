import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Card extends Application{

    @Override
    public void start(Stage stage) {
        stage.setTitle("Assignment 1");
        stage.setWidth(500);
        stage.setHeight(200);

        HBox hbox = new HBox();
        hbox.setSpacing(20);
        hbox.setPadding(new Insets(10, 10, 10, 10));

        Scene scene = new Scene(hbox);
        stage.setScene(scene);
        stage.show();
    }



}