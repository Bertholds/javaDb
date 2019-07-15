package bertholds.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Student {
    private IntegerProperty id;
    private StringProperty  name;
    private StringProperty  surname;
    private StringProperty  email;

    public Student() {
        this.name = new SimpleStringProperty();
        this.surname = new SimpleStringProperty();
        this.id = new SimpleIntegerProperty();
        this.email = new SimpleStringProperty();
    }

    public String getName() {
        return name.get();
    }

    public void setName( String name ) {
        this.name.setValue( name );
    }

    public StringProperty getNamee() {
        return name;
    }

    public String getSurname() {
        return surname.get();
    }

    public void setSurname( String surname ) {
        this.surname.setValue( surname );
    }

    public StringProperty getSurNamee() {
        return surname;
    }

    public int getId() {
        return id.get();
    }

    public void setId( int id ) {
        this.id.set( id );
    }

    public IntegerProperty getIde() {
        return id;
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail( String email ) {
        this.email.set( email );
    }

    public StringProperty getEmaile() {
        return email;
    }

}
