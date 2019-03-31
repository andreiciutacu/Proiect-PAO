package Products;

import java.time.LocalDate;

public class SSD extends Product {

    private int capacity;
    private String interfata;
    private int cache;
    protected float priceGB;

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCache() {
        return cache;
    }

    public void setCache(int cache) {
        this.cache = cache;
    }

    public float getPriceGB() {
        return priceGB;
    }

    public void setPriceGB(float priceGB) {
        this.priceGB = priceGB;
    }

    public SSD(float price, int stock, String manufacturer, int capacity, String interfata, int cache) {
        super(price, stock, manufacturer);
        this.capacity = capacity;
        this.interfata = interfata;
        this.cache = cache;
        if (super.getPrice() != 0 && capacity != 0) this.priceGB = price / capacity;
    }

    SSD(float price, int stock, LocalDate purchaseDate, String manufacturer, String color, int capacity, int cache) {
        super(price, stock, purchaseDate, manufacturer, color);
        this.capacity = capacity;
        this.cache = cache;
        if (super.getPrice() != 0 && capacity != 0) this.priceGB = price / capacity;
    }

    public SSD(float price, int stock, LocalDate purchaseDate, String manufacturer, String color, int capacity, String interfata, int cache) {
        super(price, stock, purchaseDate, manufacturer, color);
        this.capacity = capacity;
        this.interfata = interfata;
        this.cache = cache;
        if (super.getPrice() != 0 && capacity != 0) this.priceGB = price / capacity;
    }

    @Override
    public void GeneralInfo() {
        System.out.println(getManufacturer() + " " + getCapacity() + "GB");
        System.out.println(getPriceGB() + "RON/GB");
        super.GeneralInfo();
    }
}
