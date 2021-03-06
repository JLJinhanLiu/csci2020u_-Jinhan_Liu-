import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class ShowFlowPane extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        FlowPane pane = new FlowPane();
        pane.setPadding(new Insets(11,12,13,14));
        pane.setVgap(50);
        pane.setHgap(50);

        pane.getChildren().addAll(new Label("First Name:") ,
                new TextField(), new Label("MI:"));
        TextField tfMi = new TextField();
        tfMi.setPrefColumnCount(1);
        pane.getChildren().addAll(tfMi, new Label("Last Name:"), new TextField());

        Scene scene = new Scene(pane, 200,200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("ShowFlowPane");
        primaryStage.show();

    }
}
