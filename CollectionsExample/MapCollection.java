package CollectionsExample;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MapCollection {
    public static void main(String[] args) {
        HashMap<String, Double> conversion = new HashMap<String, Double>();

        conversion.put("CentimetersToInches", 2.54);
        conversion.put("CentimetersToFeet",30.48);
        conversion.put("LitersToPints", 2.11338);
        conversion.put("LitersToQuarts", 1.05669);
        conversion.put("LitersToGallons", 0.264172);

        // Show Size (number of entries) of hashmap
        System.out.println("Size of Map: " + conversion.size());
        System.out.println();

        // List hashmap entries
        for (Map.Entry<String, Double> entry : conversion.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            System.out.println("key: " + key + " value: " + value);
        }
        System.out.println();

        // Convert 9.7 liters to Quarts by retrieving the conversion factor from the HashMap and performing the calculation.
        if (conversion.containsKey("LitersToQuarts")){
            double conversionFactor = conversion.get("LitersToQuarts");
            double convertedValue = conversionFactor * 9.7;
            System.out.println("9.7 Liters is equivalent to " + convertedValue + " quarts.");
        }

    }
}
