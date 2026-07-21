package com.example.factory;

public abstract class DocumentFactory {
    // The Factory Method
    public abstract Document createDocument();

    // An optional helper method demonstrating core logic operating on the interface
    public void manageDocument() {
        Document doc = createDocument();
        doc.open();
        // Custom core system operations could live here
        doc.close();
    }
}