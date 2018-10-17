// FirstPersonCamera

class FirstPersonCamera extends THREE.PerspectiveCamera {

	constructor(fov, aspect, near, far, visitor) {
		super(fov, aspect, near, far);
		this.visitor = visitor;
	}

	update() {
		this.position.set(this.visitor.position.x, this.visitor.position.y, this.visitor.position.z);
		this.rotation.set(this.visitor.rotation.x, this.visitor.rotation.y, this.visitor.rotation.z);
		this.lookAt(this.rotation);
	}
}