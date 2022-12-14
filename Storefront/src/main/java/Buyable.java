
public class Buyable {
    
    // Sellable generic variables
    private double price;
    private String itemName;
    private String itemCategory;

    private BuyableGame game;
    private BuyableClothing clothing;
    private BuyableElectronics electronics;
    private BuyableFood food;
    public Buyable(double price, String name, String category)
    {
        this.price = price;
        itemName = name;
        itemCategory = category;
    }

    public double getPrice() {
        return price;
    }
    public String getGenre(){
        return game.getGenre();
    }

    public String getSize() { return clothing.getSize(); }

    public double getWeight() { return food.getWeight();}

    public int getNumPlayers() { return game.getNumPlayers();}
    public double setPrice(double price) {
        this.price = price;
        return price;
    }

    public String getBrand() { return electronics.getBrand();}
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

}
