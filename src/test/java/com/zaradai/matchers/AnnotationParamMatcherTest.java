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

import java.lang.annotation.Annotation;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class AnnotationParamMatcherTest {
    @Test
    public void shouldMismatchIfItemIsNull() throws Exception {
        AnnotationParamMatcher matcher = new AnnotationParamMatcher("value", is(42));
        Description description = new StringDescription();

        boolean result = matcher.matches(null);
        matcher.describeMismatch(null, description);

        assertThat(result, is(false));
        assertThat(description.toString(), containsString("was null"));
    }

    @Test
    public void shouldMismatchIfParamIsNull() throws Exception {
        AnnotationParamMatcher matcher = new AnnotationParamMatcher(null, is(42));
        Description description = new StringDescription();
        Annotation annotation = getAnnotation();

        boolean result = matcher.matches(annotation);
        matcher.describeMismatch(annotation, description);

        assertThat(result, is(false));
        assertThat(description.toString(), containsString("parameter not found"));
    }

    @Test
    public void shouldMismatchIfMatcherIsNull() throws Exception {
        AnnotationParamMatcher matcher = new AnnotationParamMatcher("value", null);
        Description description = new StringDescription();
        Annotation annotation = getAnnotation();

        boolean result = matcher.matches(annotation);
        matcher.describeMismatch(annotation, description);

        assertThat(result, is(false));
        assertThat(description.toString(), containsString("null"));
    }

    @Test
    public void shouldMismatchIfParamNotFound() throws Exception {
        AnnotationParamMatcher matcher = new AnnotationParamMatcher("notThere", anything());
        Description description = new StringDescription();
        Annotation annotation = getAnnotation();

        boolean result = matcher.matches(annotation);
        matcher.describeMismatch(annotation, description);

        assertThat(result, is(false));
        assertThat(description.toString(), containsString("parameter not found"));
    }

    @Test
    public void shouldMismatchValidParameterWithInvalidValue() throws Exception {
        AnnotationParamMatcher matcher = new AnnotationParamMatcher("value", is(23));
        Description description = new StringDescription();
        Annotation annotation = getAnnotation();

        boolean result = matcher.matches(annotation);
        matcher.describeMismatch(annotation, description);

        assertThat(result, is(false));
        assertThat(description.toString(), containsString("was <56>"));
    }

    @Test
    public void shouldDescribeTo() throws Exception {
        Description description = new StringDescription();
        AnnotationParamMatcher matcher = new AnnotationParamMatcher("value", is(56));

        matcher.describeTo(description);

        assertThat(description.toString(), is("with annotation parameter \"value\" value is <56>"));
    }

    @Test
    public void shouldMatchValidParameter() throws Exception {
        AnnotationParamMatcher matcher = new AnnotationParamMatcher("value", anything());
        Annotation annotation = getAnnotation();

        assertThat(matcher.matches(annotation), is(true));
    }

    @Test
    public void shouldMatchValidParameterAndValue() throws Exception {
        AnnotationParamMatcher matcher = new AnnotationParamMatcher("value", is(56));
        Annotation annotation = getAnnotation();

        assertThat(matcher.matches(annotation), is(true));
    }

    @Test
    public void shouldMatchWithFactoryHasParam() throws Exception {
        Annotation annotation = getAnnotation();

        assertThat(AnnotationParamMatcher.hasParam("value").matches(annotation), is(true));
    }

    @Test
    public void shouldMatchWithFactoryHasParamWithMatcher() throws Exception {
        Annotation annotation = getAnnotation();

        assertThat(AnnotationParamMatcher.hasParamValue("value", is(56)).matches(annotation), is(true));
    }

    @Test
    public void shouldMatchWithFactoryHasParamWithValue() throws Exception {
        Annotation annotation = getAnnotation();

        assertThat(AnnotationParamMatcher.hasParamValue("value", 56).matches(annotation), is(true));
    }

    private Annotation getAnnotation() {
        return new TestAnnotated().getClass().getAnnotation(TestAnnotation.class);
    }
}
