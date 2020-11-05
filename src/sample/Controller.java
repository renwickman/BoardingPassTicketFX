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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
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
    final String DATE_FORMAT = "MM/dd/yyyy";
    Cities cities;
    Locations depart;
    Locations arrive;

    //update (Boarding Pass & Price)
    @FXML
    //optional used to execute any code before GUI loads
    public void initialize() {
        checkInput check = new checkInput(name, "test");
//        check.start();
        groupGender = new ToggleGroup();
        male.setToggleGroup(groupGender);
        female.setToggleGroup(groupGender);
        departLocation.getItems().addAll("New York", "Los Angeles", "Phoenix", "Indianapolis", "Detroit");
        arriveLocation.getItems().addAll("New York", "Los Angeles", "Phoenix", "Indianapolis", "Detroit");
        time.getItems().addAll("6:00 AM", "8:00 AM", "10:00 AM", "12:00 PM", "2:00 PM", "4:00 PM", "6:00 PM", "8:00 PM", "10:00 PM");
        cities.g
    }



    @FXML
    void pressEnter() {
        //check if everything is valid
        //if yes commit

        if (textFilled() && dateChosen() && timeChosen()){
            SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Application.class)
                    .buildSessionFactory();
            Session session = factory.getCurrentSession();
            try {
                Application application = new Application();
                application.setName(name.getText());
                application.setAge(Integer.parseInt(age.getText()));
                application.setGender(((RadioButton) groupGender.getSelectedToggle()).getText().substring(0, 1));
                application.setPhone(Long.parseLong(phoneNumber.getText()));
                application.setEmail(email.getText());
                application.setOrigin(String.valueOf(departLocation.getValue()));
                application.setDepartDate(departDate.getValue().format(DateTimeFormatter.ofPattern(DATE_FORMAT)));
                application.setDepartTime(String.valueOf(time.getValue()));
                application.setDestination(String.valueOf(arriveLocation.getValue()));
                session.beginTransaction();
                session.save(application);
                session.getTransaction().commit();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println(((RadioButton) groupGender.getSelectedToggle()).getText());
            } finally {
                session.close();
                factory.close();
            }

        }
    }

    void updateInfo(){

    }

    boolean textFilled(){
        int count = 0;
        count += valid(name.getText(), ".\\S+.");
        count += valid(email.getText(), "^[A-Za-z0-9+_.-]+@(.+)$");
        count += valid(phoneNumber.getText(), "[0-9]{10}+");
        count += valid(age.getText(), "[0-9]{1,3}+");
        return count == 0;
    }

    boolean dateChosen(){ return departDate.getValue() != null; }

    boolean timeChosen(){
        return time.getValue() != null;
    }

    int valid(String text, String regex) {
        if (text.matches(regex)) {
            return 0;
        }
        return 1;
    }

    String estArrive(){
        final String DATE_FORMAT = "MM/dd/yyyy hh:mm a";

        String leaveDateTime = departDate.getValue() + " " + time.getValue();
        LocalDateTime ldt = LocalDateTime.parse(leaveDateTime, DateTimeFormatter.ofPattern(DATE_FORMAT));

        double earthRadius = 6371.01 * 0.621;

//        Double distance = Math.round(earthRadius * Math.acos(Math.sin(depart.getLat()) * Math.sin(arrive.getLat())
//                + Math.cos(depart.getLat()) * Math.cos(arrive.getLat()) * Math.cos(depart.getLon() - arrive.getLon())));

        double distance1 = 1900 / 50;
        double hours = Math.floor(distance1);
        double minutes = Math.ceil((distance1 - hours) * 60);

        LocalDateTime ldt2 = ldt.plusHours((long) hours).plusMinutes((long) minutes);

//----------Use this code once you solve depart/arrive-----------------------------------------------------------//
//        ZoneId fromId = ZoneId.of(depart.getTimeZoneString());
//        ZoneId toId = ZoneId.of(arrive.getTimeZoneString());

        ZoneId fromId = ZoneId.of("America/" + departLocation.getValue());
        ZoneId toId = ZoneId.of("America/" + arriveLocation.getValue());


        ZonedDateTime currentTime = ldt2.atZone(fromId);

        ZonedDateTime newTime = currentTime.withZoneSameInstant(toId);

        //condition to add day
        DateTimeFormatter dateFormat2 = DateTimeFormatter.ofPattern(DATE_FORMAT);
        String arriveTime = dateFormat2.format(newTime);
        System.out.println(arriveTime);
        return arriveTime;
    }

    //-----change back to float------//
    private void priceCheck(){
        float price = (float) 150.00;
//
//        if (passenger.getGender().equals("F")){
//            price = price * .75f;
//        }
//        else if (passenger.getAge() <= 12){
//            price = price * .50f;
//        }
//        else if (passenger.getAge() >= 60){
//            price = price * .40f;
//        }
//        return price;
    }

}
