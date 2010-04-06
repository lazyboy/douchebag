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
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
//import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lazyboy
 */
public class Main extends HttpServlet {

  /**
   * Processes requests for HTTP <code>GET</code>
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {

    resp.setContentType("text/html;charset=UTF-8");
    PrintWriter out = resp.getWriter();
    try {
      Value srcValue = getRequestSrc(req);
      String markup = generateMarkup(srcValue);
      out.append(markup);
    } finally {
      out.close();
    }
  }

  // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
  /**
   * Handles the HTTP <code>GET</code> method.
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    processRequest(request, response);
  }

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
    return "Short description";
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
  private String generateMarkup(Value type) {
    StringBuffer buffer = new StringBuffer();
    // Generate the javascript source.
    if (type != Value.HTML_JS_EMBED) {
      return "Not supported yet.";
    }
    buffer.append(WrapHtmlScript(jscompileUtil.compile())); // Default is HTML_JS_EMBED
    return buffer.toString();
  }

  public static StringBuffer WrapWithTag(StringBuffer content, String tag) {
    StringBuffer ret = content;
    ret.insert(0, "<" + tag + ">");
    ret.append("</" + tag + ">");
    return ret;
  }

  public static StringBuffer WrapScriptTag(StringBuffer jsContent) {
    StringBuffer ret = jsContent;
    ret.insert(0, "<script type=\"text/javascript\">");
    ret.append("</script>");
    return ret;
  }

  public static String WrapHtmlScript(String script) {
    StringBuffer bodyRaw = new StringBuffer().append("Sample body content");
    StringBuffer titleRaw = new StringBuffer().append("Sample title content");
    StringBuffer scriptRaw = new StringBuffer().append(script);

    StringBuffer head = WrapWithTag(
            WrapWithTag(titleRaw, "title").append(WrapScriptTag(scriptRaw)),
            "head");

    // Add call to main in body
    bodyRaw.append("<script>window['_main_']();</script>");
    StringBuffer body = WrapWithTag(bodyRaw, "body");
    StringBuffer html = WrapWithTag(head.append(body), "html");
    return html.toString();
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
