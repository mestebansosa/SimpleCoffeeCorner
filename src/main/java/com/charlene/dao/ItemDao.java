package com.charlene.dao;

import com.charlene.model.Item;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

// Singleton
public class ItemDao {
    private static volatile ItemDao instance;

    public static ItemDao getInstance() {
        if(instance == null) {
            synchronized(ItemDao.class) {
                if(instance == null) {
                    instance = new ItemDao();
                }
            }
        }
        return instance;
    }

    private final ConcurrentMap<String, Item> itemConcurrentMap;

    private ItemDao() {
        this.itemConcurrentMap = new ConcurrentHashMap<>();
    }

    public Item create(String name, Item.ITEM_TYPE type, Double value) {
        Item item = new Item(name, type, value);

        // If we get a non null value that means the user already exists in the Map.
        if (null != itemConcurrentMap.putIfAbsent(item.getName(), item)) {
            return null;
        }
        return item;
    }

    public Item get(String name) {
        Item item = itemConcurrentMap.get(name);
        if(item != null) {
            // create a new object. In other case it will return an object reference
            return new Item(item.getName(), item.getType(), item.getValue());
        } else {
            return null;
        }
    }

}
