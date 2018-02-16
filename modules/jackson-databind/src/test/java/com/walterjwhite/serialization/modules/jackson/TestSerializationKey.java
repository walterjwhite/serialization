package com.walterjwhite.serialization.modules.jackson;

import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
public class TestSerializationKey implements Serializable {
  //  @Column(nullable = false)
  protected String firstName;

  //  @Column(nullable = false)
  protected String lastName;

  public TestSerializationKey(String firstName, String lastName) {
    super();
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public TestSerializationKey() {
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
}
