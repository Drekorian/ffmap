/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pb138.ffmap.source;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;
import org.basex.api.xqj.*;

/**
 *
 * @author Stash
 */
public class Main {

  private static final String DRIVER = "org.basex.api.xqj.BXQDataSource";
  /** Sample query. */
  private static final String QUERY = "doc('D:/skola/ffmap/src/cz/muni/fi/pb138/ffmap/xml/ffmapSampleDatabase.xml')//joints";

  /**
   * Main method of the example class.
   * @param args (ignored) command-line arguments
   * @throws Exception exception
   */
  public static void main(final String[] args) throws Exception {

    System.out.println("=== XQJQuery ===");

    System.out.println("\n* Run query via XQJ:");

    // Build a connection to the specified driver.
    XQConnection conn = ((XQDataSource) Class.forName(DRIVER).
        newInstance()).getConnection();

    // Prepare the expression with the document and the query.
    XQPreparedExpression expr = conn.prepareExpression(QUERY);

    // Execute the query.
    XQResultSequence result = expr.executeQuery();

    // Get all results of the execution.
    while(result.next()) {
      // Print the results to the console.
      System.out.println(result.getItemAsString(null));
    }

    // Close the expression.
    expr.close();
  }

}
