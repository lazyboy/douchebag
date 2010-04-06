/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.douchebag.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Class for handling system calls.
 *
 * @author lazyboy
 */
public class SyscallRunner {

  /**
   * Executes a system command.
   * 
   * @param command The command string.
   * @return The output of the command.
   * @throws Exception The exception thrown while attempting to run <code>command</code>
   */
  public static String doRun(String command) throws Exception {
    Runtime runtime = Runtime.getRuntime();
    StringBuffer ret = new StringBuffer();

    try {
      Process process = runtime.exec(command);
      InputStream iis = process.getInputStream();
      BufferedInputStream buf = new BufferedInputStream(iis);
      InputStreamReader iireader = new InputStreamReader(buf);
      BufferedReader breader = new BufferedReader(iireader);

      String str;
      while ((str = breader.readLine()) != null) {
        ret.append(str).append("\n");
      }

      // Check for failure
      try {
        if (process.waitFor() != 0) {
          throw new Exception("Process exit status: " + process.exitValue());
        }
      } catch (InterruptedException ine) {
        throw ine;
      } finally {
        breader.close();
        iireader.close();
        buf.close();
        iis.close();
      }

    } catch (IOException ioe) {
      throw ioe;
    }
    return ret.toString();
  }
}
