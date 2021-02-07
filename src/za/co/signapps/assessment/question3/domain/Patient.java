package za.co.signapps.assessment.question3.domain;

public class Patient {
    private int id;
    private String name;

    public Patient() {
        this.id = 0;
        this.name = "";
    }


    public Patient(int id, String name) {
        this.id = id;
        this.name = name;
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
}
