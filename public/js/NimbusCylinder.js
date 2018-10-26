// NimbusCylinder.js

class NimbusCylinder extends NimbusShape {

	constructor(observable, radius, height) {
		super(observable);
		this.radius = radius;
		this.height = height;

	}

	// TODO : Check height also
	update() {
		if (this.distanceToObservable() <= this.radius) {
			this.visible = true;
			return;
		}
		this.visible = false;
	}
}