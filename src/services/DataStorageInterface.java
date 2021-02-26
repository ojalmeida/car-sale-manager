package services;

import entities.Car;
import entities.Transaction;
import entities.User;
import javafx.collections.ObservableList;

import java.util.List;

public interface DataStorageInterface {

    static List<User> loadUsers(){
        return null;
    }
    static void addUser(User user){
    }

    static String encryptData(String data){
        return null;
    }
    static String decryptData(String encryptedData){
        return null;
    }

    static ObservableList<Car> carsStoraged(){
        return null;
    }
    static ObservableList<Transaction> transactions(){
        return null;
    }

    static void addCarToFile(Car car){}
    static void removeCarFromFile(Car car){}

    static void addTransaction(Transaction transaction){}
    static void removeTransactionFromFile(Transaction transaction){}

    static void verifyFiles(){}
    static Boolean verifyUserData(User user){
        return null;
    }

}
