package com.walterjwhite.serialization.modules.java;

import com.esotericsoftware.kryo.Kryo;
import com.google.inject.AbstractModule;
import com.walterjwhite.serialization.api.service.SerializationService;

public class KryoSerializationServiceModule extends AbstractModule {
  protected final KryoConfiguration kryoConfiguration = new KryoConfiguration();

  @Override
  protected void configure() {
    bind(SerializationService.class).to(KryoSerializationService.class);
    bind(KryoConfiguration.class).toInstance(kryoConfiguration);
    bind(Kryo.class).toProvider(KryoProvider.class);

    // TODO: implement properly
    // initializeSerializationClasses();
  }

  //  protected void initializeSerializationClasses() {
  //    // entity classes
  //    kryoConfiguration
  //        .getSerializationClasses()
  //        .addAll(propertyManager.getReflections().getTypesAnnotatedWith(Entity.class));
  //
  //    // columns
  //    for (final Field field :
  //        PropertyManager.REFLECTIONS_INSTANCE.getFieldsAnnotatedWith(Column.class)) {
  //      if (Object.class.isAssignableFrom(field.getType())) {
  //        kryoConfiguration.getSerializationClasses().add(field.getType());
  //      }
  //    }
  //  }
}
