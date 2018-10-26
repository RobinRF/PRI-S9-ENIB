// Controller.js

class Controller {

	constructor(visitor) {
		this.visitor = visitor;
		this.projector = new THREE.Projector();
	}

	keyDown(event) {
		switch(event.keyCode){
			case 33 :
				this.visitor.goUp();
				break;
			case 34 :
				this.visitor.goDown();
				break;
			case 37 :
				this.visitor.goLeft(); 
				break;
			case 38 :
				this.visitor.goForward();
				break;
			case 39 :
				this.visitor.goRight();
				break;
			case 40 :
				this.visitor.goBackward();
				break;
		}
	}

	mouseDown(event) {
		var vector = new THREE.Vector3((event.clientX / window.innerWidth)*2-1, -(event.clientY / window.innerHeight)*2+1, 0.5);
		this.projector.unprojectVector(vector, camera);
		var raycaster = new THREE.Raycaster(this.visitor.position, vector.sub(this.visitor.position).normalize());
		var intersects = raycaster.intersectObjects(listeIntersection);

		if (intersects.length > 0) {
			intersects[0].object.material.transparent = true
			pointeur.position.set(intersects[0].point.x,intersects[0].point.y,intersects[0].point.z) ;

			// Change visitor position
			var world = intersects[0].object.matrixWorld;
			var origin = new THREE.Vector3(0,0,0);
       		var ext = new THREE.Vector3(0,0,2);
	       	this.visitor.setPosition(ext.applyMatrix4(world))
	       	this.visitor.setRotation(origin.applyMatrix4(world))
		}
	}


}