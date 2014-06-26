/**
 * Parameter
 * (C) 2012 Washington University School of Medicine
 * All Rights Reserved
 *
 * Released under the Simplified BSD License
 *
 * Created on 10/12/12 by rherri01
 */
package org.wurstworks.tools.pinto;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RUNTIME)
public @interface Parameter {
    /**
     * Specifies the short option for this command-line parameter.
     * @return The value of the short option.
     */
    public String value();
    /**
     * Specifies the long option for this command-line parameter.
     * @return The value of the long option.
     */
    public String longOption() default "";

    /**
     * Sets the help text to display for the parameter.
     * @return The help text.
     */
    public String help() default "";

    /**
     * Indicates whether this command-line parameter is required to be specified. By default, parameters are not
     * required.
     */
    public boolean required() default false;

    /**
     * The number of accepted arguments for this parameter. This is specified using the {@link ArgCount} enumeration. In
     * the case of {@link ArgCount#SpecificCount}, you can set the specific count to be used with this attribute. The
     * default value for this is {@link ArgCount#OneArgument}.
     * @return The number of accepted arguments for this parameter.
     */
    public ArgCount argCount() default ArgCount.OneArgument;

    /**
     * Whe the {@link #argCount()} attribute is set to {@link ArgCount#SpecificCount}, this attribute
     * specifies the value for the specific number of arguments for this parameter.
     * @return The specific number of arguments for this parameter.
     */
    public int exactArgCount() default 0;

    /**
     * The type into which the parameter arguments should be coerced.
     * @return The type into which the parameter arguments should be coerced.
     */
    public Class<?> type() default boolean.class;
}
