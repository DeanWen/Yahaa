<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Video</title>
    <meta name="format-detection" content="telephone=no" />
    <link rel="icon" href="images/favicon.ico">
    <link rel="shortcut icon" href="images/favicon.ico" />
    <link rel="stylesheet" href="css/style.css">
    <link href='http://fonts.googleapis.com/css?family=Economica:700' rel='stylesheet' type='text/css'>
    <script src="js/jquery.js"></script>
    <script src="js/jquery-migrate-1.1.1.js"></script>
    <script src="js/script.js"></script>
    <script src="js/jquery.ui.totop.js"></script>
    <script src="js/superfish.js"></script>
    <script src="js/jquery.equalheights.js"></script>
    <script src="js/jquery.mobilemenu.js"></script>
    <script src="js/jquery.easing.1.3.js"></script>
    <script src="js/processing.js"></script>
      <script src="js/init.js"></script>
    <script>
    $(document).ready(function(){
      $().UItoTop({ easingType: 'easeOutQuart' });
    })
    </script>


    <script type='text/javascript' src='http://www.google.com/jsapi'></script>
<script type='text/javascript'>google.load('visualization', '1', {'packages': ['geochart']});
google.setOnLoadCallback(drawVisualization);

  function drawVisualization() {
  var data = google.visualization.arrayToDataTable([
    ['State', 'Flickr & Twitter Sum'],
    ['Alabama', 20],
    ['Alaska', 3],
    ['Arizona', 2],
    ['Arkansas', 1],
    ['California', 50],
    ['Colorado', 4],
    ['Connecticut', 8],
    ['Delaware', 11],
    ['Florida', 19],
    ['Georgia', 0],
    ['Hawaii', 0],
    ['Idaho', 1],
    ['Illinois', 12],
    ['Indiana', 18],
    ['Lowa', 1],
    ['Kansas', 1],
    ['Kentucky', 1],
    ['Louisiana', 0],
    ['Maine', 0],
    ['Maryland', 23],
    ['Massachusetts', 29],
    ['Michigan', 31],
    ['Minnesota', 17],
    ['Mississippi', 2],
    ['Missouri', 7],
    ['Montana', 1],
    ['Nebraska', 0],
    ['Nevada', 0],
    ['New Hampshire', 1],
    ['New Jersey', 31],
    ['New Mexico', 1],
    ['New York', 71],
    ['North Carolina', 1],
    ['North Dakota', 0],
    ['Ohio', 4],
    ['Oklahoma', 13],
    ['Oregon', 18],
    ['Pennsylvania', 94],
    ['Rhode Island', 0],
    ['South Carolina', 0],
    ['South Dakota', 0],
    ['Tennessee', 0],
    ['Texas', 41],
    ['Utah', 22],
    ['Vermont', 0],
    ['Virginia', 16],
    ['Washington', 45],
    ['West Virginia', 6],
    ['Wisconsin', 9],
    ['Wyoming', 0]
  ]);
 
  var geochart = new google.visualization.GeoChart(
      document.getElementById('visualization'));
  geochart.draw(data, {width: 556, height: 347, region: "US", resolution: "provinces"});
}
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
              <a href="index.html">
                <img src="images/logo.png" alt="Your Happy Family">
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
               <li><a href="index.html">Home</a></li>
               <li><a href="index-1.html">About</a></li>
               <li><a href="index-2.html">Gallery</a></li>
               <li class="current"><a href="index-3.html">Video</a></li>
               <li><a href="index-4.html">Contacts</a></li>
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
              <div class="inner maxheight">
                <h3>Video</h3>
                <div id="visualization"></div>              
              </div>
            </div>
          </div>
          <div class="grid_4">
            <div class="box">
              <div class="inner maxheight">
                <h4>Top Videos</h4>
                <ul class="list">
                  <li class="li"><a href="viewGeography.do">Geography Trend</a></li>
                  <li class="li"><a href="viewTime.jsp">Time Trend</a></li>
                </ul>
                <h4 class="head1">Real Time</h4>
                <div class="block2">
                  
                  <script type="application/processing">
ParticleSystem ps;

void setup() {
  size(200,200);
  frameRate(80);
  colorMode(RGB,255,255,255,100);
  ps = new ParticleSystem(1,new Vector3D(width/2,height/2,0));
  smooth();
}

void draw() {
  background(245, 246, 245);
  ps.run();
  ps.addParticle();
}


// A simple Particle class

class Particle {
  Vector3D loc;
  Vector3D vel;
  Vector3D acc;
  float r;
  float timer;

  // One constructor
  Particle(Vector3D a, Vector3D v, Vector3D l, float r_) {
    acc = a.copy();
    vel = v.copy();
    loc = l.copy();
    r = r_;
    timer = 100.0;
  }
  
  // Another constructor (the one we are using here)
  Particle(Vector3D l) {
    acc = new Vector3D(0,0.07, 0);
    vel = new Vector3D(random(-1,1),random(-2,0),0);
    loc = l.copy();
    r = 10.0;
    timer = 100.0;
  }


  void run() {
    update();
    render();
  }

  // Method to update location
  void update() {
    vel.add(acc);
    loc.add(vel);
    timer -= 1.0;
  }

  // Method to display
  void render() {

    ellipseMode(CENTER);
    noStroke();
    fill(0, 128, 0,timer);
    ellipse(loc.x,loc.y,r,r);
  }
  
  // Is the particle still useful?
  boolean dead() {
    if (timer <= 0.0) {
      return true;
    } else {
      return false;
    }
  }
}


// A class to describe a group of Particles
// An ArrayList is used to manage the list of Particles 

class ParticleSystem {

  ArrayList particles;    // An arraylist for all the particles
  Vector3D origin;        // An origin point for where particles are birthed

  ParticleSystem(int num, Vector3D v) {
    particles = new ArrayList();              // Initialize the arraylist
    origin = v.copy();                        // Store the origin point
    for (int i = 0; i < num; i++) {
      particles.add(new Particle(origin));    // Add "num" amount of particles to the arraylist
    }
  }

  void run() {
    // Cycle through the ArrayList backwards b/c we are deleting
    for (int i = particles.size()-1; i >= 0; i--) {
      Particle p = (Particle) particles.get(i);
      p.run();
      if (p.dead()) {
        particles.remove(i);
      }
    }
  }

  void addParticle() {
    particles.add(new Particle(origin));
  }

  void addParticle(Particle p) {
    particles.add(p);
  }

  // A method to test if the particle system still has particles
  boolean dead() {
    if (particles.isEmpty()) {
      return true;
    } else {
      return false;
    }
  }

}



// Simple Vector3D Class 

public class Vector3D {
  public float x;
  public float y;
  public float z;

  Vector3D(float x_, float y_, float z_) {
    x = x_; y = y_; z = z_;
  }

  Vector3D(float x_, float y_) {
    x = x_; y = y_; z = 0f;
  }
  
  Vector3D() {
    x = 0f; y = 0f; z = 0f;
  }

  void setX(float x_) {
    x = x_;
  }

  void setY(float y_) {
    y = y_;
  }

  void setZ(float z_) {
    z = z_;
  }
  
  void setXY(float x_, float y_) {
    x = x_;
    y = y_;
  }
  
  void setXYZ(float x_, float y_, float z_) {
    x = x_;
    y = y_;
    z = z_;
  }

  void setXYZ(Vector3D v) {
    x = v.x;
    y = v.y;
    z = v.z;
  }
  public float magnitude() {
    return (float) Math.sqrt(x*x + y*y + z*z);
  }

  public Vector3D copy() {
    return new Vector3D(x,y,z);
  }

  public Vector3D copy(Vector3D v) {
    return new Vector3D(v.x, v.y,v.z);
  }
  
  public void add(Vector3D v) {
    x += v.x;
    y += v.y;
    z += v.z;
  }

  public void sub(Vector3D v) {
    x -= v.x;
    y -= v.y;
    z -= v.z;
  }

  public void mult(float n) {
    x *= n;
    y *= n;
    z *= n;
  }

  public void div(float n) {
    x /= n;
    y /= n;
    z /= n;
  }

  public void normalize() {
    float m = magnitude();
    if (m > 0) {
       div(m);
    }
  }

  public void limit(float max) {
    if (magnitude() > max) {
      normalize();
      mult(max);
    }
  }

  public float heading2D() {
    float angle = (float) Math.atan2(-y, x);
    return -1*angle;
  }

  public Vector3D add(Vector3D v1, Vector3D v2) {
    Vector3D v = new Vector3D(v1.x + v2.x,v1.y + v2.y, v1.z + v2.z);
    return v;
  }

  public Vector3D sub(Vector3D v1, Vector3D v2) {
    Vector3D v = new Vector3D(v1.x - v2.x,v1.y - v2.y,v1.z - v2.z);
    return v;
  }

  public Vector3D div(Vector3D v1, float n) {
    Vector3D v = new Vector3D(v1.x/n,v1.y/n,v1.z/n);
    return v;
  }

  public Vector3D mult(Vector3D v1, float n) {
    Vector3D v = new Vector3D(v1.x*n,v1.y*n,v1.z*n);
    return v;
  }

  public float distance (Vector3D v1, Vector3D v2) {
    float dx = v1.x - v2.x;
    float dy = v1.y - v2.y;
    float dz = v1.z - v2.z;
    return (float) Math.sqrt(dx*dx + dy*dy + dz*dz);
  }

}
</script><canvas width="320" height="240"></canvas>

                </div>
                <div class="block2">
                  <time datetime="2013-01-01"><span>30</span>Nov</time>
                  <div class="extra_wrapper">
                    <p class="col2"><strong><a href="#">Lorem ipsum dolor sit consectetur cingeit. In mollis s neque facilisis sit amettricies era rutrum </a></strong></p>Cras facilisis, nulla vel viverra uctorleo magna sodales felis, quis
                  </div>
                </div>
                <div class="block2">
                  <time datetime="2013-01-01"><span>12</span>Dec</time>
                  <div class="extra_wrapper">
                    <p class="col2"><strong><a href="#">Lorem ipsum dolor sit consectetur cingeit. In mollis s neque facilisis sit amettricies era rutrum </a></strong></p>Cras facilisis, nulla vel viverra uctorleo magna sodales felis, quis
                  </div>
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
              <a href="#"></a>
              <a href="#"></a>
              <a href="#"></a>
              <div class="clear"></div>
            </div>
            <div class="copy">
              &copy; <span id="copyright-year"></span> | <a href="#">Privacy Policy</a> <br>
              Website designed by <a href="http://www.templatemonster.com/" rel="nofollow">TemplateMonster.com</a>
            </div>
          </div>
        </div>
      </footer>
    </div>
  </body>
</html>