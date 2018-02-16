package com.walterjwhite.serialization.modules.java;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Registration;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.serializers.FieldSerializer;
import com.esotericsoftware.kryo.serializers.JavaSerializer;
import java.time.LocalDateTime;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.collection.internal.PersistentBag;
import org.hibernate.collection.internal.PersistentSet;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO: migrate JPA-specific code to a JPA module.
public class KryoProvider implements Provider<Kryo> {
  private static final Logger LOGGER = LoggerFactory.getLogger(KryoProvider.class);
  protected final Kryo kryo;

  @Inject
  public KryoProvider(KryoConfiguration kryoConfiguration, EntityManager entityManager) {
    super();
    kryo = new Kryo();

    kryo.register(LocalDateTime.class, new JavaSerializer());
    for (Class serializationClass : kryoConfiguration.getSerializationClasses()) {
      if (!LocalDateTime.class.equals(serializationClass)) {
        LOGGER.info("registered:" + serializationClass);
        Registration r = kryo.register(serializationClass);

        LOGGER.info("registration:" + r.getId() + "->" + r.getType());
      }
    }

    //    kryo.setDefaultSerializer(AnotherGenericSerializer.class);
    //    kryo.addDefaultSerializer(List.class, CollectionSerializer.class);

    kryo.register(
        PersistentBag.class,
        new FieldSerializer(kryo, PersistentBag.class) {
          public Object create(Kryo kryo, Input input, Class type) {
            return (new PersistentBag(
                (SharedSessionContractImplementor) entityManager.unwrap(Session.class)));
          }
        });

    kryo.register(
        PersistentSet.class,
        new FieldSerializer(kryo, PersistentSet.class) {
          public Object create(Kryo kryo, Input input, Class type) {
            return (new PersistentSet(
                (SharedSessionContractImplementor) entityManager.unwrap(Session.class)));
          }
        });
  }

  @Override
  public Kryo get() {
    return kryo;
  }
}
