import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class UpdateCommodity {
    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;//解决SQL注入问题
        ResultSet rs = null;
        try {
            Scanner in = new Scanner(System.in);
            conn = JDBCDBCPUtils.getConnection();
            String sql = "UPDATE `order_management_system`.`commodity` SET `commodity_name` = ? , `price` = ? , `inventory` = ? WHERE `ID` = ?; ";

            pst = conn.prepareStatement(sql);
            //读取商品编号
            System.out.println("输入需要更改的商品编号:");
            Commodity c = new Commodity();
            int id = in.nextInt();
            c = SelectCommodity.Select(id);
            while (c.getID() == -1) {
                System.out.println("未查询到该商品，请重新输入");
                id = in.nextInt();
                c = SelectCommodity.Select(id);
            }
            pst.setInt(4, id);

            in.nextLine();
            System.out.println("输入修改后的商品名：");
            String newName = in.nextLine();
            pst.setString(1, newName);

            System.out.println("输入涨价或降价多少");
            int price = in.nextInt();
            while (price + c.getInventory() < 0) {
                System.out.println("难度你想做慈善吗，卖东西倒贴钱，输入合适的涨跌价格");
                 price = in.nextInt();
            }
            pst.setInt(2, price + c.getInventory());


            System.out.println("输入进货数量");
            int number = in.nextInt();
            while (number < 0) {
                System.out.println("输入正确的数量");
                number = in.nextInt();
            }
            pst.setInt(3, number + c.getInventory());



            //设置是否完成

            if (pst.executeUpdate() > 0) {
                System.out.println("修改成功");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCDBCPUtils.release(conn, pst, rs);
        }
    }
}
