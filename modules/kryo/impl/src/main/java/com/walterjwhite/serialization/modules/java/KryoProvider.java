package com.walterjwhite.serialization.modules.java;

import com.esotericsoftware.kryo.Kryo;
import java.util.Arrays;
import javax.inject.Inject;
import javax.inject.Provider;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

// TODO: migrate JPA-specific code to a JPA module.
@RequiredArgsConstructor
@Getter
public class KryoProvider implements Provider<Kryo> {
  protected final Kryo kryo = new Kryo();

  @Inject
  public KryoProvider(KryoConfiguration kryoConfiguration) {
    super();

    register(kryoConfiguration);
  }

  protected void register(KryoConfiguration kryoConfiguration) {
    Arrays.stream(KryoTypeMapping.values())
        .forEach(kryoTypeMapping -> kryoTypeMapping.register(kryo, kryoConfiguration));
  }

  //    kryo.setDefaultSerializer(AnotherGenericSerializer.class);
  //    kryo.addDefaultSerializer(List.class, CollectionSerializer.class);

  @Override
  public Kryo get() {
    return kryo;
  }
}
