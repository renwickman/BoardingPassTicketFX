package sample;

import entity.Application;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


import java.awt.*;

public class Controller {


    //create (Name, Email, Age, Gender, When/What Time Leaving, Leaving From/To, ETA)
    @FXML
    private TextField name;
    @FXML
    private TextField email;
    @FXML
    private TextField age;
    @FXML
    private TextField phoneNumber;
    @FXML
    private DatePicker departDate;
    @FXML
    private RadioButton male;
    @FXML
    private RadioButton female;
    @FXML
    private MenuButton time;
    @FXML
    private Button enter;

    //update (Boarding Pass & Price)
    @FXML
    //optional used to execute any code before GUI loads
    public void initialize() {
        checkInput check = new checkInput(name, "test");
        check.start();
    }

    @FXML
    void pressEnter() {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Application.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();

        //check if everything is valid
        //if yes commit
        try {
            Application application = new Application();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("yo");
        System.out.println(name.getText());
    }


}
