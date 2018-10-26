// FocusCone.js

class FocusCone extends FocusShape {
	
	constructor(observable, angle, radius) {
		super(observable);
		this.angle = angle;
		this.radius = radius;
	}

	isVisible(object) {
		var worldPosition = object.getWorldPosition();
		var localPosition = this.worldToLocal(worldPosition);
		var r = this.getCylindricRadius(localPosition);
		var theta = this.getCylindricAngle(localPosition);
		if(r < this.radius && (theta < this.angle/2 && theta > -this.angle/2)) {
			return true;
		}
		return false;
	}

	// Move cone orientation
	update() {
		this.position.set(this.observable.position.x, this.observable.position.y, this.observable.position.z);
		this.rotation.set(this.observable.rotation.x, this.observable.rotation.y, this.observable.rotation.z);
		this.lookAt(this.rotation);
	}

	// Return radius of cylindric coord
	getCylindricRadius(vector) {
		var Z = vector.z;
		var X = vector.x;
		return Math.sqrt(Z*Z + X*X)
	}

	// Return angle of cylindric coord
	getCylindricAngle(vector) {
		var Z = vector.z;
		var X = vector.x;
		return Math.atan(X/Z);
	}
}