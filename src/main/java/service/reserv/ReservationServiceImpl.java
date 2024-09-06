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

public class ReservationServiceImpl implements ReservationService {

	private ReservationDao reservationDao;
	public ReservationServiceImpl() {
		reservationDao = new ReservationDaoImpl();
	}

	@Override
	public Map<String, Object> getAvailableTimeSlots(int hospitalId, String date) {

		return null;

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
