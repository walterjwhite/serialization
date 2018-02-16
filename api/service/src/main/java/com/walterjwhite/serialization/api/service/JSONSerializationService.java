package com.walterjwhite.serialization.api.service;

import java.io.Serializable;

public interface JSONSerializationService<
        DataType extends Serializable, EntityType extends Serializable>
    extends SerializationService<DataType, EntityType> {}
