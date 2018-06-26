package familyManager;

public class Child {
    private final String pesel;
    private final String firstName;
    private final String secondName;
    private final String sex;

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
}
