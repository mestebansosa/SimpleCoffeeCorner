package com.charlene;

import com.charlene.service.ItemsFiller;
import com.charlene.service.ReceiptService;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class Application {

    public static void main(String[] args) {
        new ItemsFiller();

        Path path = Paths.get(Arrays.stream(args).findFirst().get());

        new ReceiptService().getReceipt(path);
    }

}
