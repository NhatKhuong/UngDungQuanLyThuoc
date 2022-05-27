/**
 * @author DangNhatKhuong, NguyenThanhSon,BuiBinhMinh
 * @version 1.0
 * @created 5-Nov-2021 10:05:53 PM
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
public class NhanVien implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String maNhanVien;
	@Column(columnDefinition = "nvarchar(255)")
	private String tenNhanVien;
	private boolean gioiTinh;
	@Column(columnDefinition = "nvarchar(11)")
	private String soDienThoaiNV;
	private String passLogin;
	private boolean trangThaiLamViec;
	private String cmnd;
	private boolean loaiNV;
	@OneToMany(mappedBy = "nhanVien")
	private List<HoaDon> dsHoaDon;
	@ManyToOne
	@JoinColumn(name = "maDC")
	private DiaChi diaChi;

	public NhanVien(String maNhanVien, String tenNhanVien, boolean gioiTinh, String soDienThoaiNV, String passLogin,
			boolean trangThaiLamViec, String cmnd, boolean loaiNV, DiaChi diaChi) {
		super();
		this.maNhanVien = maNhanVien;
		this.tenNhanVien = tenNhanVien;
		this.gioiTinh = gioiTinh;
		this.soDienThoaiNV = soDienThoaiNV;
		this.passLogin = passLogin;
		this.trangThaiLamViec = trangThaiLamViec;
		this.cmnd = cmnd;
		this.loaiNV = loaiNV;
		this.diaChi = diaChi;
	}

	public NhanVien() {
		// TODO Auto-generated constructor stub
	}

	public String getMaNhanVien() {
		return maNhanVien;
	}

	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}

	public String getTenNhanVien() {
		return tenNhanVien;
	}

	public void setTenNhanVien(String tenNhanVien) {
		this.tenNhanVien = tenNhanVien;
	}

	public boolean isGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getSoDienThoaiNV() {
		return soDienThoaiNV;
	}

	public void setSoDienThoaiNV(String soDienThoaiNV) {
		this.soDienThoaiNV = soDienThoaiNV;
	}

	public String getPassLogin() {
		return passLogin;
	}

	public void setPassLogin(String passLogin) {
		this.passLogin = passLogin;
	}

	public boolean isTrangThaiLamViec() {
		return trangThaiLamViec;
	}

	public void setTrangThaiLamViec(boolean trangThaiLamViec) {
		this.trangThaiLamViec = trangThaiLamViec;
	}

	public String getCmnd() {
		return cmnd;
	}

	public void setCmnd(String cmnd) {
		this.cmnd = cmnd;
	}

	public boolean isLoaiNV() {
		return loaiNV;
	}

	public void setLoaiNV(boolean loaiNV) {
		this.loaiNV = loaiNV;
	}
	public DiaChi getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(DiaChi diaChi) {
		this.diaChi = diaChi;
	}
	
}
