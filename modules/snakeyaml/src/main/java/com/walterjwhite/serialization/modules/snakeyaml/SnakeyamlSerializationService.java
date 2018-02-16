package com.walterjwhite.serialization.modules.snakeyaml;

import com.walterjwhite.serialization.api.service.SerializationService;
import java.io.*;
import java.nio.charset.Charset;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

public class SnakeyamlSerializationService implements SerializationService<Serializable, Class> {
  /** TODO: inject this later properly configured */
  protected transient Yaml yaml = new Yaml(new MessageRepresenter(), new DumperOptions());

  @Override
  public byte[] serialize(Serializable data) {
    return (yaml.dump(data).getBytes(Charset.defaultCharset()));
  }

  @Override
  public void serialize(Serializable data, OutputStream outputStream) {
    yaml.dump(data, new OutputStreamWriter(outputStream));
  }

  @Override
  public Serializable deserialize(byte[] data) {
    // return (yaml.load(new ByteArrayInputStream(data)));
    return (deserialize(new ByteArrayInputStream(data)));
  }

  @Override
  public Serializable deserialize(byte[] data, Class entityClass) throws IOException {
    return (deserialize(data));
  }

  @Override
  public Serializable deserialize(InputStream inputStream, Class entityClass) throws IOException {
    return ((Serializable) yaml.loadAs(inputStream, entityClass));
  }

  @Override
  public Serializable deserialize(InputStream inputStream) {
    return ((Serializable) yaml.load(inputStream));
  }
}
