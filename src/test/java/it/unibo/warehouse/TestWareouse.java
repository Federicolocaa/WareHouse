package it.unibo.warehouse;

// Questi import daranno errore finché non avrai creato le classi!
import it.unibo.warehouse.api.InventoryException;
import it.unibo.warehouse.api.Warehouse;
import it.unibo.warehouse.impl.WarehouseImpl;
import it.unibo.warehouse.model.Product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TestWarehouse {

    private Warehouse warehouse;
    private final Product tv = new Product("SKU-001", "Samsung 55\" TV");
    private final Product phone = new Product("SKU-002", "Google Pixel");
    private final Product book = new Product("SKU-003", "OOP in Java");

    @BeforeEach
    void setUp() {
        warehouse = new WarehouseImpl();
        warehouse.addProduct(tv, 10);
        warehouse.addProduct(phone, 20);
    }

    @Test
    void testAddProduct() {
        warehouse.addProduct(book, 5);
        assertEquals(3, warehouse.allProducts().size());
        assertTrue(warehouse.allProducts().contains(book));
    }

    @Test
    void testAddExistingProductUpdatesStock() {
        Product tv2 = new Product("SKU-001", "Altra TV");
        warehouse.addProduct(tv2, 5); 
        assertEquals(2, warehouse.allProducts().size(), "Non deve aggiungere un nuovo prodotto");
        assertDoesNotThrow(() -> {
            assertEquals(15, warehouse.checkStock("SKU-001"), "Dovrebbe sommare lo stock");
        });
    }

    @Test
    void testAddNegativeStockFails() {
        assertThrows(IllegalArgumentException.class, () -> {
            warehouse.addProduct(book, -5);
        }, "Aggiungere stock negativo deve lanciare IllegalArgumentException (unchecked)");
    }

    @Test
    void testTakeProductSuccess() throws InventoryException {
        warehouse.takeProduct("SKU-001", 3);
        assertEquals(7, warehouse.checkStock("SKU-001"));
    }

    @Test
    void testTakeProductNegativeFails() {
        assertThrows(IllegalArgumentException.class, () -> {
            warehouse.takeProduct("SKU-001", 0);
        }, "Prendere 0 o meno deve lanciare IllegalArgumentException (unchecked)");
    }

    @Test
    void testTakeProductNotFoundFails() {
        assertThrows(InventoryException.class, () -> {
            warehouse.takeProduct("SKU-999", 1);
        }, "Prendere uno SKU inesistente deve lanciare InventoryException (checked)");
    }

    @Test
    void testTakeProductOutOfStockFails() {
        assertThrows(InventoryException.class, () -> {
            warehouse.takeProduct("SKU-002", 50);
        }, "Prendere più dello stock deve lanciare InventoryException (checked)");
    }
}