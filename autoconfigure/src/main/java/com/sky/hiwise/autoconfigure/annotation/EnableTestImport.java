package com.sky.hiwise.autoconfigure.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(TestImportSelector.class)
public @interface EnableTestImport {

}
