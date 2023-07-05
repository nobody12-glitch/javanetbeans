/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package databasemahasiswa;

import com.sun.jdi.connect.spi.Connection;

/**
 *
 * @author Mahasiswa
 */
public class Koneksi {
    String url = "jdbc:mysql://localhost:3306/dbMahasiswaJava";
    String userName = "root";
    String password = "";
    
    public Connection getConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(url, userName, password);
        } catch(ClassNotFoundException ex){
            System.out.println("Class com.mysql.jdbc.Driver tidak ditemukan");
            System.exit(0);
        } catch(SQLException ex){
            System.out.println("Gagal melakukan koneksi ke database");
            System.exit(0);
        }
        return null;
    }
}
