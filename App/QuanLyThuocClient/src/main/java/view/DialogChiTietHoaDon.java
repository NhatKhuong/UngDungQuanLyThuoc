package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.Desktop;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import java.util.List;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

import dao.HoaDonDao;
import entity.CT_HoaDon;
import entity.HoaDon;
import util.IP;
import util.Format;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class DialogChiTietHoaDon extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private final JTable table = new JTable();
	private DefaultTableModel tableModel;
	private JLabel lblNewLabel_9;
	private JLabel lblNewLabel_9_1_1;
	private JLabel lblNewLabel_9_1;
	private JScrollPane scrollPane;
	private HoaDonDao hoaDonDao;
	public JButton btn_XuatPDF;
	private HoaDon hoaDon;
	JButton btnXuatHoaDonTuCuaHang;
	private int xacNhan;

	public DialogChiTietHoaDon(String maHD) throws RemoteException {
		setModal(true);
		try {
			hoaDonDao = (HoaDonDao) Naming.lookup(IP.getIP + "hoaDonDao");
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
		try {
			hoaDon = hoaDonDao.getHoaDonTheoMa(maHD);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setResizable(false);

		getContentPane().setBackground(Color.WHITE);
		setBackground(Color.WHITE);
		setBounds(100, 100, 750, 780);
		getContentPane().setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Tên nhà thuốc: ");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 22, 118, 13);
		contentPanel.add(lblNewLabel);

		JLabel lblaCh = new JLabel("Địa chỉ: ");
		lblaCh.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblaCh.setBounds(10, 45, 110, 13);
		contentPanel.add(lblaCh);

		JLabel lblNewLabel_1 = new JLabel("NHÀ THUỐC AN NHIÊN");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_1.setBounds(112, 22, 191, 13);
		contentPanel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Số 2 Nguyễn văn bảo Phường 4 Gò vấp Tp Hồ Chí Minh");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.ITALIC, 16));
		lblNewLabel_2.setBounds(112, 45, 546, 13);
		contentPanel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("HÓA ĐƠN BÁN LẺ");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 35));
		lblNewLabel_3.setBounds(211, 98, 350, 53);
		contentPanel.add(lblNewLabel_3);

		JLabel lblNhnVin = new JLabel("Nhân viên:");
		lblNhnVin.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNhnVin.setBounds(10, 170, 110, 13);
		contentPanel.add(lblNhnVin);

		JLabel lblKhchHng = new JLabel("Khách hàng:\r\n");
		lblKhchHng.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblKhchHng.setBounds(10, 207, 110, 13);
		contentPanel.add(lblKhchHng);

		JLabel lblNewLabel_4 = new JLabel(hoaDon.getKhachHang().getTenKhachHang().toUpperCase());
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(112, 170, 179, 13);
		contentPanel.add(lblNewLabel_4);

		JLabel lblNewLabel_4_1 = new JLabel(hoaDon.getNhanVien().getTenNhanVien().toUpperCase());
		lblNewLabel_4_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_4_1.setBounds(112, 207, 215, 13);
		contentPanel.add(lblNewLabel_4_1);

		JLabel lblaCh_1 = new JLabel("Địa chỉ\r\n:");
		lblaCh_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblaCh_1.setBounds(451, 207, 110, 13);
		contentPanel.add(lblaCh_1);

		JLabel lblaCh_1_1 = new JLabel("Ngày lập\r\n");
		lblaCh_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblaCh_1_1.setBounds(451, 170, 110, 13);
		contentPanel.add(lblaCh_1_1);

		JLabel lblNewLabel_5 = new JLabel("Mã HD:\r\n");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_5.setBounds(10, 68, 70, 13);
		contentPanel.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel(hoaDon.getMaHoaDonBan());
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_6.setBounds(112, 68, 98, 13);
		contentPanel.add(lblNewLabel_6);

		JLabel lblNewLabel_2_1 = new JLabel("Gò vấp Tp Hồ Chí Minh");
		lblNewLabel_2_1.setFont(new Font("Times New Roman", Font.ITALIC, 16));
		lblNewLabel_2_1.setBounds(536, 207, 163, 13);
		contentPanel.add(lblNewLabel_2_1);

		JLabel lblNewLabel_7 = new JLabel(hoaDon.getNgayLapHDBan().toString());
		lblNewLabel_7.setFont(new Font("Times New Roman", Font.ITALIC, 16));
		lblNewLabel_7.setBounds(536, 170, 152, 13);
		contentPanel.add(lblNewLabel_7);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 250, 736, 367);

		contentPanel.add(scrollPane);

		table.setModel(tableModel = new DefaultTableModel(new Object[][] {},
				new String[] { "STT", "Tên thuốc", "DVT", "Số lượng", "Đơn giá", "Thành tiền" }));
		scrollPane.setViewportView(table);
		table.setEnabled(false);

		List<CT_HoaDon> dsCTHoaDon = hoaDonDao.chiTietHoaDon(hoaDon.getMaHoaDonBan());
		double theuVat = 0;
		double tong = 0;
		if (dsCTHoaDon != null) {
			int count = 0;
			for (CT_HoaDon ct_HoaDon : dsCTHoaDon) {
				count += 1;
				theuVat += ct_HoaDon.getThueVat() * (ct_HoaDon.getGiaBan() * ct_HoaDon.getSoLuong());
				tong += ct_HoaDon.thanhTien();
				String[] s = { String.valueOf(count), ct_HoaDon.getThuoc().getTenThuoc(),
						ct_HoaDon.getThuoc().getDonViTinh(), String.valueOf(ct_HoaDon.getSoLuong()),
						Format.chuyenDoiTienTe(ct_HoaDon.getGiaBan()), Format.chuyenDoiTienTe(ct_HoaDon.thanhTien()) };
				tableModel.addRow(s);
			}
		}

		JLabel lblNewLabel_8 = new JLabel("Tổng thành tiền:");
		lblNewLabel_8.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_8.setBounds(451, 630, 122, 20);
		contentPanel.add(lblNewLabel_8);

		JLabel lblNewLabel_8_1 = new JLabel("Thuế VAT:");
		lblNewLabel_8_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_8_1.setBounds(451, 660, 122, 20);
		contentPanel.add(lblNewLabel_8_1);

		lblNewLabel_9 = new JLabel(Format.chuyenDoiTienTe(tong));
		lblNewLabel_9.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_9.setBounds(570, 630, 156, 20);
		contentPanel.add(lblNewLabel_9);

		lblNewLabel_9_1 = new JLabel(Format.chuyenDoiTienTe(theuVat));

		lblNewLabel_9_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_9_1.setBounds(570, 660, 156, 20);
		contentPanel.add(lblNewLabel_9_1);

		JLabel lblNewLabel_8_1_1 = new JLabel("Tổng");
		lblNewLabel_8_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_8_1_1.setBounds(451, 690, 122, 20);
		contentPanel.add(lblNewLabel_8_1_1);

		lblNewLabel_9_1_1 = new JLabel(Format.chuyenDoiTienTe(theuVat + tong));
		lblNewLabel_9_1_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_9_1_1.setBounds(570, 690, 156, 20);
		contentPanel.add(lblNewLabel_9_1_1);

		JLabel lblNewLabel_8_1_1_1 = new JLabel("Khách đưa:\r\n");
		lblNewLabel_8_1_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_8_1_1_1.setBounds(10, 630, 86, 20);
		contentPanel.add(lblNewLabel_8_1_1_1);

		JLabel lblNewLabel_8_1_1_1_1 = new JLabel("Tiền thừa");
		lblNewLabel_8_1_1_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_8_1_1_1_1.setBounds(10, 660, 78, 20);
		contentPanel.add(lblNewLabel_8_1_1_1_1);

		JLabel lblNewLabel_9_2 = new JLabel(Format.chuyenDoiTienTe(hoaDon.getTienNhan()));
		lblNewLabel_9_2.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_9_2.setBounds(92, 630, 304, 20);
		contentPanel.add(lblNewLabel_9_2);

		JLabel lblNewLabel_9_2_1 = new JLabel(Format.chuyenDoiTienTe(hoaDon.getTienNhan() - hoaDon.getTongTien()));
		lblNewLabel_9_2_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_9_2_1.setBounds(92, 660, 284, 20);
		contentPanel.add(lblNewLabel_9_2_1);

		btn_XuatPDF = new JButton("Xuất PDF");
		btn_XuatPDF.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_XuatPDF.setBounds(10, 690, 90, 30);
		contentPanel.add(btn_XuatPDF);

		btnXuatHoaDonTuCuaHang = new JButton("");
		btnXuatHoaDonTuCuaHang.setBounds(661, 0, 0, 0);
		contentPanel.add(btnXuatHoaDonTuCuaHang);
		System.out.println(hoaDon.getTienNhan());

		btn_XuatPDF.addActionListener(this);
		btnXuatHoaDonTuCuaHang.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnXuatHoaDonTuCuaHang)) {
			xacNhan = JOptionPane.showConfirmDialog(this, "Bạn có muốn xem file", "Thông báo",
					JOptionPane.YES_NO_OPTION);
			xuatFile(hoaDon.getMaHoaDonBan());
			return;
		}
		if (o.equals(btn_XuatPDF)) {
			int xacNhan = JOptionPane.showConfirmDialog(this, "Bạn có muốn xuất file", "Thông báo",
					JOptionPane.YES_NO_OPTION);
			if (xacNhan == JOptionPane.YES_OPTION) {
				xacNhan = JOptionPane.showConfirmDialog(this, "Bạn có muốn xem file", "Thông báo",
						JOptionPane.YES_NO_OPTION);
				xuatFile(hoaDon.getMaHoaDonBan());
			}
		}
	}

	public void xuatFile(String path) {
		this.btn_XuatPDF.setVisible(false);
		path = "hoaDon\\" + path + ".pdf";
		if (!path.matches("(.)+(\\.pdf)$")) {
			path += ".pdf";
		}
		Container content = this.getContentPane();
		int height = content.getHeight();
		int width = content.getHeight();
		BufferedImage img = new BufferedImage(content.getWidth(), content.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = img.createGraphics();
		content.printAll(g2d);
		g2d.dispose();
		try {
			Document d = new Document();
			PdfWriter writer = PdfWriter.getInstance(d, new FileOutputStream(path));
			d.open();

			PdfContentByte contentByte = writer.getDirectContent();
			Image image = Image.getInstance(contentByte, scaleImage(595, height, img), 1);

			PdfTemplate template = contentByte.createTemplate(width, height);
			image.setAbsolutePosition(0, 0);
			template.addImage(image);
			contentByte.addTemplate(template, 0, 100);
			d.close();

			if (xacNhan == JOptionPane.YES_OPTION)
				try {
					Desktop.getDesktop().open(new File(path));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		} catch (IOException | DocumentException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, "Không thành công");
		}
		setVisible(false);
		dispose();
	}

	public BufferedImage scaleImage(int WIDTH, int HEIGHT, BufferedImage img) {
		BufferedImage bi = null;
		try {
			ImageIcon ii = new ImageIcon(img);
			bi = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
			Graphics2D g2d = (Graphics2D) bi.createGraphics();
			g2d.addRenderingHints(
					new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
			g2d.drawImage(ii.getImage(), 0, 0, WIDTH, HEIGHT, null);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return bi;
	}
}