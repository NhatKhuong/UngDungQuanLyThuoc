package util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import entity.CT_HoaDon;
import entity.CongDung;
import entity.DiaChi;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.Thuoc;

public class HibernateUtil {
	private SessionFactory sessionFactory;
	private static HibernateUtil intance = null;
	
	public HibernateUtil() {
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
								.configure()
								.build();
		Metadata meta = new MetadataSources(serviceRegistry)
								.addAnnotatedClass(DiaChi.class)
								.addAnnotatedClass(CongDung.class)
								.addAnnotatedClass(CT_HoaDon.class)
								.addAnnotatedClass(HoaDon.class)
								.addAnnotatedClass(KhachHang.class)
								.addAnnotatedClass(NhanVien.class)
								.addAnnotatedClass(Thuoc.class)
								.getMetadataBuilder()
								.build();
		sessionFactory = meta.getSessionFactoryBuilder()
								.build();
	}
	
	

	public synchronized static HibernateUtil getIntance() {
		if (intance == null)
			intance = new HibernateUtil();
		return intance;
	}



	public synchronized SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
}
