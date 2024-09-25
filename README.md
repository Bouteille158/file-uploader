# File Uploader

Ce projet contient le backend d'une application Java Spring Boot permettant la mise en ligne et le téléchargement de fichiers utilisateurs.

Une application frontend est disponible à l'adresse suivante : [file-uploader-front](https://github.com/Bouteille158/file-uploader-front).

## Fonctionnalités

- Téléversement de fichiers
- Liste des fichiers téléchargés
- Téléchargement de fichiers
- ~~Suppression de fichiers~~ (WIP)

## Prérequis

- Java 21 ou supérieur
- Maven 3.9.7 ou supérieur

## Installation

1. Clonez le dépôt :

   ```bash
   git clone https://github.com/Bouteille158/file-uploader
   cd file-uploader
   ```

2. Configurez l'application en copiant le fichier `application.properties.example` :

   ```bash
   cp src/main/resources/application.properties.example src/main/resources/application.properties
   ```

   Vous pouvez maintenant modifier les paramètres de configuration dans le fichier `application.properties`.

   Par défaut l'application utilise le driver H2 pour la base de données mais une configuration est aussi disponible pour PostgreSQL.

3. Installer les dépendances :

   ```bash
   ./mvnw install
   ```

4. Démarrez l'application :

   ```bash
   ./mvnw spring-boot:run
   ```

   Vous pouvez maintenant vérifier que l'API de l'application est bien lancée à l'adresse par défaut suivante : [http://localhost:8080](http://localhost:8080).

## Configuration

Vous pouvez configurer l'application en modifiant le fichier `application.properties` situé dans le dossier `src/main/resources`.

### Configuration de la base de données

Par défaut l'application utilise une base de données H2 en mémoire. Vous pouvez modifier la configuration pour utiliser une base de données PostgreSQL en modifiant les paramètres suivants :

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/nom_de_votre_base_de_donnees
spring.datasource.driverClassName=org.postgresql.Driver
spring.datasource.username=nom_utilisateur
spring.datasource.password=mot_de_passe
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```

Pensez bien à ajouter les identifiants de connexion à votre base de données PostgreSQL ainsi que l'URL de connexion.
