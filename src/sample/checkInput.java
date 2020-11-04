package sample;

import javafx.scene.control.TextField;
import org.w3c.dom.Text;

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
                System.out.println(text.getText());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
