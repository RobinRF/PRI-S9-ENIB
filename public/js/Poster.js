// Poster.js

class Poster extends THREE.Group {
	
	constructor(name, width, height, image) {
		super();
		this.name = name;
		this.width = width;
		this.height = height;
		this.nimbus = null;

		var geometry = new THREE.PlaneGeometry(width, height);
		var material = creerLambertTexture(image , Poster.backgroudColor);
		this.frontMesh = new THREE.Mesh(geometry, material);
		this.frontMesh.position.z = 0.01;
		this.backMesh = new THREE.Mesh(geometry, material);
		this.backMesh.rotation.y = Math.PI; 
		this.backMesh.position.z = -0.01;

		this.add(this.frontMesh);
		this.add(this.backMesh);
	}

	setTitle(title) {
		this.title = title;
	}

	getTitle() {
		return this.title;
	}

	setDescription(description) {
		this.description = description;
	}

	getDescription() {
		return this.description;
	}

	getFrontMesh() {
		return this.frontMesh;
	}

	getBackMesh() {
		return this.backMesh;
	}

	attachNimbus(nimbus) {
		this.nimbus = nimbus;
		this.nimbus.position.set(this.position.x, this.position.y, this.position.z);
	}

	getNimbus() {
		return this.nimbus;
	}


}
