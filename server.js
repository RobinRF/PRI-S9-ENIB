// Server.js

// Required packages
var express = require('express');
var mongoose   = require('mongoose');
var bodyParser = require('body-parser');
var app = express();

// Required models
//var SceneGraph = require('./src/models/sceneGraph')

// configure app to use bodyParser()
app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());

// Setup the database
//mongoose.connect('mongodb://127.0.0.1:27017/test');

// Port setup
var port = process.env.Port || 8080;


// Routes registering
// =============================
//app.use('/api', apiRouter);

// Static content middleware
// =============================
app.use(express.static('./public'));

// Start the server
// =============================
app.listen(port)
console.log("server running on port " + port);
