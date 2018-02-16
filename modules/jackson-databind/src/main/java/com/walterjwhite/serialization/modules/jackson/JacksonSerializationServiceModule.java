package com.walterjwhite.serialization.modules.jackson;

import com.google.inject.AbstractModule;
import com.walterjwhite.serialization.api.service.JSONSerializationService;
import com.walterjwhite.serialization.api.service.SerializationService;

public class JacksonSerializationServiceModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(SerializationService.class).to(JacksonSerializationService.class);
    bind(JSONSerializationService.class).to(JacksonSerializationService.class);
  }
}
