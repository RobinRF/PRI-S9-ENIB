// DataSource.js

class DataSource {

	constructor() {
		this.data = null;
	}

	setData(data) {
		this.data = data;
	}

	getData() {
		return this.data;
	}

	// Abstract
	// Asynchronous
	fetch(callback) {
		throw "Method is abstract"
	}

	// Abstract
	buildScene() {
		throw "Method is abstract"
	}

}