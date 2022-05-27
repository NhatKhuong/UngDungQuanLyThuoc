package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.Thuoc;

public interface ThuocDao extends Remote {
	public List<Thuoc> danhSachThuoc(int page,int limit, String tenThuoc, String thanhPhan, String dvt, String congDung,
			String nhomCongDung, String dangBaoChe, String nuoc , boolean trangThai,boolean cuaHang) throws RemoteException;

	public List<String> getDonViTinh() throws RemoteException;

	public List<String> getCongDung() throws RemoteException;

	public Thuoc getThuocTheoMa(String maThuoc) throws RemoteException;

	public int tongHang(String tenThuoc, String thanhPhan, String dvt, String congDung, String nhomCongDung,
			String dangBaoChe, String nuoc, boolean trangThai, boolean cuaHang) throws RemoteException;

	public boolean setNgungBan(boolean trangThai, String maThuoc) throws RemoteException;

	public boolean capNhatThuoc(Thuoc thuoc) throws RemoteException;

	public List<String> getNuocSanXuat() throws RemoteException;

	public List<String> getNhomCongDung() throws RemoteException;

	public List<String> getDangBaoChe() throws RemoteException;

	public List<Thuoc> getThuocSapHetHan() throws RemoteException;

	public List<Thuoc> getThuocSapHetSL() throws RemoteException;

}
