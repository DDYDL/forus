package dto;

import java.time.LocalTime;

public class Hospital_time {
	private Integer h_id;
	private String htime_week;
	private LocalTime htime_opening;
	private LocalTime htime_closing;
	private boolean htime_isable;
	
	public Hospital_time() {
		super();
	}
	public Hospital_time(String htime_week, LocalTime htime_opening, LocalTime htime_closing, boolean htime_isable) {
		super();
		this.htime_week = htime_week;
		this.htime_opening = htime_opening;
		this.htime_closing = htime_closing;
		this.htime_isable = htime_isable;
	}
	
	public Integer getH_id() {
		return h_id;
	}
	public void setH_id(Integer h_id) {
		this.h_id = h_id;
	}

	public String getHtime_week() {
		return htime_week;
	}

	public void setHtime_week(String htime_week) {
		this.htime_week = htime_week;
	}

	public LocalTime getHtime_opening() {
		return htime_opening;
	}
	public void setHtime_opening(LocalTime htime_opening) {
		this.htime_opening = htime_opening;
	}
	public LocalTime getHtime_closing() {
		return htime_closing;
	}
	public void setHtime_closing(LocalTime htime_closing) {
		this.htime_closing = htime_closing;
	}
	public boolean isHtime_isable() {
		return htime_isable;
	}
	public void setHtime_isable(boolean htime_isable) {
		this.htime_isable = htime_isable;
	}

	@Override
	public String toString() {
		return "Hospital_time{" +
			"h_id=" + h_id +
			", htime_week='" + htime_week + '\'' +
			", htime_opening=" + htime_opening +
			", htime_closing=" + htime_closing +
			", htime_isable=" + htime_isable +
			'}';
	}
}
