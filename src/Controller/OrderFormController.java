package Controller;

import BO.Custom.CustomerBO;
import BO.Custom.Implement.CustomerImplementBO;
import BO.Custom.Implement.ItemImplementBO;
import BO.Custom.Implement.OrderImplementBO;
import BO.Custom.ItemBO;
import BO.Custom.OrderBO;
import BO.FactoryBO;
import DAO.CrudDAO;
import DAO.Custom.CustomerDAO;
import DAO.Custom.Implement.CustomerImplementDAO;
import DAO.Custom.Implement.ItemImplementDAO;
import DAO.Custom.ItemDAO;
import DTO.CustomerDTO;
import DTO.ItemDTO;
import DTO.OrderDTO;
import DTO.OrderDetailDTO;
import View.Table.CardTable;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class OrderFormController {
    public Label lblOrderId;
    public Label lblTime;
    public Label lblDate;
    public Label lblTotal;
    public JFXComboBox<String> comCustomerId;
    public JFXTextField txtCustomerName;
    public JFXTextField txtCustomerAddress;
    public JFXTextField txtCustomerNumber;
    public JFXComboBox<String> comItemCode;
    public JFXTextField txtItemName;
    public JFXTextField txtItemCompany;
    public JFXTextField txtItemDescription;
    public JFXTextField txtItemUnitPrice;
    public JFXTextField txtQty;
    public TableView<CardTable> tblCard;
    public TableColumn colItemCode;
    public TableColumn colItemCompany;
    public TableColumn colItemDescription;
    public TableColumn colItemUnitPrice;
    public TableColumn colItemQty;
    public TableColumn colItemTotal;
    public JFXTextField txtEnterQty;
    public JFXButton btnAddToTable;
    public JFXButton btnDeleteTableRow;
    public JFXButton btnPlaceOrder;
    public JFXButton btnCancelOrderForm;

    int selectDeleteCustomerRow = -1;

    ObservableList<CardTable> card = FXCollections.observableArrayList();

    OrderBO orderBO = (OrderBO) FactoryBO.getFactoryBO().getBO(FactoryBO.BOType.ORDER);

    public void initialize() {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colItemCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
        colItemDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colItemUnitPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colItemQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colItemTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        loadAllDateAndTime();

        generateOrderId();

        try {

            loadItemCode();
            loadCustomerId();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        comCustomerId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setCustomerDate(newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        comItemCode.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setItemDate(newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        tblCard.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            selectDeleteCustomerRow = (int) newValue;
            if (selectDeleteCustomerRow > -1){
                btnDeleteTableRow.setDisable(false);
            }else {
                btnDeleteTableRow.setDisable(true);
            }
        });

        btnAddToTable.setDisable(true);
        btnPlaceOrder.setDisable(true);
        btnDeleteTableRow.setDisable(true);
    }

    private void generateOrderId() {
        try {
            lblOrderId.setText(orderBO.generateOrderId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadAllDateAndTime() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(simpleDateFormat.format(date));

        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, event -> {
            LocalTime localTime = LocalTime.now();
            lblTime.setText(localTime.getHour() + " : " + localTime.getMinute() + " : " + localTime.getSecond());
        }),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void loadCustomerId() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO> customerDTOIds = orderBO.getAllCustomer();
        for (CustomerDTO customerDTO : customerDTOIds
        ) {
            comCustomerId.getItems().addAll(customerDTO.getCustomerId());
        }
    }

    private void loadItemCode() throws SQLException, ClassNotFoundException {
        ArrayList<ItemDTO> itemDTOCodes = orderBO.getAllItem();
        for (ItemDTO itemDTO : itemDTOCodes
        ) {
            comItemCode.getItems().addAll(itemDTO.getItemCode());
        }
    }

    private void setCustomerDate(String customerId) throws SQLException, ClassNotFoundException {
        CustomerDTO customerDTO = orderBO.getCustomer(customerId);
        if (customerDTO != null){
            txtCustomerName.setText(customerDTO.getCustomerName());
            txtCustomerAddress.setText(customerDTO.getCustomerAddress());
            txtCustomerNumber.setText(customerDTO.getCustomerNumber());
        }else {
            new Alert(Alert.AlertType.WARNING, "No Customer").show();
        }
    }

    private void setItemDate(String itemCode) throws SQLException, ClassNotFoundException {
        ItemDTO itemDTO = orderBO.getItem(itemCode);
        if (itemDTO != null){
            txtItemName.setText(itemDTO.getItemName());
            txtItemCompany.setText(itemDTO.getItemCompany());
            txtItemDescription.setText(itemDTO.getItemDescription());
            txtItemUnitPrice.setText(String.valueOf(itemDTO.getItemPrice()));
            txtQty.setText(String.valueOf(itemDTO.getItemQty()));

            btnAddToTable.setDisable(false);
        }else {
            new Alert(Alert.AlertType.WARNING, "No Item").show();
        }
    }

    public void addToTable(MouseEvent mouseEvent) {

        btnPlaceOrder.setDisable(false);

        String item = txtItemName.getText();
        String company = txtItemCompany.getText();
        String description = txtItemDescription.getText();
        double unitPrice = Double.parseDouble(txtItemUnitPrice.getText());
        int qtyOnHand = Integer.parseInt(txtQty.getText());
        int qtyCustomer = Integer.parseInt(txtEnterQty.getText());
        double total = unitPrice * qtyCustomer;

        if (qtyCustomer > qtyOnHand){
            new Alert(Alert.AlertType.WARNING, "Invalid QTY").show();
            return;
        }

        CardTable cardTable = new CardTable(comItemCode.getValue(), company, description, unitPrice, qtyCustomer, total);

        int rowNumber = row(cardTable);

        if (rowNumber == -1){
            card.add(cardTable);
        }else {
            CardTable table = card.get(rowNumber);
            CardTable temp = new CardTable(table.getCode(), table.getCompany(), table.getDescription(), unitPrice, table.getQty()+qtyCustomer, total+table.getTotal());
            card.remove(rowNumber);
            card.add(temp);
        }
        tblCard.setItems(card);
        calculate();
    }

    private void calculate() {
        double total = 0.0;
        for (CardTable cardTable : card
        ) {
            total+=cardTable.getTotal();
        }
        lblTotal.setText(total + "");
    }

    private int row(CardTable cardTable) {
        for (int i = 0; i < card.size(); i++) {
            if (cardTable.getCode().equals(card.get(i).getCode())){
                return i;
            }
        }
        return -1;
    }

    public void deleteTableRow(MouseEvent mouseEvent) {
        if (selectDeleteCustomerRow == -1){
            new Alert(Alert.AlertType.WARNING, "No Select Row").show();
        }else {
            card.remove(selectDeleteCustomerRow);
            calculate();
            tblCard.refresh();
        }
    }

    public void placeOrderForm(MouseEvent mouseEvent) {
        boolean save = saveOrder(lblOrderId.getText(), comCustomerId.getValue(), lblTime.getText(), lblDate.getText(), tblCard.getItems().stream().map(table -> new OrderDetailDTO(table.getCode(), lblOrderId.getText(), table.getPrice(), table.getQty())).collect(Collectors.toList()));

        System.out.println(save);

        if (save){
            new Alert(Alert.AlertType.CONFIRMATION, "Save Order").show();
            generateOrderId();
        }else {
            new Alert(Alert.AlertType.WARNING, "No Save").show();
        }
    }

    private boolean saveOrder(String orderId, String customerId, String time, String date, List<OrderDetailDTO> orderDetailDTOS){
        try {
            System.out.println("a  "+ orderId  +"a  "+  customerId  +"a  "+  time  +"a  "+  date  +"a  ///"+  orderDetailDTOS);

            OrderDTO orderDTO = new OrderDTO(orderId, customerId, time, date, orderDetailDTOS);
            return orderBO.saveOrder(orderDTO);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void cancelOrderForm(MouseEvent mouseEvent) {
        final Node node = (Node) mouseEvent.getSource();
        final Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }
}
