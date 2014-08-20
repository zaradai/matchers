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
package com.zaradai.matchers.support;

@TestAnnotation(name = TestAnnotated.TEST_PARAM, value = TestAnnotated.TEST_VALUE)
public class TestAnnotated {
    public static final int TEST_VALUE = 56;
    public static final String TEST_PARAM = "param";

    @TestAnnotation(name = TEST_PARAM, value = TEST_VALUE)
    private String annotatedField;

    private String nonAnnotatedField;

    public TestAnnotated() {

    }

    @TestAnnotation(name = TEST_PARAM, value = TEST_VALUE)
    public TestAnnotated(String arg1) {

    }

    public TestAnnotated(String arg1, @TestAnnotation(name = TEST_PARAM, value = TEST_VALUE) int arg2) {

    }

    public void notAnnotated(int arg1) {

    }

    @TestAnnotation(name = TEST_PARAM, value = TEST_VALUE)
    public void annotated(int arg1) {

    }

    public void methodParameterAnnotated(String arg1, @TestAnnotation(name = TEST_PARAM, value = TEST_VALUE) int arg2) {

    }
}
