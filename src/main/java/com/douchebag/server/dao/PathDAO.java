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

package com.douchebag.server.dao;

import com.douchebag.server.entity.Path;
import com.douchebag.server.hibernate.HibernateHelper;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author debasis
 */
public class PathDAO {

  public void addPath(String p) throws Exception {
    if(!isAlreadyExist(p)){
      HibernateHelper hibernateHelper = new HibernateHelper();
      Path path = new Path();
      path.setP(p);
      hibernateHelper.save(path);
      hibernateHelper.close();
    }
  }

  public List<Path> getPaths() throws Exception {
    List<Path> paths;
    HibernateHelper hibernateHelper = new HibernateHelper();
    Query query = hibernateHelper.createQuery("from " + Path.class.getName());
    paths = hibernateHelper.list(query);
    hibernateHelper.close();
    return paths;
  }

  private boolean isAlreadyExist(String p) throws Exception {
    HibernateHelper hibernateHelper = new HibernateHelper();
    Query query = hibernateHelper.createQuery("from " + Path.class.getName() + " p where p.p = :path");
    query.setString("path", p);
    List pathList = hibernateHelper.list(query);
    hibernateHelper.close();
    return !pathList.isEmpty();
  }
}
