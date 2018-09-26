

function KeyboardControls(object,scene){
	this.object = object ;
	this.scene  = scene ;

	

	this.vitesse = 2.0 ; 
	this.cap     = Math.PI ; 
	this.hauteur = 0.0 ; 
 
	this.position  = new THREE.Vector3(object.position.x, object.position.y, object.position.z) ; 
	this.Vc        = new THREE.Vector3(0.0, 0.0, 0.0) ; 
	this.Vd        = new THREE.Vector3(0.0, 0.0, 0.0) ; 

	this.direction = new THREE.Vector3(0.0,0.0,-1.0) ;  
	this.lookAt    = new THREE.Vector3(0.0,1.7,0.0) ; 

	this.gaze      = null ; 

	this.target = new THREE.Mesh(new THREE.SphereGeometry(0.05), new THREE.MeshPhongMaterial({color:0xff0000})) ;
	this.target.position.set(this.lookAt.x, this.lookAt.y, this.lookAt.z) ; 
	this.scene.add(this.target) ;  



	this.orienter() ; 
	
	this.domElement = document ;

	this.moveLeft     = false ; 
	this.moveRight    = false ;  
	this.moveForward  = false ;
	this.moveBackward = false ;
	this.moveUp       = false ;
	this.moveDown     = false ;
	this.accelerer    = false ;
	this.decelerer    = false ; 

	// Pour pointer
	// ============

	this.vector    = new THREE.Vector3() ; 
	this.mouse     = new THREE.Vector2() ; 
	
	this.domElement.addEventListener(
		'keydown',
		this.onKeyDown.bind(this),
		false) ; 

	this.domElement.addEventListener(
		'keyup',
		this.onKeyUp.bind(this),
		false) ; 

	this.domElement.addEventListener(
		'mousemove',
		this.onMouseMove.bind(this),
		false) ; 

	this.domElement.addEventListener(
		'mousedown',
		this.onMouseDown.bind(this),
		false) ; 

}


KeyboardControls.prototype = {

	update : function(delta){

		// Placement
		if(this.gaze != null){
		
		}


		// Déplacement latéral sur la gauche
		if(this.moveLeft) {

			
		}
		// Déplacement latéral sur la droite
		else if (this.moveRight){

		}
		else if(this.moveUp) {

		}
		// Eloignement
		else if (this.moveDown){

		}

		// Rapprochement
		else if(this.moveForward){ 



		}	
		else if(this.moveBackward){ 



		}	
		else if (this.decelerer){

		}
		else if (this.accelerer){

		}
		else {	

		}

	},

	orienter : function(){
		this.direction.x =  Math.cos(this.cap) ; 
		this.direction.z = -Math.sin(this.cap) ; 
		this.direction.y = Math.sin(this.hauteur) ; 


		this.lookAt.x = this.object.position.x + 2*this.direction.x ; 
		this.lookAt.z = this.object.position.z + 2*this.direction.z ; 
		this.lookAt.y = this.object.position.y + 2*this.direction.y ;

		this.object.lookAt(this.lookAt) ; 
	},


	onMouseMove : function(evt){

	},

	onMouseDown : function(evt){
		var x =  (evt.clientX / window.innerWidth)*2 - 1 ; 
		var y = -(evt.clientY / window.innerHeight)*2 + 1 ; 
		var z = 0.5 ; 

		var vector = new THREE.Vector3(x,y,z)  ; 

		vector = vector.unproject(camera) ;

		var raycaster =  new THREE.Raycaster(camera.position, vector.sub(camera.position).normalize()) ; 

		var intersects = raycaster.intersectObjects(cliquables) ; 

		if(intersects.length > 0){
			console.log(intersects[0].point) ; 
			this.target.position.set(intersects[0].point.x, intersects[0].point.y,intersects[0].point.z) ; 
			this.gaze = (this.target.position).clone() ; 
		}

	},

	onKeyDown : function(event){
		console.log(event.keyCode) ; 
		switch(event.keyCode){
			case 37 : // left
				console.log("LEFT") ; 
				this.moveLeft = true ; 
				break ;
			case 38 : // up
				this.moveForward = true ; 
				break ; 
			case 39 : // right
				this.moveRight = true ;
				break ; 
			case 40 : // up
				this.moveBackward = true ; 
				break ; 
			case 81 : // right
				this.accelerer = true ;
				break ; 
			case 87 : // up
				this.decelerer = true ; 
				break ; 
		}

	},

	onKeyUp : function(event){
		switch(event.keyCode){
			case 37 : // left
				console.log("LEFT") ; 
				this.moveLeft = false ; 
				break ; 
			case 38 : // up
				this.moveForward = false ; 
				break ; 
			case 39 : // right
				this.moveRight = false ;
				break ; 
			case 40 : // up
				this.moveBackward = false ; 
				break ; 
			case 81 : // right
				this.accelerer = false ;
				break ; 
			case 87 : // up
				this.decelerer = false ; 
				break ; 
		}

	}



}
