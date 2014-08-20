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

import org.hamcrest.Matcher;

import java.lang.annotation.Annotation;

/**
 * Contains factories for annotation matchers.
 */
public final class AnnotationMatchers {
    private AnnotationMatchers() {
    }

    /**
     * Creates a matcher that matches, if the examined {@link Object} class
     * is annotated with the specified {@link Annotation}.
     * <p>
     * For example:
     * <pre>assertThat(myObject, is(classAnnotatedWith(Entity.class))</pre>
     * </p>
     *
     * @param annotation the annotation that the returned matcher will be inspecting for
     */
    public static Matcher<Object> classAnnotatedWith(Class<? extends Annotation> annotation) {
        return IsClassAnnotated.with(annotation);
    }

    /**
     * Creates a matcher that matches, if the examined {@link Object} class
     * is annotated with the specified {@link Annotation} and it has a parameter with
     * the specified <code>param</code> name.
     * <p>
     * For example:
     * <pre>assertThat(myObject, is(classAnnotatedWithParam(Table.class, "name"))</pre>
     * </p>
     * @param annotation the annotation that the returned matcher will be inspecting for
     * @param param the name of the parameter that must exist
     */
    public static Matcher<Object> classAnnotatedWithParam(Class<? extends Annotation> annotation, String param) {
        return IsClassAnnotated.withParam(annotation, param);
    }

    /**
     * Creates a matcher that matches, if the examined {@link Object} class
     * is annotated with the specified {@link Annotation} and it has a specified parameter with
     * a required value.  The value is matched using a {@link Matcher}.
     * <p>
     * For example:
     * <pre>assertThat(myObject, is(classAnnotatedWithParamValue(Table.class, "name", is("test")))</pre>
     * </p>
     * @param annotation the annotation that the returned matcher will be inspecting for
     * @param param the name of the parameter that must exist
     * @param valueMatcher the matcher that asserts the parameter value.
     */
    public static <T> Matcher<Object> classAnnotatedWithParamValue(Class<? extends Annotation> annotation, String param,
                                                                   Matcher<T> valueMatcher) {
        return IsClassAnnotated.withParamValue(annotation, param, valueMatcher);
    }

    /**
     * Creates a matcher that matches, if the examined {@link Object} class
     * is annotated with the specified {@link Annotation} and it has a specified parameter with
     * a required value.
     * <p>
     * For example:
     * <pre>assertThat(myObject, is(classAnnotatedWithParamValue(Table.class, "name", is("test")))</pre>
     * </p>
     * @param annotation the annotation that the returned matcher will be inspecting for
     * @param param the name of the parameter that must exist
     * @param paramValue the value to match with.
     */
    public static <T> Matcher<Object> classAnnotatedWithParamValue(Class<? extends Annotation> annotation, String param,
                                                                   T paramValue) {
        return IsClassAnnotated.withParamValue(annotation, param, paramValue);
    }

    /**
     * Creates a matcher that matches, if the examined {@link Object} has a constructor with
     * the specified {@link Annotation}.
     * The {@code parameterTypes} parameter is an array of {@code Class} objects that identify
     * the constructor's formal parameter types, in declared order.
     * <p>
     * For example:
     * <pre>assertThat(myObject, is(constructorAnnotatedWith(Inject.class, EventAggregator.class))</pre>
     * </p>
     *
     * @param annotation the annotation that the returned matcher will be inspecting for
     * @param parameterTypes the parameter array
     */
    public static Matcher<Object> constructorAnnotatedWith(Class<? extends Annotation> annotation,
                                                           Class<?>... parameterTypes) {
        return IsConstructorAnnotated.with(annotation, parameterTypes);
    }

    /**
     * Creates a matcher that matches, if the examined {@link Object} has a constructor with
     * the specified {@link Annotation} and the annotation contains the specified <code>param</code>.
     * The {@code parameterTypes} parameter is an array of {@code Class} objects that identify
     * the constructor's formal parameter types, in declared order.
     * <p>
     * For example:
     * <pre>assertThat(myObject, is(constructorAnnotatedWithParam(Autowired.class, "required", EventAggregator.class))</pre>
     * </p>
     *
     * @param annotation the annotation that the returned matcher will be inspecting for
     * @param param the param that the annotation must have
     * @param parameterTypes the parameter array
     */
    public static Matcher<Object> constructorAnnotatedWithParam(Class<? extends Annotation> annotation, String param,
                                                                Class<?>... parameterTypes) {
        return IsConstructorAnnotated.withParam(annotation, param, parameterTypes);
    }

    /**
     * Creates a matcher that matches, if the examined {@link Object} has a constructor with
     * the specified {@link Annotation} and the annotation contains the specified <code>param</code>
     * with a required <code>value</code>.  A value matcher is provided to do the actual matching.
     * The {@code parameterTypes} parameter is an array of {@code Class} objects that identify
     * the constructor's formal parameter types, in declared order.
     * <p>
     * For example:
     * <pre>assertThat(myObject, is(constructorAnnotatedWithParamValue(Autowired.class, "required", is(false), EventAggregator.class))</pre>
     * </p>
     *
     * @param annotation the annotation that the returned matcher will be inspecting for
     * @param param the param that the annotation must have
     * @param valueMatcher a matcher for the parameter value
     * @param parameterTypes the parameter array
     * @param <T> type of value to be matched
     */
    public static <T> Matcher<Object> constructorAnnotatedWithParamValue(Class<? extends Annotation> annotation,
                                                                         String param, Matcher<T> valueMatcher,
                                                                         Class<?>... parameterTypes) {
        return IsConstructorAnnotated.withParamValue(annotation, param, valueMatcher, parameterTypes);
    }

    /**
     * Creates a matcher that matches, if the examined {@link Object} has a constructor with
     * the specified {@link Annotation} and the annotation contains the specified <code>param</code>
     * with a required <code>value</code>.
     * The {@code parameterTypes} parameter is an array of {@code Class} objects that identify
     * the constructor's formal parameter types, in declared order.
     * <p>
     * For example:
     * <pre>assertThat(myObject, is(constructorAnnotatedWithParamValue(Autowired.class, "required", false, EventAggregator.class))</pre>
     * </p>
     *
     * @param annotation the annotation that the returned matcher will be inspecting for
     * @param param the param that the annotation must have
     * @param value the parameter value must be equal to this
     * @param parameterTypes the parameter array
     * @param <T> type of value to be matched
     */
    public static <T> Matcher<Object> constructorAnnotatedWithParamValue(Class<? extends Annotation> annotation,
                                                                         String param, T value,
                                                                         Class<?>... parameterTypes) {
        return IsConstructorAnnotated.withParamValue(annotation, param, value, parameterTypes);
    }

    /**
     * Creates a matcher that matches, if the examined {@link Object} has a field with
     * the specified {@link Annotation}.  The {@code fieldName} parameter is used to identify the field
     * to be examined.
     * <p>
     * For example:
     * <pre>assertThat(myObject, is(fieldAnnotatedWith(Column.class, "notes"))</pre>
     * </p>
     *
     * @param annotation the annotation that the returned matcher will be inspecting for
     * @param fieldName fieldName the field name
     */
    public static Matcher<Object> fieldAnnotatedWith(Class<? extends Annotation> annotation, String fieldName) {
        return IsFieldAnnotated.with(annotation, fieldName);
    }

    /**
     * Creates a matcher that matches, if the examined {@link Object} has a field with
     * the specified {@link Annotation} and the annotation contains the specified <code>param</code>.
     * The {@code fieldName} parameter is used to identify the field to be examined.
     * <p>
     * For example:
     * <pre>assertThat(myObject, is(fieldAnnotatedWithParam(Column.class, "name", "notes"))</pre>
     * </p>
     *
     * @param annotation the annotation that the returned matcher will be inspecting for
     * @param param the param that the annotation must have
     * @param fieldName the field name
     */
    public static Matcher<Object> fieldAnnotatedWithParam(Class<? extends Annotation> annotation, String param,
                                                          String fieldName) {
        return IsFieldAnnotated.withParam(annotation, param, fieldName);
    }

    /**
     * Creates a matcher that matches, if the examined {@link Object} has a field with
     * the specified {@link Annotation} and the annotation contains the specified <code>param</code>.
     * with a required <code>value</code>.  A value matcher is provided to do the actual matching.
     * The {@code fieldName} parameter is used to identify the field to be examined.
     * <p>
     * For example:
     * <pre>assertThat(myObject, is(fieldAnnotatedWithParamValue(Column.class, "name", is("notes"), "notes"))</pre>
     * </p>
     *
     * @param annotation the annotation that the returned matcher will be inspecting for
     * @param param the param that the annotation must have
     * @param valueMatcher a matcher for the parameter value
     * @param fieldName the field name
     * @param <T> type of value to be matched
     */
    public static <T> Matcher<Object> fieldAnnotatedWithParamValue(Class<? extends Annotation> annotation,
                                                                   String param, Matcher<T> valueMatcher,
                                                                   String fieldName) {
        return IsFieldAnnotated.withParamValue(annotation, param, valueMatcher, fieldName);
    }

    /**
     * Creates a matcher that matches, if the examined {@link Object} has a field with
     * the specified {@link Annotation} and the annotation contains the specified <code>param</code>.
     * with a required <code>value</code>. The {@code fieldName} parameter is used to identify the field to be examined.
     * <p>
     * For example:
     * <pre>assertThat(myObject, is(fieldAnnotatedWithParamValue(Column.class, "name", "notes", "notes"))</pre>
     * </p>
     *
     * @param annotation the annotation that the returned matcher will be inspecting for
     * @param param the param that the annotation must have
     * @param paramValue the parameter value must be equal to this
     * @param fieldName the field name
     * @param <T> type of value to be matched
     */
    public static <T> Matcher<Object> fieldAnnotatedWithParamValue(Class<? extends Annotation> annotation,
                                                                         String param, T paramValue, String fieldName) {
        return IsFieldAnnotated.withParamValue(annotation, param, paramValue, fieldName);
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
     * <pre>assertThat(myObject, is(methodAnnotatedWith(Override.class, "toString"))</pre>
     * </p>
     *
     * @param annotation the annotation that the returned matcher will be inspecting for
     * @param methodName the name of the method
     * @param methodParameterTypes the parameter array
     */
    public static Matcher<Object> methodAnnotatedWith(Class<? extends Annotation> annotation,
                                                                   String methodName, Class<?>... methodParameterTypes) {
        return IsMethodAnnotated.with(annotation, methodName, methodParameterTypes);
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
     * <pre>assertThat(myObject, is(methodAnnotatedWithParam(Autowired.class, "required", "getUser", String.class))</pre>
     * </p>
     *
     * @param annotation the annotation that the returned matcher will be inspecting for
     * @param param the param that the annotation must have
     * @param methodName the name of the method
     * @param methodParameterTypes the parameter array
     */
    public static Matcher<Object> methodAnnotatedWithParam(Class<? extends Annotation> annotation, String param,
                                                           String methodName, Class<?>... methodParameterTypes) {
        return IsMethodAnnotated.withParam(annotation, param, methodName, methodParameterTypes);
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
     * <pre>assertThat(myObject, is(methodAnnotatedWithParamValue(Autowired.class, "required", is(false), "getUser", String.class))</pre>
     * </p>
     *
     * @param annotation the annotation that the returned matcher will be inspecting for
     * @param param the param that the annotation must have
     * @param valueMatcher a matcher for the parameter value
     * @param methodName the name of the method
     * @param methodParameterTypes the parameter array
     */
    public static <T> Matcher<Object> methodAnnotatedWithParamValue(Class<? extends Annotation> annotation,
                                                                   String param, Matcher<T> valueMatcher,
                                                                   String methodName, Class<?>... methodParameterTypes) {
        return IsMethodAnnotated.withParamValue(annotation, param, valueMatcher, methodName, methodParameterTypes);
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
     * <pre>assertThat(myObject, is(methodAnnotatedWithParamValue(Autowired.class, "required", false, "getUser", String.class))</pre>
     * </p>
     *
     * @param annotation the annotation that the returned matcher will be inspecting for
     * @param param the param that the annotation must have
     * @param paramValue the parameter value must be equal to this
     * @param methodName the name of the method
     * @param methodParameterTypes the parameter array
     * @param <T> type of value to be matched
     */
    public static <T> Matcher<Object> methodAnnotatedWithParamValue(Class<? extends Annotation> annotation,
                                                                   String param, T paramValue, String methodName,
                                                                   Class<?>... methodParameterTypes) {
        return IsMethodAnnotated.withParamValue(annotation, param, paramValue, methodName, methodParameterTypes);
    }

    /**
     * Creates a matcher that matches, if the examined {@link Object} has a constructor's parameter
     * with the specified {@link java.lang.annotation.Annotation}.
     * The {@code parameterTypes} parameter is an array of {@code Class} objects that identify
     * the constructor's formal parameter types, in declared order.
     * <p>
     * For example:
     * <pre>assertThat(myObject, is(constructorParameterAnnotatedWith(Named.class, 1, String.class, String.class))</pre>
     * </p>
     *
     * @param annotation the annotation that the returned matcher will be inspecting for
     * @param parameterPosition position of target parameter
     * @param parameterTypes the parameter array
     */
    public static Matcher<Object> constructorParameterAnnotatedWith(Class<? extends Annotation> annotation, int parameterPosition,
                                                           Class<?>... parameterTypes) {
        return IsConstructorParameterAnnotated.with(annotation, parameterPosition, parameterTypes);
    }

    /**
     * Creates a matcher that matches, if the examined {@link Object} has a constructor's parameter with
     * the specified {@link Annotation} and the annotation contains the specified <code>param</code>.
     * The {@code parameterTypes} parameter is an array of {@code Class} objects that identify
     * the constructor's formal parameter types, in declared order.
     * <p>
     * For example:
     * <pre>assertThat(myObject, is(constructorParameterAnnotatedWithParam(Named.class, "value", 1, String.class, String.class))</pre>
     * </p>
     *
     * @param annotation the annotation that the returned matcher will be inspecting for
     * @param param the param that the annotation must have
     * @param parameterPosition position of target parameter
     * @param parameterTypes the parameter array
     */
    public static Matcher<Object> constructorParameterAnnotatedWithParam(Class<? extends Annotation> annotation, String param,
                                                                int parameterPosition, Class<?>... parameterTypes) {
        return IsConstructorParameterAnnotated.withParam(annotation, param, parameterPosition, parameterTypes);
    }

    /**
     * Creates a matcher that matches, if the examined {@link Object} has a constructor's parameter with
     * the specified {@link Annotation} and the annotation contains the specified <code>param</code>.
     * with a required <code>value</code>.  A value matcher is provided to do the actual matching.
     * The {@code parameterTypes} parameter is an array of {@code Class} objects that identify
     * the constructor's formal parameter types, in declared order.
     * <p>
     * For example:
     * <pre>assertThat(myObject, is(constructorParameterAnnotatedWithParamValue(Named.class, "value", is("car"), 1, String.class, String.class))</pre>
     * </p>
     *
     * @param annotation the annotation that the returned matcher will be inspecting for
     * @param param the param that the annotation must have
     * @param matcher a matcher for the parameter value
     * @param parameterPosition position of target parameter
     * @param parameterTypes the parameter array
     * @param <T> type of value to be matched
     */
    public static <T> Matcher<Object> constructorParameterAnnotatedWithParamValue(Class<? extends Annotation> annotation,
                                String param, Matcher<T> matcher, int parameterPosition, Class<?>... parameterTypes) {
        return IsConstructorParameterAnnotated.withParamValue(annotation, param, matcher, parameterPosition,
                parameterTypes);
    }

    /**
     * Creates a matcher that matches, if the examined {@link Object} has a constructor's parameter with
     * the specified {@link Annotation} and the annotation contains the specified <code>param</code>.
     * with a required <code>value</code>.
     * The {@code parameterTypes} parameter is an array of {@code Class} objects that identify
     * the constructor's formal parameter types, in declared order.
     * <p>
     * For example:
     * <pre>assertThat(myObject, is(constructorParameterAnnotatedWithParamValue(Named.class, "value", "car", 1, String.class, String.class))</pre>
     * </p>
     *
     * @param annotation the annotation that the returned matcher will be inspecting for
     * @param param the param that the annotation must have
     * @param paramValue the parameter value must be equal to this
     * @param parameterPosition position of target parameter
     * @param parameterTypes the parameter array
     * @param <T> type of value to be matched
     */
    public static <T> Matcher<Object> constructorParameterAnnotatedWithParamValue(Class<? extends Annotation> annotation,
                                                                         String param, T paramValue, int parameterPosition, Class<?>... parameterTypes) {
        return IsConstructorParameterAnnotated.withParamValue(annotation, param, paramValue, parameterPosition,
                parameterTypes);
    }

    /**
     * Creates a matcher that matches, if the examined {@link Object} has a constructor's parameter
     * with the specified {@link java.lang.annotation.Annotation}.
     * The {@code parameterTypes} parameter is an array of {@code Class} objects that identify
     * the constructor's formal parameter types, in declared order.
     * <p>
     * For example:
     * <pre>assertThat(myObject, is(methodParameterAnnotatedWith(Named.class, 1, String.class, String.class))</pre>
     * </p>
     *
     * @param annotation the annotation that the returned matcher will be inspecting for
     * @param parameterPosition position of target parameter
     * @param methodName the name of the method
     * @param parameterTypes the parameter array
     */
    public static Matcher<Object> methodParameterAnnotatedWith(Class<? extends Annotation> annotation, int parameterPosition,
                                                           String methodName, Class<?>... parameterTypes) {
        return IsMethodParameterAnnotated.with(annotation, parameterPosition, methodName, parameterTypes);
    }

    /**
     * Creates a matcher that matches, if the examined {@link Object} has a constructor's parameter with
     * the specified {@link Annotation} and the annotation contains the specified <code>param</code>.
     * The {@code parameterTypes} parameter is an array of {@code Class} objects that identify
     * the constructor's formal parameter types, in declared order.
     * <p>
     * For example:
     * <pre>assertThat(myObject, is(methodParameterAnnotatedWithParam(Named.class, "value", 1, "getFactory", String.class, String.class))</pre>
     * </p>
     *
     * @param annotation the annotation that the returned matcher will be inspecting for
     * @param param the param that the annotation must have
     * @param parameterPosition position of target parameter
     * @param methodName the name of the method
     * @param parameterTypes the parameter array
     */
    public static Matcher<Object> methodParameterAnnotatedWithParam(Class<? extends Annotation> annotation, String param,
                    int parameterPosition, String methodName, Class<?>... parameterTypes) {
        return IsMethodParameterAnnotated.withParam(annotation, param, parameterPosition, methodName, parameterTypes);
    }

    /**
     * Creates a matcher that matches, if the examined {@link Object} has method parameter with
     * the specified {@link Annotation} and the annotation contains the specified <code>param</code>.
     * with a required <code>value</code>.  A value matcher is provided to do the actual matching.
     * The {@code parameterTypes} parameter is an array of {@code Class} objects that identify
     * the method's formal parameter types, in declared order.
     * <p>
     * For example:
     * <pre>assertThat(myObject, is(methodParameterAnnotatedWithParamValue(Named.class, "value", is("car"), 1, "getFactory", String.class, String.class))</pre>
     * </p>
     *
     * @param annotation the annotation that the returned matcher will be inspecting for
     * @param param the param that the annotation must have
     * @param matcher a matcher for the parameter value
     * @param parameterPosition position of target parameter
     * @param methodName the name of the method
     * @param parameterTypes the parameter array
     * @param <T> type of value to be matched
     */
    public static <T> Matcher<Object> methodParameterAnnotatedWithParamValue(Class<? extends Annotation> annotation,
                String param, Matcher<T> matcher, int parameterPosition, String methodName, Class<?>... parameterTypes) {
        return IsMethodParameterAnnotated.withParamValue(annotation, param, matcher, parameterPosition, methodName,
                parameterTypes);
    }

    /**
     * Creates a matcher that matches, if the examined {@link Object} has method parameter with
     * the specified {@link Annotation} and the annotation contains the specified <code>param</code>.
     * with a required <code>value</code>.
     * The {@code parameterTypes} parameter is an array of {@code Class} objects that identify
     * the method's formal parameter types, in declared order.
     * <p>
     * For example:
     * <pre>assertThat(myObject, is(methodParameterAnnotatedWithParamValue(Named.class, "value", "car", 1, "getFactory", String.class, String.class))</pre>
     * </p>
     *
     * @param annotation the annotation that the returned matcher will be inspecting for
     * @param param the param that the annotation must have
     * @param paramValue the parameter value must be equal to this
     * @param parameterPosition position of target parameter
     * @param methodName the name of the method
     * @param parameterTypes the parameter array
     * @param <T> type of value to be matched
     */
    public static <T> Matcher<Object> methodParameterAnnotatedWithParamValue(Class<? extends Annotation> annotation,
                        String param, T paramValue, int parameterPosition, String methodName, Class<?>... parameterTypes) {
        return IsMethodParameterAnnotated.withParamValue(annotation, param, paramValue, parameterPosition,
                methodName, parameterTypes);
    }
}
