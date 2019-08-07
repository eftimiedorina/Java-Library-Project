package gui;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTable.PrintMode;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import model.Book;
import model.Status;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import javax.swing.Action;
import javax.swing.JToggleButton;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextPane;

public class TabelPanel extends JPanel {

	private JFrame frame;
	public static JTable table;
	public static DefaultTableModel model;

	private BookTableModel tableModel;

	private JScrollPane scrollPane;
	private ArrayList<Book> listBook;
	private JPanel panel;
	private JPopupMenu popup;

	private BookTableListener bookTableListener;
	int row;

	public TabelPanel() {

		tableModel = new BookTableModel();
		table = new JTable(tableModel);
		popup = new JPopupMenu();

		JMenuItem removeItem = new JMenuItem("Delete row");
		popup.add(removeItem);

		// add listener for showing Delete item when you press right button on mouse
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println("pressed");
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					JTable source = (JTable) e.getSource();
					row = source.rowAtPoint(e.getPoint());

					System.out.println(row);

					int column = source.columnAtPoint(e.getPoint());

					if (!source.isRowSelected(row))
						source.changeSelection(row, column, false, false);

					popup.show(e.getComponent(), e.getX(), e.getY());

				}
			}

		});

		removeItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (bookTableListener != null) {
					bookTableListener.rowDeleted(row);
					tableModel.fireTableRowsDeleted(row, row);
				}
			}

		});

		setLayout(new BorderLayout());
		add(new JScrollPane(table), BorderLayout.CENTER);

	}

	public void setData(List<Book> db) {
		tableModel.setData(db);
	}

	public void refresh() {
		tableModel.fireTableDataChanged();
	}

	public void setBookTableListener(BookTableListener listener) {
		this.bookTableListener = listener;
	}
	/*
	 * public void bookFilter(JTextField searchText) { TableRowSorter<TableModel>
	 * rowSorter = new TableRowSorter<>(table.getModel());
	 * table.setRowSorter(rowSorter);
	 * 
	 * searchText.getDocument().addDocumentListener(new DocumentListener() {
	 * 
	 * @Override public void insertUpdate(DocumentEvent e) { String text =
	 * searchText.getText();
	 * 
	 * if (text.trim().length() == 0) { rowSorter.setRowFilter(null); } else {
	 * rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text)); } }
	 * 
	 * @Override public void removeUpdate(DocumentEvent e) { String text =
	 * searchText.getText();
	 * 
	 * if (text.trim().length() == 0) { rowSorter.setRowFilter(null); } else {
	 * rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text)); } }
	 * 
	 * @Override public void changedUpdate(DocumentEvent e) { throw new
	 * UnsupportedOperationException("Not supported yet."); }
	 * 
	 * });
	 * 
	 * }
	 */
}


