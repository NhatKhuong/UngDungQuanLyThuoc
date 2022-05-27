package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import dao.NhanVienDao;
import entity.NhanVien;
import util.IP;



public class DangNhap_JFrame extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private NhanVien nhanVien;
	public DangNhapResponse dangNhapResponse;
	private JTextField txtPassword;
	private JTextField txtSDT;
	private JButton btnXoaRong;
	private JButton btnDangNhap;
	private NhanVienDao nhanVienDao;
//	private String ip = IP.getIP;
	/**
	 * Launch the application.
	 */
	public interface DangNhapResponse {
		void getNhanVien(NhanVien nhanVien);
	}
	/**
	 * Create the frame.
	 */
	public DangNhap_JFrame(DangNhapResponse dangNhapResponse) {
		this.dangNhapResponse = dangNhapResponse;
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 690, 389);
		contentPane = new JPanel();
		setResizable(false);
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(51, 51, 102));
		panel.setBounds(0, 0, 684, 44);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ĐĂNG NHẬP");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 684, 44);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Tên đăng nhập:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(113, 111, 137, 25);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Mật khẩu:");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(113, 166, 137, 25);
		contentPane.add(lblNewLabel_1_1);
		
		txtSDT = new JTextField();
		txtSDT.setText("0945601320");
		txtSDT.setBounds(260, 109, 300, 30);
		
		txtSDT.setColumns(10);
		contentPane.add(txtSDT);
		
		txtPassword = new JTextField();
		txtPassword.setText("123");
		txtPassword.setColumns(10);
		txtPassword.setBounds(260, 163, 300, 30);
		contentPane.add(txtPassword);
		
		JCheckBox chckbxNhMtKhu = new JCheckBox("Nhớ mật khẩu?");
		chckbxNhMtKhu.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		chckbxNhMtKhu.setBounds(257, 200, 297, 23);
		contentPane.add(chckbxNhMtKhu);
		
		btnXoaRong = new JButton("Xóa rỗng");
		btnXoaRong.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnXoaRong.setBounds(260, 256, 120, 35);
		contentPane.add(btnXoaRong);
		
		btnDangNhap = new JButton("Đăng nhập");
		btnDangNhap.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnDangNhap.setBounds(440, 256, 120, 35);
		contentPane.add(btnDangNhap);
		
		btnDangNhap.addActionListener(this);
		btnXoaRong.addActionListener(this);
		
		
		SecurityManager securityManager = System.getSecurityManager();
		if (securityManager == null) {
			System.setProperty("java.security.policy", "policy/policy.policy");
			System.setSecurityManager(new SecurityManager());
		}

		try {
		nhanVienDao =(dao.NhanVienDao) Naming.lookup(IP.getIP+"nhanVienDao");
		
		
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			System.out.println("1");
			e.printStackTrace();
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object object = e.getSource();
		if (object.equals(btnDangNhap)) {
			String sdt = txtSDT.getText();
			String password = txtPassword.getText();
			if (!sdt.matches(".*")) {
				JOptionPane.showMessageDialog(this, "Số điện thoại không đúng địng dạng");
				txtSDT.selectAll();
				txtSDT.requestFocus();
				return;
			}
			if (sdt.trim().length() == 0) {
				JOptionPane.showMessageDialog(this, "Bạn chưa nhập số điện thoại");
				txtSDT.selectAll();
				txtSDT.requestFocus();
				return;
			}
			if (password.trim().length() == 0) {
				JOptionPane.showMessageDialog(this, "Bạn chưa nhập mật khẩu");
				txtPassword.requestFocus();
				return;
			}
			
			try {
				if (nhanVienDao.dangNhap(sdt, password)) {
					// dung
					dangNhapResponse.getNhanVien(nhanVienDao.layThongTinNhanVienQuaSDT(sdt));
					
					
					dangNhapResponse.getNhanVien(nhanVien);
					this.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(this, "Bạn sai số điện thoại hoặc mật khẩu");
				}
			} catch (HeadlessException | RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}else if(object.equals(btnXoaRong)) {
			txtPassword.setText("");
			txtSDT.setText("");
		}
	}
}
