import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * 
 * @author Taran Chowdhury
 * 
 *         this class creates the secondary frame which is used to create new
 *         events
 *
 */
public class secondaryFrame extends JFrame implements ChangeListener {
	private JPanel namePanel;
	private JTextField nameField;
	private JPanel dateAndTimePanel;
	private JTextField dateField;
	private JLabel toLabel;
	private JTextField sTField;
	private JTextField eTField;
	private JButton saveButton;
	private JPanel errorPanel;
	private JLabel errMessage;
	DataModel model;
	private ArrayList<Event> events;

	/**
	 * creates and adds components to this panel to store name of event data and the
	 * time of the event
	 * 
	 * @param model DataModel object that is used to get the updated data.
	 */
	public secondaryFrame(DataModel model) {
		Dimension d = new Dimension(250, 150);
		this.setMinimumSize(d);
		this.model = model;
		events = this.model.getEvents();

		namePanel = new JPanel();
		nameField = new JTextField();
		nameField.setColumns(40);
		nameField.setText("Event Name goes here");
		namePanel.setLayout(new FlowLayout());
		namePanel.add(nameField);

		dateAndTimePanel = new JPanel();
		dateField = new JTextField("" + this.model.getCurrDate().format(DateTimeFormatter.ofPattern("MM/dd/yy")));
		sTField = new JTextField("00:00");
		sTField.setColumns(8);
		toLabel = new JLabel("to");
		eTField = new JTextField("00:00");
		eTField.setColumns(8);
		saveButton = new JButton("Save");

		errorPanel = new JPanel();
		errMessage = new JLabel("Time Conflict with another event! please try a diffferent time.");
		errorPanel.add(errMessage);
		errorPanel.setLayout(new FlowLayout());
		errMessage.setVisible(false);

		dateAndTimePanel.setLayout(new FlowLayout());
		dateAndTimePanel.add(dateField);
		dateAndTimePanel.add(sTField);
		dateAndTimePanel.add(toLabel);
		dateAndTimePanel.add(eTField);
		dateAndTimePanel.add(saveButton);

		setLayout(new BorderLayout());
		add(namePanel, BorderLayout.NORTH);
		add(dateAndTimePanel, BorderLayout.CENTER);
		add(errorPanel, BorderLayout.SOUTH);

		/**
		 * this action listener saves the data from the text fields to create a new
		 * event and then disable the secondary frame
		 */
		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				errMessage.setVisible(false);
				boolean conflicts = false;
				Event event = new Event();
				event.setEventName(nameField.getText());
				event.setSd(dateField.getText());
				TimeInterval ti = new TimeInterval();
				ti.setStartTime(sTField.getText());
				ti.setEndTime(eTField.getText());
				event.setTi(ti);
				model.update();
				for (Event ev : events) {
					if (ev.getSd().equals(event.getSd())) {
						if (ev.getTi().compareTo(event.getTi()) == 0) {
							conflicts = true;
						}
					}

				}
				if (conflicts == true)
					errMessage.setVisible(true);
				else {
					model.addEvent(event);
					setEnabled(false);
					setVisible(false);
				}
			}
		});

		pack();
		setEnabled(false);
		setVisible(false);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		events = model.getEvents();
	}

}
