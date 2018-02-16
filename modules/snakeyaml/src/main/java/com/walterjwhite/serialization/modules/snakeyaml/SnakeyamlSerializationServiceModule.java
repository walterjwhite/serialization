package com.walterjwhite.serialization.modules.snakeyaml;

import com.google.inject.AbstractModule;
import com.walterjwhite.serialization.api.service.SerializationService;

public class SnakeyamlSerializationServiceModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(SerializationService.class).to(SnakeyamlSerializationService.class);
  }
}
