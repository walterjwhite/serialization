package com.walterjwhite.serialization.modules.snakeyaml.providers.guice;

import com.google.inject.AbstractModule;
import com.walterjwhite.serialization.api.service.SerializationService;
import com.walterjwhite.serialization.modules.snakeyaml.SnakeyamlSerializationService;

public class SnakeyamlSerializationServiceModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(SerializationService.class).to(SnakeyamlSerializationService.class);
  }
}
