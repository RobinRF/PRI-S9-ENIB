var http = require('http');

var express = require('express')
var app = express()

var port = process.env.PORT | 8020;

var router = express.Router()

var fs = require("fs");
var data = fs.readFileSync("scene.json");
content = JSON.parse(data);
// console.log(content)

router.get("/index", function(req, res) {
	res.header("Access-Control-Allow-Origin", "*");
  	res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
	res.json(content);
});

router.get("/images/:name", function(req, res) {
	var path = 'C:\\laragon\\www\\NodeJs\\images\\';
	var file = req.params.name + ".jpg";
	res.sendFile(path + file);
});

router.get("/fonts/:name", function(req, res) {
	var path = 'C:\\laragon\\www\\NodeJs\\fonts\\';
	var file = req.params.name + ".typeface.json";
	var data = fs.readFileSync(path + file);
	content = JSON.parse(data);
	res.header("Access-Control-Allow-Origin", "*");
  	res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
	res.json(content);
});

app.use("/", router);

app.listen(port);
