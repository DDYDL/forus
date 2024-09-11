package service.reserv;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controller.reserv.check.IntegerNullCheck;
import controller.reserv.check.StringNullCheck;
import dao.reserv.ReservationDao;
import dao.reserv.ReservationDaoImpl;
import dto.Pet;
import dto.Reservation;
import dto.Hospital;
import dto.Hospital_time;
import dto.TimeSlot;
import dto.User;

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

		LocalDate reservationDate = LocalDate.parse(date);

		List<TimeSlot> availableTimeSlots = calculateAvailableTimeSlots(hospitalTimes, reservedTimes, hospital, reservationDate);

		result.put("availableTimeSlots", availableTimeSlots);
		return result;

	}


	private List<TimeSlot> calculateAvailableTimeSlots(
		List<Hospital_time> hospitalTimes, List<LocalTime> reservedTimes, Hospital hospital, LocalDate reservationDate) {

		LocalTime lunchStartTime = hospital.getH_lunch_time_start();
		LocalTime lunchEndTime = hospital.getH_lunch_time_end();

		int intervalMinutes = hospital.getH_interval_time();

		List<TimeSlot> availableTimeSlots = new ArrayList<>();
		LocalTime nowTime = LocalTime.now();
		LocalDate today = LocalDate.now();

		LocalTime openingTime = null;
		LocalTime closingTime = null;

		for (Hospital_time time : hospitalTimes) {
			openingTime = time.getHtime_opening();
			closingTime = time.getHtime_closing();

			if (openingTime == null || closingTime == null) {
				availableTimeSlots.add(new TimeSlot(LocalTime.of(0, 0), false));
				continue;
			}

			//시간대 별로 예약 가능한 시간을 계산
			validateAvailableTimeSlots(
				reservedTimes,
				reservationDate,
				openingTime,
				closingTime,
				intervalMinutes,
				lunchStartTime,
				lunchEndTime,
				today,
				nowTime,
				availableTimeSlots);
		}
			return availableTimeSlots;
		}

	private  void validateAvailableTimeSlots(List<LocalTime> reservedTimes, LocalDate reservationDate, LocalTime openingTime,
		LocalTime closingTime, int intervalMinutes, LocalTime lunchStartTime, LocalTime lunchEndTime, LocalDate today,
		LocalTime nowTime, List<TimeSlot> availableTimeSlots) {

		for (LocalTime currentTime = openingTime; isBeforeClosingTime(closingTime, currentTime); currentTime = currentTime.plusMinutes(intervalMinutes)) {
			boolean isReserved = reservedTimes.contains(currentTime);
			boolean isLunchTime = isDuringLunchTime(currentTime, lunchStartTime, lunchEndTime);
			boolean isPastTime = reservationDate.equals(today) && currentTime.isBefore(nowTime);
			boolean isAvailable = !isReserved && !isLunchTime && !isPastTime;

			if (isAvailable) {
				availableTimeSlots.add(new TimeSlot(currentTime, true));
			} else {
				availableTimeSlots.add(new TimeSlot(currentTime, false));
			}

		}
	}

	private static boolean isBeforeClosingTime(LocalTime closingTime, LocalTime currentTime) {
		return !currentTime.isAfter(closingTime);
	}

	private static boolean isDuringLunchTime(LocalTime currentTime, LocalTime lunchStartTime, LocalTime lunchEndTime) {
		return currentTime.isAfter(lunchStartTime.minusMinutes(1)) && currentTime.isBefore(
			lunchEndTime.plusMinutes(1));
	}


	@Override
	public Reservation createReservation(String selectedDate, String selectedTime, String reservationContent,
		String customContent, String userId, String petId, String hospitalId) {

		if ("기타".equals(reservationContent) && StringNullCheck.isNotEmpty(customContent)) {
			reservationContent = customContent;
		}

		Integer parsedPetId = IntegerNullCheck.parseInteger(petId);

		LocalDate reservationDate = LocalDate.parse(selectedDate);
		LocalTime reservationTime = LocalTime.parse(selectedTime);

		Reservation reservation = new Reservation();
		reservation.sethId(Integer.parseInt(hospitalId));
		reservation.setUserId(Integer.parseInt(userId));
		reservation.setPetId(parsedPetId);
		reservation.setReservDate(reservationDate);
		reservation.setReservTime(reservationTime);
		reservation.setReservContent(reservationContent);


		return reservation;
	}

	@Override
	public List<Reservation> myAfterReserv() throws Exception {
		return null;
	}

	@Override
	public User getUserInfo(Integer userId) {
		return reservationDao.findUserInfoByUserId(userId);
	}

	@Override
	public Pet getPetInfo(Integer userId) {
		return reservationDao.findPetInfByPetId(userId);
	}

	@Override
	public void insertReservation(Reservation reservation) {
		reservationDao.insertReservation(reservation);
	}



	@Override
	public List<Map<String, Object>> myAfterReservList(Integer id) throws Exception {
		return reservationDao.selectMyAfterReservList(id);
	}

	@Override
	public List<Map<String, Object>> selectMyBeforeReservList(Integer id, Integer pet_id, String startDate, String endDate, boolean isConsult) throws Exception {
		 return reservationDao.selectMyBeforeReservList(id, pet_id, startDate, endDate, isConsult);
	}

	@Override
	public Reservation reservDetail(Integer reserv_id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
