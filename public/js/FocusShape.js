// FocusShape.js

class FocusShape extends THREE.Object3D {

	constructor(observable) {
		super();
		this.observable = observable;
	}

	// Should be abstract
	isVisible(object) {
		throw "Methode isVisible is abstract"
	}

	// Should be abstract
	update() {
		throw "Methode isVisible is abstract"
	}
}