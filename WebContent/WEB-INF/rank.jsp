<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" import="java.util.*"%>
<%@ page language="java" import="databeans.UserBean"%>
<jsp:include page="template-top.jsp" />
<!--==============================Content=================================-->
<div class="content">
	<div class="ic"></div>
	<div class="container_12">
        <table>
            <tr>
                <th>Rank</th>
                <th>User</th>
                <th>Likes</th>
            </tr>
            <c:set var="count" value="0" scope="page" />
			<c:forEach var="user" items="${list}">
			<c:set var="count" value="${count + 1}" scope="page"/>
            <tr>
                <td><c:out value="${count}" /></td>      
                <td>${user.getScreen_Name()}</td>
                <td>${user.getLikeGiven()}</td>
            </tr>        
			</c:forEach>
        </table>
	</div>
</div>

	<body>
	<div id="table_div" style="width: 900px; height: 500px"></div>
	</body>

	<body>
	<div id="rank_div" style="width: 700px; height: 400px"></div>
	</body>


<script type="text/javascript" src="https://www.google.com/jsapi"?autoload={
            'modules':[{
              'name':'visualization',
              'version':'1.1',
              'packages':['table']
            }]
          }"></script>
 <script type="text/javascript">
 		google.load('visualization', '1.1', {packages: ['table']});
      google.setOnLoadCallback(drawChart);
      function drawChart() {
	      var data = new google.visualization.DataTable();
	      var jsArr = new Array();  
	  	  var jsArr2 = new Array();
	  	    
	  	    <%
	  	      UserBean[] table = (UserBean[])request.getAttribute("list");
		  	    for (int i=0; i < table.length; i++) {  
		  	    %>  
	  	    jsArr[<%= i %>] = '<%=table[i].getScreen_Name()%>'; 
	  	    <%}%>
	  	    <%  
		  	    for (int i=0; i < table.length; i++) {  
		  	    %>  
		  	  jsArr2[<%= i %>] = '<%=table[i].getLikeGiven()%>'; 
		  	    <%}%>
	    	  data.addColumn('string','Name');
	    	  data.addColumn('number', 'Count');
	    	  data.addRows(jsArr.length);
	    	  //alert(jsArr.length);
	    	    <%  
		    	    for (int i=0; i < table.length; i++) {
		    	    	
		    	    %>
	    	    data.setValue(<%= i %>,0, jsArr[<%= i %>]);
	    	    data.setValue(<%= i %>,1, parseInt(jsArr2[<%= i %>]));
	    	   
	    	    <%
		    	    }
		    	  %>

		    	  var options = {
		    	          title: 'User Rank',
		    	          showRowNumber: true,
		    	          width: '500px',
		    	          cssClassNames: {tableRow:{style: 'font-style:bold; font-size:22px;'}}
		    	        };
	
	        var chart = new google.visualization.Table(document.getElementById('table_div'));
	        chart.draw(data, options);
      }
  </script>





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
	  	      UserBean[] list = (UserBean[])request.getAttribute("list");
		  	    for (int i=0; i < list.length; i++) {  
		  	    %>  
	  	    jsArr[<%= i %>] = '<%=list[i].getScreen_Name()%>'; 
	  	    <%}%>
	  	    <%  
		  	    for (int i=0; i < list.length; i++) {  
		  	    %>  
		  	  jsArr2[<%= i %>] = '<%=list[i].getLikeGiven()%>'; 
		  	    <%}%>
	    	  data.addColumn('string','Name');
	    	  data.addColumn('number', 'Count');
	    	  data.addRows(jsArr.length);
	    	  //alert(jsArr.length);
	    	    <%  
		    	    for (int i=0; i < list.length; i++) {
		    	    	
		    	    %>
	    	    data.setValue(<%= i %>,0, jsArr[<%= i %>]);
	    	    data.setValue(<%= i %>,1, parseInt(jsArr2[<%= i %>]));
	    	   
	    	    <%
		    	    }
		    	  %>

	    	  
	        var options = {
	          title: 'User Rank',
	          hAxis: {title: 'Like Count',minValue:0},
	          vAxis: {minValue: 0},
	        };
	
	        var chart = new google.visualization.BarChart(document.getElementById('rank_div'));
	        chart.draw(data, options);
      }
  </script>
<jsp:include page="template-bottom.jsp" />