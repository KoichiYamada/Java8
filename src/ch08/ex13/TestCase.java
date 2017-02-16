package ch08.ex13;

import java.lang.annotation.Repeatable;

@Repeatable(TestCases.class)
public @interface TestCase {
    int params();

    int expected();
}
