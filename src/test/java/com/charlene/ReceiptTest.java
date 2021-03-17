package com.charlene;

import com.charlene.model.Item;
import com.charlene.model.Receipt;
import com.charlene.service.ItemsFiller;
import com.charlene.service.ReceiptService;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

@RunWith(JUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ReceiptTest {

    @BeforeClass
    public static void setUpClass() {
        new ItemsFiller();
    }

    @Test
    public void order5beverage1snack2extra() {
        Path path = Paths.get("./order5beverage1snack2extra.txt");

        ReceiptService receiptService = new ReceiptService();
        Receipt receipt = receiptService.createReceipt(path);
        Assert.assertTrue("BEVERAGE quantity", receipt.getItems().stream()
                .filter(a -> a.getType() == Item.ITEM_TYPE.BEVERAGE).count()==5);
        Assert.assertTrue("SNACK quantity", receipt.getItems().stream()
                .filter(a -> a.getType() == Item.ITEM_TYPE.SNACK).count()==1);
        Assert.assertTrue("EXTRA quantity", receipt.getItems().stream()
                .filter(a -> a.getType() == Item.ITEM_TYPE.EXTRA).count()==2);
        Assert.assertTrue("Receipt size", receipt.getItems().size()==8);

        receiptService.bonus5thBeverageFree(receipt);
        Assert.assertTrue("BONUS quantity", receipt.getItems().stream()
                .filter(a -> a.getName().contentEquals(Item.ITEM_NAME.BEVERAGE_FREE.name())).count()==1);
        Assert.assertTrue("Receipt size", receipt.getItems().size()==9);

        receiptService.bonusExtraFree(receipt);
        Assert.assertTrue("BONUS quantity", receipt.getItems().stream()
                .filter(a -> a.getName().contentEquals(Item.ITEM_NAME.EXTRA_FREE.name())).count()==1);
        Assert.assertTrue("Receipt size", receipt.getItems().size()==10);
    }

    @Test
    public void order5beverage() {
        Path path = Paths.get("./order5beverage.txt");

        ReceiptService receiptService = new ReceiptService();
        Receipt receipt = receiptService.createReceipt(path);
        Assert.assertTrue("BEVERAGE quantity", receipt.getItems().stream()
                .filter(a -> a.getType() == Item.ITEM_TYPE.BEVERAGE).count()==5);
        Assert.assertTrue("SNACK quantity", receipt.getItems().stream()
                .filter(a -> a.getType() == Item.ITEM_TYPE.SNACK).count()==0);
        Assert.assertTrue("EXTRA quantity", receipt.getItems().stream()
                .filter(a -> a.getType() == Item.ITEM_TYPE.EXTRA).count()==0);
        Assert.assertTrue("Receipt size", receipt.getItems().size()==5);

        receiptService.bonus5thBeverageFree(receipt);
        Assert.assertTrue("BONUS quantity", receipt.getItems().stream()
                .filter(a -> a.getName().contentEquals(Item.ITEM_NAME.BEVERAGE_FREE.name())).count()==1);
        Assert.assertTrue("Receipt size", receipt.getItems().size()==6);

        receiptService.bonusExtraFree(receipt);
        Assert.assertTrue("BONUS quantity", receipt.getItems().stream()
                .filter(a -> a.getName().contentEquals(Item.ITEM_NAME.EXTRA_FREE.name())).count()==0);
        Assert.assertTrue("Receipt size", receipt.getItems().size()==6);

    }

    @Test
    public void order1beverage1snack2extra() {
        Path path = Paths.get("./order1beverage1snack2extra.txt");

        ReceiptService receiptService = new ReceiptService();
        Receipt receipt = receiptService.createReceipt(path);
        Assert.assertTrue("BEVERAGE quantity", receipt.getItems().stream()
                .filter(a -> a.getType() == Item.ITEM_TYPE.BEVERAGE).count()==1);
        Assert.assertTrue("SNACK quantity", receipt.getItems().stream()
                .filter(a -> a.getType() == Item.ITEM_TYPE.SNACK).count()==1);
        Assert.assertTrue("EXTRA quantity", receipt.getItems().stream()
                .filter(a -> a.getType() == Item.ITEM_TYPE.EXTRA).count()==2);
        Assert.assertTrue("Receipt size", receipt.getItems().size()==4);

        receiptService.bonus5thBeverageFree(receipt);
        Assert.assertTrue("BONUS quantity", receipt.getItems().stream()
                .filter(a -> a.getName().contentEquals(Item.ITEM_NAME.BEVERAGE_FREE.name())).count()==0);
        Assert.assertTrue("Receipt size", receipt.getItems().size()==4);

        receiptService.bonusExtraFree(receipt);
        Assert.assertTrue("BONUS quantity", receipt.getItems().stream()
                .filter(a -> a.getName().contentEquals(Item.ITEM_NAME.EXTRA_FREE.name())).count()==1);
        Assert.assertTrue("Receipt size", receipt.getItems().size()==5);

    }

}
