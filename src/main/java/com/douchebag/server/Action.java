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

package com.douchebag.server;

import com.douchebag.server.dao.PathDAO;
import com.douchebag.server.entity.Path;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author debasis
 */
public class Action extends HttpServlet {  

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
    // Disable get request
    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);    
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
    // get the type of action
    String t = request.getParameter("t");
    if(t.equals("a")){
      // add path
      String p = request.getParameter("p");
      try{
        PathDAO pathDao = new PathDAO();
        pathDao.addPath(p);
        response.setStatus(HttpServletResponse.SC_OK);
      }
      catch(Exception ex){
        ex.printStackTrace();
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      }
    }
    else if(t.equals("gf")){
      // get files
      PrintWriter out = response.getWriter();
      try{
        PathDAO pathDao = new PathDAO();
        List<Path> paths = pathDao.getPaths();        
        
        for(Path path : paths){
          out.println(path.getId() + "," + path.getP());
        }
        response.setStatus(HttpServletResponse.SC_OK);
      }
      catch(Exception ex){
        ex.printStackTrace();
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      }
      finally{
        out.close();
      }
    }
    else{
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }
  }

  /**
   * Returns a short description of the servlet.
   * @return a String containing servlet description
   */
  @Override
  public String getServletInfo() {
      return "Action servlet to do all actions";
  }

  private String getFilesInSimpleFormat() {
    throw new UnsupportedOperationException("Not yet implemented");
  }
}
