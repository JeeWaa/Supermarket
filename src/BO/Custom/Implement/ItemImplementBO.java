package BO.Custom.Implement;

import BO.Custom.ItemBO;
import DAO.Custom.CustomerDAO;
import DAO.Custom.Implement.ItemImplementDAO;
import DAO.Custom.ItemDAO;
import DAO.FactoryDAO;
import DTO.ItemDTO;
import Entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemImplementBO implements ItemBO {

    ItemDAO itemDAO = (ItemDAO) FactoryDAO.getFactoryDAO().getDAO(FactoryDAO.DAOType.ITEM);

    @Override
    public boolean saveItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        return itemDAO.save(new Item(itemDTO.getItemCode(), itemDTO.getItemName(), itemDTO.getItemCompany(), itemDTO.getItemDescription(), itemDTO.getItemPrice(), itemDTO.getItemQty()));
    }

    @Override
    public boolean updateItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        return itemDAO.update(new Item(itemDTO.getItemCode(), itemDTO.getItemName(), itemDTO.getItemCompany(), itemDTO.getItemDescription(), itemDTO.getItemPrice(), itemDTO.getItemQty()));
    }

    @Override
    public boolean deleteItem(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(code);
    }

    @Override
    public ItemDTO getItem(String code) throws SQLException, ClassNotFoundException {
        Item item = itemDAO.get(code);
        if (item != null){
            return new ItemDTO(item.getItemCode(), item.getItem(), item.getCompany(), item.getDescription(), item.getPrice(), item.getQty());
        }else {
            return null;
        }
    }

    @Override
    public ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException {
        ArrayList<ItemDTO> itemDTOS = new ArrayList<>();
        ArrayList<Item> items = itemDAO.getAll();
        for (Item item : items){
            itemDTOS.add(new ItemDTO(item.getItemCode(), item.getItem(), item.getCompany(), item.getDescription(), item.getPrice(), item.getQty()));
        }
        return itemDTOS;
    }

    @Override
    public String generateItemCode() throws SQLException, ClassNotFoundException {
        return itemDAO.generateItemCode();
    }
}
