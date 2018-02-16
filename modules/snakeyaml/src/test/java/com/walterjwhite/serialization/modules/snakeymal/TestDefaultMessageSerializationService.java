package com.walterjwhite.serialization.modules.snakeymal;

import com.google.inject.Injector;
import com.walterjwhite.google.guice.GuiceHelper;
import com.walterjwhite.serialization.api.service.SerializationService;
import com.walterjwhite.serialization.modules.snakeyaml.SnakeyamlSerializationServiceModule;
import java.io.File;
import java.io.FileOutputStream;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestDefaultMessageSerializationService {
  private static final Logger LOGGER =
      LoggerFactory.getLogger(TestDefaultMessageSerializationService.class);

  private static Injector GUICE_INJECTOR;

  protected SerializationService serializationService;

  @Before
  public void before() throws Exception {
    GuiceHelper.addModules(new SnakeyamlSerializationServiceModule());
    GuiceHelper.setup();

    serializationService = GuiceHelper.getGuiceInjector().getInstance(SerializationService.class);
  }

  @Test
  public void testSerialization() throws Exception {
    final TestSerialization testSerialization = new TestSerialization("Fred", "Rogers", 65);

    final byte[] data = serializationService.serialize(testSerialization);

    final File sourceFile = new File("/tmp/serialization-test");
    IOUtils.write(data, new FileOutputStream(sourceFile));

    sourceFile.delete();
  }
}
