package com.walterjwhite.serialization.modules.jackson;

import com.walterjwhite.google.guice.GuiceHelper;
import com.walterjwhite.serialization.api.service.SerializationService;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestDefaultMessageSerializationService {
  private static final Logger LOGGER =
      LoggerFactory.getLogger(TestDefaultMessageSerializationService.class);

  protected SerializationService serializationService;

  @Before
  public void before() throws Exception {
    GuiceHelper.addModules(new JacksonSerializationServiceModule());
    GuiceHelper.setup();

    serializationService = GuiceHelper.getGuiceInjector().getInstance(SerializationService.class);
  }

  @Test
  public void testSerialization() throws Exception {
    final TestSerialization testSerialization =
        new TestSerialization("Fred", "Rogers", 65, LocalDateTime.now());

    final byte[] data = serializationService.serialize(testSerialization);
    final File sourceFile = new File("/tmp/serialization-test");
    IOUtils.write(data, new FileOutputStream(sourceFile));

    final Object deserialized =
        serializationService.deserialize(
            FileUtils.readFileToByteArray(new File("/tmp/serialization-test")));
    System.out.println("deserialized:" + deserialized);
    System.out.println("deserialized:" + deserialized.getClass());
    LOGGER.info("deserialized:" + deserialized);

    //    System.out.println("deserialized:" +
    // serializationService.get().readValue(FileUtils.readFileToByteArray(new
    // File("/tmp/serialization-test")), TestSerialization.class));

    sourceFile.delete();
  }
}
