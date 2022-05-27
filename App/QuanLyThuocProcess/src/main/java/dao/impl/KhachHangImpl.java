package dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import dao.KhachHangDao;
import dao.MaTuDong;
import entity.KhachHang;
import util.HibernateUtil;


public class KhachHangImpl extends UnicastRemoteObject implements KhachHangDao{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SessionFactory sessionFactory;
	private static final int limit = 20;
	private MaTuDong maTuDong;
	

	public KhachHangImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
		
		this.sessionFactory = HibernateUtil.getIntance().getSessionFactory();
		this.maTuDong = new MaTuDongImpl();
	}
	@Override
	public String phatSinhMaKhachHang() throws RemoteException {
//		MaTuDong maTuDong = new MaTuDongImpl();
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		try {
			
			tr.begin();
			
			String sql = "select max(MaKhachHang) from KhachHang";
			String maxId = session.createNativeQuery(sql).uniqueResult().toString();
			
			tr.commit();
			return "KH"+maTuDong.fomatAA000000(maxId.substring(2));
			
		} catch (Exception e) {
			// TODO: handle exception
			//e.printStackTrace();
			tr.rollback();
		}
		return null;
		
	}

	@Override
	public List<KhachHang> danhSachKhachHang(int page, String txtSearch, String gioiTinh) throws RemoteException {
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		if (txtSearch == null)
			txtSearch = "";
		if (gioiTinh == null)
			gioiTinh = "";
		int offset = page * limit ;// lay du lieu bat dau tu vi tri page*20

		try {
			tr.begin();

			String sql = "select * from KhachHang inner join DiaChi on  KhachHang.maDC = DiaChi.maDC where tenKhachHang like N'%"
					+ txtSearch + "%' and gioiTinh like '%" + gioiTinh + "%'" + " order by maKhachHang desc "
					+ " OFFSET " + offset + " ROWS FETCH NEXT " + limit + " ROWS ONLY";

			List<KhachHang> dsKhachHang = session.createNativeQuery(sql, KhachHang.class).getResultList();

//			KhachHang khachHang = session.
			tr.commit();

			return dsKhachHang;
		} catch (Exception e) {
			// TODO: handle exception
			tr.rollback();

		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public KhachHang layThongTinKhachHangQuaSDT(String sdt) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		
		try {
			
			tr.begin();
			String sql = "select * from KhachHang inner join DiaChi on  KhachHang.maDC = DiaChi.maDC where soDienThoai ='"+sdt+"'";
			KhachHang khachHang = (KhachHang) session.createNativeQuery(sql,KhachHang.class).getSingleResult();
			
			tr.commit();
			return khachHang;
			
			
		} catch (Exception e) {
			// TODO: handle exception
			//e.printStackTrace();
			tr.rollback();
		}
		session.close();
		return null;
	}

	@Override
	public int tongSoHang(String txtSearch, String trangThaiLamViec) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		
		try {
			String ttlv = "";
			if (trangThaiLamViec.equals("1")) {
				ttlv = "1"; 
			}else if(trangThaiLamViec.equals("0")) {
				ttlv = "0"; 
			}
			tr.begin();
			String sql = "select COUNT(*) from KhachHang where tenKhachHang like N'%"+txtSearch+"%'  and gioiTinh like '%" + ttlv +"%'"; 
			String soTrang = session.createNativeQuery(sql).uniqueResult().toString();
			int n = Integer.parseInt(soTrang);
			tr.commit();
			return n;
			
		} catch (Exception e) {
			// TODO: handle exception
			//e.printStackTrace();
			tr.rollback();
		}
		return -1;
	}

	@Override
	public boolean themKhachHang(KhachHang khachHang) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();	
		try {
			tr.begin();
			session.save(khachHang);
			tr.commit();
			return true;
			
		} catch (Exception e) {
			// TODO: handle exception
			//e.printStackTrace();
			tr.rollback();
		}
		return false;
	}

	@Override
	public boolean kiemTraSoDienThoai(String sdt) throws RemoteException {
		KhachHang khachHang = layThongTinKhachHangQuaSDT(sdt);
		if(khachHang == null) {
			return true;
		}
		return false;
	}

	@Override
	public Boolean suaKhachHang(KhachHang khachHang) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();	
		try {
			tr.begin();
			session.update(khachHang);
			tr.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			//e.printStackTrace();
			tr.rollback();
		}
		return false;
	}
	@Override
	public KhachHang layThongTinKhachHangQuaId(String id) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();	
		try {
			
			tr.begin();
			KhachHang khachHang = session.find(KhachHang.class,id);
			
			tr.commit();
			return khachHang;
		} catch (Exception e) {
			// TODO: handle exception
			//e.printStackTrace();
			tr.rollback();
		}
		return null;
	}

}
