/**
 * @author DangNhatKhuong, NguyenThanhSon,BuiBinhMinh
 * @version 1.0
 * @created 5-Nov-2021 10:10:53 PM
 */
package entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Thuoc implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id	
	private String maThuoc;
	@Column(columnDefinition = "nvarchar(255)")
	private String tenThuoc;
	@Column(columnDefinition = "money")
	private Double giaBan;
	@Column(columnDefinition = "nvarchar(255)")
	private String donViTinh;
	@Column(columnDefinition = "nvarchar(255)")
	private String thanhPhan;
	@Column(columnDefinition = "nvarchar(255)")
	private String quyCachDongGoi;
	@Column(columnDefinition = "nvarchar(255)")
	private String dangBaoChe;
	@Column(columnDefinition = "nvarchar(255)")
	private String hamLuong;
	@Column(name = "cTySanXuat",columnDefinition = "nvarchar(255)")
	private String congTySanXuat;
	@Column(columnDefinition = "nvarchar(255)")
	private String nuocSanXuat;
	@Column(name = "trangThaiKD")
	private boolean trangThaiKinhDoanh;
	private float thueVAT;
	@Column(name = "soDK")
	private String soDangKy;
	@ManyToOne
	@JoinColumn(name = "maCongDung")
	private CongDung congDung;
	@OneToMany(mappedBy = "thuoc")
	private List<CT_HoaDon> ct_HoaDons;
	private int soLuongBanDau;
	private Date hanSuDung;
	@Override
	public String toString() {
		return "Thuoc [maThuoc=" + maThuoc + ", tenThuoc=" + tenThuoc + ", giaBan=" + giaBan + ", donViTinh="
				+ donViTinh + ", thanhPhan=" + thanhPhan + ", quyCachDongGoi=" + quyCachDongGoi + ", dangBaoChe="
				+ dangBaoChe + ", hamLuong=" + hamLuong + ", congTySanXuat=" + congTySanXuat + ", nuocSanXuat="
				+ nuocSanXuat + ", trangThaiKinhDoanh=" + trangThaiKinhDoanh + ", thueVAT=" + thueVAT + ", soDangKy="
				+ soDangKy + ", soLuongBanDau=" + soLuongBanDau + ", hanSuDung=" + hanSuDung + "]";
	}
	public Thuoc(String maThuoc, String tenThuoc, Double giaBan, String donViTinh, String thanhPhan,
			String quyCachDongGoi, String dangBaoChe, String hamLuong, String congTySanXuat, String nuocSanXuat,
			boolean trangThaiKinhDoanh, float thueVAT, String soDangKy, CongDung congDung, int soLuongBanDau,
			Date hanSuDung) {
		super();
		this.maThuoc = maThuoc;
		this.tenThuoc = tenThuoc;
		this.giaBan = giaBan;
		this.donViTinh = donViTinh;
		this.thanhPhan = thanhPhan;
		this.quyCachDongGoi = quyCachDongGoi;
		this.dangBaoChe = dangBaoChe;
		this.hamLuong = hamLuong;
		this.congTySanXuat = congTySanXuat;
		this.nuocSanXuat = nuocSanXuat;
		this.trangThaiKinhDoanh = trangThaiKinhDoanh;
		this.thueVAT = thueVAT;
		this.soDangKy = soDangKy;
		this.congDung = congDung;
		this.soLuongBanDau = soLuongBanDau;
		this.hanSuDung = hanSuDung;
	}
	public Thuoc() {
		// TODO Auto-generated constructor stub
	}
	public String getMaThuoc() {
		return maThuoc;
	}
	public void setMaThuoc(String maThuoc) {
		this.maThuoc = maThuoc;
	}
	public String getTenThuoc() {
		return tenThuoc;
	}
	public void setTenThuoc(String tenThuoc) {
		this.tenThuoc = tenThuoc;
	}
	public Double getGiaBan() {
		return giaBan;
	}
	public void setGiaBan(Double giaBan) {
		this.giaBan = giaBan;
	}
	public String getDonViTinh() {
		return donViTinh;
	}
	public void setDonViTinh(String donViTinh) {
		this.donViTinh = donViTinh;
	}
	public String getThanhPhan() {
		return thanhPhan;
	}
	public void setThanhPhan(String thanhPhan) {
		this.thanhPhan = thanhPhan;
	}
	public String getQuyCachDongGoi() {
		return quyCachDongGoi;
	}
	public void setQuyCachDongGoi(String quyCachDongGoi) {
		this.quyCachDongGoi = quyCachDongGoi;
	}
	public String getDangBaoChe() {
		return dangBaoChe;
	}
	public void setDangBaoChe(String dangBaoChe) {
		this.dangBaoChe = dangBaoChe;
	}
	public String getHamLuong() {
		return hamLuong;
	}
	public void setHamLuong(String hamLuong) {
		this.hamLuong = hamLuong;
	}
	public String getCongTySanXuat() {
		return congTySanXuat;
	}
	public void setCongTySanXuat(String congTySanXuat) {
		this.congTySanXuat = congTySanXuat;
	}
	public String getNuocSanXuat() {
		return nuocSanXuat;
	}
	public void setNuocSanXuat(String nuocSanXuat) {
		this.nuocSanXuat = nuocSanXuat;
	}
	public boolean isTrangThaiKinhDoanh() {
		return trangThaiKinhDoanh;
	}
	public void setTrangThaiKinhDoanh(boolean trangThaiKinhDoanh) {
		this.trangThaiKinhDoanh = trangThaiKinhDoanh;
	}
	public float getThueVAT() {
		return thueVAT;
	}
	public void setThueVAT(float thueVAT) {
		this.thueVAT = thueVAT;
	}
	public String getSoDangKy() {
		return soDangKy;
	}
	public void setSoDangKy(String soDangKy) {
		this.soDangKy = soDangKy;
	}
	public CongDung getCongDung() {
		return congDung;
	}
	public void setCongDung(CongDung congDung) {
		this.congDung = congDung;
	}
	public int getSoLuongBanDau() {
		return soLuongBanDau;
	}
	public void setSoLuongBanDau(int soLuongBanDau) {
		this.soLuongBanDau = soLuongBanDau;
	}
	public Date getHanSuDung() {
		return hanSuDung;
	}
	public void setHanSuDung(Date hanSuDung) {
		this.hanSuDung = hanSuDung;
	}
}
