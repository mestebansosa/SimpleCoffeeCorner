package com.charlene.service;

import com.charlene.dao.ItemDao;
import com.charlene.model.Item;

public class ItemsFiller {
    // initialization block
    static {
        ItemDao itemDao = ItemDao.getInstance();
        itemDao.create(Item.ITEM_NAME.COFFEE_SMALL.name(),Item.ITEM_TYPE.BEVERAGE, 2.50);
        itemDao.create(Item.ITEM_NAME.COFFEE_MEDIUM.name(),Item.ITEM_TYPE.BEVERAGE, 3.00);
        itemDao.create(Item.ITEM_NAME.COFFEE_LARGE.name(),Item.ITEM_TYPE.BEVERAGE, 3.50);
        itemDao.create(Item.ITEM_NAME.BACON_ROLL.name(),Item.ITEM_TYPE.SNACK, 4.50);
        itemDao.create(Item.ITEM_NAME.ORANGE_JUICE.name(),Item.ITEM_TYPE.BEVERAGE, 3.95);
        itemDao.create(Item.ITEM_NAME.EXTRA_MILK.name(),Item.ITEM_TYPE.EXTRA, 0.3);
        itemDao.create(Item.ITEM_NAME.FOAMED_MILK.name(),Item.ITEM_TYPE.EXTRA, 0.5);
        itemDao.create(Item.ITEM_NAME.SPECIAL_ROAST_COFFEE.name(),Item.ITEM_TYPE.EXTRA, 0.9);
        itemDao.create(Item.ITEM_NAME.BEVERAGE_FREE.name(),Item.ITEM_TYPE.BONUS, 0.0);
        itemDao.create(Item.ITEM_NAME.EXTRA_FREE.name(),Item.ITEM_TYPE.BONUS, 0.0);
    }
}
