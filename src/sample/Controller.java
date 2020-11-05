package sample;

import com.mysql.cj.util.TestUtils;
import entity.Application;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;


public class Controller {
    final String nameRegex = ".*\\S+.*";
    final String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
    final String ageRegex = "[0-9]{1,3}+";
    final String phoneRegex = "[0-9]{10}+";
    final String invalid = "-fx-border-color: red; -fx-focus-color: red;";
    ToggleGroup groupGender;
    SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
            .addAnnotatedClass(Application.class)
            .buildSessionFactory();

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

    @FXML
    //Used to execute code before GUI loads
    public void initialize() {
        groupGender = new ToggleGroup();
        male.setToggleGroup(groupGender);
        female.setToggleGroup(groupGender);
        male.setSelected(true);
        departLocation.getItems().addAll("New York", "Los Angeles", "Phoenix", "Indianapolis", "Detroit");
        arriveLocation.getItems().addAll("New York", "Los Angeles", "Phoenix", "Indianapolis", "Detroit");
        time.getItems().addAll("6:00 AM", "8:00 AM", "10:00 AM", "12:00 PM", "2:00 PM", "4:00 PM", "6:00 PM", "8:00 PM", "10:00 PM");
        setStyle(name, nameRegex);
        setStyle(email, emailRegex);
        setStyle(age, ageRegex);
        setStyle(phoneNumber, phoneRegex);
        setStyle(time);
        setStyle(departLocation);
        setStyle(arriveLocation);
        setStyle(departDate);
        departDate.setDayCellFactory(d ->
                new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        setDisable(item.isBefore(LocalDate.now()));
                    }
                });
    }

    @FXML
        //enter button
    void pressEnter() {
        if (verifyInputs()) {
            Session session = factory.getCurrentSession();
            try {//build entity
                Application application = new Application();
                application.setName(name.getText());
                application.setAge(Integer.parseInt(age.getText()));
                application.setGender(((RadioButton) groupGender.getSelectedToggle()).getText().substring(0, 1));
                application.setPhone(Long.parseLong(phoneNumber.getText()));
                application.setEmail(email.getText());
                application.setOrigin(String.valueOf(departLocation.getValue()));
                application.setDepartDate(String.valueOf(departDate.getValue()));
                application.setDepartTime(String.valueOf(time.getValue()));
                application.setDestination(String.valueOf(arriveLocation.getValue()));
                session.beginTransaction();
                session.save(application);
                session.getTransaction().commit();
                reset();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                session.close();
            }
        }
    }

    //Looks at each part of the form for validity.
    boolean verifyInputs() {

        return valid(name.getText(), nameRegex) +
                valid(email.getText(), emailRegex) +
                valid(phoneNumber.getText(), phoneRegex) +
                valid(age.getText(), ageRegex) +
                valid(departDate) +
                valid(time) +
                verifyLocations() == 0;
    }

    //check to see if depart and arrive locations are different
    int verifyLocations() {
        return (departLocation.getValue() != null && arriveLocation.getValue() != null && departLocation.getValue().equals(arriveLocation.getValue())) ? 1 : 0;
    }

    //compares strings against a regex, used for TextFields
    int valid(String text, String regex) {
        return text.matches(regex) ? 0 : 1;
    }

    //checks if an object is null. Used for DatePicker and ChoiceBox
    int valid(Object object) {
        return object == null ? 1 : 0;
    }

    //Visual feedback to show invalid text
    void setStyle(TextField textField, String regex) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            textField.setStyle(!newValue.matches(regex) ? invalid : "");
        });
    }

    //Visual feedback when a date picker is null
    void setStyle(DatePicker datePicker) {
        datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            datePicker.setStyle(newValue == null ? invalid : "");
        });
    }

    //Visual feedback when a choice box is null
    void setStyle(ChoiceBox choiceBox) {
        choiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            choiceBox.setStyle(newValue == null ? invalid : "");
            //Outline depart and arrive in red when the same, meaning invalid
            //weird place to put it as it will get called with time box too. OK for now.
            if (verifyLocations() == 1) {
                departLocation.setStyle(invalid);
                arriveLocation.setStyle(invalid);
            } else {
                departLocation.setStyle(departLocation.getValue() == null ? invalid : "");
                arriveLocation.setStyle(arriveLocation.getValue() == null ? invalid : "");
            }
        });
    }

    //wipes UI after a successful database transaction
    void reset() {
        name.setText("");
        email.setText("");
        age.setText("");
        phoneNumber.setText("");
        departDate.setValue(null);
        departLocation.setValue(null);
        arriveLocation.setValue(null);
        time.setValue(null);
        male.setSelected(true);

    }

}
