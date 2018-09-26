

// Scène contenant : 
//	- 1 source ponctuelle
//	- 1 sol
//	- 1 sphere

var data = {
	"materiaux" : [
			{	
			  "nom"     : "parquet1", 
			  "type"    : "lambertTexture",
			   "url"    : "assets/textures/sols_plafonds/parquet1.jpg",
			   "nx" : 10, "ny" : 10
			} ,
			{
			  "nom"     : "moquette", 
			  "type"    : "lambertTexture",
			   "url"    : "assets/textures/sols_plafonds/moquette.jpg",
			   "nx" : 10, "ny" : 10
			} ,
			{
			  "nom"     : "brique50", 
			  "type"    : "lambertTexture",
			   "url"    : "assets/textures/murs/bricks3.jpg",
			   "nx" : 10, "ny" : 5
			} ,
			{
			  "nom"     : "dante", 
			  "type"    : "lambertTexture",
			   "url"    : "assets/textures/murs/dante.jpg",
			   "nx" : 1, "ny" : 1
			} 


		      ] , 

	"sol" : {
			"taille"   : 50 , 
			"materiau" : "parquet1" 
		},

	"posters" : [
			{
				"cle" : "poster1",
				"nom" : "poster1",
				"url" : "assets/images/brest0/LaPorteNationale.jpg",
				"largeur" : 3.0,
				"hauteur" : 2.0
			},
			{
				"cle" : "poster2",
				"nom" : "poster2",
				"url" : "assets/images/brest0/LaRueDeSiam.jpg",
				"largeur" : 3.0,
				"hauteur" : 2.0
			},
			{
				"cle" : "poster3",
				"nom" : "poster3",
				"url" : "assets/images/brest0/LesGares.jpg",
				"largeur" : 3.0,
				"hauteur" : 2.0
			},
			{
				"cle" : "poster4",
				"nom" : "poster4",
				"url" : "assets/images/brest0/LesGlacisEtLaRueDeParis.jpg",
				"largeur" : 3.0,
				"hauteur" : 2.0
			},
			{
				"cle" : "poster5",
				"nom" : "poster5",
				"url" : "assets/images/brest0/LHotelDeVille.jpg",
				"largeur" : 3.0,
				"hauteur" : 2.0
			},
			{
				"cle" : "poster6",
				"nom" : "poster6",
				"url" : "assets/images/brest0/PlaceDesPortes.jpg",
				"largeur" : 3.0,
				"hauteur" : 2.0
			},
			{
				"cle" : "poster7",
				"nom" : "poster7",
				"url" : "assets/images/brest0/PontNational.jpg",
				"largeur" : 3.0,
				"hauteur" : 2.0
			}
		    ],

	"cloisons" : [

			{"cle":"cloison1","nom":"cloison1","largeur":10,"hauteur":4,"epaisseur":0.1,"materiau":"dante"},
			{"cle":"cloison2","nom":"cloison2","largeur":10,"hauteur":4,"epaisseur":0.1,"materiau":"brique50"}
		    ],

	"poses" : [
			{"cle" : "poster1", "pere" : "scene",
                         "x" : 10, "y" : 3, "z" : 0, "angle" : 0
			},
			{"cle" : "poster2", "pere" : "scene",
                         "x" : 6, "y" : 3, "z" : 0, "angle" : 0
			},
			{"cle" : "poster3", "pere" : "scene",
                         "x" : 2, "y" : 3, "z" : 0, "angle" : 0
			},
			{"cle" : "poster4", "pere" : "scene",
                         "x" : -2, "y" : 3, "z" : 0, "angle" : 0
			},
			{"cle" : "poster5", "pere" : "scene",
                         "x" : 10, "y" : 3, "z" : 5, "angle" : -Math.PI/2
			},
			{"cle" : "poster6", "pere" : "scene",
                         "x" : 10, "y" : 3, "z" : 10, "angle" : -Math.PI/2
			},
			{"cle" : "poster7", "pere" : "scene",
                         "x" : -5, "y" : 3, "z" : 5, "angle" : Math.PI/2
			},
			{"cle" : "cloison1", "pere" : "scene",
                         "x" : 10, "y" : 0, "z" : -5, "angle" : 0
			},
			{"cle" : "cloison2", "pere" : "scene",
                         "x" : -10, "y" : 0, "z" : -5, "angle" : 0
			}




		  ]
} ; 


var materiaux = {} ; 
var noeuds    = {} ;
var listeIntersection = [] ; 

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
		poster = creerPoster1(_poster.cle, 
                                     _poster.largeur, _poster.hauteur, 
                                     //"assets/images/gala-2018/AG-1.jpg") ; 
				     _poster.url) ; 
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
		noeud.rotation.y = _pose.angle ; 
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
