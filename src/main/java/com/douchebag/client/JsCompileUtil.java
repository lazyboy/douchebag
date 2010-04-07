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

import com.douchebag.Constants;
import com.douchebag.util.SyscallRunner;
import java.io.IOException;
import java.net.URL;

/**
 * Compiles project javascript.
 * @author lazyboy
 *
 */
public class JsCompileUtil {
  private InitParams params;

  public JsCompileUtil(InitParams params) {
    this.params = params;
  }

  public String compile() throws JsCompileException {
    String command = "python " + params.CLOSURE_COMPILE_PY
            + " -c " + params.CLOSURE_COMPILER_PATH
            + " -b " + params.CLOSURE_LIBRARY_DIR
            + " -j " + params.BASE_JS_DIR
            + " -m " + params.MAIN_JS_PATH;

    System.out.println("Java Executing: " + command);

    try {
      String ret = SyscallRunner.doRun(command);
      //System.out.println("===========Source==========\n" + ret);
      return ret;
    } catch (Exception e) {
      throw new JsCompileException("System error: " + e.toString());
    }
  }

  public String getCss() throws JsCompileException {
    try {
      URL cssUrl = getClass().getResource(Constants.CSS_FILE);
      String css = LocalResourceCache.read(cssUrl.getFile());
      return css;
    } catch (IOException ioe) {
      throw new JsCompileException("Js comp error [css]: " + ioe.toString());
    }
  }
}
