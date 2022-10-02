package DAO.Custom;

import DAO.CrudDAO;
import DTO.OrderDTO;
import Entity.Order;

import java.sql.SQLException;

public interface OrderDAO extends CrudDAO<Order, String> {
    public String generateOrderId() throws SQLException, ClassNotFoundException;
}
