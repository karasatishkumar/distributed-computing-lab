package org.simple.distributed.eventproducer.tasks.batch;

public class SystemLog {

	private String month;
	private int day;
	private String time;
	private String machine;
	private String message;

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public String getMachine() {
		return machine;
	}

	public void setMachine(String machine) {
		this.machine = machine;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Month - " + month + ", Day - " + day + ", Time - " + time + ", machine - " + machine + ", Message - "
				+ message;
	}
}
