// NimbusCylinder.js

class NimbusCylinder extends THREE.Object3D {

	constructor(observer, radius, target /*height*/) {
		super();
		this.observer = observer;
		this.observer.addTarget(target);
		//this.visible = false;
		this.radius = radius;
		//this.height = height;

	}

	
/*	update() {
		if (this.distanceToobserver() <= this.radius) {
			this.visible = true;
		}
		this.visible = false;
	}*/	
	isVisible() {
		return this.getDistanceToObserver() <= this.radius
	}

	getDistanceToObserver () {
		var pos1=this.getWorldPosition()
		var pos2=this.observer.getWorldPosition()
		return pos1.distanceTo(pos2)
	}
}