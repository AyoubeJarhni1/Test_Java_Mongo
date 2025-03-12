package net.codejava;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;

public class ManipulationMongo {
    MongoDBConnection connection=new MongoDBConnection();
    MongoClient mongoClient=connection.getMongoClient();

    public void findAllDBs(){
        Iterable<String> liste= mongoClient.listDatabaseNames();
        for (String string : liste) {
            System.out.println(string);
        }
    }

public void createDb(String dbName){
    MongoDatabase database =mongoClient.getDatabase(dbName);
    MongoCollection<Document> collection = database.getCollection("sampleCollection");
    System.out.println(dbName + " created successfully");
}

    public void deleteDb(){

    }
public void findAllCollections(String db ){

}
public void createCollections(){

}
public void deleteCollections(){

}
public void showCollections(){}
public void addDocument(){

}
public void deleteDocument(){

}
public void updateDocument(){

}


}
