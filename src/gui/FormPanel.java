package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class FormPanel extends JPanel {

	private JTextField textRatio;
	private JTextField textAuthor;
	private JTextField textTitle;
	private JTextField textStatus;
	private JTextField textReader;
	private JButton btnUpdate;

	private JList statusList;
	private FormListener formListener;

	private JLabel labelRatio;
	private JLabel labelAuthor;
	private JLabel labelTitle;
	private JLabel labelStatus;
	private JLabel labelReader;

	public FormPanel() {
		Dimension dim = getPreferredSize();
		dim.width = 250;
		setPreferredSize(dim);

		statusList = new JList();
		textRatio = new JTextField(10);
		textAuthor = new JTextField(10);
		textTitle = new JTextField(10);
		textStatus = new JTextField(10);
		textReader = new JTextField(10);

		labelRatio = new JLabel("Ratio: ");
		labelAuthor = new JLabel("Author: ");
		labelTitle = new JLabel("Title: ");
		labelStatus = new JLabel("Status: ");
		labelReader = new JLabel("Reader: ");

		btnUpdate = new JButton("Update");

		/*----------------- create status list-------------*/

		DefaultListModel statusModel = new DefaultListModel();
		statusModel.addElement("SHELF");
		statusModel.addElement("OUTSIDE");
		statusModel.addElement("BORROWED");
		statusList.setModel(statusModel);

		statusList.setPreferredSize(new Dimension(110, 66));
		statusList.setBorder(BorderFactory.createEtchedBorder());
		statusList.setSelectedIndex(1);
		/*----------------- create border for GUI components-------------*/

		Border innerBorder = BorderFactory.createTitledBorder("Update Library");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

		setLayout(new GridBagLayout());

		GridBagConstraints gc = new GridBagConstraints();

		// First row //

		gc.weightx = 1;
		gc.weighty = 0.1;

		gc.gridx = 0;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.fill = GridBagConstraints.NONE;
		add(labelRatio, gc);

		gc.gridx = 1;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		add(textRatio, gc);

		/* second row */

		gc.weightx = 1;
		gc.weighty = 0.1;

		gc.gridy = 1;
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		add(labelAuthor, gc);

		gc.gridy = 1;
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		add(textAuthor, gc);

		/* third row */
		gc.weightx = 1;
		gc.weighty = 0.1;

		gc.gridy = 2;
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		add(labelTitle, gc);

		gc.gridy = 2;
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		add(textTitle, gc);

		/* fouth row */

		gc.weightx = 1;
		gc.weighty = 0.1;

		gc.gridy = 3;
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		add(labelStatus, gc);

		gc.gridy = 3;
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		add(statusList, gc);

		/* seventh row */

		gc.weightx = 1;
		gc.weighty = 0.1;

		gc.gridy = 4;
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		add(labelReader, gc);

		gc.gridy = 4;
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		add(textReader, gc);

		/* sixth row */
		gc.weightx = 1;
		gc.weighty = 1.0;

		gc.gridy = 5;
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(btnUpdate, gc);

		// add row
		btnUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String ratio = textRatio.getText();
				String author = textAuthor.getText();
				String title = textTitle.getText();
				String reader = textReader.getText();
				String status = (String) statusList.getSelectedValue();

				System.out.println(status);
				FormEvent ev = new FormEvent(this, ratio, author, title, status, reader);

				if (formListener != null) {
					formListener.formEventOccurred(ev);
				}

			}

		});
	}

	public void setFormListener(FormListener listener) {
		this.formListener = listener;
	}
}


