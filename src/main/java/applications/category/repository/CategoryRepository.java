package applications.category.repository;

import applications.category.Category;
import com.google.gson.Gson;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import constant.ApplicationConstant;
import org.bson.Document;

public class CategoryRepository implements ICategoryRepository{
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    private void connectToCollection() {
        mongoClient = MongoClients.create(ApplicationConstant.MONGODB_CLIENT);
        database = mongoClient.getDatabase(ApplicationConstant.MONGODB_DATABASE);
        collection = database.getCollection("category");
    }

    private void closeConnection() {
        mongoClient.close();
    }

    Gson gson = new Gson();

    public Category createCategory(Category category) {
        //open connection
        connectToCollection();

        //add time
        Long current = System.currentTimeMillis();
        category.setCreatedAt(current);
        category.setUpdatedAt(current);
        Document categoryDocument = Document.parse(gson.toJson(category));

        //insert to db
        collection.insertOne(categoryDocument);

        //close connection
        closeConnection();
        return category;
    }

}
