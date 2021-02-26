package entities;

import javafx.beans.property.*;

import java.util.Date;

public class Transaction {

    private final ObjectProperty<Car> carProperty;
    private final ObjectProperty<Date> dateProperty;
    private final ObjectProperty<User> salesmanProperty;


    public Transaction(Car car, Date date, User salesman) {
        carProperty = new SimpleObjectProperty<>();
        carProperty.set(car);
        dateProperty = new SimpleObjectProperty<>();
        dateProperty.set(date);
        salesmanProperty = new SimpleObjectProperty<>();
        salesmanProperty.set(salesman);


    }

    public Car getCar() {
        return carProperty.get();
    }

    public ObjectProperty<Car> getCarProperty() {
        return carProperty;
    }


    public Date getDate() {
        return dateProperty.get();
    }

    public ObjectProperty<Date> getDateProperty() {
        return dateProperty;
    }


    public User getSalesman() {
        return salesmanProperty.get();
    }

    public ObjectProperty<User> getSalesmanProperty() {
        return salesmanProperty;
    }

}
