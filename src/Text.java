import java.sql.SQLException;

public class Text {
    public static void main(String[] args) throws SQLException {
        /*开始时两个表为空，
        字段
            商品表：
                商品ID(ID),商品名（commodity_name),价格(price),存货(inventory)
            订单表：
                订单ID(ID),购买的商品ID(commodity_id),购买的数量(number),总价格(price),下订单的时间(date),订单是否完成(if_finish)
        商品ID和订单ID均以10001开始，设置为主键、自增
        */

        /*
        测试插入
        */
        //InsertCommodity.main(null);
        //InsertOrder.main(null);
        /*
        插入的商品三个商品，存货分别为20,0,56
        购买了第一个商品,此时完成购买商品一的事务
        */

        /*
        删除测试
        */

        /*
        InsertOrder.main(null);//购买商品1 20个,此时购买失败，但是插入成功，进行一次事务，回滚
        DeleteOrder.Delete(10002);//删除失败
        UpdateOrder.main(null);//更新购买数量为 15个
        UpdateCommodity.main(null);//更新商品1数量为15个
        DeleteOrder.Delete(10002);//删除成功。进行一次事务
        */

        /*
        测试修改
         */
        /*
        InsertOrder.main(null);//增加订单，购买商品2 10个
        UpdateOrder.main(null);//修改为购买商品3 15个
        UpdateCommodity.main(null);//将商品1名字改为不想取名字
        */
        /*
        查询测试
         */
        /*
        SelectOrder.Select(10001);
        SelectOrder.SelectASC("price");//可以自行设置表中有的字段

        SelectCommodity.Select(10001);
        SelectCommodity.SelectASC("inventory");//可以自行设置表中有的字段
        */

    }


}
