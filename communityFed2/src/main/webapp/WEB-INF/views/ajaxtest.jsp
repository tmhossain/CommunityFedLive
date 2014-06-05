
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
  <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
   <script type="text/javascript" >
$(document).ready(function(){
  sendAjax();
});
 
function sendAjax() {
 var input = "{\"id\":10,\"userId\":2,\"cn\":\"Person\",\"ou\":\"USA\",\"title\":\"Software Dev\",\"fn\":\"Tareq\",\"ln\":\"Zaman\"}";
 $('#input').html("Request data:"+input);
$.ajax({ 
    url: "/tolling/rest", 
    type: 'POST', 
    dataType: 'json', 
    data: input, 
    contentType: 'application/json',
    mimeType: 'application/json',
    success: function(data) { 
        //alert(data.id + " " + data.accountId);
    	$('#id').html(data.id);
    	$('#uid').html(data.userId);
    	$('#fn').html(data.fn);
    	$('#ln').html(data.ln);
    },
    error:function(data,status,er) {
    	var result = "data:"+data + ",er:"+er;
       alert(result);
    }
});
}
</script>
</head>
<body>
 <div id="input"></div>
Response received
<br/>
 <div id="id"></div>
  <div id="uid"></div>
 <div id="fn"></div>
 <div id="ln"></div>
</body>
</html>