<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" import="java.util.*"%>
<jsp:include page="template-trendtop.jsp" />
<!--==============================Content=================================-->
<div class="inner maxheight">
	<h3>Number of Like By Hour</h3>
	<div id="chart_div"></div>
</div>
</div>
</div>
<div class="grid_4">
	<div class="box">
		<div class="inner maxheight">
			<h4>Top Videos</h4>
			<ul class="list">
				<li class="li"><a href="viewGeography.do">Geography Trend</a></li>
				<li class="li"><a href="likeTimeChart.do">Time Trend</a></li>
				<li class="li"><a href="viewTag.do">Tag Cloud</a></li>
			</ul>
			<h4 class="head1"></h4>
			<div class="block2">
			
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
	          curveType: 'function',
	          backgroundColor: 'F3F6F8',
	          hAxis: {title: 'Time(H)',maxAlternation:1,showTextEvery:2},
	          vAxis: {minValue: 0},
	          colors: ['#47AA48']
	        };
	
	        var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
	        chart.draw(data, options);
      }
    </script>


			</div>
			<jsp:include page="template-trendbottom.jsp" />