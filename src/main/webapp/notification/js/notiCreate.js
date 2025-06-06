document.addEventListener("DOMContentLoaded", function() {
	
	//時間選取器，限定只能選現在時間10分鐘後的時間
	let date = new Date();
	date.setMinutes(date.getMinutes() + 10);
	let year = String(date.getFullYear());
	let month = String(date.getMonth() + 1).padStart(2, "0");
	let day = String(date.getDate()).padStart(2, "0");
	let hour = String(date.getHours()).padStart(2, "0");
	let minute = String(date.getMinutes()).padStart(2, "0");
	let today = year + "-" + month + "-" + day + "T" + hour + ":" +minute;
	let dateInput = document.getElementById("notificationSentAt");
	dateInput.setAttribute("min", today);
	
	//會員選取器
	$('#notificationMemberId').multiSelect({ // 注意這裡的方法名是小寫開頭的 'multiselect'
			nonSelectedText: '請選擇選項',
			enableFiltering: true, // 啟用搜尋功能
			includeSelectAllOption: true, // 允許選擇所有選項 (可選)
			buttonWidth: '100%', // 按鈕寬度 (可選)
			selectionHeader: "<label for='notificationMemberId'>通知會員</label>",
			selectableHeader: "<label>會員清單</label>",
			selectableFooter: "<p style='font-size: 10px; text-align: center;'>點選上面要通知的對象</p>",
		});

});