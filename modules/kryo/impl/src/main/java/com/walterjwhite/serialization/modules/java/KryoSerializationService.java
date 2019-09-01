package com.walterjwhite.serialization.modules.java;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.walterjwhite.serialization.api.service.SerializationService;
import java.io.*;
import javax.inject.Inject;

public class KryoSerializationService implements SerializationService<Serializable, Class> {
  protected final Kryo kryo;

  @Inject
  public KryoSerializationService(Kryo kryo) {
    super();
    this.kryo = kryo;
  }

  @Override
  public void serialize(Serializable data, OutputStream outputStream) {
    try (final Output output = new Output(outputStream)) {
      // kryo.writeClassObject(output, data);
      kryo.writeClassAndObject(output, data);
    }
  }

  @Override
  public Serializable deserialize(InputStream inputStream, Class entityType) throws IOException {
    try (final Input input = new Input(inputStream)) {
      //      return ((Serializable) kryo.readClassObject(input, entityType));
      //      return ((Serializable) kryo.readClassAndObject(input, entityType));
      return ((Serializable) kryo.readClassAndObject(input));
    }
  }

  @Override
  public Serializable deserialize(InputStream inputStream) {
    //    return (deserialize(inputStream, null));
    throw (new UnsupportedOperationException("Need a class."));
  }
}
