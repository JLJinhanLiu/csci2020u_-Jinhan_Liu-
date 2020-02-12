import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.commons.validator.routines.*;

import javax.swing.text.Caret;

public class ShowGridPane extends Application {
    //check if phone number is int onlyw
    public boolean checkPhoneNumberSymbols(String input) {
        for (char i : input.toCharArray()) {
            if (i < 48 || i > 57)
                return false;
        }
        return true;
    }

    //check if phone number has 10 digits
    public boolean checkPhoneNumberLength(String input) {
        int counter = 0;
        for (char i : input.toCharArray()) {
            if (i < 48 || i > 57)
                continue;
            counter++;
        }
        return counter == 10;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(20, 100, 20, 20));
        pane.setVgap(15);
        pane.setHgap(10);

        //initialize text field
        TextField username = new TextField();
        TextField password = new PasswordField();
        TextField fullname = new TextField();
        TextField email = new TextField();
        TextField phone = new TextField();
        DatePicker dob = new DatePicker();

        //special setting for these labels to avoid text wrapping
        Label usernameLabel = new Label("Username:");
        usernameLabel.setMinWidth(20);
        Label dobLabel = new Label("Date of Birth:");
        dobLabel.setMinWidth(80);
        //add labels to scene
        pane.add(usernameLabel, 0, 0);
        pane.add(username, 1, 0);
        pane.add(new Label("Password:"), 0, 1);
        pane.add(password, 1, 1);
        pane.add(new Label("Full Name:"), 0, 2);
        pane.add(fullname, 1, 2);
        pane.add(new Label("E-Mail:"), 0, 3);
        pane.add(email, 1, 3);
        pane.add(new Label("Phone #:"), 0, 4);
        pane.add(phone, 1, 4);
        pane.add(dobLabel, 0, 5);
        pane.add(dob, 1, 5);

        //special label for email validation
        Label emailInvalid = new Label("Invalid E-Mail.");
        emailInvalid.setMinWidth(80);
        pane.add(emailInvalid, 2, 3);
        emailInvalid.setOpacity(0);

        //special label for invalid submission
        Label submissionInvalid = new Label("Invalid Submission.");
        submissionInvalid.setMinHeight(50);
        submissionInvalid.setTextFill(Color.color(1,0,0));
        pane.add(submissionInvalid, 1, 7);
        submissionInvalid.setOpacity(0);
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(2500),
                ae -> submissionInvalid.setOpacity(0)));
        //if changes are detected in email textfield, check validity
        email.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue,
                                String s, String t1) {
                if (EmailValidator.getInstance().isValid(email.getText()) || email.getText().length() == 0) {
                    emailInvalid.setOpacity(0);
                } else {
                    emailInvalid.setOpacity(100);
                }
            }
        });

        //if changes detected in the phone field, reformat (or not)
        java.text.MessageFormat phoneFormat = new java.text.MessageFormat("({0})-{1}-{2}");
        phone.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                                String oldValue, String newValue) {
                //System.out.println(oldValue + "\t" + newValue);//debug
                //if 10 digits, all numbers
                if (checkPhoneNumberLength(phone.getText()) && checkPhoneNumberSymbols(phone.getText())) {
                    String unformattedNumber = phone.getText();
                    String[] formattedNumber = {unformattedNumber.substring(0, 3),
                            unformattedNumber.substring(3, 6),
                            unformattedNumber.substring(6)};//cut number into 3 parts as a list
                    Platform.runLater(() -> {
                        phone.setText(phoneFormat.format(formattedNumber));//replace content in textfield
                    });
                } else {
                    if (!checkPhoneNumberLength(phone.getText()) ||
                            (checkPhoneNumberLength(phone.getText()) && phone.getText().length() != 14)) {
                        String revertText = "";
                        for (char i : phone.getText().toCharArray()) {
                            if (i >= 48 && i <= 57)
                                revertText += i;
                        }
                        String finalRevertText = revertText;
                        Platform.runLater(() -> {
                            phone.setText(finalRevertText);
                        });
                    }
                }
                Platform.runLater(() -> {
                    phone.positionCaret(phone.getLength());
                });
            }
        });
        //add button
        Button btAdd = new Button("Register");
        pane.add(btAdd, 1, 6);
        GridPane.setHalignment(btAdd, HPos.LEFT);
        //on button press
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if (EmailValidator.getInstance().isValid(email.getText())
                    && username.getText().length() != 0
                    && password.getText().length() != 0
                    && fullname.getText().length() != 0
                    && phone.getText().length() == 14
                    && dob.getEditor().getText().length() != 0) {
                    //only continue if all fields are valid
                    System.out.println("Username: " + username.getText());
                    System.out.println("Password: " + password.getText());
                    System.out.println("Full Name: " + fullname.getText());
                    System.out.println("E-Mail: " + email.getText());
                    System.out.println("Phone #: " + phone.getText());
                    System.out.println("DOB: " + dob.getEditor().getText());
                    username.clear();
                    password.clear();
                    fullname.clear();
                    email.clear();
                    phone.clear();
                    dob.getEditor().clear();
                    submissionInvalid.setOpacity(0);
                }
                else{
                    submissionInvalid.setOpacity(100);
                    timeline.play();
                }
            }
        };
        btAdd.setOnAction(event);

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Registration");
        primaryStage.show();
    }
}
