
import java.sql.SQLOutput;
import java.util.*;
import java.util.Random;

public class Store {
    Scanner input = new Scanner(System.in);

    // User data variables
    private BankAccount myBankAccount;
    private ArrayList<Buyable> myStuff;
    private ArrayList<Buyable> myShoppingCart;

    // Store data variables
    private StoreInventory storeInventory;

    boolean moneyChosen = false;

    boolean recentPurchaseMade = false;

    String mostRecentPurchase = "";
    String secondMostRecentPurchase = "";
    String thirdMostRecentPurchase = "";

    static boolean admin = false;

    public static void setAdmin(boolean isAdmin) {
        admin = isAdmin;
    }

    public Store() {
        System.out.println("Welcome to my storefont!");
        setupAccounts();
        setupStore();
        presentShoppingMenu();
    }

    private void setupAccounts() {
        setupBankAccount();
        myStuff = new ArrayList<Buyable>();
        myShoppingCart = new ArrayList<Buyable>();
    }

    private void setupStore() {
        storeInventory = new StoreInventory();
    }

    private void setupBankAccount() {
        while (!moneyChosen) {
            try {
                System.out.println("To begin, please set up a bank account.");
                System.out.println("How much money should your account contain?");
                int depositAmount = input.nextInt();
                myBankAccount = new BankAccount(depositAmount);
                moneyChosen = true;
            }
            catch (InputMismatchException exception) {
                input.nextLine();
                System.out.println("Invalid Input! Try again.");
            }
        }
    }

    private void presentShoppingMenu() {
        boolean stillShopping = true;
        while (stillShopping) {
            try {
                System.out.println("\n****************************************************** ");
                System.out.println("Please choose from one of the following menu options: ");
                System.out.println("1. View catalog of items to buy");
                System.out.println("2. Buy an item");
                System.out.println("3. View your cart of held items");
                System.out.println("4. Review the items you already own");
                System.out.println("5. View the status of your financials");
                System.out.println("6. Return recently purchased items");
                //If you're an admin, give the option for an admin menu
                if(admin) {
                    System.out.println("7. Admin Menu");
                    System.out.println("8. Exit Program");
                }
                //Otherwise just run a 1-7 menu
                else {
                    System.out.println("7. Exit program");
                }
                int userInput = input.nextInt();
                input.nextLine(); // buffer clear

                switch (userInput) {
                    case 1 -> viewCatalog();

                    case 2 -> buyItem();

                    case 3 -> reviewMyShoppingCart();

                    case 4 -> reviewMyInventory();

                    case 5 -> reviewFinancials();

                    case 6 -> returnItem();

                    case 7 -> {

                        //If you are an admin, give the option to create an item when choosing the admin section
                        if(admin) {
                            System.out.println("1. Create a new item");
                            System.out.println("2. Go back");

                            int num = input.nextInt();

                            if(num == 1) {
                                newItem();
                            }
                        }
                        //Otherwise case 8 is just an exit program
                        if(!admin) {
                            System.out.println("Thanks for shopping! Now exiting program ... ");
                            stillShopping = false;
                            System.exit(0);
                        }
                    }

                    case 8 -> {
                        //If you aren't an admin, the input is useless since it's only 1-7
                        if(!admin) {
                            System.out.println("Incorrect input. Choose again!");
                        }
                        //If you are an admin, it's 1-8 so it's your exit program
                        if(admin) {
                            System.out.println("Thanks for shopping! Now exiting program ... ");
                            stillShopping = false;
                            System.exit(0);
                        }
                    }
                    default -> System.out.println("Incorrect input. Choose again!");
                }
            } catch (InputMismatchException exception) {
                input.nextLine();
                System.out.println("Incorrect input. Choose again!");
            }
        }
    }

    private void newItem() {
        try {
            //Asks the user what type of item they would like to create
            System.out.println("What category is the item?\n");

            System.out.println("1. Clothing");
            System.out.println("2. Food");
            System.out.println("3. Games");
            System.out.println("4. Electronics");

            int type = input.nextInt();

            if(type == 1) {
                //Asks the user for the price
                System.out.println("What is the price?");
                double price = input.nextDouble();

                //Asks the user for the name
                System.out.println("What is the name?");
                input.nextLine();
                String name = input.nextLine();

                //Asks for size
                System.out.println("What is the size?");
                String size = input.nextLine();
                BuyableClothing newClothes = new BuyableClothing(price,name,size);

                //Asks for quantity
                System.out.println("How many would you like to add?");
                int amount = input.nextInt();

                //Adds amount inputted with correct details, creating a new item
                storeInventory.addMultiple(newClothes,amount);
            }
            else if(type == 2) {
                //Asks for price
                System.out.println("What is the price?");
                double price = input.nextDouble();

                //Asks for name
                System.out.println("What is the name?");
                input.nextLine();
                String name = input.nextLine();

                //Asks for weight
                System.out.println("What is the weight?");
                double weight = input.nextDouble();

                BuyableFood newFood = new BuyableFood(price,name,weight);

                System.out.println("How many would you like to add?");
                int amount = input.nextInt();

                //Adds amount inputted with correct details, creating a new item
                storeInventory.addMultiple(newFood,amount);
            }
            else if(type == 3) {
                //Asks for price
                System.out.println("What is the price?");
                double price = input.nextDouble();

                //Asks for name
                System.out.println("What is the name?");
                input.nextLine();
                String name = input.nextLine();

                //Asks for players
                System.out.println("How many players?");
                int players = input.nextInt();
                input.nextLine();

                //Asks for genre
                System.out.println("What's the genre?");
                String genre = input.nextLine();

                //Creates it
                BuyableGame newGame = new BuyableGame(price,name,players,genre);

                //Asks for how many to make
                System.out.println("How many would you like to add?");
                int amount = input.nextInt();

                //Adds amount inputted with correct details, creating a new item
                storeInventory.addMultiple(newGame,amount);
            }
            else if(type == 4) {
                //Asks for price
                System.out.println("What is the price?");
                double price = input.nextDouble();

                //Asks for name
                System.out.println("What is the name?");
                input.nextLine();
                String name = input.nextLine();

                //Asks for brand
                System.out.println("What brand is it?");
                String brand = input.nextLine();

                //Creates it
                BuyableElectronics newElectronics = new BuyableElectronics(price,name,brand);

                //Asks for quantity
                System.out.println("How many would you like to add?");
                int amount = input.nextInt();

                //Adds amount inputted with correct details, creating a new item
                storeInventory.addMultiple(newElectronics,amount);
            }
            else {
                System.out.println("Invalid Input.");
            }
        }
        catch(InputMismatchException exception) {
            System.out.println("Invalid Input.");
        }
    }
    private void viewCatalog() {
        boolean finished = false;
        while (!finished) {
            try {
                System.out.println("What items are you looking for?\n");

                //Prompt on which category of items the user would like to view
                System.out.println("1. Clothing");
                System.out.println("2. Food");
                System.out.println("3. Games");
                System.out.println("4. Electronics");
                System.out.println("5. All available items");
                System.out.println("6. View a specific item");
                System.out.println("7. Back to main menu");

                int category = input.nextInt();

                switch (category) {
                    //If one is chosen, print out all the clothes
                    case 1 -> {
                        for (Buyable item : storeInventory.getClothesList()) {
                            System.out.println("" + item.getItemName());
                        }
                        finished = true;
                    }
                    case 2 -> {
                        //If 2 is chosen, print out all the food
                        for (Buyable item : storeInventory.getFoodList()) {
                            System.out.println("" + item.getItemName());
                        }
                        finished = true;
                    }
                    case 3 -> {
                        //If 3 chosen, print out all games
                        for (Buyable item : storeInventory.getGamesList()) {
                            System.out.println("" + item.getItemName());

                        }
                        finished = true;
                    }
                    case 4 -> {
                        //If 4 chosen, print out all electronics
                        for (Buyable item : storeInventory.getElectronicsList()) {
                            System.out.println("" + item.getItemName());
                        }
                        finished = true;
                    }
                    case 5 -> {
                        //If 5, print out everything
                        for (Buyable item : storeInventory.getFullInventoryList()) {
                            System.out.println("" + item.getItemName());
                        }
                        finished = true;
                    }
                    case 6 -> {
                        //If 6, ask for an item to view the details of
                        boolean found1 = false;
                        System.out.println("List the name of the item you would like to view the details of");
                        input.nextLine();
                        String itemName = input.nextLine();

                        //Checks if the item is in the store, and gives info and breaks if it is
                        for(Buyable item: storeInventory.getFullInventoryList()) {
                            if(item.getItemName().toLowerCase().equals(itemName.toLowerCase())) {
                                found1 = true;
                                System.out.println("We have " + item.getItemName() + " in stock!");
                                getInfo(item);
                                break;
                            }
                        }
                        //Checks if you own it instead, and gives info and breaks if it is
                        if(!found1) {
                            for(Buyable item: myStuff) {
                                if(item.getItemName().toLowerCase().equals(itemName.toLowerCase())) {
                                    found1 = true;
                                    System.out.println("We have " + item.getItemName() + " in stock!");
                                    getInfo(item);
                                    break;
                                }
                            }
                        }
                        //Checks if its in your shopping cart instead and gives info and breaks if it is
                        if(!found1) {
                            for(Buyable item: myShoppingCart) {
                                if(item.getItemName().toLowerCase().equals(itemName.toLowerCase())) {
                                    found1 = true;
                                    System.out.println("We have " + item.getItemName() + " in stock!");
                                    getInfo(item);
                                    break;
                                }
                            }
                        }

                        //Otherwise the item was unable to be located and breaks
                        if(!found1) {
                            System.out.println("Item could not be found");
                            finished = true;
                        }
                        finished = true;
                    }

                    case 7 -> {
                        finished = true;
                    }
                    default -> System.out.println("Invalid Input...");
                }
            } catch (InputMismatchException exception) {
                input.nextLine();
                System.out.println("Invalid Input! Try again");
            }
        }
    }

    private void returnItem() {
        Buyable itemToBuy = null;
        System.out.println("Please type in the name of the item you wish to return");
        String itemName = input.nextLine();
        //If the item name inputted is a real item available, execute the return item from inventory to store method
        try {
            for (Buyable item : myStuff) {
                if (item.getItemName().toLowerCase().equals(itemName.toLowerCase())) {
                    itemToBuy = item;
                    returnItemFromInventoryToStore(itemToBuy);
                    break;
                }
            }
        }
        catch (ConcurrentModificationException ignored) {

        }
    }

    private void buyItem() {
        boolean finished = false;
        while (!finished) {
            try {
                System.out.println("Please type in the name of the item you wish to buy!");

                // Get user input
                String itemName = input.nextLine();

                // Holding variable for the desired item, if found
                Buyable itemToBuy = null;

                // Look through the full inventory to see if the item is present
                // Convert both item name and user input to lower case to prevent case issues!
                for (Buyable item : storeInventory.getFullInventoryList()) {
                    if (item.getItemName().toLowerCase().equals(itemName.toLowerCase())) {
                        itemToBuy = item;
                        break;
                    }
                }
                // If a suitable item was found, give them the option to buy it!
                if (itemToBuy != null) {
                    //Lists the details of the item inputted as long as it's available
                    getInfo(itemToBuy);

                    System.out.println("Type 1 to BUY NOW or 2 to PLACE IN YOUR SHOPPING CART or 3 to attempt to HAGGLE THE ITEM.");

                    int userInput = input.nextInt();
                    input.nextLine(); // buffer clear

                    //If the user presses 1, let them buy the item
                    if (userInput == 1) {
                        makePurchaseFromStore(itemToBuy);
                        finished = true;
                    //If the user presses 2, put it in their shopping cart
                    } else if (userInput == 2) {
                        System.out.println("We'll hold onto this item for you.");
                        moveItemToShoppingCart(itemToBuy);
                        finished = true;
                    //If the user presses 3, let them haggle
                    } else if (userInput == 3) {
                        haggleItem(itemToBuy);
                        finished = true;
                    //Otherwise, cancel the purchase
                    } else {
                        System.out.println("Incorrect input. Purchase cancelled.");
                        finished = true;
                    }
                } else // No suitable item found
                {
                    System.out.println("The item you are looking for is sold out or does not exist, sorry!");
                    finished = true;
                }
            } catch (InputMismatchException exception) {
                input.nextLine();
                System.out.println("Invalid Input! Try again");
            }
        }
    }


    private void haggleItem(Buyable item) {
        try {
            //Preset options for a discount %
            Random rand = new Random();

            //Menu letting the user know the options and chances of each to occur
            System.out.println("The price of the item you are looking to buy is $" + item.getPrice() + ". How much would you like to haggle?");
            System.out.println("1. 5% Discount (50% Chance)");
            System.out.println("2. 15% Discount (25% Chance)");
            System.out.println("3. 30% Discount (10% Chance)\n");
            int y1 = 100;
            int y2 = 100;
            int y3 = 100;
            int discount1Max = 50;

            int numChosen = input.nextInt();

            if (numChosen == 1) {
                int randomNum = rand.nextInt(y1);
                //If you choose 5%, generate a random number to see the success, and then if it is successful, discount it
                if (randomNum >= discount1Max) {
                    double discount = 1 / 1.05;
                    item.setPrice(item.getPrice() * discount);

                    System.out.println("Haggle Attempt Success\n");
                    makePurchaseFromStore(item);
                    item.setPrice(item.getPrice() * 1.05);
                } else {
                    System.out.println("Haggle attempt failed.");
                }
            } else if (numChosen == 2) {

                int randomNum = rand.nextInt(y2);
                //If you choose 15%, generate a random number to see the success, and then if it is successful, discount it
                if (randomNum >= 0 && randomNum <= 25) {
                    double discount = 1 / 1.15;
                    item.setPrice(item.getPrice() * discount);
                    System.out.println("Haggle Attempt Success\n");
                    makePurchaseFromStore(item);
                } else {
                    System.out.println("Haggle attempt failed.");
                }

            } else if (numChosen == 3) {

                int randomNum = rand.nextInt(y3);
                //If you choose 30%, generate a random number to see the success, and then if it is successful, discount it
                if (randomNum >= 0 && randomNum <= 10) {
                    double discount = 1 / 1.3;
                    item.setPrice(item.getPrice() * discount);
                    System.out.println("Haggle Attempt Success\n");
                    makePurchaseFromStore(item);
                } else {
                    System.out.println("Haggle attempt failed.");
                }
            } else {
                System.out.println("Incorrect input");
            }
        } catch (InputMismatchException exception) {
            System.out.println("Invalid Input... returning to main menu");
        }

    }

    private void reviewMyInventory() {
        try {
            System.out.println("What items are you looking for?\n");

            System.out.println("1. Clothing");
            System.out.println("2. Food");
            System.out.println("3. Games");
            System.out.println("4. Electronics");
            System.out.println("5. All available items");

            int numChosen = input.nextInt();

            if (recentPurchaseMade) {
                System.out.println("Here is a list of the items you now own in the category chosen: ");
            }
            for (Buyable buyable : myStuff) {
                //Checks the category to see whether or not to print the item or not
                if (numChosen == 1) {
                    if (buyable.getItemCategory().equals("Clothing")) {
                        System.out.print(buyable.getItemName());
                        System.out.println("");
                    }

                } else if (numChosen == 2) {
                    if (buyable.getItemCategory().equals("Food")) {
                        System.out.print(buyable.getItemName());
                        System.out.println("");
                    }

                } else if (numChosen == 3) {
                    if (buyable.getItemCategory().equals("Game")) {
                        System.out.print(buyable.getItemName());
                        System.out.println("");
                    }

                } else if (numChosen == 4) {
                    if (buyable.getItemCategory().equals("Electronics")) {
                        System.out.print(buyable.getItemName());
                        System.out.println("");
                    }
                } else if (numChosen == 5) {
                    System.out.print(buyable.getItemName());
                    System.out.println("");
                }
            }

            if(recentPurchaseMade) {
                System.out.println("Print out the name of an item in your inventory if you would like to view the details about it, or press anything else to go back to the menu");
                input.nextLine();
                String next = input.nextLine();


                for(Buyable item: myStuff) {
                    if (item.getItemName().toLowerCase().equals(next.toLowerCase())) {
                        getInfo(item);
                        break;
                    }
                }
            }

            viewRecentPurchases();
        }
        catch(InputMismatchException exception) {

        }
    }




    private void viewRecentPurchases() {
        //If a recent purchase is made, then print out the most recent purchase
        if(recentPurchaseMade) {
            System.out.println();
            System.out.println("Most recent purchase: " + mostRecentPurchase);
            //If the second most recent purchase has a value, then print it out too
            if(!secondMostRecentPurchase.equals("")) {
                System.out.println("Second most recent purchase: " + secondMostRecentPurchase);
            }
            //If the third most recent purchase has a value, then print it out too
            if(!thirdMostRecentPurchase.equals("")) {
                System.out.println("Third most recent purchase: " + thirdMostRecentPurchase);
            }
        }
        //Otherwise, no purchases have been made
        else {
            System.out.println("No purchases made.");
        }
    }
    
    private void reviewFinancials() {
        boolean finished = false;
        while(!finished) {
            try {
                myBankAccount.balanceReport();
                //If the user presses 1, they can deposit a positive integer of the number listed
                System.out.println("\n1. Deposit          2. Withdraw");
                System.out.println("\n-Press any other number to go back-");
                int depOrWith = input.nextInt();

                if(depOrWith == 1) {
                    System.out.println("How much would you like to deposit?");
                    int numDeposit = input.nextInt();

                    myBankAccount.depositMoney(Math.abs(numDeposit));
                    finished = true;
                }
                //If the user presses 2, they can withdraw a positive integer by the number listed as long as you can afford it
                else if (depOrWith == 2) {
                    System.out.println("How much would you like to withdraw?");
                    int withdrawNum = input.nextInt();

                    if(myBankAccount.canAfford(Math.abs(withdrawNum))) {

                        if(myBankAccount.checkPassword()) {
                            myBankAccount.makePurchase(Math.abs(withdrawNum));
                            finished = true;
                        }
                    }
                }
                else {
                    finished = true;
                }
            }
            catch(InputMismatchException exception) {
                input.nextLine();
                System.out.println("Invalid Input! Make sure you type a number and try again.");
            }
        }
    }
    
    
    // SHOPPING CART METHODS
    private void reviewMyShoppingCart() {
        boolean finished = false;
        while(!finished) {
            try {
                //Buyable item = null;
                if(!myShoppingCart.isEmpty())
                {
                    //Prints every item in the shopping cart
                    System.out.println("Here are all of the items being held in your shopping cart: ");
                    for(Buyable item: myShoppingCart)
                    {
                        System.out.println("" + item.getItemName());
                    }
                    //If 1 is pressed, you can purchase any items in the shopping cart, if 2, you can remove any in shopping cart, and press anything else to go back
                    System.out.println("Press 1 if you would like to purchase any held items, press 2 if you would like to remove any items from your shopping cart, and press any other key for neither");

                    //Get user input
                    String userInput = input.nextLine();

                    //If user chooses 1, buy the item
                    if(userInput.equals("1"))
                    {
                        buyItemInShoppingCart();
                        finished = true;
                    }

                    //If the user chooses 2, remove the item from the shopping cart
                    else if(userInput.equals("2")) {

                        for(Buyable item: myShoppingCart)
                        {
                            //this aspect is still causing an exception error, still needs to be fixed
                            removeItemFromShoppingCart(item);
                            break;

                        }
                        finished = true;
                    }
                    else
                    {
                        System.out.println("Leaving shopping cart as is and returning to the storefront ... ");
                        finished = true;
                    }
                }
                else
                {
                    System.out.println("Your shopping cart is empty! Nothing to see here ... ");
                    finished = true;
                }
            }
            catch(InputMismatchException exception) {
                input.nextLine();
                System.out.println("Invalid Input! Try again.");
            }
        }
    }
    
    private void buyItemInShoppingCart() {
        try {
            System.out.println("Type in the name of the item you want to buy from the shopping cart: ");
            String userChoice = input.nextLine();

            Buyable item = null;
            for(Buyable itemInCart: myShoppingCart)
            {
                //If the item named is a real item available, then list the item and it's details
                if(itemInCart.getItemName().toLowerCase().equals(userChoice.toLowerCase()))
                {
                    getInfo(itemInCart);
                    item = itemInCart;
                    break;
                }
            }
            if(item != null) {
                makePurchaseFromShoppingCart(item);
            }
            //Otherwise the item couldn't be found
            else {
                System.out.println("Item could not be found in shopping cart.");
            }
        }
        catch(InputMismatchException exception) {

        }
    }

    private void getInfo(Buyable item) {
        //Print out the items name, category and price
        System.out.println();
        System.out.println("Item: " + item.getItemName());
        System.out.println("Category: " + item.getItemCategory());
        System.out.println("Price: $" + item.getPrice());

        //If the item's a game, print out the genre and number of players
        if(item.getItemCategory().equals("Game")) {
            System.out.println("Genre: " + item.getGenre());
            System.out.println("Number of Players: " + item.getNumPlayers());
        }
        //If the items clothing, print out the size
        if(item.getItemCategory().equals("Clothing")) {
            System.out.println("Size: " + item.getSize());
        }

        //If the items food, print out the weight
        if(item.getItemCategory().equals("Food")) {
            System.out.println("Weight: " + item.getWeight());
        }

        //If the item is an electronic, print out the brand
        if(item.getItemCategory().equals("Electronics")) {
            System.out.println("Brand: " + item.getBrand());
        }
        System.out.println();
    }


        

    
    private void removeItemFromShoppingCart(Buyable item) {
        boolean finished = false;
        //While finished is false
        while(!finished) {
            //Ask for the item that needs to be removed
            try {
                System.out.println("Which item would you like to remove from your shopping cart?");

                String userChoice = input.nextLine();
                try {
                    Buyable items = null;
                    for(Buyable cartItem: myShoppingCart)
                    {
                        //If the item named is actually in the shopping cart, remove it from the shopping cart and restock it to the store's inventory
                        if(cartItem.getItemName().toLowerCase().equals(userChoice.toLowerCase()))
                        {
                            items = cartItem;
                            System.out.println("You have removed " + cartItem.getItemName() + " from your shopping cart.");
                        }

                    }
                    if(items != null) {
                        myShoppingCart.remove(items);
                        storeInventory.addMultiple(items,1);
                        finished = true;
                    }
                    //Otherwise the item named couldn't be found in the shopping cart
                    else {
                        System.out.println("Item could not be found in your shopping cart.");
                        finished = true;
                    }

                }
                catch(ConcurrentModificationException exception) {}
            }
            catch(InputMismatchException exception) {
                input.nextLine();
                System.out.println("Invalid Input! Try again");
            }
        }
    }
    
    // Move item from inventory to shopping cart
    private void moveItemToShoppingCart(Buyable item) {
        myShoppingCart.add(item);
        storeInventory.removeItemFromInventory(item);
    }
    
    private void moveItemFromShoppingCartToInventory(Buyable item) {
        storeInventory.restockItemToInventory(item);
        myShoppingCart.remove(item);
    }

    private void returnItemFromInventoryToStore(Buyable item) {
        //If the item's name is equal to the most recent purchase, then return and restock the item and change the order of the most recent purchases
        if(item.getItemName().toLowerCase().equals(mostRecentPurchase.toLowerCase())) {
            double itemPrice = item.getPrice();
            myBankAccount.depositMoney(itemPrice);
            storeInventory.restockItemToInventory(item);
            myStuff.remove(item);

            String temp1 = secondMostRecentPurchase;
            mostRecentPurchase = temp1;
            secondMostRecentPurchase = thirdMostRecentPurchase;
            thirdMostRecentPurchase = "";

            if(mostRecentPurchase.equals("") && secondMostRecentPurchase.equals("") && thirdMostRecentPurchase.equals("")) {
                recentPurchaseMade = false;
            }
        }
        //If the item's name is equal to the second most recent purchase, then return and restock the item and change the order of the most recent purchases
        else if(item.getItemName().toLowerCase().equals(secondMostRecentPurchase.toLowerCase())) {
            double itemPrice = item.getPrice();
            myBankAccount.depositMoney(itemPrice);
            storeInventory.restockItemToInventory(item);
            myStuff.remove(item);

            //String temp1 = secondMostRecentPurchase;
            //mostRecentPurchase = temp1;
            secondMostRecentPurchase = thirdMostRecentPurchase;
            thirdMostRecentPurchase = "";

            if(mostRecentPurchase.equals("") && secondMostRecentPurchase.equals("") && thirdMostRecentPurchase.equals("")) {
                recentPurchaseMade = false;
            }
        }
        //If the item's name is equal to the third most recent purchase, then return and restock the item and change the order of the most recent purchases
        else if(item.getItemName().toLowerCase().equals(thirdMostRecentPurchase.toLowerCase())) {
            double itemPrice = item.getPrice();
            myBankAccount.depositMoney(itemPrice);
            storeInventory.restockItemToInventory(item);
            myStuff.remove(item);

            String temp1 = secondMostRecentPurchase;
            mostRecentPurchase = temp1;
            secondMostRecentPurchase = thirdMostRecentPurchase;
            thirdMostRecentPurchase = "";

            if(mostRecentPurchase.equals("") && secondMostRecentPurchase.equals("") && thirdMostRecentPurchase.equals("")) {
                recentPurchaseMade = false;
            }
        }
        //Otherwise the item listed isn't one of your 3 most recent purchases
        else {
            System.out.println("This item isn't one of your 3 most recent purchases...");
        }
    }
    

    private void makePurchaseFromStore(Buyable item) {
        // If you can afford the item, buy it and remove it from the store
        if(myBankAccount.canAfford(item.getPrice()))
        {
            //Check the password first to make sure the user inputs the correct password
            if(myBankAccount.checkPassword()) {
                myBankAccount.makePurchase(item.getPrice());
                System.out.println("Purchase complete! You now own " + item.getItemName());
                myStuff.add(item);
                //If there is no most recent purchase, then the purchase just made is the most recent purchase
                if(mostRecentPurchase.equals("")) {
                    mostRecentPurchase = item.getItemName();
                    recentPurchaseMade = true;
                }
                //If there is a most recent purchase but not a second, then the purchase made is the second most recent
                else if(!mostRecentPurchase.equals("") && secondMostRecentPurchase.equals("")) {
                    secondMostRecentPurchase = mostRecentPurchase;
                    mostRecentPurchase = item.getItemName();
                }
                //If there is a most recent and second most recent, then the purchase is the 3rd most recent
                else if(!mostRecentPurchase.equals("") && !secondMostRecentPurchase.equals("") && thirdMostRecentPurchase.equals("")) {
                    String temp1;
                    String temp2;
                    temp1 = mostRecentPurchase;
                    temp2 = secondMostRecentPurchase;

                    secondMostRecentPurchase = temp1;
                    thirdMostRecentPurchase = temp2;
                    mostRecentPurchase = item.getItemName();
                }
                //If all are there, then shift the most recent to 2nd, 2nd to third, third gone and the newest purchase as the most
                else if(!mostRecentPurchase.equals("") && !secondMostRecentPurchase.equals("") && !thirdMostRecentPurchase.equals("")) {
                    String tempOne;
                    String tempTwo;

                    tempOne = mostRecentPurchase;
                    tempTwo = secondMostRecentPurchase;

                    secondMostRecentPurchase = tempOne;
                    thirdMostRecentPurchase = tempTwo;

                    mostRecentPurchase = item.getItemName();
                }
                //Remove the item from the inventory
                storeInventory.removeItemFromInventory(item);
            }
        }
        //Otherwise the user can't afford the item
        else
        {
            System.out.println("You can't afford that item ... ");
        }
    }
    
    private void makePurchaseFromShoppingCart(Buyable item) {
        // If you can afford the item, buy it and remove it from the store
        if(myBankAccount.canAfford(item.getPrice()))
        {
            //Check the password first
            if(myBankAccount.checkPassword()) {
                try {
                    myBankAccount.makePurchase(item.getPrice());
                    System.out.println("Purchase complete! You now own " + item.getItemName());
                    myStuff.add(item);
                    myShoppingCart.remove(item);
                    //If there is no most recent purchase, then the purchase just made is the most recent purchase
                    if(mostRecentPurchase.equals("")) {
                        mostRecentPurchase = item.getItemName();
                        recentPurchaseMade = true;
                    }
                    //If there is a most recent purchase but not a second, then the purchase made is the second most recent
                    else if(!mostRecentPurchase.equals("") && secondMostRecentPurchase.equals("")) {
                        secondMostRecentPurchase = mostRecentPurchase;
                        mostRecentPurchase = item.getItemName();
                    }
                    //If there is a most recent and second most recent, then the purchase is the 3rd most recent
                    else if(!mostRecentPurchase.equals("") && !secondMostRecentPurchase.equals("") && thirdMostRecentPurchase.equals("")) {
                        String temp1;
                        String temp2;
                        temp1 = mostRecentPurchase;
                        temp2 = secondMostRecentPurchase;

                        secondMostRecentPurchase = temp1;
                        thirdMostRecentPurchase = temp2;
                        mostRecentPurchase = item.getItemName();
                    }
                    //If all are there, then shift the most recent to 2nd, 2nd to third, third gone and the newest purchase as the most
                    else if(!mostRecentPurchase.equals("") && !secondMostRecentPurchase.equals("") && !thirdMostRecentPurchase.equals("")) {
                        String tempOne;
                        String tempTwo;

                        tempOne = mostRecentPurchase;
                        tempTwo = secondMostRecentPurchase;

                        secondMostRecentPurchase = tempOne;
                        thirdMostRecentPurchase = tempTwo;

                        mostRecentPurchase = item.getItemName();
                    }
                }
                catch(ConcurrentModificationException exception) {
                    System.out.println("Error! Try again.");
                }
            }
        }
        //Otherwise, the user can't afford the item
        else {
            System.out.println("You can't afford that item ... ");
        }        
    }
}