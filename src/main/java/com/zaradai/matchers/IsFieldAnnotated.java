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
 * A matcher to inspect class fields for specified annotations.
 */
public class IsFieldAnnotated extends AbstractAnnotationMatcher {
    private final String fieldName;

    /**
     * Setup the search parameters to locate required fields and verify annotation expectations.
     * @param annotationClass annotation that must decorate the method
     * @param paramMatcher  matcher to check for specified annotation parameter value
     * @param fieldName name of field
     */
    protected IsFieldAnnotated(Class<? extends Annotation> annotationClass, Matcher<Annotation> paramMatcher,
                               String fieldName) {
        super(annotationClass, paramMatcher);

        this.fieldName = fieldName;
    }

    @Override
    protected Annotation getAnnotation(Object item, Class<? extends Annotation> annotationClass) throws Exception {
        return item.getClass().getDeclaredField(fieldName).getAnnotation(annotationClass);
    }

    @Override
    protected String getAnnotationSource() {
        return "field";
    }

    /**
     * Creates a matcher that matches, if the examined {@link Object} has a field with
     * the specified {@link Annotation}.  The {@code fieldName} parameter is used to identify the field
     * to be examined.
     * <p>
     * For example:
     * <pre>assertThat(myObject, is(with(Column.class, "notes"))</pre>
     * </p>
     *
     * @param annotation the annotation that the returned matcher will be inspecting for
     * @param fieldName fieldName the field name
     */
    @Factory
    public static Matcher<Object> with(Class<? extends Annotation> annotation, String fieldName) {
        return new IsFieldAnnotated(annotation, null, fieldName);
    }

    /**
     * Creates a matcher that matches, if the examined {@link Object} has a field with
     * the specified {@link Annotation} and the annotation contains the specified <code>param</code>.
     * The {@code fieldName} parameter is used to identify the field to be examined.
     * <p>
     * For example:
     * <pre>assertThat(myObject, is(withParam(Column.class, "name", "notes"))</pre>
     * </p>
     *
     * @param annotation the annotation that the returned matcher will be inspecting for
     * @param param the param that the annotation must have
     * @param fieldName the field name
     */
    @Factory
    public static Matcher<Object> withParam(Class<? extends Annotation> annotation, String param, String fieldName) {
        return new IsFieldAnnotated(annotation, AnnotationParamMatcher.hasParam(param), fieldName);
    }

    /**
     * Creates a matcher that matches, if the examined {@link Object} has a field with
     * the specified {@link Annotation} and the annotation contains the specified <code>param</code>.
     * with a required <code>value</code>.  A value matcher is provided to do the actual matching.
     * The {@code fieldName} parameter is used to identify the field to be examined.
     * <p>
     * For example:
     * <pre>assertThat(myObject, is(withParamValue(Column.class, "name", is("notes"), "notes"))</pre>
     * </p>
     *
     * @param annotation the annotation that the returned matcher will be inspecting for
     * @param param the param that the annotation must have
     * @param matcher a matcher for the parameter value
     * @param fieldName the field name
     * @param <T> type of value to be matched
     */
    @Factory
    public static <T> Matcher<Object> withParamValue(Class<? extends Annotation> annotation, String param,
                                                     Matcher<T> matcher, String fieldName) {
        return new IsFieldAnnotated(annotation, AnnotationParamMatcher.hasParamValue(param, matcher),
                fieldName);
    }

    /**
     * Creates a matcher that matches, if the examined {@link Object} has a field with
     * the specified {@link Annotation} and the annotation contains the specified <code>param</code>.
     * with a required <code>value</code>. The {@code fieldName} parameter is used to identify the field to be examined.
     * <p>
     * For example:
     * <pre>assertThat(myObject, is(withParamValue(Column.class, "name", "notes", "notes"))</pre>
     * </p>
     *
     * @param annotation the annotation that the returned matcher will be inspecting for
     * @param param the param that the annotation must have
     * @param paramValue the parameter value must be equal to this
     * @param fieldName the field name
     * @param <T> type of value to be matched
     */
    @Factory
    public static <T> Matcher<Object> withParamValue(Class<? extends Annotation> annotation, String param, T paramValue,
                                                     String fieldName) {
        return new IsFieldAnnotated(annotation, AnnotationParamMatcher.hasParamValue(param, paramValue),
                fieldName);
    }
}
