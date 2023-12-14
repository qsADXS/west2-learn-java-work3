import java.sql.*;
import java.util.Scanner;

//没有用联表查询，因为忘记有这玩意了，写完才想起来有这个东西
public class UpdateOrder {
    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;//解决SQL注入问题
        ResultSet rs = null;
        try {
            Scanner in = new Scanner(System.in);
            conn = JDBCDBCPUtils.getConnection();
            String sql = "UPDATE `order_management_system`.`order` SET `commodity_id` = ? , `number` = ? , `price` = ? , `is_finish` = ? WHERE `ID` = ?; " ;

            pst = conn.prepareStatement(sql);
            //读取订单编号
            System.out.println("输入要修改的订单编号：");
            int id1 = in.nextInt();
            order o = new order();
            o = SelectOrder.Select(id1);
            while (o.getID() == -1) {
                System.out.println("未查询到该订单，请重新输入");
                id1 = in.nextInt();
                o = SelectOrder.Select(id1);
            }

            if(o.isFinish()){
                System.out.println("订单已完成，无法修改");
            }else{
                pst.setInt(5,id1);
                System.out.println("输入需要更改的商品编号");
                Commodity c = new Commodity();
                int id2 = in.nextInt();
                c = SelectCommodity.Select(id2);
                while (c.getID() == -1) {
                    System.out.println("未查询到该商品，请重新输入");
                    id2 = in.nextInt();
                    c = SelectCommodity.Select(id2);
                }
                pst.setInt(1,id2);
                System.out.println("输入购买数量");
                int number = in.nextInt();
                while (number <= 0){
                    System.out.println("输入正确的数量");
                    number = in.nextInt();
                }
                pst.setInt(2,number);

                double price = number * c.getPrice();
                if (price >= 1000) {
                    price = 1000 + (price - 1000) * 0.8;
                }//总价超过1000元的部分打八折
                pst.setDouble(3, price);

                //设置是否完成

                pst.setBoolean(4, false);

                if(pst.executeUpdate() > 0){
                    System.out.println("修改成功");
                }

                Transaction.Transaction(id1);

            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCDBCPUtils.release(conn, pst, rs);
        }
    }
}
