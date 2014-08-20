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
 * Extends {@link AbstractAnnotationMatcher} to support annotations within a parameter list such as those with constructors
 * and methods.
 */
public abstract class AbstractParameterAnnotatedMatcher extends AbstractAnnotationMatcher {
    private final int parameterPosition;

    /**
     * Setup the matcher with {@link Annotation} class to be matched with and an optional {@link Matcher<Annotation> }
     * to match the annotations parameters with.  The parameter position determines the location within the parameter
     * list of the annotation to be inspected.
     * @param annotationClass annotation that must decorate the class
     * @param paramMatcher matcher to check the annotation for a specific parameter
     * @param parameterPosition position of parameter
     */
    protected AbstractParameterAnnotatedMatcher(Class<? extends Annotation> annotationClass,
                                                Matcher<Annotation> paramMatcher, int parameterPosition) {
        super(annotationClass, paramMatcher);

        this.parameterPosition = parameterPosition;
    }

    @Override
    protected Annotation getAnnotation(Object item, Class<? extends Annotation> annotationClass) throws Exception {
        Annotation[][] annotations = getParameterAnnotations(item);

        for (Annotation annotation : annotations[parameterPosition]) {
            if (annotationClass.isInstance(annotation)) {
                    return annotation;
            }
        }

        return null;
    }

    /**
     * Returns an array of arrays that represent the annotations on the formal
     * parameters, in declaration order, of the target.
     * @param item object to get annotations from
     * @throws Exception
     */
    protected abstract Annotation[][] getParameterAnnotations(Object item) throws Exception;
}
