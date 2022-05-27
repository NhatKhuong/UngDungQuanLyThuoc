/**
 * @author DangNhatKhuong, NguyenThanhSon,BuiBinhMinh
 * @version 1.0
 * @created 5-Nov-2021 9:55:53 PM
 */
package entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "HoaDonBan")
public class HoaDon implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String maHoaDonBan;
	private Date ngayLapHDBan;
	@Column(columnDefinition = "money")
	private double tienNhan;
	@ManyToOne
	@JoinColumn(name = "maKhachHang")
	private KhachHang khachHang;
	@ManyToOne
	@JoinColumn(name = "maNhanVien")
	private NhanVien nhanVien;
	@OneToMany(mappedBy = "hoaDon",fetch = FetchType.EAGER ,cascade={CascadeType.ALL})
	private List<CT_HoaDon> ct_HoaDons;
	
	public HoaDon() {
		// TODO Auto-generated constructor stub
	}

	public String getMaHoaDonBan() {
		return maHoaDonBan;
	}

	public void setMaHoaDonBan(String maHoaDonBan) {
		this.maHoaDonBan = maHoaDonBan;
	}

	public Date getNgayLapHDBan() {
		return ngayLapHDBan;
	}

	public void setNgayLapHDBan(Date ngayLapHDBan) {
		this.ngayLapHDBan = ngayLapHDBan;
	}

	public double getTienNhan() {
		return tienNhan;
	}

	public void setTienNhan(double tienNhan) {
		this.tienNhan = tienNhan;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public List<CT_HoaDon> getCt_HoaDons() {
		return ct_HoaDons;
	}

	public void setCt_HoaDons(List<CT_HoaDon> ct_HoaDons) {
		this.ct_HoaDons = ct_HoaDons;
	}

	@Override
	public String toString() {
		return "HoaDon [maHoaDonBan=" + maHoaDonBan + ", ngayLapHDBan=" + ngayLapHDBan + ", tienNhan=" + tienNhan + "]";
	}
	
	/**
	 * 
	 * @return double
	 */
	public double getTongTien() {
		double sum = 0;
		for (CT_HoaDon ct_HoaDon : ct_HoaDons) {
			sum+=ct_HoaDon.thanhTien() + ct_HoaDon.thanhTien()* ct_HoaDon.getThueVat();
		}
		return sum ;
	}

	


	
	

}
