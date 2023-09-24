package applications.user.repository;

import applications.user.User;
import com.google.gson.Gson;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import constant.ApplicationConstant;
import org.bson.Document;
public class UserRepository implements IUserRepository{
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    private void connectToCollection() {
        mongoClient = MongoClients.create(ApplicationConstant.MONGODB_CLIENT);
        database = mongoClient.getDatabase(ApplicationConstant.MONGODB_DATABASE);
        collection = database.getCollection("user");
    }

    private void closeConnection() {
        mongoClient.close();
    }

    Gson gson = new Gson();

    public User createUser(User user) {
        //open connection
        connectToCollection();

        //add time
        Long current = System.currentTimeMillis();
        user.setCreatedAt(current);
        user.setUpdatedAt(current);
        Document userDocument = Document.parse(gson.toJson(user));

        //insert to db
        collection.insertOne(userDocument);

        //close connection
        closeConnection();
        return user;
    }
}
