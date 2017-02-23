/**
 * 
 */

var AJAX_ACTION_URL = "ajaxAction.do?";
function sendmessages(){
	var reciever = document.getElementById("currentreciever");
	reciever = reciever.value;
	var sender = document.getElementById("loggedinuser");
	sender = sender.value;
	var message = document.getElementById("message").value;
	$.ajax({
		type: "POST",
		url: AJAX_ACTION_URL,
		data: "addChatMessage=addChatMessage"+"&sender=" + sender+"&reciever="+reciever+"&message="+message,
		success: function(msg) {
		document.getElementById("message").value = "";
			}
	});
	var messages="";
	$.ajax({
		type: "POST",
		url: AJAX_ACTION_URL,
		data: "loadMessages=loadMessages"+"&sender=" + sender+"&reciever="+reciever,
		success: function(msg) {
			document.getElementById("chatpanel").innerHTML = "";
			msg = eval(msg.trim());
			messages = messages + "<div ><b>"+ reciever +" </b> <small> chat box</small></div>";
			messages = messages + "<div  style='height: 100%;'>";
			for(var i=0;i<msg.length;i++){
				if(msg[i].sender == sender){
					messages = messages + "<div ><blockquote class='me pull-right'>"+sender+":"+msg[i].message+"</blockquote></div>";
				}else{
					messages = messages + "<div><blockquote class='me pull-left'>"+reciever+":"+msg[i].message+"</blockquote></div>";
				}
			}
			messages = messages +  "</div>";
			document.getElementById("chatpanel").innerHTML = messages;
			}
	});
	
	
}

function popup(reciever){
var sender = document.getElementById("loggedinuser");
sender = sender.value;
	$("#chatbox").dialog();
	var hidden_reciever = document.getElementById("reciever");
	hidden_reciever.innerHTML = "<input type = 'hidden' id='currentreciever' value="+reciever+">"
	setInterval(function(){ 
	var messages="";
	$.ajax({
		type: "POST",
		url: AJAX_ACTION_URL,
		data: "loadMessages=loadMessages"+"&sender=" + sender+"&reciever="+reciever,
		success: function(msg) {
			document.getElementById("chatpanel").innerHTML = "";
			msg = eval(msg.trim());
			messages = messages + "<div><b>"+ reciever +" </b> <small> chat box</small></div>";
			messages = messages + "<div style='height: 100%;'>";
			for(var i=0;i<msg.length;i++){
				if(msg[i].sender == sender){
					messages = messages + "<div ><blockquote class='me pull-right'>"+sender+":"+msg[i].message+"</blockquote></div>";
				}else{
					messages = messages + "<div ><blockquote class='me pull-left'>"+reciever+":"+msg[i].message+"</blockquote></div>";
				}
			}
			messages = messages +  "</div>";
			document.getElementById("chatpanel").innerHTML = messages;
			}
	});
    //code goes here that will be run every 5 seconds.    
	}, 5000);
}