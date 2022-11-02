# Front Paris Sportifs

## Introduction

[Énoncé](https://github.com/Neferith/example/blob/master/Exercice_Mobile_Natif_ParisSportifs.pdf)

L'exercice a été réalisé sur Android en Kotlin, via un pattern MVP.

## Librairies utilisés

L'application utilise plusieurs librairies :
* Retrofit : Pour les appels à l'API, tous les appels sont mises en cache. En cas d'absence d'internet, le cache sera automatiquement récupéré s'il existe.
* GSON : Pour le parsing du JSON.
* Coroutine : Pour les executions asynchrones.
* Dagger et Hilt : Pour l'injection des dépendances
* Glide pour le chargement des images
* Room : Pour stocker les leagues en base de données, c'est un élément secondaire car les données sont en caches. Mais j'ai fait le choix de l'intégrer pour pouvoir vérifier directement l'existence d'une league en base de données.
