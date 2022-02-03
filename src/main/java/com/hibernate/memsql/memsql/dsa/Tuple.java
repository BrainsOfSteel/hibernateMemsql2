package com.hibernate.memsql.memsql.dsa;

import java.util.Objects;

public class Tuple {
    int x;
    int d;

    public Tuple(int x,int d) {
        this.x = x;
        this.d = d;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tuple tuple = (Tuple) o;
        return x == tuple.x &&
                d == tuple.d;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, d);
    }
}
