package CAMSv2;


// change to interface
/**
 * 
 * The DataBase abstract class provides a blueprint for database classes in the CAMSv2 system.
 * It defines two key operations that all database classes must implement: loadToCSV and writeToCSV.
 * The loadToCSV method is responsible for loading data from a CSV file into the database.
 * The writeToCSV method is responsible for writing the current state of the database to a CSV file.
 * This class follows the Singleton design pattern to ensure that only one instance of each database class exists in the system.
 * @author Benjamin Fernandez
 * @since 13-11-2023
 */
public abstract class DataBase{
    // Singleton

    public abstract void loadToCSV();
    public abstract void writeToCSV();
}
