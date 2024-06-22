package com.likanug.dual;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {

    private App app;

    @BeforeEach
    void setUp() {
        app = new App();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testAppSize() {
        assertEquals(640, App.INTERNAL_CANVAS_SIDE_WIDTH);
        assertEquals(60, App.FPS);
    }
}