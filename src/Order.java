import java.util.ArrayList;

public class Order {
    private final int orderId;
    private final String customerName;
    private ArrayList<CartItem> items;
    private double total;
    private OrderStatus status;

    public Order(int orderId, String customerName) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.status = OrderStatus.PENDING;
        items = new ArrayList<>();
        total = 0;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public ArrayList<CartItem> getItems() {
        return items;
    }


    public OrderStatus getStatus() {
        return status;
    }


    public void addItem(Product product, int quantity) {
        if (this.status == OrderStatus.PENDING) {
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).getProduct() == product) {
                    items.get(i).setQuantity(items.get(i).getQuantity() + quantity);
                    this.total += items.get(i).getProduct().getPrice() * quantity;
                    System.out.println("The Item added successfully.");
                    return;
                }
            }
            CartItem item = new CartItem(product, quantity);
            items.add(item);
            total += item.calculateSubtotal();
            System.out.println("The Item added successfully.");
        } else
            System.out.println("This Order statue: " + status.name());
    }

    public int removeItem(Product product) {
        if (this.status == OrderStatus.PENDING) {
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).getProduct() == product) {
                    int quantity = items.get(i).getQuantity();
                    total -= items.get(i).calculateSubtotal();
                    items.remove(items.get(i));
                    System.out.println("This Item removed successfully.");
                    return quantity;
                }
            }
            System.out.println("This product isn't found.");
            return 0;
        } else
            System.out.println("This Order statue: " + status.name());
        return 0;
    }

    public double calculateTotal() {
        return total;
    }

    public void displayOrder() {
        int counter = 1;
        System.out.println("ID: " + orderId +
                "\nCustomer Name: " + customerName);
        System.out.println();
        System.out.println("Items :");
        for (CartItem item : items) {
            System.out.println("Item " + counter);
            System.out.println("Product Name:" + item.getProduct().getName());
            System.out.println("Product Price:" + item.getProduct().getPrice());
            System.out.println("Quantity: " + item.getQuantity());
            System.out.println("Subtotal: "+ item.calculateSubtotal());
            System.out.println();
        }
        System.out.println("Total: " + total);
    }

    public void updateStatus(OrderStatus status) {
        this.status = status;
    }
}
