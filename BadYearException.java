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
public class BadYearException extends Exception{
    /**
     * default constructor
     */
    public BadYearException(){
        super ("Year not in range");
    }

    /**
     *
     * @param S takes in a message that will be displayed as the error message
     */
    // takes in a message that'll be displaced per user's request

    /**
     *
     * @param S returns the message above
     */
    public BadYearException(String S){
        super(S);

    }
    public String getMessage(){
        return super.getMessage();
    }
}
