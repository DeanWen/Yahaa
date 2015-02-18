<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" import="java.util.*"%>
<%@ page language="java" import="databeans.UserBean"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Home</title>
<meta name="format-detection" content="telephone=no" />
<link rel="icon" href="images/favicon.ico">
<link rel="shortcut icon" href="images/favicon.ico" />
<link rel="stylesheet" href="css/style.css">
<link href='http://fonts.googleapis.com/css?family=Economica:700'
    rel='stylesheet' type='text/css'>
<script src="js/jquery.js"></script>
<script src="js/jquery-migrate-1.1.1.js"></script>
<script src="js/script.js"></script>
<script src="js/jquery.ui.totop.js"></script>
<script src="js/superfish.js"></script>
<script src="js/jquery.equalheights.js"></script>
<script src="js/jquery.mobilemenu.js"></script>
<script src="js/jquery.easing.1.3.js"></script>
<script>
        $(document).ready(function(){
            $().UItoTop({ easingType: 'easeOutQuart' });
        })
        </script>



<!-- CSS for slidesjs.com example -->
<link rel="stylesheet" href="css/example.css">
<link rel="stylesheet" href="css/font-awesome.min.css">
<link rel="stylesheet" href="css/front.css">
<!-- End CSS for slidesjs.com example -->

<!-- SlidesJS Optional: If you'd like to use this design -->
<style>
body {
    -webkit-font-smoothing: antialiased;
    font: normal 15px/1.5 "Helvetica Neue", Helvetica, Arial, sans-serif;
    color: #232525;
    padding-top: 70px;
}

#slides {
    display: none
}

#slides .slidesjs-navigation {
    margin-top: 5px;
}

a.slidesjs-next, a.slidesjs-previous, a.slidesjs-play, a.slidesjs-stop {
    background-image: url(img/btns-next-prev.png);
    background-repeat: no-repeat;
    display: block;
    width: 12px;
    height: 18px;
    overflow: hidden;
    text-indent: -9999px;
    float: left;
    margin-right: 5px;
}

a.slidesjs-next {
    margin-right: 10px;
    background-position: -12px 0;
}

a:hover.slidesjs-next {
    background-position: -12px -18px;
}

a.slidesjs-previous {
    background-position: 0 0;
}

a:hover.slidesjs-previous {
    background-position: 0 -18px;
}

a.slidesjs-play {
    width: 15px;
    background-position: -25px 0;
}

a:hover.slidesjs-play {
    background-position: -25px -18px;
}

a.slidesjs-stop {
    width: 18px;
    background-position: -41px 0;
}

a:hover.slidesjs-stop {
    background-position: -41px -18px;
}

.slidesjs-pagination {
    margin: 7px 0 0;
    float: right;
    list-style: none;
}

.slidesjs-pagination li {
    float: left;
    margin: 0 1px;
}

.slidesjs-pagination li a {
    display: block;
    width: 13px;
    height: 0;
    padding-top: 13px;
    background-image: url(img/pagination.png);
    background-position: 0 0;
    float: left;
    overflow: hidden;
}

.slidesjs-pagination li a.active, .slidesjs-pagination li a:hover.active
    {
    background-position: 0 -13px
}

.slidesjs-pagination li a:hover {
    background-position: 0 -26px
}

#slides a:link, #slides a:visited {
    color: #333
}

#slides a:hover, #slides a:active {
    color: #9e2020
}

.navbar {
    overflow: hidden
}
</style>
<!-- End SlidesJS Optional-->

<!-- SlidesJS Required: These styles are required if you'd like a responsive slideshow -->
<style>
#slides {
    display: none
}

.container {
    margin: 0 auto
}

/* For tablets & smart phones */
@media ( max-width : 767px) {
    body {
        padding-left: 20px;
        padding-right: 20px;
    }
    .container {
        width: auto
    }
}

/* For smartphones */
@media ( max-width : 480px) {
    .container {
        width: auto
    }
}

/* For smaller displays like laptops */
@media ( min-width : 768px) and (max-width: 979px) {
    .container {
        width: 724px
    }
}

/* For larger displays */
@media ( min-width : 1200px) {
    .container {
        width: 1078px
    }
}
</style>
<!-- SlidesJS Required: -->
</head>
<body class="page1" id="top">
    <!--==============================header=================================-->
    <div class="main">
        <header>
            <div class="clear"></div>
            <div class="container_12">
                <div class="grid_12">
                    <h1>
                        <a href="index.do"> <img src="images/logo.gif"
                            alt="Yahaa">
                        </a>
                    </h1>
                    
                </div>
            </div>
        </header>
    </div>
    <div class="menu_block">
        <div class="container_12">
            <div class="grid_12">
                <nav class="horizontal-nav full-width horizontalNav-notprocessed">
                    <ul class="sf-menu">
					<c:choose>
						<c:when test = "${not empty user}">
						<li><a href="home.do">Home</a></li>						

						<li><a href = "viewGeography.do">Trend</a></li>
						<li class = "current"><a href = "rank.do">Rank</a></li>
						<li><a href="logout.do">Logout</a></li>
						</c:when>
						<c:otherwise>
						
						<li><a href="index.do">Browse</a></li>
						<li class="current"><a href="rank.do">Rank</a></li>
						<li><a href="login.do">Login</a></li>
						</c:otherwise>
					</c:choose>

                    </ul>
                </nav>
                <div class="clear"></div>
            </div>
            <div class="clear"></div>
        </div>
    </div>
<!--==============================Content=================================-->
<div class="content">
	<div class="ic"></div>
	<div class="container_12">
        <div class="grid_6">
        <br>
        <br>
        <table class="rank">
            <thead>
            <tr>
                <th>Rank</th>
                <th>User</th>
                <th>Likes</th>
            </tr>
            </thead>
            <tbody>
            <c:set var="count" value="0" scope="page" />
            <c:forEach var="user" items="${list}">
            <c:set var="count" value="${count + 1}" scope="page"/>
            <tr>
                <td><c:out value="${count}" /></td>      
                <td>${user.getScreen_Name()}</td>
                <td>${user.getLikeGiven()}</td>
            </tr>        
            </c:forEach>
            </tbody>
        </table>
	        <c:choose>
	        <c:when test = "${not empty user}">
	            <form action="sendTweet.do" method="POST">
	                <p style="font-size: 18px; padding: 2px;">&nbsp&nbsp&nbspShare with friends: <input type="text" name="text" size="40" style="padding: 5px; margin 5px;" value = "I have given ${user.getLikeGiven()} likes at Yahaa. Come join me!" required = "required"><br></p>
	                <button type="submit" style="border: 0; background: transparent; cursor:pointer;">
	                    <p>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<img src="images/Twitter-Button.png" width="80" height="25" alt="submit" /></p>
	                </button>
	            </form>
	        </c:when>
	        </c:choose>
        </div>
        <div class="grid_6">
            <div id="rank_div" style="width: 500px; height: 400px"></div>
        </div>
	</div>
</div>

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
	          colors: ['#5AB8E3']
	        };
	
	        var chart = new google.visualization.BarChart(document.getElementById('rank_div'));
	        chart.draw(data, options);
      }
  </script>
<jsp:include page="template-bottom.jsp" />