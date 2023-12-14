import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteCommodity {

    public static void Delete(int id) {
        Connection conn = null;
        Statement st =null;
        ResultSet rs =null;
        try{
            conn = JdbcUtils.getConnection();
            st = conn.createStatement();
            String sql = "DELETE FROM `order_management_system`.`commodity` WHERE `ID` = " + id +";";
            if(st.executeUpdate(sql) > 0){
                System.out.println("删除成功");
            }

        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.release(conn, st, rs);
        }

    }

}
