package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.CT_HoaDon;
import entity.HoaDon;

public interface HoaDonDao extends Remote{
	public  List<HoaDon> layDanhSachHoaDon( String maHD,String tenKH,String sdt_KH,String sdt_NV,String ngayLapHD,int cmb,int page, int limit) throws RemoteException;
	
    public  int tongTrang( String maHD,String tenKH,String sdt_KH,String sdt_NV,String ngayLapHD,int cmb, int limit) throws RemoteException;
    
    public  String phatSinhMaTuDong() throws RemoteException;
        
    public  boolean themHoaDon(HoaDon hoaDon) throws RemoteException;
        
    public  HoaDon getHoaDonTheoMa( String maHD) throws RemoteException;
    
	public List<CT_HoaDon> chiTietHoaDon(String maHoaDonBan) throws RemoteException;
	
	public List<HoaDon> getHoaDonByNgay(String ngay, String maNV) throws RemoteException;
	
	public List<HoaDon> getHoaDonByThang(String ngay, String thang, String nam, String maNV) throws RemoteException;
	
	public List<HoaDon> getHoaDonByNam (String thang, String nam, String maNV) throws RemoteException;

}
