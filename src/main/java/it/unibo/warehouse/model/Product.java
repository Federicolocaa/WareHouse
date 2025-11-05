package it.unibo.warehouse.model;

import java.util.Objects;

public class Product {

    private final String sku;
    private final String name;

    public Product(final String sku, final String name){
        this.sku = sku;
        this.name = name;
    }

    public String getSku(){
        return this.sku;
    }

    public String getName(){
        return this.name;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){return true;}
        if (!(obj instanceof Product)) {
            return false;   
        }
        Product otherSku = (Product) obj;
        return java.util.Objects.equals(otherSku.getSku(), this.sku);
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.sku);
    }
}
