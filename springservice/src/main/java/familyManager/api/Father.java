package familyManager.api;

import java.time.LocalDate;

public class Father {
    private String pesel;
    private String firstName;
    private String secondName;
    private LocalDate birthDate;

    public Father() {
    }

    public Father(String pesel, String firstName, String secondName, LocalDate birthDate) {
        this.pesel = pesel;
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthDate = birthDate;
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

    public LocalDate getBirthDate() {
        return birthDate;
    }
}
