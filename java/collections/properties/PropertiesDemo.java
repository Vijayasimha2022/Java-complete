package collections.properties;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesDemo {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Properties dbConfig = new Properties();

        dbConfig.setProperty("database.url","jdbc:mysql://localhost:3306/mydb");
        dbConfig.setProperty("database.user", "admin_user");
        dbConfig.setProperty("database.password", "securePass123");
        dbConfig.setProperty("connection.timeout", "5000");

        System.out.println(dbConfig);

        System.out.println("connecting to db :"+dbConfig.getProperty("database.url"));

        // Storing to a .txt file
        // We use a FileOutputStream pointing to the desired file name
        // The second argument is a comment header that appears at the top of the file
        dbConfig.store( new FileOutputStream("./java/collections/properties/app-settings.txt"), "Data base configuration");

        dbConfig.storeToXML( new FileOutputStream("./java/collections/properties/app-settings.xml"), "Data base configuration");



    }
}
