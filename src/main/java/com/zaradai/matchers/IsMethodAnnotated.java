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
 * A matcher to inspect methods for specified annotations.
 */
public class IsMethodAnnotated extends AbstractAnnotationMatcher {
    private final String methodName;
    private final Class<?>[] methodParameterTypes;

    /**
     * Setup the search parameters to locate required function and verify annotation expectations.
     * @param annotationClass annotation that must decorate the method.
     * @param paramMatcher matcher to check for specified annotation parameter value
     * @param methodName name of method to look for
     * @param methodParameterTypes zero or more method parameter types to define wanted method signature
     */
    protected IsMethodAnnotated(Class<? extends Annotation> annotationClass, Matcher<Annotation> paramMatcher,
                                String methodName, Class<?>... methodParameterTypes) {
        super(annotationClass, paramMatcher);

        this.methodName = methodName;
        this.methodParameterTypes = methodParameterTypes;
    }

    @Override
    protected Annotation getAnnotation(Object item, Class<? extends Annotation> annotationClass) throws Exception {
        return item.getClass().getDeclaredMethod(methodName, methodParameterTypes).getAnnotation(annotationClass);
    }

    @Override
    protected String getAnnotationSource() {
        return "method";
    }

    /**
     * Creates a matcher that matches, if the examined {@link Object} has a method with
     * the specified {@link Annotation}. The {@code name} parameter is a
     * {@code String} that specifies the simple name of the desired
     * method, and the {@code parameterTypes} parameter is an array of
     * {@code Class} objects that identify the method's formal parameter
     * types, in declared order.  If more than one method with the same
     * parameter types is declared in a class, and one of these methods has a
     * return type that is more specific than any of the others, that method is
     * returned; otherwise one of the methods is chosen arbitrarily.
     * <p>
     * For example:
     * <pre>assertThat(myObject, is(with(Override.class, "toString"))</pre>
     * </p>
     *
     * @param annotation the annotation that the returned matcher will be inspecting for
     * @param methodName the name of the method
     * @param methodParameterTypes the parameter array
     */
    @Factory
    public static Matcher<Object> with(Class<? extends Annotation> annotation, String methodName,
                                       Class<?>... methodParameterTypes) {
        return new IsMethodAnnotated(annotation, null, methodName, methodParameterTypes);
    }

    /**
     * Creates a matcher that matches, if the examined {@link Object} has a method with
     * the specified {@link Annotation} and the annotation contains the specified <code>param</code>.
     * The {@code name} parameter is a {@code String} that specifies the simple name of the desired
     * method, and the {@code parameterTypes} parameter is an array of
     * {@code Class} objects that identify the method's formal parameter
     * types, in declared order.  If more than one method with the same
     * parameter types is declared in a class, and one of these methods has a
     * return type that is more specific than any of the others, that method is
     * returned; otherwise one of the methods is chosen arbitrarily.
     * <p>
     * For example:
     * <pre>assertThat(myObject, is(withParam(Autowired.class, "required", "getUser", String.class))</pre>
     * </p>
     *
     * @param annotation the annotation that the returned matcher will be inspecting for
     * @param param the param that the annotation must have
     * @param methodName the name of the method
     * @param methodParameterTypes the parameter array
     */
    @Factory
    public static Matcher<Object> withParam(Class<? extends Annotation> annotation, String param, String methodName,
                                            Class<?>... methodParameterTypes) {
        return new IsMethodAnnotated(annotation, AnnotationParamMatcher.hasParam(param), methodName,
                methodParameterTypes);
    }

    /**
     * Creates a matcher that matches, if the examined {@link Object} has a method with
     * the specified {@link Annotation} and the annotation contains the specified <code>param</code>
     * with a required <code>value</code>. A value matcher is provided to do the actual matching.
     * The {@code name} parameter is a {@code String} that specifies the simple
     * name of the desired method, and the {@code parameterTypes} parameter is an array of
     * {@code Class} objects that identify the method's formal parameter
     * types, in declared order.  If more than one method with the same
     * parameter types is declared in a class, and one of these methods has a
     * return type that is more specific than any of the others, that method is
     * returned; otherwise one of the methods is chosen arbitrarily.
     * <p>
     * For example:
     * <pre>assertThat(myObject, is(withParamValue(Autowired.class, "required", is(false), "getUser", String.class))</pre>
     * </p>
     *
     * @param annotation the annotation that the returned matcher will be inspecting for
     * @param param the param that the annotation must have
     * @param matcher a matcher for the parameter value
     * @param methodName the name of the method
     * @param methodParameterTypes the parameter array
     */
    @Factory
    public static <T> Matcher<Object> withParamValue(Class<? extends Annotation> annotation, String param,
                                                     Matcher<T> matcher, String methodName,
                                                     Class<?>... methodParameterTypes) {
        return new IsMethodAnnotated(annotation, AnnotationParamMatcher.hasParamValue(param, matcher),
                methodName, methodParameterTypes);
    }

    /**
     * Creates a matcher that matches, if the examined {@link Object} has a method with
     * the specified {@link Annotation} and the annotation contains the specified <code>param</code>
     * with a required <code>value</code>. The {@code name} parameter is a {@code String} that specifies the simple
     * name of the desired method, and the {@code parameterTypes} parameter is an array of
     * {@code Class} objects that identify the method's formal parameter
     * types, in declared order.  If more than one method with the same
     * parameter types is declared in a class, and one of these methods has a
     * return type that is more specific than any of the others, that method is
     * returned; otherwise one of the methods is chosen arbitrarily.
     * <p>
     * For example:
     * <pre>assertThat(myObject, is(withParamValue(Autowired.class, "required", false, "getUser", String.class))</pre>
     * </p>
     *
     * @param annotation the annotation that the returned matcher will be inspecting for
     * @param param the param that the annotation must have
     * @param paramValue the parameter value must be equal to this
     * @param methodName the name of the method
     * @param methodParameterTypes the parameter array
     * @param <T> type of value to be matched
     */
    @Factory
    public static <T> Matcher<Object> withParamValue(Class<? extends Annotation> annotation, String param, T paramValue,
                                                     String methodName, Class<?>... methodParameterTypes) {
        return new IsMethodAnnotated(annotation, AnnotationParamMatcher.hasParamValue(param, paramValue),
                methodName, methodParameterTypes);
    }
}
