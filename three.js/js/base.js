/*Dépreciée. Ne pas utiliser. Laissée pour des raisons de compatibilitées
Les methodes et attributs de groupe ont été ajouté dans THREE.Object3D*/
function creerGroupe(nom){
	var groupe  = new THREE.Group() ; 
	groupe.name = nom ; 
	return groupe ; 
}
	
/*
Génération du sol de la scène
entrée : nom       : (String) nom donné au mesh
		 largeur   : (int) largerur du sol
		 hauteur   : (int) longueur du sol
		 materiaux : (THREE.MeshLambertMaterial) texture du sol

sortie : THREE.Mesh contenant le sol
*/
function creerSol(nom,largeur,hauteur,materiau){
	var geo   = new THREE.PlaneGeometry(
					largeur,hauteur,
					Math.floor(largeur/10.0)+1, Math.floor(hauteur/10)+1) ; 
	var mesh  = new THREE.Mesh(geo,materiau) ; 
	mesh.name = nom ;
	mesh.rotation.x = - Math.PI / 2 ;
	return mesh ;   
}

/*
Génération d'une cloison
entrée : nom       : (String) nom donné au mesh
		 largeur   : (int) largerur de la cloison
		 hauteur   : (int) hauteur de la cloison
		 épaisseur : (int) épaisseur de la cloison
		 materiaux : (THREE.MeshLambertMaterial) texture de la cloison

sortie : THREE.Group contenant la cloison
*/
function creerCloison(nom,largeur, hauteur, epaisseur, materiau){
	var geo  = new THREE.BoxGeometry(largeur, hauteur, epaisseur) ; 
	var mesh = new THREE.Mesh(geo, materiau) ;
	var groupe = new THREE.Group() ; 
	groupe.name = nom ;
	groupe.add(mesh) ; 
	mesh.position.set(0,hauteur/2.0,0) ;  
	return groupe ;  	
}

/*
Génération d'une sphère
entrée : nom         : (String) nom donné au mesh
		 rayon       : (int) rayon de la sphère
		 subdivision : pas utilisé. laissé pour des raison de compabilitées.
		 materiaux   : (THREE.MeshLambertMaterial) texture de la sphère

sortie : THREE.Mesh contenant sphère
*/
function creerSphere(nom,rayon, subdivisions, materiau){
	var geo  = new THREE.SphereGeometry(rayon) ; 
	var mesh = new THREE.Mesh(geo, materiau) ; 
	mesh.name = nom ; 
	return mesh ;  
}

/*
Déprécié au profit de creerPoster1
*/
function creerPoster(nom,largeur, hauteur, nomImage, titre){
	var geo   = new THREE.PlaneGeometry(largeur, hauteur) ; 
	var mat   = creerLambertTexture(nomImage, 0xffffff) ; 
	var mesh  = new THREE.Mesh(geo, mat) ; 
	
	return mesh ;   
}

/*
Génération d'un poster (ou tableaux)
entrée : nom         : (String) nom donné au mesh
		 largeur     : (int) largeur du poster
		 hauteur     : (int) hauteur du poster
		 nomImage    : (String) chemin d'accès à l'image du poster
		 Titre       : (String) Titre affiché sous le poster
		 Description : (String) Déscription du poster. appelé par l'UI
		 radius      : (int) Rayon du nimbus du poster

sortie : (THREE.Group) poster à insérer dans la scène
*/
function creerPoster1(nom,largeur, hauteur, nomImage, titre, description,radius=5){
	var geo    = new THREE.PlaneGeometry(largeur, hauteur) ; 
	var mat    = creerLambertTexture(nomImage, 0xffffff) ; 
	var mesh   = new THREE.Mesh(geo, mat) ; 
	var dos    = new THREE.Mesh(geo, materiauBlanc) ; 
	var groupe = new THREE.Group() ; 
	dos.rotation.y = Math.PI ; 
	dos.position.z = -0.01 ; 
	mesh.position.z = 0.01 ; 
	mesh.titre= titre;
	mesh.description = description;
    listeIntersection.push(mesh) ; 

	
	groupe.add(mesh) ; 
	groupe.add(dos) ; 
	plaque=creerText(titre,largeur,hauteur)
	plaque.position.y = -hauteur*0.6
	groupe.add(plaque)
	var nimbus=new NimbusCylinder(camera.getObjectByName("FocusCone"),radius,groupe)
	groupe.add(nimbus)
	groupe.name  = nom ;
	groupe.description=description
	groupe.nimbus=nimbus;

	return groupe ;   
}

/*
Génération d'une plaque décriptive contenant le titre pour les poster 
entrée : Description : (String) texte contenu dans le canvas
		 largeur     : (int) largeur du poster
		 hauteur     : (int) hauteur du posterDescription
		 
sortie : (THREE.Mesh) plaque à insérer   
*/
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

	var geometry = new THREE.PlaneGeometry(largeur*0.9, hauteur*0.1) ; 
	texture = new THREE.CanvasTexture(canvas);
	var material = new THREE.MeshLambertMaterial({color:0xffffff,map:texture}) ;
	var mesh = new THREE.Mesh(geometry,material)
    return mesh;


}

/* 
Création de sources Lumineuses ponctuelle de la scène

entrée : position    : (THREE.Vector3) Position de la source
		 couleur     : (int) code coleur de la source
		 intensite   : (int) intensitée de la source
		 portee      : (int) portée de la source
		 attenuation : (int) attenuation de la source

sortie : (THREE.PointLight) source lumineuse à insérer dans la scène
*/

function creerSourcePonctuelle(position,couleur, intensite, portee, attenuation){
	var light = new THREE.PointLight(couleur,intensite,portee,attenuation) ; 
	light.position.copy(position) ; 
	return light ; 
}

/*
Création de la source lumineuse glolbale de la scène

Entrée : NA

Sortie : (THREE.HemisphereLight) source lumineuse à insérer dans la scène
*/
function creerSoleil(){
	var h = new THREE.HemisphereLight(0xffffbb,0x080820,1) ; 
	return h ; 
}



// =====================
// Création de matériaux
// =====================



var materiauBlanc  = creerLambert(0xffffff) ; 
var materiauRouge  = creerLambert(0xff0000) ;

function creerLambert(couleur){
  	var mat = new THREE.MeshLambertMaterial({color:couleur}) ; 
	return mat ; 
}

function creerLambertTexture(nomImage,couleur,nx,ny){
	var textureLoader = new THREE.TextureLoader() ; 
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
		pointeur.position.set(intersects[0].point.x,intersects[0].point.y,+intersects[0].point.z) ;
		var world = intersects[0].object.matrixWorld;
       	origin = new THREE.Vector3(0,0,0);
       	ext = new THREE.Vector3(0,0,2);
    	origin.applyMatrix4(world);
		ext.applyMatrix4(world);
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

	
                         



