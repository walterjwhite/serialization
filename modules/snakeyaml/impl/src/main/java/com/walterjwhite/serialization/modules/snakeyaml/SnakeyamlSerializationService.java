package com.walterjwhite.serialization.modules.snakeyaml;

import com.walterjwhite.serialization.api.service.SerializationService;
import java.io.*;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

public class SnakeyamlSerializationService implements SerializationService {
  /** TODO: inject this later properly configured */
  protected transient Yaml yaml = new Yaml(new MessageRepresenter(), new DumperOptions());

  @Override
  public void serialize(Serializable data, OutputStream outputStream) {
    yaml.dump(data, new OutputStreamWriter(outputStream));
  }

  @Override
  public <EntityType extends Serializable> EntityType deserialize(
      InputStream inputStream, Class<EntityType> entityClass) {
    return yaml.loadAs(inputStream, entityClass);
  }

  @Override
  public Serializable deserialize(InputStream inputStream) {
    return ((Serializable) yaml.load(inputStream));
  }
}
