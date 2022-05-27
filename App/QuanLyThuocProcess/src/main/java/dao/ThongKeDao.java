package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface ThongKeDao extends Remote{
	public double getDoanhThuTheoThang(int month, int year) throws RemoteException;
	public List<String> getSoNam() throws RemoteException;
	public Map<String, Integer> getTop10ThuocBanChayNhatTheoNam(int year) throws RemoteException;
}
