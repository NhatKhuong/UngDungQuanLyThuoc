package app;

import java.rmi.registry.LocateRegistry;

import javax.naming.Context;
import javax.naming.InitialContext;

import dao.CTHoaDonDao;
import dao.DiaChiDao;
import dao.HoaDonDao;
import dao.KhachHangDao;
import dao.NhanVienDao;
import dao.ThuocDao;
import dao.impl.CTHoaDonImpl;
import dao.impl.DiaChiImpl;
import dao.impl.HoaDonImpl;
import dao.impl.KhachHangImpl;
import dao.impl.NhanVienImpl;
import dao.impl.ThuocImpl;
//import util.HibernateUtil;

public class App{
	private static String IP = "rmi://127.0.0.1:8000";

	public static void main(String[] args) {
//		SessionFactory sessionFactory = new HibernateUtil().getSessionFactory();
		SecurityManager securityManager = System.getSecurityManager();
		if (securityManager == null) {
			System.setProperty("java.security.policy", "policy/policy.policy");
			System.setSecurityManager(new SecurityManager());
		}

		try {
			KhachHangDao khachHangDao = new KhachHangImpl();
			NhanVienDao nhanVienDao = new NhanVienImpl();
			DiaChiDao diaChiDao = new DiaChiImpl();
			ThuocDao thuocDao = new ThuocImpl();
			HoaDonDao hoaDonDao = new HoaDonImpl();
			CTHoaDonDao ctHoaDonDao = new CTHoaDonImpl();

			LocateRegistry.createRegistry(8000);
			Context context = new InitialContext();
			
			context.bind(IP+"/khachHangDao", khachHangDao);
			context.bind(IP+"/nhanVienDao", nhanVienDao);
			context.bind(IP+"/diaChiDao", diaChiDao);
			context.bind(IP+"/thuocDao", thuocDao);
			context.bind(IP+"/hoaDonDao", hoaDonDao);
			context.bind(IP+"/ctHoaDonDao", ctHoaDonDao);
			System.out.println("Server bound in RMIRegistry");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
