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

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class IsConstructorAnnotatedTest {
    @Test
    public void shouldMismatchIfItemIsNull() throws Exception {
        IsConstructorAnnotated matcher = new IsConstructorAnnotated(TestAnnotation.class, null);
        Description description = new StringDescription();

        boolean result = matcher.matches(null);
        matcher.describeMismatch(null, description);

        assertThat(result, is(false));
        assertThat(description.toString(), containsString("was null"));
    }

    @Test
    public void shouldMismatchIfConstructorMissing() throws Exception {
        TestAnnotated annotated = new TestAnnotated();
        IsConstructorAnnotated matcher = new IsConstructorAnnotated(TestAnnotation.class, null, double.class);
        Description description = new StringDescription();

        boolean result = matcher.matches(annotated);
        matcher.describeMismatch(annotated, description);

        assertThat(result, is(false));
        assertThat(description.toString(), containsString("java.lang.NoSuchMethodException: com.zaradai.matchers.support.TestAnnotated.<init>(double)"));
    }

    @Test
    public void shouldMismatchIfAnnotationMissing() throws Exception {
        TestAnnotated annotated = new TestAnnotated();
        IsConstructorAnnotated matcher = new IsConstructorAnnotated(TestAnnotation.class, null);
        Description description = new StringDescription();

        boolean result = matcher.matches(annotated);
        matcher.describeMismatch(annotated, description);

        assertThat(result, is(false));
        assertThat(description.toString(), containsString("annotation missing"));
    }

    @Test
    public void shouldMismatchIfTargetAnnotationIsNull() throws Exception {
        TestAnnotated annotated = new TestAnnotated();
        IsConstructorAnnotated matcher = new IsConstructorAnnotated(null, null);
        Description description = new StringDescription();

        boolean result = matcher.matches(annotated);
        matcher.describeMismatch(annotated, description);

        assertThat(result, is(false));
        assertThat(description.toString(), containsString("NullPointerException"));
    }

    @Test
    public void shouldMatchIfAnnotationPresent() throws Exception {
        TestAnnotated annotated = new TestAnnotated();
        IsConstructorAnnotated matcher = new IsConstructorAnnotated(TestAnnotation.class, null, String.class);

        boolean result = matcher.matches(annotated);

        assertThat(result, is(true));
    }

    @Test
    public void shouldMismatchIfAnnotationParamIsMissing() throws Exception {
        TestAnnotated annotated = new TestAnnotated();
        IsConstructorAnnotated matcher = new IsConstructorAnnotated(TestAnnotation.class,
                AnnotationParamMatcher.hasParam("notThere"));
        Description description = new StringDescription();

        boolean result = matcher.matches(annotated);
        matcher.describeMismatch(annotated, description);

        assertThat(result, is(false));
        assertThat(description.toString(), containsString("annotation missing"));
    }

    @Test
    public void shouldMismatchIfAnnotationParamValueDifferent() throws Exception {
        TestAnnotated annotated = new TestAnnotated();
        IsConstructorAnnotated matcher = new IsConstructorAnnotated(TestAnnotation.class,
                AnnotationParamMatcher.hasParamValue("value", 42), String.class);
        Description description = new StringDescription();

        boolean result = matcher.matches(annotated);
        matcher.describeMismatch(annotated, description);

        assertThat(result, is(false));
        assertThat(description.toString(), containsString("was <56>"));
    }

    @Test
    public void shouldMatchIfAnnotationHasParam() throws Exception {
        TestAnnotated annotated = new TestAnnotated();
        IsConstructorAnnotated matcher = new IsConstructorAnnotated(TestAnnotation.class,
                AnnotationParamMatcher.hasParam("value"), String.class);

        assertThat(matcher.matches(annotated), is(true));
    }

    @Test
    public void shouldMatchIfAnnotationHasParamValue() throws Exception {
        TestAnnotated annotated = new TestAnnotated();
        IsConstructorAnnotated matcher = new IsConstructorAnnotated(TestAnnotation.class,
                AnnotationParamMatcher.hasParamValue("value", TestAnnotated.TEST_VALUE), String.class);

        assertThat(matcher.matches(annotated), is(true));
    }

    @Test
    public void shouldDescribeTo() throws Exception {
        Description description = new StringDescription();
        IsConstructorAnnotated matcher = new IsConstructorAnnotated(TestAnnotation.class,
                AnnotationParamMatcher.hasParamValue("value", TestAnnotated.TEST_VALUE), String.class);

        matcher.describeTo(description);

        assertThat(description.toString(), is("constructor to be annotated with " +
                "<interface com.zaradai.matchers.support.TestAnnotation> " +
                "with annotation parameter \"value\" value <56>"));
    }

    @Test
    public void shouldMatchFactoryWith() throws Exception {
        TestAnnotated annotated = new TestAnnotated();

        assertThat(IsConstructorAnnotated.with(TestAnnotation.class, String.class).matches(annotated), is(true));
    }

    @Test
    public void shouldMatchFactoryWithParam() throws Exception {
        TestAnnotated annotated = new TestAnnotated();

        assertThat(IsConstructorAnnotated.withParam(TestAnnotation.class, "value", String.class).matches(annotated),
                is(true));
    }

    @Test
    public void shouldMatchFactoryWithParamValueUsingMatcher() throws Exception {
        TestAnnotated annotated = new TestAnnotated();

        assertThat(IsConstructorAnnotated.withParamValue(TestAnnotation.class, "value", is(56), String.class)
                .matches(annotated), is(true));
    }

    @Test
    public void shouldMatchFactoryWithParamValue() throws Exception {
        TestAnnotated annotated = new TestAnnotated();

        assertThat(IsConstructorAnnotated.withParamValue(TestAnnotation.class, "value", 56, String.class)
                .matches(annotated), is(true));
    }
}
