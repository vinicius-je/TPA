package entities;

public class Product implements Comparable<Product> {
    private String name;
    private Double price;
    private Integer amountStock;

    public Product(String name, Double price, Integer amountStock){
        this.name = name;
        this.price = price;
        this.amountStock = amountStock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getAmountStock() {
        return amountStock;
    }

    public void setAmountStock(Integer amount) {
        this.amountStock = amount;
    } 

    @Override
    public String toString(){
        return "Produt: " + this.name + "\n" + 
                "Price: " + this.price + "\n" +
                "Amount in stock: " + this.amountStock + "\n\n"; 
    }

    @Override
    public int compareTo(Product other) {
        return Integer.compare(this.amountStock, other.getAmountStock());
    }
}
