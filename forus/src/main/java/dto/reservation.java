package dto;

public class reservation {
	private Integer reservId;
	private Integer userId;
	private Integer petId;
	private Integer hId;
	private String reservDate;
	private String reservTime;
	private String reservApplyTime;
	private String reservContent;
	private String reservStatus;
	private String reservMemo;

	public Integer getReservId() {
		return reservId;
	}

	public void setReservId(Integer reservId) {
		this.reservId = reservId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getPetId() {
		return petId;
	}

	public void setPetId(Integer petId) {
		this.petId = petId;
	}

	public Integer gethId() {
		return hId;
	}

	public void sethId(Integer hId) {
		this.hId = hId;
	}

	public String getReservDate() {
		return reservDate;
	}

	public void setReservDate(String reservDate) {
		this.reservDate = reservDate;
	}

	public String getReservTime() {
		return reservTime;
	}

	public void setReservTime(String reservTime) {
		this.reservTime = reservTime;
	}

	public String getReservApplyTime() {
		return reservApplyTime;
	}

	public void setReservApplyTime(String reservApplyTime) {
		this.reservApplyTime = reservApplyTime;
	}

	public String getReservContent() {
		return reservContent;
	}

	public void setReservContent(String reservContent) {
		this.reservContent = reservContent;
	}

	public String getReservStatus() {
		return reservStatus;
	}

	public void setReservStatus(String reservStatus) {
		this.reservStatus = reservStatus;
	}

	public String getReservMemo() {
		return reservMemo;
	}

	public void setReservMemo(String reservMemo) {
		this.reservMemo = reservMemo;
	}

	public reservation(Integer reservId, Integer userId, Integer petId, Integer hId, String reservDate,
		String reservTime,
		String reservApplyTime, String reservContent, String reservStatus, String reservMemo) {
		this.reservId = reservId;
		this.userId = userId;
		this.petId = petId;
		this.hId = hId;
		this.reservDate = reservDate;
		this.reservTime = reservTime;
		this.reservApplyTime = reservApplyTime;
		this.reservContent = reservContent;
		this.reservStatus = reservStatus;
		this.reservMemo = reservMemo;
	}
}
