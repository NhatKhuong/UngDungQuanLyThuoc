package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import dao.ThuocDao;
import entity.Thuoc;
import util.IP;


public class DialogSuaSuaThuoc extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtTenThuoc;
	private JTextField txtGiaBan;
	private JTextField txtVAT;
	private JTextField txtSoDangKy;
	private JTextField txtHoatChat;
	private JTextField txtHamLuong;
	private JTextField txtHangSX;
	private JButton btnHuy;
	private JButton btnLu;
	private JComboBox<String> cboDVT;
	private JLabel lblTenthuoc;
	private JLabel lblGiaBan;
	private JComboBox<String> cboDBC;
	private JComboBox<String> cboNuocSX;
	private JTextField txtQCDG;
	private Thuoc thuoc;
	private ThuocDao thuocDao;
	private JComboBox<String> cboTrangThai;
	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	public DialogSuaSuaThuoc(Thuoc thuoc) {
		setModal(true);
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
		this.thuoc = thuoc;
		setModalExclusionType(getModalExclusionType());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		setBounds(100, 100, 1185, 614);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel jLabel1 = new JLabel();
		jLabel1.setBackground(new Color(51, 51, 102));
		jLabel1.setOpaque(true);
		jLabel1.setText("Sửa thông tin thuốc");
		jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel1.setForeground(Color.WHITE);
		jLabel1.setFont(new Font("Arial", Font.BOLD, 25));
		jLabel1.setBounds(0, 0, 1184, 45);
		contentPane.add(jLabel1);

		lblTenthuoc = new JLabel();
		lblTenthuoc.setText("Tên thuốc");
		lblTenthuoc.setPreferredSize(new Dimension(101, 17));
		lblTenthuoc.setMinimumSize(new Dimension(101, 17));
		lblTenthuoc.setMaximumSize(new Dimension(101, 17));
		lblTenthuoc.setFont(new Font("Arial", Font.BOLD, 14));
		lblTenthuoc.setBounds(23, 74, 101, 30);
		contentPane.add(lblTenthuoc);

		txtTenThuoc = new JTextField();
		txtTenThuoc.setFont(new Font("Arial", Font.PLAIN, 14));
		txtTenThuoc.setBounds(134, 74, 440, 30);
		contentPane.add(txtTenThuoc);

		lblGiaBan = new JLabel();
		lblGiaBan.setText("Giá bán");
		lblGiaBan.setPreferredSize(new Dimension(101, 17));
		lblGiaBan.setMinimumSize(new Dimension(101, 17));
		lblGiaBan.setMaximumSize(new Dimension(101, 17));
		lblGiaBan.setFont(new Font("Arial", Font.BOLD, 14));
		lblGiaBan.setBounds(23, 129, 101, 30);
		contentPane.add(lblGiaBan);

		txtGiaBan = new JTextField();
		txtGiaBan.setFont(new Font("Arial", Font.PLAIN, 14));
		txtGiaBan.setBounds(134, 129, 440, 30);
		contentPane.add(txtGiaBan);

		JLabel lblQCDG7 = new JLabel();
		lblQCDG7.setText("VAT(%)");
		lblQCDG7.setFont(new Font("Arial", Font.BOLD, 14));
		lblQCDG7.setBounds(611, 129, 80, 30);
		contentPane.add(lblQCDG7);

		txtVAT = new JTextField();
		txtVAT.setFont(new Font("Arial", Font.PLAIN, 14));
		txtVAT.setBounds(716, 130, 440, 30);
		contentPane.add(txtVAT);

		JLabel lblQCDG12 = new JLabel();
		lblQCDG12.setText("ĐVT");
		lblQCDG12.setFont(new Font("Arial", Font.BOLD, 14));
		lblQCDG12.setBounds(611, 185, 79, 30);
		contentPane.add(lblQCDG12);

		cboDVT = new JComboBox<String>();
		cboDVT.setFont(new Font("Arial", Font.PLAIN, 14));
		cboDVT.setBackground(new Color(153, 153, 153));
		cboDVT.setBounds(716, 185, 440, 30);
		contentPane.add(cboDVT);

		JLabel lblQCDG2 = new JLabel();
		lblQCDG2.setText("Dạng bào chế");
		lblQCDG2.setFont(new Font("Arial", Font.BOLD, 14));
		lblQCDG2.setBounds(23, 185, 100, 30);
		contentPane.add(lblQCDG2);

		JLabel lblMaThuoc = new JLabel();
		lblMaThuoc.setText("Số đăng ký");
		lblMaThuoc.setPreferredSize(new Dimension(101, 17));
		lblMaThuoc.setMinimumSize(new Dimension(101, 17));
		lblMaThuoc.setMaximumSize(new Dimension(101, 17));
		lblMaThuoc.setFont(new Font("Arial", Font.BOLD, 14));
		lblMaThuoc.setBounds(23, 245, 107, 30);
		contentPane.add(lblMaThuoc);

		txtSoDangKy = new JTextField();
		txtSoDangKy.setFont(new Font("Arial", Font.PLAIN, 14));
		txtSoDangKy.setFocusable(false);
		txtSoDangKy.setEditable(false);
		txtSoDangKy.setBackground(new Color(245, 245, 245));
		txtSoDangKy.setBounds(134, 246, 1022, 30);
		contentPane.add(txtSoDangKy);

		JLabel lblTenHC = new JLabel();
		lblTenHC.setText("Hoạt chất");
		lblTenHC.setPreferredSize(new Dimension(101, 17));
		lblTenHC.setMinimumSize(new Dimension(101, 17));
		lblTenHC.setMaximumSize(new Dimension(101, 17));
		lblTenHC.setFont(new Font("Arial", Font.BOLD, 14));
		lblTenHC.setBounds(611, 300, 81, 30);
		contentPane.add(lblTenHC);

		txtHoatChat = new JTextField();
		txtHoatChat.setFont(new Font("Arial", Font.PLAIN, 14));
		txtHoatChat.setBounds(716, 301, 440, 30);
		contentPane.add(txtHoatChat);

		JLabel lblQCDG6 = new JLabel();
		lblQCDG6.setText("Hàm lượng");
		lblQCDG6.setPreferredSize(new Dimension(101, 17));
		lblQCDG6.setMinimumSize(new Dimension(101, 17));
		lblQCDG6.setMaximumSize(new Dimension(101, 17));
		lblQCDG6.setFont(new Font("Arial", Font.BOLD, 14));
		lblQCDG6.setBounds(23, 301, 107, 30);
		contentPane.add(lblQCDG6);

		txtHamLuong = new JTextField();
		txtHamLuong.setFont(new Font("Arial", Font.PLAIN, 14));
		txtHamLuong.setBounds(134, 301, 440, 30);
		contentPane.add(txtHamLuong);

		JLabel lblQCDG = new JLabel();
		lblQCDG.setText("QC đóng gói");
		lblQCDG.setPreferredSize(new Dimension(101, 17));
		lblQCDG.setMinimumSize(new Dimension(101, 17));
		lblQCDG.setMaximumSize(new Dimension(101, 17));
		lblQCDG.setFont(new Font("Arial", Font.BOLD, 14));
		lblQCDG.setBounds(23, 355, 107, 30);
		contentPane.add(lblQCDG);

		JLabel lblQCDG4 = new JLabel();
		lblQCDG4.setText("Hãng sản xuất");
		lblQCDG4.setPreferredSize(new Dimension(101, 17));
		lblQCDG4.setMinimumSize(new Dimension(101, 17));
		lblQCDG4.setMaximumSize(new Dimension(101, 17));
		lblQCDG4.setFont(new Font("Arial", Font.BOLD, 14));
		lblQCDG4.setBounds(23, 416, 107, 30);
		contentPane.add(lblQCDG4);

		txtHangSX = new JTextField();
		txtHangSX.setFont(new Font("Arial", Font.PLAIN, 14));
		txtHangSX.setBounds(134, 416, 440, 30);
		contentPane.add(txtHangSX);

		JLabel lblQCDG5 = new JLabel();
		lblQCDG5.setText("Nước SX");
		lblQCDG5.setPreferredSize(new Dimension(101, 17));
		lblQCDG5.setMinimumSize(new Dimension(101, 17));
		lblQCDG5.setMaximumSize(new Dimension(101, 17));
		lblQCDG5.setFont(new Font("Arial", Font.BOLD, 14));
		lblQCDG5.setBounds(611, 416, 107, 30);
		contentPane.add(lblQCDG5);

		btnHuy = new JButton();
		btnHuy.setIcon(new ImageIcon(DialogSuaSuaThuoc.class.getResource("/img/stopping.png")));
		btnHuy.setToolTipText("Hủy thêm nhân viên");
		btnHuy.setText("Hủy");
		btnHuy.setFont(new Font("Arial", Font.BOLD, 16));
		btnHuy.setBackground(new Color(204, 204, 204));
		btnHuy.setBounds(23, 503, 131, 41);
		contentPane.add(btnHuy);

		btnLu = new JButton();
		btnLu.setIcon(new ImageIcon(DialogSuaSuaThuoc.class.getResource("/img/cap_nhat.png")));
		btnLu.setToolTipText("Thêm nhân viên");
		btnLu.setText("Lưu");
		btnLu.setFont(new Font("Arial", Font.BOLD, 16));
		btnLu.setBackground(new Color(204, 204, 204));
		btnLu.setBounds(1016, 503, 140, 41);
		contentPane.add(btnLu);
		
		JLabel lblTrngThiKd = new JLabel();
		lblTrngThiKd.setText("Trạng thái KD");
		lblTrngThiKd.setFont(new Font("Arial", Font.BOLD, 14));
		lblTrngThiKd.setBounds(611, 74, 99, 30);
		contentPane.add(lblTrngThiKd);
		
		cboTrangThai = new JComboBox<String>();
		cboTrangThai.setFont(new Font("Arial", Font.PLAIN, 14));
		cboTrangThai.setBackground(new Color(153, 153, 153));
		cboTrangThai.setBounds(716, 74, 440, 30);
		contentPane.add(cboTrangThai);

		txtTenThuoc.setText(thuoc.getTenThuoc());
		txtGiaBan.setText(thuoc.getGiaBan().toString());
		txtHamLuong.setText(thuoc.getHamLuong());
		txtHangSX.setText(thuoc.getCongTySanXuat());
		txtHoatChat.setText(thuoc.getThanhPhan());
		txtSoDangKy.setText(thuoc.getSoDangKy());
		txtVAT.setText(thuoc.getThueVAT() + "");

		cboDBC = new JComboBox<String>();
		cboDBC.setFont(new Font("Arial", Font.PLAIN, 14));
		cboDBC.setBounds(134, 186, 440, 30);
		contentPane.add(cboDBC);

		cboNuocSX = new JComboBox<String>();
		cboNuocSX.setFont(new Font("Arial", Font.PLAIN, 14));
		cboNuocSX.setBackground(new Color(153, 153, 153));
		cboNuocSX.setBounds(716, 416, 440, 30);
		contentPane.add(cboNuocSX);
		try {
			Thuoc_Pn.loadCb(cboNuocSX, thuocDao.getNuocSanXuat());
			Thuoc_Pn.loadCb(cboDBC, thuocDao.getDangBaoChe());
			Thuoc_Pn.loadCb(cboDVT, thuocDao.getDonViTinh());
			Thuoc_Pn.loadCb(cboTrangThai, Arrays.asList("Kinh doanh","Ngừng kinh doanh"));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cboNuocSX.setSelectedItem(thuoc.getNuocSanXuat());
		cboDBC.setSelectedItem(thuoc.getDangBaoChe());
		cboDVT.setSelectedItem(thuoc.getDonViTinh());
		cboTrangThai.setSelectedIndex(thuoc.isTrangThaiKinhDoanh() ? 0 : 1);

		txtQCDG = new JTextField();
		txtQCDG.setText((String) null);
		txtQCDG.setFont(new Font("Arial", Font.PLAIN, 14));
		txtQCDG.setBounds(134, 356, 1022, 30);
		contentPane.add(txtQCDG);

		txtQCDG.setText(thuoc.getQuyCachDongGoi());
		
		

		btnLu.addActionListener(this);
		btnHuy.addActionListener(this);		
	
	}
	public boolean validData() {
		
		if(txtTenThuoc.getText().equals("")) {
			JOptionPane.showMessageDialog(this,"Tên thuốc không được rỗng");
			txtTenThuoc.selectAll();
			txtTenThuoc.requestFocus();
			return false;
		}
		String gia = txtGiaBan.getText().trim();
		try {
			Double.parseDouble(gia);
			if(Double.parseDouble(gia) <= 0) {
				JOptionPane.showMessageDialog(this,"Giá là số dương");
				txtGiaBan.selectAll();
				txtGiaBan.requestFocus();
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Giá là số dương");
			txtGiaBan.selectAll();
			txtGiaBan.requestFocus();
			return false;
		}
		
		String vat = txtVAT.getText().trim();
		try {
			Double.parseDouble(vat);
			if(Double.parseDouble(vat) < 0) {
				JOptionPane.showMessageDialog(this,"Thuế VAT phải là số dương");
				txtVAT.selectAll();
				txtVAT.requestFocus();
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Thuế VAT phải là số dương");
			txtVAT.selectAll();
			txtVAT.requestFocus();
			return false;
		}
		
		if(txtHamLuong.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this,"Hàm lượng không được rỗng");
			txtHamLuong.selectAll();
			txtHamLuong.requestFocus();
			return false;
		}
		
		if(txtHoatChat.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this,"Hoạt chất không được rỗng");
			txtHoatChat.selectAll();
			txtHoatChat.requestFocus();
			return false;
		}
		
		if(txtQCDG.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Qui cách đóng gói không được rỗng");
			txtQCDG.selectAll();
			txtQCDG.requestFocus();
			return false;
		}
		
		if(txtHangSX.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Hãng sản xuất không được rỗng");
			txtHangSX.selectAll();
			txtHangSX.requestFocus();
			return false;
		}
			
		return true;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnLu)&& validData()) {
			String ten = txtTenThuoc.getText();
			Double gia = Double.parseDouble(txtGiaBan.getText());
			float vat = Float.parseFloat(txtVAT.getText());
			String dvt = cboDVT.getSelectedItem().toString();
			String dbc = cboDBC.getSelectedItem().toString();
			String sdk = txtSoDangKy.getText();
			String hl = txtHamLuong.getText();
			String hc = txtHoatChat.getText();
			String dg = txtQCDG.getText();
			String hangsx = txtHangSX.getText();
			String nuocsx = cboNuocSX.getSelectedItem().toString();
			boolean trangThai = cboTrangThai.getSelectedIndex() == 0 ? true : false;

			Thuoc thuoc1 = new Thuoc(thuoc.getMaThuoc(), ten, gia, dvt, hc, dg, dbc, hl, hangsx, nuocsx,
					trangThai, vat, sdk, thuoc.getCongDung(), thuoc.getSoLuongBanDau(),
					thuoc.getHanSuDung());

			boolean tt = false;
			try {
				tt = thuocDao.capNhatThuoc(thuoc1);
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (tt)
			{
				JOptionPane.showMessageDialog(null, "Sửa thành công");
				dispose();
			}
			else
				JOptionPane.showMessageDialog(null, "Sửa không thành công, vui lòng xem lại");
		} if(o.equals(btnHuy)) {
			dispose();
		}
	}
}
