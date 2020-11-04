package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;


public class Controller {
    //create (Name, Email, Age, Gender, When/What Time Leaving, Leaving From/To, ETA)
    private TextField name;
    private TextField email;
    private TextField age;
    private TextField phoneNumber;
    private DatePicker departDate;
    private RadioButton male;
    private RadioButton female;
    private MenuButton departTime;
    private Button enter;

    //update (Boarding Pass & Price)
    @FXML
    //optional - used to execute any code before GUI loads
    public void initialize(){

    }

    @FXML
    void pressEnter(){
        System.out.println("Enter");
    }
}
