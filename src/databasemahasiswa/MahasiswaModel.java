/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package databasemahasiswa;

import com.sun.jdi.connect.spi.Connection;
import javax.naming.spi.DirStateFactory;
import javax.swing.JOptionPane;

/**
 *
 * @author Mahasiswa
 */
public class MahasiswaModel {
    String nim;
    String nama;
    String alamat;
    String telp;
    String konek = new Koneksi();
    PreparedStatement ps;
    
    public boolean simpan(){
        Connection cn=konek.getConnection();
        try{
            ps=cn.prepareStatement("INSERT INTO tblMahasiswa(nim, nama, alamat, telp) VALUES(?, ?, ?, ?)");
            ps.setString(1, nim);
            ps.setString(2, nama);
            ps.setString(3, alamat);
            ps.setString(4, telp);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Gagal dalam menyimpan data mahasiswa");
            return false;
        }
    }
    public boolean hapus(){
        Connection cn=konek.getConnection();
        try{
            ps=cn.prepareStatement("DELETE FROM tblMahasiswa WHERE nim = ?");
            ps.setString(1, nim);
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Gagal dalam menghapus data mahasiswa dengan nim"+nim);
            return false;
        }
    }
    public MahasiswaModel cari(String nim){
        Connection cn=konek.getConnection();
        try{
            ps=cn.prepareStatement("SELECT nama, alamat, telp FROM tblMahasiswa WHERE nim = ?");
            ps.setString(1, nim);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                this.nim=nim;
                this.nama=rs.getString(1);
                this.alamat=rs.getString(2);
                this.telp=rs.getString(3);
            }
            rs.close();
            ps.close();
            return this;
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Gagal dalam mencari mahasiswa dengan nim"+nim);
            return null;
        }
    }
    public boolean update(String nim){
        Connection cn=konek.getConnection();
        String sql="UPDATE tblMahasiswa SET nama = ?, alamt = ?, telp = ? WHERE nim = ?";
        try{
            ps=cn.prepareStatement(sql);
            ps.setString(1, nama);
            ps.setString(2, alamat);
            ps.setString(3, telp);
            ps.setString(4, nim);
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException ex){
            String msg="Gagal dalam menghapus data mahasiswa dengan nim "+nim;
            JOptionPane.showMessageDialog(null, msg);
            return false;
        }
    }
}
