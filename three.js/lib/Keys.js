

function KeyboardControls(object){
	this.object = object ;

	this.vitesse = 2.0 ; 
	this.cap     = Math.PI ; 
	this.hauteur = 0.0 ; 
 
	this.position  = new THREE.Vector3(object.position.x, object.position.y, object.position.z) ; 
	this.direction = new THREE.Vector3(0.0,0.0,-1.0) ;  
	this.lookAt    = new THREE.Vector3(0.0,1.7,0.0) ; 

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
	this.projector = new THREE.Projector() ; 
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

}


KeyboardControls.prototype = {

	update : function(delta){
		if(this.moveLeft) {
			console.log("ON TOURNE A GAUCHE") ; 
			this.cap += Math.PI/360 ;  
			this.orienter() ; 
			console.log(this.direction); 
			
		}
		else if (this.moveRight){
			console.log("ON TOURNE A DROITE") ; 
			this.cap -= Math.PI/360 ;
			this.orienter() ; 
		}
		else if(this.moveUp) {
			console.log("ON TOURNE A GAUCHE") ; 
			this.hauteur += Math.PI/360 ;  
			this.orienter() ; 
			console.log(this.direction); 
			
		}
		else if (this.moveDown){
			console.log("ON TOURNE A DROITE") ; 
			this.hauteur -= Math.PI/360 ;
			this.orienter() ; 
		}
		else if(this.moveForward){ 

			var k = delta * this.vitesse ; 
		
			this.object.position.x += k * this.direction.x ;
			this.object.position.y += k * this.direction.y ;
			this.object.position.z += k * this.direction.z;

			this.lookAt.x = this.object.position.x + k * this.direction.x ; 
			this.lookAt.y = this.object.position.y + k * this.direction.y ; 
			this.lookAt.z = this.object.position.z + k * this.direction.z ;

		}	
		else if(this.moveBackward){ 

			var k = delta * this.vitesse ; 

			this.object.position.x -= k * this.direction.x ;
			this.object.position.y -= k * this.direction.y ;
			this.object.position.z -= k * this.direction.z;

			this.lookAt.x = this.object.position.x + k * this.direction.x ; 
			this.lookAt.y = this.object.position.y + k * this.direction.y ; 
			this.lookAt.z = this.object.position.z + k * this.direction.z ;

		}	
		else if (this.decelerer){
			this.vitesse = this.vitesse - 0.1 ; 
			if(this.vitesse < 0.0)
				this.vitesse = 0.0 ; 
		}
		else if (this.accelerer){
			this.vitesse = this.vitesse + 0.1 ; 
			if(this.vitesse > 10.0)
				this.vitesse = 10.0 ; 
		}
		else {

			

		}

	},

	orienter : function(){
		this.direction.x =  Math.cos(this.cap) ; 
		this.direction.z = -Math.sin(this.cap) ; 
		this.direction.y = Math.sin(this.hauteur) ; 

		this.direction.normalize() ; 
		this.direction.multiplyScalar(this.vitesse) ; 

		this.lookAt.x = this.object.position.x + 2*this.direction.x ; 
		this.lookAt.z = this.object.position.z + 2*this.direction.z ; 
		this.lookAt.y = this.object.position.y + 2*this.direction.y ;

		this.object.lookAt(this.lookAt) ; 
	},

	onMouseMove(event){ 
		event.preventDefault() ; 
		this.mouse.x =  (event.clientX  / window.innerWidth)*2 - 1 ; 
		this.mouse.y = -(event.clientY /  window.innerHeight)*2 + 1 ; 

		console.log(this.mouse.y) ; 

		if(this.mouse.x < -0.1){
			this.moveLeft  = true ;
			this.moveRight = false ; 

		} else if(this.mouse.x > 0.1){
			this.moveLeft  = false ;
			this.moveRight = true ; 
		} else {
			this.moveLeft  = false ;
			this.moveRight = false ;
		}

		if(this.mouse.y < -0.1){
			this.moveDown  = true ;
			this.moveUp = false ; 

		} else if(this.mouse.y > 0.1){
			this.moveDown  = false ;
			this.moveUp = true ; 
		} else {
			this.moveDown  = false ;
			this.moveUp = false ;
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
