/**
 * Copyright 2010 lazyboybd@gmail.com, Debasis Roy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law
 * or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */

package com.douchebag.client;

import javax.servlet.ServletContext;
import com.douchebag.Constants;

/**
 *
 * @author lazyboy
 */

public class InitParams {
  /**
   * Dir of closure library.
   */
  public String CLOSURE_LIBRARY_DIR;
  /**
   * Path to closure compiler.
   */
  public String CLOSURE_COMPILER_PATH;
  /**
   * Python script path to compile javascript.
   */
  public String CLOSURE_COMPILE_PY;
  /**
   * Base dir of javascript sources.
   */
  public String BASE_JS_DIR;
  /**
   * Path to main javascript file.
   */
  public String MAIN_JS_PATH;
  /**
   * Path to closure template for java compiler.
   */
  public String CLOSURE_TEMPLATE_JAVA_COMPILER;
  /**
   * Path to closure template for javascript compiler.
   */
  public String CLOSURE_TEMPLATE_JAVASCRIPT_COMPILER;
  /**
   * Path to soy root dir.
   */
  public String SOY_BASE_DIR;

  public InitParams(ServletContext context) {
    CLOSURE_LIBRARY_DIR = context.getInitParameter("closure_library");
    CLOSURE_COMPILER_PATH = context.getInitParameter("closure_compiler");
    CLOSURE_TEMPLATE_JAVA_COMPILER = context.getInitParameter(
        "closure_template_java_compiler");
    CLOSURE_TEMPLATE_JAVASCRIPT_COMPILER = context.getInitParameter(
        "closure_template_javascript_compiler");
    // TODO: Move stuffs to /resources dir.
    BASE_JS_DIR = context.getRealPath("/WEB-INF/js/com/douchebag/");
    MAIN_JS_PATH = context.getRealPath("/WEB-INF/js/main.js");
    CLOSURE_COMPILE_PY = context.getRealPath("/WEB-INF/compile.py");
    SOY_BASE_DIR = (getClass().getResource(Constants.BASE_SOY_DIR)).getFile();
    System.out.println("SOY BASE DIR |" + SOY_BASE_DIR + "|");
  }
}
