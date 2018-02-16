package com.walterjwhite.serialization.modules.snakeymal;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import java.util.Objects;

public class TestSerialization extends AbstractEntity {
  protected String firstName;
  protected String lastName;
  protected int age;

  public TestSerialization(String firstName, String lastName, int age) {
    super();
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
  }

  public TestSerialization() {
    super();
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TestSerialization that = (TestSerialization) o;
    return age == that.age
        && Objects.equals(firstName, that.firstName)
        && Objects.equals(lastName, that.lastName);
  }

  @Override
  public int hashCode() {

    return Objects.hash(firstName, lastName, age);
  }
}
