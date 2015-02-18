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
<script src="js/tagcanvas.min.js" type="text/javascript"></script>
<script type="text/javascript">
	window.onload = function() {
		try {
			TagCanvas.Start('myCanvas', 'tags', {
				textColour : '#616130',
				outlineColour : '#f3f6f8',
				reverse : true,
				depth : 0.6,
				maxSpeed : 0.1
			});
		} catch (e) {
			// something went wrong, hide the canvas container
			document.getElementById('myCanvasContainer').style.display = 'none';
		}
	};
</script>

<!--[if lt IE 8]>
    <div style=' clear: both; text-align:center; position: relative;'>
      <a href="http://windows.microsoft.com/en-US/internet-explorer/products/ie/home?ocid=ie6_countdown_bannercode">
        <img src="http://storage.ie6countdown.com/assets/100/images/banners/warning_bar_0000_us.jpg" border="0" height="42" width="820" alt="You are using an outdated browser. For a faster, safer browsing experience, upgrade for free today." />
      </a>
    </div>
    <![endif]-->
<!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <link rel="stylesheet" media="screen" href="css/ie.css">
    <![endif]-->
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
					<c:choose>
						<c:when test = "${not empty user}">
						<li class = "current"><a href="viewGeography.do">Trend</a></li>
						<li><a href="home.do">Home</a></li>
						<li><a href="logout.do">Logout</a></li>
						</c:when>
						<c:otherwise>
						<li><a href="login.do">Login</a></li>
						<li class="current"><a href="index.do">Index</a></li>
						<li><a href="rank.do">Rank</a></li>
						</c:otherwise>
					</c:choose>
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
					<div class="box" style="padding-right:10%;text-align:left">
						<div class="cloud" style="border:none">
							<h3 style="padding-left:10%">Twitter Tag Cloud</h3>
							
							<script src="js/d3.v3.min.js"></script>
<script src="js/d3.layout.cloud.js"></script>
<script type="text/javascript">

var fill = d3.scale.category20();

var cityData = [], 
    width = 500, 
    height = 300;

var color = d3.scale.linear()
            .domain([0,1,2,3,4,5,6,10,15,20,100])
            .range(["#009100", "#007500", "#00bb00", "#c4c440", "#00a600", "#006000", "#808040", "#4f4f4f", "#0e0e0", "#8c8c00", "#00a600", "#f9f9f9"]);


d3.csv("js/tag.csv", function(data) {
    // build the list of city names
    data.forEach( function (d) {
        cityData.push({"tag" : d.tag, "count" : d.count});
    });

    d3.layout.cloud()
            .size([width, height])
            .words(cityData.map(function(d){
                return {text: d.tag, size: (5 + d.count / 200 * 40)};
            }))
            .rotate(function() { return ~~(Math.random() * 2) * 90; })
            .font("serif")
            .fontSize(function(d) { return d.size; })
            .on("end", draw)
            .start();

});
    
function draw(words) {
        d3.select(".cloud").append("svg")
                .attr("width", 617)
                .attr("height", 597)
                .attr("class", "wordcloud")
                .append("g")
                // without the transform, words words would get cutoff to the left and top, they would
                // appear outside of the SVG area
                .attr("transform", "translate(320,200)")
                .selectAll("text")
                .data(words)
                .enter().append("text")
                .style("font-size", function(d) { return d.size + "px"; })
                .style("fill", function(d, i) { return color(i); })
                .attr("transform", function(d) {
                    return "translate(" + [d.x, d.y] + ")rotate(" + d.rotate + ")";
                })
                .text(function(d) { return d.text; });
    }       
</script>
							
							
						</div>
					</div>
				</div>
				<div class="grid_4">
					<div class="box">
						<div class="inner maxheight" style="padding-left:0; text-align:left">
							<h4 style="padding-left:10%">Trends</h4>
							<ul style="padding-left: 10%;list-style:disc">
								<li ><a href="viewGeography.do">Geography
										Trend</a></li>
								<li ><a href="likeTimeChart.do">Time Trend</a></li>
								<li ><a href="viewTag.do">Tag Cloud</a></li>
							</ul>
							<h4 class="head1" style="padding-left:10%">Flickr Tag</h4>


							<div id="myCanvasContainer">
								<canvas width="300" height="300" id="myCanvas"></canvas>
							</div>
							<div id="tags">
								<ul>
									<li><a href="#" target="_blank">Pittsburgh</a></li>
									<li><a href="#">Snow</a></li>
									<li><a href="#">Night View</a></li>
									<li><a href="#">Pirates</a></li>
									<li><a href="#">Steeler</a></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--==============================footer=================================-->
		<footer>
			<div class="hor bg3"></div>
			<div class="container_12">
				<div class="grid_12">
					<div class="socials">
						<a href="#"></a> <a href="#"></a> <a href="#"></a>
						<div class="clear"></div>
					</div>
					<div class="copy">
						&copy; <span id="copyright-year"></span> | <a href="#">Privacy
							Policy</a> <br> Website designed by <a
							href="http://www.templatemonster.com/" rel="nofollow">TemplateMonster.com</a>
					</div>
				</div>
			</div>
		</footer>
	</div>
</body>
</html>