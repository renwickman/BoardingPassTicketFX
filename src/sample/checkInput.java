package sample;

import javafx.scene.control.TextField;
// not needed anymore. Keep in case.
public class checkInput extends Thread implements Runnable {
    TextField text;
    String regex;

    checkInput(TextField text, String regex) {
        this.text = text;
    }
    //can override
    public void start() {
        super.start();
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                if(text.getText().matches(regex))
                    text.setStyle("-fx-text-box-border: red;,-fx-focus-color: red;");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}