package Products;
import java.time.LocalDate;
public class Product {
    private float price;
    private int Stock;
    private LocalDate purchaseDate;
    private String manufacturer;
    private String color;
    private float discount;

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int stock) {
        Stock = stock;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Product(float price, int stock, String manufacturer) {
        this.price = price;
        Stock = stock;
        this.manufacturer = manufacturer;
    }

    public Product(float price, int stock, LocalDate purchaseDate, String manufacturer, String color) {
        this.price = price;
        Stock += stock;
        this.purchaseDate = purchaseDate;
        this.manufacturer = manufacturer;
        this.color = color;
    }

    public void GeneralInfo(){
        if (checkStock() != 0){
            if (getColor() != null)
                System.out.println("Color: " + this.getColor() + "\n" + this.getPrice() + "\n");
            else
                System.out.println(this.getPrice() + "\n");
        }

    }

    public int checkStock(){
        if (getStock() == 0){
            System.out.println("Out of stock");
            return 0;
        }
        else{
            System.out.println("Current stock is : " + getStock() );
            return getStock();
        }

    }
}
