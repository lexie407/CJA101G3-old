document.addEventListener("DOMContentLoaded", function() {

	let deleteBtn = document.getElementById("deleteAdminNotificationBtn");
	//刪除按鈕動作綁定，整理準備通知資料給Servlet
	if(deleteBtn != null){
		deleteBtn.addEventListener("click", function() {
				if (confirm("確定刪除嗎?")){
					let form = document.createElement("form");
					form.method = "post";
					form.action = deleteBtn.getAttribute("data-formAction");

					let actionInput = document.createElement("input");
					actionInput.type = "input";
					actionInput.name = "action";
					actionInput.value = deleteBtn.getAttribute("data-action");
					form.appendChild(actionInput);

					let uriInput = document.createElement("input");
					uriInput.type = "input";
					uriInput.name = "originalUri";
					uriInput.value = deleteBtn.getAttribute("data-originalUri");
					form.appendChild(uriInput);

					let notiInput = document.createElement("input");
					notiInput.type = "input";
					notiInput.name = "notiId";
					notiInput.value = deleteBtn.getAttribute("data-notiId");
					form.appendChild(notiInput);

					document.body.appendChild(form);
					form.submit();
				}

			});
	}
	
	//綁定返回按鈕
	let backBtn = document.getElementById("backToAdminNotificationsBtn");
	backBtn.addEventListener("click", function(){
		history.back();
	})

});