package DAO.Custom;

import DAO.CrudDAO;
import DTO.ItemDTO;
import Entity.Item;

import java.sql.SQLException;

public interface ItemDAO extends CrudDAO<Item, String> {
    public boolean updateQty(String code, int qty) throws SQLException, ClassNotFoundException;
    public String generateItemCode() throws SQLException, ClassNotFoundException;
}
