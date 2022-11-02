# Front Paris Sportifs

## Introduction

L'exercice a été réalisé sur Android en Kotlin, via un pattern MVP comme demandé dans l'énoncé.

## Librairies utilisées

L'application utilise plusieurs librairies :
* Retrofit : Pour les appels à l'API, tous les appels sont mis en cache. En cas d'absence d'internet, le cache sera automatiquement récupéré s'il existe.
* GSON : Pour le parsing du JSON.
* Coroutine : Pour les exécutions asynchrones.
* Dagger et Hilt : Pour l'injection des dépendances
* Glide pour le chargement des images
* Room : Pour stocker les leagues en base de données, c'est un élément secondaire, car les données sont en caches. Mais j'ai fait le choix de l'intégrer pour pouvoir vérifier directement l'existence d'une league en base de données.

## Problèmes rencontrés

Le web service https://www.thesportsdb.com/api/v1/json/1/searchteams.php?t=Paris%20SG pour la fiche détaillée m'est inaccessible, car il faut clef Patreon. J'ai testé plusieurs valeurs 1 et 2 (Dans l'énoncé, il est noté API KEY : 1), mais rien ne fonctionne.
Néanmoins le web service search_all_teams semble retourner les champs requis par l'énoncé : 
- Nom de l’équipe
- Bannière
- Pays
- Championnat
- Description
Je me suis donc permis de simplement ignorer ce web service.

## Structure de l'application

L'application est découpé en plusieurs packages :
* Network : Connexion à l'API
* Dao : Connexion à la base de données local
* Di : Injection des dépendances
* Model : Modèle de données de l'application
* Repository : Classe pour récupérer le données, faisant le lien entre l'API et la base de données
* Ui : Contient les différentes activités, les fragments, la présentation...
* Utils : Méthodes utilitaires pour l'application.

L'application est décomposé en deux activités principales :

* L'activité de recherche : Elle est composé de deux fragments. Un fragment pour sélectionner une ligue (Automplétion ou recherche libre). Si l'utilisateur entre une ligue qui n'existe pas, il ne se passe rien. Sinon le paramètre est envoyé au second fragment qui lance la recherche d'équipe. Chaque fragment possède sa propre couche MVP. J'ai fait le choix de ne pas utiliser le composant ViewModel d'Android, même si j'aurais tout à fait pu faire une classe présentation qui est un ViewModel, mais je trouve cela incohérent. J'utilise néanmoins les livedata.
* L'activité fiche détaillée : C'est une simple vue qui affiche la bannière en haut de l'écran et les détails en dessous. 

## Test unitaires

J'ai réalisé des test unitaires sur deux classes uniquement. J'ai choisi de faire des tests sur la couche présentation et le repository, qui sont des couches centrales dans l'application.

## Amélioration possible

* Lorsque l'utilisateur rentre une liste inexistante, il ne se passe rien. Il faudrait ajouter un retour d'erreur.
* Tests unitaires, pour éviter les régressions, il serait important de couvrir une partie plus importante du code.
* Rendre l'UI plus jolie serait un gros plus.
