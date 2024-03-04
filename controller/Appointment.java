package controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Appointment {
    private final StringProperty id;
    private final StringProperty name;
    private final StringProperty date;
    private final StringProperty time;
    private final StringProperty service;
    private final StringProperty dentist;

    public Appointment(String id, String name, String date, String time, String service, String dentist) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.date = new SimpleStringProperty(date);
        this.time = new SimpleStringProperty(time);
        this.service = new SimpleStringProperty(service);
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

    public StringProperty dateProperty() {
        return date;
    }

    public String getDate() {
        return date.get();
    }

    public void setDate(String newDate) {
        date.set(newDate);
    }

    public StringProperty timeProperty() {
        return time;
    }

    public String getTime() {
        return time.get();
    }

    public void setTime(String newTime) {
        time.set(newTime);
    }

    public StringProperty serviceProperty() {
        return service;
    }

    public String getService() {
        return service.get();
    }

    public void setService(String newService) {
        service.set(newService);
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
