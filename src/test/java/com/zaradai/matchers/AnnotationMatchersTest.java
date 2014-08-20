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

import com.zaradai.matchers.support.*;
import org.junit.Test;

import static com.zaradai.matchers.AnnotationMatchers.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AnnotationMatchersTest {
    @Test
    public void shouldMatchAnnotatedClass() throws Exception {
        TestAnnotated annotated = new TestAnnotated();

        assertThat(annotated, is(classAnnotatedWith(TestAnnotation.class)));
    }

    @Test
    public void shouldMatchAnnotatedClassParam() throws Exception {
        TestAnnotated annotated = new TestAnnotated();

        assertThat(annotated, is(classAnnotatedWithParam(TestAnnotation.class, "value")));
    }

    @Test
    public void shouldMatchAnnotatedClassParamValueUsingMatcher() throws Exception {
        TestAnnotated annotated = new TestAnnotated();

        assertThat(annotated, is(classAnnotatedWithParamValue(TestAnnotation.class, "value", is(56))));
    }

    @Test
    public void shouldMatchAnnotatedClassParamValueUsingValue() throws Exception {
        TestAnnotated annotated = new TestAnnotated();

        assertThat(annotated, is(classAnnotatedWithParamValue(TestAnnotation.class, "value", 56)));
    }

    @Test
    public void shouldMatchAnnotatedConstructor() throws Exception {
        TestAnnotated annotated = new TestAnnotated();

        assertThat(annotated, is(constructorAnnotatedWith(TestAnnotation.class, String.class)));
    }

    @Test
    public void shouldMatchAnnotatedConstructorParam() throws Exception {
        TestAnnotated annotated = new TestAnnotated();

        assertThat(annotated, is(constructorAnnotatedWithParam(TestAnnotation.class, "value", String.class)));
    }

    @Test
    public void shouldMatchAnnotatedConstructorParamValueUsingMatcher() throws Exception {
        TestAnnotated annotated = new TestAnnotated();

        assertThat(annotated, is(constructorAnnotatedWithParamValue(TestAnnotation.class, "value", is(56), String.class)));
    }

    @Test
    public void shouldMatchAnnotatedConstructorParamValueUsingValue() throws Exception {
        TestAnnotated annotated = new TestAnnotated();

        assertThat(annotated, is(constructorAnnotatedWithParamValue(TestAnnotation.class, "value", 56, String.class)));
    }

    @Test
    public void shouldMatchAnnotatedField() throws Exception {
        TestAnnotated annotated = new TestAnnotated();

        assertThat(annotated, is(fieldAnnotatedWith(TestAnnotation.class, "annotatedField")));
    }

    @Test
    public void shouldMatchAnnotatedFieldParam() throws Exception {
        TestAnnotated annotated = new TestAnnotated();

        assertThat(annotated, is(fieldAnnotatedWithParam(TestAnnotation.class, "value", "annotatedField")));
    }

    @Test
    public void shouldMatchAnnotatedFieldParamValueUsingMatcher() throws Exception {
        TestAnnotated annotated = new TestAnnotated();

        assertThat(annotated, is(fieldAnnotatedWithParamValue(TestAnnotation.class, "value", is(56), "annotatedField")));
    }

    @Test
    public void shouldMatchAnnotatedFieldParamValueUsingValue() throws Exception {
        TestAnnotated annotated = new TestAnnotated();

        assertThat(annotated, is(fieldAnnotatedWithParamValue(TestAnnotation.class, "value", 56, "annotatedField")));
    }

    @Test
    public void shouldMatchAnnotatedMethod() throws Exception {
        TestAnnotated annotated = new TestAnnotated();

        assertThat(annotated, is(methodAnnotatedWith(TestAnnotation.class, "annotated", int.class)));
    }

    @Test
    public void shouldMatchAnnotatedMethodParam() throws Exception {
        TestAnnotated annotated = new TestAnnotated();

        assertThat(annotated, is(methodAnnotatedWithParam(TestAnnotation.class, "value", "annotated", int.class)));
    }

    @Test
    public void shouldMatchAnnotatedMethodParamValueUsingMatcher() throws Exception {
        TestAnnotated annotated = new TestAnnotated();

        assertThat(annotated, is(methodAnnotatedWithParamValue(TestAnnotation.class, "value", is(56), "annotated", int.class)));
    }

    @Test
    public void shouldMatchAnnotatedMethodParamValueUsingValue() throws Exception {
        TestAnnotated annotated = new TestAnnotated();

        assertThat(annotated, is(methodAnnotatedWithParamValue(TestAnnotation.class, "value", 56, "annotated", int.class)));
    }

    @Test
    public void shouldMatchAnnotatedConstructionParameter() throws Exception {
        TestAnnotated annotated = new TestAnnotated();

        assertThat(annotated, is(constructorParameterAnnotatedWith(TestAnnotation.class, 1, String.class, int.class)));
    }

    @Test
    public void shouldMatchAnnotatedConstructionParameterParam() throws Exception {
        TestAnnotated annotated = new TestAnnotated();

        assertThat(annotated, is(constructorParameterAnnotatedWithParam(TestAnnotation.class, "value", 1, String.class, int.class)));
    }

    @Test
    public void shouldMatchAnnotatedConstructionParameterParamValueUsingMatcher() throws Exception {
        TestAnnotated annotated = new TestAnnotated();

        assertThat(annotated, is(constructorParameterAnnotatedWithParamValue(TestAnnotation.class, "value", is(56), 1, String.class, int.class)));
    }

    @Test
    public void shouldMatchAnnotatedConstructionParameterParamValueUsingValue() throws Exception {
        TestAnnotated annotated = new TestAnnotated();

        assertThat(annotated, is(constructorParameterAnnotatedWithParamValue(TestAnnotation.class, "value", 56, 1, String.class, int.class)));
    }

    @Test
    public void shouldMatchAnnotatedMethodParameter() throws Exception {
        TestAnnotated annotated = new TestAnnotated();

        assertThat(annotated, is(methodParameterAnnotatedWith(TestAnnotation.class, 1, "methodParameterAnnotated", String.class, int.class)));
    }

    @Test
    public void shouldMatchAnnotatedMethodParameterParam() throws Exception {
        TestAnnotated annotated = new TestAnnotated();

        assertThat(annotated, is(methodParameterAnnotatedWithParam(TestAnnotation.class, "value", 1, "methodParameterAnnotated", String.class, int.class)));
    }

    @Test
    public void shouldMatchAnnotatedMethodParameterParamValueUsingMatcher() throws Exception {
        TestAnnotated annotated = new TestAnnotated();

        assertThat(annotated, is(methodParameterAnnotatedWithParamValue(TestAnnotation.class, "value", is(56), 1, "methodParameterAnnotated", String.class, int.class)));
    }

    @Test
    public void shouldMatchAnnotatedMethodParameterParamValueUsingValue() throws Exception {
        TestAnnotated annotated = new TestAnnotated();

        assertThat(annotated, is(methodParameterAnnotatedWithParamValue(TestAnnotation.class, "value", 56, 1, "methodParameterAnnotated", String.class, int.class)));
    }
}
