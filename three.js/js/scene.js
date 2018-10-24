var materiaux = {} ; 
var noeuds    = {} ;
var listeIntersection = [] ; 
var data= {} ;
var pointeur ; 
var mire ;
var url = "http://localhost:8020/index";

function init(grapheScene){
	width  = window.innerWidth ; 
	height = window.innerHeight ; 
	
	renderer = new THREE.WebGLRenderer({antialias:true}) ; 
	renderer.setSize(width, height) ; 
	document.body.appendChild(renderer.domElement) ; 

	camera = new THREE.PerspectiveCamera(75, width/height, 0.1, 1000.0) ;
	camera.position.set(0,1.7,0) ;  
	// camera.lookAt(new THREE.Vector3(0,1.5,0)) ; 

	controls = new KeyboardControls(camera) ; 

	scene = creerScene() ; 

	temps = 0  ; 
	clock = new THREE.Clock() ; 

	window.addEventListener("resize", function (){
		width  = window.innerWidth ; 
		height = window.innerHeight ; 
		camera.aspect = width / height ; 
		camera.updateProjectionMatrix() ; 
		renderer.setSize(width, height) ; 
	}) ; 

	document.addEventListener("mousedown", mouseDown, false) ; 
	// document.addEventListener("mousemove", mouseMove, false) ; 
	document.addEventListener("keydown",   keyDown, false) ; 
	document.addEventListener("keyup",     keyUp, false) ; 
}


function animate(){
	requestAnimationFrame(animate) ; 
	delta = clock.getDelta() ; 
	temps += delta ; 
	controls.update(delta) ; 
	renderer.render(scene,camera) ; 
}

// ======
// Parser
// ======

function fileReader(name){
	var xmlhttp = new XMLHttpRequest();
	
	xmlhttp.onreadystatechange = 	function() {
		console.log(this.status)
		if (this.readyState==4 && this.status==200) {
			data = JSON.parse(this.responseText);
			init("scene.config") ;
			animate() ; 
		}
		else if (this.readyState==4 && this.status==404) {
			document.getElementById("body").innerHTML="<H1>CA MARCHE PAS :'( </H1>"
		}

	};
	xmlhttp.open("GET", url, true);
	xmlhttp.send(); 
}


function parser(data,scene){

	// Création des matériaux
	// ----------------------

	console.log(data.materiaux)
	var _materiaux = data.materiaux ;
	console.log("vous etes ici")
	console.log(_materiaux) 
	var _mat ; 
	for(var i=0; i<_materiaux.length;i++){
		_mat = _materiaux[i] ; 
		materiaux[_mat.nom] = creerLambertTexture(_mat.url,0xffffff,_mat.nx,_mat.ny);
		
	} ; 

	// Création des posters

	var _posters = data.posters ; 
	var _poster, poster ; 
	for(var i = 0; i <_posters.length; i++){
		_poster = _posters[i] ; 
		poster = creerPoster1(_poster.cle, 
                                     _poster.largeur, _poster.hauteur, 
                                     //"assets/images/gala-2018/AG-1.jpg") ; 
				     _poster.url, _poster.titre, _poster.description) ; 
		noeuds[_poster.cle] = poster ; 
		scene.add(poster) ;   
	}

	// Création des cloisons

	var _cloisons = data.cloisons ; 
	var _cloison, cloison ; 
	for(var i = 0; i <_cloisons.length; i++){
		_cloison = _cloisons[i] ; 
		cloison = creerCloison(_cloison.cle, 
                                     _cloison.largeur, _cloison.hauteur, _cloison.epaisseur,
				     materiaux[_cloison.materiau]) ; 
		noeuds[_cloison.cle] = cloison ; 
		scene.add(cloison) ;   
	} ; 


	// Création du sol
	// ---------------

	var _sol      = data["sol"] ; 
	console.log(_sol) ; 
	var _taille   = _sol.taille || 100.0 ; 
	var _materiau = _sol.materiau  ; 
	var sol = creerSol("sol",_taille,_taille,materiaux[_materiau]) ;
	scene.add(sol) ; 
   

	// Placement des objets
	// --------------------

	var _poses = data.poses ; 
	var _pose ; 
	var noeud ;
	for(var i = 0; i < _poses.length; i++){
		_pose = _poses[i] ; 
		console.log(_pose.cle) ; 
		noeud = noeuds[_pose.cle] ; 
		noeud.position.set(_pose.x, _pose.y, _pose.z) ;
		noeud.rotation.y = _pose.angle*Math.PI/180 ; 
	}
}








function creerScene(){
			
	var scene = new THREE.Scene() ; 


	// Ajoût de sources lumineuses à la scène
	// ======================================

	var l1 = creerSourcePonctuelle(new THREE.Vector3(5,5,2),0xffffff,1,30,2) ; 
	scene.add(l1) ; 

	var l2 = creerSoleil() ; 
	scene.add(l2) ; 

	// Peuplement des objets de la scène
	// =================================

	var mat1 = creerLambert(0xff00ff) ; 
	var mat2 = creerLambert(0xffff00) ; 
	var mat3 = creerLambertTexture("assets/textures/sols_plafonds/sol.jpg",0xffffff,50,50) ; 


    	// Création d'un pointeur à utiliser pour visualiser un objet sélectionné
    	pointeur = creerSphere("pointeur",0.05,16, mat2) ; 
    	scene.add(pointeur) ; 

	var sph1 = creerSphere("sph1",0.5,16,mat2) ;
	placerXYZ(sph1,10,1.5,5) ;  
	parentDe(scene,sph1) ; 	

	var sph2 = creerSphere("sph2",0.5,16,mat3) ;
	placerXYZ(sph2,10,1.5,7) ;  
	parentDe(scene,sph2) ; 	

	parser(data,scene) ; 


	// FIN
	// =================================

	return scene ; 
}
