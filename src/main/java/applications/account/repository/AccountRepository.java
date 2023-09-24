package applications.account.repository;
import applications.account.Account;

import com.google.gson.Gson;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import constant.ApplicationConstant;
import org.bson.Document;
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
}
