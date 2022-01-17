package cn.bugstack.guide.idea.plugin.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * VM options：
 * -javaagent:E:\itstack\booklet\idea-plugin\guide\guide-idea-plugin-probe\probe-agent\build\libs\probe-agent-1.0-SNAPSHOT-all.jar
 */
public class ApiTest {

    public static void main(String[] args) throws Exception {

        String URL = "jdbc:mysql://127.0.0.1:3306/itstack?characterEncoding=utf-8";
        String USER = "root";
        String PASSWORD = "123456";
        Class.forName("com.mysql.jdbc.Driver");

        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        String sql="SELECT * FROM USER WHERE id = ? AND name = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(1,1L);
        statement.setString(2,"谢飞机");
        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            System.out.println(rs.getString("name") + " " + rs.getString("address"));
        }

    }

}
