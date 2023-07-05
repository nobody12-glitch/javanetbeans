/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package databasemahasiswa;

import com.sun.jdi.connect.spi.Connection;

/**
 *
 * @author Mahasiswa
 */
public class Databasemahasiswa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        deleteDatabase();
        if (!createDatabase())
            System.exit(0);
        System.out.println("Database berhasil diciptakan");
        
        if (!createTable())
            System.exit(0);
        System.out.println("Tabel berhasil diciptakan");
    }
    private static Connection getConnection(){
        try{
            String url = "jdbc:mysql://localhost:3306/mysql";
            String userName = "root";
            String pasword = "";
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(url,userName,password);
        } catch(ClassNotFoundException ex){
            System.out.println("Class com.mysql.jdbc.Driver tidak ditemukan");
            System.exit(0);
        } catch(SQLException ex){
            System.out.println("Gagal melakukan koneksi ke mysql");
            System.exit(0);
        }
        return null;
    }
    private static boolean deleteDatabase(){
        Connection cn = getConnection();
        try{
            PreparedStatement ps = cn.prepareStatement("DROP DATABASE dbMahasiswaJava");
            ps.executeUpdate();
            return true;
        } catch (SQLException ex){
            System.out.println("Gagal menghapus database lama");
            return false;
        }
    }
    private static boolean createDatabase(){
        Connection cn = getConnection();
        try{
            PreparedStatement ps = cn.prepareStatement("CREATE DATABASE dbMahasiswaJava");
            ps.executeUpdate();
            return true;
        } catch (SQLException ex){
            System.out.println("Gagal menciptakan database baru");
            return false;
        }
    }
    private static boolean createTable(){
        Connection cn = getConnection();
        try{
            PreparedStatement ps = cn.prepareStatement("USE dbmahasiswajava;");
            ps.executeUpdate();
            
            String sql = "CREATE TABLE tblmahasiswa(nim char(15) not null, nama char(100), ";
            sql+= "alamat char(100), telp char(50), primary key(nim));";
            ps = cn.prepareStatement(sql);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex){
            System.out.println("Gagal menciptakan table mahasiswa");
            return false;
        }
    }
    
}
