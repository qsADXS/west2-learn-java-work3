import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


//感觉用购买来表现事务有点怪怪的，但是只是为了写事务的代码
public class Transaction {
    public static void Transaction(int orderID) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = JDBCDBCPUtils.getConnection();
            //关闭自动提交 自动开启事务
            conn.setAutoCommit(false);//开启事务
            int commodityID = SelectOrder.Select(orderID).getCommodityId();
            int inventory = SelectCommodity.Select(commodityID).getInventory();
            int number = SelectOrder.Select(orderID).getNumber();
            // 购买商品 number 个
            String sql1 = "update `order_management_system`.`commodity` set inventory = " +(inventory-number) + " where ID=" + commodityID +";";
            ps = conn.prepareStatement(sql1);
            ps.executeUpdate();
            String sql2 = "update `order_management_system`.`order` set is_finish = true where ID=" + orderID +";";
            ps = conn.prepareStatement(sql2);
            ps.executeUpdate();

            //这里没有和下面的写在一起是为了和mysql的操作错误区分开
           if(inventory-number < 0){
                System.out.println("购买失败");
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }else {
                //业务完毕，提交事务
                System.out.println("购买成功");
                conn.commit();
            }
            //if(inventory-number < 0)throw new SQLException();

        } catch (SQLException e) {
            System.out.println("购买失败");
            e.printStackTrace();
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            JDBCDBCPUtils.release(conn, ps, rs);
        }
    }

    public static void main(String[] args) {

    }

}
