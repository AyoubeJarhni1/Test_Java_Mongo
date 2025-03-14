package net.codejava;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class ManipulationMongo {
    MongoDBConnection connection=new MongoDBConnection();
    MongoClient mongoClient=connection.getMongoClient();

    public void findAllDBs(){
        Iterable<String> liste= mongoClient.listDatabaseNames();
        for (String string : liste) {
            System.out.println(string);
        }
    }

    private boolean isDbExist(String dbName) {
        Iterable<String> liste = mongoClient.listDatabaseNames();
        for (String db : liste) {
            if (db.equals(dbName)) {
                return true;
            }

        }
        return false;
    }


    public void createDatabase(String dbName){
    MongoDatabase db=mongoClient.getDatabase(dbName);
    if (!isDbExist(dbName)){
        db.createCollection("collection1");
        db.getCollection("collection1").insertOne(new Document());
        System.out.println(dbName +
                " created succefully");
    }
        System.out.println("Database already exists");
    }

    public void deleteDB(String dbName){
        if (isDbExist(dbName)){
            MongoDatabase db=mongoClient.getDatabase(dbName);
            for (String collection : db.listCollectionNames() ) {
                db.getCollection(collection).drop();
            }
            System.out.println(dbName+ " deleted succefully");
        }
        else {
            System.out.println("Database " + dbName + " does not exist");
        }

    }

    public void findAllCollections(String dbName ){
      MongoDatabase db=mongoClient.getDatabase(dbName);
      if (!isDbExist(dbName)){
          System.out.println(dbName + " does not exist");
      }
      for (String collection : db.listCollectionNames() ) {
          System.out.println(collection);
      }
    }

    public boolean isCollectionExist(String database, String collectionName) {
        MongoDatabase db = mongoClient.getDatabase(database);
        for (String name : db.listCollectionNames()) {
            if (name.equals(collectionName)) {
                return true;
            }
        }
        return false;
    }

    public void createCollection(String database, String collectionName){
        MongoDatabase db=mongoClient.getDatabase(database);
        if (!isDbExist(database)){
            System.out.println(database + " does not exist");
        }
        if (!isCollectionExist(database,collectionName)){
            db.createCollection(collectionName);
            System.out.println( "Collection " + collectionName + " created succefully");
        }
        else{
            System.out.println("Collection " + collectionName + " already exists");
        }
    }

    public void deleteCollection(String database, String collectionName){
        MongoDatabase db=mongoClient.getDatabase(database);
        if (!isDbExist(database)){
            System.out.println(database + " does not exist");
        }

        if (isCollectionExist(database,collectionName)){
            db.getCollection(collectionName).drop();
            System.out.println( "Collection " + collectionName + " deleted succefully");
        }
        else{
            System.out.println("Collection " + collectionName + " does not exist");
        }
    }

    public void showCollection(String dbName , String collection) {
        MongoDatabase db=mongoClient.getDatabase(dbName);
        if(!isDbExist(dbName)){
            System.out.println(dbName + " does not exist");
            return;
        }

        if (!isCollectionExist(dbName,collection)){
        System.out.println(dbName + " does not exist");
            return;
        }
        findDocuments(dbName,collection);
    }

    public void findDocuments(String dbName , String collection) {
        MongoDatabase db=mongoClient.getDatabase(dbName);
       if (!isDbExist(dbName)){
           System.out.println(dbName + " does not exist");
           return;
       }
       if (!isCollectionExist(dbName,collection)){
           System.out.println(collection + " Collection  does not exist");
           return;
       }
       MongoCollection<Document> mongoCollection=db.getCollection(collection);
       Iterable<Document> documents=mongoCollection.find();
       for (Document document : documents) {
           System.out.println(document.toJson());
       }
    }


    }