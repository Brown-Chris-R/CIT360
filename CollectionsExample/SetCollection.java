package CollectionsExample;

import java.util.LinkedHashSet;

public class SetCollection {
    public static void main(String[] args) {

        // ntBookSet will contain a subset of books in the New Testament
        LinkedHashSet<String> ntBookSet =
                new LinkedHashSet<String>();

        // Add the 4 Gospels to ntBookSet
        ntBookSet.add("Matthew");
        ntBookSet.add("Mark");
        ntBookSet.add("Luke");
        ntBookSet.add("John");

        // This will not add new element as Luke already exists  
        ntBookSet.add("Luke");
        // Add Acts to ntBookSet
        ntBookSet.add("Acts");

        //Show the size of ntBookSet - should be 5
        System.out.println("Size of the LinkedHashSet = " +
                ntBookSet.size());
        //Show the books in ntBookSet
        System.out.println("Original LinkedHashSet:" + ntBookSet);

        //Remove Acts from ntBookSet - this should succeed
        System.out.println("Removing Acts from the LinkedHashSet: " +
                ntBookSet.remove("Acts"));
        //Remove Romans from ntBookSet - this will not work because it wasn't in the set
        System.out.println("Trying to Remove Romans which is not " +
                "present: " + ntBookSet.remove("Romans"));

        //Check if Mark is in the set, this should be true
        System.out.println("Checking if Mark is present=" +
                ntBookSet.contains("Mark"));

        //Show the remaining books in the set, should just be the 4 Gospels
        System.out.println("Updated LinkedHashSet: " + ntBookSet);

    }
}

