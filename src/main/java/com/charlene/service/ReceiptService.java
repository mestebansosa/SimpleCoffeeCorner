package com.charlene.service;

import com.charlene.dao.ItemDao;
import com.charlene.model.Item;
import com.charlene.model.Receipt;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ReceiptService {

    public ReceiptService() {}

    private final Integer beverageFree = 5;

    public void getReceipt(Path path) {
        Receipt receipt = createReceipt(path);
        bonus5thBeverageFree(receipt);
        bonusExtraFree(receipt);
        printReceipt(receipt);
    }

    public Receipt createReceipt(Path path) {
        Receipt receipt = new Receipt();

        try {
            Files.readAllLines(path).forEach(l -> {
                if(l.isEmpty() || l.isBlank()) return;
                String[] line = l.split(",", 2);
                Item item = ItemDao.getInstance().get(line[0].trim());
                Integer quantity = Integer.valueOf(line[1].trim());
                if(item==null) {
                    System.err.println("item in order " + Objects.requireNonNull(item).getName() + " is not valid.");
                    System.exit(1);
                }
                for(int i=0;i < quantity; i++) {
                    receipt.getItems().add(item);
                }
            });
        } catch (NoSuchFileException e) {
            System.err.println("NoSuchFileException: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return receipt;
    }

    // Every 5th beverage is for free.
    public void bonus5thBeverageFree(Receipt receipt) {
        if(receipt.getItems().size()==0) return;

        // get number of beverages in receipt
        long beveragesInOrder = receipt.getItems().stream()
                .filter(a -> a.getType()== Item.ITEM_TYPE.BEVERAGE).count();

        if(beveragesInOrder < beverageFree) return;

        long bonus = beveragesInOrder / beverageFree;

        while(bonus > 0) {
            Item bonusItem = ItemDao.getInstance().get(Item.ITEM_NAME.BEVERAGE_FREE.name());
            // look for min price of beverages
            bonusItem.setValue(receipt.getItems().stream().filter(a -> a.getType()== Item.ITEM_TYPE.BEVERAGE)
                    .mapToDouble(a -> a.getValue()).min().orElseThrow(NoSuchElementException::new) * -1.0);
            receipt.getItems().add(bonusItem);
            bonus--;
        }
    }

    // If a customer orders a beverage and a snack, one of the extra's is free.
    public void bonusExtraFree(Receipt receipt) {
        if(receipt.getItems().size()==0) return;

        long beverages = receipt.getItems().stream()
                .filter(a -> a.getType()==Item.ITEM_TYPE.BEVERAGE).count();
        long snacks = receipt.getItems().stream()
                .filter(a -> a.getType()==Item.ITEM_TYPE.SNACK).count();
        long extras = receipt.getItems().stream()
                .filter(a -> a.getType()==Item.ITEM_TYPE.EXTRA).count();
        long bonus = 0;
        if(beverages > 0 && snacks > 0 && extras > 0) {
            List<Long> list = Arrays.asList(beverages, snacks, extras);
            bonus = list.stream().min(Long::compare).get();
        }

        while(bonus > 0) {
            Item item = ItemDao.getInstance().get(Item.ITEM_NAME.EXTRA_FREE.name());
            // look for min price of extras
            item.setValue(receipt.getItems().stream()
                    .filter(a -> a.getType() == Item.ITEM_TYPE.EXTRA && ! a.getName().contentEquals(Item.ITEM_NAME.EXTRA_FREE.name()))
                    .mapToDouble(a -> a.getValue()).min().orElseThrow(NoSuchElementException::new) * -1.0);
            receipt.getItems().add(item);
            bonus--;
        }
    }

    public void printReceipt(Receipt receipt) {
        System.out.printf("Date: %s\n", receipt.getDateCreated());
        receipt.getItems().forEach(item -> {
            System.out.printf("%-20s: %2.02f\n", item.getName(), item.getValue());
        });
        System.out.printf("%-20s: %02.02f\n", "Total", receipt.getItems().stream().mapToDouble(a -> a.getValue()).sum());
    }
}
