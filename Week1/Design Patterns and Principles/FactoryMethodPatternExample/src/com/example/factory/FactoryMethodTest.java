package com.example.factory;

public class FactoryMethodTest {
    public static void main(String[] args) {
        System.out.println("--- Testing Factory Method Pattern Implementation ---\n");

        // 1. Instantiate Concrete Factories
        DocumentFactory wordFactory = new WordDocumentFactory();
        DocumentFactory pdfFactory = new PdfDocumentFactory();
        DocumentFactory excelFactory = new ExcelDocumentFactory();

        // 2. Generate specialized objects via their respective factories
        System.out.println("[Testing Word Factory Production]");
        Document wordDoc = wordFactory.createDocument();
        wordDoc.open();
        wordDoc.close();
        System.out.println();

        System.out.println("[Testing PDF Factory Production]");
        Document pdfDoc = pdfFactory.createDocument();
        pdfDoc.open();
        pdfDoc.close();
        System.out.println();

        System.out.println("[Testing Excel Factory Production]");
        Document excelDoc = excelFactory.createDocument();
        excelDoc.open();
        excelDoc.close();
        System.out.println("\nSUCCESS: Factory Method pattern safely generated polymorphic variations!");
    }
}
