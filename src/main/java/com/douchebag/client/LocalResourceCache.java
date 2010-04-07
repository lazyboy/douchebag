/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.douchebag.client;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Returns a local resource content.
 * Caches the value if not in debug mode.
 *
 * @author lazyboy
 */
public class LocalResourceCache {
  private static class Resource {
    // TODO: Use this field.
    public long lastModified;
    public String content;
    Resource(String content, long lastModified) {
      this.content = content;
      this.lastModified = lastModified;
    }
  }

  // TODO: Make this thread safe.
  private static Map<String, Resource> contentCache = new HashMap<String, Resource>();

  public static String read(String resourcePath) throws IOException {
    // Serve it from the cache if it's already there.
    if (!com.douchebag.Constants.DEBUG && contentCache.containsKey(resourcePath)) {
      // TODO: Trigger a refresh cache on separate thread.
      return contentCache.get(resourcePath).content;
    }

    try {
      String content = readHelper(resourcePath);
      return content;
    } catch (FileNotFoundException fnfe) {
      // This is really a bad idea.
      throw new IOException("File " + resourcePath + " not found.");
    }
  }

  private static String readHelper(String resourcePath)
      throws FileNotFoundException {
    Scanner scanner = new Scanner(new File(resourcePath));
    StringBuffer content = new StringBuffer();
    try {
      while(scanner.hasNextLine()) {
        content.append(scanner.nextLine());
      }
    } finally {
      scanner.close();
    }
    return content.toString();
  }
}
