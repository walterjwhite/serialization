package com.walterjwhite.serialization.jackson.guice;

import com.google.inject.AbstractModule;
import com.walterjwhite.serialization.api.service.JSONSerializationService;
import com.walterjwhite.serialization.api.service.SerializationService;
import com.walterjwhite.serialization.modules.jackson.JacksonSerializationService;

public class JacksonSerializationServiceModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(SerializationService.class).to(JacksonSerializationService.class);
    bind(JSONSerializationService.class).to(JacksonSerializationService.class);
  }
}
