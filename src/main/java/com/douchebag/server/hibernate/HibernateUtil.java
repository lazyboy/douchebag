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
package com.douchebag.server.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 *
 * @author debasis
 */
public class HibernateUtil {

  private static final SessionFactory mSessionFactory;

  static {
    try {
      // Create the SessionFactory from standard (hibernate.cfg.xml)
      mSessionFactory = new AnnotationConfiguration()
              .configure("hibernate.cfg.xml").buildSessionFactory();
    }
    catch (Throwable ex) {
      System.out.println("Initial SessionFactory creation failed." + ex);
      throw new ExceptionInInitializerError(ex);
    }
  }

  public static SessionFactory getSessionFactory() {
    return mSessionFactory;
  }

  public static Session getSession() throws Exception {
    return mSessionFactory.openSession();
  }
}
