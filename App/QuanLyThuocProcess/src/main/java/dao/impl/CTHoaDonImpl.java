package dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import dao.CTHoaDonDao;
import entity.CT_HoaDon;
import util.HibernateUtil;

public class CTHoaDonImpl extends UnicastRemoteObject implements CTHoaDonDao{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3880977571383756453L;
	private SessionFactory sessionFactory;
	
	public CTHoaDonImpl() throws RemoteException {
		super();
		this.sessionFactory = new HibernateUtil().getSessionFactory();
	}

	@Override
	public List<CT_HoaDon> chiTietHoaDon(String maHD) throws RemoteException {
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		
		try {
			tr.begin();
			String sql = "select * from CT_HoaDonBan where maHoaDonBan = '" + maHD + "' order by maHoaDonBan desc";
			List<CT_HoaDon> cthd =  session.createNativeQuery(sql, CT_HoaDon.class).getResultList();
			tr.commit();
			return cthd;
		} catch (Exception e) {
			tr.rollback();
		} finally {
			session.close();
		}
		return null;
	}

}
