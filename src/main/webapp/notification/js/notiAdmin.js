document.addEventListener("DOMContentLoaded", function(){
	
	let getUriElement = document.getElementsByClassName("main-content")[0];
	
	//待發送通知數計算
	let notiTableLine = document.getElementsByClassName("noti-table-line");
	let dashboardNumber = document.getElementsByClassName("large-number")[0];
	
	dashboardNumber.innerHTML = notiTableLine.length;
	
	//通知清單動作綁定，整理準備通知資料給Servlet
	for(let i = 0; i < notiTableLine.length; i++){
		notiTableLine[i].addEventListener("click", function(){
			let form = document.createElement("form");
			form.method = "post";
			form.action = notiTableLine[i].getAttribute("data-form-action");
			
			let actionInput = document.createElement("input");
			actionInput.type = "input";
			actionInput.name = "action";
			actionInput.value = notiTableLine[i].getAttribute("data-action");
			form.appendChild(actionInput);
			
			let uriInput = document.createElement("input");
			uriInput.type = "input";
			uriInput.name = "originalUri";
			uriInput.value = getUriElement.getAttribute("data-originalUri");
			form.appendChild(uriInput);
			
			let notiInput = document.createElement("input");
			notiInput.type = "input";
			notiInput.name = "notiId";
			notiInput.value = notiTableLine[i].getAttribute("data-notiId");
			form.appendChild(notiInput);
			
			document.body.appendChild(form);
			form.submit();
		});
	
	}
	
});