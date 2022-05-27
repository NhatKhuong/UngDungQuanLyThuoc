package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.CT_HoaDon;

public interface CTHoaDonDao extends Remote{
	public List<CT_HoaDon> chiTietHoaDon(String maHD) throws RemoteException;
}
