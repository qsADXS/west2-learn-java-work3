import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteOrder {

    public static void Delete(int id) {
        Connection conn = null;
        Statement st =null;
        ResultSet rs =null;
        try{
            conn = JdbcUtils.getConnection();
            st = conn.createStatement();
            String sql = "DELETE FROM `order_management_system`.`order` WHERE `ID` = " + id +";";

            //订单未完成
            if(!SelectOrder.Select(id).isFinish()) {
                Transaction.Transaction(id);
            }
            if(SelectOrder.Select(id).isFinish()) {
                if(st.executeUpdate(sql) > 0){
                    System.out.println("删除成功");
                }
            }else {
                System.out.println("订单未完成，无法删除");
            }


        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.release(conn, st, rs);
        }

    }

}
