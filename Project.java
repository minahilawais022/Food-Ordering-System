import java.util.*;
public class Project {
    //restraunts
        static void displayRestaurants() {
            System.out.println("Available Restaurants:");
            System.out.println("1. Burger House");
            System.out.println("2. Pizza Palace");
            System.out.println("3. Desi Delight");
        }

        //menu
        static void displayMenu(int choice) {
            if (choice == 1) {
                System.out.println("\nBurger House Menu");
                System.out.println("101 Zinger Burger - 550");
                System.out.println("102 Beef Burger   - 650");
                System.out.println("103 Fries         - 250");
            } else if (choice == 2) {
                System.out.println("\nPizza Palace Menu");
                System.out.println("201 Chicken Pizza - 1200");
                System.out.println("202 Fajita Pizza  - 1400");
                System.out.println("203 Garlic Bread  - 300");
            } else if (choice == 3) {
                System.out.println("\nDesi Delight Menu");
                System.out.println("301 Chicken Karahi - 1100");
                System.out.println("302 Biryani        - 350");
                System.out.println("303 Raita          - 80");
            }
        }

        //item code
        static boolean isValidItem(int restaurant, int code) {
            if (restaurant == 1)
                return code == 101 || code == 102 || code == 103;
            else if (restaurant == 2)
                return code == 201 || code == 202 || code == 203;
            else if (restaurant == 3)
                return code == 301 || code == 302 || code == 303;
            return false;
        }

        //price
        static int getPrice(int restaurant, int code) {
            if (restaurant == 1) {
                if (code == 101) return 550;
                if (code == 102) return 650;
                if (code == 103) return 250;
            } else if (restaurant == 2) {
                if (code == 201) return 1200;
                if (code == 202) return 1400;
                if (code == 203) return 300;
            } else if (restaurant == 3) {
                if (code == 301) return 1100;
                if (code == 302) return 350;
                if (code == 303) return 80;
            }
            return 0;
        }

        //subtotal
        static int calculateSubtotal(int[] codes, int[] qty, int restaurant) {
            int subtotal = 0;
            for (int i = 0; i < codes.length; i++) {
                int price = getPrice(restaurant, codes[i]);
                subtotal += price * qty[i];
            }
            return subtotal;
        }

        //discount
        static int calculateDiscount(int subtotal) {
            if (subtotal > 3000)
                return (int) (subtotal * 0.10);
            return 0;
        }

        //reciept
        static void printReceipt(int restaurant, int[] codes, int[] qty, int subtotal, int discount) {
            String restaurantName = "";

            if (restaurant == 1) restaurantName = "Burger House";
            else if (restaurant == 2) restaurantName = "Pizza Palace";
            else if (restaurant == 3) restaurantName = "Desi Delight";

            System.out.println("\nSelected Restaurant: " + restaurantName);
            System.out.println("----------------------------------");

            for (int i = 0; i < codes.length; i++) {
                int price = getPrice(restaurant, codes[i]);
                int total = price * qty[i];
                System.out.println("Code: " + codes[i] +
                        "  Qty: " + qty[i] +
                        "  Price: " + price +
                        "  Total: " + total);
            }

            System.out.println("----------------------------------");
            System.out.println("Subtotal: " + subtotal);
            System.out.println("Discount: " + discount);
            System.out.println("Final Payable Amount: " + (subtotal - discount));
        }

        //main
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);

            displayRestaurants();
            System.out.print("\nSelect restaurant (1-3): ");
            int restaurantChoice = sc.nextInt();

            if (restaurantChoice < 1 || restaurantChoice > 3) {
                System.out.println("Invalid restaurant selection!");
                return;
            }

            displayMenu(restaurantChoice);

            System.out.print("\nHow many items do you want to order? ");
            int n = sc.nextInt();

            int[] itemCodes = new int[n];
            int[] quantities = new int[n];

            for (int i = 0; i < n; i++) {
                System.out.print("Enter item code: ");
                int code = sc.nextInt();

                while (!isValidItem(restaurantChoice, code)) {
                    System.out.print("Invalid code! Re-enter item code: ");
                    code = sc.nextInt();
                }

                System.out.print("Enter quantity: ");
                int quantity = sc.nextInt();

                while (quantity <= 0) {
                    System.out.print("Quantity must be positive. Re-enter: ");
                    quantity = sc.nextInt();
                }

                itemCodes[i] = code;
                quantities[i] = quantity;
            }

            int subtotal = calculateSubtotal(itemCodes, quantities, restaurantChoice);
            int discount = calculateDiscount(subtotal);

            printReceipt(restaurantChoice, itemCodes, quantities, subtotal, discount);

            sc.close();
        }
}
