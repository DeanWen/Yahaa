<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" import="java.util.*"%>
<jsp:include page="template-top.jsp" />
<!--==============================Content=================================-->
<div id="page-wrapper">
	<div class="container-fluid">
		<div class="row">
    		<div class="col-lg-12">
<%
	if (request.getAttribute("countList") != null) {
%>
<script type="text/javascript" src="https://www.google.com/jsapi"?autoload={
            'modules':[{
              'name':'visualization',
              'version':'1.1',
              'packages':['corechart']
            }]
          }"></script>
 <script type="text/javascript">
 		google.load('visualization', '1.1', {packages: ['corechart']});
      google.setOnLoadCallback(drawChart);
      function drawChart() {
	      var data = new google.visualization.DataTable();
	      var jsArr = new Array();  
	  	  var jsArr2 = new Array();
	  	    
	  	    <%
	  	  ArrayList<String> time = (ArrayList<String>)request.getAttribute("time");
		  	  ArrayList<Integer> count = (ArrayList<Integer>)request.getAttribute("countList");
		  	    for (int i=0; i < time.size(); i++) {  
		  	    %>  
	  	    jsArr[<%= i %>] = '<%=time.get(i)%>'; 
	  	    <%}%>
	  	    <%  
		  	    for (int i=0; i < count.size(); i++) {  
		  	    %>  
	  	    jsArr2[<%= i %>] = '<%=count.get(i)%>'; 
	  	    <%}%>
	    	  data.addColumn('string','Time');
	    	  data.addColumn('number', 'Count');
	    	  data.addRows(jsArr.length);
	    	  //alert(jsArr.length);
	    	    <%  
		    	    for (int i=0; i < count.size(); i++) {
		    	    	
		    	    %>
	    	    data.setValue(<%= i %>,0, jsArr[<%= i %>]);
	    	    data.setValue(<%= i %>,1, parseInt(jsArr2[<%= i %>]));
	    	   
	    	    <%
		    	    }
		    	  %>

	    	  
	        var options = {
	          title: 'Like Count',
	          curveType: 'none',
	          hAxis: {title: 'Time(H)',maxAlternation:1,showTextEvery:2},
	          vAxis: {title: 'Count', minValue: 0}
	        };
	
	        var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
	        chart.draw(data, options);
      }
    </script>
    <%
	}
%>
	<body>
	<div id="chart_div" style="width: 900px; height: 500px"></div>
	</body>
</div>
</div>
</div>
</div>
<jsp:include page="template-bottom.jsp" />