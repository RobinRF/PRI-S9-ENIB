// HttpDataSource.js

class HttpDataSource extends DataSource {

	constructor(uri, host, port) {
		super();
		this.host = host;
		this.port = port;
		this.uri = uri;
		this.data = null;
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

	getUri() {
		return this.uri;
	}

	setUri(uri) {
		this.uri = uri;
	}

	getData() {
		return this.data;
	}

	
	// Asynchronous fetch of the scene
	fetch(callback) {
		var url = null;
		if (this.host == undefined) {
			url = this.uri;
		} else {
			url = "http://" + this.host + ":" + this.port + "/" + this.uri;
		}

		var xmlhttp = new XMLHttpRequest();
		xmlhttp.onreadystatechange = function() {
		    if (this.readyState == 4) {
		    	if (this.status == 200) {
		    		this.data = JSON.parse(this.responseText);
		    		callback(this.data);
		    	}
		        else {
		        	throw "Http response ("+this.status+")";
		        }
		    }
		};

		xmlhttp.open("GET", url, true);
		xmlhttp.send();
	}
}