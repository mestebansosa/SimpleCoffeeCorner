package com.charlene;

import com.charlene.dao.ItemDao;
import com.charlene.model.Item;
import com.charlene.service.ItemsFiller;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;

@RunWith(JUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ItemTest {
    private final String itemName = "This is a test";

    @BeforeClass
    public static void setUpClass() {
        new ItemsFiller();
    }

    @Test
    public void item_1_create() {
        ItemDao.getInstance().create(itemName, Item.ITEM_TYPE.BEVERAGE, 304.99);
        Assert.assertTrue("Item not found", ItemDao.getInstance().get(itemName)!=null);
    }

    @Test
    public void item_2_get() {
        Assert.assertTrue("Item not found", ItemDao.getInstance().get(Item.ITEM_NAME.SPECIAL_ROAST_COFFEE.name())!=null);
    }

}
