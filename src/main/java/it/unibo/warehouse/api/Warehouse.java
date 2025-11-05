package it.unibo.warehouse.api;

import java.util.Set;

import it.unibo.warehouse.model.Product;

public interface Warehouse{

    void addProduct(Product product, int quantity);
    
    Set<Product> allProducts();

    int checkStock(String sku) throws InventoryException;

    void takeProduct(String sku, int quantity) throws InventoryException;
}