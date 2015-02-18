<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<body class="page1" id="top">
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
						<li><a href="viewGeography.do">Trend</a></li>
						<li><a href="rank.do">Rank</a></li>
						<li class="current"><a href="home.do">Home</a></li>
						<li><a href="logout.do">Logout</a></li>
						</c:when>
						<c:otherwise>
						<li><a href="login.do">Login</a></li>
						<li ><a href="index.do">Index</a></li>
						<li class="current"><a href="rank.do">Rank</a></li>
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