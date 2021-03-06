package transactions;

import entities.Transaction;
import entities.User;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import main.Main;
import services.DataStorageService;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class TransactionsController implements Initializable {

      public TableView<Transaction> tableView;
      public Button exitButton;
      public Button newSaleButton;
      public Button removeButton;
      public Button backButton;
      public Text text;
      public TableColumn<Transaction, String> brand;
      public TableColumn<Transaction, String> model;
      public TableColumn<Transaction, String> value;
      public TableColumn<Transaction, String> date;
      public TableColumn<Transaction, String> salesman;
      public static SimpleBooleanProperty NEEDS_DATA_UPDATE = new SimpleBooleanProperty(false);
      private Double sum = 0.0;



      public void onActionExitButton(ActionEvent actionEvent) {

            Stage stage = (Stage) exitButton.getScene().getWindow();
            stage.close();
      }

      public void onActionNewTransactionButton(ActionEvent actionEvent) throws IOException, ParseException {

            Stage popup = new Stage();
            popup.setScene(new Scene(FXMLLoader.load(Main.class.getResource("../transactions/newTransaction.fxml"))));
            popup.initStyle(StageStyle.UNDECORATED);
            popup.setX(380);
            popup.setY(260);
            popup.show();


      }

      public void onActionRemoveButton(ActionEvent actionEvent) throws IOException {
            Transaction transaction = tableView.getSelectionModel().getSelectedItem();
            if(transaction != null){
                  DataStorageService.removeTransactionFromFile(transaction);
                  tableView.getItems().remove(transaction);

            }
      }

      public void onActionBackButton(ActionEvent actionEvent) throws IOException {
            Main.enterMain((Stage) exitButton.getScene().getWindow());
      }

      public void updateTable(){
            try {

                  tableView.setItems(DataStorageService.transactions());
                  NEEDS_DATA_UPDATE.set(false);

            } catch (IOException | ParseException e) {
                  e.printStackTrace();
            }
      }


      @Override
      public void initialize(URL url, ResourceBundle resourceBundle) {

            NEEDS_DATA_UPDATE.addListener((observable, oldValue, newValue) -> {

                  if (newValue) {
                        updateTable();
                  }

            });

            date.setCellValueFactory(new PropertyValueFactory<>("Date"));
            date.setCellValueFactory(entry -> new SimpleStringProperty(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(entry.getValue().getDate())));

            salesman.setCellValueFactory(new PropertyValueFactory<>("Salesman"));
            salesman.setCellValueFactory(entry -> new SimpleStringProperty(entry.getValue().getSalesman().getUsername()));

            brand.setCellValueFactory(new PropertyValueFactory<>("Brand"));
            brand.setCellValueFactory(entry -> new SimpleStringProperty(entry.getValue().getCar().getBrand()));

            model.setCellValueFactory(new PropertyValueFactory<>("Model"));
            model.setCellValueFactory(entry -> new SimpleStringProperty(entry.getValue().getCar().getModel()));

            value.setCellValueFactory(new PropertyValueFactory<>("Value"));
            value.setCellValueFactory(entry -> new SimpleStringProperty(Double.toString(entry.getValue().getCar().getValue())));
            value.setCellFactory(column -> new TableCell<>() {
                  @Override
                  protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);

                        setText(empty ? "" : getItem());
                        setGraphic(null);

                        TableRow<Transaction> currentRow = getTableRow();

                        tableView.refresh();
                        if (!isEmpty()) {

                              if (Double.parseDouble(item) < 0) {
                                    currentRow.setStyle("-fx-background-color:#ff6666");
                              }

                              else{
                                    currentRow.setStyle("-fx-background-color:#66ff66");
                              }
                        }
                  }
            });

            updateTable();

            NEEDS_DATA_UPDATE.set(true);

      }
}
