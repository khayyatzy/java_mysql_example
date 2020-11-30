/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author khayyatzy
 */
public class myConnector {
    Connection connect = null;
    
    public myConnector(){
        this.connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1/cpit_db","root","abcd1234");
        System.out.println("Output = "+this.connect.toString());
    }
    
    public void insertEmployee(String name, String address, String phone){
       String query = "insert into employee (name,address,phone) values (?,?,?);";
       PreparedStatement preparedStatement = connect.prepareStatement(query);
       preparedStatement.setString(1, name);
       preparedStatement.setString(2, address);
       preparedStatement.setString(3, phone);
       preparedStatement.executeUpdate();
    }
    
    public String selectEmployee(int id){
        String query = "select * from employee where id = "+id;
        Statement statement = connect.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        String myResult = "";
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            String address = resultSet.getString("address");
            myResult = name+","+address;
        }
        return myResult;
    }
    
    
    public static void main(String[] args){
        myConnector mycode = new myConnector();
        mycode.insertEmployee("Mohammed","KSU","9876541");
        System.out.println(mycode.selectEmployee(2));
    }
}
