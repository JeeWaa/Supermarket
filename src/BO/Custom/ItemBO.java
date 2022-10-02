package BO.Custom;

import BO.SuperBO;
import DTO.CustomerDTO;
import DTO.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO extends SuperBO {
    public boolean saveItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;
    public boolean updateItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;
    public boolean deleteItem(String code) throws SQLException, ClassNotFoundException;
    public ItemDTO getItem(String code) throws SQLException, ClassNotFoundException;
    public ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException;
    public String generateItemCode() throws SQLException, ClassNotFoundException;
}
