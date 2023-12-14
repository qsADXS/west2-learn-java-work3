import java.sql.*;
import java.util.Date;
import java.util.Scanner;

public class InsertOrder {
    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;//解决SQL注入问题
        ResultSet rs = null;
        try {
            Scanner in = new Scanner(System.in);
            conn = JDBCDBCPUtils.getConnection();
            String sql = "INSERT INTO `order_management_system`.`order` (`commodity_id`,`number`,`price`,`date`,`is_finish`) VALUES (?,?,?,?,?);";

            pst = conn.prepareStatement(sql);
            //设置id
            System.out.println("输入要购买的商品id：");
            int id = in.nextInt();
            Commodity c = new Commodity();
            c = SelectCommodity.Select(id);
            while (c.getID() == -1) {
                System.out.println("未查询到该商品，请重新输入");
                id = in.nextInt();
                c = SelectCommodity.Select(id);
            }

            pst.setInt(1, id);
            //设置数量
            System.out.println("输入要购买的商品数量：");
            int number = in.nextInt();
            pst.setInt(2, number);

            //设置价格
            double price = number * c.getPrice();
            if (price >= 1000) {
                price = 1000 + (price - 1000) * 0.8;
            }//总价超过1000元的部分打八折
            pst.setDouble(3, price);

            //设置时间
            pst.setTimestamp(4, new Timestamp(new Date().getTime()));
            //设置是否完成

            pst.setBoolean(5, false);

            if(pst.executeUpdate() > 0){
                System.out.println("添加成功");
            }

            Transaction.Transaction(SelectOrder.lastID());

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCDBCPUtils.release(conn, pst, rs);
        }
    }
}
