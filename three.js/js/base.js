
function creerGroupe(nom){
	var groupe  = new THREE.Group() ; 
	groupe.name = nom ; 
	return groupe ; 
}
	

function creerSol(nom,largeur,hauteur,materiau){
	var geo   = new THREE.PlaneGeometry(
					largeur,hauteur,
					Math.floor(largeur/10.0)+1, Math.floor(hauteur/10)+1) ; 
	var mesh  = new THREE.Mesh(geo,materiau) ; 
	mesh.name = nom ;
	mesh.rotation.x = - Math.PI / 2 ;
	return mesh ;   
}

function creerCloison(nom,largeur, hauteur, epaisseur, materiau){
	var geo  = new THREE.BoxGeometry(largeur, hauteur, epaisseur) ; 
	var mesh = new THREE.Mesh(geo, materiau) ;
	var groupe = new THREE.Group() ; 
	groupe.name = nom ;
	groupe.add(mesh) ; 
	mesh.position.set(0,hauteur/2.0,0) ;  
	return groupe ;  	
}


function creerSphere(nom,rayon, subdivisions, materiau){
	var geo  = new THREE.SphereGeometry(rayon, subdivisions, subdivisions) ; 
	var mesh = new THREE.Mesh(geo, materiau) ; 
	mesh.name = nom ; 
	return mesh ;  
}


function creerPoster(nom,largeur, hauteur, nomImage, titre){
	var geo   = new THREE.PlaneGeometry(largeur, hauteur) ; 
	var mat   = creerLambertTexture(nomImage, 0xffffff) ; 
	var mesh  = new THREE.Mesh(geo, mat) ; 
	
	return mesh ;   
}

function creerPoster1(nom,largeur, hauteur, nomImage, titre, description){
	var geo    = new THREE.PlaneGeometry(largeur, hauteur) ; 
	var mat    = creerLambertTexture(nomImage, 0xffffff) ; 
	var mesh   = new THREE.Mesh(geo, mat) ; 
	var dos    = new THREE.Mesh(geo, materiauBlanc) ; 
	var groupe = new THREE.Group() ; 
	/*loader.load( '/assets/fonts/helvetiker_regular.typeface.json', function ( font ) {

	var geometry = new THREE.TextGeometry( titre , {
		font: font,
		size: 80,
		height: 5,
		curveSegments: 12,
		bevelEnabled: true,
		bevelThickness: 10,
		bevelSize: 8,
		bevelSegments: 5
	} );
	groupe.add(font);
} );*/
	//var text = new THREE.Mesh(textgeometry, materiauBlanc);
   // text.position.y = -1;

	dos.rotation.y = Math.PI ; 
	dos.position.z = -0.01 ; 
	mesh.position.z = 0.01 ; 
	//mesh.name = nom ;
	mesh.titre= titre;
	mesh.description = description;
    listeIntersection.push(mesh) ; 

	
	groupe.add(mesh) ; 
	groupe.add(dos) ; 
	plaque=creerText(titre,largeur,hauteur)
	plaque.position.y = -hauteur*0.6
	groupe.add(plaque)
	var nimbus=new NimbusCylinder(camera.getObjectByName("FocusCone"),5,groupe)
	groupe.add(nimbus)
	groupe.name  = nom ;
	groupe.description=description
	groupe.nimbus=nimbus;

	return groupe ;   
}

function creerText(description,largeur,hauteur){
	canvas = document.createElement('canvas')
	context = canvas.getContext('2d');
	canvas.width=1000
	canvas.heigth=5
	context.font = '80pt Arial';
	context.fillStyle = 'white';
	context.fillRect(0, 0, canvas.width - 0, canvas.height - 0);
	context.fillStyle = 'black';
    context.textAlign = "center";
    context.textBaseline = "middle";
    context.fillText(description, canvas.width / 2, canvas.height / 2);
	//context.fillStyle = 'white'
	//context.fillRect(0, 0, canvas.width, canvas.height);
	//context.fillStyle = 'blue';
	//
	//context.textAlign = "center";
    //context.textBaseline = "middle";
	//context.fillText(description, canvas.width / 2, canvas.height / 2);
	//context.scale(0.01,0.01)
	//document.body.appendChild(canvas) 

	var geometry = new THREE.PlaneGeometry(largeur*0.9, hauteur*0.1) ; 
	texture = new THREE.CanvasTexture(canvas);
	var material = new THREE.MeshLambertMaterial({color:0xffffff,map:texture}) ;
	var mesh = new THREE.Mesh(geometry,material)
    return mesh;


}

// ===================
// Création de sources
// ===================

function creerSourcePonctuelle(position,couleur, intensite, portee, attenuation){
	var light = new THREE.PointLight(couleur,intensite,portee,attenuation) ; 
	light.position.copy(position) ; 
	return light ; 
}

function creerSoleil(){
	var h = new THREE.HemisphereLight(0xffffbb,0x080820,1) ; 
	return h ; 
}



// =====================
// Création de matériaux
// =====================

var textureLoader = new THREE.TextureLoader() ; 

var materiauBlanc  = creerLambert(0xffffff) ; 
var materiauRouge  = creerLambert(0xff0000) ;

function creerLambert(couleur){
  	var mat = new THREE.MeshLambertMaterial({color:couleur}) ; 
	return mat ; 
}

function creerLambertTexture(nomImage,couleur,nx,ny){
	var texture = textureLoader.load(nomImage) ; 
	var mat = new THREE.MeshLambertMaterial({color:couleur,map:texture}) ; 
	nx = nx ||   1 ; 
	ny = ny ||   1 ; 
	mat.map.wrapS = THREE.RepeatWrapping ;
	mat.map.wrapT = THREE.RepeatWrapping ;
	mat.map.repeat.set(nx,ny) ; 
	return mat ; 
}


// ======================
// Traitements des meshes
// ======================

function placerXYZ(mesh,x,y,z){
	mesh.translateX(x) ; 
	mesh.translateY(y) ; 
	mesh.translateZ(z) ; 
}

function orienterY(mesh,y){
	mesh.rotateY(y) ; 
}

function parentDe(pere,fils){
	pere.add(fils) ; 
}




// =============================
// Sélection par lancer de rayon
// =============================

var projector   = new THREE.Projector() ;
var listeIntersection = [] ;  
//var ext = null;
//var origin = null;
var mouseClicked = false;
var ext = new THREE.Vector3()
var origin = new THREE.Vector3()

function mouseDown(event){
	var vector = new THREE.Vector3(
			 (event.clientX / window.innerWidth)*2-1,
                        -(event.clientY / window.innerHeight)*2+1,
			0.5) ; 
	projector.unprojectVector(vector, camera) ; 

	var raycaster = new THREE.Raycaster(
				camera.position, 
				vector.sub(camera.position).normalize()) ; 
	var intersects = raycaster.intersectObjects(listeIntersection) ; 

	if(intersects.length > 0){
		mouseClicked = true;
		intersects[0].object.material.transparent = true ; 
		alert("titre du poster : " + intersects[0].object.titre +"\ndescription : "+intersects[0].object.description) ; 
		//alert( intersects[0].point.x+" , "+intersects[0].point.y+" , "+intersects[0].point.z) ; 
		pointeur.position.set(intersects[0].point.x,intersects[0].point.y,+intersects[0].point.z) ;
		var world = intersects[0].object.matrixWorld;
		//console.log(camera.position)
		//camera.position.set(intersects[0].point.x,intersects[0].point.y,+intersects[0].point.z)
		//camera.object.position.set(intersects[0].point.x,intersects[0].point.y,+intersects[0].point.z)
		//console.log(camera.position)
		// camera.matrixWorld=world;
		// console.log(camera.matrixWorld);
		// camera.translateX(2);
		// console.log(intersects[0].object)
		// camera.cible=intersects[0].object.position;
		// vector = camera.getWorldDirection();
		// angle = Math.atan2(vector.x,vector.z);
       	origin = new THREE.Vector3(0,0,0);
       	ext = new THREE.Vector3(0,0,2);
    	origin.applyMatrix4(world);
		ext.applyMatrix4(world);
       	//ext = ext;
       	//origin = origin; 
	}
}

var oldX = 0
var deltaX ; 

function mouseMove(event){


}

function keyDown(event){
	mouseClicked=false;
	switch(event.keyCode){
		case 33 : // HAUT
			controls.plusHaut = true ; 
			break ; 
		case 34 : // BAS
			controls.plusBas = true ;
			break ; 
		case 37 : // GAUCHE
			controls.aGauche = true ; 
			break ; 
		case 38 : // HAUT
			controls.enAvant = true ;
			break ; 
		case 39 : // DROITE
			controls.aDroite = true ;
			break ; 
		case 40 : // BAS
			controls.enArriere = true ;
			break ; 
	}
}

function keyUp(event){
	switch(event.keyCode){
		case 33 : // HAUT
			controls.plusHaut = false ; 
			break ; 
		case 34 : // BAS
			controls.plusBas = false ;
			break ; 
		case 37 : // GAUCHE
			controls.aGauche = false ; 
			break ; 
		case 38 : // HAUT
			controls.enAvant = false ;
			break ; 
		case 39 : // DROITE
			controls.aDroite = false ;
			break ; 
		case 40 : // BAS
			controls.enArriere = false ;
			break ; 
	}
}

var KeyboardControls = function(object){
	this.object    = object ; 

	this.position  = new THREE.Vector3(1,1.7,5) ; 

	this.angle     = 0.0 ; 
	this.direction = new THREE.Vector3(1,0,0) ; 
	this.cible     = new THREE.Vector3(2,1.7,5) ; 

	this.vitesse   = 2.0 ; 

	this.plusHaut  = false ; 
	this.plusBas   = false ; 
	this.enAvant   = false ; 
	this.enArriere = false ; 
	this.aGauche   = false ; 
	this.aDroite   = false ; 
}


KeyboardControls.prototype.update = function(dt){


	if(this.plusHaut)
		this.position.y += this.vitesse * dt ; 

	if(this.plusBas)
		this.position.y -= this.vitesse * dt ; 

	if(this.aGauche)
		this.angle += 0.05 ; 


	if(this.aDroite)
		this.angle -= 0.05 ; 

	if(this.enAvant){
		this.position.x +=  this.vitesse * dt * Math.cos(this.angle) ; 
		this.position.z += -this.vitesse * dt * Math.sin(this.angle) ;  
	}

	if(this.enArriere){
		this.position.x -=  this.vitesse * dt * Math.cos(this.angle) ; 
		this.position.z -= -this.vitesse * dt * Math.sin(this.angle) ;  
	}
	
	this.object.position.copy(this.position) ; 

	this.direction.set(Math.cos(this.angle),0.0,-Math.sin(this.angle)) ; 
	

	if(mouseClicked) {
		this.object.position.set(ext.x,ext.y,ext.z);
		this.position.set(ext.x,ext.y,ext.z);
		this.cible.set(origin.x,origin.y,origin.z);
		vector = camera.getWorldDirection();
		this.angle = -Math.atan2(vector.z,vector.x);

	} else {
		this.cible.set(this.position.x + Math.cos(this.angle), 
						this.position.y, 
						this.position.z - Math.sin(this.angle))	
		 
	} 

	this.object.lookAt(this.cible) ; 
	
}

	
                         



