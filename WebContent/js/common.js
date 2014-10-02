function formattime(val) {  
	       if(val != undefined){
	    	   var year=parseInt(val.year)+1900;  
			    var month=(parseInt(val.month)+1);  
			    month=month>9?month:('0'+month);  
			    var date=parseInt(val.date);  
			    date=date>9?date:('0'+date); 
			    var time=year+'-'+month+'-'+date;
			   // var hours=parseInt(val.hours);  
			   // hours=hours>9?hours:('0'+hours);  
			   // var minutes=parseInt(val.minutes);  
			   // minutes=minutes>9?minutes:('0'+minutes);  
			   // var seconds=parseInt(val.seconds);  
			   // seconds=seconds>9?seconds:('0'+seconds); 
			   // var time=year+'-'+month+'-'+date+' '+hours+':'+minutes+':'+seconds;
			    return time;
	       }else{
	    	   return "";
	       }
}  