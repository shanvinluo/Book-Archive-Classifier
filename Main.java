import java.io.*;
//import java.sql.SQLOutput;

import java.util.NoSuchElementException;
import java.util.Scanner;
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
import java.io.*;
import java.util.Scanner;
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
/**
 * * This code reads and sorts through an archive of documentations. In the sorting process, entries that are not syntactically
 *  nor semantically sound get discarded from the pool and written into separate documents, containing either syntactically or semantically
 *  incorrect entries.
 *  Ordering the entries based on genres, the documentations get distributed into individual files and then serialized
 *  in order to upload into respective binary files.
 *  Furthermore, an interactive representation of the archive is presented that allows for the user to see the files distinguished by genre.
 *  The presentation of entries in each respective file can be moderated by an input of a number. The number regulates the quantity of outputs
 *  to be outputed. Positive and negative numbers are used to prompt output of a quantity. The difference is that the negative sign
 *  requests the last inputs whereas the positive prompt the next number of entries.
 */
public class Main {

    /**
     *
     * @param line
     * @param genres
     * @param repository
     * @throws TooFewFieldsException
     * @throws TooManyFieldsException
     * @throws UnknownGenreException
     * @throws MissingFieldException
     */
    private static void error_thrower(String line, String[] genres, String repository) throws TooFewFieldsException, TooManyFieldsException, UnknownGenreException, MissingFieldException {
        // Splits the string with consideration for the possibility of commas inside the quoted title
        String[] linearray = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        // The  known_genres string stores coma separated genre codes from the String[] genre array.
        // This allows for a proper identification of unknown genres
        //*****************************
        String known_genres = "";
        for (int i1 = 0; i1 < genres.length; i1++) {
            known_genres += genres[i1] + ",";
        }
        //****************************

        // This sequence of if, else if and else statements is responsile for identifying the syntax errors
        // The syntax errors comprise Unknown genre, Too many fields, Too few fiels, and a Missing Field
        // By splitting the string that represents an entry, we can identify those that contain too many or too few fields
        // with the process of elimination.
        // Does the entry contain more than 6 fields. Too many Fields, then. Less? Too few.
        // Exactly 6? Then, the outcome will result in either Unknown genre Exception or a Missing Field
        // The methods does not return anything as it throws an exception that interrupts the natural sequence of the program.
        // Jumping from the the origin of the thrown exception to its respective catch method, anything that is in between will get skipped.
        // Thus the distrubition of syntatically correct and incorrect files occurs as those get written into separate files.
        if (linearray.length > 6)
            throw new TooManyFieldsException(line, repository);
        else if (linearray.length < 6)
            throw new TooFewFieldsException(line, repository);
        /*
        In order to properly display the number of missing fields, int count is initiated.
        the missing_fields array with length 6 contains "true" values an indecies where fields are missing.
         */
        else {

            int count = 0;
            boolean[] missing_fields = new boolean[6];

            if (!known_genres.contains(linearray[4])) {
                throw new UnknownGenreException(line, repository); //checks whether the genre is known
            }

            for (int i = 0; i < linearray.length; i++) {
                if (linearray[i].equals("")) {
                    missing_fields[i] = true; // if there is an empty string in the split entry, the boolean array will contain a true value
                }
            }
            for (int i = 0; i < missing_fields.length; i++) {
                if (missing_fields[i] == true) {
                    count++;    // counts the number of true values
                }
            }
            /*
            This piece of code makes sure the syntax is nice and neat
             */
            String Syntax = "";
            String s = "";
            if (count > 0) {
                if (count > 1) {
                    Syntax = "are";
                    s = "s";
                } else if (count == 1)
                    Syntax = "is";

                throw new MissingFieldException("There " + Syntax + " " + count + " Missing Field" + s, line, repository);
            }
        }
    }


    private static void do_part1() {
        File collection = new File("src/Assg3_Needed_Files (1)/part1_input_file_names.txt");
        File errors = new File("syntax_error_file.txt");

        BufferedReader RepositoryReader = null;
        PrintWriter pw = null;
        Scanner CollectionReader = null;
        String[] newArray = null;

        String[] fields = {"title", "author", "price", "isbn", "genre", "year"}; //stores the order in which entries are provided
        String[] known_genres = {"CCB", "HCB", "MTV", "MRB", "NEB", "OTR", "SSM", "TPA"}; // stores the known genres
        String[] file_names = {"Cartoon_Comics.csv.txt", "Hobbies_Collectibles.csv.txt",
                "Movies_TV_Books.csv.txt", "Music_Radio_Books.csv.txt",
                "Nostalgia_Eclectic_Books.csv.txt", "Old_Time_Radio_Books.csv.txt",
                "Sports_Sports_Memorabilia.csv.txt", "Trains_Planes_Automobiles.csv.txt"}; // stores the file names to be filled

        int numOfFiles;
        int[] counts = new int[file_names.length]; // contains the number of entries contained in each genre file
        String[][] books = new String[file_names.length][10];
        String line; // initialized to contain the file names taken from part1_input_files.txt
        try {
            // initializing the input and output streams
            CollectionReader = new Scanner(new FileInputStream(collection));
            pw = new PrintWriter(new FileOutputStream(errors));
            numOfFiles = CollectionReader.nextInt(); // contains the number of files present in part1_input_files.txt
        } catch (FileNotFoundException maine1) {
            System.out.println(maine1);
        } catch (IOException maine2) {
            System.out.println(maine2);
        } // Since the FileNotFound and IO exceptions are checked exceptions, they should be caught.


        do {

            line = CollectionReader.nextLine(); //contains the file names taken from part1_input_files.txt
            try {
                if (line.equals("")) // ignores the empty string at the end of part1_input_files.txt
                    continue;

                RepositoryReader = new BufferedReader(new FileReader("src/Assg3_Needed_Files (1)/" + line.strip())); // the files within part1_input_files.txt are being opened
                String bookline = RepositoryReader.readLine(); // its lines are being read

                while (bookline != null) {
                    if (bookline == null) //breaks off the loop at the encounter of a null object
                        break;
                    String[] bookarray = bookline.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1); // splits the entry at comas, ignoring the title

                    try {
                        error_thrower(bookline, known_genres, line); // here the exceptions being thrown
                        // This code is ignored when an exception is thrown. This allows for the recording of syntactically correct entries
                        //****************************************
                        // This code records the entries within each file into separate files distinguished by the genres.
                        /*
                            Through an iteration of the for loop, the 4th index of every entry and that of the genre array are checked for comparison.
                            Once they match, the index is used for the uploading process of entries into the expandable 2d array.
                         */
                        for (int j = 0; j < known_genres.length; j++) {
                            if (bookarray[4].equals(known_genres[j])) {
                                if (counts[j] >= books[j].length) {
                                    String[][] copyArray = new String[counts.length][books[j].length * 100]; //dynamic array extension
                                    for (int l = 0; l < books.length; l++) {
                                        for (int u = 0; u < books[j].length; u++) {
                                            copyArray[l][u] = books[l][u]; // the longer arrays copies the contents of the book array
                                        }
                                    }
                                    books = copyArray; // the book array's pointer gets redirected to the longer array
                                }
                                books[j][counts[j]++] = bookline; //the postscript increments the value in the counts array
                                // the counts array stores the number of entries within each respective genre
                            }
                        }
                        //****************************************
                    } catch (MissingFieldException missing) {

                        pw.println(missing.getMessage());
                    } catch (TooManyFieldsException many) {
                        pw.println(many.getMessage());
                    } catch (TooFewFieldsException few) {
                        pw.println(few.getMessage());
                    } catch (UnknownGenreException genre) {
                        pw.println(genre.getMessage());
                    } finally {
                        bookline = RepositoryReader.readLine();
                    }
                    //These catch statements record the entries that are syntactically erroneous.
                }
            } catch (FileNotFoundException file) {
                System.out.println(file);
            } catch (IOException io) {
                System.out.println(io.getMessage());
            }
        } while (CollectionReader.hasNextLine()); // the condition for the do while loop

        //The input and output streams are being closed here
        pw.close();
        CollectionReader.close();

        try {
            RepositoryReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        for (int p = 0; p < known_genres.length; p++) {
            try {
                pw = new PrintWriter(new FileOutputStream(file_names[p]), true);
                pw.println(counts[p]);
                for (int t = 0; t < counts[p]; t++) {
                    pw.println(books[p][t]);
                }
            } catch (FileNotFoundException e) {
                e.getMessage();
            }

        }
    }

    /**
     * do_part2 method
     *
     * @throws Exception
     */
    public static void do_part2() throws Exception {
        /**
         * Initializing PrintWriter and BufferedReader
         */
        PrintWriter pw2 = null;
        BufferedReader bf = null;
        String[] file_names = {"Cartoon_Comics.csv.txt", "Hobbies_Collectibles.csv.txt",
                "Movies_TV_Books.csv.txt", "Music_Radio_Books.csv.txt",
                "Nostalgia_Eclectic_Books.csv.txt", "Old_Time_Radio_Books.csv.txt",
                "Sports_Sports_Memorabilia.csv.txt", "Trains_Planes_Automobiles.csv.txt"};
        String[] output2 = {"Cartoons_Comics.csv.ser",
                "Hobbies_Collectibles.csv.ser",
                "Movies_TV_Books.csv.ser",
                "Music_Radio_Books.csv.ser",
                "Nostalgia_Eclectic_Books.csv.ser",
                "Old_Time_Radio_Books.csv.ser",
                "Sports_Sports_Memorabilia.csv.ser",
                "Trains_Planes_Automobiles.csv.ser"};

/**
 * First try method to open files
 */
        try {
            /**
             * For loop to iterate through every file
             */
            for (int i = 0; i < output2.length; i++) {
                Book[] BookArray = new Book[1];
                int count = 1;
                /**
                 * Initializing BufferedReader
                 */
                bf = new BufferedReader(new FileReader(file_names[i]));
                /**
                 * Initializing ObjectOutputStream
                 */
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(output2[i]));
                /**
                 * Initialzing PrintWriter
                 */
                pw2 = new PrintWriter(new FileOutputStream("semantic_error_file.txt",true),true);
                String Repository;
                /**
                 * Skips the first line of each file since it displays the number of files
                 */
                Repository = bf.readLine();
                /**
                 * While loop to iterate through every line
                 */
                while ((Repository = bf.readLine()) != null) {
                    try {
                        String[] splitBook;
                        Book[] copyArray = BookArray;
                        BookArray = new Book[count];
                        for (int y = 0; y < copyArray.length; y++) {
                            BookArray[y] = copyArray[y];
                        }
                        int sum1 = 0;
                        int sum2 = 0;
                        String[] splitISBN = new String[0];
                        /**
                         * If statement to check if there's double quotes in our line. If there is,
                         * split it such in an array TitleSplit such that it contains the title and the rest
                         * of the line. Then split the rest with respect to commas, add 1 to the length of the array
                         * the rest of the line split into called splitbook and move all of these by 1 towards the end.
                         * Then add the title back in.
                         */
                        if (Repository.contains("\"")) {
                            String[] TitleSplit = Repository.substring(1).split("\"");
                            splitBook = TitleSplit[1].substring(1).split(",");
                            String[] copyArray1 = splitBook.clone();
                            splitBook = new String[6];
                            for (int j = 0; j < copyArray1.length; j++) {
                                splitBook[j + 1] = copyArray1[j];
                            }
                            splitBook[0] = "\"" + TitleSplit[0] + "\"";
                            /**
                             * If no double quotes, split the line with respect to commas
                             */
                        } else
                            splitBook = Repository.split(",");
                        /**
                         * Split the ISBN in an array as to get the digits individually
                         */
                        splitISBN = splitBook[3].trim().split("");

                        /**
                         * If statement for if the ISBN is 10 by length
                         */
                        if (splitISBN.length == 10) {
                            /**
                             * For loop with the purpose of fulfilling the ISBN
                             * validation statement for a 10 digit ISBN.
                             * Checks if there's an x at the end
                             * If there's an x, change it to 10
                             */
                            for (int p = 0; p < splitISBN.length; p++) {
                                if (splitISBN[9].equalsIgnoreCase("x")) {
                                    splitISBN[9] = "10";
                                }
                                sum1 += (10-p)*Integer.parseInt(splitISBN[p]);
                            }
                            /**
                             * If statement for if the ISBN is 13 by length
                             */
                        } else if (splitISBN.length == 13) {
                            /**
                             * for loop with the purpose of fulfilling the ISBN
                             * validation statement for a 13 digit ISBN.
                             * Checks if there's an x at the end
                             * If there's an x, change it to 10
                             */
                            for (int k = 0; k < splitISBN.length; k++) {
                                if (splitISBN[12].equalsIgnoreCase("x")) {
                                    splitISBN[12] = "10";
                                }
                                int multiplier = Integer.parseInt(splitISBN[k]);
                                if (k % 2 == 1) {
                                    multiplier = multiplier * 3;
                                }

                                sum2 += multiplier;

                            }
                        }
                        /**
                         * Check for Exceptions
                         */
                        if ((Double.parseDouble(splitBook[2]) < 0)) {
                            throw new BadPriceException();
                        }

                        if (splitISBN.length == 10 && ((sum1 % 11) != 0)) {
                            throw new BadIsbn10Exception();
                        }
                        if (splitISBN.length == 13 && (sum2 % 10) != 0) {
                            throw new BadIsbn13Exception();
                        }
                        if ((1995 > Integer.parseInt(splitBook[5])) || (Integer.parseInt(splitBook[5]) > 2010)) {
                            throw new BadYearException();
                        }
                        /**
                         * Augments count by 1 to make it so that the BookArray can take in one more valid book
                         */
                        ++count;
                        /**
                         *Creates a book object with the information of that line if no exception is found
                         */
                        Book lineBook = new Book(splitBook[0], splitBook[1], Double.parseDouble(splitBook[2]), splitBook[3], splitBook[4], Integer.parseInt(splitBook[5]));
                        /**
                         * Put the book object in the BookArray
                         */
                        BookArray[BookArray.length - 1] = lineBook;
                    }
                    /**
                     * catch statements to deal with Exceptions
                     */
                    catch (BadIsbn10Exception e) {
                        pw2.println("Semantic error in file: " + output2[i]);
                        pw2.println("====================");
                        pw2.println(e.getMessage());
                        pw2.println("Record: " + Repository);

                    } catch (BadIsbn13Exception e2) {
                        pw2.println("Semantic error in file: " + output2[i]);
                        pw2.println("====================");
                        pw2.println(e2.getMessage());
                        pw2.println("Record: " + Repository);

                    } catch (BadPriceException e3) {
                        pw2.println("Semantic error in file: " + output2[i]);
                        pw2.println("====================");
                        pw2.println(e3.getMessage());
                        pw2.println("Record: " + Repository);

                    } catch (BadYearException e4) {
                        pw2.println("Semantic error in file: " + output2[i]);
                        pw2.println("====================");
                        pw2.println(e4.getMessage());
                        pw2.println("Record: " + Repository);

                    }


                }

                /**
                 * Write the BookArray to the respective binary file
                 */
                /**
                 * Close oos, pw2 and bf
                 */
                oos.writeObject(BookArray);
                oos.close();

            }


        }

        /**
         * Catch statements to deal with file opening
         */
        catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException in main");
        } catch (IOException e) {
            System.out.println("IOException in main");
        }
        pw2.close();
        bf.close();

    }

    /**
     * Part 3 method
     */
    public static void do_part3() {
        /**
         * Initializing Scanners
         */
        Scanner sc = new Scanner(System.in);
        Scanner sc2 = null;
        String[] serialized_files = {"Cartoons_Comics.csv.ser",
                "Hobbies_Collectibles.csv.ser",
                "Movies_TV_Books.csv.ser",
                "Music_Radio_Books.csv.ser",
                "Nostalgia_Eclectic_Books.csv.ser",
                "Old_Time_Radio_Books.csv.ser",
                "Sports_Sports_Memorabilia.csv.ser",
                "Trains_Planes_Automobiles.csv.ser"};
        String[] serialized_files_lengths = new String[serialized_files.length];
        String capsule = serialized_files[0];
        Book[] [] deserialize = new Book[serialized_files_lengths.length] [];
        int select_choice = 0;

        try {
            /**
             * For loop to put the deserialized files into a 2D array. The Book Array
             * of each file is put into a 1D array, making it a 2D array. Also making it so that we
             * can store the lengths of each book array in another file with its name to make
             * the display easier.
             */
            for (int i = 0; i < serialized_files.length; i++) {
                FileInputStream fileIn = new FileInputStream(serialized_files[i]);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                deserialize[i] = (Book[]) in.readObject();
                sc2 = new Scanner(in);
                /**
                 * Length method to check the length of a book array
                 */
                int length = (deserialize[i]).length;
                /**
                 * Store the length between parenthesis
                 */
                String storage = "(" + length + " records)";
                /**
                 *An array that is going to have the title of the file displayed with the numbers
                 * of elements inside
                 */
                serialized_files_lengths[i] = serialized_files[i] + "\t" + storage;
            }
            /**
             * Initialize display
             */
            String display = serialized_files_lengths[0];
            String firstchoice;
            select_choice=1;
            /**
             * Do-while loop that ends only when user enters x
             */
            do {

                /**
                 * Print menu
                 */
                System.out.println("-----------------------------\n" +
                        "Main Menu\n" +
                        "-----------------------------\n" +
                        "v View the selected file: " + display + "\n" +
                        "s Select a file to view\n" +
                        "x Exit\n" +
                        "-----------------------------");
                System.out.print("Enter Your Choice: ");
                /**
                 * Scanner to allow the user to enter a choice and store it
                 * in variable firstchoice. Make it to lowercase too as to make
                 * it so that caps don't matter
                 */
                firstchoice = sc.next().toLowerCase();
                /**
                 * If user enters v
                 */
                if (firstchoice.equals ("v")) {
                    /**
                     * Variable to display the current record index
                     */
                    int initialRecordIndex = 0;
                    /**
                     * while loop makes it so that the viewing continues until user enters 0
                     */
                    while (true) {
                        System.out.println("Viewing: " + display);
                        System.out.print("Please enter a command: ");
                        int n = sc.nextInt();
                        /**
                         * If statement for if the user enters 0
                         */
                        if (n == 0) {
                            System.out.println("Viewing session now ending\nRedirecting you back to main menu");
                            break;
                        }
                        /**
                         * If statement for when the user enters a number over 0
                         */
                        else if (n > 0)
                        {
                            int i = 0;

                            /**Try block to catch the ArrayIndexOutOfBoundException and handle by displaying
                            *the EOF message
                             */
                            try
                            {
                                /**
                                 * For loop to display the records
                                 */
                                for (i = initialRecordIndex; i <= (initialRecordIndex + (n - 1)) ; i++)
                                {
                                    /**
                                     * Display the records
                                     */
                                    System.out.println(deserialize[select_choice-1][i]);
                                }
                            }
                            /**
                             * Catch block to handle the ArrayIndexOutOfBoundsException
                             */
                            catch(ArrayIndexOutOfBoundsException e)
                            {
                                System.out.println("EOF has been reached");
                            }
                            /**
                             * Setting the index variable to the current position of the object
                             */
                            initialRecordIndex = i - 1;
                            System.out.println();
                        }


                        else
                        {
                            int i;
                            int counterForBOFMessage = 0; // Keeping count of the number of times the BOF has been reached to display the message once
                            // index of the first book record displayed on the console, will also be used to set the index of the next current object
                            int firstOne = (initialRecordIndex -(Math.abs(n) - 1));
                            for (i = (initialRecordIndex -(Math.abs(n) - 1)); i <= initialRecordIndex; i++)
                            {
                                /**
                                 * If index is negative, print that BOF has been reached
                                 */
                                if (i < 0)
                                {
                                    if (counterForBOFMessage == 1)
                                        System.out.println("BOF has been reached");
                                    else
                                    {
                                        /**
                                         * Purposefully made to do nothing
                                         */
                                    }
                                    counterForBOFMessage++;
                                }
                                else
                                {
                                    /**
                                     * Displaying book records
                                     */
                                    System.out.println(deserialize[select_choice-1][i]);
                                }

                            }
                            /**
                             * Made it so that if the first displayed Book object is negative, the index is still going to be the first
                             * one. MAde to evade mistakes
                             */
                            if (!(firstOne < 0))
                                initialRecordIndex = firstOne;
                        }

                        System.out.println();
                    }

                }
                /**
                 * If statement for if the user inputs s
                 */
                if (firstchoice.equals("s")) {
                    System.out.println("------------------------------\n" +
                            "File Sub-Menu\n" +
                            "------------------------------");
                    /**
                     * For loop to print out all files
                     */
                    for (int y = 0; y < serialized_files_lengths.length; y++) {
                        System.out.println((y + 1) + "\t");
                        System.out.println(serialized_files_lengths[y]);
                    }
                    /**
                     * Prints out exit option
                     */
                    System.out.println((serialized_files_lengths.length + 1 + "\t Exit"));
                    System.out.println("-----------------------------\n");
                    System.out.print("Enter Your Choice: ");
                    select_choice = sc.nextInt();
                    /**
                     *  If statement to return to menu if the Exit option is selected
                     */
                    if (select_choice == serialized_files_lengths.length + 1) {
                        continue;
                    }
                    /**
                     * If a valid choice of file is selected, that file is going to be stored
                     * in a variable and will be the next file that can be viewed when they
                     * get back to the main menu. The file selected is also going
                     * to be the one displayed in the main menu for the view option
                     */
                    else if (0 < select_choice && select_choice <= serialized_files_lengths.length) {
                        capsule = serialized_files[select_choice -1];
                        display = serialized_files_lengths[select_choice -1 ];
                    }
                    /**
                     * Else statement when the input is invlaid and direct back to menu
                     */
                    else {
                        System.out.println("Invalid input, Directing you back to main menu.");
                        continue;
                    }


                }



            } while (!firstchoice.equals("x"));
            /**
             * Loop stops when user inputs x and prints thank you message
             */
            System.out.println("Thank you for using our Book catalog!");

/**
 * Catch blocks to handle exceptions
 */
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException in main");
        } catch (IOException e) {
            System.out.println("IOException in main");
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException in main");
        }


    }

    /**
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        do_part1();
        do_part2();
        do_part3();
    }
}