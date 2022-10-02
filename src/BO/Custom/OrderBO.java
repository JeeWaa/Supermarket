package BO.Custom;

import BO.SuperBO;
import DTO.CustomerDTO;
import DTO.ItemDTO;
import DTO.OrderDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderBO extends SuperBO {
    public boolean saveOrder(OrderDTO orderDTO) throws SQLException, ClassNotFoundException;
    public CustomerDTO getCustomer(String id) throws SQLException, ClassNotFoundException;
    public ItemDTO getItem(String code) throws SQLException, ClassNotFoundException;
    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException;
    public ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException;
    public String generateOrderId() throws SQLException, ClassNotFoundException;
}
