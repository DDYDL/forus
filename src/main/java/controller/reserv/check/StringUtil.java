package controller.reserv.check;

public class StringUtil {
	/**
	 * 문자열이 null이 아니고, 비어있지 않은지 검사하는 메서드
	 * @param str 검사할 문자열
	 * @return 문자열이 null 또는 빈 문자열이 아닌 경우 true 반환
	 */
	public static boolean isNotEmpty(String str) {
		return str != null && !str.trim().isEmpty();
	}
}