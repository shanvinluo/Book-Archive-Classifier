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
public class MissingFieldException extends Exception{
    /**
     *
     * @param line stores the actual entry that is being read from the file
     * @param repository is the file from which current entries are being taken
     */
    // repository is the file from which current entries are being taken
    // line stores the actual entry that is being read from the file
    public MissingFieldException(String line, String repository){
        super(  "syntax error in file: " + repository + "\n" +
                "===================================" + "\n" +
                "Missing Field!"+ "\n" +
                "Record: " + line + "\n");
    }

    /**
     *
     * @param message is the user's creative endeavor
     * @param line stores the actual entry that is being read from the file
     * @param repository is the file from which current entries are being taken
     */
    // repository is the file from which current entries are being taken
    // line stores the actual entry that is being read from the file
    public MissingFieldException(String message, String line, String repository){
        super(  "syntax error in file: " + repository + "\n" +
                "===================================" + "\n" +
                "Error: " + message + "\n" +
                "Record: " + line + "\n");
    }
    public String getMessage(){
        return super.getMessage();
    }
}
