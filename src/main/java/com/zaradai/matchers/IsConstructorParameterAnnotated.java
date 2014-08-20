/**
 * Copyright 2014 Zaradai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zaradai.matchers;

import org.hamcrest.Factory;
import org.hamcrest.Matcher;

import java.lang.annotation.Annotation;

/**
 * A matcher to inspect constructor parameters for specified annotations.
 */
public class IsConstructorParameterAnnotated extends AbstractParameterAnnotatedMatcher {
    private final Class<?>[] parameterTypes;

    /**
     * Setup the search parameters to locate required constructor and verify annotation expectations.
     * @param annotationClass annotation that must decorate the method.
     * @param paramMatcher matcher to check the annotation for a specific parameter
     * @param parameterPosition position of parameter to be analyzed.
     * @param parameterTypes zero or more parameter types to declare wanted constructor signature.
     */
    protected IsConstructorParameterAnnotated(Class<? extends Annotation> annotationClass, Matcher<Annotation> paramMatcher,
                                              int parameterPosition, Class<?>... parameterTypes) {
        super(annotationClass, paramMatcher, parameterPosition);
        this.parameterTypes = parameterTypes;
    }

    @Override
    protected Annotation[][] getParameterAnnotations(Object item) throws Exception {
        return item.getClass().getDeclaredConstructor(parameterTypes).getParameterAnnotations();
    }

    @Override
    protected String getAnnotationSource() {
        return "constructor parameter";
    }

    /**
     * Creates a matcher that matches, if the examined {@link Object} has a constructor's parameter
     * with the specified {@link java.lang.annotation.Annotation}.
     * The {@code parameterTypes} parameter is an array of {@code Class} objects that identify
     * the constructor's formal parameter types, in declared order.
     * <p>
     * For example:
     * <pre>assertThat(myObject, is(with(Named.class, 1, String.class, String.class))</pre>
     * </p>
     *
     * @param annotation the annotation that the returned matcher will be inspecting for
     * @param parameterPosition position of target parameter
     * @param parameterTypes the parameter array
     */
    @Factory
    public static Matcher<Object> with(Class<? extends Annotation> annotation, int parameterPosition,
                                                             Class<?>... parameterTypes) {
        return new IsConstructorParameterAnnotated(annotation, null, parameterPosition, parameterTypes);
    }

    /**
     * Creates a matcher that matches, if the examined {@link Object} has a constructor's parameter with
     * the specified {@link Annotation} and the annotation contains the specified <code>param</code>.
     * The {@code parameterTypes} parameter is an array of {@code Class} objects that identify
     * the constructor's formal parameter types, in declared order.
     * <p>
     * For example:
     * <pre>assertThat(myObject, is(withParam(Named.class, "value", 1, String.class, String.class))</pre>
     * </p>
     *
     * @param annotation the annotation that the returned matcher will be inspecting for
     * @param param the param that the annotation must have
     * @param parameterPosition position of target parameter
     * @param parameterTypes the parameter array
     */
    @Factory
    public static Matcher<Object> withParam(Class<? extends Annotation> annotation, String param, int parameterPosition,
                                            Class<?>... parameterTypes) {
        return new IsConstructorParameterAnnotated(annotation, AnnotationParamMatcher.hasParam(param),
                parameterPosition, parameterTypes);
    }

    /**
     * Creates a matcher that matches, if the examined {@link Object} has a constructor's parameter with
     * the specified {@link Annotation} and the annotation contains the specified <code>param</code>.
     * with a required <code>value</code>.  A value matcher is provided to do the actual matching.
     * The {@code parameterTypes} parameter is an array of {@code Class} objects that identify
     * the constructor's formal parameter types, in declared order.
     * <p>
     * For example:
     * <pre>assertThat(myObject, is(withParamValue(Named.class, "value", is("car"), 1, String.class, String.class))</pre>
     * </p>
     *
     * @param annotation the annotation that the returned matcher will be inspecting for
     * @param param the param that the annotation must have
     * @param matcher a matcher for the parameter value
     * @param parameterPosition position of target parameter
     * @param parameterTypes the parameter array
     * @param <T> type of value to be matched
     */
    @Factory
    public static <T> Matcher<Object> withParamValue(Class<? extends Annotation> annotation, String param,
                                                     Matcher<T> matcher, int parameterPosition,
                                                     Class<?>... parameterTypes) {
        return new IsConstructorParameterAnnotated(annotation, AnnotationParamMatcher.hasParamValue(param, matcher),
                parameterPosition, parameterTypes);
    }

    /**
     * Creates a matcher that matches, if the examined {@link Object} has a constructor's parameter with
     * the specified {@link Annotation} and the annotation contains the specified <code>param</code>.
     * with a required <code>value</code>.
     * The {@code parameterTypes} parameter is an array of {@code Class} objects that identify
     * the constructor's formal parameter types, in declared order.
     * <p>
     * For example:
     * <pre>assertThat(myObject, is(withParamValue(Named.class, "value", "car", 1, String.class, String.class))</pre>
     * </p>
     *
     * @param annotation the annotation that the returned matcher will be inspecting for
     * @param param the param that the annotation must have
     * @param paramValue the parameter value must be equal to this
     * @param parameterPosition position of target parameter
     * @param parameterTypes the parameter array
     * @param <T> type of value to be matched
     */
    @Factory
    public static <T> Matcher<Object> withParamValue(Class<? extends Annotation> annotation, String param,
                                                     T paramValue, int parameterPosition,
                                                     Class<?>... parameterTypes) {
        return new IsConstructorParameterAnnotated(annotation, AnnotationParamMatcher.hasParamValue(param, paramValue),
                parameterPosition, parameterTypes);
    }
}
