public class Commodity {

    protected int ID;
    protected String name;
    protected int inventory;
    protected double price;

    public Commodity() {
        name = "";
        ID = -1;
        inventory = 0;
        price = 0.00;
    }


    public void set(int ID, String name,double price, int inventory) {
        this.ID = ID;
        this.name = name;
        this.price=price;
        this.inventory = inventory;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setInventory(int inventory) {
        this.inventory = inventory;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public double getPrice() {
        return price;
    }


    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }


    public int getInventory() {
        return inventory;
    }

    @Override
    public String toString() {
        return "ID:"+getID()+" 商品名"+getName()+" price:"+getPrice()+" 库存："+getInventory();
    }
}
