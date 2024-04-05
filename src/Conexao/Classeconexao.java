/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexao;


import java.sql.*;


public class Classeconexao {
    public static Connection conector(){
        java.sql.Connection conexao= null;
        String driver = "com.mysql.jdbc.Driver";
        
        String url="jdbc:mysql://localhost:3325/dbsystem";
        String user="root";
        String password="";
        
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, password);
            return conexao;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static Connection getConexao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
