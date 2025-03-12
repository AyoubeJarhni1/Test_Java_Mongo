package net.codejava;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MongoDBConnection {

    private static MongoClient mongoClient;



    MongoDBConnection() {}

    public static MongoClient getMongoClient() {
        if (mongoClient == null) {
            String uri = "mongodb://localhost:27017";
            try {
                mongoClient = MongoClients.create(uri);
                System.out.println("Connexion établie avec MongoDB!");
            } catch (Exception e) {
                System.err.println("Erreur de connexion : " + e.getMessage());
            }
        }
        return mongoClient;
    }



    public static void main(String[] args) {
        try {
            MongoClient mongoClient = MongoDBConnection.getMongoClient();
            MongoDatabase database = mongoClient.getDatabase("tweets");
            System.out.println("Base de données sélectionnée : " + database.getName());

        } catch (Exception e) {
            System.err.println("Erreur lors de l'accès à la base de données : " + e.getMessage());
        }
    }
}
