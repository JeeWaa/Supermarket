package BO.Custom.Implement;

import BO.Custom.CustomerBO;
import DAO.Custom.CustomerDAO;
import DAO.Custom.Implement.CustomerImplementDAO;
import DAO.FactoryDAO;
import DTO.CustomerDTO;
import Entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerImplementBO implements CustomerBO {

    CustomerDAO customerDAO = (CustomerDAO) FactoryDAO.getFactoryDAO().getDAO(FactoryDAO.DAOType.CUSTOMER);

    @Override
    public boolean saveCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.save(new Customer(customerDTO.getCustomerId(), customerDTO.getCustomerName(), customerDTO.getCustomerAddress(), customerDTO.getCustomerNumber()));
    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(customerDTO.getCustomerId(), customerDTO.getCustomerName(), customerDTO.getCustomerAddress(), customerDTO.getCustomerNumber()));
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id);
    }

    @Override
    public CustomerDTO getCustomer(String id) throws SQLException, ClassNotFoundException {
        Customer customer = customerDAO.get(id);
        if (customer != null){
            return new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress(), customer.getNumber());
        }else {
            return null;
        }
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO> customerDTOS = new ArrayList<>();
        ArrayList<Customer> customers = customerDAO.getAll();
        for (Customer customer : customers){
            customerDTOS.add(new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress(), customer.getNumber()));
        }
        return customerDTOS;
    }

    @Override
    public String generateCustomerId() throws SQLException, ClassNotFoundException {
        return customerDAO.generateCustomerID();
    }
}
