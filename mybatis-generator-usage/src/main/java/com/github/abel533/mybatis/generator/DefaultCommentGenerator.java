package com.github.abel533.mybatis.generator;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;

public class DefaultCommentGenerator
        extends org.mybatis.generator.internal.DefaultCommentGenerator {

    @Override
    public void addFieldComment(Field field,
            IntrospectedTable introspectedTable,
            IntrospectedColumn introspectedColumn) {
        if (introspectedColumn.getRemarks() != null
                && !introspectedColumn.getRemarks().equals("")) {
            field.addJavaDocLine("/** " + introspectedColumn.getRemarks() + " */");
//            field.addJavaDocLine(" * " + introspectedColumn.getRemarks());
//            addJavadocTag(field, false);
//            field.addJavaDocLine(" */");
        }
    }
}
