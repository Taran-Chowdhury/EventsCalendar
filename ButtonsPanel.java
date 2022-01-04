import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author Taran Chowdhury 11/30/21
 * 
 *         creates a panel with a list of buttons in grid layout. these buttons
 *         act as the days of months.
 */
public class ButtonsPanel extends JPanel {
	private ArrayList<CustomButton> buttonsList = new ArrayList<CustomButton>();
	private JPanel labelPanel;
	private JLabel monthYear;
	private JLabel weekDays;
	private JPanel dayPanel;
	private DataModel model;

	/**
	 * creates a Buttons Panel in grid format. This date Grid represents the current
	 * Month.
	 * 
	 * @param model DataModel object that is used to get the updated data.
	 * @throws FileNotFoundException
	 */
	public ButtonsPanel(DataModel model) throws FileNotFoundException {
		this.model = model;
		for (int i = 0; i < 42; i++) {
			buttonsList.add(new CustomButton(model));
		}

		labelPanel = new JPanel();
		monthYear = new JLabel();
		monthYear.setSize(20, 20);
		weekDays = new JLabel(
				"Su            Mo            Tu            We            Th            Fr            Sa ");
		weekDays.setSize(20, 20);
		labelPanel.setLayout(new BorderLayout());
		labelPanel.add(monthYear, BorderLayout.NORTH);
		labelPanel.add(weekDays, BorderLayout.SOUTH);

		dayPanel = new JPanel();

		setLayout(new BorderLayout());
		add(labelPanel, BorderLayout.NORTH);
		add(dayPanel, BorderLayout.CENTER);

		dayPanel.setLayout(new GridLayout(6, 7));
		for (JButton button : buttonsList) {
			dayPanel.add(button);
		}

		updateButtons();
	}

	/**
	 * This method determines what day the month starts and enables the buttons
	 * accordingly. It also takes into account the total number of days the month
	 * has. This method also changes the current date of each Custom button
	 * according to the date that button represents.
	 */
	public void updateButtons() {
		Month month = model.getCurrDate().getMonth();
		int year = model.getCurrDate().getYear();
		YearMonth ym = YearMonth.of(year, month);
		int monthStartDay = LocalDate.of(year, month, 1).getDayOfWeek().getValue();
		int counter = 0;

		monthYear.setText(month + " " + year);

		if (monthStartDay != 7) {
			for (int i = 0; i < monthStartDay; i++) {
				counter++;
			}
			counter--;
		}

		for (int i = 1; i <= ym.lengthOfMonth(); i++) {
			buttonsList.get(i + counter).setEnabled(true);
			buttonsList.get(i + counter).setText("" + i);
			buttonsList.get(i + counter).setDayOfMonth(i);
			buttonsList.get(i + counter).setCurrDate(LocalDate.of(year, month, i));
			buttonsList.get(i + counter).addActionListener(listener);

			if (model.getCurrDate().getDayOfMonth() == i) {
				buttonsList.get(i + counter).setBackground(Color.yellow);
			}
		}
	}

	/**
	 * This method resets all the data of all the custom buttons in the buttonsList.
	 */
	public void resetAllButtons() {
		for (CustomButton b : buttonsList) {
			b.setEnabled(false);
			b.setText("  ");
			b.setDayOfMonth(0);
			b.setCurrDate(null);
			b.removeActionListener(listener);
			b.setBackground(getBackground());
		}
	}

	/**
	 * An ActionListener that updates the currentChosen date in the data model. once
	 * the user clicks one of the buttons on the date grid, the date represented by
	 * that button is then set as the current date in the data model.
	 */
	ActionListener listener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			CustomButton b = (CustomButton) e.getSource();
			model.setCurrDate(b.getCurrDate());
		}

	};
}
