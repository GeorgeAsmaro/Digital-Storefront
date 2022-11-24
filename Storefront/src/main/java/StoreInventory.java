
import java.util.ArrayList;

public class StoreInventory {
    
    private ArrayList<BuyableClothing> clothesForSale = new ArrayList<BuyableClothing>();
    private ArrayList<BuyableFood> foodForSale = new ArrayList<BuyableFood>();
    private ArrayList<BuyableGame> gamesForSale = new ArrayList<BuyableGame>();

    private ArrayList<BuyableElectronics> electronicsForSale = new ArrayList<BuyableElectronics>();
    
    public StoreInventory() {
        populateClothesInventory();
        populateFoodInventory();
        populateGamesInventory();
        populateElectronicsInventory();
    }
    
    // Getters and setters for inventory lists
    
    public ArrayList<BuyableClothing> getClothesInventory()
    {
        return clothesForSale;
    }
    
    public ArrayList<BuyableFood> getFoodInventory()
    {
        return foodForSale;
    }
    
    public ArrayList<BuyableGame> getGamesInventory()
    {
        return gamesForSale;
    }

    public ArrayList<BuyableElectronics> getElectronicsInventory() {
        return electronicsForSale;
    }
    
    // Returns a master list of all inventory items at once
    public ArrayList<Buyable> getFullInventoryList() {
        ArrayList<Buyable> fullInventory = new ArrayList<Buyable>();
        fullInventory.addAll(clothesForSale);
        fullInventory.addAll(foodForSale);
        fullInventory.addAll(gamesForSale);
        fullInventory.addAll(electronicsForSale);
        
        return fullInventory;
    }

    public ArrayList<Buyable> getClothesList() {
        ArrayList<Buyable> clothesInventory = new ArrayList<Buyable>();
        clothesInventory.addAll(clothesForSale);
        return clothesInventory;
    }

    public ArrayList<Buyable> getGamesList() {
        ArrayList<Buyable> gamesInventory = new ArrayList<Buyable>();
        gamesInventory.addAll(gamesForSale);
        return gamesInventory;
    }

    public ArrayList<Buyable> getFoodList() {
        ArrayList<Buyable> foodInventory = new ArrayList<Buyable>();
        foodInventory.addAll(foodForSale);
        return foodInventory;
    }

    public ArrayList<Buyable> getElectronicsList() {
        ArrayList<Buyable> electronicsInventory = new ArrayList<Buyable>();
        electronicsInventory.addAll(electronicsForSale);
        return electronicsInventory;
    }
    
    
    public void removeItemFromInventory(Buyable item) {
        if(item instanceof BuyableClothing)
        {
            clothesForSale.remove((BuyableClothing)item);
        }
        else if(item instanceof BuyableFood)
        {
            foodForSale.remove((BuyableFood)item);
        }
        else if(item instanceof BuyableGame)
        {
            foodForSale.remove((BuyableGame)item);
        }
    }
    
    public void restockItemToInventory(Buyable item) {
        if(item instanceof BuyableClothing)
        {
            clothesForSale.add((BuyableClothing)item);
        }
        else if(item instanceof BuyableFood)
        {
            foodForSale.add((BuyableFood)item);
        }
        else if(item instanceof BuyableGame)
        {
            foodForSale.remove((BuyableGame)item);
        }       
    }
    
    // Methods to populate the inventory
    private void populateClothesInventory() {
        // Master list of all clothes held in the store on opening
        
        // Hoodies
        BuyableClothing smallHoodie = new BuyableClothing(59.99, "Hoodie", "small");
        clothesForSale.add(smallHoodie);
        BuyableClothing mediumHoodie = new BuyableClothing(59.99, "Hoodie", "medium");
        clothesForSale.add(mediumHoodie);
        BuyableClothing largeHoodie = new BuyableClothing(59.99, "Hoodie", "lage");
        clothesForSale.add(largeHoodie);
        
        // Shoes
        BuyableClothing dressShoes = new BuyableClothing(99.99, "Dress Shoes", "8");
        clothesForSale.add(dressShoes);
        BuyableClothing sandals = new BuyableClothing(9.99, "Sandals", "5");
        clothesForSale.add(sandals);
        
        // Gloves
        BuyableClothing gloves = new BuyableClothing(13.49, "Gloves", "Medium");
        addMultiple(gloves, 3);
    }
    
    private void populateFoodInventory() {
        // Master list of all food held in the store on opening
        
        // Perishables
        BuyableFood pizza = new BuyableFood(12.99, "Pizza", 400);
        foodForSale.add(pizza);
        BuyableFood lasagna = new BuyableFood(24.00, "Lasagna", 1000);
        foodForSale.add(lasagna);
        BuyableFood spinach = new BuyableFood(3.99, "Spinach", 250);
        foodForSale.add(spinach);
        
        // Non-perishables
        BuyableFood beans = new BuyableFood(1.49, "Beans", 300);
        foodForSale.add(beans);
        BuyableFood noodles = new BuyableFood(0.99, "Noodles", 125);
        foodForSale.add(noodles);
        BuyableFood rice = new BuyableFood(7.99, "Rice", 2000);
        addMultiple(rice, 5);
    }
    
    private void populateGamesInventory() {
        // Master list of all games held in the store on opening
        // Board games
        BuyableGame monopoly = new BuyableGame(19.99, "Monopoly", 4, "Board Game");
        gamesForSale.add(monopoly);
        BuyableGame scrabble = new BuyableGame(24.99, "Scrabble", 2, "Board Game");
        gamesForSale.add(scrabble);
              
        // Computer games
        BuyableGame breathOfTheWild = new BuyableGame(79.99, "Breath of the Wild", 1, "Video Game");
        gamesForSale.add(breathOfTheWild);
        BuyableGame forza = new BuyableGame(59.99, "Forza", 2, "Video Game");
        gamesForSale.add(forza);
        BuyableGame eldenRing = new BuyableGame(79.99,"Elden Ring",1,"Video Game");
        gamesForSale.add(eldenRing);
    }

    private void populateElectronicsInventory() {
        BuyableElectronics iPhone = new BuyableElectronics(999, "iPhone 14", "Apple");
        electronicsForSale.add(iPhone);

        BuyableElectronics S20 = new BuyableElectronics(799, "Galaxy S20", "Samsung");
        electronicsForSale.add(S20);

        BuyableElectronics Pixel4 = new BuyableElectronics(699, "Pixel 4", "Google");
        electronicsForSale.add(Pixel4);

        BuyableElectronics ZFold = new BuyableElectronics(1299, "Galaxy Z Fold", "Samsung");
        electronicsForSale.add(ZFold);

        BuyableElectronics Huawei = new BuyableElectronics(599, "Huawei", "Huawei");
        electronicsForSale.add(Huawei);
    }
    
    // Helper method to add multiple copies of the same item to the inventory at once
    private void addMultiple(Buyable item, int numberToAdd) {
        if(item instanceof BuyableClothing)
        {
            for(int i = 0; i < numberToAdd; i++)
            {
                
                clothesForSale.add((BuyableClothing)item);
            }
        }
        else if(item instanceof BuyableFood)
        {
            for(int i = 0; i < numberToAdd; i++)
            {
                foodForSale.add((BuyableFood)item);
            }            
        }
        else if(item instanceof BuyableGame)
        {
             for(int i = 0; i < numberToAdd; i++)
            {
                gamesForSale.add((BuyableGame)item);
            }           
        }
    }
}
