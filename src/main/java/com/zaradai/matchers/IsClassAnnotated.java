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
 * A matcher to inspect classes for specified annotations.
 */
public class IsClassAnnotated extends AbstractAnnotationMatcher {
    /**
     * Setup the parameters for the annotation matcher.
     * @param annotationClass annotation that must decorate the class
     * @param paramMatcher matcher to check the annotation for a specific parameter
     */
    protected IsClassAnnotated(Class<? extends Annotation> annotationClass, Matcher<Annotation> paramMatcher) {
        super(annotationClass, paramMatcher);
    }

    @Override
    protected Annotation getAnnotation(Object item, Class<? extends Annotation> annotationClass) throws Exception {
        return item.getClass().getAnnotation(annotationClass);
    }

    @Override
    protected String getAnnotationSource() {
        return "class";
    }

    /**
     * Creates a matcher that matches, if the examined {@link Object} class
     * is annotated with the specified {@link Annotation}.
     * <p>
     * For example:
     * <pre>assertThat(myObject, is(with(Entity.class))</pre>
     * </p>
     * @param annotation the annotation that the returned matcher will be inspecting for
     */
    @Factory
    public static Matcher<Object> with(Class<? extends Annotation> annotation) {
        return new IsClassAnnotated(annotation, null);
    }

    /**
     * Creates a matcher that matches, if the examined {@link Object} class
     * is annotated with the specified {@link Annotation} and it has a parameter with
     * the specified <code>param</code> name.
     * <p>
     * For example:
     * <pre>assertThat(myObject, is(withParam(Table.class, "name"))</pre>
     * </p>
     * @param annotation the annotation that the returned matcher will be inspecting for
     * @param param the name of the parameter that must exist
     */
    @Factory
    public static Matcher<Object> withParam(Class<? extends Annotation> annotation, String param) {
        return new IsClassAnnotated(annotation, AnnotationParamMatcher.hasParam(param));
    }

    /**
     * Creates a matcher that matches, if the examined {@link Object} class
     * is annotated with the specified {@link Annotation} and it has a specified parameter with
     * a required value.  The value is matched using a {@link Matcher}.
     * <p>
     * For example:
     * <pre>assertThat(myObject, is(withParamValue(Table.class, "name", is("test")))</pre>
     * </p>
     * @param annotation the annotation that the returned matcher will be inspecting for
     * @param param the name of the parameter that must exist
     * @param matcher the matcher that asserts the parameter value.
     */
    @Factory
    public static <T> Matcher<Object> withParamValue(Class<? extends Annotation> annotation, String param, Matcher<T> matcher) {
        return new IsClassAnnotated(annotation, AnnotationParamMatcher.hasParamValue(param, matcher));
    }

    /**
     * Creates a matcher that matches, if the examined {@link Object} class
     * is annotated with the specified {@link Annotation} and it has a specified parameter with
     * a required value.
     * <p>
     * For example:
     * <pre>assertThat(myObject, is(withParamValue(Table.class, "name", is("test")))</pre>
     * </p>
     * @param annotation the annotation that the returned matcher will be inspecting for
     * @param param the name of the parameter that must exist
     * @param paramValue the value to match with.
     */
    @Factory
    public static <T> Matcher<Object> withParamValue(Class<? extends Annotation> annotation, String param, T paramValue) {
        return new IsClassAnnotated(annotation, AnnotationParamMatcher.hasParamValue(param, paramValue));
    }
}
