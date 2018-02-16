package com.walterjwhite.serialization.modules.java;

import java.util.LinkedHashSet;
import java.util.Set;

public class KryoConfiguration {
  // TODO: share this across implementations?
  protected final Set<Class> serializationClasses = new LinkedHashSet<>();

  public Set<Class> getSerializationClasses() {
    return serializationClasses;
  }
}
