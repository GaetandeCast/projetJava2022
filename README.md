# Projet Empreinte Carbone

Ce projet a pour but de recommander de manière simplifiée à l'utilisateur une manière de réduire son empreinte carbone en analysant son impact vis-à-vis de différents postes de consommation comme le logement, les transports ou encore l'alimentation.

## Description

Ce projet Java contient deux packages :
* consocarbone
* utilisateur

le package consocarbone contient la classe mère ConsoCarbone et ses classes filles relatives aux postes de consommation carbone

le package utilisateur contient la classe Utilisateur qui importe le package consocarbone, la classe IO et une Exception Fichier

La classe Utilisateur fournit la fiche de consommation carbone de l'utilisateur et les méthodes de recommendation

La classe IO implémente un menu interactif

On pourra se placer dans le dossier du projet pour exécuter le programme.

## Pour commencer

### pré-requis

* JVM et JRE à jour et compatible avec votre OS afin de pouvoir compiler et éxecuter le programme

### Installation

* télécharger le dossier ProjetJava contenant les packages et la classe Main

### compiler et exécuter le programme
* une fois dans le dossier contenant les packages et la classe Main

* compiler avec
```
javac Main.class
```
* exécuter avec
```
java Main
```

## Aide

Commande à taper en cas d'erreur non traitée pouvant survenir
```
0 #fais quitter le programme
```

Format du fichier à fournir (voir le fichier test.txt pour un exemple)

<br>nombre de vehicules
<br>(nom vehicule, age, distance, taille (petit/grand)) x nbVehicule (sur une ligne differente à chaque fois)
<br>nombres de logements
<br>(nom logement, surface, classe) x nbLogement (sur une ligne differente à chaque fois)
<br>nombre de vols long courrier, nombre de vols moyen courrier, nombre de vols petit courrier
<br>taux de repas à base de boeuf en %, taux de repas vegetariens en %
<br>montant des dépenses anuelle en bien de consommation



## Auteurs 

Gaétan De castellane

Martin Youssef
