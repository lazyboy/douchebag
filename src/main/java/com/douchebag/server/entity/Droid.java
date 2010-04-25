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

package com.douchebag.server.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author debasis
 */
@Entity
@Table(name = "droid")
@NamedQueries({@NamedQuery(name = "Droid.findAll", query = "SELECT d FROM Droid d"), @NamedQuery(name = "Droid.findById", query = "SELECT d FROM Droid d WHERE d.id = :id"), @NamedQuery(name = "Droid.findByType", query = "SELECT d FROM Droid d WHERE d.type = :type")})
public class Droid implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id")
  private Integer id;
  @Basic(optional = false)
  @Column(name = "type")
  private int type;
  @Basic(optional = false)
  @Lob
  @Column(name = "name")
  private String name;
  @Basic(optional = false)
  @Lob
  @Column(name = "desc")
  private String desc;
  @Basic(optional = false)
  @Lob
  @Column(name = "tags")
  private String tags;
  @Basic(optional = false)
  @Lob
  @Column(name = "url")
  private String url;
  @Basic(optional = false)
  @Lob
  @Column(name = "localurl")
  private String localurl;
  @JoinTable(name = "droid_sin", joinColumns = {@JoinColumn(name = "fk_droid_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "fk_sin_id", referencedColumnName = "id")})
  @ManyToMany
  private List<Sin> sinCollection;

  public Droid() {
  }

  public Droid(Integer id) {
    this.id = id;
  }

  public Droid(Integer id, int type, String name, String desc, String tags, String url, String localurl) {
    this.id = id;
    this.type = type;
    this.name = name;
    this.desc = desc;
    this.tags = tags;
    this.url = url;
    this.localurl = localurl;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public String getTags() {
    return tags;
  }

  public void setTags(String tags) {
    this.tags = tags;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getLocalurl() {
    return localurl;
  }

  public void setLocalurl(String localurl) {
    this.localurl = localurl;
  }

  public List<Sin> getSinCollection() {
    return sinCollection;
  }

  public void setSinCollection(List<Sin> sinCollection) {
    this.sinCollection = sinCollection;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (id != null ? id.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Droid)) {
      return false;
    }
    Droid other = (Droid) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.douchebag.server.entity.Droid[id=" + id + "]";
  }

}
