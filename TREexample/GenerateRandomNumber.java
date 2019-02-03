package TREexample;

//********************************************************************
//  GenerateRandomNumber.java
//
//  Generate a random number between 0 and 1000
//********************************************************************

    class GenerateNumber {

// Note: If we changed the class definition to "public class RandNumber"
// then we would put this class definition in a separate file RandNumber.java

//  A randomly chosen number between 1 and 6.

        private final int MAX = 1000;  // maximum value

        private int currentValue;  // current value

        //-----------------------------------------------------------------
        //  Constructor: Sets the initial current value.
        //-----------------------------------------------------------------
        public GenerateNumber() {
            currentValue = 1;
        }

        // Alternate Constructor

        public GenerateNumber(int value) {
            currentValue = value;
        }

        //-----------------------------------------------------------------
        //  Generates random number and returns the result.
        //  Use of the 'min' parameter allows you to "juice" the chosen number
        //  (guarantee a minimum value)
        //-----------------------------------------------------------------
        public int generate(int min) {
            int range = (MAX - min) + 1;
            currentValue = (int)(Math.random() * range) + min;

            return currentValue;
        }

        //-----------------------------------------------------------------
        //  Current value mutator.
        //-----------------------------------------------------------------
        public void setCurrentValue (int value) {
            currentValue = value;
        }

        //-----------------------------------------------------------------
        //  Face value accessor.
        //-----------------------------------------------------------------
        public int getCurrentValue() {
            return currentValue;
        }

        // Returns a string representation of this die.
        public String toString() {
            String result = Integer.toString(currentValue);
            return result;
        }

    }

    /**
     *
     * @author cbrown
     */
    public class GenerateRandomNumber {

        //-----------------------------------------------------------------
        //  Creates a GenerateNumber object and generates a random number.
        //-----------------------------------------------------------------
        public static void main (String[] args) {
            GenerateNumber aNumber;

            aNumber = new GenerateNumber();

            int randomNumber = aNumber.generate(1);
            System.out.println ("Generated Number: " + randomNumber );

        }
    }
