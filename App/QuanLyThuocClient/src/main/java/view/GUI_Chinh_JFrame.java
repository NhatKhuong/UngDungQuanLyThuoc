package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import entity.NhanVien;
import view.DangNhap_JFrame.DangNhapResponse;

public class GUI_Chinh_JFrame extends JFrame implements MouseListener, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Launch the application.
	 */

	private JTabbedPane tabbedPane;
	private view.CuaHang_Pn cuaHang_Pn;
	private view.NhanVien_Pn nhanVien_Pn;
	private view.KhachHang_Pn khachHang_Pn;
	private view.Thuoc_Pn thuoc_Pn;
	private view.HoaDon_Pn hoaDon_Pn;
	private view.ThongKe_Pn thongKe_Pn;
	private JButton btnDangXuat;
	public static NhanVien nhanVien;

	protected static String IP = "rmi://127.0.0.1:8000";

	/**
	 * Create the frame.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			GUI_Chinh_JFrame chinh_JFrame;

			class DangNhapInterface implements DangNhapResponse {

				@Override
				public void getNhanVien(NhanVien nhanVien1) {
					if (nhanVien1 != null) {
						nhanVien = nhanVien1;
						chinh_JFrame = new GUI_Chinh_JFrame();
						chinh_JFrame.setVisible(true);
					}
				}

			}

			public void run() {
				try {
					UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
					new DangNhap_JFrame(new DangNhapInterface()).setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
//		new GUI_Chinh_JFrame().setVisible(true);
	}

	public GUI_Chinh_JFrame() {
		getContentPane().setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1290, 720);
		setResizable(false);
		getContentPane().setLayout(null);
		this.setBackground(new Color(233, 245, 237));
		this.setLocationRelativeTo(null);
		JPanel pnTitleName = new JPanel();
		pnTitleName.setBackground(new Color(233, 245, 237));
		pnTitleName.setBounds(0, 0, 1284, 54);
		pnTitleName.setBackground(new Color(0, 0, 51));
		getContentPane().add(pnTitleName);
		pnTitleName.setLayout(null);

		JLabel lblTitle = new JLabel("NHÀ THUỐC AN NHIÊN");
		lblTitle.setForeground(new Color(46, 139, 87));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setVerticalAlignment(SwingConstants.CENTER);

		lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setBounds(485, 14, 301, 28);
		pnTitleName.add(lblTitle);

		JLabel lblUser = new JLabel("Nhân viên: " + nhanVien.getTenNhanVien().toUpperCase());
		lblUser.setForeground(new Color(46, 139, 87));
		lblUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblUser.setVerticalAlignment(SwingConstants.CENTER);

		lblUser.setFont(new Font("Arial", Font.ITALIC, 15));
		lblUser.setForeground(Color.WHITE);
		lblUser.setBounds(870, 14, 301, 28);
		pnTitleName.add(lblUser);

		btnDangXuat = new JButton("Đăng Xuất");
		btnDangXuat.setBounds(1150, 14, 100, 28);
		btnDangXuat.addActionListener(this);
		pnTitleName.add(btnDangXuat);

		JPanel panel = new JPanel();
		panel.setBounds(0, 56, 1284, 625);
		getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addMouseListener(this);
		panel.add(tabbedPane, BorderLayout.CENTER);

		cuaHang_Pn = new CuaHang_Pn();
		thuoc_Pn = new Thuoc_Pn();
		nhanVien_Pn = new NhanVien_Pn();
		khachHang_Pn = new KhachHang_Pn();
		hoaDon_Pn = new HoaDon_Pn();
		thongKe_Pn = new ThongKe_Pn();
		cuaHang_Pn.khoiTaoDuLieu();

		tabbedPane.addTab("Cửa hàng ", new ImageIcon(GUI_Chinh_JFrame.class.getResource("/img/storeNav.png")),
				cuaHang_Pn, "Cửa hàng");
		tabbedPane.addTab("Thuốc   ", new ImageIcon(GUI_Chinh_JFrame.class.getResource("/img/pill_nav.png")), thuoc_Pn,
				"Quản lý thuốc");
		if (nhanVien.isLoaiNV()) {
			tabbedPane.addTab("Nhân viên   ",
					new ImageIcon(GUI_Chinh_JFrame.class.getResource("/img/employee_nav.png")), nhanVien_Pn,
					"Quản lý nhân viên");
		}
		tabbedPane.addTab("Khách hàng   ", new ImageIcon(GUI_Chinh_JFrame.class.getResource("/img/patient.png")),
				khachHang_Pn, "Quản lý khách hàng");
		tabbedPane.addTab("Hóa đơn   ", new ImageIcon(GUI_Chinh_JFrame.class.getResource("/img/invoices.png")),
				hoaDon_Pn, "Quản lý hóa đơn");
		tabbedPane.addTab("Thống kê  ", new ImageIcon(GUI_Chinh_JFrame.class.getResource("/img/statistics.png")),
				thongKe_Pn, "Thống kê");

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object object = e.getSource();
		if (object.equals(btnDangXuat)) {
			int check = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn đăng xuất", "Đăng xuất",
					JOptionPane.YES_NO_OPTION);
			if (check == JOptionPane.YES_OPTION) {
				nhanVien = null;
				this.setVisible(false);
				this.dispose();
				main(null);
				return;

			} else {
				return;
			}

		}
	}

	public void mouseClicked(MouseEvent e) {
		switch (tabbedPane.getSelectedIndex()) {
		case 0: {
			cuaHang_Pn.khoiTaoDuLieu();
			break;
		}
		case 1: {
			thuoc_Pn.khoiTaoDuLieu();
			break;
		}
		case 2: {
			if (nhanVien.isLoaiNV()) {
				nhanVien_Pn.khoiTaoDuLieu();
			} else {
				khachHang_Pn.khoiTaoDuLieu();
			}
			break;
		}
		case 3: {
			if (nhanVien.isLoaiNV()) {
				khachHang_Pn.khoiTaoDuLieu();
			} else {
				hoaDon_Pn.khoiTaoDuLieu();
			}
			break;
		}
		case 4: {
	
			if (nhanVien.isLoaiNV()) {
				hoaDon_Pn.khoiTaoDuLieu();
			} else {
				thongKe_Pn.khoiTaoDuLieu();
			}
			break;
		}
		case 5: {
			thongKe_Pn.khoiTaoDuLieu();
			break;
		}
		}
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
