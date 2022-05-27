package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface DiaChiDao extends Remote{
	public List<String> danhSachPhuongXa() throws RemoteException;
	public List<String> danhSachPhuongXaTheoQuanHuyen(String quanHuyen) throws RemoteException;
	public List<String> danhSachQuanHuyen() throws RemoteException;
	public List<String> danhSachQuanHuyenTheoTinh(String tinh) throws RemoteException;
	public List<String> danhSachTinhTP() throws RemoteException;
	public List<String> danhSachTinhTPTheoQuanHuyen(String quanHuyen) throws RemoteException;
	public String getMaDC(String tinhTp, String quanHuyen, String xaPhuong) throws RemoteException;
	
}
