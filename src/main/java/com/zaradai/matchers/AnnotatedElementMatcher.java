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
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

/**
 * Tests if an {@link AnnotatedElement} has expected annotation.
 */
public class AnnotatedElementMatcher extends TypeSafeDiagnosingMatcher<AnnotatedElement> {
    private final Class<? extends Annotation> annotation;

    /**
     * Create an {@link AnnotatedElement} matcher.
     * @param annotation {{@link Annotation} to match against.
     */
    public AnnotatedElementMatcher(Class<? extends Annotation> annotation) {
        this.annotation = annotation;
    }

    @Override
    protected boolean matchesSafely(AnnotatedElement item, Description mismatchDescription) {
        try {
            if (!item.isAnnotationPresent(annotation)) {
                mismatchDescription.appendText("annotation missing");
                return false;
            }
        } catch (Exception e) {
            mismatchDescription.appendText(e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(" annotated with ").appendValue(annotation.getName());
    }

    /**
     * Creates a matcher of {@link AnnotatedElement} that matches any object annotated
     * with the <code>annotation</code>.
     * <p>
     * For example:
     * <pre>assertThat(myObject, hasAnnotation(Inject.class))</pre>
     * </p>
     *
     * @param annotation to match with
     */
    public static Matcher<AnnotatedElement> hasAnnotation(Class<? extends Annotation> annotation) {
        return new AnnotatedElementMatcher(annotation);
    }
}
