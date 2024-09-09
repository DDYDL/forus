<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>병원 일정 관리하기</title>
<link rel="stylesheet" href="css/button.css" />
<style>
.manage {
	font-weight: bold;
	color: rgba(105, 233, 46);
}
</style>
<link rel="stylesheet" href="request.css" />
<link rel="stylesheet" href="css/hmy/list.css" />
<link rel="stylesheet" href="css/hmy/containerbox.css" />
<link rel="stylesheet" href="css/hmy/weekbutton.css" />
</head>
<body>
	<%@ include file="../mainhmypage.jsp"%>

	<div class="bodycontainerbox">

		<div class="bodybox">
			<div>
				<h3 align="left" text-align="left">예약 일정 관리</h3>
			</div>

			<td>점심시간
			<td><input type="time" id="input"> ~ <input type="time"
				id="input"></td>


			<td>예약간격 <select>
					<option value="30">30분</option>
					<option value="60">60분</option>
			</select>
			</td>

			<div>
				<div>
					<table class="styled-table">
						<thead>
							<tr>
								<td>
									<div class="check">
										<label> <input type="checkbox" id="mon" name="mon"
											value="월"> <span>월</span>
										</label>
									</div>
								</td>
								<td>영업시간
								<td><input type="time" id="input"> ~ <input
									type="time" id="input"></td>

<td>	</td>
<td>	</td>
<td>	</td>
<td>	</td>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									<div class="check">
										<label> <input type="checkbox" id="mon" name="mon"
											value="화"> <span>화</span>
										</label>
									</div>
								</td>
								<td>영업시간
								<td><input type="time" id="input"> ~ <input
									type="time" id="input"></td>
								</td>

							</tr>
							<tr>
								<td>
									<div class="check">
										<label> <input type="checkbox" id="mon" name="mon"
											value="월"> <span>수</span>
										</label>
									</div>
								</td>
								<td>영업시간
								<td><input type="time" id="input"> ~ <input
									type="time" id="input"></td>


							</tr>
							<tr>
								<td>
									<div class="check">
										<label> <input type="checkbox" id="mon" name="mon"
											value="월"> <span>목</span>
										</label>
									</div>
								</td>
								<td>영업시간
								<td><input type="time" id="input"> ~ <input
									type="time" id="input"></td>
								</td>

							</tr>
							<tr>
								<td>
									<div class="check">
										<label> <input type="checkbox" id="mon" name="mon"
											value="월"> <span>금</span>
										</label>
									</div>
								</td>
								<td>영업시간
								<td><input type="time" id="input"> ~ <input
									type="time" id="input"></td>
								</td>
							</tr>
							<tr>
								<td>
									<div class="check">
										<label> <input type="checkbox" id="mon" name="mon"
											value="월"> <span>토</span>
										</label>
									</div>
								</td>
								<td>영업시간
								<td><input type="time" id="input"> ~ <input
									type="time" id="input"></td>
								</td>
							</tr>
							<tr>
								<td>
									<div class="check">
										<label> <input type="checkbox" id="mon" name="mon"
											value="월"> <span>일</span>
										</label>
									</div>
								</td>
								<td>영업시간
								<td><input type="time" id="input"> ~ <input
									type="time" id="input"></td>
								</td>
								
							</tr>

						</tbody>
					</table>
				</div>
			
				<div>
					<br> <br> <br> 
					<button type="button" title="Button fade blue/green"
						class=" btn btnFade btnBlueGreen find-btn1"
						onclick="location.href='#.jsp'">수정</button>

				</div>


			</div>
		</div>
	</div>
</body>
</html>