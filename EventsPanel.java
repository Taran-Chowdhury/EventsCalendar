import java.awt.BorderLayout;
import java.awt.Color;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * 
 * @author Taran Chowdhury 11/30/21
 * 
 *         This class creates the events panel this displays the events of the
 *         current date.
 */
public class EventsPanel extends JPanel {

	JTextArea text = new JTextArea();
	JLabel label = new JLabel();
	private int WIDTH = 40;
	private int HEIGHT = 20;
	private DataModel model;
	private ArrayList<Event> events;

	/**
	 * creates the components of the events panel to display the selected date and
	 * the events scheduled for that date
	 * 
	 * @param model DataModel object that is used to get the updated data.
	 */
	public EventsPanel(DataModel model) {
		this.model = model;
		text.setColumns(WIDTH);
		text.setRows(HEIGHT);
		text.setBackground(Color.lightGray);
		setLayout(new BorderLayout());
		add(label, BorderLayout.NORTH);
		add(text, BorderLayout.SOUTH);
		events = model.getEvents();
		updateDateLabel(this.model.getCurrDate());
		updateTextArea();
	}

	/**
	 * updates the date label with current date
	 * 
	 * @param date currenDate from dataModel.
	 */
	public void updateDateLabel(LocalDate date) {
		label.setText(date.getDayOfWeek() + " " + date.format(DateTimeFormatter.ofPattern("MM/dd")));
	}

	/**
	 * updates the text area with the events scheduled for the currentDate
	 */
	public void updateTextArea() {
		events = model.getEvents();
		for (Event e : events) {
			if (e.getSd().equals(model.getCurrDate())) {
				text.append(e.getEventName() + ":  " + e.getTi() + "\n");
			}
		}

	}

	/**
	 * clears the current date label and the events text area
	 */
	public void resetTextArea() {
		text.setText("");
		label.removeAll();
	}
}
