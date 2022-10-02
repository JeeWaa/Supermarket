package DAO.Custom.Implement;

import DAO.CrudUtil;
import DAO.Custom.ItemDAO;
import DTO.ItemDTO;
import Entity.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemImplementDAO implements ItemDAO {
    @Override
    public boolean save(Item item) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO Item VALUES(?,?,?,?,?,?)", item.getItemCode(), item.getItem(),item.getCompany(), item.getDescription(), item.getPrice(), item.getQty());
    }

    @Override
    public boolean update(Item item) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("UPDATE Item SET item=?, company=?, description=?, price=?, qty=? WHERE itemCode=?", item.getItem(), item.getCompany(), item.getDescription(), item.getPrice(), item.getQty(), item.getItemCode());
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("DELETE FROM Item WHERE itemCode='"+s+"'");
    }

    @Override
    public Item get(String s) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM Item WHERE itemCode=?", s);
        if (resultSet.next()){
            return new Item(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDouble(5), resultSet.getInt(6));
        }else {
            return null;
        }
    }

    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM Item");
        ArrayList<Item> allItem = new ArrayList<>();
        while (resultSet.next()){
            allItem.add(new Item(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDouble(5), resultSet.getInt(6)));
        }
        return allItem;
    }

    @Override
    public boolean updateQty(String code, int qty) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("UPDATE ITEM SET qtyOnHand=(qtyOnHand-" + qty + ") WHERE code='" + code + "'");
    }

    @Override
    public String generateItemCode() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT itemCode FROM Item ORDER BY itemCode DESC LIMIT 1");
        if (resultSet.next()){
            int itemCode = Integer.parseInt(resultSet.getString(1).split("-")[1]);
            itemCode=itemCode+1;
            if (itemCode<9){
                return "I-00"+itemCode;
            }else if(itemCode<99){
                return "I-0"+itemCode;
            }else{
                return "I-"+itemCode;
            }
        }else {
            return "I-001";
        }
    }
}
