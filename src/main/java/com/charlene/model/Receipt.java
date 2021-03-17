package com.charlene.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public final class Receipt {
    private List<Item> items = new ArrayList<>();
    private Double total;
    private final LocalDateTime dateCreated = LocalDateTime.now();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Receipt() {
    }

    public List<Item> getItems() {
        return items;
    }

    public String getDateCreated() {
        return dateCreated.format(formatter);
    }
}
