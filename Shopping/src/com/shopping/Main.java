import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Creating fixed products for the product catalog
        ProductCatalog productCatalog = new ProductCatalog();
        Product p1 = new Product(101, "Iphone 13", 50.0, 10);
        Product p2 = new Product(102, "Iphone 14", 70.0, 10);
        Product p3 = new Product(103, "Iphone 15", 85.0, 10);
        Product p4 = new Product(104, "Laptop ", 25.0, 20);
        Product p5 = new Product(105, "Camera", 100.0, 5);
        productCatalog.addProduct(p1);
        productCatalog.addProduct(p2);
        productCatalog.addProduct(p3);
        productCatalog.addProduct(p4);
        productCatalog.addProduct(p5);

        // Taking input for user details
        System.out.print("Enter your name: ");
        String customerName = scanner.nextLine();
        System.out.print("Enter your order ID: ");
        int orderID = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character left by nextInt()
        Order order = new Order(orderID, customerName, LocalDate.now());

        // Displaying available products in the product catalog
        System.out.println("\nProduct Catalog:");
        for (Product product : productCatalog.getAllProducts()) {
            product.displayDetails();
            System.out.println("--------------");
        }

        // Taking input for products in the shopping cart
        ShoppingCart shoppingCart = new ShoppingCart();
        System.out.println("\nAdding products to the shopping cart (enter 0 to stop):");

        while (true) {
            System.out.print("Enter Product ID to add to the cart: ");
            int productID = scanner.nextInt();
            if (productID == 0) {
                break;
            }
            Product product = productCatalog.getProductByID(productID);
            if (product != null) {
                shoppingCart.addProduct(product);
                System.out.println("Product added to the cart.");
            }

            else {
                System.out.println("Product not found in the catalog.");
            }
        }
        System.out.println("Enter Product ID to remove the product from cart");
        int productIDs=scanner.nextInt();
        Product product1=productCatalog.getProductByID(productIDs);
        if (product1 != null){
            shoppingCart.removeProduct(product1);
            System.out.println("Product has been removed");
        }



        // Displaying shopping cart details
        //System.out.println("\nShopping Cart Details:");
        shoppingCart.displayCartDetails();

        // Taking input for products in the order
        System.out.println("\nPlacing order from the shopping cart:");
        for (Product product : shoppingCart.getProducts()) {
            System.out.print("Enter quantity for Product " + product.getProductID() + ": ");
            int quantity = scanner.nextInt();
            if (quantity <= product.getQuantityAvailable()) {
                product.updateQuantity(quantity); // Update the quantity in the product
                order.addProduct(product, quantity); // Add the product with the specified quantity to the order
            } else {
                System.out.println("Not enough quantity available for product: " + product.getProductName());
                System.out.print("Enter again quantity for Product " + product.getProductID() + ": ");
                quantity = scanner.nextInt();
            }
            
           
        }

        // Displaying order details
        System.out.println("\nOrder Details:");
        order.displayOrderDetails();

        
        CustomerStatistics customerStats = new CustomerStatistics();
        customerStats.addOrder(order);

        // Displaying customer statistics
        System.out.println("\nCustomer Statistics:");
        System.out.println("Average Order Amount: $" + customerStats.calculateAverageOrderAmount());
        System.out.println("Highest Spending Customer: " + customerStats.getHighestSpendingCustomer());
        
        customerStats.generateCustomerReport();

        scanner.close();


    }



}