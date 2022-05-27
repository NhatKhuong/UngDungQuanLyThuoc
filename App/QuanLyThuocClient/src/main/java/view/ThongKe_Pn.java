package view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;

import dao.CTHoaDonDao;
import dao.HoaDonDao;
import dao.ThuocDao;
import entity.CT_HoaDon;
import entity.HoaDon;
import entity.Thuoc;
import util.IP;

import java.awt.BorderLayout;
import javax.swing.SwingConstants;

public class ThongKe_Pn extends JPanel implements MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2166177788734061893L;

	private CTHoaDonDao daoCTHD;
	private HoaDonDao daoHD;
	private ThuocDao daoThuoc;

	private DecimalFormat moneyFormat = new DecimalFormat("###,###,###");

	private JDateChooser date;
	private JYearChooser year_TKT;
	private JMonthChooser month_TKT;
	private JLabel doanhThu_TKT;
	private JPanel pn_TKT;
	private JLabel lbl_DoanhThu_TKN;
	private DefaultTableModel modelTKN;
	private JTabbedPane tabbedPane;
	private JLabel doanhThu_TKN;
	private JPanel pn_TKN;
	private JYearChooser year_TKN;

	private DefaultTableModel model_ThuocHetHan;

	private DefaultTableModel model_ThuocHetSL;
	private String maNhanVien = !GUI_Chinh_JFrame.nhanVien.isLoaiNV() ? GUI_Chinh_JFrame.nhanVien.getMaNhanVien():"";

	/**
	 * Create the panel.
	 */

	public ThongKe_Pn() {
		SecurityManager securityManager = System.getSecurityManager();
		if (securityManager == null) {
			System.setProperty("java.security.policy", "policy/policy.policy");
			System.setSecurityManager(new SecurityManager());
		}

		try {
			daoHD = (HoaDonDao) Naming.lookup(IP.getIP + "hoaDonDao");
			daoCTHD = (CTHoaDonDao) Naming.lookup(IP.getIP + "ctHoaDonDao");
			daoThuoc = (ThuocDao) Naming.lookup(IP.getIP + "thuocDao");
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}

		setSize(1310, 580);
		setLayout(null);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1300, 570);
		add(tabbedPane);

		// thong ke theo ngay
		JPanel tab1 = new JPanel();
		tab1.setLayout(null);
		tabbedPane.addTab("Theo ngày", null, tab1, null);

		JPanel pn_Title1 = new JPanel();
		pn_Title1.setBounds(0, 0, 1295, 50);
		tab1.add(pn_Title1);
		pn_Title1.setLayout(null);

		JLabel lblNewLabel1 = new JLabel("Thống kê doanh thu theo ngày");
		lblNewLabel1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel1.setBounds(10, 10, 210, 30);
		pn_Title1.add(lblNewLabel1);

		date = new JDateChooser();
		date.setFont(new Font("Tahoma", Font.BOLD, 13));
		date.setBounds(230, 10, 150, 30);
		date.setDate(new Date());
		pn_Title1.add(date);

		lbl_DoanhThu_TKN = new JLabel("");
		lbl_DoanhThu_TKN.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_DoanhThu_TKN.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbl_DoanhThu_TKN.setBounds(498, 10, 500, 30);
		pn_Title1.add(lbl_DoanhThu_TKN);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 50, 1255, 470);
		tab1.add(scrollPane);

		JTable tbl_TKN = new JTable();
		tbl_TKN.setRowHeight(35);
		tbl_TKN.setEnabled(false);
		tbl_TKN.setColumnSelectionAllowed(true);
		tbl_TKN.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "M\u00E3 h\u00F3a \u0111\u01A1n", "M\u00E3 thu\u1ED1c", "T\u00EAn thu\u1ED1c",
						"S\u1ED1 l\u01B0\u1EE3ng b\u00E1n", "Gi\u00E1", "Th\u00E0nh ti\u1EC1n" }));
		modelTKN = (DefaultTableModel) tbl_TKN.getModel();
		if (tbl_TKN.getColumnModel().getColumnCount() > 0) {
			tbl_TKN.getColumnModel().getColumn(0).setResizable(false);
			tbl_TKN.getColumnModel().getColumn(0).setPreferredWidth(40);
			tbl_TKN.getColumnModel().getColumn(1).setResizable(false);
			tbl_TKN.getColumnModel().getColumn(1).setPreferredWidth(40);
			tbl_TKN.getColumnModel().getColumn(2).setResizable(false);
			tbl_TKN.getColumnModel().getColumn(2).setPreferredWidth(300);
			tbl_TKN.getColumnModel().getColumn(3).setResizable(false);
			tbl_TKN.getColumnModel().getColumn(3).setPreferredWidth(20);
			tbl_TKN.getColumnModel().getColumn(4).setResizable(false);
			tbl_TKN.getColumnModel().getColumn(4).setPreferredWidth(80);
			tbl_TKN.getColumnModel().getColumn(5).setResizable(false);
			tbl_TKN.getColumnModel().getColumn(5).setPreferredWidth(80);
		}
		tbl_TKN.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrollPane.setViewportView(tbl_TKN);

		// Theo thang
		JPanel tab2 = new JPanel();
		tab2.setLayout(null);
		tabbedPane.addTab("Theo tháng", null, tab2, null);

		JPanel pn_Title2 = new JPanel();
		pn_Title2.setLayout(null);
		pn_Title2.setBounds(0, 0, 1295, 50);
		tab2.add(pn_Title2);

		JLabel lblNewLabel2 = new JLabel("Thống kê doanh thu theo tháng");
		lblNewLabel2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel2.setBounds(10, 10, 210, 30);
		pn_Title2.add(lblNewLabel2);

		month_TKT = new JMonthChooser();
		month_TKT.setFont(new Font("Tahoma", Font.BOLD, 13));
		month_TKT.setBounds(230, 10, 120, 30);
		pn_Title2.add(month_TKT);

		year_TKT = new JYearChooser();
		year_TKT.setBounds(360, 10, 60, 30);
		year_TKT.setFont(new Font("Tahoma", Font.BOLD, 13));
		pn_Title2.add(year_TKT);

//		doanhThu_TKT = new JLabel("Tổng doanh thu trong tháng: 0 (VND)");
		doanhThu_TKT = new JLabel("");
		doanhThu_TKT.setHorizontalAlignment(SwingConstants.CENTER);
		doanhThu_TKT.setFont(new Font("Tahoma", Font.BOLD, 13));
		doanhThu_TKT.setBounds(525, 10, 500, 30);
		pn_Title2.add(doanhThu_TKT);

		pn_TKT = new JPanel();
		pn_TKT.setBounds(0, 50, 1275, 491);
		pn_TKT.setLayout(new BorderLayout(0, 0));
		tab2.add(pn_TKT);

		JPanel tab3 = new JPanel();
		tab3.setLayout(null);
		tabbedPane.addTab("Theo năm", null, tab3, null);

		JPanel pn_title3 = new JPanel();
		pn_title3.setBounds(0, 0, 1295, 50);
		tab3.add(pn_title3);
		pn_title3.setLayout(null);

		JLabel lblNewLabel = new JLabel("Thống kê doanh thu theo năm");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(10, 10, 210, 30);
		pn_title3.add(lblNewLabel);

		year_TKN = new JYearChooser();
		year_TKN.setBounds(250, 10, 60, 30);
		pn_title3.add(year_TKN);

		doanhThu_TKN = new JLabel("");
		doanhThu_TKN.setHorizontalAlignment(SwingConstants.CENTER);
		doanhThu_TKN.setFont(new Font("Tahoma", Font.BOLD, 13));
		doanhThu_TKN.setBounds(525, 10, 436, 30);
		pn_title3.add(doanhThu_TKN);

		pn_TKN = new JPanel();
		pn_TKN.setBounds(0, 50, 1295, 492);
		tab3.add(pn_TKN);
		pn_TKN.setLayout(new BorderLayout(0, 0));

		JPanel tab4 = new JPanel();
		tab4.setLayout(null);
		tabbedPane.addTab("Thuốc sắp hết hạn", null, tab4, null);

		JPanel pn_title4 = new JPanel();
		pn_title4.setBounds(0, 0, 1295, 50);
		tab4.add(pn_title4);
		pn_title4.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_1 = new JLabel("DANH SÁCH THUỐC SẮP HẾT HẠN (HSD < 90 ngày)");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		pn_title4.add(lblNewLabel_1, BorderLayout.CENTER);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 50, 1255, 492);
		tab4.add(scrollPane_1);

		JTable tbl_ThuocHetHan = new JTable();
		tbl_ThuocHetHan.setRowHeight(35);
		tbl_ThuocHetHan.setEnabled(false);
		tbl_ThuocHetHan.setColumnSelectionAllowed(true);
		tbl_ThuocHetHan.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "M\u00E3 thu\u00F4c", "T\u00EAn thu\u1ED1c", "C\u00F4ng ty SX", "Th\u00E0nh ph\u1EA7n",
						"H\u00E0m l\u01B0\u1EE3ng", "S\u1ED1 l\u01B0\u1EE3ng", "Ng\u00E0y h\u1EBFt h\u1EA1n" }));
		tbl_ThuocHetHan.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrollPane_1.setViewportView(tbl_ThuocHetHan);
		model_ThuocHetHan = (DefaultTableModel) tbl_ThuocHetHan.getModel();
		if (tbl_ThuocHetHan.getColumnModel().getColumnCount() > 0) {
			tbl_ThuocHetHan.getColumnModel().getColumn(0).setResizable(false);
			tbl_ThuocHetHan.getColumnModel().getColumn(0).setPreferredWidth(40);
			tbl_ThuocHetHan.getColumnModel().getColumn(1).setResizable(false);
			tbl_ThuocHetHan.getColumnModel().getColumn(1).setPreferredWidth(200);
			tbl_ThuocHetHan.getColumnModel().getColumn(2).setResizable(false);
			tbl_ThuocHetHan.getColumnModel().getColumn(2).setPreferredWidth(150);
			tbl_ThuocHetHan.getColumnModel().getColumn(3).setResizable(false);
			tbl_ThuocHetHan.getColumnModel().getColumn(3).setPreferredWidth(150);
			tbl_ThuocHetHan.getColumnModel().getColumn(4).setResizable(false);
			tbl_ThuocHetHan.getColumnModel().getColumn(4).setPreferredWidth(40);
			tbl_ThuocHetHan.getColumnModel().getColumn(5).setResizable(false);
			tbl_ThuocHetHan.getColumnModel().getColumn(5).setPreferredWidth(40);
			tbl_ThuocHetHan.getColumnModel().getColumn(6).setResizable(false);
			tbl_ThuocHetHan.getColumnModel().getColumn(6).setPreferredWidth(60);
		}

		JPanel tab5 = new JPanel();
		tab5.setLayout(null);
		tabbedPane.addTab("Thuốc sắp hết số lượng", null, tab5, null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1295, 50);
		tab5.add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_2 = new JLabel("THUỐC SẮP HẾT SỐ LƯỢNG (SL < 10 đơn vị)");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_2, BorderLayout.CENTER);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 50, 1255, 492);
		tab5.add(scrollPane_2);

		JTable table = new JTable();
		table.setRowHeight(35);
		table.setColumnSelectionAllowed(true);
		table.setEnabled(false);
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "M\u00E3 thu\u00F4c", "T\u00EAn thu\u1ED1c", "C\u00F4ng ty SX", "Th\u00E0nh ph\u1EA7n",
						"H\u00E0m l\u01B0\u1EE3ng", "S\u1ED1 l\u01B0\u1EE3ng", "Ng\u00E0y h\u1EBFt h\u1EA1n" }));
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		model_ThuocHetSL = (DefaultTableModel) table.getModel();
		if (table.getColumnModel().getColumnCount() > 0) {
			table.getColumnModel().getColumn(0).setResizable(false);
			table.getColumnModel().getColumn(0).setPreferredWidth(40);
			table.getColumnModel().getColumn(1).setResizable(false);
			table.getColumnModel().getColumn(1).setPreferredWidth(200);
			table.getColumnModel().getColumn(2).setResizable(false);
			table.getColumnModel().getColumn(2).setPreferredWidth(150);
			table.getColumnModel().getColumn(3).setResizable(false);
			table.getColumnModel().getColumn(3).setPreferredWidth(150);
			table.getColumnModel().getColumn(4).setResizable(false);
			table.getColumnModel().getColumn(4).setPreferredWidth(40);
			table.getColumnModel().getColumn(5).setResizable(false);
			table.getColumnModel().getColumn(5).setPreferredWidth(40);
			table.getColumnModel().getColumn(6).setResizable(false);
			table.getColumnModel().getColumn(6).setPreferredWidth(60);
		}
		scrollPane_2.setViewportView(table);

		tabbedPane.addMouseListener(this);

		// chon ngay thong ke
		date.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				// TODO Auto-generated method stub
				if ("date".equals(evt.getPropertyName())) {
					if (date.getDate().after(new Date())) {
						date.setDate(new Date());
						JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày <= ngày hiện tại");
					} else {
						try {
							thongKeNgay(date.getDate());
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		});

		// Chon thang thong ke
		month_TKT.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				// TODO Auto-generated method stub
				if ("month".equals(evt.getPropertyName())) {
					SimpleDateFormat fmtY = new SimpleDateFormat("yyyy");
					SimpleDateFormat fmtM = new SimpleDateFormat("MM");
					Date d = new Date();
					int m = Integer.valueOf(fmtM.format(d));
					int y = Integer.valueOf(fmtY.format(d));

					if (year_TKT.getYear() == y && month_TKT.getMonth() + 1 > m) {
						JOptionPane.showMessageDialog(null, "Vui lòng chọn tháng <= tháng hiện tại");
					} else {
						try {
							thongKeThang(String.valueOf(month_TKT.getMonth() + 1), String.valueOf(year_TKT.getYear()));
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		});

		// chon nam thong ke
		year_TKT.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				// TODO Auto-generated method stub
				if ("year".equals(evt.getPropertyName())) {
					SimpleDateFormat fmtY = new SimpleDateFormat("yyyy");
					SimpleDateFormat fmtM = new SimpleDateFormat("MM");
					Date d = new Date();
					int m = Integer.valueOf(fmtM.format(d));
					int y = Integer.valueOf(fmtY.format(d));

					if (year_TKT.getYear() > y) {
						JOptionPane.showMessageDialog(null, "Vui lòng chọn năm <= năm hiện tại");
					} else if (year_TKT.getYear() == y && month_TKT.getMonth() + 1 > m) {
						JOptionPane.showMessageDialog(null, "Vui lòng chọn tháng <= tháng hiện tại");
					} else {
						try {
							thongKeThang(String.valueOf(month_TKT.getMonth() + 1), String.valueOf(year_TKT.getYear()));
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		});

		year_TKN.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				// TODO Auto-generated method stub
				if ("year".equals(evt.getPropertyName())) {
					SimpleDateFormat fmtY = new SimpleDateFormat("yyyy");
					Date d = new Date();
					int y = Integer.valueOf(fmtY.format(d));

					if (year_TKN.getYear() > y) {
						JOptionPane.showMessageDialog(null, "Vui lòng chọn năm <= năm hiện tại");
					} else {
						try {
							thongKeNam(String.valueOf(year_TKN.getYear()));
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		});
		date.setDate(new Date());
	}

//	============================================================

	public void khoiTaoDuLieu() {
		tabbedPane.setSelectedIndex(0);
		date.setDate(new Date());
	}

	private void thuocSapHetSL() throws RemoteException {
		model_ThuocHetSL.getDataVector().removeAllElements();
		List<Thuoc> list = daoThuoc.getThuocSapHetSL();

		if (list.size() == 0) {
			JOptionPane.showMessageDialog(null, "Không có thuốc sắp hết số lượng");
			return;
		}
		for (Thuoc t : list) {
			model_ThuocHetSL.addRow(new Object[] { t.getMaThuoc(), t.getTenThuoc(), t.getCongTySanXuat(),
					t.getThanhPhan(), t.getHamLuong(), t.getSoLuongBanDau(), t.getHanSuDung() });
		}
	}

	private void thuocSapHetHan() throws RemoteException {
		model_ThuocHetHan.getDataVector().removeAllElements();
		List<Thuoc> list = daoThuoc.getThuocSapHetHan();

		if (list.size() == 0) {
			JOptionPane.showMessageDialog(null, "Không có thuốc sắp hết hạn");
			return;
		}
		for (Thuoc t : list) {
			model_ThuocHetHan.addRow(new Object[] { t.getMaThuoc(), t.getTenThuoc(), t.getCongTySanXuat(),
					t.getThanhPhan(), t.getHamLuong(), t.getSoLuongBanDau(), t.getHanSuDung() });
		}
	}

	private void thongKeNam(String nam) throws RemoteException {
		pn_TKN.repaint();
		pn_TKN.removeAll();
		ChartPanel chart_TKN = new ChartPanel(createChartNam(nam));
		pn_TKN.add(chart_TKN);
	}

	private JFreeChart createChartNam(String nam) throws RemoteException {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		long sum = 0;
		for (int i = 1; i < 13; i++) {
			String thang = String.valueOf(i);
			long tt = 0;
			List<HoaDon> list = daoHD.getHoaDonByNam(thang, nam,maNhanVien);
			for (HoaDon hd : list) {
				tt += hd.getTongTien();
			}
			if (tt > 0) {
				dataset.addValue(tt, "Tổng tiền", thang);
			}
			sum += tt;
			dataset.addValue(tt, "Tổng tiền", thang);
		}
		if (sum > 0) {
			doanhThu_TKN.setText("Tổng doanh thu trong năm: " + moneyFormat.format(sum) + " VND");
			JFreeChart barChart = ChartFactory.createBarChart("BIỂU ĐỒ DOANH THU TRONG NĂM " + nam, "Tháng",
					"Tổng tiền (VND)", dataset, PlotOrientation.VERTICAL, false, false, false);
			return barChart;
		} else {
			JOptionPane.showMessageDialog(null, "Không có hóa đơn nào trong năm để thông kê");
			doanhThu_TKN.setText("");
			return null;
		}
	}

	private void thongKeThang(String thang, String nam) throws RemoteException {
		pn_TKT.repaint();
		pn_TKT.removeAll();
		ChartPanel chart_TKT = new ChartPanel(createChartThang(thang, nam ));
		pn_TKT.add(chart_TKT);
	}

	private JFreeChart createChartThang(String thang, String nam) throws RemoteException {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		long sum = 0;
		for (int i = 1; i < 32; i++) {
			String ngay = String.valueOf(i);
			long tt = 0;
			List<HoaDon> list = daoHD.getHoaDonByThang(ngay, thang, nam,maNhanVien);
			for (HoaDon hd : list) {
				tt += hd.getTongTien();
			}
			if (tt > 0) {
				dataset.addValue(tt, "Tổng tiền", ngay);
			}
			sum += tt;
			dataset.addValue(tt, "Tổng tiền", ngay);
		}
		if (sum > 0) {
			doanhThu_TKT.setText("Tổng doanh thu trong tháng: " + moneyFormat.format(sum) + " VND");
			JFreeChart barChart = ChartFactory.createBarChart("BIỂU ĐỒ DOANH THU TRONG THÁNG " + thang, "Ngày",
					"Tổng tiền (VND)", dataset, PlotOrientation.VERTICAL, false, false, false);
			return barChart;
		} else {
			doanhThu_TKT.setText("");
			JOptionPane.showMessageDialog(null, "Không có hóa đơn nào trong tháng để thông kê");
			return null;
		}
	}

	private void thongKeNgay(Date ngay) throws RemoteException {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd");
		String ngayStr = fmt.format(ngay);
		List<HoaDon> listHD = daoHD.getHoaDonByNgay(ngayStr,maNhanVien);

		modelTKN.getDataVector().removeAllElements();
		modelTKN.fireTableDataChanged();

		if (listHD.size() == 0) {
			lbl_DoanhThu_TKN.setText("");
			JOptionPane.showMessageDialog(null, "Không có hóa đơn nào trong ngày để thông kê");
			return;
		}

		List<CT_HoaDon> listCTHD = new ArrayList<CT_HoaDon>();
		long tt = 0;
		for (HoaDon hd : listHD) {
			tt += hd.getTongTien();
			listCTHD.addAll(daoCTHD.chiTietHoaDon(hd.getMaHoaDonBan()));
		}

		lbl_DoanhThu_TKN.setText("Tổng doanh thu trong ngày: " + moneyFormat.format(tt) + " VND");

		for (CT_HoaDon t : listCTHD) {
			modelTKN.addRow(new Object[] { t.getHoaDon().getMaHoaDonBan(), t.getThuoc().getMaThuoc(),
					t.getThuoc().getTenThuoc(), t.getSoLuong(), moneyFormat.format(Math.round(t.getGiaBan())) + " VND",
					moneyFormat.format(Math.round(t.getSoLuong() * t.getGiaBan() * (1 + t.getThueVat()))) + " VND" });
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		SimpleDateFormat fmtY = new SimpleDateFormat("yyyy");
		SimpleDateFormat fmtM = new SimpleDateFormat("MM");
		Date d = new Date();
		String m = fmtM.format(d);
		String y = fmtY.format(d);

		Object o = e.getSource();

		if (o.equals(tabbedPane)) {
			switch (tabbedPane.getSelectedIndex()) {
			case 0:
				date.setDate(new Date());
				break;

			case 1:
				try {
					thongKeThang(m, y);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case 2:
				try {
					thongKeNam(y);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case 3:
				try {
					thuocSapHetHan();
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case 4:
				try {
					thuocSapHetSL();
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			default:
				break;
			}
		}
		;

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
