import java.awt.BorderLayout;
import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * 
 * @author Taran Chowdhury 11/30/21
 * 
 *         This class creates the primary frame of the calendar app. where all
 *         the other components are added.
 */
public class primaryFrame extends JFrame implements ChangeListener {

	private DataModel dataModel;
	private ButtonsPanel buttons;
	private NavigatorPanel navigator;
	private EventsPanel EP;

	/**
	 * adds all of the components of the calendar application to this JFrame
	 * 
	 * @param datamodel DataModel object that is used to get the updated data.
	 * @throws FileNotFoundException
	 */
	public primaryFrame(DataModel datamodel) throws FileNotFoundException {
		this.dataModel = datamodel;
		setLayout(new BorderLayout());
		buttons = new ButtonsPanel(this.dataModel);
		add(buttons, BorderLayout.WEST);
		navigator = new NavigatorPanel(this.dataModel);
		add(navigator, BorderLayout.NORTH);
		EP = new EventsPanel(this.dataModel);
		add(EP, BorderLayout.EAST);

		this.setResizable(false);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		setEnabled(true);
	}

	/**
	 * updates all of the components to show case updated data.
	 */
	@Override
	public void stateChanged(ChangeEvent e) {
		buttons.resetAllButtons();
		buttons.updateButtons();
		EP.resetTextArea();
		EP.updateDateLabel(dataModel.getCurrDate());
		EP.updateTextArea();
	}

}
