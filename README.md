[![Build Status](https://travis-ci.org/zaradai/matchers.svg?branch=master)](https://travis-ci.org/zaradai/matchers)

# Matchers

Hamcrest extended matcher for Annotations

## Introduction

The library is used to help aid unit testing of annotations.  The range of annotations that can be tested are :-

- [Types - Classes, Interfaces, Enums](#Types)
- [Constructors](#Constructors)
- [Fields](#Fields)
- [Methods](#Methods)
- [Constructor Parameter](#Constructor-Parameter)
- [Method Parameter](#Method-Parameter)



## Integration with Maven

To use the official release of Annotation matchers, please use the following snippet in your pom.xml

```xml
    <dependency>
        <groupId>com.zaradai</groupId>
        <artifactId>matchers</artifactId>
        <version>0.2</version>
    </dependency>
```

## Usage

The following code snippets describe how to use the matcher library.  Each example will show the code under test and a passing test. For every type there are 4 specific tests that an be run.

- <b>AnnotatedWith</b>: <i>Check for annotation existence.</i>
- <b>AnnotatedWithParam</b>: <i>Check for annotation and that it has the specified parameter.</i>
- <b>AnnotatedWithParamValue</b>: <i>Check for annotation and that it has the specified parameter and value.  The value is matched using a Matcher<T>.</i>
- <b>AnnotatedWithParamValue</b>: <i>Check for annotation and that it has the specified parameter and value.  The value is matched using an equality test.</i>


### Types

For classes, interfaces, enums asserts that the type has the specified annotation.

```java
@Table(name="categories")
public class Category {

}
```

Ensure the Category class is annotated with <b>Table</b> annotation.

```java
assertThat(category, is(classAnnotatedWith(Entity.class)));
```

Ensure the Category class is annotated with <b>Table</b> annotation and that the annotation has a parameter called <b>name</b>.

```java
assertThat(category, is(classAnnotatedWithParam(Table.class, "name")));
```

Ensure the Category class is annotated with <b>Table</b> annotation and that the annotation has a parameter called <b>name</b> with value <b>categories</b> using a matcher.

```java
assertThat(category, is(classAnnotatedWithParamValue(Table.class, "name", is("categories"))));
```

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

Ensure the Point class is annotated with <b>ConstructorProperties</b> annotation.  The specific constructor we are interested in has a signature of 2 int types so these have to be include so that the matcher can locate the constructor under test.

```java
assertThat(point, is(constructorAnnotatedWith(ConstructorProperties.class, int.class, int.class)));
```

Now ensure the annotation has a <b>value</b> parameter.

```java
assertThat(point, is(constructorAnnotatedWithParam(ConstructorProperties.class, "value", int.class, int.class)));
```

Check to ensure we have supplied the correct values to the annotation.  We shall use a matcher to ensure that all values have been supplied in the correct order.

```java
assertThat(point, is(constructorAnnotatedWithParamValue(ConstructorProperties.class, "value", arrayContaining("x", "y"), int.class, int.class)));
```

Finally similar to above but a bit more long winded, matching using equality.

```java
String[] toTest = {"x", "y"};

assertThat(point, is(constructorAnnotatedWithParamValue(ConstructorProperties.class, "value", toTest, int.class, int.class)));
```

### Fields

### Constructor Parameter

### Method-Parameter


