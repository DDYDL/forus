package controller.reserv.check;

import java.time.LocalTime;

public class TimeNullCheck {
	/**
	 * 시간(LocalTime)이 null인지 확인하는 메서드
	 * @param time 검사할 LocalTime 객체
	 * @return LocalTime이 null이면 true 반환
	 */
	public static boolean isNull(LocalTime time) {
		return time == null;
	}

	/**
	 * 시간(LocalTime)이 null이 아닌지 확인하는 메서드
	 * @param time 검사할 LocalTime 객체
	 * @return LocalTime이 null이 아니면 true 반환
	 */
	public static boolean isNotNull(LocalTime time) {
		return time != null;
	}
}



