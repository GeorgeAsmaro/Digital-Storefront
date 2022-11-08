public class BuyableElectronics extends Buyable {

    private String brand;

    public BuyableElectronics(double price, String name, String brand) {
        super(price, name, "Clothing");
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }
}
