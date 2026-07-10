package com.yujun.mtt.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.Connection;

public class JDBCUtil {
    // 每一个线程准备一个自己的储物柜，用于存储连接对象，
    // 服务器现在有三个请求，SpringBoot 会开三个线程。如果没有 ThreadLocal 就会都操作同一个 Connection，整个程序就乱了。
    // 所以每个线程应该拥有自己的 Connection。
    // ThreadLocal：Thread-1 -> Connection-1
    // ThreadLocal：Thread-2 -> Connection-2
    // ThreadLocal：Thread-3 -> Connection-3
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
    // 连接池，里面维护着很多 Connection，dataSource.getConnection() 从连接池中获取一个 Connection。
    private static DataSource dataSource;

    // 类第一次加载的时候，初始化连接池，只初始化一次。
    static {
        // 读取.properties配置文件
        Properties properties = new Properties();
        InputStream resourceAsStream = JDBCUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
        try {
            properties.load(resourceAsStream);
            // 初始化连接池
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**1 向外提供连接池的方法*/
    public static DataSource getDataSource(){
        return dataSource;
    }

    /**2 向外提供连接的方法*/
    /**
     * getConnection() -> ThreadLocal 有没有？-> 没有 -> 连接池拿一个 -> 保存到 ThreadLocal -> 返回
     * 第二次：getConnection() -> ThreadLocal 有？-> 有 -> 直接返回
     */
    public static Connection getConnection(){
        // 去 ThreadLocal 看看：这个线程有没有 Connection。例如：Thread-1 -> Connection-1
        Connection connection = threadLocal.get();
        // 如果没有，就从连接池中获取一个 Connection。
        if (null == connection) {
            try {
                connection = dataSource.getConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            // 保存到 ThreadLocal：Thread-1 -> Connection-1，再次调用的时候，就能拿到，不会重新获取
            threadLocal.set(connection);
        }
        // 如果有线程中有，就直接返回。
        return connection;
    }

    /**定义一个归还连接的方法（解除和ThreadLocal之间的关联关系）*/
    public static void releaseConnection(){
        Connection connection = threadLocal.get();
        if (null != connection) {
            // 解除绑定，原来：Thread-1 -> Connection-1，现在：Thread-1 -> null
            threadLocal.remove();
            // 把连接设置回自动提交的连接
            try {
                connection.setAutoCommit(true);
                // 自动归还到连接池
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}