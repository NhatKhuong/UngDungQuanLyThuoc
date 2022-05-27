package dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import dao.DiaChiDao;
import util.HibernateUtil;

public class DiaChiImpl extends UnicastRemoteObject implements DiaChiDao{
	private SessionFactory sessionFactory;

	public DiaChiImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
		this.sessionFactory = new HibernateUtil().getSessionFactory();
		
		
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public List<String> danhSachPhuongXa() throws RemoteException {
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		
		try {
			tr.begin();
			
			String sql = "SELECT DISTINCT phuongXa FROM DiaChi";
			@SuppressWarnings("unchecked")
			List<String> list = session.createNativeQuery(sql).getResultList();
			
			tr.commit();
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tr.rollback();
		}
		session.close();
		return null;
	}

	@Override
	public List<String> danhSachPhuongXaTheoQuanHuyen(String quanHuyen) throws RemoteException {
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		
		try {
			tr.begin();
			
			String sql = "SELECT DISTINCT phuongXa FROM DiaChi where quanHuyen like N'%"+quanHuyen+"%'";
			@SuppressWarnings("unchecked")
			List<String> list = session.createNativeQuery(sql).getResultList();
			
			tr.commit();
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tr.rollback();
		}
		session.close();
		return null;
	}

	@Override
	public List<String> danhSachQuanHuyen() throws RemoteException {
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		
		try {
			tr.begin();
			
			String sql = "SELECT DISTINCT quanHuyen FROM DiaChi";
			@SuppressWarnings("unchecked")
			List<String> list = session.createNativeQuery(sql).getResultList();
			
			tr.commit();
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tr.rollback();
		}
		session.close();
		return null;
	}

	@Override
	public List<String> danhSachQuanHuyenTheoTinh(String tinh) throws RemoteException {
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		
		try {
			tr.begin();
			
			String sql = "SELECT DISTINCT quanHuyen FROM DiaChi where tinhTp like N'%"+tinh+"%' order by quanHuyen";
			@SuppressWarnings("unchecked")
			List<String> list = session.createNativeQuery(sql).getResultList();
			
			tr.commit();
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tr.rollback();
		}
		session.close();
		return null;
	}

	@Override
	public List<String> danhSachTinhTP() throws RemoteException {
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		
		try {
			tr.begin();
			
			String sql = "SELECT DISTINCT tinhTp FROM DiaChi";
			@SuppressWarnings("unchecked")
			List<String> list = session.createNativeQuery(sql).getResultList();
			
			tr.commit();
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tr.rollback();
		}
		session.close();
		return null;
	}

	@Override
	public List<String> danhSachTinhTPTheoQuanHuyen(String quanHuyen) throws RemoteException {
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		
		try {
			tr.begin();
			
			String sql = "SELECT DISTINCT tinhTp FROM DiaChi where quanHuyen like N'%"+quanHuyen+"%'";
			@SuppressWarnings("unchecked")
			List<String> list = session.createNativeQuery(sql).getResultList();
			
			tr.commit();
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tr.rollback();
		}
		session.close();
		return null;
	}

	@Override
	public String getMaDC(String tinhTp, String quanHuyen, String xaPhuong) throws RemoteException {
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		String ma = "";
		try {
			tr.begin();
			
			String sql = "SELECT maDC FROM DiaChi where quanHuyen like N'%"+quanHuyen+"%'  and tinhTp like N'%"+tinhTp+"%' and phuongXa like N'%"+xaPhuong+"%' " ;
			ma = session.createNativeQuery(sql).uniqueResult().toString();
			
			tr.commit();
			return ma;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tr.rollback();
		}
		session.close();
		return ma;
	}
	

}
