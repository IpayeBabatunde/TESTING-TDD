package com.TDD.Testing.TDD;

import java.util.Objects;

public class Pair {

    private final String from;
    private final String to;

    public Pair(String from, String to) {
        this.from=from;
        this.to=to;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Pair pair=(Pair) object;
        return Objects.equals(from, pair.from) && Objects.equals(to, pair.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }

}

