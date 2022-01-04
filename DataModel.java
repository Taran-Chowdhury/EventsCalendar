import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * 
 * @author Taran Chowdhury 11/30/21
 * 
 *         This class is the DataModel. Stores all the data for this application
 */
public class DataModel {
	private ArrayList<Event> events = new ArrayList<Event>();
	private ArrayList<ChangeListener> listeners = new ArrayList<ChangeListener>();
	private LocalDate currentDate;

	/**
	 * sets the currentDate to today's date
	 * 
	 * @throws IOException
	 */
	public DataModel() throws IOException {
		currentDate = LocalDate.now().plusDays(-1);
		loadEvents();
	}

	/**
	 * Reads the events file and initializes the events arrayList. stores events
	 * from events file into events ArrayList
	 * 
	 * @throws IOException
	 */
	public void loadEvents() throws IOException {

		File txt = new File("C:\\Users\\taran\\Desktop\\CS 151\\assignment5\\Calender_Project\\events.txt");
		if (txt.createNewFile()) {
			System.out.println("file created " + txt.getName());
		} else {
			System.out.println("file exists " + txt.getName());
		}
		Scanner scnr = new Scanner(txt);

		while (scnr.hasNextLine()) {
			String line = scnr.nextLine();
			Event e = new Event();

			e.setEventName(line);
			line = scnr.nextLine();

			String[] array = line.split(" ");
			TimeInterval time = new TimeInterval();

			time.setStartTime(array[0]);
			time.setEndTime(array[1]);
			e.setTi(time);
			e.setSd(array[2]);
			events.add(e);
		}
		scnr.close();
	}

	/**
	 * mutator for currentDate calls the stateChanged for each change listener to
	 * send updated data.
	 * 
	 * @param date the new currentDate
	 */
	public void setCurrDate(LocalDate date) {
		currentDate = date;
		update();
	}

	/**
	 * Accessor for currentDate
	 * 
	 * @return the currentDate
	 */
	public LocalDate getCurrDate() {
		return currentDate;
	}

	/**
	 * adds new events to the events ArrayList calls the stateChanged of all
	 * registered changeListeners
	 * 
	 * @param e the event to be added
	 */
	public void addEvent(Event e) {
		events.add(e);
		update();
	}

	/**
	 * Accessor for the events ArrayList
	 * 
	 * @return the clone of the events ArrayList
	 */
	public ArrayList<Event> getEvents() {
		return (ArrayList<Event>) (events.clone());
	}

	/**
	 * adds new changeListner to dataModel
	 * 
	 * @param cl the changeListner to be added.
	 */
	public void attach(ChangeListener cl) {
		listeners.add(cl);
	}

	/**
	 * clears the events ArrayList and calls stateChnaged to update the data
	 */
	public void clearEventsList() {
		events.removeAll(events);
		update();
	}

	/**
	 * calls stateChanged of all attached ChangeListeners
	 */
	public void update() {
		for (ChangeListener l : listeners) {
			l.stateChanged(new ChangeEvent(this));
		}
	}
}
