package controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Patient {
    private final StringProperty id;
    private final StringProperty name;
    private final StringProperty birthdate;
    private final StringProperty contact;
    private final StringProperty record;
    private final StringProperty dentist;

    public Patient(String id, String name, String birthdate, String contact, String record, String dentist) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.birthdate = new SimpleStringProperty(birthdate);
        this.contact = new SimpleStringProperty(contact);
        this.record = new SimpleStringProperty(record);
        this.dentist = new SimpleStringProperty(dentist);
    }

    public StringProperty idProperty() {
        return id;
    }

    public String getId() {
        return id.get();
    }

    public void setId(String newId) {
        id.set(newId);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String newName) {
        name.set(newName);
    }

    public StringProperty birthdateProperty() {
        return birthdate;
    }

    public String getBirthdate() {
        return birthdate.get();
    }

    public void setBirthdate(String newBirthdate) {
        birthdate.set(newBirthdate);
    }

    public StringProperty contactProperty() {
        return contact;
    }

    public String getContact() {
        return contact.get();
    }

    public void setContact(String newContact) {
        contact.set(newContact);
    }

    public StringProperty recordProperty() {
        return record;
    }

    public String getRecord() {
        return record.get();
    }

    public void setRecord(String newRecord) {
        record.set(newRecord);
    }

    public StringProperty dentistProperty() {
        return dentist;
    }

    public String getDentist() {
        return dentist.get();
    }

    public void setDentist(String newDentist) {
        dentist.set(newDentist);
    }
}
