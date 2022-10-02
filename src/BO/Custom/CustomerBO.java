package BO.Custom;

import BO.SuperBO;
import DTO.CustomerDTO;
import Entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {
    public boolean saveCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;
    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;
    public CustomerDTO getCustomer(String id) throws SQLException, ClassNotFoundException;
    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException;
    public String generateCustomerId() throws SQLException, ClassNotFoundException;
}
