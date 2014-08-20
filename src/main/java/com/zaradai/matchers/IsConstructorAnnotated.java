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
 * A matcher to inspect constructors for specified annotations.
 */
public class IsConstructorAnnotated  extends AbstractAnnotationMatcher {
    private final Class<?>[] parameterTypes;

    /**
     * Setup the search parameters to locate required constructor and verify annotation expectations.
     * @param annotationClass annotation that must decorate the constructor
     * @param paramMatcher matcher to check for specified annotation parameter value
     * @param parameterTypes zero or more parameter types to declare wanted constructor signature
     */
    protected IsConstructorAnnotated(Class<? extends Annotation> annotationClass, Matcher<Annotation> paramMatcher,
                                     Class<?>... parameterTypes) {
        super(annotationClass, paramMatcher);

        this.parameterTypes = parameterTypes;
    }

    @Override
    protected Annotation getAnnotation(Object item, Class<? extends Annotation> annotationClass) throws Exception {
        return item.getClass().getDeclaredConstructor(parameterTypes).getAnnotation(annotationClass);
    }

    @Override
    protected String getAnnotationSource() {
        return "constructor";
    }

    /**
     * Creates a matcher that matches, if the examined {@link Object} has a constructor with
     * the specified {@link Annotation}.
     * The {@code parameterTypes} parameter is an array of {@code Class} objects that identify
     * the constructor's formal parameter types, in declared order.
     * <p>
     * For example:
     * <pre>assertThat(myObject, is(with(Inject.class, EventAggregator.class))</pre>
     * </p>
     *
     * @param annotation the annotation that the returned matcher will be inspecting for
     * @param parameterTypes the parameter array
     */
    @Factory
    public static Matcher<Object> with(Class<? extends Annotation> annotation, Class<?>... parameterTypes) {
        return new IsConstructorAnnotated(annotation, null, parameterTypes);
    }

    /**
     * Creates a matcher that matches, if the examined {@link Object} has a constructor with
     * the specified {@link Annotation} and the annotation contains the specified <code>param</code>.
     * The {@code parameterTypes} parameter is an array of {@code Class} objects that identify
     * the constructor's formal parameter types, in declared order.
     * <p>
     * For example:
     * <pre>assertThat(myObject, is(withParam(Autowired.class, "required", EventAggregator.class))</pre>
     * </p>
     *
     * @param annotation the annotation that the returned matcher will be inspecting for
     * @param param the param that the annotation must have
     * @param parameterTypes the parameter array
     */
    @Factory
    public static Matcher<Object> withParam(Class<? extends Annotation> annotation, String param,
                                            Class<?>... parameterTypes) {
        return new IsConstructorAnnotated(annotation, AnnotationParamMatcher.hasParam(param), parameterTypes);
    }

    /**
     * Creates a matcher that matches, if the examined {@link Object} has a constructor with
     * the specified {@link Annotation} and the annotation contains the specified <code>param</code>
     * with a required <code>value</code>.  A value matcher is provided to do the actual matching.
     * The {@code parameterTypes} parameter is an array of {@code Class} objects that identify
     * the constructor's formal parameter types, in declared order.
     * <p>
     * For example:
     * <pre>assertThat(myObject, is(withParamValue(Autowired.class, "required", is(false), EventAggregator.class))</pre>
     * </p>
     *
     * @param annotation the annotation that the returned matcher will be inspecting for
     * @param param the param that the annotation must have
     * @param matcher a matcher for the parameter value
     * @param parameterTypes the parameter array
     * @param <T> type of value to be matched
     */
    @Factory
    public static <T> Matcher<Object> withParamValue(Class<? extends Annotation> annotation, String param,
                                                     Matcher<T> matcher, Class<?>... parameterTypes) {
        return new IsConstructorAnnotated(annotation, AnnotationParamMatcher.hasParamValue(param, matcher),
                parameterTypes);
    }

    /**
     * Creates a matcher that matches, if the examined {@link Object} has a constructor with
     * the specified {@link Annotation} and the annotation contains the specified <code>param</code>
     * with a required <code>value</code>.
     * The {@code parameterTypes} parameter is an array of {@code Class} objects that identify
     * the constructor's formal parameter types, in declared order.
     * <p>
     * For example:
     * <pre>assertThat(myObject, is(withParamValue(Autowired.class, "required", false, EventAggregator.class))</pre>
     * </p>
     *
     * @param annotation the annotation that the returned matcher will be inspecting for
     * @param param the param that the annotation must have
     * @param paramValue the parameter value must be equal to this
     * @param parameterTypes the parameter array
     * @param <T> type of value to be matched
     */
    @Factory
    public static <T> Matcher<Object> withParamValue(Class<? extends Annotation> annotation, String param, T paramValue,
                                                     Class<?>... parameterTypes) {
        return new IsConstructorAnnotated(annotation, AnnotationParamMatcher.hasParamValue(param, paramValue),
                parameterTypes);
    }
}
