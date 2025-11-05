# Istruzioni Laboratorio: Warehouse

## Parte 1: Il Modello `Product`
1.  Crea la classe `Product` nel package `it.unibo.warehouse.model`.
2.  Deve avere i campi `private final String sku` (codice prodotto) e `private final String name`.
3.  Implementa un costruttore che inizializza questi campi.
4.  Implementa i getter `getSku()` e `getName()`.
5.  **`equals()` e `hashCode()`**: Fai l'override di questi due metodi. Due `Product` sono considerati uguali **solo se i loro `sku` sono uguali**.

## Parte 2: L'Eccezione `InventoryException`
1.  Crea una **checked exception** `InventoryException` nel package `it.unibo.warehouse.api`.
2.  Deve **estendere `Exception`**.
3.  Deve avere un costruttore che accetta un `String message` e lo passa a `super(message)`.

## Parte 3: L'Interfaccia `Warehouse`
1.  Crea l'interfaccia `Warehouse` nel package `it.unibo.warehouse.api`.
2.  Deve definire i seguenti metodi:
    * `void addProduct(Product product, int quantity)` (Lancia `IllegalArgumentException` se `quantity < 0`).
    * `Set<Product> allProducts()` (Restituisce un set di tutti i prodotti).
    * `int checkStock(String sku)` (Lancia `InventoryException` se lo SKU non è trovato).
    * `void takeProduct(String sku, int quantity)` (Lancia `IllegalArgumentException` se `quantity <= 0`, lancia `InventoryException` se SKU non trovato o se `quantity > stock`).

## Parte 4: L'Implementazione `WarehouseImpl`
1.  Crea la classe `WarehouseImpl` nel package `it.unibo.warehouse.impl` che **implementa `Warehouse`**.
2.  **Struttura Dati:** Usa una `Map<Product, Integer>` per memorizzare la quantità (`Integer`) di ogni `Product` (la chiave).
3.  Implementa tutti i metodi dell'interfaccia usando la mappa.
    * *Suggerimento per `checkStock` e `takeProduct`:* Come cerchi un prodotto nella mappa usando solo una `String sku`? Dovrai creare un "finto" `Product` (`new Product(sku, "")`) da usare con `map.get()`. Questo funziona solo perché hai implementato `equals/hashCode` correttamente!