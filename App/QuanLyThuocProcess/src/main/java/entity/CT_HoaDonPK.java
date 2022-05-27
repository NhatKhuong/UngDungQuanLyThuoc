/**
 * @author DangNhatKhuong, NguyenThanhSon,BuiBinhMinh
 * @version 1.0
 * @created 5-Nov-2021 9:45:53 PM
 */
package entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class CT_HoaDonPK implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String thuoc;
	private String hoaDon;
	
	public CT_HoaDonPK() {
		// TODO Auto-generated constructor stub
	}
	
	public String getThuoc() {
		return thuoc;
	}
	public void setThuoc(String thuoc) {
		this.thuoc = thuoc;
	}
	public String getHoaDon() {
		return hoaDon;
	}
	public void setHoaDon(String hoaDon) {
		this.hoaDon = hoaDon;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hoaDon == null) ? 0 : hoaDon.hashCode());
		result = prime * result + ((thuoc == null) ? 0 : thuoc.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CT_HoaDonPK other = (CT_HoaDonPK) obj;
		if (hoaDon == null) {
			if (other.hoaDon != null)
				return false;
		} else if (!hoaDon.equals(other.hoaDon))
			return false;
		if (thuoc == null) {
			if (other.thuoc != null)
				return false;
		} else if (!thuoc.equals(other.thuoc))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "CT_HoaDonPK [thuoc=" + thuoc + ", hoaDon=" + hoaDon + "]";
	}
	
	

}
