package DAO.Custom;

import DAO.CrudDAO;
import DTO.CustomerDTO;
import Entity.Customer;

import java.sql.SQLException;

public interface CustomerDAO extends CrudDAO<Customer, String> {
    public String generateCustomerID() throws SQLException, ClassNotFoundException;
}
