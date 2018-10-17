// HttpDataSource.js

class HttpDataSource extends DataSource {

	constructor(host, port) {
		super();
		this.host = host;
		this.port = port;
	}

	getHost() {
		return this.host;
	}

	setHost(host) {
		this.host = host;
	}

	getPort() {
		return this.host;
	}

	setPort(host) {
		this.host = host;
	}

	
	// Asynchronous fetch of the scene
	fetch(callback) {
		var xmlhttp = new XMLHttpRequest();
		xmlhttp.onreadystatechange = function() {
		    if (this.readyState == 4) {
		    	if (this.status == 200) {
		    		this.data = JSON.parse(this.responseText);
		    		callback();
		    	}
		        else {
		        	throw "Wrong http response ("+this.status+")";
		        }
		    }
		};

	}
}