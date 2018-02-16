package com.walterjwhite.serialization.modules.jackson;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import java.time.LocalDateTime;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "class")
public class TestSerialization extends AbstractEntity {
  protected TestSerializationKey id;
  protected int age;
  protected LocalDateTime creationDateTime;

  public TestSerialization(
      String firstName, String lastName, int age, LocalDateTime creationDateTime) {
    super();
    this.id = new TestSerializationKey(firstName, lastName);
    this.age = age;
    this.creationDateTime = creationDateTime;
  }

  public TestSerialization() {
    super();
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public LocalDateTime getCreationDateTime() {
    return creationDateTime;
  }

  public void setCreationDateTime(LocalDateTime creationDateTime) {
    this.creationDateTime = creationDateTime;
  }
}
