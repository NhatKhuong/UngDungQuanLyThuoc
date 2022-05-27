package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.CongDung;

public interface CongDungDao extends Remote{
	public List<CongDung> getCongDung() throws RemoteException;
	public List<CongDung> getNCongDung() throws RemoteException;
	

}
