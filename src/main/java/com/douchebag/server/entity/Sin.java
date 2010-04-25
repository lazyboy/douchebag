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
@Table(name = "sin")
@NamedQueries({@NamedQuery(name = "Sin.findAll", query = "SELECT s FROM Sin s"), @NamedQuery(name = "Sin.findById", query = "SELECT s FROM Sin s WHERE s.id = :id"), @NamedQuery(name = "Sin.findByStart", query = "SELECT s FROM Sin s WHERE s.start = :start"), @NamedQuery(name = "Sin.findByEnd", query = "SELECT s FROM Sin s WHERE s.end = :end"), @NamedQuery(name = "Sin.findByLength", query = "SELECT s FROM Sin s WHERE s.length = :length"), @NamedQuery(name = "Sin.findByRate", query = "SELECT s FROM Sin s WHERE s.rate = :rate")})
public class Sin implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id")
  private Integer id;
  @Basic(optional = false)
  @Lob
  @Column(name = "desc")
  private String desc;
  @Basic(optional = false)
  @Column(name = "start")
  private int start;
  @Basic(optional = false)
  @Column(name = "end")
  private int end;
  @Basic(optional = false)
  @Column(name = "length")
  private int length;
  @Basic(optional = false)
  @Column(name = "rate")
  private int rate;
  @JoinTable(name = "sin_path", joinColumns = {@JoinColumn(name = "fk_sin_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "fk_path_id", referencedColumnName = "id")})
  @ManyToMany
  private List<Path> pathCollection;
  @ManyToMany(mappedBy = "sinCollection")
  private List<Droid> droidCollection;

  public Sin() {
  }

  public Sin(Integer id) {
    this.id = id;
  }

  public Sin(Integer id, String desc, int start, int end, int length, int rate) {
    this.id = id;
    this.desc = desc;
    this.start = start;
    this.end = end;
    this.length = length;
    this.rate = rate;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public int getStart() {
    return start;
  }

  public void setStart(int start) {
    this.start = start;
  }

  public int getEnd() {
    return end;
  }

  public void setEnd(int end) {
    this.end = end;
  }

  public int getLength() {
    return length;
  }

  public void setLength(int length) {
    this.length = length;
  }

  public int getRate() {
    return rate;
  }

  public void setRate(int rate) {
    this.rate = rate;
  }

  public List<Path> getPathCollection() {
    return pathCollection;
  }

  public void setPathCollection(List<Path> pathCollection) {
    this.pathCollection = pathCollection;
  }

  public List<Droid> getDroidCollection() {
    return droidCollection;
  }

  public void setDroidCollection(List<Droid> droidCollection) {
    this.droidCollection = droidCollection;
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
    if (!(object instanceof Sin)) {
      return false;
    }
    Sin other = (Sin) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.douchebag.server.entity.Sin[id=" + id + "]";
  }

}
