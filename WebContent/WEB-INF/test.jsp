<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
    <title>D3 CSV</title>

</head>
<body>

<script src="js/d3.v3.min.js"></script>
<script src="js/d3.layout.cloud.js"></script>
<script type="text/javascript">

var fill = d3.scale.category20();

var cityData = [], 
    width = 650, 
    height = 500;

var color = d3.scale.linear()
            .domain([0,1,2,3,4,5,6,10,15,20,100])
            .range(["#ddd", "#ccc", "#bbb", "#aaa", "#999", "#888", "#777", "#666", "#555", "#444", "#333", "#222"]);


d3.csv("js/tag.csv", function(data) {
    // build the list of city names
    data.forEach( function (d) {
        cityData.push({"tag" : d.tag, "count" : d.count});
    });

    d3.layout.cloud()
            .size([550, 400])
            .words(cityData.map(function(d){
                return {text: d.tag, size: (5 + d.count / 200 * 50)};
            }))
            .rotate(function() { return ~~(Math.random() * 2) * 90; })
            .font("Impact")
            .fontSize(function(d) { return d.size; })
            .on("end", draw)
            .start();

});
    
function draw(words) {
        d3.select("body").append("svg")
                .attr("width", 850)
                .attr("height", 350)
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
</body>
</html>