package com.qianfeng.oa;

import org.junit.Test;

import java.sql.*;
import java.util.Date;

public class Haproxy {

    @Test
    public void testHaproxy(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.72.188:5001/java1706", "root", "123456");

//            for (int i = 0; i < 100; i++) {
            PreparedStatement pr = null;
            ResultSet res = null;
            try {
                pr = conn.prepareStatement("select * from tb_demo");
                res = pr.executeQuery();
                if(res.next()) {
                    System.out.println(new Date().toLocaleString() + "->" + res.getInt(1));
                }
            } catch (Exception e) {
                e.printStackTrace();
                res.close();
                pr.close();
            }

//                Thread.sleep(25000);
//            }

            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
