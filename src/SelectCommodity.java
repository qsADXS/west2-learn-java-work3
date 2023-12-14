import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectCommodity {
    //不懂为什么这里用JDBC连接池会报错，prepareStatement类没能把占位符改掉，所以就不用JDBC了，刚好这里不会出现注入问题
    public static Commodity Select(int id) {
        Connection conn = null;
        Statement st =null;
        ResultSet rs =null;
        Commodity c = new Commodity();
        try{
            conn = JdbcUtils.getConnection();
            String sql = "SELECT `ID`,`commodity_name`,`price`,`inventory` FROM order_management_system.commodity WHERE ID = " + id +";";
            st = conn.createStatement();
            rs = st.executeQuery(sql);

            if(rs.next()){
                c.set(rs.getInt("ID"),rs.getString("commodity_name"),rs.getDouble("price"),rs.getInt("inventory"));
            }

        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.release(conn, st, rs);
        }

        return c;
    }

    public static void SelectASC(String s) {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        Commodity c = new Commodity();
        try {
            conn = JdbcUtils.getConnection();
            String sql = "SELECT `ID`,`commodity_name`,`price`,`inventory` FROM order_management_system.commodity  ORDER BY " + s + " DESC;";
            st = conn.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                System.out.println("ID:" + rs.getInt("ID") +
                        " 商品名：" + rs.getString("commodity_name") +
                        " 价格：" + rs.getDouble("price") +
                        " 存货：" + rs.getInt("inventory"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.release(conn, st, rs);
        }
    }
}
