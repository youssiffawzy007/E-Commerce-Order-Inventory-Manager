import java.util.*;

public class Store {
    private ArrayList<Product> products;
    private HashMap<Integer, Product> productsById;
    private HashMap<Integer, Product> removedProducts;
    private HashSet<String> categories;
    private HashMap<Integer, Order> ordersById;
    private LinkedList<Order> shippingQueue;
    private LinkedHashMap<Integer, Order> deliveredOrders;
    private ArrayList<Review> reviews;

    public Store() {
        this.products = new ArrayList<>();
        this.productsById = new HashMap<>();
        this.removedProducts = new HashMap<>();
        this.categories = new HashSet<>();
        this.ordersById = new HashMap<>();
        this.shippingQueue = new LinkedList<>();
        this.deliveredOrders = new LinkedHashMap<>();
        this.reviews = new ArrayList<>();
    }

    private void deleteProductEverywhere(Product product) {
        if (productsById.containsKey(product.getID())) {
            if (!isProductUsedInAnyOrder(product.getID())) {
                removedProducts.put(product.getID(), product);
                products.remove(product);
                productsById.remove(product.getID());
                System.out.println("The Product removed successfully.");
            } else
                System.out.println("This Product is using now in Order. \nI can't remove it now. pls tray again later.");
        } else
            System.out.println("This Product isn't found.");
    }

    private boolean isProductUsedInAnyOrder(int productId) {
        for (Order order : ordersById.values()) {

            if (order.getStatus() == OrderStatus.PENDING ||
                    order.getStatus() == OrderStatus.SHIPPED) {

                for (CartItem item : order.getItems()) {

                    if (item.getProduct().getID() == productId) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean CanUseThisProductID(int productID) {
        return !(productsById.containsKey(productID) || removedProducts.containsKey(productID));
    }

    public boolean CanUseThisOrderID(int orderID) {
        return !ordersById.containsKey(orderID);
    }

    public void addProduct(int ID, String name, double price, String category, int stockQuantity) {
        if (productsById.containsKey(ID)) {
            System.out.println("This Product ID already exists.");
            return;
        }
        if (removedProducts.containsKey(ID)) {
            System.out.println("This Product ID was exists before.");
            return;
        }
        Product product = new Product(ID, name, price, category, stockQuantity);
        products.add(product);
        productsById.put(ID, product);
        categories.add(category);
        System.out.println("This Product created successfully.");
    }

    public void removeProduct(int id) {
        if (productsById.containsKey(id)) {
            deleteProductEverywhere(productsById.get(id));
        } else
            System.out.println("This ID isn't exist.");
    }

    public void displayAllProducts() {
        int counter = 1;
        if (!products.isEmpty()) {
            System.out.println("Products Details: ");
            for (Product product : products) {
                System.out.println("Product " + counter++);
                System.out.println(product);
            }
        } else
            System.out.println("There are no Products yet.");
    }

    public void searchProductById(int productID) {
        Product product = productsById.get(productID);
        if (product == null) {
            System.out.println("There are no Product in this ID.");
            return;
        }
        System.out.println("Product Details: ");
        System.out.println(product);
    }

    public void showAllCategories() {
        if (!categories.isEmpty()) {
            System.out.println("Categories :");
            categories.forEach(System.out::println);
        } else
            System.out.println("There are no categories yet.");
    }

    public void displayProductsOrderedByPrice() {
        if (!products.isEmpty()) {
            products.stream()
                    .sorted()
                    .forEach(System.out::println);
        } else
            System.out.println("There are no Products yet.");
    }

    public void createOrder(int orderID, String customerName) {
        if (ordersById.containsKey(orderID)) {
            System.out.println("This ID already exists.");
            return;
        }
        Order order = new Order(orderID, customerName);
        ordersById.put(orderID, order);
        System.out.println("This order created successfully.");
    }

    public void addItemToOrder(int orderID, int productID, int quantity) {
        if (!ordersById.containsKey(orderID)) {
            System.out.println("This Order ID isn't exist.");
            return;
        }
        if (!productsById.containsKey(productID)) {
            System.out.println("This Product ID isn't exist.");
            return;
        }

        if (productsById.get(productID).getStockQuantity() < quantity) {
            System.out.println("If there is a sufficient number of products to complete the process.");
            System.out.println("Only " + productsById.get(productID).getStockQuantity() + " remain.");
            return;
        }
        if (ordersById.get(orderID).getStatus() != OrderStatus.PENDING){
            System.out.println("This Order statue: " + ordersById.get(orderID).getStatus().name());
            return;
        }
        ordersById.get(orderID).addItem(productsById.get(productID), quantity);
        productsById.get(productID).removeToStockQuantity(quantity);
        System.out.println("This Product added successfully to the Order.");
    }

    public void removeItemFromOrder(int orderID, int productID) {
        if (!ordersById.containsKey(orderID)) {
            System.out.println("This Order ID isn't exist.");
            return;
        }
        if (!productsById.containsKey(productID)) {
            System.out.println("This Product ID isn't exist.");
            return;
        }
        if (ordersById.get(orderID).getStatus() != OrderStatus.PENDING) {
            System.out.println("This Order statue: " + ordersById.get(orderID).getStatus().name());
            return;
        }
        //ADD Item quantity Again and remove The Item from The Order.
        productsById.get(productID).addToStockQuantity(ordersById.get(orderID).removeItem(productsById.get(productID)));
        System.out.println("This Product removed successfully from the Order.");
    }

    public void displayOrder(int orderID) {
        if (!ordersById.containsKey(orderID)) {
            System.out.println("This Order ID isn't exist.");
            return;
        }
        System.out.println("Order Details: ");
        ordersById.get(orderID).displayOrder();
    }

    public void addOrderToShipping(int orderID) {
        if (!ordersById.containsKey(orderID)) {
            System.out.println("This Order ID isn't exist.");
            return;
        }
        if (ordersById.get(orderID).getStatus() != OrderStatus.PENDING) {
            System.out.println("This Order statue: " + ordersById.get(orderID).getStatus().name());
            return;
        }
        ordersById.get(orderID).updateStatus(OrderStatus.SHIPPED);
        shippingQueue.add(ordersById.get(orderID));
        System.out.println("This Order statue is Shipping now.");
    }

    public void shipNextOrder() {
        if (!shippingQueue.isEmpty()) {
            Order order = shippingQueue.getFirst();
            shippingQueue.removeFirst();
            order.updateStatus(OrderStatus.DELIVERED);
            deliveredOrders.put(order.getOrderId(), order);
            System.out.println("This Order statue is Delivered now.");
        } else
            System.out.println("There are no shipping Orders yet.");
    }

    public void cancelOrder(int orderID) {
        if (!ordersById.containsKey(orderID)) {
            System.out.println("This Order ID isn't exist.");
            return;
        }
        if (ordersById.get(orderID).getStatus() == OrderStatus.CANCELLED ||
                ordersById.get(orderID).getStatus() == OrderStatus.DELIVERED) {
            System.out.println("This Order statue: " + ordersById.get(orderID).getStatus().name());
            return;
        }
        if (ordersById.get(orderID).getStatus() == OrderStatus.SHIPPED) {
            shippingQueue.remove(ordersById.get(orderID));
        }
        for (CartItem item : ordersById.get(orderID).getItems()) {
            // Restoring the product if it was previously deleted via a method removeOutOfStockProducts()
            if (removedProducts.containsKey(item.getProduct().getID())) {
                productsById.put(item.getProduct().getID(), item.getProduct());
                products.add(item.getProduct());
                removedProducts.remove(item.getProduct().getID());
            }
            // Adding The quantity to the Product again.
            productsById.get(item.getProduct().getID()).addToStockQuantity(item.getQuantity());
        }
        ordersById.get(orderID).updateStatus(OrderStatus.CANCELLED);
        System.out.println("This Order is Cancelled now.");
    }

    public void searchOrderById(int orderID) {
        if (!ordersById.containsKey(orderID)) {
            System.out.println("This Order ID isn't exist.");
            return;
        }
        System.out.println("Order details: ");
        ordersById.get(orderID).displayOrder();
    }

    public void addReview(int productID, String customerName, String comment) {
        if (!productsById.containsKey(productID)) {
            System.out.println("This Product ID isn't exist.");
            return;
        }
        Review review = new Review(productID, customerName, comment);
        reviews.add(review);
        System.out.println("The review added successfully.");
    }

    public void showReviewsForProduct(int productID) {
        int counter = 1;
        if (!productsById.containsKey(productID)) {
            System.out.println("This Product ID isn't exist.");
            return;
        }
        for (Review review : reviews) {
            if (review.getProductId() == productID) {
                System.out.println("Review " + counter++);
                System.out.println("Customer Name: " + review.getCustomerName());
                System.out.println("Comment: " + review.getComment());
            }
        }
        if (counter == 1)
            System.out.println("There are no Reviews on this Product yet.");
    }

    public void removeOutOfStockProducts() {
        Iterator<Product> iterator = products.iterator();

        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getStockQuantity() == 0) {
                deleteProductEverywhere(product);
            }
        }
    }

    public void displayOrdersOrderedByTotal() {
        List<Order> sortedOrders = new ArrayList<>(ordersById.values());

        sortedOrders.stream()
                .sorted(Comparator.comparingDouble(Order::calculateTotal))
                .forEach((Order::displayOrder));
    }


}
