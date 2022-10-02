package Controller;

import BO.Custom.CustomerBO;
import BO.Custom.Implement.ItemImplementBO;
import BO.Custom.ItemBO;
import BO.FactoryBO;
import DAO.CrudDAO;
import DAO.Custom.Implement.ItemImplementDAO;
import DTO.ItemDTO;
import View.Table.ItemTable;
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

public class ManageItemFormController {
    public JFXTextField txtItemCode;
    public JFXTextField txtItemName;
    public JFXTextField txtItemCompany;
    public JFXTextField txtItemDescription;
    public JFXTextField txtItemPrice;
    public JFXTextField txtItemQty;
    public TableView<ItemTable> tblItem;
    public TableColumn colItemCode;
    public TableColumn colItemName;
    public TableColumn colItemCompany;
    public TableColumn colItemDescription;
    public TableColumn colItemPrice;
    public TableColumn colItemQty;
    public JFXButton btnSaveItemId;
    public JFXButton btnUpdateItemId;
    public JFXButton btnDeleteItemId;
    public JFXButton btnCancelCustomerFormId;

    int selectDeleteItemRow = -1;

    ObservableList<ItemTable> item = FXCollections.observableArrayList();

    ItemBO itemBO = (ItemBO) FactoryBO.getFactoryBO().getBO(FactoryBO.BOType.ITEM);

    public void initialize() throws SQLException, ClassNotFoundException {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("item"));
        colItemCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
        colItemDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colItemPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colItemQty.setCellValueFactory(new PropertyValueFactory<>("qty"));

        tblItem.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            selectDeleteItemRow = (int) newValue;
            selectRow();

            if (selectDeleteItemRow != -1){
                btnDeleteItemId.setDisable(false);
                btnSaveItemId.setDisable(true);
                btnUpdateItemId.setDisable(false);
            }else {
                btnDeleteItemId.setDisable(true);
            }
        });

        allItemLoadTable(itemBO.getAllItem());

        generateItemCode();

        btnDeleteItemId.setDisable(true);
        btnSaveItemId.setDisable(false);
        btnUpdateItemId.setDisable(true);

        tblItem.getSelectionModel().clearSelection();

        tblItem.refresh();

        txtItemName.clear();
        txtItemCompany.clear();
        txtItemDescription.clear();
        txtItemPrice.clear();
        txtItemQty.clear();
    }

    private void generateItemCode(){
        try {
            txtItemCode.setText(itemBO.generateItemCode());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveItem(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        ItemDTO itemDTO = new ItemDTO(txtItemCode.getText(), txtItemName.getText(), txtItemCompany.getText(), txtItemDescription.getText(), Double.parseDouble(txtItemPrice.getText()), Integer.parseInt(txtItemQty.getText()));
        if (itemBO.saveItem(itemDTO)){
            new Alert(Alert.AlertType.CONFIRMATION, "Save Item").show();
            itemLoadTable();
            generateItemCode();

            tblItem.getSelectionModel().clearSelection();
            tblItem.refresh();

            txtItemName.clear();
            txtItemCompany.clear();
            txtItemDescription.clear();
            txtItemPrice.clear();
            txtItemQty.clear();

            btnSaveItemId.setDisable(false);
            btnUpdateItemId.setDisable(true);
            btnDeleteItemId.setDisable(true);
        }else {
            new Alert(Alert.AlertType.WARNING, "No Save Item").show();
        }
    }

    public void updateItem(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        ItemDTO itemDTO = new ItemDTO(txtItemCode.getText(), txtItemName.getText(), txtItemCompany.getText(), txtItemDescription.getText(), Double.parseDouble(txtItemPrice.getText()), Integer.parseInt(txtItemQty.getText()));
        if (itemBO.updateItem(itemDTO)){
            new Alert(Alert.AlertType.CONFIRMATION, "Update Item").show();
            itemLoadTable();
            generateItemCode();

            tblItem.getSelectionModel().clearSelection();
            tblItem.refresh();

            txtItemName.clear();
            txtItemCompany.clear();
            txtItemDescription.clear();
            txtItemPrice.clear();
            txtItemQty.clear();
        }else {
            new Alert(Alert.AlertType.WARNING, "No Update Item").show();
        }
    }

    public void deleteItem(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        if(itemBO.deleteItem(txtItemCode.getText())){
            new Alert(Alert.AlertType.CONFIRMATION, "Delete Item").show();
            generateItemCode();

            tblItem.refresh();

            txtItemName.clear();
            txtItemCompany.clear();
            txtItemDescription.clear();
            txtItemPrice.clear();
            txtItemQty.clear();
        }else {
            new Alert(Alert.AlertType.WARNING, "No Delete Item").show();
        }
    }

    private void allItemLoadTable(ArrayList<ItemDTO> itemDTOTable){
        itemDTOTable.forEach(table->{item.add(new ItemTable(table.getItemCode(), table.getItemName(), table.getItemCompany(), table.getItemDescription(), table.getItemPrice(), table.getItemQty()));});
        tblItem.setItems(item);
    }

    private void itemLoadTable(){
        ItemTable itemTable = new ItemTable(txtItemCode.getText(), txtItemName.getText(), txtItemCompany.getText(), txtItemDescription.getText(), Double.parseDouble(txtItemPrice.getText()), Integer.parseInt(txtItemQty.getText()));
        item.add(itemTable);
        tblItem.setItems(item);
    }

    private void selectRow(){
        if (selectDeleteItemRow != -1){
            ItemTable itemTable = item.get(selectDeleteItemRow);
            txtItemCode.setText(itemTable.getCode());
            txtItemName.setText(itemTable.getItem());
            txtItemCompany.setText(itemTable.getCompany());
            txtItemDescription.setText(itemTable.getDescription());
            txtItemPrice.setText(String.valueOf(itemTable.getPrice()));
            txtItemQty.setText(String.valueOf(itemTable.getQty()));
        }else {
            //No Alert
        }
    }

    public void cancelCustomerForm(MouseEvent mouseEvent) {
        final Node node = (Node) mouseEvent.getSource();
        final Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }
}
