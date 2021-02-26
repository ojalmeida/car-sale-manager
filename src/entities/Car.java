package entities;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Car {

    private final SimpleStringProperty brand;
    private final SimpleStringProperty model;
    private final SimpleDoubleProperty value;
    private final SimpleIntegerProperty mileage;

    public Car(String brand, String model, Double value, Integer mileage) {
        this.brand = new SimpleStringProperty(brand);
        this.model = new SimpleStringProperty(model);
        this.value = new SimpleDoubleProperty(value);
        this.mileage = new SimpleIntegerProperty(mileage);
    }
    


    public String getBrand() {
        return brand.get();
    }

    public SimpleStringProperty brandProperty() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand.set(brand);
    }

    public String getModel() {
        return model.get();
    }

    public SimpleStringProperty modelProperty() {
        return model;
    }

    public void setModel(String model) {
        this.model.set(model);
    }

    public double getValue() {
        return value.get();
    }

    public SimpleDoubleProperty valueProperty() {
        return value;
    }

    public void setValue(double value) {
        this.value.set(value);
    }

    public int getMileage() {
        return mileage.get();
    }

    public SimpleIntegerProperty mileageProperty() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage.set(mileage);
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand=" + brand +
                ", model=" + model +
                ", value=" + value +
                ", mileage=" + mileage +
                '}';
    }
}
