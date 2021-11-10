package com.example.UUIDTest;

import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

@Table("domain_object_long_id")
public class DomainObjectLongId implements Persistable<Long>{

  @Id
  private Long id;
  private String value;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  @Override
  public boolean isNew() {
    return id == null;
  }
}
