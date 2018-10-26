// NimbusShape.js

class NimbusShape extends THREE.Object3D {

	constructor(observable) {
		super();
		this.observable = observable;
		this.observable.attach(this);
		this.visible = false;
	}

	// Should be abstract
	update() {
		throw "Method NimbusShape.update is abstract";
	}

	// Should be abstract
	isVisible() {
		return this.visible;
	}

	getObservable() {
		return this.observable;
	}

	// Return the distance to the observable in XZ plan
	distanceToObservable() {
		var vectA = this.getWorldPosition();
		var vectB = this.observable.getWorldPosition();
		var dx = vectA.x - vectB.x;
		var dz = vectA.z - vectB.z;
		return Math.sqrt(dx*dx + dz*dz);
	}
	
}