package it.dany98.ddd_template.common;

public class AssertionConcern {
    protected AssertionConcern() {
        super();
    }

    protected void assertArgumentEquals(Object o1, Object o2, String message) {
        if (!o1.equals(o2)) {
            throw new IllegalArgumentException(message);
        }
    }

    protected void assertArgumentNotEquals(Object o1, Object o2, String message) {
        if (o1.equals(o2)) {
            throw new IllegalArgumentException(message);
        }
    }

    protected void assertArgumentFalse(boolean b, String message) {
        if (b) {
            throw new IllegalArgumentException(message);
        }
    }

    protected void assertArgumentTrue(boolean b, String message) {
        if (!b) {
            throw new IllegalArgumentException(message);
        }
    }

    protected void assertArgumentMaxLength(String s, int maximum, String message) {
        if (s.length() > maximum) {
            throw new IllegalArgumentException(message);
        }
    }

    protected void assertArgumentMinLength(String s, int minimum, String message) {
        if (s.length() < minimum) {
            throw new IllegalArgumentException(message);
        }
    }

    protected void assertArgumentLength(String s, int minimum, int maximum, String message) {
        int length = s.length();
        if (length < minimum || length > maximum) {
            throw new IllegalArgumentException(message);
        }
    }

    protected void assertArgumentNotEmpty(String s, String message) {
        if (s == null || s.trim().isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }

    protected void assertArgumentNotNull(Object o, String message) {
        if (o == null) {
            throw new IllegalArgumentException(message);
        }
    }

    protected void assertArgumentRange(double value, double minimum, double maximum, String message) {
        if (value < minimum || value > maximum) {
            throw new IllegalArgumentException(message);
        }
    }

    protected void assertArgumentRange(float value, float minimum, float maximum, String message) {
        if (value < minimum || value > maximum) {
            throw new IllegalArgumentException(message);
        }
    }

    protected void assertArgumentRange(int value, int minimum, int maximum, String message) {
        if (value < minimum || value > maximum) {
            throw new IllegalArgumentException(message);
        }
    }

    protected void assertArgumentRange(long value, long minimum, long maximum, String message) {
        if (value < minimum || value > maximum) {
            throw new IllegalArgumentException(message);
        }
    }

    protected void assertStateFalse(boolean b, String message) {
        if (b) {
            throw new IllegalStateException(message);
        }
    }

    protected void assertStateTrue(boolean b, String message) {
        if (!b) {
            throw new IllegalStateException(message);
        }
    }

}
