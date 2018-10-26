var data = null;
var materiaux = {} ; 
var noeuds    = {} ;
var listeIntersection = [] ;
var posterList = [];


var pointeur ; 
var mire ; 

// ======
// Parser
// ======

function parser(data,scene){

	// Création des matériaux
	// ----------------------

	var _materiaux = data.materiaux ; 
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
		poster = new Poster(_poster.cle, _poster.largeur, _poster.hauteur, _poster.url);
		poster.setTitle(_poster.nom);
		poster.setDescription(_poster.description);
		listeIntersection.push(poster.getFrontMesh());
		posterList.push(poster);
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
		//console.log(_pose.cle) ; 
		noeud = noeuds[_pose.cle] ;

		noeud.position.set(_pose.x, _pose.y, _pose.z) ; 
		noeud.rotation.y = _pose.angle;
		// Add nimbus
		if(noeud instanceof Poster) {
			noeud.attachNimbus(new NimbusCylinder(visitor, 4, 10));
		}
	}
}


function creerScene(dataSource){
			
	scene = new THREE.Scene() ; 


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

	dataSource.fetch(function(data) {
		parser(data, scene);
	});
}