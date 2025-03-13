package net.codejava;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;


public class ManipulationMongo {
    MongoDBConnection connection=new MongoDBConnection();
    MongoClient mongoClient=connection.getMongoClient();

        public void showAllDBs(){
            Iterable<String> liste= mongoClient.listDatabaseNames();
            for (String string : liste) {
                System.out.println(string);
            }
        }
        public void createDb(String dbName){
            MongoDatabase database =mongoClient.getDatabase(dbName);
            database.createCollection("collection_init");
            Document document=new Document("_id","init");
            database.getCollection("collection_init").insertOne(document);

            if(isDbExist(dbName)){
                System.out.println(dbName + " created successfully");
            }
            else {
                System.out.println( " Faild To Create"+" "+ dbName);
            }
        }
        public void deleteDb(String dbName){

        }
        public void findAllCollections(String dbName ){

        }
        public void createCollection(String dbName,String collection){
            MongoDatabase database = mongoClient.getDatabase(dbName);
            database.createCollection(collection);
            if(isCollectionExist(dbName,collection)){
                System.out.println("The Collection "+collection + " created successfully");
            }
            else{
                System.out.println( " Faild To Create"+" "+ collection +" Collection");
            }



        }
        public void deleteCollection(String dbName,String collection){

        }

        //    ******    //
        public boolean isDbExist(String dbName){
            boolean isExist = false;
            for (String e : mongoClient.listDatabaseNames()){
                if(e.equals(dbName)){
                    isExist=true;
                }
            }
            return isExist;
        }
        public Boolean isCollectionExist(String dbName,String collection){
        MongoDatabase database = mongoClient.getDatabase(dbName);
        boolean isExist = false;
        for (String e : database.listCollectionNames()){
            if(e.equals(collection)){
                isExist=true;
            }
        }
        return isExist;

    }
        public void addDocument(String dbName,String collection,Document doc){
            MongoDatabase database = mongoClient.getDatabase(dbName);
            if(!isCollectionExist(dbName,collection)){
                createCollection(dbName,collection);
            }
            InsertOneResult result = database.getCollection(collection).insertOne(doc);
            if(result.getInsertedId() != null){
                System.out.println("Insertion Succesful");
            }
            else {
                System.out.println("Insertion Faild");
            }
        }
        public void deleteDocument(String dbName, String collection, String field, Object value) {
            if (!isDbExist(dbName)) {
                System.out.println(dbName + " database does not exist");
                return;
            }

            if (!isCollectionExist(dbName, collection)) {
                System.out.println("Collection " + collection + " does not exist");
                return;
            }

            MongoDatabase database = mongoClient.getDatabase(dbName);
            MongoCollection<Document> myCollection = database.getCollection(collection);
            DeleteResult result = myCollection.deleteOne(eq(field, value));
            if (result.getDeletedCount() > 0) {
                System.out.println("Document deleted successfully.");
            } else {
                System.out.println("No matching document found to delete.");
            }
        }

        public void updateDocument(String dbName, String collection, String queryField,
                                   Object queryValue, String updateField, Object updateValue) {
            if (!isDbExist(dbName)) {
                System.out.println(dbName + " database does not exist");
                return;
            }

            if (!isCollectionExist(dbName, collection)) {
                System.out.println("Collection " + collection + " does not exist");
                return;
            }

            MongoDatabase database = mongoClient.getDatabase(dbName);
            MongoCollection<Document> myCollection = database.getCollection(collection);

            Bson filter = eq(queryField, queryValue);
            Bson update = set(updateField, updateValue);

            UpdateResult result = myCollection.updateOne(filter, update);

            if (result.getModifiedCount() > 0) {
                System.out.println("Document updated successfully.");
            } else {
                System.out.println("No matching document found to update.");
            }
        }

       public void findAllDocuments(String dbName, String collection) {
        if (!isDbExist(dbName)) {
            System.out.println(dbName + " database does not exist");
            return;
        }

        if (!isCollectionExist(dbName, collection)) {
            System.out.println("Collection " + collection + " does not exist");
            return;
        }

        MongoDatabase database = mongoClient.getDatabase(dbName);
        MongoCollection<Document> myCollection = database.getCollection(collection);

        FindIterable<Document> documents = myCollection.find();

        System.out.println("Documents in collection " + collection + ":");
        int count = 0;
        for (Document doc : documents) {
            System.out.println(doc.toJson());
            count++;
        }

        if (count == 0) {
            System.out.println("Collection is empty");
        } else {
            System.out.println("Total documents: " + count);
        }
    }




}
