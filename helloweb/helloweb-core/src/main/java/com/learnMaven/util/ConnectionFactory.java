package com.learnMaven.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

/**
 * Created by 马昕 on 2018/10/26.
 */
public class ConnectionFactory {
    private static String driver;
    private static String dburl;
    private static String user;
    private static String password;

    private static final ConnectionFactory factory=new ConnectionFactory();

    private Connection connection;

    static {
        Properties properties=new Properties();
        try {
            InputStream in=ConnectionFactory.class.getClassLoader().getResourceAsStream("dbconfig.properties");
            properties.load(in);

        }catch (Exception e){
            e.printStackTrace();
        }
        driver=properties.getProperty("driver");
        dburl=properties.getProperty("dburl");
        user=properties.getProperty("user");
        password=properties.getProperty("password");
    }

    private ConnectionFactory(){

    }

    public static ConnectionFactory getInstance(){
        return factory;
    }

    public Connection makeConnection() {
        try {
            Class.forName(driver);
            connection= DriverManager.getConnection(dburl,user,password);
        }catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }

    public static void main(String[] args) throws SQLException {
        Connection connection=ConnectionFactory.getInstance().makeConnection();
        System.out.println(connection.getAutoCommit());
    }
}
