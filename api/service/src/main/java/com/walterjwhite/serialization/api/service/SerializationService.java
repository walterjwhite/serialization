package com.walterjwhite.serialization.api.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

public interface SerializationService<
    DataType extends Serializable, EntityType extends Serializable> {
  byte[] serialize(DataType data) throws Exception;

  void serialize(DataType data, OutputStream outputStream) throws Exception;

  DataType deserialize(byte[] data) throws IOException;

  DataType deserialize(InputStream inputStream) throws IOException;

  DataType deserialize(
      byte[] data, /*final Class<? extends AbstractEntity> entityClass*/ EntityType entityType)
      throws IOException;

  DataType deserialize(
      InputStream inputStream, /*final Class<? extends AbstractEntity> entityClass*/
      EntityType entityType)
      throws IOException;
  //
  //  HandlerType get();
}
