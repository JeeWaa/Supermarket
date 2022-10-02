package DAO.Custom.Implement;

import DAO.CrudUtil;
import DAO.Custom.CustomerDAO;
import DTO.CustomerDTO;
import Entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerImplementDAO implements CustomerDAO {
    @Override
    public boolean save(Customer customer) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO Customer VALUES(?,?,?,?)", customer.getId(), customer.getName(), customer.getAddress(), customer.getNumber());
    }

    @Override
    public boolean update(Customer customer) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("UPDATE Customer SET name=?, address=?, number=? WHERE id=?", customer.getName(), customer.getAddress(), customer.getNumber(), customer.getId());
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("DELETE FROM Customer WHERE id='"+s+"'");
    }

    @Override
    public Customer get(String s) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM Customer WHERE id=?", s);
        if (resultSet.next()){
            return new Customer(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
        }else {
            return null;
        }
    }

    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM Customer");
        ArrayList<Customer> allCustomer = new ArrayList<>();
        while (resultSet.next()){
            allCustomer.add(new Customer(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)));
        }
        return allCustomer;
    }

    @Override
    public String generateCustomerID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT id FROM Customer ORDER BY id DESC LIMIT 1");
        if (resultSet.next()){
            int customerId = Integer.parseInt(resultSet.getString(1).split("-")[1]);
            customerId=customerId+1;
            if (customerId<9){
                return "C-00"+customerId;
            }else if(customerId<99){
                return "C-0"+customerId;
            }else{
                return "C-"+customerId;
            }
        }else {
            return "C-001";
        }
    }
}
