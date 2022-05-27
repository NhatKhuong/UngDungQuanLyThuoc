/**
 * @author DangNhatKhuong, NguyenThanhSon,BuiBinhMinh
 * @version 1.0
 * @created 5-Nov-2021 9:40:53 PM
 */
package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "CT_HoaDonBan")
@IdClass(CT_HoaDonPK.class)
public class CT_HoaDon implements  Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1631488440955952457L;

	@Id
	@ManyToOne
	@JoinColumn(name = "maHoaDonBan")
	private HoaDon hoaDon;
	@Id
	@ManyToOne
	@JoinColumn(name = "maThuoc")
	private Thuoc thuoc;
	private int soLuong;
	@Column(columnDefinition = "money")
	private double giaBan;
	private float thueVat;
	public CT_HoaDon() {
		// TODO Auto-generated constructor stub
	}
	
	public CT_HoaDon(HoaDon hoaDon, Thuoc thuoc, int soLuong, double giaBan, float thueVat) {
		super();
		this.hoaDon = hoaDon;
		this.thuoc = thuoc;
		this.soLuong = soLuong;
		this.giaBan = giaBan;
		this.thueVat = thueVat;
	}

	public HoaDon getHoaDon() {
		return hoaDon;
	}

	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}

	public Thuoc getThuoc() {
		return thuoc;
	}

	public void setThuoc(Thuoc thuoc) {
		this.thuoc = thuoc;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public float getThueVat() {
		return thueVat;
	}

	public void setThueVat(float thueVat) {
		this.thueVat = thueVat;
	}

	public void setGiaBan(double giaBan) {
		this.giaBan = giaBan;
	}

	public double getGiaBan() {
		// TODO Auto-generated method stub
	
		return giaBan;
	}
	/**
	 * 
	 * @return double
	 */
	public double thanhTien() {
		// TODO Auto-generated method stub
	
		return giaBan * soLuong;
	}

	@Override
	public String toString() {
		return "CT_HoaDon [hoaDon=" + hoaDon + ", thuoc=" + thuoc + ", soLuong=" + soLuong + ", giaBan=" + giaBan
				+ ", thueVat=" + thueVat + "]";
	}
	

}
