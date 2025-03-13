package net.codejava;

import org.bson.Document;

public class Main {
    public static void main(String[] args) {
      ManipulationMongo manipulationMongo = new ManipulationMongo();

        // Créer une base de données de test
        String dbName = "test_db";
        System.out.println("\n--- Création de la base de données " + dbName + " ---");
        manipulationMongo.createDb(dbName);

        // Créer une collection
        String collectionName = "test_collection";
        System.out.println("\n--- Création de la collection " + collectionName + " ---");
        manipulationMongo.createCollection(dbName, collectionName);

        // Ajouter plusieurs documents
        System.out.println("\n--- Ajout de documents ---");
        Document doc1 = new Document("name", "Salma")
                .append("age", 25)
                .append("city", "Rabat");

        Document doc2 = new Document("name", "Ilham")
                .append("age", 30)
                .append("city", "Casa");

        Document doc3 = new Document("name", "Layla")
                .append("age", 28)
                .append("city", "Rabat");

        manipulationMongo.addDocument(dbName, collectionName, doc1);
        manipulationMongo.addDocument(dbName, collectionName, doc2);
        manipulationMongo.addDocument(dbName, collectionName, doc3);

        // Ajouter la méthode findAllDocuments
        System.out.println("\n--- Affichage des documents avant les modifications ---");
        manipulationMongo.findAllDocuments(dbName, collectionName);

        // Tester updateDocument
        System.out.println("\n--- Test de la mise à jour de document ---");
        manipulationMongo.updateDocument(dbName, collectionName, "name", "Salma", "age", 31);
        manipulationMongo.updateDocument(dbName, collectionName, "name", "Layla", "city", "Bordeaux");

        // Afficher les documents après la mise à jour
        System.out.println("\n--- Affichage des documents après la mise à jour ---");
        manipulationMongo.findAllDocuments(dbName, collectionName);

        // Tester deleteDocument
        System.out.println("\n--- Test de la suppression de document ---");
        manipulationMongo.deleteDocument(dbName, collectionName, "name", "Layla");

        // Afficher les documents après la suppression
        System.out.println("\n--- Affichage des documents après la suppression ---");
        manipulationMongo.findAllDocuments(dbName, collectionName);

        // Tester la suppression d'un document qui n'existe pas
        System.out.println("\n--- Test de la suppression d'un document inexistant ---");
        manipulationMongo.deleteDocument(dbName, collectionName, "name", "Ahmed");


    }

}