public class order {
    protected int ID;
    protected int commodityId;
    protected int number;
    protected double price;
    protected String time;
    protected boolean isFinish;

    public order() {
        this.ID = -1;
        this.commodityId = 0;
        this.number = 0;
        this.price = 0.00;
        this.time = "";
        this.isFinish = false;
    }

    public void set(int ID, int commodityId, int number, double price, String time, boolean isFinish) {
        this.ID = ID;
        this.commodityId = commodityId;
        this.number = number;
        this.price = price;
        this.time = time;
        this.isFinish = isFinish;
    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isFinish() {
        return isFinish;
    }

    public void setFinish(boolean finish) {
        isFinish = finish;
    }

    @Override
    public String toString(){
        return "订单编号: " + getID()+" 购买商品：" + SelectCommodity.Select(getID()).getName()+
                " 购买数量：" + getNumber() + " 订单总价格："+getPrice()+" 下单时间："+getTime()+" 订单状态："+ isFinish();

    }

}
