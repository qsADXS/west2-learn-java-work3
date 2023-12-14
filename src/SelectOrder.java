import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectOrder {

    public static order Select(int id) {
        Connection conn = null;
        Statement st =null;
        ResultSet rs =null;
        order o = new order();
        try{
            conn = JdbcUtils.getConnection();
            String sql = "SELECT `ID`,`commodity_id`,`number`,`price`,`date`,`is_finish` FROM `order_management_system`.`order` WHERE ID = " + id +";";
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            if(rs.next()){
                o.set(rs.getInt("ID"),rs.getInt("commodity_id"),rs.getInt("number"),rs.getDouble("price"),rs.getObject("date").toString(),rs.getBoolean("is_finish"));
            }

        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.release(conn, st, rs);
        }

        return o;
    }

    public static void SelectASC(String s) {
        Connection conn = null;
        Statement st =null;
        ResultSet rs =null;
        order o = new order();
        try{
            conn = JdbcUtils.getConnection();
            String sql = "SELECT `ID`,`commodity_id`,`number`,`price`,`date`,`is_finish` FROM `order_management_system`.`order` ORDER BY " + s + " ASC;";
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()){
                o.set(rs.getInt("ID"),rs.getInt("commodity_id"),rs.getInt("number"),rs.getDouble("price"),rs.getObject("date").toString(),rs.getBoolean("is_finish"));
                System.out.println(o.toString());
            }

        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.release(conn, st, rs);
        }

    }
    public static int lastID() {
        Connection conn = null;
        Statement st =null;
        ResultSet rs =null;
        order o = new order();
        try{
            conn = JdbcUtils.getConnection();
            String sql = "SELECT `ID`,`commodity_id`,`number`,`price`,`date`,`is_finish` FROM `order_management_system`.`order` ORDER BY ID DESC;";
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            if (rs.next()){
                o.set(rs.getInt("ID"),rs.getInt("commodity_id"),rs.getInt("number"),rs.getDouble("price"),rs.getObject("date").toString(),rs.getBoolean("is_finish"));

            }


        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.release(conn, st, rs);
        }
        return o.getID();
    }

}
