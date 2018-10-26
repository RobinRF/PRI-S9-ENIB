// Visitor.js

class Visitor extends THREE.Object3D {

	constructor(position, rotation, speed, angle) {
		super();
		this.position.set(position.x, position.y, position.z);
		this.rotation.set(rotation.x, rotation.y, rotation.z);
		this.speed = speed;
		this.angle = angle;
		this.observers = new Array();
	}

	setPosition(position) {
		this.position.set(position.x, position.y, position.z);
		this.notify();
	}

	setRotation(rotation) {
		this.rotation.set(rotation.x, rotation.y, rotation.z);
		this.notify();
	}

	getSpeed() {
		return this.speed;
	}

	setSpeed(speed) {
		this.speed = speed;
	}

	getAngle() {
		return this.angle;
	}

	setAngle(angle) {
		this.angle = angle;
	}

	// Should be private
	updateTarget() {
		this.rotation.x = this.position.x + Math.cos(this.angle);
		this.rotation.z = this.position.z - Math.sin(this.angle);
		this.notify();
	}

	goForward() {
		this.position.x +=  this.speed * Math.cos(this.angle) ; 
		this.position.z += -this.speed * Math.sin(this.angle) ; 
		this.updateTarget();
	}

	goBackward() {
		this.position.x -=  this.speed * Math.cos(this.angle) ; 
		this.position.z -= -this.speed * Math.sin(this.angle) ; 
		this.updateTarget();
	}

	goLeft() {
		this.angle += 0.05;
		this.updateTarget();
	}

	goRight() {
		this.angle -= 0.05;
		this.updateTarget();
	}

	goUp() {
		this.position.y += this.speed;
		this.rotation.y = this.position.y;
		this.notify();
	}

	goDown() {
		this.position.y -= this.speed;
		this.rotation.y = this.position.y;
		this.notify();
	}

	attach(observer) {
		this.observers.push(observer);
	}

	notify() {
		this.observers.forEach(function(item, index, array) {
			item.update();
		});
	}

}