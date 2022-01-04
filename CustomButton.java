import java.io.FileNotFoundException;
import java.time.LocalDate;

import javax.swing.JButton;

/**
 * 
 * @author Taran Chowdhury 11/30/21
 *
 *
 *         This class creates a custom JButton
 */
public class CustomButton extends JButton {

	private LocalDate currentDate;
	private int dayOfMonth;

	/**
	 * Initializes the current date and the Day of month of the current date from
	 * DataModel
	 * 
	 * @param model DataModel object that is used to get the updated data.
	 * @throws FileNotFoundException
	 */
	public CustomButton(DataModel model) throws FileNotFoundException {
		currentDate = model.getCurrDate();
		dayOfMonth = model.getCurrDate().getDayOfMonth();
		setEnabled(false);
	}

	/**
	 * sets the currentDate for this button.
	 * 
	 * @param date the date represented by this button.
	 */
	public void setCurrDate(LocalDate date) {
		currentDate = date;
	}

	/**
	 * 
	 * @return returns the currentDate represented by this button.
	 */
	public LocalDate getCurrDate() {
		return currentDate;
	}

	/**
	 * sets the DayOfMonth of this button
	 * 
	 * @param day the DayOfMonth of the currentDate.
	 */
	public void setDayOfMonth(int day) {
		dayOfMonth = day;
	}

	/**
	 * 
	 * @return the dayOfMonth represented by CurrentDay
	 */
	public int getDayOfMonth() {
		return dayOfMonth;
	}
}
