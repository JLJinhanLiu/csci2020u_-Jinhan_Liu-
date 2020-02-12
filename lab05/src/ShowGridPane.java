import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ShowGridPane extends Application {

    TableView tableView = new TableView();

    @Override
    public void start(Stage stage) {
        stage.setTitle("Student Records");
        stage.setWidth(1000);
        stage.setHeight(500);

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

        VBox vbox = new VBox();
        vbox.setSpacing(20);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(tableView);

        Scene scene = new Scene(vbox);
        stage.setScene(scene);
        stage.show();
    }
}


