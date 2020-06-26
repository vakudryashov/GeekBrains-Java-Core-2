package Lesson_04;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.*;

public class JavaFxController implements Initializable {
    @FXML
    TextArea publicFrame;
    @FXML
    TextField userInput;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void sendMessage(){
        publicFrame.appendText(userInput.getText() + "\n");
        userInput.clear();
        userInput.requestFocus();
    }
}
