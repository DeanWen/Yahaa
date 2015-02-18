<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
					
						
						
						<li><a href="index.do">Browse</a></li>
						<li><a href="rank.do">Rank</a></li>
						<li  class="current"><a href="register.do">Register</a></li>
				
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
		<form action="setPassword.do" method="POST">
			<table>
				<tbody>
					<tr>
						<th>User Name</th>
						<td class="center"><input class="text" placeholder="Name"
							name="username" type="text" value="${sessionScope.user.screen_Name}" /></td>
					</tr>
					<tr>
						<th>Password</th>
						<td class="center"><input class="text" placeholder="Password"
							name="password" type="password" /></td>
					</tr>
					<tr>
						<td class="center" colspan="2"><input id="login"
							type="submit" name="action" value="Done" /></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</div>
<jsp:include page="template-bottom.jsp" />