package com.walterjwhite.modules.serialization.kryo.guice;

import com.esotericsoftware.kryo.Kryo;
import com.google.inject.AbstractModule;
import com.walterjwhite.serialization.api.service.SerializationService;
import com.walterjwhite.serialization.modules.java.KryoConfiguration;
import com.walterjwhite.serialization.modules.java.KryoProvider;
import com.walterjwhite.serialization.modules.java.KryoSerializationService;

public class KryoSerializationServiceModule extends AbstractModule {
  protected final KryoConfiguration kryoConfiguration = new KryoConfiguration();

  @Override
  protected void configure() {
    bind(SerializationService.class).to(KryoSerializationService.class);
    bind(KryoConfiguration.class).toInstance(kryoConfiguration);
    bind(Kryo.class).toProvider(KryoProvider.class);
  }
}
