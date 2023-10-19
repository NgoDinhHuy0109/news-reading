package applications.category.repository;

import applications.category.Category;
import com.google.gson.Gson;
import com.mongodb.client.*;
import constant.ApplicationConstant;
import org.bson.Document;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryRepository implements ICategoryRepository {
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
    @Override
    public Long countCategoryByName(String categoryName) {
        //Connect to mongodb
        connectToCollection();
        // count category name existed
        Map<String, Object> query = new HashMap<>();
        query.put("categoryName", categoryName);
        Document jsonQuery = new Document(query);
        Long result = collection.countDocuments(jsonQuery);
        //close mongodb
        closeConnection();
        return result;
    }
    @Override
    public List<Category> getAll() {
        connectToCollection();
        Map<String, Object> query = new HashMap<>();
        query.put("isDeleted", false);
        Document jsonQuery = new Document(query);
        FindIterable<Document> categoriesDocument = collection.find(jsonQuery);
        List<Category> categoryList = new ArrayList<>();

        for (Document categoryDocument : categoriesDocument) {
            Category category = documentToCategory(categoryDocument);
            categoryList.add(category);
        }
        closeConnection();
        return categoryList;
    }

    public Category documentToCategory(Document document) {
        Category category = new Category();
        category.set_id(document.getObjectId("_id").toString());
        category.setCategoryName(document.getString("categoryName"));
        category.setDescription(document.getString("description"));
        return category;
    }
}
