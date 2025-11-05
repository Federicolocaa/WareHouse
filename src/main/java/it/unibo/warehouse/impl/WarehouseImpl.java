package it.unibo.warehouse.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import it.unibo.warehouse.api.InventoryException;
import it.unibo.warehouse.api.Warehouse;
import it.unibo.warehouse.model.Product;

public class WarehouseImpl implements Warehouse {
    
    // Il campo 'stock' (la mappa) è corretto
    private final Map<Product, Integer> stock = new HashMap<>();

    @Override
    public void addProduct(Product product, int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantità negativa!");
        }
        // 1. Prendi la quantità attuale (o 0 se il prodotto è nuovo)
        final int currentQuantity = this.stock.getOrDefault(product, 0);
        // 2. Aggiungi la nuova quantità e aggiorna la mappa
        this.stock.put(product, currentQuantity + quantity);
    }

    @Override
    public Set<Product> allProducts() {
        return this.stock.keySet();
    }

    @Override
    // ERRORE CORRETTO: Aggiunta la clausola 'throws' obbligatoria
    public int checkStock(String sku) throws InventoryException {
        // La tua logica "finto prodotto" era quasi perfetta!
        final Product keyToFind = new Product(sku, "");
        final Integer quantity = this.stock.get(keyToFind); // Usa la chiave 'Product'

        if (quantity == null) {
            throw new InventoryException("Prodotto sku: " + sku + ", non trovato!");
        } else {
            return quantity;
        }
    }

    @Override
    // ERRORE CORRETTO: Aggiunta la clausola 'throws' obbligatoria
    public void takeProduct(String sku, int quantity) throws InventoryException {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantità deve essere positiva!");
        }
        
        // 1. Riusiamo la nostra logica per trovare lo stock attuale
        //    Questo lancia InventoryException se lo SKU non è trovato!
        final int currentStock = this.checkStock(sku);

        // 2. Controlliamo se ne abbiamo abbastanza
        if (quantity > currentStock) {
            throw new InventoryException(
                "Stock insufficiente per " + sku + ": richiesti " + quantity + ", disponibili " + currentStock
            );
        }

        // 3. Aggiorniamo lo stock (dobbiamo ricreare la chiave fittizia)
        final Product keyToUpdate = new Product(sku, "");
        this.stock.put(keyToUpdate, currentStock - quantity);
    }
}