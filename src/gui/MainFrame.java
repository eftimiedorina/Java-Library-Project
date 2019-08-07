package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;

import controller.Controller;

public class MainFrame extends JFrame {
	private TabelPanel tabelPanel;
	private Toolbar toolbar;
	private FormPanel formPanel;
	private Controller controller;
	// private TextPanel textPanel;
	private JFileChooser fileChooser;

	public MainFrame() {
		setTitle("Library");
		setLayout(new BorderLayout());
		setSize(900, 800);
		setMinimumSize(new Dimension(500, 400));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		tabelPanel = new TabelPanel();
		toolbar = new Toolbar();
		formPanel = new FormPanel();
		// textPanel = new TextPanel();

		fileChooser = new JFileChooser();

		// add filter to save a particular type of file
		fileChooser.addChoosableFileFilter(new BookFileFilter());

		setJMenuBar(createMenuBar());

		controller = new Controller();

		tabelPanel.setData(controller.getBooks());

		tabelPanel.setBookTableListener(new BookTableListener() {
			public void rowDeleted(int row) {
				controller.removeBook(row);
			}
		});

		formPanel.setFormListener(new FormListener() {
			public void formEventOccurred(FormEvent e) {
				controller.addBook(e);
				// refresh table
				tabelPanel.refresh();

			}
		});

		add(toolbar, BorderLayout.NORTH);
		add(tabelPanel, BorderLayout.CENTER);
		add(formPanel, BorderLayout.WEST);

	}

	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();

		JMenu fileMenu = new JMenu("File");

		JMenuItem exportDataItem = new JMenuItem("Export Data...");
		JMenuItem importDataItem = new JMenuItem("Import Data...");
		JMenuItem exitItem = new JMenuItem("Exit");

		fileMenu.add(exportDataItem);
		fileMenu.add(importDataItem);
		fileMenu.addSeparator(); // add line between items
		fileMenu.add(exitItem);

		// add the fileMenu to the menuBar
		menuBar.add(fileMenu);

		// mnemonice
		fileMenu.setMnemonic(KeyEvent.VK_F);// all the keys to the keyboard
		exitItem.setMnemonic(KeyEvent.VK_X);

		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));

		importDataItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));

		exportDataItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));

		importDataItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
					try {
						controller.loadFromFile(fileChooser.getSelectedFile());
						tabelPanel.refresh();
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(MainFrame.this, "Colud not load data from file.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}

				}
				;
			}

		});

		exportDataItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
					try {
						controller.saveToFile(fileChooser.getSelectedFile());
						tabelPanel.refresh();
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(MainFrame.this, "Colud not load data to file.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}

				;
			}

		});

		exitItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				System.exit(0);
			}

		});

		return menuBar;

	}

}
