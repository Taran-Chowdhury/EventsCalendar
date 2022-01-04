
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * 
 * @author Taran Chowdhury
 * 
 *         this class stores the time of event and compares them to times of
 *         other events to avoid time conflicts
 *
 */
public class TimeInterval implements Comparable<Object> {
	private String startTime;
	private String endTime;
	private LocalTime st;
	private LocalTime et;
	DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");

	/**
	 * mutator for startTime
	 * 
	 * @param time the start time of an event
	 */
	public void setStartTime(String time) {
		this.startTime = time;
		int length = startTime.length();
		setSt(startTime.substring(0, length - 2));
	}

	/**
	 * accessor for startTime
	 * 
	 * @return the startTime of an event
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * mutator for endTime
	 * 
	 * @param time the end time of an event
	 */
	public void setEndTime(String time) {
		this.endTime = time;
		int length = endTime.length();
		setEt(endTime.substring(0, length - 2));
	}

	/**
	 * accessor for endTime
	 * 
	 * @return the endTime of an event
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * accessor for st
	 * 
	 * @return the start time of an event
	 */
	public LocalTime getSt() {
		return st;
	}

	/**
	 * mutator for st
	 * 
	 * @param st the startime of an event
	 */
	public void setSt(String st) {
		this.st = LocalTime.parse(st, timeFormat);
	}

	/**
	 * accessor for et
	 * 
	 * @return the end time of an event
	 */
	public LocalTime getEt() {
		return et;
	}

	/**
	 * mutator for et
	 * 
	 * @param et the end time of an event
	 */
	public void setEt(String et) {
		this.et = LocalTime.parse(et, timeFormat);
	}

	/**
	 * compares two time intervals
	 */
	@Override
	public int compareTo(Object o1) {

		TimeInterval t1 = (TimeInterval) o1;

		int i = this.getSt().compareTo(t1.getSt());
		int j = this.getEt().compareTo(t1.getEt());
		int k = this.getSt().compareTo(t1.getEt());
		int l = this.getEt().compareTo(t1.getSt());

		if (i == 0 || j == 0 || (i == 1 && k == -1) || (l == 1 && j == -1)) {
			return 0;
		}
		return 1;
	}

	/**
	 * This method formats the toString for the timeInterval object
	 */
	public String toString() {
		return getStartTime() + " " + getEndTime();
	}
}
