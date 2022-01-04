import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 
 * @author Taran Chowdhury 11/30/21
 * 
 *         This class stores data for an event
 */
public class Event {
	private String eventName;
	private TimeInterval ti;
	private LocalDate sd;
	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yy");

	/**
	 * Accessor for event name
	 * 
	 * @return the eventName
	 */
	public String getEventName() {
		return eventName;
	}

	/**
	 * Mutator for eventName
	 * 
	 * @param eventName the name of event
	 */
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	/**
	 * Accessor for the TimeInterval of the Event
	 * 
	 * @return the timeInterval of the Event
	 */
	public TimeInterval getTi() {
		return ti;
	}

	/**
	 * Mutator for the TimeInterval of the Event
	 * 
	 * @param ti the timeInterval
	 */
	public void setTi(TimeInterval ti) {
		this.ti = ti;
	}

	/**
	 * Accessor for the date of the Event
	 * 
	 * @return date of the event
	 */
	public LocalDate getSd() {
		return sd;
	}

	/**
	 * Mutator for the date of the event
	 * 
	 * @param sd the date of the event
	 */
	public void setSd(String sd) {
		this.sd = LocalDate.parse(sd, dateFormat);
	}
}
