
package person;

// ABSTRACTION: Person is an abstract class.
// We cannot directly create a Person object.
// Student and Faculty will inherit from this class.

public abstract class Person {

    // ENCAPSULATION:
    // Protected variables can be accessed by child classes.
    protected int id;
    protected String name;

    // CONSTRUCTOR:
    // Initializes common information for Student and Faculty.
    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // GETTER METHODS:
    // Used to retrieve private/protected information.
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // ABSTRACT METHOD:
    // Child classes must provide their own implementation.
    public abstract void displayDetails();
}
