
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Store {
    Scanner input = new Scanner(System.in);
    
    // User data variables
    private BankAccount myBankAccount;
    private ArrayList<Buyable> myStuff;
    private ArrayList<Buyable> myShoppingCart;
    
    // Store data variables
    private StoreInventory storeInventory;

    boolean moneyChosen = false;
    
    public Store()
    {
        System.out.println("Welcome to my storefont!");
        setupAccounts();
        setupStore();
        presentShoppingMenu();
    }
    
    private void setupAccounts()
    {
        setupBankAccount();
        myStuff = new ArrayList<Buyable>();
        myShoppingCart = new ArrayList<Buyable>();
    }
    
    private void setupStore()
    {
        storeInventory = new StoreInventory();
    }

    private void setupBankAccount()
    {
        while(!moneyChosen) {
            try {
                System.out.println("To begin, please set up a bank account.");
                System.out.println("How much money should your account contain?");
                int depositAmount = input.nextInt();
                myBankAccount = new BankAccount(depositAmount);
                moneyChosen = true;


            }
            catch(InputMismatchException exception) {
                input.nextLine();
                System.out.println("Invalid Input! Try again.");
            }
        }

    }
    
    private void presentShoppingMenu()
    {
        boolean stillShopping = true;
        while(stillShopping)
        {
            try {
                System.out.println("\n****************************************************** ");
                System.out.println("Please choose from one of the following menu options: ");
                System.out.println("1. View catalog of items to buy");
                System.out.println("2. Buy an item");
                System.out.println("3. View your cart of held items");
                System.out.println("4. Review the items you already own");
                System.out.println("5. View the status of your financials");
                System.out.println("6. YOUR CUSTOM IDEA HERE??");
                System.out.println("7. Exit program");

                int userInput = input.nextInt();
                input.nextLine(); // buffer clear

                switch(userInput){
                    case 1 -> viewCatalog();

                    case 2 -> buyItem();

                    case 3 -> reviewMyShoppingCart();

                    case 4 -> reviewMyInventory();

                    case 5 -> reviewFinancials();

                    case 6 -> System.out.println("YOUR CONTENT HERE! :) :)");

                    case 7 -> {
                        System.out.println("Thanks for shopping! Now exiting program ... ");
                        stillShopping = false;
                        System.exit(0);
                    }

                    default -> System.out.println("Incorrect input. Choose again!");
                }
            }
            catch(InputMismatchException exception) {
                input.nextLine();
                System.out.println("Incorrect input. Choose again!");
            }

            
        }
    }
    
    private void viewCatalog()
    {
        boolean finished = false;
        while(!finished) {
            try {
                System.out.println("What items are you looking for?\n");

                System.out.println("1. Clothing");
                System.out.println("2. Food");
                System.out.println("3. Games");
                System.out.println("4. Electronics");
                System.out.println("5. All available items");

                int category = input.nextInt();

                switch(category) {
                    case 1 -> {
                        for(Buyable item: storeInventory.getClothesList())
                        {
                            System.out.println("" + item.getItemName());
                        }
                        finished = true;
                    }

                    case 2 -> {
                        for(Buyable item: storeInventory.getFoodList())
                        {
                            System.out.println("" + item.getItemName());
                        }
                        finished = true;
                    }

                    case 3 -> {
                        for(Buyable item: storeInventory.getGamesList())
                        {
                            System.out.println("" + item.getItemName());
                        }
                        finished = true;
                    }

                    case 4 -> {
                        for(Buyable item: storeInventory.getElectronicsList())
                        {
                            System.out.println("" + item.getItemName());
                        }
                        finished = true;
                    }

                    case 5 -> {
                        for(Buyable item: storeInventory.getFullInventoryList())
                        {
                            System.out.println("" + item.getItemName());
                        }
                        finished = true;
                    }

                    default -> {
                        System.out.println("Invalid Input...");
                    }
                }
            }
            catch(InputMismatchException exception) {
                input.nextLine();
                System.out.println("Invalid Input! Try again");
            }
        }
    }
    
    private void buyItem()
    {
        boolean finished = false;
        while(!finished) {
            try {
                System.out.println("Please type in the name of the item you wish to buy!");

                // Get user input
                String itemName = input.nextLine();

                // Holding variable for the desired item, if found
                Buyable itemToBuy = null;

                // Look through the full inventory to see if the item is present
                // Convert both item name and user input to lower case to prevent case issues!
                for(Buyable item: storeInventory.getFullInventoryList())
                {
                    if(item.getItemName().toLowerCase().equals(itemName.toLowerCase()))
                    {
                        itemToBuy = item;
                        break;
                    }
                }

                // If a suitable item was found, give them the option to buy it!
                if(itemToBuy != null)
                {
                    System.out.println("We have " + itemToBuy.getItemName() + " in stock!");
                    System.out.println("Type 1 to BUY NOW or 2 to PLACE IN YOUR SHOPPING CART.");

                    int userInput = input.nextInt();
                    input.nextLine(); // buffer clear

                    if(userInput == 1)
                    {
                        makePurchaseFromStore(itemToBuy);
                        finished = true;
                    }
                    else if(userInput == 2)
                    {
                        System.out.println("We'll hold onto this item for you.");
                        moveItemToShoppingCart(itemToBuy);
                        finished = true;
                    }
                    else
                    {
                        System.out.println("Incorrect input. Purchase cancelled.");
                        finished = true;
                    }

                }
                else // No suitable item found
                {
                    System.out.println("The item you are looking for is sold out or does not exist, sorry!");
                    finished = true;
                }
            }
            catch(InputMismatchException exception) {
                input.nextLine();
                System.out.println("Invalid Input! Try again");
            }
        }

        
    }
    

    private void reviewMyInventory()
    {
        System.out.println("Here is a list of the items you now own: ");
        for(int i = 0; i < myStuff.size(); i++)
        {
            System.out.println("" + myStuff.get(i).getItemName());
        }
    }
    
    private void reviewFinancials()
    {
        boolean finished = false;
        while(!finished) {
            try {
                myBankAccount.balanceReport();

                System.out.println("\n1. Deposit          2. Withdraw");
                System.out.println("\n*Press any other number to go back*");
                int depOrWith = input.nextInt();

                if(depOrWith == 1) {
                    System.out.println("How much would you like to deposit?");
                    int numDesposit = input.nextInt();

                    myBankAccount.depositMoney(Math.abs(numDesposit));
                    finished = true;
                }
                else if (depOrWith == 2) {
                    System.out.println("How much would you like to withdraw?");
                    int withdrawNum = input.nextInt();

                    if(myBankAccount.canAfford(Math.abs(withdrawNum))) {

                        if(myBankAccount.checkPassword()) {
                            myBankAccount.makePurchase(Math.abs(withdrawNum));
                            finished = true;
                        }
                    }
                    else {
                        System.out.println("You can't afford that!");
                    }

                }
                else {
                    finished = true;
                }
            }
            catch(InputMismatchException exception) {
                input.nextLine();
                System.out.println("Invalid Input! Try again.");
            }
        }


    }
    
    
    // SHOPPING CART METHODS
    private void reviewMyShoppingCart()
    {
        boolean finished = false;
        while(!finished) {
            try {
                //Buyable item = null;
                if(!myShoppingCart.isEmpty())
                {
                    System.out.println("Here are all of the items being held in your shopping cart: ");
                    for(Buyable item: myShoppingCart)
                    {
                        System.out.println("" + item.getItemName());


                    }
                    System.out.println("Press 1 if you would like to purchase any held items, press 2 if you would like to remove any items from your shopping cart, and press any other key for neither");

                    String userInput = input.nextLine();

                    if(userInput.equals("1"))
                    {
                        buyItemInShoppingCart();
                        finished = true;
                    }
                    else if(userInput.equals("2")) {

                        for(Buyable item: myShoppingCart)
                        {
                            removeItemFromShoppingCart(item);


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
    
    private void buyItemInShoppingCart()
    {
        System.out.println("Type in the name of the item you want to buy from the shopping cart: ");
        String userChoice = input.nextLine();

            for(Buyable itemInCart: myShoppingCart)
            {
                if(itemInCart.getItemName().toLowerCase().equals(userChoice.toLowerCase()))
                {

                    makePurchaseFromShoppingCart(itemInCart);
                    break;

                }
                else
                {
                    System.out.println("Item could not be found in shopping cart.");
                }
            }
    }


        

    
    private void removeItemFromShoppingCart(Buyable item)
    {
        boolean finished = false;
        while(!finished) {
            try {
                System.out.println("Which item would you like to remove from your shopping cart?");

                String userChoice = input.nextLine();

                for(Buyable cartItem: myShoppingCart)
                {
                    if(cartItem.getItemName().toLowerCase().equals(userChoice.toLowerCase()))
                    {

                        myShoppingCart.remove(item);
                        System.out.println("You have removed " + cartItem.getItemName() + " from your shopping cart.");
                        finished = true;

                    }
                    else
                    {
                        System.out.println("Item could not be found in your shopping cart.");
                        finished = true;
                    }
                }
            }
            catch(InputMismatchException exception) {
                input.nextLine();
                System.out.println("Invalid Input! Try again");
            }
        }

    }
    
    // Move item from inventory to shopping cart
    private void moveItemToShoppingCart(Buyable item)
    {
        myShoppingCart.add(item);
        storeInventory.removeItemFromInventory(item);
    }
    
    private void moveItemFromShoppingCartToInventory(Buyable item)
    {
        storeInventory.restockItemToInventory(item);
        myShoppingCart.remove(item);
    }
    

    private void makePurchaseFromStore(Buyable item)
    {
        // If you can afford the item, buy it and remove it from the store
        if(myBankAccount.canAfford(item.getPrice()))
        {
            if(myBankAccount.checkPassword()) {
                myBankAccount.makePurchase(item.getPrice());
                System.out.println("Purchase complete! You now own " + item.getItemName());
                myStuff.add(item);
                storeInventory.removeItemFromInventory(item);
            }
        }
        else
        {
            System.out.println("You can't afford that item ... ");
        }
    }
    
    private void makePurchaseFromShoppingCart(Buyable item)
    {
        // If you can afford the item, buy it and remove it from the store
        if(myBankAccount.canAfford(item.getPrice()))
        {
            if(myBankAccount.checkPassword()) {
                try {
                    myBankAccount.makePurchase(item.getPrice());
                    System.out.println("Purchase complete! You now own " + item.getItemName());
                    myStuff.add(item);
                    myShoppingCart.remove(item);
                }
                catch(ConcurrentModificationException exception) {
                    System.out.println("Error! Try again.");
                }

            }

        }
        else
        {
            System.out.println("You can't afford that item ... ");
        }        
    }
}
