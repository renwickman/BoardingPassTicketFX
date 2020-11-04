package entity;

import javax.persistence.*;

@Entity
@Table
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private long phone;

    @Column(name = "age")
    private int age;

    @Column(name = "gender")
    private String gender;

    @Column(name = "origin")
    private String origin;

    @Column(name = "depart_time")
    private String depart_time;

    @Column(name = "depart_date")
    private String depart_date;

    @Column(name = "destination")
    private String destination;

    @Column(name = "eta")
    private String eta;

    @Column(name = "boarding_pass")
    private String boarding_pass;

    @Column(name = "total_price")
    private float total_price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }


    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }


    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }


    public String getDepartTime() {
        return depart_time;
    }

    public void setDepartTime(String depart_time) {
        this.depart_time = depart_time;
    }


    public String getDepartDate() {
        return depart_date;
    }

    public void setDepartDate(String depart_date) {
        this.depart_date = depart_date;
    }


    public String getEta() {
        return eta;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }


    public String getBoarding_pass() {
        return boarding_pass;
    }

    public void setBoarding_pass(String boarding_pass) {
        this.boarding_pass = boarding_pass;
    }


    public float getTotal_price() {
        return total_price;
    }

    public void setTotal_price(float total_price) {
        this.total_price = total_price;
    }

    @Override
    public String toString() {
        return "\nBoarding Pass: " + getBoarding_pass() + "\n" +
                "Name: " + getName() + "\n" +
                "Age: " + getAge() + "\n" +
                "Gender: " + getGender() + "\n" +
                "Phone Number: " + getPhone() + "\n" +
                "Email: " + getEmail() + "\n" +
                "Departure City: " + getOrigin() + "\n" +
                "Arrival City: " + getDestination() + "\n" +
                "Departure Date: " + getDepartDate() + "\n" +
                "Departure Time: " + getDepartTime() + "\n" +
                "ETA: " + getEta() + "\n" +
                "Total Price: $" + String.format("%.2f",getTotal_price());

    }
}
