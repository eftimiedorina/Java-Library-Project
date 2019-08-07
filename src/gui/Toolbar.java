package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import utils.CryptoException;
import utils.CryptoUtils;

public class Toolbar extends JPanel {
	private JButton btnEncrypt;
	private JButton btnDecrypt;
	private JButton btnPrint;
	private TabelPanel pn = new TabelPanel();
	public static JTextField searchText;

	private final JFileChooser fileChooser;
	private CryptoUtils crypto;
	private TabelPanel tablePanel;
	private File outputFile;
	private String key;

	public Toolbar() {
		btnEncrypt = new JButton("Encrypt file");
		btnEncrypt.setBounds(120, 380, 250, 30);

		btnDecrypt = new JButton("Decrypt file");
		btnDecrypt.setBounds(120, 380, 250, 30);

		btnPrint = new JButton("Print");
		btnPrint.setBounds(120, 380, 250, 30);

		fileChooser = new JFileChooser();

		outputFile = new File("crypt.txt");

		searchText = new JTextField(12);
		// searchLabel = new JLabel("Enter author name");

		String key = "thebestsecretkey";

		setLayout(new FlowLayout(FlowLayout.LEFT));
		add(btnEncrypt);
		add(btnDecrypt);
		// add(btnPrint);
		// add(searchLabel);
		// add(searchText);

		btnEncrypt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (fileChooser.showOpenDialog(Toolbar.this) == JFileChooser.APPROVE_OPTION) {
					try {

						crypto.encrypt(key, fileChooser.getSelectedFile(), outputFile);
						JOptionPane.showMessageDialog(Toolbar.this, "The file has been encrypted.");

					} catch (CryptoException e1) {
						System.out.println(e1.getMessage());
						e1.printStackTrace();
					}

				}

			}

		});

		btnDecrypt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					crypto.decrypt(key, outputFile, outputFile);
					JOptionPane.showMessageDialog(Toolbar.this, "The file has been decrypted.");
				} catch (CryptoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		});

	}

}
