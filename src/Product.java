public class Product implements Comparable<Product> {
    private final int ID;
    private final String name;
    private double price;
    private String category;
    private int stockQuantity;

    public Product(int ID, String name, double price, String category, int stockQuantity) {
        this.ID = ID;
        this.name = name;
        this.price = price;
        this.category = category;
        this.stockQuantity = stockQuantity;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void addToStockQuantity(int num) {
        stockQuantity += num;
    }

    public void removeToStockQuantity(int num) {
        stockQuantity -= num;
    }

    @Override
    public int compareTo(Product o) {
        return Double.compare(this.price, o.getPrice());
    }

    @Override
    public String toString() {
        return "ID: " + ID +
                "\nName: " + name +
                "\nPrice: " + price +
                "\nCategory: " + category +
                "\nStockQuantity: " + stockQuantity;
    }
}
