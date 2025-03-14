package net.codejava;

public class Main {
    public static void main(String[] args) {
      ManipulationMongo manipulationMongo = new ManipulationMongo();



      // manipulationMongo.createDb("Cars");
        // manipulationMongo.deleteDB("Cars");
        manipulationMongo.findAllCollections("Cars");
        manipulationMongo.showCollection("Cars","collection1");
       // manipulationMongo.createCollection("Cars","car");
       // manipulationMongo.deleteCollection("Cars","car");

    }

}