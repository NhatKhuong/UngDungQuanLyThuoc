package dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import dao.HoaDonDao;
import entity.CT_HoaDon;
import entity.HoaDon;
import util.HibernateUtil;

public class HoaDonImpl extends UnicastRemoteObject implements HoaDonDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SessionFactory sessionFactory;

	public HoaDonImpl() throws RemoteException {
		this.sessionFactory = HibernateUtil.getIntance().getSessionFactory();
	}

	@Override
	public List<HoaDon> layDanhSachHoaDon(String maHD, String tenKH, String sdtKH, String sdtNV, String ngayLapHD,
			int cmb, int page, int limit) throws RemoteException {
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		long millis = System.currentTimeMillis();
		Date date = new java.sql.Date(millis);
		if (maHD == null)
			maHD = "";
		if (tenKH == null)
			tenKH = "";
		if (sdtKH == null)
			sdtKH = "";
		if (sdtNV == null)
			sdtNV = "";
		if (ngayLapHD == null)
			ngayLapHD = "";
		if (cmb == 0) {
			date = java.sql.Date.valueOf("0001-01-1");
		} else if (cmb == 1) {
			date = java.sql.Date.valueOf(LocalDate.now().toString());
		} else if (cmb == 2) {
			date = java.sql.Date.valueOf(LocalDate.now().getYear() + "-" + LocalDate.now().getMonthValue() + "-1");
		} else if (cmb == 3) {
			date = java.sql.Date.valueOf(LocalDate.now().getYear() + "-01-1");
		}

		if (limit <= 0) {
			limit = 20;
		}

		int offset = page * limit;// lay du lieu bat dau tu vi tri page*20
//		System.out.println(offset+" - "+ page);

		try {
			tr.begin();
			String sql = "select * from [dbo].[KhachHang] join [dbo].[HoaDonBan] on [dbo].[KhachHang].maKhachHang = [dbo].[HoaDonBan].maKhachHang\r\n"
					+ "  join [dbo].[NhanVien] on [dbo].[NhanVien].maNhanVien = [dbo].[HoaDonBan].maNhanVien where "
					+ "maHoaDonBan like '%" + maHD + "%' and tenKhachHang like N'%" + tenKH
					+ "%' and soDienThoai like N'%" + sdtKH + "%' and soDienThoaiNV like '%" + sdtNV
					+ "%' and ngayLapHDBan like '%" + ngayLapHD + "%' and ngayLapHDBan >= '" + date
					+ "' order by maHoaDonBan desc" + " OFFSET " + offset + " ROWS FETCH NEXT 20 ROWS ONLY";
			List<HoaDon> dsHoaDon = session.createNativeQuery(sql, HoaDon.class).getResultList();
			tr.commit();
			return dsHoaDon;
		} catch (Exception e) {
			// TODO: handle exception
			tr.rollback();

		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public int tongTrang(String maHD, String tenKH, String sdt_KH, String sdt_NV, String ngayLapHD, int cmb, int limit)
			throws RemoteException {

		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		tr.begin();
		long millis = System.currentTimeMillis();
		Date date = new java.sql.Date(millis);
		if (maHD == null)
			maHD = "";
		if (tenKH == null)
			tenKH = "";
		if (sdt_KH == null)
			sdt_KH = "";
		if (sdt_NV == null)
			sdt_NV = "";
		if (ngayLapHD == null)
			ngayLapHD = "";
		if (cmb == 0) {
			date = java.sql.Date.valueOf("0001-01-1");
		} else if (cmb == 1) {
			date = java.sql.Date.valueOf(LocalDate.now().toString());
		} else if (cmb == 2) {
			date = java.sql.Date.valueOf(LocalDate.now().getYear() + "-" + LocalDate.now().getMonthValue() + "-1");
		} else if (cmb == 3) {
			date = java.sql.Date.valueOf(LocalDate.now().getYear() + "-01-1");
		}
		if (limit <= 0) {
			limit = 20;
		}
		try {

			String sql = "select COUNT(*) from [dbo].[KhachHang] join [dbo].[HoaDonBan] on [dbo].[KhachHang].maKhachHang = [dbo].[HoaDonBan].maKhachHang\r\n"
					+ "  join [dbo].[NhanVien] on [dbo].[NhanVien].maNhanVien = [dbo].[HoaDonBan].maNhanVien where "
					+ "maHoaDonBan like '%" + maHD + "%' and tenKhachHang like N'%" + tenKH
					+ "%' and soDienThoai like N'%" + sdt_KH + "%' and soDienThoaiNV like '%" + sdt_NV
					+ "%' and ngayLapHDBan like '%" + ngayLapHD + "%' and ngayLapHDBan >= '" + date
					+ "' and ngayLapHDBan <= '" + LocalDate.now().toString() + "'";
			int result = Integer.parseInt(session.createNativeQuery(sql).uniqueResult().toString());

			tr.commit();
			System.err.println(sql);
			return result % limit == 0 ? result / limit : (result / limit) + 1;
		} catch (Exception e) {
			// TODO: handle exception
//			e.printStackTrace();
			System.out.println("tongHang: " + e.getMessage());
			tr.rollback();

		} finally {
			session.close();
		}
		return 0;
	}

	@Override
	public String phatSinhMaTuDong() throws RemoteException {
		String maHoaDon = null;
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();

		try {
			tr.begin();
			String sql = "select max(maHoaDonBan) from HoaDonBan";
			maHoaDon = (String) session.createNativeQuery(sql).getSingleResult();
			tr.commit();
			maHoaDon = "HD" + new MaTuDongImpl().fomatAA000000(maHoaDon.substring(2));
		} catch (Exception e) {
			// TODO: handle exception
//			e.printStackTrace();
			tr.rollback();
		} finally {
			session.close();
		}
		return maHoaDon;
	}

	@Override
	public boolean themHoaDon(HoaDon hoaDon) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			hoaDon.setMaHoaDonBan(phatSinhMaTuDong());
			session.save(hoaDon);
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
	public HoaDon getHoaDonTheoMa(String maHD) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			HoaDon hoaDon = session.find(HoaDon.class, maHD);
			tr.commit();
			return hoaDon;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tr.rollback();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<CT_HoaDon> chiTietHoaDon(String maHD) throws RemoteException {
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		if (maHD == null)
			maHD = "";
		try {
			tr.begin();
			String sql = "select * from CT_HoaDonBan where maHoaDonBan = '" + maHD + "'";
			List<CT_HoaDon> dsCTHoaDon = session.createNativeQuery(sql, CT_HoaDon.class).getResultList();
			tr.commit();
			return dsCTHoaDon;
		} catch (Exception e) {
			// TODO: handle exception
			tr.rollback();

		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<HoaDon> getHoaDonByNgay(String ngay, String maNV) throws RemoteException {
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();

		try {
			tr.begin();
			String sql = "select * from HoaDonBan where  maNhanVien like '%"+maNV+"%' and ngayLapHDBan = '" + ngay + "' order by maHoaDonBan desc";
			List<HoaDon> hd = session.createNativeQuery(sql, HoaDon.class).getResultList();
			tr.commit();
			return hd;
		} catch (Exception e) {
			tr.rollback();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<HoaDon> getHoaDonByThang(String ngay, String thang, String nam, String maNV) throws RemoteException {
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();

		try {
			tr.begin();
			String sql = "select * from HoaDonBan where   maNhanVien like '%"+maNV+"%' and  day(ngayLapHDBan) = '" + ngay + "' and MONTH(ngayLapHDBan) = '"
					+ thang + "' and YEAR(ngayLapHDBan) = '" + nam + "'";
			List<HoaDon> hd = session.createNativeQuery(sql, HoaDon.class).getResultList();
			tr.commit();
			return hd;
		} catch (Exception e) {
			tr.rollback();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<HoaDon> getHoaDonByNam(String thang, String nam ,  String maNV) throws RemoteException {
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();

		try {
			tr.begin();
			String sql = "select * from HoaDonBan where   maNhanVien like '%"+maNV+"%' and  MONTH(ngayLapHDBan) = '"
					+ thang + "' and YEAR(ngayLapHDBan) = '" + nam + "'";
			List<HoaDon> hd = session.createNativeQuery(sql, HoaDon.class).getResultList();
			tr.commit();
			return hd;
		} catch (Exception e) {
			tr.rollback();
		} finally {
			session.close();
		}
		return null;
	}

}
