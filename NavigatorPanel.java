
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.time.format.DateTimeFormatter;

/**
 * 
 * @author Taran Chowdhury 11/30/21
 * 
 *         This class creates the panel with the next, previous, create and quit
 *         buttons.
 */
public class NavigatorPanel extends JPanel {

	JButton next = new JButton();
	JButton prev = new JButton();
	JButton create = new JButton();
	JButton quit = new JButton();
	private DataModel model;
	private secondaryFrame frame;
	private ArrayList<Event> events;

	/**
	 * This method creates the next, previous, create and quit buttons and adds them
	 * to this panel. This method also adds action listener to each button for their
	 * functionality.
	 * 
	 * @param model DataModel object that is used to get the updated data.
	 */
	public NavigatorPanel(DataModel model) {
		this.model = model;

		next.setText("Next");
		prev.setText("Previous");
		create.setText("Create");
		quit.setText("Quit");
		add(prev);
		add(next);
		add(create);
		add(quit);
		setLayout(new FlowLayout());

		/**
		 * This action listener just adds a day to the currentDate and updates the
		 * dataModel
		 */
		next.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				LocalDate next = model.getCurrDate().plusDays(1);
				model.setCurrDate(next);
			}
		});

		/**
		 * This action listener just subtracts a day to the currentDate and updates the
		 * dataModel
		 */
		prev.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				LocalDate prev = model.getCurrDate().plusDays(-1);
				model.setCurrDate(prev);
			}
		});

		/**
		 * This action listener creates and enables the secondary frame
		 */
		create.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame = new secondaryFrame(model);
				frame.setVisible(true);
				frame.setEnabled(true);
			}
		});

		/**
		 * This action listener takes all of the events stored in events ArrayList and
		 * writes them in a file after that it clears the eventsArrayList and disables
		 * the primary frame.
		 */
		quit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				events = model.getEvents();

				try {
					File file = new File(
							"C:\\Users\\taran\\Desktop\\CS 151\\assignment5\\Calender_Project\\events.txt");
					if (file.createNewFile()) {
						System.out.println("File created: " + file.getName());
					} else {
						System.out.println("File already exists.");
					}
				} catch (IOException e) {
					System.out.println("An error occurred.");
					e.printStackTrace();
				}

				try {
					FileWriter myWriter = new FileWriter(
							"C:\\Users\\taran\\Desktop\\CS 151\\assignment5\\Calender_Project\\events.txt");
					for (int i = 0; i < events.size(); i++) {
						String formattedSD = events.get(i).getSd().format(DateTimeFormatter.ofPattern("MM/dd/yy"));

						myWriter.write(events.get(i).getEventName() + "\n");
						myWriter.write(events.get(i).getTi().toString() + " " + formattedSD + "\n");

					}

					myWriter.close();
					System.out.println("Successfully wrote to the file.");
				} catch (IOException e) {
					System.out.println("An error occurred.");
					e.printStackTrace();
				}

				model.clearEventsList();
				closePrimaryFrame();
			}
		});
	}

	/**
	 * This method disables the primaryFrame
	 */
	public void closePrimaryFrame() {
		Container c = this.getParent();
		c.setVisible(false);
		c.setEnabled(false);
	}

}
