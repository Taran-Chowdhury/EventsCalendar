import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 
 * @author Taran Chowdhury
 * 
 *         this class creates an instance of the DataModel, primaryFrame and
 *         SecondaryFrame. it passes the datModel object to each frame for
 *         sending updated data. it attaches the two frames to the data model
 *
 */
public class SimpleCalendarTester {
	public static void main(String[] args) throws IOException {
		DataModel model = new DataModel();
		primaryFrame frame1 = new primaryFrame(model);
		secondaryFrame frame2 = new secondaryFrame(model);
		model.attach(frame1);
		model.attach(frame2);

	}
}
