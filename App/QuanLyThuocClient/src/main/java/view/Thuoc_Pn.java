package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import dao.ThuocDao;
import entity.Thuoc;
import util.IP;
import util.Format;

public class Thuoc_Pn extends JPanel implements ActionListener, KeyListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JTextField txtTen;
	private static JTextField txtHoatChat;
	private static JTable table;
	private JButton btnLui1;
	private JButton btnTien1;
	private JButton btnTienCuoi;
	private JButton btnLuiCuoi;
	private static JLabel lblSoTrang;
	private JButton btnNgungBan;
	private JButton btnChiTiet;
	private JButton btnSuaChua;
	private JButton btnLamMoi;
	private static JComboBox<String> cboNuocSanXuat;
	private static JComboBox<String> cboDVT;
	private static JComboBox<String> cboDangBaoChe;
	private static JComboBox<String> cboNCongDung;
	private static JComboBox<String> cboCongDung;
	private static DefaultTableModel model;
	private static ThuocDao thuocDao;

	private static List<Thuoc> listThuoc;
	private JLabel lblTotalPage;
	private JComboBox<String> cboTrangThai;

	/**
	 * Create the panel.
	 */
	public Thuoc_Pn() {
		
		SecurityManager securityManager = System.getSecurityManager();
		if (securityManager == null) {
			System.setProperty("java.security.policy", "policy/policy.policy");
			System.setSecurityManager(new SecurityManager());
		}

		try {
			thuocDao =  (ThuocDao) Naming.lookup(IP.getIP+"thuocDao");
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}

		setBackground(Color.white);
		setLayout(null);
		setSize(1276, 570);

		JLabel lblNewLabel = new JLabel("Tên thuốc: ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(38, 11, 98, 22);
		add(lblNewLabel);

		JLabel lblHotCht = new JLabel("Hoạt chất: ");
		lblHotCht.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblHotCht.setBounds(666, 11, 98, 22);
		add(lblHotCht);

		JLabel lblNhpm = new JLabel("N.Công dụng: ");
		lblNhpm.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNhpm.setBounds(38, 44, 109, 22);
		add(lblNhpm);

		JLabel lblCngDng = new JLabel("Công dụng: ");
		lblCngDng.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCngDng.setBounds(666, 44, 98, 22);
		add(lblCngDng);

		txtTen = new JTextField();
		txtTen.setFont(new Font("Arial", Font.PLAIN, 16));
		txtTen.setBounds(175, 11, 450, 30);
	    add(txtTen);
		txtTen.setColumns(10);

		txtHoatChat = new JTextField();
		txtHoatChat.setFont(new Font("Arial", Font.PLAIN, 16));
		txtHoatChat.setColumns(10);
		txtHoatChat.setBounds(794, 11, 175, 30);
		add(txtHoatChat);

		cboNCongDung = new JComboBox<String>();
		cboNCongDung.setFont(new Font("Arial", Font.PLAIN, 16));
		cboNCongDung.setBounds(175, 47, 450, 30);
		add(cboNCongDung);

		cboCongDung = new JComboBox<String>();
		cboCongDung.setFont(new Font("Arial", Font.PLAIN, 16));
		cboCongDung.setBounds(794, 47, 450, 30);
		add(cboCongDung);

		JLabel lblDngBoCh = new JLabel("Dạng bào chế: ");
		lblDngBoCh.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDngBoCh.setBounds(38, 84, 109, 22);
		add(lblDngBoCh);

		cboDangBaoChe = new JComboBox<String>();
		cboDangBaoChe.setBounds(175, 84, 450, 30);
		add(cboDangBaoChe);

		JLabel lblCngDng_1 = new JLabel("ĐVTính: ");
		lblCngDng_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCngDng_1.setBounds(666, 84, 98, 22);
		add(lblCngDng_1);

		cboDVT = new JComboBox<String>();
		cboDVT.setBounds(794, 84, 175, 30);
		add(cboDVT);

		JLabel lblCngDng_1_1 = new JLabel("Nước SX: ");
		lblCngDng_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCngDng_1_1.setBounds(979, 88, 98, 22);
		add(lblCngDng_1_1);

		cboNuocSanXuat = new JComboBox<String>();
		cboNuocSanXuat.setBounds(1075, 84, 169, 30);
		add(cboNuocSanXuat);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(51, 51, 102));
		panel.setBounds(0, 124, 1267, 35);
		add(panel);
		panel.setLayout(null);

		btnNgungBan = new JButton("Ngừng bán");
		btnNgungBan.setIcon(new ImageIcon(Thuoc_Pn.class.getResource("/img/stopping.png")));
		btnNgungBan.setFont(new Font("Arial", Font.PLAIN, 14));
		btnNgungBan.setBounds(340, 0, 140, 35);
		panel.add(btnNgungBan);

		btnChiTiet = new JButton("Chi tiết");
		btnChiTiet.setIcon(new ImageIcon(Thuoc_Pn.class.getResource("/img/description.png")));
		btnChiTiet.setFont(new Font("Arial", Font.PLAIN, 14));
		btnChiTiet.setBounds(501, 0, 140, 35);
		panel.add(btnChiTiet);

		btnSuaChua = new JButton("Sửa");
		btnSuaChua.setIcon(new ImageIcon(Thuoc_Pn.class.getResource("/img/repair.png")));
		btnSuaChua.setFont(new Font("Arial", Font.PLAIN, 14));
		btnSuaChua.setBounds(663, 0, 140, 35);
		panel.add(btnSuaChua);

		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setIcon(new ImageIcon(Thuoc_Pn.class.getResource("/img/lam_moi.png")));
		btnLamMoi.setFont(new Font("Arial", Font.PLAIN, 14));
		btnLamMoi.setBounds(825, 0, 140, 35);
		panel.add(btnLamMoi);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 170, 1267, 362);
		add(scrollPane);
		model = new DefaultTableModel(new String[] { "Tên thuốc", "Thành phần", "Dạng bào chế", "ĐVT", "Hàm lượng",
				"Nước sản xuất", "Nhóm công dụng", "Công dụng", "Giá (Vnđ)", "Số lượng" ,"Trạng Thái kinh doanh"}, 0);
		table = new JTable(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setDefaultEditor(Object.class, null);
		scrollPane.setViewportView(table);
		scrollPane.setViewportView(table);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(0, 533, 1300, 37);
		add(panel_1);
		panel_1.setLayout(null);

		btnLuiCuoi = new JButton("");
		btnLuiCuoi.setIcon(new ImageIcon(Thuoc_Pn.class.getResource("/img/previousEnd.png")));
		btnLuiCuoi.setBounds(417, 3, 89, 30);
		panel_1.add(btnLuiCuoi);

		btnLui1 = new JButton("");
		btnLui1.setIcon(new ImageIcon(Thuoc_Pn.class.getResource("/img/rewind-button.png")));
		btnLui1.setBounds(516, 3, 89, 30);
		panel_1.add(btnLui1);

		btnTien1 = new JButton("");
		btnTien1.setIcon(new ImageIcon(Thuoc_Pn.class.getResource("/img/nextbutton.png")));
		btnTien1.setBounds(721, 3, 89, 30);
		panel_1.add(btnTien1);

		btnTienCuoi = new JButton("");
		btnTienCuoi.setIcon(new ImageIcon(Thuoc_Pn.class.getResource("/img/nextEnd.png")));
		btnTienCuoi.setBounds(831, 3, 89, 30);
		panel_1.add(btnTienCuoi);

		lblSoTrang = new JLabel("1");
		lblSoTrang.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSoTrang.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSoTrang.setBounds(615, 3, 46, 30);
		panel_1.add(lblSoTrang);
		
		lblTotalPage = new JLabel("");
		lblTotalPage.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotalPage.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTotalPage.setBounds(665, 3, 46, 30);
		panel_1.add(lblTotalPage);

		table.setRowHeight(35);
		cboDangBaoChe.setFont(new Font("Arial", Font.PLAIN, 16));


		cboNuocSanXuat.setFont(new Font("Arial", Font.PLAIN, 16));

		cboDVT.setFont(new Font("Arial", Font.PLAIN, 16));
		
		JLabel lblTrngThiKd = new JLabel("Trạng thái KD");
		lblTrngThiKd.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTrngThiKd.setBounds(979, 14, 109, 22);
		add(lblTrngThiKd);
		
		cboTrangThai = new JComboBox<String>();
		cboTrangThai.setFont(new Font("Arial", Font.PLAIN, 16));
		cboTrangThai.setBounds(1098, 11, 146, 30);
		add(cboTrangThai);
		
		try {
			cboCongDung.addItem("Tất cả");
			loadCb(cboCongDung, thuocDao.getCongDung());
			cboDVT.addItem("Tất cả");
			loadCb(cboDVT, thuocDao.getDonViTinh());
			cboDangBaoChe.addItem("Tất cả");
			loadCb(cboDangBaoChe, thuocDao.getDangBaoChe());
			cboNCongDung.addItem("Tất cả");
			loadCb(cboNCongDung, thuocDao.getNhomCongDung());
			cboNuocSanXuat.addItem("Tất cả");
			loadCb(cboNuocSanXuat, thuocDao.getNuocSanXuat());
			loadCb(cboTrangThai, Arrays.asList("Kinh doanh","Ngừng KD"));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		addInToTable();

		btnChiTiet.addActionListener(this);
		btnLui1.addActionListener(this);
		btnLuiCuoi.addActionListener(this);
		btnTien1.addActionListener(this);
		btnTienCuoi.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnSuaChua.addActionListener(this);
		btnNgungBan.addActionListener(this);

		txtTen.addKeyListener(this);
		txtHoatChat.addKeyListener(this);

		cboCongDung.addActionListener(this);
		cboDangBaoChe.addActionListener(this);
		cboDVT.addActionListener(this);
		cboNCongDung.addActionListener(this);
		cboNuocSanXuat.addActionListener(this);
		cboTrangThai.addActionListener(this);

		table.addMouseListener(this);
		
		lblTotalPage.setText("/"+tinhTongPage());
		
		
	}
	
	public static void loadCb(JComboBox<String> cb, List<String> list) {
		for (String string : list) {
			cb.addItem(string);
		}
	}

	public Thuoc dataChiTietThuoc() {
		int row = table.getSelectedRow();
		if (row == -1) {
			return null;
		}
		String maThuoc = table.getValueAt(row, 0).toString();
		Thuoc thuoc;
		try {
			thuoc = thuocDao.getThuocTheoMa(maThuoc);
			return thuoc;
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	

	}

	public void addInToTable() {
		int page = Integer.parseInt(lblSoTrang.getText());

		String ten = txtTen.getText();
		String thanhPhan = txtHoatChat.getText();
		String nhomCongDung = "";
		if (cboNCongDung.getSelectedIndex() != 0)
			nhomCongDung = cboNCongDung.getSelectedItem().toString();
		String congDung = "";
		if (cboCongDung.getSelectedIndex() != 0)
			congDung = cboCongDung.getSelectedItem().toString();
		String dangBaoChe = "";
		if (cboDangBaoChe.getSelectedIndex() != 0)
			dangBaoChe = cboDangBaoChe.getSelectedItem().toString();
		String dvt = "";
		if (cboDVT.getSelectedIndex() != 0)
			dvt = cboDVT.getSelectedItem().toString();
		String nuoc = "";
		if (cboNuocSanXuat.getSelectedIndex() != 0)
			nuoc = cboNuocSanXuat.getSelectedItem().toString();
		boolean trangThai = cboTrangThai.getSelectedIndex() == 0 ? true : false;

		try {
			listThuoc = thuocDao.danhSachThuoc(page - 1,9, ten, thanhPhan, dvt, congDung, nhomCongDung, dangBaoChe,
					nuoc,trangThai,false);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (Thuoc thuoc : listThuoc) {
			model.addRow(new Object[] { thuoc.getTenThuoc(), thuoc.getThanhPhan(), thuoc.getDangBaoChe(),
					thuoc.getDonViTinh(), thuoc.getHamLuong(), thuoc.getNuocSanXuat(),
					thuoc.getCongDung().getNhomCongDung(), thuoc.getCongDung().getCongDung(), Format.chuyenDoiTienTe(thuoc.getGiaBan()),
					thuoc.getSoLuongBanDau(),thuoc.isTrangThaiKinhDoanh()?"Đang kinh doanh":"Ngừng kinh doanh"});
		}
	}
	
	public void khoiTaoDuLieu(){
		xoaToanBoBang();
		addInToTable();
	}

	public static void xoaToanBoBang() {
		model.getDataVector().removeAllElements();
		model.fireTableDataChanged();
	}
	
	public int tinhTongPage() {
		int tongHang=0;
		try {
			

			String ten = txtTen.getText();
			String thanhPhan = txtHoatChat.getText();
			String nhomCongDung = "";
			if (cboNCongDung.getSelectedIndex() != 0)
				nhomCongDung = cboNCongDung.getSelectedItem().toString();
			String congDung = "";
			if (cboCongDung.getSelectedIndex() != 0)
				congDung = cboCongDung.getSelectedItem().toString();
			String dangBaoChe = "";
			if (cboDangBaoChe.getSelectedIndex() != 0)
				dangBaoChe = cboDangBaoChe.getSelectedItem().toString();
			String dvt = "";
			if (cboDVT.getSelectedIndex() != 0)
				dvt = cboDVT.getSelectedItem().toString();
			String nuoc = "";
			if (cboNuocSanXuat.getSelectedIndex() != 0)
				nuoc = cboNuocSanXuat.getSelectedItem().toString();
			boolean trangThai = cboTrangThai.getSelectedIndex() == 0 ? true : false;
			
			tongHang = thuocDao.tongHang(ten, thanhPhan, dvt, congDung, nhomCongDung, dangBaoChe, nuoc,trangThai,false);
		} catch (RemoteException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		int tongPage = tongHang % 9 == 0 ? tongHang / 9 : (tongHang / 9) + 1;
		return tongPage;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		int tongPage = tinhTongPage();
		if (o.equals(btnChiTiet)) {
			int row = table.getSelectedRow();
			if (row < 0) {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn thuốc cần xem chi tiết");
			} else {
				new ChiTietThuoc_JFrame(listThuoc.get(row).getMaThuoc()).setVisible(true);
			}
		}
		if (o.equals(btnTien1)) {

			int page = Integer.parseInt(lblSoTrang.getText()) + 1;
			btnNgungBan.setIcon(new ImageIcon(Thuoc_Pn.class.getResource("/img/stopping.png")));
			btnNgungBan.setText("Ngừng bán");
			if (page <= tongPage) {
				lblSoTrang.setText(Integer.toString(page));
				xoaToanBoBang();
				table.clearSelection();
				addInToTable();

			}

		}
		if (o.equals(btnLui1)) {
			int page = Integer.parseInt(lblSoTrang.getText()) - 1;
			btnNgungBan.setIcon(new ImageIcon(Thuoc_Pn.class.getResource("/img/stopping.png")));
			btnNgungBan.setText("Ngừng bán");
			if (page >= 1) {
				lblSoTrang.setText(Integer.toString(page));
				xoaToanBoBang();
				table.clearSelection();
				addInToTable();
			}
		}

		if (o.equals(btnTienCuoi)) {
			int page = Integer.parseInt(lblSoTrang.getText());
			btnNgungBan.setIcon(new ImageIcon(Thuoc_Pn.class.getResource("/img/stopping.png")));
			btnNgungBan.setText("Ngừng bán");
			if (page != tongPage) {
				lblSoTrang.setText(Integer.toString(tongPage));
				xoaToanBoBang();
				table.clearSelection();
				addInToTable();
			}
		}

		if (o.equals(btnLuiCuoi)) {
			int page = Integer.parseInt(lblSoTrang.getText());
			btnNgungBan.setIcon(new ImageIcon(Thuoc_Pn.class.getResource("/img/stopping.png")));
			btnNgungBan.setText("Ngừng bán");
			if (page != 1) {
				lblSoTrang.setText(Integer.toString(1));
				xoaToanBoBang();
				table.clearSelection();
				addInToTable();
			}
		}

		if (o.equals(btnLamMoi)) {
			btnNgungBan.setIcon(new ImageIcon(Thuoc_Pn.class.getResource("/img/stopping.png")));
			btnNgungBan.setText("Ngừng bán");
			txtTen.setText("");
			txtHoatChat.setText("");
			cboDangBaoChe.setSelectedIndex(0);
			cboNuocSanXuat.setSelectedIndex(0);
			cboDVT.setSelectedIndex(0);
			cboCongDung.setSelectedIndex(0);
			cboNCongDung.setSelectedIndex(0);

			lblSoTrang.setText("1");
			xoaToanBoBang();
			table.clearSelection();
			addInToTable();
		}
		if (o.equals(cboCongDung) || o.equals(cboDVT) || o.equals(cboDangBaoChe) || o.equals(cboNCongDung)
				|| o.equals(cboNuocSanXuat) || o.equals(cboTrangThai)) {
			lblSoTrang.setText("1");
			xoaToanBoBang();
			addInToTable();
			lblTotalPage.setText("/"+tinhTongPage());
		}
		if (o.equals(btnSuaChua)) {
			int row = table.getSelectedRow();
			if (row < 0) {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn thuốc cần sửa");
			} else {
				Thuoc thuoc = listThuoc.get(row);
				DialogSuaSuaThuoc s = new DialogSuaSuaThuoc(thuoc);
				s.setVisible(true);
				xoaToanBoBang();
				addInToTable();
				
				
			}
		}
		if (o.equals(btnNgungBan)) {
			int row = table.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn thuốc");
			} else {
				String ma = listThuoc.get(row).getMaThuoc();
				boolean tt = listThuoc.get(row).isTrangThaiKinhDoanh();
				int check;
				if (tt) {
					check = JOptionPane.showConfirmDialog(null, "Bạn có muốn ngừng bán", "Thông báo",
							JOptionPane.YES_NO_OPTION);
				} else {
					check = JOptionPane.showConfirmDialog(null, "Bạn có muốn bán lại", "Thông báo",
							JOptionPane.YES_NO_OPTION);
				}
				if (check == JOptionPane.YES_OPTION) {

					boolean t =false;
					try {
						t = thuocDao.setNgungBan(!tt, ma);
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (t) {
						JOptionPane.showMessageDialog(null, "Thành công");
						xoaToanBoBang();
						addInToTable();
						listThuoc.get(row).setTrangThaiKinhDoanh(!tt);
						
						if (!tt) {
							btnNgungBan.setIcon(new ImageIcon(Thuoc_Pn.class.getResource("/img/stopping.png")));
							btnNgungBan.setText("Ngừng bán");
							table.setValueAt("Đang Kinh doanh", row, 10);
						} else {
							btnNgungBan.setIcon(new ImageIcon(Thuoc_Pn.class.getResource("/img/money-bag.png")));
							btnNgungBan.setText("Bán lại");
							table.setValueAt("Ngừng Kinh doanh", row, 10);
						}
					} else
						JOptionPane.showMessageDialog(null, "Thất bại");
				}
			}
		}
	}
	


	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		Object o = e.getSource();
		if (o.equals(txtTen) || o.equals(txtHoatChat)) {
			lblSoTrang.setText("1");
			xoaToanBoBang();
			addInToTable();
			lblTotalPage.setText("/"+tinhTongPage());
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();

		boolean trangThai = listThuoc.get(row).isTrangThaiKinhDoanh();
		if (trangThai) {
			btnNgungBan.setIcon(new ImageIcon(Thuoc_Pn.class.getResource("/img/stopping.png")));
			btnNgungBan.setText("Ngừng bán");
		
		} else {
			btnNgungBan.setIcon(new ImageIcon(Thuoc_Pn.class.getResource("/img/money-bag.png")));
			btnNgungBan.setText("Bán lại");
			
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
