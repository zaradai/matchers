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
import org.hamcrest.DiagnosingMatcher;
import org.hamcrest.Matcher;

import java.lang.annotation.Annotation;

/**
 * Supports basic annotation matcher functionality.
 */
public abstract class AbstractAnnotationMatcher extends DiagnosingMatcher<Object> {
    private final Matcher<Annotation> paramMatcher;
    private final Class<? extends Annotation> annotationClass;

    /**
     * Setup the matcher with {@link Annotation} class to be matched with and an optional {@link Matcher<Annotation> }
     * to match the annotations parameters with.
     * @param annotationClass  annotation that must decorate the class
     * @param paramMatcher matcher to check the annotation for a specific parameter
     */
    protected AbstractAnnotationMatcher(Class<? extends Annotation> annotationClass, Matcher<Annotation> paramMatcher) {
        this.annotationClass = annotationClass;
        this.paramMatcher = paramMatcher;
    }

    @Override
    protected boolean matches(Object item, Description mismatchDescription) {
        return validateItem(item, mismatchDescription) && match(item, mismatchDescription);
    }

    private boolean match(Object item, Description mismatchDescription) {
        Annotation annotation;

        try {
            annotation = getAnnotation(item, annotationClass);
        } catch (Exception e) {
            mismatchDescription.appendText(e.toString());
            return false;
        }

        if (annotation == null) {
            mismatchDescription.appendText("annotation missing");
            return false;
        }

        return matchParameter(annotation, mismatchDescription);
    }

    private boolean matchParameter(Annotation annotation, Description mismatchDescription) {
        if (paramMatcher != null) {
            if (!paramMatcher.matches(annotation)) {
                paramMatcher.describeMismatch(annotation, mismatchDescription);
                return false;
            }
        }
        return true;
    }

    private boolean validateItem(Object item, Description mismatchDescription) {
        if (item == null) {
            mismatchDescription.appendText("was null");
            return false;
        }

        return true;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(getAnnotationSource()).appendText(" to be annotated with ").appendValue(annotationClass);

        if (paramMatcher != null) {
            description.appendText(" ");
            paramMatcher.describeTo(description);
        }
    }

    /**
     * get a string to help describe the matcher.
     * @return description of source
     */
    protected abstract String getAnnotationSource();

    /**
     * get specific annotation from the implementation.
     * @param item object instance to get annotation from
     * @param clazz type of annotation to retrieve
     * @return required annotation from the item
     * @throws Exception
     */
    protected abstract Annotation getAnnotation(Object item, Class<? extends Annotation> clazz) throws Exception;
}
