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
import java.util.NoSuchElementException;
import java.util.zip.DataFormatException;

/**
 * Test the method created in the ExceptionalBank class and the Coin class
 */
public class ExceptionalBankTester extends java.lang.Object {

  /**
   * This method checks whether the ExceptionalBank constructor throws an IllegalArgumentException
   * with appropriate error message, when it is passed a zero or a negative capacity. This test must
   * fail if another kind of exception is thrown for such test scenario.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testExceptionalBankConstructor() {
    try {
      // create an exceptional bank with a negative capacity
      ExceptionalBank bank = new ExceptionalBank(-10);
      System.out
          .println("Problem detected. The constructor call of the ExceptionalBank class did not "
              + "throw an IllegalArgumentException when it is passed a negative capacity.");
      return false; // return false if no exception has been thrown
    } catch (IllegalArgumentException e1) {
      // check that the caught IllegalArgumentException includes
      // an appropriate error message
      if (e1.getMessage() == null // your test method should not throw
          // a NullPointerException,but must return false if e1.getMessage is null
          || !e1.getMessage().toLowerCase().contains("must be a non-zero positive integer")) {
        System.out
            .println("Problem detected. The IllegalArgumentException thrown by the constructor "
                + "call of the ExceptionalBank class when it is passed a negative capacity "
                + "does not contain an appropriate error message.");
        return false;
      }
    } catch (Exception e2) {
      // an exception other than IllegalArgumentException has been thrown
      System.out
          .println("Problem detected. An unexpected exception has been thrown when calling the "
              + "constructor of the ExceptionBank class with a negative argument. "
              + "An IllegalArgumentException was expected to be thrown. "
              + "But, it was NOT the case.");
      e2.printStackTrace(); // to help locate the error within the bad ExceptionalBank
      // constructor code.
      return false;
    }
    return true; // test passed
  }

  /**
   * This method checks whether the ExceptionalBank constructor does not throw any exceptions when
   * it is passed positive capacity. This test must fail if another kind of exception is thrown for
   * such test scenario.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testGoodExceptionalBankConstructor() {
    try {
      // create an exceptional bank with a capacity 20
      ExceptionalBank bank = new ExceptionalBank(20);
    } catch (Exception e2) {
      // an exception has been thrown
      System.out
          .println("Problem detected. An unexpected exception has been thrown when calling the "
              + "constructor of the ExceptionBank class with capacity 20. ");
      e2.printStackTrace(); // to help locate the error within the bad ExceptionalBank
      // constructor code.
      return false;
    }
    return true;// test passed
  }

  /**
   * This method checks whether the addCoin method throws an IllegalArgumentException with
   * appropriate error message, when it is passed a null Coin. This test must fail if another kind
   * of exception is thrown for such test scenario.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAddCoin() {
    try {
      // add Coin with reference null to the good bank
      ExceptionalBank bank = new ExceptionalBank(10);
      bank.addCoin(null);
      System.out.println("Problem detected. The addCoin method did not "
          + "throw an IllegalArgumentException when the bank is added a null Coin.");
      return false; // return false if no exception has been thrown
    } catch (IllegalArgumentException e1) {
      // check that the caught IllegalArgumentException includes
      // an appropriate error message
      if (e1.getMessage() == null // return false if e1.getMessage is null or does not contain right
                                  // error message
          || !e1.getMessage().toLowerCase().contains("add a null reference to this bank")) {
        System.out.println("Problem detected. The IllegalArgumentException thrown by the method "
            + "addCoin does not contain an appropriate error message.");
        return false;
      }
    } catch (Exception e2) {
      // an exception other than IllegalArgumentException has been thrown
      System.out
          .println("Problem detected. An unexpected exception has been thrown when calling the "
              + "addCoin method in the ExceptionalBank class. "
              + "An IllegalArgumentException was expected to be thrown. "
              + "But, it was NOT the case.");
      e2.printStackTrace(); // to help locate the error within the bad ExceptionalBank
      return false;
    }
    return true; // test passed
  }

  /**
   * This method checks whether the removeCoin method throws an NoSuchElementException with
   * appropriate error message, when the bank is already empty. This test must fail if another kind
   * of exception is thrown for such test scenario.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testRemoveCoinEmptyBank() {
    try {
      // create an exceptional bank with no coin add in it
      // call the removeCoin method
      ExceptionalBank bank = new ExceptionalBank();
      bank.removeCoin();
      System.out.println("Problem detected. The removeCoin method did not "
          + "throw a NoSuchElementException when the bank is already empty.");
      return false; // return false if no exception has been thrown
    } catch (NoSuchElementException e1) {
      // check that the caught NoSuchElmentException includes
      // an appropriate error message
      if (e1.getMessage() == null // return false if e1.getMessage is null
          || !e1.getMessage().toLowerCase().contains("to remove a coin")) {
        System.out.println(
            "Problem detected. The NoSuchElementException thrown by the "
            + "removeCoin method when the bank is already empty "
                + "does not contain an appropriate error message.");
        return false;
      }
    } catch (Exception e2) {
      // an exception other than NoSuchElemntException has been thrown
      System.out
          .println("Problem detected. An unexpected exception has been thrown when calling the "
              + "removeCoin method with a empty bank. "
              + "An NoSuchElementException was expected to be thrown. "
              + "But, it was NOT the case.");
      e2.printStackTrace(); // to help locate the error within the bad ExceptionalBank
      return false;
    }
    return true; // test passed
  }

  /**
   * This method checks whether the addCoins method does not throw any exceptions , when it receives
   * a valid String. This test must fail if any exception is thrown for such test scenario.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAddCoinsValidFormat() {
    try {
      // create an exceptional bank
      // add coins in it with valid format
      ExceptionalBank bank = new ExceptionalBank(1);
      bank.addCoins​(" penny: 1");

    } catch (Exception e) {
      // an exception is thrown
      System.out
          .println("Problem detected. An unexpected exception has been thrown when calling the "
              + "addCoins method of the ExceptionBank class with a valid argument. ");
      e.printStackTrace(); // to help locate the error within the bad ExceptionalBank
      return false;
    }
    return true; // test passed

  }

  /**
   * This method checks whether the addCoins method throws an DataFormatException with appropriate
   * error message, when it is passed invalid argument. This test must fail if another kind of
   * exception is thrown for such test scenario.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAddCoinsInvalidDataFormat() {
    try {
      // create an exceptional bank
      // add coins with invalid argument
      ExceptionalBank bank = new ExceptionalBank(10);
      bank.addCoins​("DIME:-5");
      bank.addCoins​("penny:ten");
      bank.addCoins​("penny 10");
      bank.addCoins​("penny:2:penny:10");
      bank.addCoins​("penny:ten");
      System.out
          .println("Problem detected. The addCoins method of the ExceptionalBank class did not "
              + "throw an DataFormatException when it receives an invalid argument.");
      return false; // return false if no exception has been thrown
    } catch (DataFormatException e1) {
      // check that the caught DataFormatException includes
      // an appropriate error message
      if (e1.getMessage() == null // must return false if e1.getMessage is null
          || !e1.getMessage().toLowerCase().contains("format of the command line")) {
        System.out.println(
            "Problem detected. The DataFormatException thrown by addCoins method of the ExceptionalBank "
                + "class when it is passed a negative capacity does not contain an appropriate error message.");
        return false;
      }
    } catch (Exception e3) {
      // an exception other than DataFormatException has been thrown
      System.out.println(
          "Problem detected. An unexpected exception has been thrown when calling addCoins method"
              + " of the ExceptionBank class."
              + "An DataFormatException was expected to be thrown. " + "But, it was NOT the case.");
      e3.printStackTrace();// to help locate the error within the bad ExceptionalBank
      return false;

    }
    return true; // test passed
  }

  /**
   * This method checks whether the addCoins method throws an NoSuchElementException with
   * appropriate error message, when it is passed invalid string for coin name. This test must fail
   * if another kind of exception is thrown for such test scenario.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAddCoinsNoSuchElement() {
    try {
      // create an exceptional bank
      // add coins with wrong coin name
      ExceptionalBank bank = new ExceptionalBank(1);
      bank.addCoins​("blabla: 1");
      System.out
          .println("Problem detected. The addCoins method of the ExceptionalBank class did not "
              + "throw an NoSuchElementException when it receives a wrong coin name.");
      return false;
    } catch (NoSuchElementException e1) {
      if (e1.getMessage() == null // must return false if e1.getMessage is null
          || !e1.getMessage().toLowerCase().contains("coin name provided in the command line")) {
        System.out
            .println("Problem detected. The NoSuchElementException thrown by the addCoins method"
                + " of the ExceptionalBank class does not contain an appropriate error message.");
        return false;
      }
    } catch (Exception e) {
      // an exception other than NoSuchElementException has been thrown
      System.out
          .println("Problem detected. An unexpected exception has been thrown when calling the "
              + "addCoins method of the ExceptionBank class with a invalid coin name. "
              + "An NoSuchElementException was expected to be thrown. "
              + "But, it was NOT the case.");
      e.printStackTrace(); // to help locate the error within the bad ExceptionalBank
      return false;
    }
    return true; // test passed

  }

  /**
   * This method checks whether the ExceptionalBank addCoins method throws an
   * IllegalArgumentException with appropriate error message, when it is passed a null reference for
   * input. This test must fail if another kind of exception is thrown for such test scenario.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAddCoinsIllegalArgument() {
    try {
      // create an exceptional bank
      // add coins with null reference
      ExceptionalBank bank = new ExceptionalBank();
      bank.addCoins​(null);
      System.out
          .println("Problem detected. The addCoins method of the ExceptionalBank class did not "
              + "throw an IllegalArgumentException when it adds a null Coins.");
      return false; // return false if no exception has been thrown
    } catch (IllegalArgumentException e1) {
      // check that the caught IllegalArgumentException includes
      // an appropriate error message
      if (e1.getMessage() == null // return false if e1.getMessage is null
          || !e1.getMessage().toLowerCase().contains("does not accept a null reference as input")) {
        System.out
            .println("Problem detected. The IllegalArgumentException thrown by addCoins method "
                + "call of the ExceptionalBank class when it adds a null Coins "
                + "does not contain an appropriate error message.");
        return false;
      }
    } catch (Exception e2) {
      // an exception other than IllegalArgumentException has been thrown
      System.out
          .println("Problem detected. An unexpected exception has been thrown when calling the "
              + "addCoins method of the ExceptionBank class with a null Coin. "
              + "An IllegalArgumentException was expected to be thrown. "
              + "But, it was NOT the case.");
      e2.printStackTrace(); // to help locate the error within the bad ExceptionalBank
      return false;
    }
    return true; // test passed
  }

  /**
   * This method checks whether the ExceptionalBank loadCoins throws an IllegalArgumentException
   * with appropriate error message, when it access a null file. This test must fail if another kind
   * of exception is thrown for such test scenario.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testLoadCoinsNullReference() {
    try {
      // create an exceptional bank
      // load a file with null reference
      ExceptionalBank bank = new ExceptionalBank(1);
      bank.loadCoins​(null);
      System.out.println("Problem detected. The loadCoins of the ExceptionalBank class did not "
          + "throw an NullPointerException when it accesses to a null file.");
      return false;
    } catch (NullPointerException e1) {
      // check that the caught NullPointerException includes
      // an appropriate error message
      if (e1.getMessage() == null // return false if e1.getMessage is null
          || !e1.getMessage().toLowerCase().contains("file is null")) {
        System.out
            .println("Problem detected. The NullPointerException thrown by the loadCoins method "
                + "call of the ExceptionalBank class when it accesses a null file "
                + "does not contain an appropriate error message.");
        return false;
      }
    } catch (Exception e) {
      // an exception other than NullPointerException has been thrown
      System.out
          .println("Problem detected. An unexpected exception has been thrown when calling the "
              + "loadCoins method of the ExceptionBank class with a null file. "
              + "A NullPointerException was expected to be thrown. " + "But, it was NOT the case.");
      e.printStackTrace(); // to help locate the error within the bad ExceptionalBank
      return false;
    }
    return true; // test passed
  }

  /**
   * This method checks whether the ExceptionalBank loadCoins method throws an FileNotFoundException
   * with appropriate error message, when there is no appointed file. This test must fail if another
   * kind of exception is thrown for such test scenario.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testLoadCoinsFileNotFound() {
    try {
      // create an exceptional bank with a negative capacity
      ExceptionalBank bank = new ExceptionalBank(1);
      File file =
          new File("/Users/violettian/eclipse-workspace/P04 Exceptional Piggy Bank/blabla.txt");
      bank.loadCoins​(file);
      System.out
          .println("Problem detected. The loadCoins method of the ExceptionalBank class did not "
              + "throw an FileNotFoundException when there is no appointed file.");
      return false;
    } catch (FileNotFoundException e1) {
      // check that the caught FileNotFoundException includes
      // an appropriate error message
      if (e1.getMessage() == null // return false if e1.getMessage is null
          || !e1.getMessage().toLowerCase().contains("file does not exist")) {
        System.out
            .println("Problem detected. The FileNotFoundException thrown by the loadCoins method "
                + "call of the ExceptionalBank class when there is no appointed file"
                + "does not contain an appropriate error message.");
        return false;
      }
    } catch (Exception e) {
      // an exception other than FileNotFoundException has been thrown
      System.out
          .println("Problem detected. An unexpected exception has been thrown when calling the "
              + "loadCoins of the ExceptionBank class with a invalid file name. "
              + "A FileNotFoundException was expected to be thrown. "
              + "But, it was NOT the case.");
      e.printStackTrace(); // to help locate the error within the bad ExceptionalBank
      return false;
    }
    return true; // test passed
  }

  /**
   * This method checks whether the ExceptionalBank loadCoins method does not throw exceptions ,
   * when it is passed a valid file name. This test must fail if any kind of exception is thrown for
   * such test scenario.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testLoadCoinsFileFound() {
    try {
      // create an exceptional bank
      // load a file with valid file name
      ExceptionalBank bank = new ExceptionalBank(1);
      File file =
          new File("/Users/violettian/eclipse-workspace/P04 Exceptional Piggy Bank/sample1.txt");
      bank.loadCoins​(file);
    } catch (Exception e) {
      // an exception has been thrown
      System.out
          .println("Problem detected. An unexpected exception has been thrown when calling the "
              + "loadCoins method of the ExceptionBank class with valid file name. ");
      e.printStackTrace(); // to help locate the error within the bad ExceptionalBank
      return false;
    }
    return true; // test passed
  }

  public static void main(java.lang.String[] args) {


//    System.out.println("testExceptionalBankConstructor: "
//        + ExceptionalBankTester.testExceptionalBankConstructor());// pass
//
//    System.out.println("testGoodExceptionalBankConstructor: "
//        + ExceptionalBankTester.testGoodExceptionalBankConstructor());/// pass
//
//    System.out.println("testAddCoin: " + ExceptionalBankTester.testAddCoin());// pass
//
//    System.out
//        .println("testRomoveCoinEmptyBank: " + ExceptionalBankTester.testRemoveCoinEmptyBank());// pass
//
//    System.out
//        .println("testAddCoinsValidFormat: " + ExceptionalBankTester.testAddCoinsValidFormat());// pass
//
//    System.out.println(
//        "testAddCoinsIllegalArgument: " + ExceptionalBankTester.testAddCoinsIllegalArgument());// pass
//
//    System.out
//        .println("testAddCoinsNoSuchElement: " + ExceptionalBankTester.testAddCoinsNoSuchElement());// pass
//
//    System.out.println(
//        "testAddCoinsInvalidDataFormat: " + ExceptionalBankTester.testAddCoinsInvalidDataFormat());
//
//    System.out.println("testLoadCoinsFileFound: " + ExceptionalBankTester.testLoadCoinsFileFound());// pass
//
//    System.out.println(
//        "testLoadCoinsNullReference: " + ExceptionalBankTester.testLoadCoinsNullReference());// pass
//
//    System.out
//        .println("testLoadCoinsFileNotFound: " + ExceptionalBankTester.testLoadCoinsFileNotFound());// pass
//
//
//    ExceptionalBank bank = new ExceptionalBank(10);
//    
//    try {
//      bank.addCoins​("penny:7");
//      bank.addCoins​("quarter:2");
//      bank.addCoins​("penny:26");
//    } catch (DataFormatException e) {
//      e.printStackTrace();
//    }
//    bank.saveBankSummary​(new File("file"));
String s ="abc";
s = s.concat(" songs");
System.out.println(s);
  }
}


