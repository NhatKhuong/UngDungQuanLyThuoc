package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import dao.ThuocDao;
import entity.Thuoc;
import util.IP;

public class ChiTietThuoc_JFrame extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnDong;
	private JLabel lblTenThuoc;
	private JLabel lblTrangThai;
	private JLabel lblMaThuoc;
	private JLabel lblNhomThuoc;
	private JLabel lblQuiCachDongGoi;
	private JLabel lblDangBaoChe;
	private JLabel lblSoLuongTon;
	private JLabel lblDonViTinh;
	private JLabel lblGia;
	private JLabel lblCongDung;
	private JLabel lblHoatChat;
	private JLabel lblHamLuong;
	private JLabel lblVAT;
	private JLabel lblNuocSX;
	private JLabel lblHangSX;
	private String maThuoc;
	private ThuocDao thuocDao;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public ChiTietThuoc_JFrame(String maThuoc) {
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
		
		this.maThuoc = maThuoc;
		setBackground(Color.LIGHT_GRAY);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 990, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 51));
		panel.setBounds(0, 0, 984, 52);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblChiTitThuc = new JLabel("Chi Tiết Thuốc");
		lblChiTitThuc.setHorizontalAlignment(SwingConstants.CENTER);
		lblChiTitThuc.setForeground(Color.WHITE);
		lblChiTitThuc.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblChiTitThuc.setBounds(0, 11, 983, 30);
		panel.add(lblChiTitThuc);

		lblTenThuoc = new JLabel("5-Fluorouracil \"Ebewe\"");
		lblTenThuoc.setHorizontalAlignment(SwingConstants.CENTER);
		lblTenThuoc.setForeground(new Color(30, 144, 255));
		lblTenThuoc.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTenThuoc.setBounds(44, 74, 915, 30);
		contentPane.add(lblTenThuoc);

		JLabel lblTnThuc = new JLabel("Mã thuốc");
		lblTnThuc.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTnThuc.setBounds(44, 119, 100, 20);
		contentPane.add(lblTnThuc);

		JLabel lblTnThuc_1 = new JLabel("N.Thuốc");
		lblTnThuc_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTnThuc_1.setBounds(44, 177, 100, 20);
		contentPane.add(lblTnThuc_1);

		JLabel lblTnThuc_2 = new JLabel("QC.Đóng gói");
		lblTnThuc_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTnThuc_2.setBounds(44, 241, 100, 20);
		contentPane.add(lblTnThuc_2);

		JLabel lblTnThuc_3 = new JLabel("Dạng BC ");
		lblTnThuc_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTnThuc_3.setBounds(44, 494, 100, 20);
		contentPane.add(lblTnThuc_3);

		JLabel lblTnThuc_4 = new JLabel("SL.Tồn");
		lblTnThuc_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTnThuc_4.setBounds(44, 366, 100, 20);
		contentPane.add(lblTnThuc_4);

		lblTrangThai = new JLabel("Ngừng kinh doanh");
		lblTrangThai.setForeground(Color.RED);
		lblTrangThai.setFont(new Font("Tahoma", Font.ITALIC, 15));
		lblTrangThai.setBounds(853, 51, 131, 30);
		contentPane.add(lblTrangThai);

		JLabel lblTnThuc_4_1 = new JLabel("ĐV.Tính");
		lblTnThuc_4_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTnThuc_4_1.setBounds(44, 433, 100, 20);
		contentPane.add(lblTnThuc_4_1);

		JLabel lblCngDng = new JLabel("Công dụng");
		lblCngDng.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCngDng.setBounds(546, 115, 100, 20);
		contentPane.add(lblCngDng);

		JLabel lblTnThuc_1_1 = new JLabel("Hoạt chất");
		lblTnThuc_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTnThuc_1_1.setBounds(546, 173, 100, 20);
		contentPane.add(lblTnThuc_1_1);

		JLabel lblTnThuc_2_1 = new JLabel("Hàm lượng");
		lblTnThuc_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTnThuc_2_1.setBounds(546, 237, 100, 20);
		contentPane.add(lblTnThuc_2_1);

		JLabel lblTnThuc_3_1 = new JLabel("VAT");
		lblTnThuc_3_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTnThuc_3_1.setBounds(546, 299, 100, 20);
		contentPane.add(lblTnThuc_3_1);

		JLabel lblTnThuc_4_2 = new JLabel("Nước SX");
		lblTnThuc_4_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTnThuc_4_2.setBounds(546, 366, 100, 20);
		contentPane.add(lblTnThuc_4_2);

		JLabel lblTnThuc_4_1_1 = new JLabel("Hãng SX");
		lblTnThuc_4_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTnThuc_4_1_1.setBounds(44, 299, 100, 20);
		contentPane.add(lblTnThuc_4_1_1);

		lblMaThuoc = new JLabel("A0001");
		lblMaThuoc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMaThuoc.setBounds(156, 119, 324, 20);
		contentPane.add(lblMaThuoc);

		lblNhomThuoc = new JLabel("Trị ung thư và miễn dịch");
		lblNhomThuoc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNhomThuoc.setBounds(154, 176, 326, 20);
		contentPane.add(lblNhomThuoc);

		lblQuiCachDongGoi = new JLabel("Dạng lọ 10ml");
		lblQuiCachDongGoi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblQuiCachDongGoi.setBounds(154, 241, 382, 20);
		contentPane.add(lblQuiCachDongGoi);

		lblDangBaoChe = new JLabel("Dung dịch đậm đặc để pha thuốc tiêm");
		lblDangBaoChe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDangBaoChe.setBounds(147, 494, 520, 20);
		contentPane.add(lblDangBaoChe);

		lblSoLuongTon = new JLabel("100");
		lblSoLuongTon.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSoLuongTon.setBounds(147, 366, 333, 20);
		contentPane.add(lblSoLuongTon);

		lblDonViTinh = new JLabel("Lọ");
		lblDonViTinh.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDonViTinh.setBounds(147, 433, 333, 20);
		contentPane.add(lblDonViTinh);

		lblCongDung = new JLabel("Trị ung thư");
		lblCongDung.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCongDung.setBounds(636, 115, 338, 20);
		contentPane.add(lblCongDung);

		lblHoatChat = new JLabel("Nacl,Zn,P");
		lblHoatChat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHoatChat.setBounds(640, 173, 334, 20);
		contentPane.add(lblHoatChat);

		lblHamLuong = new JLabel("500mg/10ml");
		lblHamLuong.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHamLuong.setBounds(640, 238, 334, 20);
		contentPane.add(lblHamLuong);

		lblVAT = new JLabel("0.1");
		lblVAT.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblVAT.setBounds(636, 300, 338, 20);
		contentPane.add(lblVAT);

		lblNuocSX = new JLabel("Đức");
		lblNuocSX.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNuocSX.setBounds(636, 366, 338, 20);
		contentPane.add(lblNuocSX);

		lblHangSX = new JLabel("Honda");
		lblHangSX.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHangSX.setBounds(147, 299, 333, 20);
		contentPane.add(lblHangSX);

		JLabel lblGi = new JLabel("Giá");
		lblGi.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblGi.setBounds(546, 433, 46, 14);
		contentPane.add(lblGi);

		lblGia = new JLabel("100.000.00");
		lblGia.setForeground(new Color(255, 0, 0));
		lblGia.setFont(new Font("Dialog", Font.ITALIC, 15));
		lblGia.setBounds(636, 431, 338, 18);
		contentPane.add(lblGia);

		btnDong = new JButton("Thoát");
		btnDong.setIcon(new ImageIcon(ChiTietThuoc_JFrame.class
				.getResource("/img/logout.png")));
		btnDong.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnDong.setBounds(808, 506, 131, 33);
		contentPane.add(btnDong);
		
		Thuoc thuoc = dataChiTietThuoc();
		lblTenThuoc.setText(thuoc.getTenThuoc());
		lblVAT.setText(Float.toString(thuoc.getThueVAT())+"%");
		lblTrangThai.setText(thuoc.isTrangThaiKinhDoanh()?"Đang kinh doanh":"Ngừng kinh doanh");
		lblSoLuongTon.setText(Integer.toString(thuoc.getSoLuongBanDau()));
		lblQuiCachDongGoi.setText(thuoc.getQuyCachDongGoi());
		lblNuocSX.setText(thuoc.getNuocSanXuat());
		lblNhomThuoc.setText(thuoc.getCongDung().getNhomCongDung());
		lblMaThuoc.setText(thuoc.getMaThuoc());
		lblHoatChat.setText(thuoc.getThanhPhan());
		lblHangSX.setText(thuoc.getCongTySanXuat());
		lblHamLuong.setText(thuoc.getHamLuong());
		lblGia.setText(Double.toString(thuoc.getGiaBan())+" đ/ "+thuoc.getDonViTinh());
		lblDonViTinh.setText(thuoc.getDonViTinh());
		lblDangBaoChe.setText(thuoc.getDangBaoChe());
		lblCongDung.setText(thuoc.getCongDung().getCongDung());
		
		JSeparator separator = new JSeparator();
		separator.setBounds(44, 158, 895, 325);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(44, 218, 895, 265);
		contentPane.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(44, 283, 895, 200);
		contentPane.add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(44, 345, 895, 138);
		contentPane.add(separator_3);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(44, 411, 895, 72);
		contentPane.add(separator_4);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(44, 478, 895, 5);
		contentPane.add(separator_5);

		btnDong.addActionListener(this);
	}

	private Thuoc dataChiTietThuoc() {
		if(maThuoc.length() == 0){
			return null;
		}
		Thuoc thuoc = null;
		try {
			thuoc = thuocDao.getThuocTheoMa(maThuoc);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return thuoc;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(btnDong)){
			dispose();
		}

	}
}
