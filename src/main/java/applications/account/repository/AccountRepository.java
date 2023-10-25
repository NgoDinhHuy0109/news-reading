package applications.account.repository;
import applications.account.Account;

import com.google.gson.Gson;
import com.mongodb.client.*;
import constant.ApplicationConstant;
import org.bson.Document;
import org.bson.types.ObjectId;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AccountRepository implements IAccountRepository{
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;
    private void connectToCollection() {
        mongoClient = MongoClients.create(ApplicationConstant.MONGODB_CLIENT);
        database = mongoClient.getDatabase(ApplicationConstant.MONGODB_DATABASE);
        collection = database.getCollection("account");
    }

    private void closeConnection() {
        mongoClient.close();
    }
    Gson gson = new Gson();
    public Account createAccount(Account account) {
        //open connection
        connectToCollection();
        //add time
        Long current = System.currentTimeMillis();
        account.setCreatedAt(current);
        account.setUpdatedAt(current);
        Document accountDocument = Document.parse(gson.toJson(account));
        //insert to db
        collection.insertOne(accountDocument);
        //close connection
        closeConnection();
        return account;
    }
    @Override
    public Account getByUsername(String accountName) {
        connectToCollection();
        Map<String, Object> query = new HashMap<>();
        query.put("username", accountName);
        query.put("isDeleted", false);
        Document jsonQuery = new Document(query);
        FindIterable<Document> accountsDocument = collection.find(jsonQuery);
        Document result = accountsDocument.first();
        if (result == null) {
            return null;
        }
        Account account = documentToAccount(Objects.requireNonNull(result));
        closeConnection();
        return account;
    }

    public Account documentToAccount(Document document) {
        Account account = new Account();
        account.set_id(document.getObjectId("_id").toString());
        account.setUser_id(document.getString("user_id"));
        account.setUsername(document.getString("username"));
        account.setPassword(document.getString("password"));
        account.setCreatedAt(document.getLong("createdAt"));
        account.setUpdatedAt(document.getLong("updatedAt"));
        return account;
    }

}
