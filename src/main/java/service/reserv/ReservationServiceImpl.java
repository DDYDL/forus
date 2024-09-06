package service.reserv;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.reserv.ReservationDao;
import dao.reserv.ReservationDaoImpl;
import dto.Reservation;
import dto.Hospital;
import dto.Hospital_time;
import dto.TimeSlot;

public class ReservationServiceImpl implements ReservationService {

	private ReservationDao reservationDao;

	public ReservationServiceImpl() {
		reservationDao = new ReservationDaoImpl();
	}

	@Override
	public Map<String, Object> getAvailableTimeSlots(int hospitalId, String date) {
		Map<String, Object> result = new HashMap<>();

		Map<String, Object> params = new HashMap<>();
		params.put("hospitalId", hospitalId);
		params.put("date", date);

		List<Hospital_time> hospitalTimes = reservationDao.findHospitalTimeByHospitalId(params);
		Hospital hospital = reservationDao.findHospitalLunchTimeAndIntervalByHospitalId(hospitalId);
		List<LocalTime> reservedTimes = reservationDao.findReservedTimesByDate(params);


		List<TimeSlot> availableTimeSlots = calculateAvailableTimeSlots(hospitalTimes, reservedTimes, hospital);

		result.put("availableTimeSlots", availableTimeSlots);
		return result;

	}

	private List<TimeSlot> calculateAvailableTimeSlots(
		List<Hospital_time> hospitalTimes,
		List<LocalTime> reservedTimes,
		Hospital hospital) {

		LocalTime lunchStartTime = hospital.getH_lunch_time_start();
		LocalTime lunchEndTime = hospital.getH_lunch_time_end();
		int intervalMinutes = hospital.getH_interval_time();

		List<TimeSlot> availableTimeSlots = new ArrayList<>();

		LocalTime openingTime = null;
		LocalTime closingTime = null;
		for (Hospital_time time : hospitalTimes) {
			openingTime = time.getHtime_opening();
			closingTime = time.getHtime_closing();

			if (openingTime == null || closingTime == null) {
				availableTimeSlots.add(new TimeSlot(LocalTime.of(0, 0), false));
				continue;
			}

			for (LocalTime currentTime = openingTime; !currentTime.isAfter(
				closingTime); currentTime = currentTime.plusMinutes(intervalMinutes)) {
				boolean isReserved = reservedTimes.contains(currentTime);
				boolean isLunchTime = isDuringLunchTime(currentTime, lunchStartTime, lunchEndTime);

				if (!isLunchTime && !isReserved) {
					availableTimeSlots.add(new TimeSlot(currentTime, true));
				} else {
					availableTimeSlots.add(new TimeSlot(currentTime, false));
				}

			}
		}
			return availableTimeSlots;
		}


	private static boolean isDuringLunchTime(LocalTime currentTime, LocalTime lunchStartTime, LocalTime lunchEndTime) {
		return currentTime.isAfter(lunchStartTime.minusMinutes(1)) && currentTime.isBefore(
			lunchEndTime.plusMinutes(1));
	}

	@Override
	public List<Reservation> myAfterReserv() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reservation reservDetail(Integer reserv_id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
