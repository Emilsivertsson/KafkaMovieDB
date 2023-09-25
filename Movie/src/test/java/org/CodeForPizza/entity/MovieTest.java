package org.CodeForPizza.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MovieTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Movie#Movie()}
     *   <li>{@link Movie#toString()}
     * </ul>
     */
    @Test
    void testConstructor() {
        assertEquals("Movie{title='null', year='null'}", (new Movie()).toString());
        assertEquals("Movie{title='Dr', year='Year'}", (new Movie("Dr", "Year")).toString());
    }
}

