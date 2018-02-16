package com.walterjwhite.serialization.modules.java;

import com.walterjwhite.serialization.api.service.SerializationService;
import java.io.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NativeSerializationService implements SerializationService<Serializable, Class> {
  private static final Logger LOGGER = LoggerFactory.getLogger(NativeSerializationService.class);

  @Override
  public byte[] serialize(Serializable data) {
    try (final ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
      serialize(data, baos);
      return (baos.toByteArray());
    } catch (IOException e) {
      LOGGER.error("error serializing", e);
      throw (new RuntimeException("Error serializing", e));
    }
  }

  @Override
  public void serialize(Serializable data, OutputStream outputStream) {
    try {
      try (ObjectOutputStream out = new ObjectOutputStream(outputStream)) {
        out.writeObject(data);
      }

    } catch (IOException e) {
      LOGGER.error("error serializing", e);
      throw (new RuntimeException("Error serializing", e));
    }
  }

  @Override
  public Serializable deserialize(byte[] data) {
    return (deserialize(new ByteArrayInputStream(data)));
  }

  @Override
  public Serializable deserialize(byte[] data, Class entityClass) throws IOException {
    return (deserialize(data));
  }

  @Override
  public Serializable deserialize(InputStream inputStream, Class entityClass) throws IOException {
    return (deserialize(inputStream));
  }

  @Override
  public Serializable deserialize(InputStream inputStream) {
    try {
      try (ObjectInputStream in = new ObjectInputStream(inputStream)) {
        return ((Serializable) in.readObject());
      }

    } catch (IOException | ClassNotFoundException e) {
      LOGGER.error("error serializing", e);
      throw (new RuntimeException("Error serializing", e));
    }
  }
}
