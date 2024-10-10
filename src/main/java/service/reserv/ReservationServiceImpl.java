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
import dto.JoinedHospitalData;
import dto.Pet;
import dto.Reservation;
import dto.Hospital;
import dto.Hospital_time;
import dto.TimeSlot;
import dto.TimeSlotContext;
import dto.User;
import util.PageInfo;

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
		List<LocalTime> reservedTimes = reservationDao.findReservedTimesByDate(params);
		Hospital hospital = reservationDao.findHospitalLunchTimeAndIntervalByHospitalId(hospitalId);

		LocalDate reservationDate = LocalDate.parse(date);

		List<TimeSlot> availableTimeSlots = calculateAvailableTimeSlots(hospitalTimes, reservedTimes, hospital,reservationDate);

		result.put("availableTimeSlots", availableTimeSlots);

		return result;

	}

	private List<TimeSlot> calculateAvailableTimeSlots(
		List<Hospital_time> hospitalTimes, List<LocalTime> reservedTimes, Hospital hospital,
		LocalDate reservationDate) {

		LocalTime lunchStartTime = hospital.getH_lunch_time_start();
		LocalTime lunchEndTime = hospital.getH_lunch_time_end();

		int intervalMinutes = hospital.getH_interval_time();

		List<TimeSlot> availableTimeSlots = new ArrayList<>();
		LocalTime nowTime = LocalTime.now();
		LocalDate today = LocalDate.now();

		for (Hospital_time time : hospitalTimes) {
			LocalTime openingTime = time.getHtime_opening();
			LocalTime closingTime = time.getHtime_closing();

			if (openingTime == null || closingTime == null) {
				availableTimeSlots.add(new TimeSlot(LocalTime.of(0, 0), false));
				continue;
			}

			TimeSlotContext timeSlotContext = createTimeSlotContext(reservationDate, openingTime, closingTime,
				lunchStartTime, lunchEndTime, today, nowTime,
				intervalMinutes);

			//시간대 별로 예약 가능한 시간을 계산
			validateAvailableTimeSlots(reservedTimes, timeSlotContext, availableTimeSlots);

		}
		return availableTimeSlots;
	}


	private void validateAvailableTimeSlots(List<LocalTime> reservedTimes, TimeSlotContext context,
		List<TimeSlot> availableTimeSlots) {

		for (LocalTime currentTime = context.getOpeningTime(); isBeforeClosingTime(context.getClosingTime(),currentTime); currentTime = currentTime.plusMinutes(context.getIntervalMinutes())) {

			boolean isAvailable = isAvailable(reservedTimes, context.getReservationDate(),
				context.getLunchStartTime(), context.getLunchEndTime(),
				context.getToday(), context.getNowTime(), currentTime);

			if (isAvailable) {
				availableTimeSlots.add(new TimeSlot(currentTime, true));
			} else {
				availableTimeSlots.add(new TimeSlot(currentTime, false));
			}

		}
	}

	private static boolean isAvailable(List<LocalTime> reservedTimes, LocalDate reservationDate,
		LocalTime lunchStartTime, LocalTime lunchEndTime, LocalDate today, LocalTime nowTime, LocalTime currentTime) {
		boolean isReserved = reservedTimes.contains(currentTime);
		boolean isLunchTime = isDuringLunchTime(currentTime, lunchStartTime, lunchEndTime);
		boolean isPastTime = reservationDate.equals(today) && currentTime.isBefore(nowTime);

		return !isReserved && !isLunchTime && !isPastTime;
	}

	private static boolean isBeforeClosingTime(LocalTime closingTime, LocalTime currentTime) {
		return !currentTime.isAfter(closingTime);
	}

	private static boolean isDuringLunchTime(LocalTime currentTime, LocalTime lunchStartTime, LocalTime lunchEndTime) {
		return currentTime.isAfter(lunchStartTime.minusMinutes(1)) && currentTime.isBefore(lunchEndTime.plusMinutes(1));
	}

	@Override
	public Reservation createReservation(String selectedDate, String selectedTime, String reservationContent,
		String customContent, String userId, String petId, String hospitalId) {

		if ("기타".equals(reservationContent) && StringNullCheck.isNotEmpty(customContent)) {
			reservationContent = customContent;
		}

		if (StringNullCheck.isEmpty(hospitalId)) {
			throw new IllegalArgumentException("병원 ID가 없습니다.");
		}

		if (StringNullCheck.isEmpty(userId)) {
			throw new IllegalArgumentException("사용자 ID가 없습니다.");
		}

		if (StringNullCheck.isEmpty(petId)) {
			throw new IllegalArgumentException("펫 ID가 없습니다.");
		}

		Integer parsedHospitalId = IntegerNullCheck.parseInteger(hospitalId);
		Integer parsedUserId = IntegerNullCheck.parseInteger(userId);
		Integer parsedPetId = IntegerNullCheck.parseInteger(petId);

		LocalDate reservationDate = LocalDate.parse(selectedDate);
		LocalTime reservationTime = LocalTime.parse(selectedTime);

		Reservation reservation = new Reservation();
		reservation.sethId(parsedHospitalId);
		reservation.setUserId(parsedUserId);
		reservation.setPetId(parsedPetId);
		reservation.setReservDate(reservationDate);
		reservation.setReservTime(reservationTime);
		reservation.setReservContent(reservationContent);
		reservation.setReservStatus("예약");

		return reservation;
	}

	private  TimeSlotContext createTimeSlotContext(LocalDate reservationDate, LocalTime openingTime, LocalTime closingTime,
		LocalTime lunchStartTime, LocalTime lunchEndTime, LocalDate today, LocalTime nowTime, int intervalMinutes) {
		TimeSlotContext timeSlotContext = new TimeSlotContext();
		timeSlotContext.setOpeningTime(openingTime);
		timeSlotContext.setClosingTime(closingTime);
		timeSlotContext.setLunchStartTime(lunchStartTime);
		timeSlotContext.setLunchEndTime(lunchEndTime);
		timeSlotContext.setReservationDate(reservationDate);
		timeSlotContext.setToday(today);
		timeSlotContext.setNowTime(nowTime);
		timeSlotContext.setIntervalMinutes(intervalMinutes);

		return timeSlotContext;
	}


	@Override
	public User getUserInfo(Integer userId) {
		return reservationDao.findUserInfoByUserId(userId);
	}

	@Override
	public List<Pet> getPetsInfo(Integer userId) {
		return reservationDao.findPetsByUserId(userId);
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
	public Integer selectMyBeforeReservCount(Integer id, Integer pet_id, String startDate, String endDate,
		boolean isConsult) throws Exception {
		return reservationDao.selectMyBeforeReservCount(id, pet_id, startDate, endDate, isConsult);
	}

	@Override
	public List<Map<String, Object>> selectMyBeforeReservList(Integer id, Integer pet_id, String startDate,
		String endDate, boolean isConsult, PageInfo pageInfo) throws Exception {
		Integer allCount = reservationDao.selectMyBeforeReservCount(id, pet_id, startDate, endDate, isConsult);

		Integer allPage = (int)Math.ceil((double)allCount / 10);
		//startPage : 1~10 => 1, 11~20 => 11
		Integer startPage = (pageInfo.getCurPage() - 1) / 10 * 10 + 1;
		Integer endPage = startPage + 10 - 1;
		if (endPage > allPage)
			endPage = allPage;

		pageInfo.setAllPage(allPage);
		pageInfo.setStartPage(startPage);
		pageInfo.setEndPage(endPage);

		Integer row = (pageInfo.getCurPage() - 1) * 10 + 1;
		return reservationDao.selectMyBeforeReservList(id, pet_id, startDate, endDate, isConsult, row - 1);
	}

	@Override
	public Map<String, Object> selectReservByReservId(Integer reserv_id) throws Exception {
		return reservationDao.selectReservByReservId(reserv_id);
	}

	@Override
	public Integer deleteReservation(int reserv_id) throws Exception {
		return reservationDao.deleteReserv(reserv_id);
	}



}
