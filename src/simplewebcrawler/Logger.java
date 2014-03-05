/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simplewebcrawler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


    public class Logger {
        public static void log(String message) throws IOException { 
      BufferedWriter out = new BufferedWriter(new FileWriter("D:\\Craigslist\\jobresults.txt", true));
      out.write(message + "\r\n");
      out.close();
    }
    }