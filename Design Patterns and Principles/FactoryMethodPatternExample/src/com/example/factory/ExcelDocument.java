package com.example.factory;

public class ExcelDocument implements Document {
    @Override
    public void open() {
        System.out.println("Opening Excel Document (.xlsx)... Initializing grid cells and formulas.");
    }

    @Override
    public void close() {
        System.out.println("Saving grid configurations and closing Excel Document.");
    }
}