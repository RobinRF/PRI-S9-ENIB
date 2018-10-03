var http = require('http');

var express = require('express')
var app = express()

var port = process.env.PORT | 8020;

var router = express.Router()

var fs = require("fs");
var data = fs.readFileSync("scene.json");
content = JSON.parse(data);
// console.log(content)

router.route("/index").get(function(req, res) {
	res.header("Access-Control-Allow-Origin", "*");
  	res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
	res.json(content);
});

app.use("/", router);

app.listen(port);


