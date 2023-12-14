# 数据库创建过程

```sql
CREATE DATABASE order_management_system CHARACTER SET utf8 COLLATE utf8_general_ci; 
```

```sql
CREATE TABLE `order_management_system`.`commodity`
( `ID` INT(5) NOT NULL AUTO_INCREMENT COMMENT '商品编号', 
`commodity_name` VARCHAR(20) NOT NULL COMMENT '商品名', 
`price` DOUBLE(4,2) NOT NULL COMMENT '价格', 
PRIMARY KEY (`ID`) 
) ENGINE=INNODB CHARSET=utf8 COLLATE=utf8_general_ci; 
```

```sql
 CREATE TABLE `order_management_system`.`order`
 ( `ID` INT(5) NOT NULL COMMENT '订单编号', 
 `commodity_id` INT(5) NOT NULL COMMENT '商品id', 
 `number` INT(5) NOT NULL DEFAULT 1 COMMENT '商品数量', 
 `price` DOUBLE(8,2) NOT NULL COMMENT '订单总价格',
  PRIMARY KEY (`ID`) 
  )ENGINE=INNODB CHARSET=utf8 COLLATE=utf8_general_ci;  
```

突然发现忘记加订单时间了

```sql
ALTER TABLE `order_management_system`.`order` 
ADD COLUMN `date` DATETIME NULL COMMENT '下单时间' AFTER `price`; 

```

想起来后面需要完成事务，在商品表中添加库存，在订单表中添加订单情况

```sql
ALTER TABLE `order_management_system`.`commodity` ADD COLUMN `inventory` INT(10) 
DEFAULT 0 NOT NULL COMMENT '库存容量' AFTER `price`; 

ALTER TABLE `order_management_system`.`order` ADD COLUMN `is_finish` BOOLEAN 
DEFAULT FALSE NOT NULL COMMENT '订单状态' AFTER `data`; 
```

代码写一半发现order表主键忘记设置自增

```sql
ALTER TABLE `order_management_system`.`order` CHANGE `ID` `ID` INT(5) NOT NULL AUTO_INCREMENT COMMENT '订单编号'; 
```

# 测试

## 初始

![image-20231214202141204](https://s2.loli.net/2023/12/14/Yc2LF7hA6dbyQuW.png)

![image-20231214202352911](https://s2.loli.net/2023/12/14/pMQR8VaUdE1rBov.png)

![image-20231214202150603](https://s2.loli.net/2023/12/14/wp8XIedAxnoRJiS.png)

![image-20231214202341783](https://s2.loli.net/2023/12/14/k4LYW7TSFUCOA5N.png)

## 测试插入

```java
        /*
        测试插入
        */
        InsertCommodity.main(null);
        InsertOrder.main(null);
        /*
        插入的商品三个商品，存货分别为20,0,56
        购买了第一个商品
        */
```

![image-20231214203450452](https://s2.loli.net/2023/12/14/lJQjex4OVmC8ftB.png)

![image-20231214203518890](https://s2.loli.net/2023/12/14/C7nQw4GWPIZrmAV.png)

## 测试删除

```java
        /*
        删除测试
        */

        InsertOrder.main(null);//购买商品1 20个,此时购买失败，但是插入成功，进行一次事务，回滚
        DeleteOrder.Delete(10002);//删除失败
        UpdateOrder.main(null);//更新购买数量为 15个
        UpdateCommodity.main(null);//更新商品1数量为15个
        DeleteOrder.Delete(10002);//删除成功。进行一次事务
```

![image-20231214204242933](https://s2.loli.net/2023/12/14/ktK9H4rFV8vLnip.png)

![image-20231214204248418](https://s2.loli.net/2023/12/14/1Dv3xYs9mfVid2I.png)

## 修改测试





![image-20231214205510711](https://s2.loli.net/2023/12/14/zrn54ca9ARsCygi.png)

![image-20231214205529537](https://s2.loli.net/2023/12/14/G1itYag2vWCXZKP.png)

```java
        /*
        测试修改
         */

        InsertOrder.main(null);//增加订单，购买商品2 10个
        UpdateOrder.main(null);//修改为购买商品3 15个
        UpdateCommodity.main(null);//将商品1名字改为不想取名字
```



## 查询测试

```java
        /*
        查询测试
         */

        SelectOrder.Select(10001);
        SelectOrder.SelectASC("price");//可以自行设置表中有的字段

        SelectCommodity.Select(10001);
        SelectCommodity.SelectASC("inventory");//可以自行设置表中有的字段
```





![image-20231214205912578](https://s2.loli.net/2023/12/14/l1EZFtbsYS83c5D.png)

# 最后的最后

- 因为忘记有联表查询(`join on`),所以在写程序的时候没有用，在编写学习纪录的时候突然想起来
- 下面是``SELECT`的用法

```sql
SELECT [ALL | DISTINCT]
{* | table.* | [table.field1[as alias1][,table.field2[as alias2]][,...]]}
FROM table_name [as table_alias]
    [left | right | inner join table_name2]  -- 联合查询
    [WHERE ...]  -- 指定结果需满足的条件
    [GROUP BY ...]  -- 指定结果按照哪几个字段来分组
    [HAVING]  -- 过滤分组的记录必须满足的次要条件
    [ORDER BY ...]  -- 指定查询记录按一个或多个条件排序
    [LIMIT {[offset,]row_count | row_countOFFSET offset}];
    --  指定查询的记录从哪条至哪条
```

