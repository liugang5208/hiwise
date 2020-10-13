package com.sky.hiwise.autoconfigure.annotation;

import com.sky.hiwise.autoconfigure.configuration.HelloConfiguration;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class TestImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{HelloConfiguration.class.getName()};
    }
}
