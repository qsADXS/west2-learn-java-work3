import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertCommodity {
    public static void main(String[] args) throws SQLException {
        Connection conn =null;
        PreparedStatement pst =null;//解决SQL注入问题
        ResultSet rs =null;
        try{
            Scanner in = new Scanner(System.in);
            conn = JDBCDBCPUtils.getConnection();
            StringBuilder sql = new StringBuilder("INSERT INTO `order_management_system`.`commodity` (`commodity_name`, `price`,`inventory`) VALUES ");

            System.out.println("输入需要插入的行数");
            int i = in.nextInt();
            sql.append("(?,?,?),".repeat(Math.max(0, i - 1)));
            sql.append("(?,?,?);");
            int count = 1;//计数
            pst = conn.prepareStatement(sql.toString());
            for (int j = 0; j < i; j++) {
                in.nextLine();
                System.out.println("输入要添加的商品名称：");
                pst.setString(count++,in.nextLine());
                System.out.println("输入要添加的商品价格：");
                pst.setDouble(count++,in.nextDouble());
                System.out.println("输入要添加的商品存货：");
                pst.setInt(count++,in.nextInt());
            }
            int num = 0;
            if (pst != null) {
                num = pst.executeUpdate();
            }
            if (num > 0){
                System.out.println("成功插入"+num+"条数据");
            }

        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCDBCPUtils.release(conn, pst, rs);
        }
    }
}
