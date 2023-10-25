package applications.user.repository;

import applications.account.Account;
import applications.user.User;
import com.google.gson.Gson;
import com.mongodb.client.*;
import com.mongodb.client.result.InsertOneResult;
import constant.ApplicationConstant;
import org.bson.BsonValue;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
        InsertOneResult result = collection.insertOne(userDocument);
        BsonValue _id = result.getInsertedId();
        if(_id == null) {
            return null;
        }
        Map<String, Object> query = new HashMap<>();
        query.put("_id", _id);
        query.put("isDeleted", false);
        Document jsonQuery = new Document(query);
        FindIterable<Document> userFounded = collection.find(jsonQuery);
        User createdUser = documentToUser(Objects.requireNonNull(userFounded.first()));
        //close connection
        closeConnection();
        return createdUser;
    }

    @Override
    public User getUserById(String idUser) {
        connectToCollection();

        // Parse the idUser String into an ObjectId
        ObjectId userId = new ObjectId(idUser);

        Map<String, Object> query = new HashMap<>();
        query.put("_id", userId);
        query.put("isDeleted", false);
        Document jsonQuery = new Document(query);
        FindIterable<Document> userDocument = collection.find(jsonQuery);
        Document result = userDocument.first();

        // Check if the result is null to avoid a NullPointerException
        if (result == null) {
            closeConnection();
            return null;
        }

        User user = documentToUser(result);
        closeConnection();
        return user;
    }
    public User documentToUser(Document document) {
        User user = new User();
        user.set_id(document.getObjectId("_id").toString());
        user.setUserName(document.getString("userName"));
        user.setEmail(document.getString("email"));
        user.setDescription(document.getString("description"));
        return user;
    }
}
