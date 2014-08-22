[![Build Status](https://travis-ci.org/zaradai/matchers.svg?branch=master)](https://travis-ci.org/zaradai/matchers)
[![Maven central](https://maven-badges.herokuapp.com/maven-central/com.zaradai/matchers/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.zaradai/matchers)
# Matchers

Hamcrest extended matcher for Annotations

## Introduction

The library is used to help unit testing of annotations.  The range of annotations that can be tested are :-

- [Types - Classes, Interfaces, Enums](#Types)
- [Constructors](#Constructors)
- [Fields](#Fields)
- [Methods](#Methods)
- [Constructor Parameter](#Constructor-Parameter)
- [Method Parameter](#Method-Parameter)



## Integration with Maven

To use the official release of Annotation matchers, please add the following snippet to your pom.xml

```xml
    <dependency>
        <groupId>com.zaradai</groupId>
        <artifactId>matchers</artifactId>
        <version>0.2</version>
    </dependency>
```

## Usage

The following examples describe how to use the matcher library.  Each example will show the code under test and a passing test. For every type there are 4 specific tests that can be run.

- <b>AnnotatedWith</b>: <i>Check for annotation existence.</i>
- <b>AnnotatedWithParam</b>: <i>Check for annotation and that it has the specified parameter.</i>
- <b>AnnotatedWithParamValue</b>: <i>Check for annotation and that it has the specified parameter and value.  The value is matched using a Matcher<T>.</i>
- <b>AnnotatedWithParamValue</b>: <i>Check for annotation and that it has the specified parameter and value.  The value is matched using an equality test.</i>


### Types

For classes, interfaces, enums, asserts that the type has the specified annotation.

Code to be tested

```java
@Table(name="categories")
public class Category {

}
```

#### AnnotatedWith

Ensure the Category class is annotated with <b>Table</b> annotation.

```java
assertThat(category, is(classAnnotatedWith(Entity.class)));
```

#### AnnotatedWithParam

Ensure the Category class is annotated with <b>Table</b> annotation and that the annotation has a parameter called <b>name</b>.

```java
assertThat(category, is(classAnnotatedWithParam(Table.class, "name")));
```

#### AnnotatedWithParamValue (using value matcher)

Ensure the Category class is annotated with <b>Table</b> annotation and that the annotation has a parameter called <b>name</b> with value <b>categories</b> using a matcher.

```java
assertThat(category, is(classAnnotatedWithParamValue(Table.class, "name", is("categories"))));
```

#### AnnotatedWithParamValue (using value equality)

Ensure the Category class is annotated with <b>Table</b> annotation and that the annotation has a parameter called <b>name</b> with value <b>categories</b> using a value to match against.

```java
assertThat(category, is(classAnnotatedWithParamValue(Table.class, "name", "categories")));
```


### Constructors

For constructors I shall use the [ConstructorProperties](http://docs.oracle.com/javase/6/docs/api/java/beans/ConstructorProperties.html) annotation that shows how the parameters of that constructor correspond to the constructed object's getter methods.
This example will also show how much neater using a value matcher is.

Code to be tested

```java
public class Point {
    private final int x;
    private final int y;

    @ConstructorProperties({"x", "y"})
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
```

#### AnnotatedWith

Ensure the Point class is annotated with <b>ConstructorProperties</b> annotation.  The specific constructor we are interested in has a signature of 2 int types so these have to be include so that the matcher can locate the constructor under test.

```java
assertThat(point, is(constructorAnnotatedWith(ConstructorProperties.class, int.class, int.class)));
```

#### AnnotatedWithParam

Now ensure the annotation has a <b>value</b> parameter.

```java
assertThat(point, is(constructorAnnotatedWithParam(ConstructorProperties.class, "value", int.class, int.class)));
```

#### AnnotatedWithParamValue (using value matcher)

Check to ensure we have supplied the correct values to the annotation.  We shall use a matcher to ensure that all values have been supplied in the correct order.

```java
assertThat(point, is(constructorAnnotatedWithParamValue(ConstructorProperties.class, "value", arrayContaining("x", "y"), int.class, int.class)));
```

#### AnnotatedWithParamValue (using value equality)

Finally similar to above but a bit more long winded, matching using equality.

```java
String[] toTest = {"x", "y"};

assertThat(point, is(constructorAnnotatedWithParamValue(ConstructorProperties.class, "value", toTest, int.class, int.class)));
```


### Fields

To test field annotations, one is required to specify the field name.  For the test code I shall be looking for javax persistence [Column](http://docs.oracle.com/javaee/5/api/javax/persistence/Column.html) annotation.

Code to be tested

```java
@Column(name = "DESC")
private String description;
```

#### AnnotatedWith

Ensure field is appropriately annotated.

```java
assertThat(category, is(fieldAnnotatedWith(Column.class, "description")));
```


#### AnnotatedWithParam

Check for the <b>name</b> parameter.

```java
 assertThat(category, is(fieldAnnotatedWithParam(Column.class, "name", "description")));
```

#### AnnotatedWithParamValue (using value matcher)

Check the <b>name</b> parameter has value <b>DESC</b> using a matcher.

```java
assertThat(category, is(fieldAnnotatedWithParamValue(Column.class, "name", is("DESC"), "description")));
```

#### AnnotatedWithParamValue (using value equality)

Check the <b>name</b> parameter has value <b>DESC</b> using equality check.

```java
assertThat(category, is(fieldAnnotatedWithParamValue(Column.class, "name", "DESC", "description")));
```


### Constructor Parameter

In addition to specifying the constructor signature using its types (in order), one also needs to indicate the parameter to test using its position.  Positional indexing is 0 based.

The code to be tested will be annotated with javax persistence Named annotation and the constructor signature with be String, int.

Code to be tested

```java
@Inject
public NamedInjection(String notNamed, @Named("test") int named) {
}
```

#### AnnotatedWith

Ensure required constructor parameter is annotated.

```java
assertThat(namedInjection, is(constructorParameterAnnotatedWith(Named.class, 1, String.class, int.class)));
```


#### AnnotatedWithParam

Check for the <b>value</b> parameter.

```java
assertThat(namedInjection, is(constructorParameterAnnotatedWithParam(Named.class, "value", 1, String.class, int.class)));
```

#### AnnotatedWithParamValue (using value matcher)

Check the <b>value</b> parameter has value <b>test</b> using a matcher.

```java
assertThat(namedInjection, is(constructorParameterAnnotatedWithParamValue(Named.class, "value", is("test"), 1, String.class, int.class)));
```

#### AnnotatedWithParamValue (using value equality)

Check the <b>value</b> parameter has value <b>test</b> using equality check.

```java
assertThat(namedInjection, is(constructorParameterAnnotatedWithParamValue(Named.class, "value", "test", 1, String.class, int.class)));
```


### Method-Parameter

In addition to specifying the method's signature using its types (in order) and method name, one also needs to indicate the parameter to test using its position.  Positional indexing is 0 based.

To make things simple I shall reuse the Named annotation from above to decorate the method parameter.

Code to be tested

```java
@Inject
public void namedInjection(String notNamed, @Named("test") int named) {
}
```

#### AnnotatedWith

Ensure required method parameter is annotated..

```java
assertThat(namedInjection, is(methodParameterAnnotatedWith(Named.class, 1, "namedInjection", String.class, int.class)));
```


#### AnnotatedWithParam

Check for the <b>value</b> parameter.

```java
assertThat(namedInjection, is(methodParameterAnnotatedWithParam(Named.class, "value", 1, "namedInjection", String.class, int.class)));
```

#### AnnotatedWithParamValue (using value matcher)

Check the <b>value</b> parameter has value <b>test</b> using a matcher.

```java
assertThat(namedInjection, is(methodParameterAnnotatedWithParamValue(Named.class, "value", is("test"), 1, "namedInjection", String.class, int.class)));
```

#### AnnotatedWithParamValue (using value equality)

Check the <b>value</b> parameter has value <b>test</b> using equality check.

```java
assertThat(namedInjection, is(methodParameterAnnotatedWithParamValue(Named.class, "value", "test", 1, "namedInjection", String.class, int.class)));
```


## Licence

[Apache 2.0](http://www.apache.org/licenses/LICENSE-2.0)


