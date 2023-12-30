# 任务

- 创建了两个表
  - 商品: 商品编号、商品名、商品价格、存货
  - 订单: 订单编号、商品信息、下单时间、订单价格、是否完成订单

- 通过sqlyog创建表
- 编写JBDC工具，完成要求任务
- 完成了测试类

# Bonus

- 使用其他连接池，如Druid。（使用了JDBC连接池）

# 项目目录结构介绍

```tree
├─lib
│      commons-dbcp2-2.11.0.jar
│      commons-logging-1.3.0.jar
│      commons-pool2-2.12.0.jar
│      mysql-connector-j-8.2.0.jar
│      
├─out
│  └─production
│      └─order_management_system
│              Commodity.class
│              dbcp.properties
│              DeleteCommodity.class
│              DeleteOrder.class
│              InsertCommodity.class
│              InsertOrder.class
│              JDBCDBCPUtils.class
│              JdbcUtils.class
│              Main.class
│              order.class
│              SelectCommodity.class
│              SelectOrder.class
│              Test.class
│              Transaction.class
│              UpdateCommodity.class
│              UpdateOrder.class
│              
└─src
        Commodity.java
        dbcp.properties
        DeleteCommodity.java
        DeleteOrder.java
        InsertCommodity.java
        InsertOrder.java
        JDBCDBCPUtils.java
        JdbcUtils.java
        order.java
        SelectCommodity.java
        SelectOrder.java
        Test.java
        Transaction.java
        UpdateCommodity.java
        UpdateOrder.java
        

```
