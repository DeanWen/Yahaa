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
<link rel="stylesheet" href="css/login.css">
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
						<li class="current"><a href="login.do">Login</a></li>
					

                    </ul>
                </nav>
                <div class="clear"></div>
            </div>
            <div class="clear"></div>
        </div>
    </div>
    <div class="main">
<!--==============================Content=================================-->
	<div class="content"><div class="ic"> </div>
		<div class="container_12">
			<div class="grid_6">
				<div class=" left cl3 pos1">				
					<div class="login">
					   <form action="login.do" method="POST">
					       <h1>Login to Yahaa</h1>
					       <p><input class="text" placeholder="Name" name="username" type="text" /></p>
					       <p><input class="text" placeholder="Password" name="password" type="password" /></p>
					       <p class="submit"><input type="submit" name="commit" value="Login"></p>
					   </form>
					</div>
				</div>
			</div>
			
			<div class="grid_6">
				<div class=" right cl2">
				</br>
				</br>
				<form action="twitterLogin.do" method="POST">
					<button type="submit" style="border: 0; background: transparent; cursor:pointer;">
						<img src="images/twitter.png" width="320" height="60" alt="submit" />
					</button>
<!-- 					<input id="login" type="submit" name="action" value="Twitter Login" /> -->
				</form>
				</br>
				</br>
				<form action="flickrLogin.do" method="POST">
					<button type="submit" style="border: 0; background: transparent; cursor:pointer;">
						<img src="images/flickr.png" width="320" height="60" alt="submit" />
					</button>
<!-- 					<input id="login" type="submit" name="action" value="Flickr Login" /> -->
				</form>
				</div>
			</div>
			
		<div class="clear"></div>
			<div class="grid_12">
				<a href="#" class="round">Welcome Join</a>
			</div>
		</div>
	</div>
<jsp:include page="template-bottom.jsp" />