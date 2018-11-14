// FocusCone.js

class FocusCone extends THREE.Object3D {
	
	constructor(observer, angle, radius) {
		super();
		this.observer = observer;
		this.angle = angle;
        this.radius = radius;
		this.target=[]
		this.name="FocusCone"
    }
    
    addTarget(object) {
        this.target.push(object)
    }

    inSight(){
        console.log("aah")
        var objectSeen=[]
        this.target.forEach(function(element){
            if (this.canSee(object)){
                objectSeen.push(element)
            }
        })
        return objectSeen
    }

	canSee(object) {
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
		this.position.set(this.observer.position.x, this.observer.position.y, this.observer.position.z);
		this.rotation.set(this.observer.rotation.x, this.observer.rotation.y, this.observer.rotation.z);
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
		var angle = Math.atan(X/Z);
		if (Z>0){
			angle += Math.pi
		} 
		return angle;
	}
}