import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ShowHBoxVBox extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane pane = new BorderPane();

        pane.setBottom(button());
        pane.setLeft(list());

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Registration");
        primaryStage.show();
    }

    private HBox button(){
        HBox hBox = new HBox(15);
        hBox.setPadding(new Insets(15,15,15,15));
        hBox.getChildren().add(new Button("Register"));

        return hBox;
    }

    private VBox list(){
        VBox listBox = new VBox(15);
        listBox.setPadding(new Insets(15,15,15,15));

        Label[] items = {new Label("Username:"), new Label("Password:") ,
                new Label("Full Name:"), new Label("E-Mail:"),
                new Label("Phone #:"), new Label("Date of Birth:")};

        for (Label item :items){
            VBox.setMargin(item, new Insets(0,0,0,15));
            listBox.getChildren().add(item);
        }
        return listBox;
    }

//    private VBox entry(){
//        VBox entryBox = new VBox(15);
//        entryBox.setPadding(new Insets(15,15,15,15));
//
//
//    }
}
