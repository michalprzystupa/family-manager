package familyManager;

public class Child {
    private String pesel;
    private String firstName;
    private String secondName;
    private String sex;

    public Child() {
    }

    public Child(String pesel, String firstName, String secondName, String sex) {
        this.pesel = pesel;
        this.firstName = firstName;
        this.secondName = secondName;
        this.sex = sex;
    }

    public String getPesel() {
        return pesel;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getSex() {
        return sex;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
