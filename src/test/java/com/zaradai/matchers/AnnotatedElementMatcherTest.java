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

import com.zaradai.matchers.support.TestAnnotated;
import com.zaradai.matchers.support.TestAnnotation;
import org.hamcrest.Description;
import org.hamcrest.StringDescription;
import org.junit.Test;

import java.lang.reflect.AnnotatedElement;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AnnotatedElementMatcherTest {
    @Test
    public void shouldMismatchIfItemIsNull() throws Exception {
        AnnotatedElementMatcher matcher = new AnnotatedElementMatcher(TestAnnotation.class);
        Description description = new StringDescription();

        boolean result = matcher.matches(null);
        matcher.describeMismatch(null, description);

        assertThat(result, is(false));
        assertThat(description.toString(), containsString("was null"));
    }

    @Test
    public void shouldMismatchIfAnnotationClassIsNull() throws Exception {
        AnnotatedElementMatcher matcher = new AnnotatedElementMatcher(null);
        Description description = new StringDescription();
        AnnotatedElement annotatedElement = getAnnotatedElement();

        boolean result = matcher.matches(annotatedElement);
        matcher.describeMismatch(annotatedElement, description);

        assertThat(result, is(false));
        assertThat(description.toString(), containsString("null"));
    }

    @Test
    public void shouldMismatchIfAnnotationNotFound() throws Exception {
        AnnotatedElementMatcher matcher = new AnnotatedElementMatcher(Override.class);
        Description description = new StringDescription();
        AnnotatedElement annotatedElement = getAnnotatedElement();

        boolean result = matcher.matches(annotatedElement);
        matcher.describeMismatch(annotatedElement, description);

        assertThat(result, is(false));
        assertThat(description.toString(), containsString("annotation missing"));
    }

    @Test
    public void shouldMatchIfAnnotationFound() throws Exception {
        AnnotatedElementMatcher matcher = new AnnotatedElementMatcher(TestAnnotation.class);
        AnnotatedElement annotatedElement = getAnnotatedElement();

        assertThat(matcher.matches(annotatedElement), is(true));
    }

    @Test
    public void shouldMatchWithFactoryHasAnnotation() throws Exception {
        AnnotatedElement annotatedElement = getAnnotatedElement();

        assertThat(AnnotatedElementMatcher.hasAnnotation(TestAnnotation.class).matches(annotatedElement), is(true));
    }

    @Test
    public void shouldDescribeTo() throws Exception {
        Description description = new StringDescription();
        AnnotatedElementMatcher matcher = new AnnotatedElementMatcher(TestAnnotation.class);

        matcher.describeTo(description);

        assertThat(description.toString(), is(" annotated with \"com.zaradai.matchers.support.TestAnnotation\""));
    }

    private AnnotatedElement getAnnotatedElement() {
        return TestAnnotated.class;
    }
}
