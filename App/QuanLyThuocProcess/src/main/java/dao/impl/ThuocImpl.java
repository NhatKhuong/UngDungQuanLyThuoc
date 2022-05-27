package dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import dao.ThuocDao;
import entity.Thuoc;
import util.HibernateUtil;

public class ThuocImpl extends UnicastRemoteObject implements ThuocDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SessionFactory sessionFactory;

	public ThuocImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
		sessionFactory = HibernateUtil.getIntance().getSessionFactory();

	}

	@Override
	public List<Thuoc> danhSachThuoc(int page, int limit, String tenThuoc, String thanhPhan, String dvt, String congDung,
			String nhomCongDung, String dangBaoChe, String nuoc,boolean trangThai, boolean cuaHang) throws RemoteException {
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			int offset = page * limit;
			String sql = "select * from Thuoc join CongDung on Thuoc.maCongDung  = CongDung.maCongDung "
					+ "where tenThuoc like N'%" + tenThuoc + "%' " + "and congDung like N'%" + congDung + "%' "
					+ "and donViTinh like N'%" + dvt + "%' " + "and nhomCongDung like N'%" + nhomCongDung + "%'"
					+ "and dangBaoChe like N'%" + dangBaoChe + "%'" + "and nuocSanXuat like N'%" + nuoc + "%'"
					+ "and thanhPhan like N'%" + thanhPhan +"%'" ;

			if (cuaHang) {
				sql += "  and trangThaiKD = 1 and hanSuDung > GETDATE() ";
			}
			sql += " and trangThaiKD = '"+trangThai+"' order by Thuoc.tenThuoc offset " + offset + " rows fetch next "+limit+" rows only";
			
			List<Thuoc> thuoList = session.createNativeQuery(sql, Thuoc.class).getResultList();

			tr.commit();
			return thuoList;
		} catch (Exception e) {
			// TODO: handle exception
			tr.rollback();
			e.printStackTrace();
		}
		session.close();
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getDonViTinh() throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		List<String> list = new ArrayList<String>();
//		list.add("Tất cả");
		try {

			tr.begin();
			String sql = "select distinct donViTinh from Thuoc";
			list = session.createNativeQuery(sql).getResultList();

			tr.commit();
			return list;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getCongDung() throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		List<String> list = new ArrayList<String>();
//		list.add("Tất cả");
		try {

			tr.begin();
			String sql = "select congDung from CongDung";
			list = session.createNativeQuery(sql).getResultList();
			tr.commit();
			return list;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getNhomCongDung() throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		List<String> list = new ArrayList<String>();
//		list.add("Tất cả");
		try {

			tr.begin();
			String sql = "select nhomCongDung from CongDung";
			list = session.createNativeQuery(sql).getResultList();
			tr.commit();
			return list;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getNuocSanXuat() throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		List<String> list = new ArrayList<String>();
//		list.add("Tất cả");
		try {

			tr.begin();
			String sql = "  select distinct nuocSanXuat from [dbo].[Thuoc]";
			list = session.createNativeQuery(sql).getResultList();
			tr.commit();
			return list;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getDangBaoChe() throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		List<String> list = new ArrayList<String>();
//		list.add("Tất cả");
		try {

			tr.begin();
			String sql = "select distinct dangBaoChe from [dbo].[Thuoc]";
			list = session.createNativeQuery(sql).getResultList();
			tr.commit();
			return list;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}

	@Override
	public Thuoc getThuocTheoMa(String maThuoc) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();

		try {
			tr.begin();

			Thuoc thuoc = session.find(Thuoc.class, maThuoc);
			tr.commit();

			return thuoc;

		} catch (Exception e) {
			// TODO: handle exception
			tr.rollback();
		}
		session.close();
		return null;
	}

	@Override
	public int tongHang(String tenThuoc, String thanhPhan, String dvt, String congDung, String nhomCongDung,
			String dangBaoChe, String nuoc, boolean trangThai,boolean cuaHang) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();

		try {
			tr.begin();
			String sql = "select count(*) from Thuoc join CongDung on Thuoc.maCongDung  = CongDung.maCongDung "
					+ "where tenThuoc like N'%" + tenThuoc + "%' " + "and congDung like N'%" + congDung + "%' "
					+ "and donViTinh like N'%" + dvt + "%' " + "and nhomCongDung like N'%" + nhomCongDung + "%'"
					+ "and dangBaoChe like N'%" + dangBaoChe + "%'" + "and nuocSanXuat like N'%" + nuoc + "%'"
					+ "and thanhPhan like N'%" + thanhPhan + "%' ";
			if (cuaHang) {
				sql += " and trangThaiKD = 1 and hanSuDung > GETDATE()";
			}
			sql += " and trangThaiKD = '"+trangThai+"'";
			String soTrang = session.createNativeQuery(sql).uniqueResult().toString();
			int n = Integer.parseInt(soTrang);
			tr.commit();
			return n;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tr.rollback();
		}
		return -1;
	}

	@Override
	public boolean setNgungBan(boolean trangThai, String maThuoc) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		try {

			tr.begin();
			Thuoc thuoc = session.find(Thuoc.class, maThuoc);
			thuoc.setTrangThaiKinhDoanh(trangThai);
			session.update(thuoc);
			tr.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}

	@Override
	public boolean capNhatThuoc(Thuoc thuoc) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		try {

			tr.begin();
			session.update(thuoc);
			tr.commit();
			return true;

		} catch (Exception e) {
			// TODO: handle exception
			tr.rollback();
		}
		session.close();
		return false;

	}

	@Override
	public List<Thuoc> getThuocSapHetHan() throws RemoteException {
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();

		try {
			tr.begin();
			String sql = "SELECT * FROM Thuoc WHERE DATEDIFF(day, GETDATE(), hanSuDung ) BETWEEN 1 AND 90  ORDER BY hanSuDung";
			List<Thuoc> thuocs = session.createNativeQuery(sql, Thuoc.class).getResultList();
			tr.commit();
			return thuocs;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tr.rollback();
		}
		session.close();
		return null;
	}

	@Override
	public List<Thuoc> getThuocSapHetSL() throws RemoteException {
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();

		try {
			tr.begin();
			String sql = "SELECT * FROM Thuoc WHERE soLuongBanDau < 10";
			List<Thuoc> thuocs = session.createNativeQuery(sql, Thuoc.class).getResultList();

			tr.commit();
			return thuocs;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tr.rollback();
		}
		session.close();
		return null;
	}

}
