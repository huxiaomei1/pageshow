package edu.xcdq.dao;

import edu.xcdq.util.ConfigManager;

import java.sql.*;

public class BaseDao {
    Connection connection = null ;
    PreparedStatement ps = null ;
    ResultSet rs = null ;

    public boolean getConnection() {
        String driver = ConfigManager.getInstance().getString("jdbc.driver_class");
        String url = ConfigManager.getInstance().getString("jdbc.connection.url");
        String userName = ConfigManager.getInstance().getString("jdbc.connection.username");
        String password = ConfigManager.getInstance().getString("jdbc.connection.password");
        try {
            // 1 注册驱动
            Class.forName(driver);
            connection = DriverManager.getConnection(url, userName, password);
        } catch (Exception e) {
            e.printStackTrace();
            return false ;
        }
        return true;
    }

    // 封装sql代码 分两类 读 写操作
    // 写操作 （增删改）
    public int executeUpdate(String sql , Object[] params ) {
        int updateRows = 0 ;
        if (getConnection()) {
            try {
                ps = connection.prepareStatement(sql);
                for (int i = 0; i < params.length; i++) {
                    ps.setObject(i+1 , params[i] );
                }
                updateRows = ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return updateRows;
    }
    // 读操作
    public ResultSet executeSQL(String sql , Object [] params ) {
        if (getConnection() ) {
            try {
                ps = connection.prepareStatement(sql);
                for (int i = 0; i < params.length; i++) {
                    ps.setObject(i + 1 , params[i] );
                }
                rs = ps.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return rs ;
    }

    public boolean closeAll() {
        if ( rs != null ) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        if ( ps != null ) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        if ( connection != null ) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        return true;
    }

}
