/**
 * @author DangNhatKhuong, NguyenThanhSon,BuiBinhMinh
 * @version 1.0
 * @created 5-Nov-2021 9:50:53 PM
 */
package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DiaChi implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String maDC;
	@Column(columnDefinition = "nvarchar(255)")
	private String tinhTp;
	@Column(columnDefinition = "nvarchar(255)")
	private String quanHuyen;
	@Column(columnDefinition = "nvarchar(255)")
	private String phuongXa;
		
	
	public DiaChi() {
		// TODO Auto-generated constructor stub
	}
	public DiaChi(String maDC) {
		// TODO Auto-generated constructor stub
		this.maDC = maDC;
	}

	
	@Override
	public String toString() {
		return "DiaChi [maDC=" + maDC + ", tinhTp=" + tinhTp + ", quanHuyen=" + quanHuyen + ", phuongXa=" + phuongXa
				+ "]";
	}


	public DiaChi(String maDC, String tinhTp, String quanHuyen, String phuongXa) {
	super();
	this.maDC = maDC;
	this.tinhTp = tinhTp;
	this.quanHuyen = quanHuyen;
	this.phuongXa = phuongXa;
}

	public String getMaDC() {
		return maDC;
	}

	public void setMaDC(String maDC) {
		this.maDC = maDC;
	}

	public String getTinhTp() {
		return tinhTp;
	}

	public void setTinhTp(String tinhTp) {
		this.tinhTp = tinhTp;
	}

	public String getQuanHuyen() {
		return quanHuyen;
	}

	public void setQuanHuyen(String quanHuyen) {
		this.quanHuyen = quanHuyen;
	}


	public String getPhuongXa() {
		return phuongXa;
	}

	public void setPhuongXa(String phuongXa) {
		this.phuongXa = phuongXa;
	}
	
}