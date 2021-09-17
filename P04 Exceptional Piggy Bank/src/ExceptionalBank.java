//////////////// FILE HEADER //////////////////////////
//
// Title: P04 Exceptional Piggy Bank
// Files: ExceptionalBank, ExceptionalBankTester
// Course: CS300,Spring, 2020
//
// Author: Meng Tian
// Email: mtian42@wisc.edu
// Lecturer's Name: Gary Dahl
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;
import java.util.zip.DataFormatException;


/**
 * This class implements an expanded version of elastic bank application
 * 
 */
public class ExceptionalBank extends java.lang.Object {

  private Coin[] coins; // array which stores all coins held in this elastic bank
  private int size; // size of this elastic bank
  private int expansionsLeft; // number of expansions left for this elastic bank
  private static Random rand = new Random(100); // random integers generator

  /**
   * Creates a new elastic bank object with a given initial capacity
   * 
   * @param initialCapacity initial capacity of this elastic bank
   */
  public ExceptionalBank(int initialCapacity) throws IllegalArgumentException {
    if (initialCapacity <= 0) {
      throw new IllegalArgumentException(
          "WARNING! The initial capacity of a bank must be a non-zero positive integer.");
    }
    coins = new Coin[initialCapacity];
    this.size = 0;
    this.expansionsLeft = 2;

  }

  /**
   * Creates a new elastic bank object with an initial capacity equal to 10
   */
  public ExceptionalBank() {
    this(10);
  }

  /**
   * Returns the capacity of this elastic bank
   * 
   * @return the capacity of this elastic bank
   */
  public int capacity() {
    return coins.length;
  }

  /**
   * Returns the expansions left for this elastic bank
   * 
   * @return the expansions left for this elastic bank
   */
  public int getExpansions() {
    return this.expansionsLeft;
  }

  /**
   * Returns the number of coins held in this elastic bank
   * 
   * @return the size of this elastic bank
   */
  public int getSize() {
    return this.size;
  }

  /**
   * Returns the value in cents of coins held in this elastic bank
   * 
   * @return the balance of this elastic bank
   */
  public int getBalance() {
    int balance = 0;
    // add the value of each coin held in this bank to balance, then return it
    for (int i = 0; i < size; i++) {
      balance += coins[i].value();
    }
    return balance;
  }

  /**
   * Returns the number of coins with a specific coinName held in this bank. The coin name
   * comparison is case insensitive
   * 
   * @param coinName name of a coin
   * @return the count of coins having the provided coinName, held in this bank
   */
  public int getSpecificCoinCount(String coinName) {
    int count = 0;
    for (int i = 0; i < size; i++) {
      if (coins[i].name().equalsIgnoreCase(coinName))
        count++;
    }
    return count;
  }

  /**
   * Returns a string representation of all the coins held in this elastic bank. Each coin is
   * represented by the pair "(name, value)", and the string representation should contain all of
   * these pairs in one space-separated line. For example: "(PENNY, 1) (QUARTER, 25) (PENNY, 1)
   * (DIME, 10) (NICKEL, 5)"
   * 
   * @return a String representation of the contents of the bank.
   */
  public String getCoins() {
    String contents = "";
    // traverse the coins oversize array and add each coin's string representation to the string to
    // be returned
    for (int i = 0; i < size; i++) {
      contents += "(" + coins[i].name() + ", " + coins[i].value() + ")";
      if (i < size - 1)
        contents += " ";
    }
    return contents;
  }

  /**
   * Returns a summary of this bank contents
   *
   * @return an empty string if this bank is empty, and a string representation of the summary of
   *         this bank otherwise. The summary of the bank is a set of lines. Each line is formatted
   *         as follows "coin_name:coin_count"
   */
  public String getSummary() {
    String summary = "";
    Coin[] values = Coin.values();
    // traverse this bank contents and update its summary
    for (int i = 0; i < values.length; i++) {
      String name = values[i].name();
      int count = getSpecificCoinCount(name);
      if (count != 0) {
        summary += name + ":" + count + "\n";
      }
    }
    return summary.trim(); // remove whitespace (spaces, new lines etc.) from the beginning and end
                           // of summary and return the resulting string

  }


  /**
   * Removes and returns a coin at a random position from this elastic bank
   * 
   * @return the removed coin or null if this bank is empty
   */
  public Coin removeCoin() throws NoSuchElementException {
    // check if the bank is already empty
    if (size == 0) {
      throw new NoSuchElementException("WARNING! This bank is empty. Unable to remove a coin.");// if
                                                                                                // yes,
                                                                                                // throw
                                                                                                // a
                                                                                                // warning
    }
    int randPosition = rand.nextInt(size); // get a random position from 0 .. size-1
    Coin removedCoin = coins[randPosition]; // store the coin to be removed
    // The order of the coins within this bank (a piggy bank) is not important
    // So, move the coin at the end of the coins array to the random position
    // and set that last element to null.
    coins[randPosition] = coins[size - 1];
    coins[size - 1] = null;
    size--; // update size
    return removedCoin;

  }

  /**
   * Removes all the coins from this elastic bank
   */
  public void empty() {
    // set all the non-null references within the coins array to null
    for (int i = 0; i < size; i++) {
      coins[i] = null;
    }
    // set the size of this bank to 0
    size = 0;
  }

  /**
   * adds a Coin to the bank and adjusts the capacity of coins if necessary and possible
   * 
   * @param c coin to be added to this elastic bank
   */
  public void addCoin(Coin c) throws IllegalArgumentException {
    // check if c is null
    if (c == null) {
      throw new IllegalArgumentException("WARNING! You cannot add a null reference to this bank.");// if
                                                                                                   // yes,
                                                                                                   // throw
                                                                                                   // a
                                                                                                   // warning
    }
    // check if this bank is full

    int signal = 0;// initialize the signal to 0 at first
    if (size == coins.length) {

      // check whether there are expansions left
      if (this.expansionsLeft > 0) {
        // expand the capacity of this elastic bank by 10
        coins = Arrays.copyOf(coins, coins.length + 10);
        this.expansionsLeft--;
      } else { // no expansions left
        // empty this elastic bank
        empty();
        signal = 1;// set the signal to 1
      }
    }
    // check the signal number
    if (signal == 1) {// if it equals to 1
      coins[0] = c;// store the new Coin at the first place
      size = 1;// set the size be 1
    } else {// if not
      coins[size] = c;// add c at the end of this bank
      size++;// the size of the bank increases one
    }
  }

  /**
   * adds a number of same type Coins to the bank and adjusts the capacity of coins if necessary and
   * possible
   * 
   * @param command certain type of coin (string in the format of "coin_name:coin_value") to be
   *                added to this elastic bank
   */
  public void addCoins​(java.lang.String command) throws java.util.zip.DataFormatException {
    // check if the command is a null reference
    if (command == null)
      throw new IllegalArgumentException(// if yes, throw a warning
          "WARNING! The addCoins() method does not accept a null reference as input.");
    // check if the command contains a ":"
    if (command.indexOf(":") == -1)// if no, throw a warning
      throw new DataFormatException("The format of the command line" + command + " is incorrect. ");


    String[] parts = command.split(":");// split the command by ":"
    // check if there are two parts of the string command
    if (parts.length != 2)// if not, throw a warning
      throw new DataFormatException("The format of the command line" + command + " is incorrect. ");

    String coinName = parts[0];// initialize the first part
    coinName = coinName.trim();// ignore the space before
    String coinValue = parts[1];// initialize the second part
    coinValue = coinValue.trim();// ignore the space before

    // check if coinValue stores a integer
    try {
      int numberOfCoins = Integer.valueOf(coinValue);// Integer.valueOf only throws
                                                     // NumberFormatException
    } catch (NumberFormatException e) {// if there is one, catch it and throw the
                                       // DataFormatException
      throw new DataFormatException("The format of the command line" + command + " is incorrect. ");
    }

    int numberOfCoins = Integer.valueOf(coinValue);

    // check if the coinValue is a positive integer
    if (numberOfCoins <= 0)// if not, throw a warning
      throw new DataFormatException("The format of the command line" + command + " is incorrect. ");

    // check if the coin name is right
    if (!coinName.equalsIgnoreCase("DIME") && !coinName.equalsIgnoreCase("QUARTER")
        && !coinName.equalsIgnoreCase("PENNY") && !coinName.equalsIgnoreCase("NICKEL"))
      throw new NoSuchElementException(// if not, throw a warning
          "The coin name provided in the command line " + command + " is invalid.");

    // check the type of the coin and add it to bank
    if (coinName.equalsIgnoreCase("DIME")) {
      for (int i = 0; i < numberOfCoins; i++)
        addCoin(Coin.DIME);
    }
    if (coinName.equalsIgnoreCase("QUARTER")) {
      for (int i = 0; i < numberOfCoins; i++)
        addCoin(Coin.QUARTER);
    }
    if (coinName.equalsIgnoreCase("PENNY")) {
      for (int i = 0; i < numberOfCoins; i++)
        addCoin(Coin.PENNY);
    }
    if (coinName.equalsIgnoreCase("NICKEL")) {
      for (int i = 0; i < numberOfCoins; i++)
        addCoin(Coin.NICKEL);
    }

  }

  /**
   * load a list of coins from a file object which refers to a data file written in a specific
   * format (a set of lines each formatted as follows "coin_name:coin_count").
   * 
   * @param file file object which refers to a data file of coin_names and their counts
   */
  public void loadCoins​(java.io.File file) throws java.io.FileNotFoundException {
    // check if the file is null
    if (file == null)// if yes, throw a warning
      throw new NullPointerException("WARNING! The file is null.");

    // check if the file exists
    if (file.exists() == false)// if not, throw a warning
      throw new FileNotFoundException("WARNING! The file does not exist.");
    // initialize the scanner
    Scanner fin = new Scanner(file);
    String list;
    // store a line of information in the file to a string called list and add the correct
    // information to the bank
    while (fin.hasNext()) {// scan the whole file
      list = fin.nextLine();// store a line to list
      // catch all exceptions that it may contained and add it to bank if no exception
      try {
        addCoins​(list);
      } catch (IllegalArgumentException e) {
        System.out.println("WARNING.Skipped the line because of null string.");
      } catch (DataFormatException e) {
        System.out.println("WARNING.Skipped the line because of wrong data format.");
      } catch (NoSuchElementException e) {
        System.out.println("WARNING.Skipped the line because of unexisting element.");
      }
    }
    // close the file
    fin.close();

  }

  /**
   * save the summary of this bank to the provided file in a specific format for each line:
   * coin_name:coin_count.
   * 
   * @param file file object where a summary of the contents of this bank will be saved
   */
  public void saveBankSummary​(java.io.File file) {

    // check if the file is null
    if (file == null)
      throw new NullPointerException("WARNING! The file is null.");
    // check if it is accessible to the file
    PrintStream fout = null;
    try {
      fout = new PrintStream("file");// may cause a FileNotFoundException
      // count the number of different types of coins in the bank
    // save the information in the file in the format of "coin_name:coin_number"
    int quartersNum = getSpecificCoinCount("quarter");
    if (quartersNum != 0) {// if it is 0, then do not print anything
      fout.println("QUARTER" + ":" + quartersNum);
    }
    int dimesNum = getSpecificCoinCount("dime");
    if (dimesNum != 0) {
      fout.println("DIME" + ":" + dimesNum);
    }
    int nickelsNum = getSpecificCoinCount("nickel");
    if (nickelsNum != 0) {
      fout.println("NICKEL" + ":" + nickelsNum);
    }
    int pennysNum = getSpecificCoinCount("penny");
    if (pennysNum != 0) {
      fout.println("PENNY" + ":" + pennysNum);
    }
    fout.close();// close the file, may causes an IOException
    } catch (IOException e) {
      System.out.println("WARNING! The file cannot be found.");
    }

    

  }
}


