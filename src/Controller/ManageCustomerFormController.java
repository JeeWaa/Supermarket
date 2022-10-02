package Controller;

import BO.Custom.CustomerBO;
import BO.Custom.Implement.CustomerImplementBO;
import BO.FactoryBO;
import DAO.CrudDAO;
import DAO.Custom.CustomerDAO;
import DAO.Custom.Implement.CustomerImplementDAO;
import DAO.Custom.ItemDAO;
import DAO.FactoryDAO;
import DTO.CustomerDTO;
import View.Table.CustomerTable;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;

public class ManageCustomerFormController {
    public JFXTextField txtCustomerId;
    public JFXTextField txtCustomerName;
    public JFXTextField txtCustomerAddress;
    public JFXTextField txtCustomerNumber;
    public TableView<CustomerTable> tblCustomer;
    public TableColumn colCustomerId;
    public TableColumn colCustomerName;
    public TableColumn colCustomerAddress;
    public TableColumn colCustomerNumber;
    public JFXButton btnSaveCustomerId;
    public JFXButton btnUpdateCustomerId;
    public JFXButton btnDeleteCustomerId;
    public JFXButton btnCancelFormId;

    int selectDeleteCustomerRow = -1;

    ObservableList<CustomerTable> customer = FXCollections.observableArrayList();

    CustomerBO customerBO = (CustomerBO) FactoryBO.getFactoryBO().getBO(FactoryBO.BOType.CUSTOMER);

    public void initialize() throws SQLException, ClassNotFoundException {
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCustomerAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCustomerNumber.setCellValueFactory(new PropertyValueFactory<>("number"));

        tblCustomer.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            selectDeleteCustomerRow = (int) newValue;
            selectRow();

            if (selectDeleteCustomerRow != -1){
                btnDeleteCustomerId.setDisable(false);
                btnSaveCustomerId.setDisable(true);
                btnUpdateCustomerId.setDisable(false);
            }else {
                btnDeleteCustomerId.setDisable(true);
            }
        });

        allCustomerLoadTable(customerBO.getAllCustomer());

        setCustomerId();

        btnDeleteCustomerId.setDisable(true);
        btnSaveCustomerId.setDisable(false);
        btnUpdateCustomerId.setDisable(true);

        tblCustomer.getSelectionModel().clearSelection();

        txtCustomerName.clear();
        txtCustomerAddress.clear();
        txtCustomerNumber.clear();

    }

    private void setCustomerId() throws SQLException, ClassNotFoundException {
        txtCustomerId.setText(customerBO.generateCustomerId());
    }

    public void saveCustomer(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        String id = txtCustomerId.getText();
        CustomerDTO customerDTO = new CustomerDTO(id, txtCustomerName.getText(), txtCustomerAddress.getText(), txtCustomerNumber.getText());
        if (customerBO.saveCustomer(customerDTO)){
            new Alert(Alert.AlertType.CONFIRMATION, "Save Customer").show();
            customerLoadTable();
            setCustomerId();

            tblCustomer.getSelectionModel().clearSelection();
            tblCustomer.refresh();

            txtCustomerName.clear();
            txtCustomerAddress.clear();
            txtCustomerNumber.clear();
        }else {
            new Alert(Alert.AlertType.WARNING, "No save Customer").show();
        }
    }

    public void updateCustomer(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        String id = txtCustomerId.getText();
        CustomerDTO customerDTO = new CustomerDTO(id, txtCustomerName.getText(), txtCustomerAddress.getText(), txtCustomerNumber.getText());
        if (customerBO.updateCustomer(customerDTO)){
            new Alert(Alert.AlertType.CONFIRMATION, "Update Customer").show();

            tblCustomer.refresh();
            tblCustomer.getSelectionModel().clearSelection();

            txtCustomerName.clear();
            txtCustomerAddress.clear();
            txtCustomerNumber.clear();

            btnSaveCustomerId.setDisable(false);
            btnUpdateCustomerId.setDisable(true);
            btnDeleteCustomerId.setDisable(true);
        }else {
            new Alert(Alert.AlertType.WARNING, "No Update Customer").show();
        }
    }

    public void deleteCustomer(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        if (customerBO.deleteCustomer(txtCustomerId.getText())){
            new Alert(Alert.AlertType.CONFIRMATION, "Delete Customer").show();
            tblCustomer.refresh();

            txtCustomerName.clear();
            txtCustomerAddress.clear();
            txtCustomerNumber.clear();

            btnSaveCustomerId.setDisable(false);
            btnUpdateCustomerId.setDisable(true);
            btnDeleteCustomerId.setDisable(true);
        }else {
            new Alert(Alert.AlertType.WARNING, "No Delete Customer").show();
        }
    }

    private void allCustomerLoadTable(ArrayList<CustomerDTO> customerDTOTables) {
        customerDTOTables.forEach(table->{customer.add(new CustomerTable(table.getCustomerId(), table.getCustomerName(), table.getCustomerAddress(), table.getCustomerNumber()));});
        tblCustomer.setItems(customer);
    }

    private void customerLoadTable(){
        CustomerTable customerTable = new CustomerTable(txtCustomerId.getText(), txtCustomerName.getText(), txtCustomerAddress.getText(), txtCustomerNumber.getText());
        customer.add(customerTable);
        tblCustomer.setItems(customer);
    }

    private void selectRow() {
        if (selectDeleteCustomerRow != -1){
            CustomerTable customerTable1 = customer.get(selectDeleteCustomerRow);
            txtCustomerId.setText(customerTable1.getId());
            txtCustomerName.setText(customerTable1.getName());
            txtCustomerAddress.setText(customerTable1.getAddress());
            txtCustomerNumber.setText(customerTable1.getNumber());
        }else {
            //No alert
        }
    }

    public void cancelCustomerForm(MouseEvent mouseEvent) {
        final Node node = (Node) mouseEvent.getSource();
        final Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }
}
