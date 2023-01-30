public class Author {
    private final String fistName;
    private final String lastName;

    Author(String firstName, String lastName) {
        this.fistName = firstName;
        this.lastName = lastName;
    }

    public String getFistName() {
        return fistName;
    }

    public String getLastName() {
        return lastName;
    }
}