package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.NhanVien;

public interface NhanVienDao extends Remote {

	public List<NhanVien> DanhSachNhanVien(int page, String txtSearch, String gioiTinh , String trangThaiLamViec , int limit) throws RemoteException;

	public NhanVien layThongTinNhanVienQuaSDT(String sdt) throws RemoteException;

	public int tongTrang( String txtSearch, String gioiTinh , String trangThaiLamViec, int limit) throws RemoteException;

	public String phatSinhMaTuDong() throws RemoteException;

	public boolean themNhanVien(NhanVien nhanVien) throws RemoteException;

	public boolean suaCmnd(String maNhanVien, String cmnd) throws RemoteException;

	public boolean suaNhanVien(NhanVien nhanVien) throws RemoteException;

	public boolean suaTrangThaiLamViecQuaSoDienThoai(String soDienThoai, boolean trangThaiLamViec) throws RemoteException;

	public boolean kiemTraSoDienThoai(String sdt) throws RemoteException;

	public boolean kiemTraSoChungMinhNhanDan(String cmnd) throws RemoteException;

	public boolean dangNhap(String sdt, String pass) throws RemoteException;

	public String layMaNhanVienQuaSoDienThoai(String sdt) throws RemoteException;

	public boolean xoaNhanVien(String maNhanVien) throws RemoteException;

}
