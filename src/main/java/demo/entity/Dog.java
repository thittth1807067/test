package demo.entity;

import javax.persistence.*;
import java.sql.Date;
@Entity
public class Dog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(columnDefinition = "TEXT")
    private String name;
    private String breedName;
    private Date birthDay;
    private int gender;
    private String color;
    private int status;

    public Dog() {
    }

    public Dog(int id, String name, String breedName, Date birthDay, int gender, String color, int status) {
        this.id = id;
        this.name = name;
        this.breedName = breedName;
        this.birthDay = birthDay;
        this.gender = gender;
        this.color = color;
        this.status = status;
    }

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

    public String getBreedName() {
        return breedName;
    }

    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
