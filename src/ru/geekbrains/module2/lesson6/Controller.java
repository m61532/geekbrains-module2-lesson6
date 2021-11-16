package ru.geekbrains.module2.lesson6;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {
    public TextField chatTextField;
    public TextArea chatTextArea;
    public EchoClient client;

    public Controller() {
        client = new EchoClient(this);
    }

    public void sendMessage(ActionEvent actionEvent) {
        Object source = (Button) actionEvent.getSource();
        final String message = chatTextField.getText();
        chatTextField.clear();
        chatTextArea.appendText("You: " + message + "\n");
        if (message.isEmpty()) {
            return;
        }
        client.sendMessage(message);
        chatTextField.requestFocus();
    }

    public void addMessage(String message) {
        chatTextArea.appendText(message + "\n");
    }
}
