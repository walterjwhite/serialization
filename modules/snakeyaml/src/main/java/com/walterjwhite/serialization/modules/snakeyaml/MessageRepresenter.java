package com.walterjwhite.serialization.modules.snakeyaml;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.serialization.api.annotation.PrivateField;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.introspector.Property;
import org.yaml.snakeyaml.nodes.NodeTuple;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

public class MessageRepresenter extends Representer {
  private static final Logger LOGGER = LoggerFactory.getLogger(MessageRepresenter.class);

  public MessageRepresenter() {
    /**
     * TODO: this doesn't do anything instead, iterate through all model objects inheriting from
     * AbstractMessage / portage and then add the class/tag combination
     */
    addClassTag(getClass(), new Tag("!" + getClass().getSimpleName()));
  }

  @Override
  protected Set<Property> getProperties(Class<?> type) {
    final Set<Property> properties = super.getProperties(type);
    final Set<Property> safeProperties = new HashSet<>();

    for (final Property property : properties) {
      try {
        if (isFieldPrivate(type, property)) {
          LOGGER.debug("field is NOT safe:" + property.getName());
        } else {
          LOGGER.debug("field is safe:" + property.getName());

          safeProperties.add(property);
        }
      } catch (Exception e) {
        LOGGER.error("error determining if field was private", e);
      }
    }

    return (safeProperties);
  }

  private static boolean isFieldPrivate(final Class<?> type, final Property property) {
    // if (!type.isAssignableFrom(AbstractMessage.class)) {
    if (!AbstractEntity.class.isAssignableFrom(type)) {
      // throw(new RUntimeException(""))
      LOGGER.warn(
          "cannot determine, so marking as private:"
              + type.getSimpleName()
              + ":"
              + property.getName());
      return (true);
    }

    try {
      final Field field = type.getDeclaredField(property.getName());
      // type.getField(property.getName());

      return (field.isAnnotationPresent(PrivateField.class));
    } catch (NoSuchFieldException e) {
      LOGGER.debug("field does not exist here:" + type.getSimpleName());

      return (isFieldPrivate(type.getSuperclass(), property));
    }
  }

  @Override
  protected NodeTuple representJavaBeanProperty(
      Object javaBean, Property property, Object propertyValue, Tag customTag) {
    if (propertyValue == null) {
      return null;
    }

    return super.representJavaBeanProperty(javaBean, property, propertyValue, customTag);
  }
}
