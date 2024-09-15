# POC Your Car Your Way

Le POC contient la fonctionnalité de chat synchrone.
Il permet, pour une instance employé, de chatter avec plusieurs instances clients en même temps.

## Pré-requis

Node v20
Angular V18
Java v17
MySQL v8

## Installation et lancement du projet

- Cloner le dépôt en local
- Depuis le dossier backend, lancer le back avec maven: `mvn spring-boot:run`
- Depuis le dossier frontend, lancer le front avec: `ng serve`

- Ouvrir trois onglets sur localhost:4200
- Sélectionner le chat employé en premier
- Sélectionner sur les deux autres onglets chat client
- Envoyer un message depuis chaque chat client
- Répondre avec le chat employé

## Schéma de données

- Bien que non utilisé dans le POC, un schéma de données représentant l'état attendu de l'application peut être injecté en base de données
  via le script `init.sql` situé dans le dossier data
