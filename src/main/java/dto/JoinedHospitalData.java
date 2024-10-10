package dto;

import java.time.LocalTime;

public class JoinedHospitalData {
	private LocalTime htime_opening;
	private LocalTime htime_closing;
	private LocalTime h_lunch_time_start;
	private LocalTime h_lunch_time_end;
	private int h_interval_time;
	private LocalTime reserv_time;

	@Override
	public String toString() {
		return "JoinedHospitalData{" +
			"htime_opening=" + htime_opening +
			", htime_closing=" + htime_closing +
			", h_lunch_time_start=" + h_lunch_time_start +
			", h_lunch_time_end=" + h_lunch_time_end +
			", h_interval_time=" + h_interval_time +
			", reserv_time=" + reserv_time +
			'}';
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

	public LocalTime getH_lunch_time_start() {
		return h_lunch_time_start;
	}

	public void setH_lunch_time_start(LocalTime h_lunch_time_start) {
		this.h_lunch_time_start = h_lunch_time_start;
	}

	public LocalTime getH_lunch_time_end() {
		return h_lunch_time_end;
	}

	public void setH_lunch_time_end(LocalTime h_lunch_time_end) {
		this.h_lunch_time_end = h_lunch_time_end;
	}

	public int getH_interval_time() {
		return h_interval_time;
	}

	public void setH_interval_time(int h_interval_time) {
		this.h_interval_time = h_interval_time;
	}

	public LocalTime getReserv_time() {
		return reserv_time;
	}

	public void setReserv_time(LocalTime reserv_time) {
		this.reserv_time = reserv_time;
	}
}
