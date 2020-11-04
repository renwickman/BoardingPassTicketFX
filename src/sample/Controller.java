package sample;

import entity.Application;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    private ChoiceBox time;
    @FXML
    private ChoiceBox departLocation;
    @FXML
    private ChoiceBox arriveLocation;
    @FXML
    private Button enter;

    ArrayList<TextField> textFields = new ArrayList<>();
    ToggleGroup groupGender;
    //update (Boarding Pass & Price)
    @FXML
    //optional used to execute any code before GUI loads
    public void initialize() {
        checkInput check = new checkInput(name, "test");
        check.start();
        groupGender = new ToggleGroup();
        male.setToggleGroup(groupGender);
        female.setToggleGroup(groupGender);
    }

    @FXML
    void pressEnter() {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Application.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();

        //check if everything is valid
        //if yes commit

        if (textFilled() && dateChosen() && timeChosen()){
            try {
                Application application = new Application();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("yo");
        System.out.println(name.getText());
    }

    boolean textFilled(){
        int count = 0;
        count += valid(name.getText(), ".\\S+.");
        count += valid(email.getText(), "^[A-Za-z0-9+_.-]+@(.+)$");
        count += valid(phoneNumber.getText(), "[0-9]{10}+");
        count += valid(age.getText(), "[0-9]{1,3}+");
        return count == 0;
    }

    boolean dateChosen(){
        return departDate.getValue() != null;
    }

    boolean timeChosen(){
        System.out.println(time.isShowing());
        return time.isShowing();
    }

    int valid(String text, String regex) {
        if (text.matches(regex)) {
            return 0;
        }
        return 1;
    }




}
