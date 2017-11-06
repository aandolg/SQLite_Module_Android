package in.good_work.sqlite_module.Model;

/**
 * Created by Alex on 30.10.2017.
 */

public class Person {
    private String id;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private String skype;

    public Person() {

    }

    public Person(String name, String surname, String phone, String email, String skype) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.skype = skype;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", skype='" + skype + '\'' +
                '}';
    }
}
