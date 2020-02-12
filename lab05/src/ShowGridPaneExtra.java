import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ShowGridPaneExtra extends Application {

    TableView tableView = new TableView();

    @Override
    public void start(Stage stage) {
        stage.setTitle("Student Records");
        stage.setWidth(1000);
        stage.setHeight(600);

        TableColumn<String, StudentRecord> IDCol = new TableColumn<>("SID");
        IDCol.setCellValueFactory(new PropertyValueFactory<>("studentID"));

        TableColumn<String, StudentRecord> assignmentsCol = new TableColumn<>("Assignments");
        assignmentsCol.setCellValueFactory(new PropertyValueFactory<> ("assignments"));

        TableColumn<String, StudentRecord> midtermCol = new TableColumn<>("Midterm");
        midtermCol.setCellValueFactory(new PropertyValueFactory<> ("midterm"));

        TableColumn<String, StudentRecord> finalExamCol = new TableColumn<>("Final Exam");
        finalExamCol.setCellValueFactory(new PropertyValueFactory<> ("finalExam"));

        TableColumn<String, StudentRecord> finalMarkCol = new TableColumn<>("Final Mark");
        finalMarkCol.setCellValueFactory(new PropertyValueFactory<> ("finalMark"));

        TableColumn<String, StudentRecord> letterGradeCol = new TableColumn<>("Letter Grade");
        letterGradeCol.setCellValueFactory(new PropertyValueFactory<> ("letterGrade"));

        tableView.setItems(new DataSource().getAllMarks());
        tableView.getColumns().addAll(IDCol, assignmentsCol, midtermCol, finalExamCol,
                finalMarkCol, letterGradeCol);

        //extra stuff
        TextField textFieldID = new TextField();
        TextField textFieldAssignments = new TextField();
        TextField textFieldMidterm = new TextField();
        TextField textFieldFinalExam = new TextField();

        Button button = new Button("Add");

        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10, 0, 0, 10));

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER_LEFT);
        gridPane.setHgap(30);
        gridPane.setVgap(10);

        gridPane.add(new Label("SID:"), 0, 0);
        gridPane.add(textFieldID, 1, 0);
        gridPane.add(new Label("Assignments:"), 2,0);
        gridPane.add(textFieldAssignments, 3, 0);
        gridPane.add(new Label("Midterm:"), 0,1);
        gridPane.add(textFieldMidterm, 1, 1);
        gridPane.add(new Label("Final Exam:"), 2,1);
        gridPane.add(textFieldFinalExam, 3, 1);
        gridPane.add(button, 1, 2);

        Label submissionInvalid = new Label("Invalid Submission.");
        submissionInvalid.setMinHeight(50);
        submissionInvalid.setTextFill(Color.color(1,0,0));
        gridPane.add(submissionInvalid, 2, 2);
        submissionInvalid.setOpacity(0);
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(2500),
                ae -> submissionInvalid.setOpacity(0)));

        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if ( textFieldID.getText().length() != 0
                        && textFieldAssignments.getText().length() != 0
                        && textFieldFinalExam.getText().length() != 0
                        && textFieldMidterm.getText().length() != 0) {
                    //only continue if all fields are valid
                    tableView.getItems().add(new StudentRecord(textFieldID.getText(),
                            Float.parseFloat(textFieldAssignments.getText()),
                            Float.parseFloat(textFieldMidterm.getText()),
                            Float.parseFloat(textFieldFinalExam.getText())));

                    textFieldID.clear();
                    textFieldAssignments.clear();
                    textFieldFinalExam.clear();
                    textFieldMidterm.clear();
                    submissionInvalid.setOpacity(0);
                }
                else{
                    submissionInvalid.setOpacity(100);
                    timeline.play();
                }
            }
        };
        button.setOnAction(event);

        vbox.getChildren().addAll(tableView, gridPane);
        Scene scene = new Scene(vbox);
        stage.setScene(scene);
        stage.show();
    }
}


