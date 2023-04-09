//-------------------------------------------------------
//Assignment {3}
//Written by: {Shanvin Luo Student ID:40248485 } and {Atai Askarov Student ID:40248327}
//For COMP 249 Section {Section QQ} - Winter 2023
//--------------------------------------------------------

/**
 * Written by: {Shanvin Luo Student ID:40248485 } and {Atai Askarov Student ID:40248327}
 * For COMP 249 Section {Section QQ} - Winter 2023 Assignment {2}
 * Due 11:59 PM – Monday, March 27, 2023
 */
public class TooManyFieldsException extends Exception {
    /**
     *
     * @param line stores the actual entry that is being read from the file
     * @param repository is the file from which current entries are being taken
     */
    public TooManyFieldsException(String line, String repository){
        super(  "syntax error in file: " + repository + "\n" +
                "===================================" + "\n" +
                "Too Many Fields!"+ "\n" +
                "Record: " + line + "\n");
    }

    /**
     *
     * @param message is the user's creative endeavor
     * @param line stores the actual entry that is being read from the file
     * @param repository is the file from which current entries are being taken
     */
    public TooManyFieldsException(String message, String line, String repository){
        super(  "syntax error in file: " + repository + "\n" +
                "===================================" + "\n" +
                "Error: " + message + "\n" +
                "Record: " + line + "\n");
    }
    public String getMessage(){
        return super.getMessage();
    }
}
