package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.KhachHang;

public interface KhachHangDao extends Remote{
	public  String phatSinhMaKhachHang() throws RemoteException;

	public  List<KhachHang> danhSachKhachHang( int page, String txtSearch, String gioiTinh) throws RemoteException;

	public  KhachHang layThongTinKhachHangQuaSDT( String sdt) throws RemoteException;

	public  int tongSoHang(String txtSearch ,String trangThaiLamViec) throws RemoteException;

	public  boolean themKhachHang (KhachHang khachHang) throws RemoteException;

	public  boolean kiemTraSoDienThoai( String sdt) throws RemoteException;

	public  Boolean suaKhachHang(KhachHang khachHang) throws RemoteException;
	
	public  KhachHang layThongTinKhachHangQuaId( String id) throws RemoteException;

	
}
