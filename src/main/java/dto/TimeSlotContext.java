package dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class TimeSlotContext {
	private LocalTime openingTime;
	private LocalTime closingTime;
	private LocalTime lunchStartTime;
	private LocalTime lunchEndTime;
	private LocalDate reservationDate;
	private LocalDate today;
	private LocalTime nowTime;
	private int intervalMinutes;

	public LocalTime getOpeningTime() {
		return openingTime;
	}

	public void setOpeningTime(LocalTime openingTime) {
		this.openingTime = openingTime;
	}

	public LocalTime getClosingTime() {
		return closingTime;
	}

	public void setClosingTime(LocalTime closingTime) {
		this.closingTime = closingTime;
	}

	public LocalTime getLunchStartTime() {
		return lunchStartTime;
	}

	public void setLunchStartTime(LocalTime lunchStartTime) {
		this.lunchStartTime = lunchStartTime;
	}

	public LocalTime getLunchEndTime() {
		return lunchEndTime;
	}

	public void setLunchEndTime(LocalTime lunchEndTime) {
		this.lunchEndTime = lunchEndTime;
	}

	public LocalDate getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(LocalDate reservationDate) {
		this.reservationDate = reservationDate;
	}

	public LocalDate getToday() {
		return today;
	}

	public void setToday(LocalDate today) {
		this.today = today;
	}

	public LocalTime getNowTime() {
		return nowTime;
	}

	public void setNowTime(LocalTime nowTime) {
		this.nowTime = nowTime;
	}

	public int getIntervalMinutes() {
		return intervalMinutes;
	}

	public void setIntervalMinutes(int intervalMinutes) {
		this.intervalMinutes = intervalMinutes;
	}
}
