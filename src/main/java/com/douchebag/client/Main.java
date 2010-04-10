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

//import java.io.File;
import com.douchebag.Constants;
import com.google.template.soy.SoyFileSet;
import com.google.template.soy.data.SoyMapData;
import com.google.template.soy.tofu.SoyTofu;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
//import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

/**
 *
 * @author lazyboy
 */
public class Main extends HttpServlet {

  public String foo() {
    // Bundle the Soy files for your project into a SoyFileSet.
    String soyPath = initParams.SOY_BASE_DIR + "simple.soy";
    SoyFileSet sfs = (new SoyFileSet.Builder()).add(new File(soyPath)).build();

    // Compile the template into a SoyTofu object.
    // SoyTofu has a render method that can render all the templates in the SoyFileSet.
    SoyTofu tofu = sfs.compileToJavaObj();

    // Call the template with no data.
    //System.out.println("****" + tofu.render("examples.simple.helloWorld", (SoyMapData) null, null));
    return tofu.render("examples.simple.helloWorld", (SoyMapData) null, null);
  }

  /**
   * Logger class.
  private Logger logger = LoggerFactory.getLogger(Main.class);
   */

  /**
   * Handles the HTTP <code>GET</code> method.
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {

    resp.setContentType("text/html;charset=UTF-8");
    PrintWriter out = resp.getWriter();
    try {
      Value srcValue = getRequestSrc(req);

      String extraBodyContent = "If you see the string <b>abacusbatman</b> below then javascript compilation was successful.<br>" +
          "<div id=\"p1\"></div>" +
          "If you see a <b>Hello world</b> below then soy compilation was successful.<br>" +
          foo();
      String markup = generateMarkup(srcValue, extraBodyContent);
      out.append(markup);
    } catch (JsCompileException jce) {
      out.append("Error compiling javascript: " + jce.toString());
    } finally {
      out.close();
    }
  }

  // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

  /**
   * Handles the HTTP <code>POST</code> method.
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    // Disable post requests to main servlet.
    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
  }

  /**
   * Returns a short description of the servlet.
   * @return a String containing servlet description
   */
  @Override
  public String getServletInfo() {
    return "Main servlet for serving page.";
  }// </editor-fold>

  public enum Param {
    SRC
  }

  public enum Value {
    HTML_JS_EMBED("html"), // Default value
    JS("js"),
    HTML_JS_SCRIPT("htmlscr");

    private Value(String value) {
      this.value = value;
    }
    private String value;

    public String getValue() {
      return value.toLowerCase();
    }
  }

  /**
   * Return the src attribute of the request or the default value if none requested.
   * @param req Request
   * @return The value.
   */
  private Value getRequestSrc(HttpServletRequest req) {
    String srcParam = Param.SRC.toString().toLowerCase();
    String srcValue = req.getParameter(srcParam);
    Value ret = Value.HTML_JS_EMBED;
    for (Value v : Value.values()) {
      if (v.getValue().equals(srcValue)) {
        ret = v;
      }
    }
    return ret;
  }

  private String generateMarkup(Value type, String extraBodyContent) throws JsCompileException {
    StringBuffer buffer = new StringBuffer();
    // Generate the javascript source.
    if (type != Value.HTML_JS_EMBED) {
      return "Not supported yet.";
    }
    buffer.append(buildHTML("Douchebag main page",
                            getCss(),
                            jscompileUtil.compile(), // Default is HTML_JS_EMBED
                            extraBodyContent));
    return buffer.toString();
  }

  private String getCss() {
    String css = "";
    try {
      css = jscompileUtil.getCss();
    } catch (JsCompileException jce) {
      // Return no css in case of error.
      //logger.debug("Exception adding css tag: " + e.toString());
      System.out.println("Exception adding css tag: " + jce.toString());
    }
    return css;
  }

  public static StringBuffer WrapWithTag(StringBuffer content, String tag) {
    StringBuffer ret = content;
    ret.insert(0, "<" + tag + ">");
    ret.append("</" + tag + ">");
    return ret;
  }

  public static StringBuffer WrapWithTag(String content, String tag) {
    return WrapWithTag(new StringBuffer(content), tag);
  }

  public static StringBuffer WrapWithTag(String content, String startTag,
      String endTag) {
    StringBuffer ret = new StringBuffer(content);
    ret.insert(0, startTag);
    ret.append(endTag);
    return ret;
  }

  public static String buildHTML(String title, String css, String script, String body) {
    // Set the default values so we don't eat an NPE.
    if (body == null) body = "Sample body content";
    if (title == null) title = "Sample title";
    if (script == null) script = "alert('script not found');";
    if (css == null) css = ".body { background-color: red; }";

    // A call to js main start function; appended in the body.
    String jsMainCall = "<script>window['_main_']();</script>";


    StringBuffer titleElement = WrapWithTag(title, "title");
    StringBuffer cssElement = WrapWithTag(css,
        "<style type=\"text/css\">", "</style>");
    StringBuffer scriptElement = WrapWithTag(script,
        "<script type=\"text/javascript\">", "</script>");
    StringBuffer bodyElement = WrapWithTag(body + jsMainCall, "body");

    // Build the HTML structure.
    // title, css, script flat in head.
    StringBuffer headContent = new StringBuffer()
        .append(titleElement)
        .append(cssElement)
        .append(scriptElement);
    StringBuffer headElement = WrapWithTag(headContent, "head");

    StringBuffer htmlElement = WrapWithTag(headElement.append(bodyElement), "html");
    return htmlElement.toString();
  }

  private InitParams initParams;
  private JsCompileUtil jscompileUtil;

  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    // Initialize the init params.
    initParams = new InitParams(config.getServletContext());
    // Initialize the compiling unit.
    jscompileUtil = new JsCompileUtil(initParams);
  }

}
