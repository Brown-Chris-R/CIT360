package CollectionsExample;

import java.util.SortedMap;
import java.util.TreeMap;

public class TreeCollection {
    public static void main(String[] args) {
        // Creating a TreeMap
        SortedMap<String, String> fileExtensions  = new TreeMap<>();

        // Adding new key-value pairs to a TreeMap
        fileExtensions.put("python", ".py");
        fileExtensions.put("c++", ".cpp");
        fileExtensions.put("java", ".java");
        fileExtensions.put("cobol", ".cbl");
        fileExtensions.put("pascal", ".pas");
        fileExtensions.put("ruby", ".rb");
        fileExtensions.put("c", ".c");

        // This one will not be added since a TreeSet does not allow duplicate key values
        fileExtensions.put("cobol", ".cob");

        // This one WILL be added since a TreeSet does allow duplicate pair values
        fileExtensions.put("objective-c", ".c");

        // Printing the TreeMap (Output will be sorted based on keys)
        System.out.println(fileExtensions);
    }}
