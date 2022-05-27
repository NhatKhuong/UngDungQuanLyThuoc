/**
 * @author DangNhatKhuong, NguyenThanhSon,BuiBinhMinh
 * @version 1.0
 * @created 5-Nov-2021 10:00:53 PM
 */
package entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class KhachHang implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String maKhachHang;
	@Column(columnDefinition = "nvarchar(255)")
	private String tenKhachHang;
	private boolean gioiTinh;
	private String soDienThoai;
	@ManyToOne
	@JoinColumn(name = "maDC")
	private DiaChi diaChi;
	@OneToMany(mappedBy = "khachHang")
	private List<HoaDon> dsHoaDon;

	public KhachHang() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "KhachHang [maKhachHang=" + maKhachHang + ", tenKhachHang=" + tenKhachHang + ", gioiTinh=" + gioiTinh
				+ ", soDienThoai=" + soDienThoai + ", diaChi=" + diaChi + "]";
	}

	public KhachHang(String maKhachHang, String tenKhachHang, boolean gioiTinh, String soDienThoai, DiaChi diaChi) {
		super();
		this.maKhachHang = maKhachHang;
		this.tenKhachHang = tenKhachHang;
		this.gioiTinh = gioiTinh;
		this.soDienThoai = soDienThoai;
		this.diaChi = diaChi;
	}

	public String getMaKhachHang() {
		return maKhachHang;
	}

	public void setMaKhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
	}

	public String getTenKhachHang() {
		return tenKhachHang;
	}

	public void setTenKhachHang(String tenKhachHang) {
		this.tenKhachHang = tenKhachHang;
	}

	public boolean isGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public DiaChi getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(DiaChi diaChi) {
		this.diaChi = diaChi;
	}

}
