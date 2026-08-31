import java.util.InputMismatchException;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Store store = new Store();
        boolean stay = true;
        int choice;
        System.out.println("Welcome to our E-commerce.");
        while (stay) {
            System.out.println("1. Add Product\n" +
                    "2. Remove Product\n" +
                    "3. Display All Products\n" +
                    "4. Search Product by ID\n" +
                    "5. Show All Categories\n" +
                    "6. Display Products Ordered by Price\n" +
                    "7. Create Order\n" +
                    "8. Add Item to Order\n" +
                    "9. Remove Item from Order\n" +
                    "10. Display Order\n" +
                    "11. Add Order to the Shipping List\n" +
                    "12. Ship Next Order\n" +
                    "13. Cancel Order\n" +
                    "14. Search Order by ID\n" +
                    "15. Add Review to a Product\n" +
                    "16. Show All Reviews for a Product\n" +
                    "17. Remove Out-of-Stock Products\n" +
                    "18. Display Orders Ordered by Total\n" +
                    "19. Exit");
            while (true) {
                System.out.println("Enter your choice.");
                try {
                    choice = scanner.nextInt();
                    scanner.nextLine();
                    if (choice >= 1 && choice <= 19)
                        break;
                    else
                        System.out.println("You can only enter valid integer between 1 and 19.");
                } catch (InputMismatchException e) {
                    scanner.nextLine();
                    System.out.println("Please enter only valid integer.");
                }
            }
            switch (choice) {
                case 1 -> {
                    String name, category;
                    int productID, stockQuantity;
                    double price;
                    while (true) {
                        System.out.println("Enter Product name");
                        name = scanner.nextLine();
                        if (name.trim().length() > 3)
                            break;
                        else
                            System.out.println("The name should be greater than 3.");
                    }
                    while (true) {
                        System.out.println("Enter Product ID.");
                        try {
                            productID = scanner.nextInt();
                            scanner.nextLine();
                            if (productID >= 0) {
                                if (store.CanUseThisProductID(productID))
                                    break;
                                else
                                    System.out.println("This ID already exist or was exists before.");
                            } else {
                                System.out.println("Please Enter ID greater than 0.");
                            }
                        } catch (InputMismatchException e) {
                            scanner.nextLine();
                            System.out.println("Please enter only valid integer.");
                        }
                    }
                    while (true) {
                        System.out.println("Enter Product Category");
                        category = scanner.nextLine();
                        if (!category.trim().isEmpty())
                            break;
                        else
                            System.out.println("The category should be greater than 0.");
                    }
                    while (true) {
                        System.out.println("Enter Stock Quantity.");
                        try {
                            stockQuantity = scanner.nextInt();
                            scanner.nextLine();
                            if (stockQuantity >= 0) {
                                break;
                            } else {
                                System.out.println("Please Enter stockQuantity greater than 0.");
                            }
                        } catch (InputMismatchException e) {
                            scanner.nextLine();
                            System.out.println("Please enter only valid integer.");
                        }
                    }
                    while (true) {
                        System.out.println("Enter The Product price.");
                        try {
                            price = scanner.nextInt();
                            scanner.nextLine();
                            if (price >= 0) {
                                break;
                            } else {
                                System.out.println("Please Enter price greater than 0.");
                            }
                        } catch (InputMismatchException e) {
                            scanner.nextLine();
                            System.out.println("Please enter only valid integer.");
                        }
                    }
                    store.addProduct(productID, name, price, category, stockQuantity);
                }
                case 2 -> {
                    int productID;
                    while (true) {
                        System.out.println("Enter Product ID.");
                        try {
                            productID = scanner.nextInt();
                            scanner.nextLine();
                            if (productID >= 0) {
                                break;
                            } else {
                                System.out.println("Please Enter ID greater than 0.");
                            }
                        } catch (InputMismatchException e) {
                            scanner.nextLine();
                            System.out.println("Please enter only valid integer.");
                        }
                    }
                    store.removeProduct(productID);
                }
                case 3 -> {
                    store.displayAllProducts();
                }
                case 4 -> {
                    int productID;
                    while (true) {
                        System.out.println("Enter Product ID.");
                        try {
                            productID = scanner.nextInt();
                            scanner.nextLine();
                            if (productID >= 0) {
                                break;
                            } else {
                                System.out.println("Please Enter ID greater than 0.");
                            }
                        } catch (InputMismatchException e) {
                            scanner.nextLine();
                            System.out.println("Please enter only valid integer.");
                        }
                    }
                    store.searchProductById(productID);
                }
                case 5 -> {
                    store.showAllCategories();
                }
                case 6 -> {
                    store.displayProductsOrderedByPrice();
                }
                case 7 -> {
                    String customerName;
                    int orderID;
                    while (true) {
                        System.out.println("Enter Customer name");
                        customerName = scanner.nextLine();
                        if (customerName.trim().length() > 3)
                            break;
                        else
                            System.out.println("The name should be greater than 3.");
                    }
                    while (true) {
                        System.out.println("Enter Order ID.");
                        try {
                            orderID = scanner.nextInt();
                            scanner.nextLine();
                            if (orderID >= 0) {
                                if (store.CanUseThisOrderID(orderID))
                                    break;
                                else
                                    System.out.println("This ID already exist or was exists before.");
                            } else {
                                System.out.println("Please Enter ID greater than 0.");
                            }
                        } catch (InputMismatchException e) {
                            scanner.nextLine();
                            System.out.println("Please enter only valid integer.");
                        }
                    }
                    store.createOrder(orderID, customerName);
                }
                case 8 -> {
                    int orderID, productID, quantity;
                    while (true) {
                        System.out.println("Enter Order ID.");
                        try {
                            orderID = scanner.nextInt();
                            scanner.nextLine();
                            if (orderID >= 0) {
                                break;
                            } else {
                                System.out.println("Please Enter ID greater than 0.");
                            }
                        } catch (InputMismatchException e) {
                            scanner.nextLine();
                            System.out.println("Please enter only valid integer.");
                        }
                    }
                    while (true) {
                        System.out.println("Enter Product ID.");
                        try {
                            productID = scanner.nextInt();
                            scanner.nextLine();
                            if (productID >= 0) {
                                break;
                            } else {
                                System.out.println("Please Enter ID greater than 0.");
                            }
                        } catch (InputMismatchException e) {
                            scanner.nextLine();
                            System.out.println("Please enter only valid integer.");
                        }
                    }
                    while (true) {
                        System.out.println("Enter Quantity.");
                        try {
                            quantity = scanner.nextInt();
                            scanner.nextLine();
                            if (quantity >= 0) {
                                break;
                            } else {
                                System.out.println("Please Enter Quantity greater than 0.");
                            }
                        } catch (InputMismatchException e) {
                            scanner.nextLine();
                            System.out.println("Please enter only valid integer.");
                        }
                    }
                    store.addItemToOrder(orderID, productID, quantity);
                }
                case 9 -> {
                    int orderID, productID;
                    while (true) {
                        System.out.println("Enter Order ID.");
                        try {
                            orderID = scanner.nextInt();
                            scanner.nextLine();
                            if (orderID >= 0) {
                                break;
                            } else {
                                System.out.println("Please Enter ID greater than 0.");
                            }
                        } catch (InputMismatchException e) {
                            scanner.nextLine();
                            System.out.println("Please enter only valid integer.");
                        }
                    }
                    while (true) {
                        System.out.println("Enter Product ID.");
                        try {
                            productID = scanner.nextInt();
                            scanner.nextLine();
                            if (productID >= 0) {
                                break;
                            } else {
                                System.out.println("Please Enter ID greater than 0.");
                            }
                        } catch (InputMismatchException e) {
                            scanner.nextLine();
                            System.out.println("Please enter only valid integer.");
                        }
                    }
                    store.removeItemFromOrder(orderID, productID);
                }
                case 10 -> {
                    int orderID;
                    while (true) {
                        System.out.println("Enter Order ID.");
                        try {
                            orderID = scanner.nextInt();
                            scanner.nextLine();
                            if (orderID >= 0) {
                                break;
                            } else {
                                System.out.println("Please Enter ID greater than 0.");
                            }
                        } catch (InputMismatchException e) {
                            scanner.nextLine();
                            System.out.println("Please enter only valid integer.");
                        }
                    }
                    store.displayOrder(orderID);
                }
                case 11 -> {
                    int orderID;
                    while (true) {
                        System.out.println("Enter Order ID.");
                        try {
                            orderID = scanner.nextInt();
                            scanner.nextLine();
                            if (orderID >= 0) {
                                break;
                            } else {
                                System.out.println("Please Enter ID greater than 0.");
                            }
                        } catch (InputMismatchException e) {
                            scanner.nextLine();
                            System.out.println("Please enter only valid integer.");
                        }
                    }
                    store.addOrderToShipping(orderID);
                }
                case 12 -> {
                    store.shipNextOrder();
                }
                case 13 -> {
                    int orderID;
                    while (true) {
                        System.out.println("Enter Order ID.");
                        try {
                            orderID = scanner.nextInt();
                            scanner.nextLine();
                            if (orderID >= 0) {
                                break;
                            } else {
                                System.out.println("Please Enter ID greater than 0.");
                            }
                        } catch (InputMismatchException e) {
                            scanner.nextLine();
                            System.out.println("Please enter only valid integer.");
                        }
                    }
                    store.cancelOrder(orderID);
                }
                case 14 -> {
                    int orderID;
                    while (true) {
                        System.out.println("Enter Order ID.");
                        try {
                            orderID = scanner.nextInt();
                            scanner.nextLine();
                            if (orderID >= 0) {
                                break;
                            } else {
                                System.out.println("Please Enter ID greater than 0.");
                            }
                        } catch (InputMismatchException e) {
                            scanner.nextLine();
                            System.out.println("Please enter only valid integer.");
                        }
                    }
                    store.searchOrderById(orderID);
                }
                case 15 -> {
                    int productID;
                    String customerName, comment;
                    while (true) {
                        System.out.println("Enter Product ID.");
                        try {
                            productID = scanner.nextInt();
                            scanner.nextLine();
                            if (productID >= 0) {
                                break;
                            } else {
                                System.out.println("Please Enter ID greater than 0.");
                            }
                        } catch (InputMismatchException e) {
                            scanner.nextLine();
                            System.out.println("Please enter only valid integer.");
                        }
                    }
                    while (true) {
                        System.out.println("Enter Customer name");
                        customerName = scanner.nextLine();
                        if (customerName.trim().length() > 3)
                            break;
                        else
                            System.out.println("The name should be greater than 3.");
                    }
                    while (true) {
                        System.out.println("Enter Your comment");
                        comment = scanner.nextLine();
                        if (!comment.trim().isEmpty())
                            break;
                        else
                            System.out.println("The comment shouldn't be empty.");
                    }
                    store.addReview(productID, customerName, comment);
                }
                case 16 -> {
                    int productID;
                    while (true) {
                        System.out.println("Enter Product ID.");
                        try {
                            productID = scanner.nextInt();
                            scanner.nextLine();
                            if (productID >= 0) {
                                break;
                            } else {
                                System.out.println("Please Enter ID greater than 0.");
                            }
                        } catch (InputMismatchException e) {
                            scanner.nextLine();
                            System.out.println("Please enter only valid integer.");
                        }
                    }
                    store.showReviewsForProduct(productID);
                }
                case 17 -> {
                    store.removeOutOfStockProducts();
                }
                case 18 -> {
                    store.displayOrdersOrderedByTotal();
                }
                case 19 -> {
                    System.out.println("Thanks for visiting our E-commerce.");
                    stay = false;
                }
            }
        }

    }
}