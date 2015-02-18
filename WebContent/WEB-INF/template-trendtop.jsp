<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Trend</title>
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
<script src="js/processing.js"></script>
<script src="js/d3.v3.min.js"></script>
<script src="js/d3.layout.cloud.js"></script>
<script src="../js/tagcanvas.min.js" type="text/javascript"></script>
<script type="text/javascript">
	window.onload = function() {
		try {
			TagCanvas.Start('myCanvas', 'tags', {
				textColour : '#000000',
				outlineColour : '#ffffff',
				reverse : true,
				depth : 0.8,
				maxSpeed : 0.1
			});
		} catch (e) {
			// something went wrong, hide the canvas container
			document.getElementById('myCanvasContainer').style.display = 'none';
		}
	};
</script>
<script src="js/init.js"></script>
<script>
	$(document).ready(function() {
		$().UItoTop({
			easingType : 'easeOutQuart'
		});
	})
</script>


<script type='text/javascript' src='http://www.google.com/jsapi'></script>
<script type='text/javascript'>
	google.load('visualization', '1', {
		'packages' : [ 'geochart' ]
	});
	google.setOnLoadCallback(drawVisualization);
	
	var stateData = [];
	d3.csv("js/states.csv", function(data){
		data.forEach(function(d){
		stateData.push([d.state, d.count]);
		});
	});	
	
	function drawVisualization() {
		/* var data = google.visualization.arrayToDataTable([
				[ 'State', 'Flickr & Twitter Sum' ], [ 'Alabama', 20 ],
				[ 'Alaska', 3 ], [ 'Arizona', 2 ], [ 'Arkansas', 1 ],
				[ 'California', 50 ], [ 'Colorado', 4 ], [ 'Connecticut', 8 ],
				[ 'Delaware', 11 ], [ 'Florida', 19 ], [ 'Georgia', 0 ],
				[ 'Hawaii', 0 ], [ 'Idaho', 1 ], [ 'Illinois', 12 ],
				[ 'Indiana', 18 ], [ 'Lowa', 1 ], [ 'Kansas', 1 ],
				[ 'Kentucky', 1 ], [ 'Louisiana', 0 ], [ 'Maine', 0 ],
				[ 'Maryland', 23 ], [ 'Massachusetts', 29 ],
				[ 'Michigan', 31 ], [ 'Minnesota', 17 ], [ 'Mississippi', 2 ],
				[ 'Missouri', 7 ], [ 'Montana', 1 ], [ 'Nebraska', 0 ],
				[ 'Nevada', 0 ], [ 'New Hampshire', 1 ], [ 'New Jersey', 31 ],
				[ 'New Mexico', 1 ], [ 'New York', 71 ],
				[ 'North Carolina', 1 ], [ 'North Dakota', 0 ], [ 'Ohio', 4 ],
				[ 'Oklahoma', 13 ], [ 'Oregon', 18 ], [ 'Pennsylvania', 94 ],
				[ 'Rhode Island', 0 ], [ 'South Carolina', 0 ],
				[ 'South Dakota', 0 ], [ 'Tennessee', 0 ], [ 'Texas', 41 ],
				[ 'Utah', 22 ], [ 'Vermont', 0 ], [ 'Virginia', 16 ],
				[ 'Washington', 45 ], [ 'West Virginia', 6 ],
				[ 'Wisconsin', 9 ], [ 'Wyoming', 0 ] ]); */
	
		var states = [['state', 'count']];
		for(i = 1; i < stateData.length; i++) {
			var cur = [stateData[i][0], parseInt(stateData[i][1])];
			states.push(cur);
		}
		
		var Final = google.visualization.arrayToDataTable(states);
		var geochart = new google.visualization.GeoChart(document
				.getElementById('visualization'));
		geochart.draw(Final, {
			width : 556,
			height : 347,
			region : "US",
			resolution : "provinces"
		});
	}
</script>

</head>
<body id="top">
	<!--==============================header=================================-->
	<div class="main">
		<header>
			<div class="clear"></div>
			<div class="container_12">
				<div class="grid_12">
					<h1>
						<a href="index.do"> <img src="images/logo.gif"
							alt="Your Happy Family">
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
					
						<li><a href="home.do">Home</a></li>
						<li class = "current"><a href="viewGeography.do">Trend</a></li>
						<li><a href="rank.do">Rank</a></li>
						<li><a href="logout.do">Logout</a></li>
						
						
					</ul>
				</nav>
				<div class="clear"></div>
			</div>
			<div class="clear"></div>
		</div>
	</div>
	<div class="main">
		<!--==============================Content=================================-->
		<div class="content">
			<div class="container_12">
				<div class="grid_8">
					<div class="box">