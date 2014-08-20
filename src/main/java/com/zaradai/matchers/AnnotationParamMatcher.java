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

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.equalTo;

/**
 * Tests if an annotation has expected parameter and value.
 * @param <T> type of value to match with.
 */
public class AnnotationParamMatcher<T> extends TypeSafeDiagnosingMatcher<Annotation> {
    private final String param;
    private final Matcher<T> valueMatcher;

    /**
     * Create an {@link Annotation} parameter matcher.
     * @param param name of parameter to match
     * @param valueMatcher matcher to match parameter value
     */
    public AnnotationParamMatcher(String param, Matcher<T> valueMatcher) {
        this.param = param;
        this.valueMatcher = valueMatcher;
    }

    @Override
    protected boolean matchesSafely(Annotation item, Description mismatchDescription) {
        Method wantedMethod = getWantedMethod(item);

        if (wantedMethod == null) {
            mismatchDescription.appendText("parameter not found");
            return false;
        }

        return invokeAndMatch(item, wantedMethod, mismatchDescription);
    }

    private boolean invokeAndMatch(Annotation item, Method method, Description mismatchDescription) {
        try {
            Object value = method.invoke(item);

            if (!valueMatcher.matches(value)) {
                valueMatcher.describeMismatch(value, mismatchDescription);
                return false;
            }
        } catch (Exception e) {
            mismatchDescription.appendText(e.getMessage());
            return false;
        }

        return true;
    }

    private Method getWantedMethod(Annotation item) {
        try {
            return item.annotationType().getDeclaredMethod(param);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("with annotation parameter ")
                .appendValue(param)
                .appendText(" value ");
        valueMatcher.describeTo(description);
    }

    /**
     * Creates a matcher of {@link Annotation} that matches any object containing
     *  the named <code>param</code>.
     *  <p>
     *  For example:
     *  <pre>assertThat(myAnnotation, hasParam("value"))(</pre>
     *  </p>
     *
     * @param param name of parameter to check for existence
     */
    @Factory
    public static Matcher<Annotation> hasParam(String param) {
        return new AnnotationParamMatcher(param, anything());
    }

    /**
     * Creates a matcher of {@link Annotation} that matches any object containing
     * the named <code>param</code> with a value is matched by the corresponding
     * matcher from the specified <code>matcher</code>.
     *  <p>
     *  For example:
     *  <pre>assertThat(myAnnotation, hasParamValue("value", endsWith("b))(</pre>
     *  </p>
     *
     * @param param name of parameter to check for existence
     * @param matcher the matcher to apply to the given annotation parameter
     * @param <T> type of value being matched
     */
    @Factory
    public static <T> Matcher<Annotation> hasParamValue(String param, Matcher<T> matcher) {
        return new AnnotationParamMatcher<T>(param, matcher);
    }

    /**
     * Creates a matcher of {@link Annotation} that matches any object containing
     * the named <code>param</code> with a value equal to to <code>paramValue</code>.
     *  <p>
     *  For example:
     *  <pre>assertThat(myAnnotation, hasParamValue("value", "b")(</pre>
     *  </p>
     *
     * @param param name of parameter to match
     * @param paramValue value to match against
     * @param <T> type of value to match
     */
    @Factory
    public static <T> Matcher<Annotation> hasParamValue(String param, T paramValue) {
        return new AnnotationParamMatcher<T>(param, equalTo(paramValue));
    }
}
