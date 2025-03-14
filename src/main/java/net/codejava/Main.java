package net.codejava;

public class Main {
    public static void main(String[] args) {
      ManipulationMongo manipulationMongo = new ManipulationMongo();



      // manipulationMongo.createDb("Cars");
        // manipulationMongo.deleteDB("Cars");
        manipulationMongo.findAllCollections("Cars");
       // manipulationMongo.createCollection("Cars","car");
       // manipulationMongo.deleteCollection("Cars","car");
        manipulationMongo.showCollection("Cars","car");
    }

}