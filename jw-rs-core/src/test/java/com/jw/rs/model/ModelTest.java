package com.jw.rs.model;

import com.jw.rs.util.ModelInvoker;
import org.junit.Before;
import org.junit.Test;

/**
 * Junit test class for models.
 */
public class ModelTest {

    private ModelInvoker invoker;

    @Before
    public void setup() {
        invoker = new ModelInvoker();
    }

    /**
     * Test model retrieve and assignments.
     */
    @Test
    public void testModel() throws Exception {
        invoker.invokeModel(new ErrorResponse(500, "error"), true);
    }

}
