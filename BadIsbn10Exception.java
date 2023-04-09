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
public class BadIsbn10Exception extends Exception {
    /**
     *  default constructor
     */
    public BadIsbn10Exception(){
        super ("Bad 10 digits ISBN code");
    }

    /**
     *
     * @param S takes in a message that will be displayed as the error message
     */
    // takes in a message that'll be displaced per user's request
    public BadIsbn10Exception(String S){
        super(S);

        /**
         * @return String return the message above
         */
    }
    public String getMessage(){
        return super.getMessage();
    }
}
