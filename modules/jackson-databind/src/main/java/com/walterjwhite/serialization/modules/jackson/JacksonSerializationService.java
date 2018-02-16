package com.walterjwhite.serialization.modules.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.walterjwhite.serialization.api.service.JSONSerializationService;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JacksonSerializationService implements JSONSerializationService<Serializable, Class> {
  /** TODO: perhaps we may need to customize ObjectMapper, possibly inject this? */
  protected final ObjectMapper mapper;

  public JacksonSerializationService() {
    super();
    this.mapper = new ObjectMapper();
    JavaTimeModule javaTimeModule = new JavaTimeModule();
    // Hack time module to allow 'Z' at the end of string (i.e. javascript json's)
    javaTimeModule.addDeserializer(
        LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ISO_DATE_TIME));
    mapper.registerModule(javaTimeModule);
    mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

    // ignore id / version fields
    // TODO: ignore null fields
    //    mapper.setDefaultVisibility(
    //        JsonAutoDetect.Value.construct(
    //            PropertyAccessor.ALL, JsonAutoDetect.Visibility.NON_PRIVATE));
  }

  @Override
  public byte[] serialize(Serializable data) throws Exception {
    return (mapper.writeValueAsBytes(data));
  }

  @Override
  public void serialize(Serializable data, OutputStream outputStream) throws Exception {
    outputStream.write(mapper.writeValueAsBytes(data));
  }

  @Override
  public Serializable deserialize(byte[] data) throws IOException {
    // mapper.writerFor(Object.class).writeValueAsBytes()
    return ((Serializable) mapper.readValue(data, Object.class));
  }

  @Override
  public Serializable deserialize(InputStream inputStream) throws IOException {
    return ((Serializable) mapper.readValue(inputStream, Object.class));
  }

  @Override
  public Serializable deserialize(byte[] data, Class entityType) throws IOException {
    return ((Serializable) mapper.readValue(data, entityType));
  }

  @Override
  public Serializable deserialize(InputStream inputStream, Class entityType) throws IOException {
    return ((Serializable) mapper.readValue(inputStream, entityType));
  }

  //  @Override
  //  public ObjectMapper get() {
  //    return(mapper);
  //  }
}
