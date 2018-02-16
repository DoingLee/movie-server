package com.homework.common.mongo;


import org.apache.commons.lang3.StringUtils;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.converters.BigDecimalConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class MorphiaInit {

    private static Logger LOG = LoggerFactory.getLogger(MorphiaInit.class);

    public MorphiaInit(Morphia morphia, Datastore datastore, List<String> packages) {
        this(morphia, datastore, packages, null);
    }

    public MorphiaInit(Morphia morphia, Datastore datastore, List<String> packages, List<Class> classes) {

        if (!CollectionUtils.isEmpty(packages)) {
            for (String packageName : packages) {
                if (StringUtils.isNotBlank(packageName)) {
                    morphia.mapPackage(packageName);
                }
            }
        }
        if (!CollectionUtils.isEmpty(classes)) {
            for (Class clazz : classes) {
                morphia.map(clazz);
            }
        }

        morphia.getMapper().getConverters().addConverter(BigDecimalConverter.class);

        datastore.ensureCaps();
        datastore.ensureIndexes();

    }

}
