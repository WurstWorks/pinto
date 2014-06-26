/**
 * TestPintoBeans
 * (C) 2012 Washington University School of Medicine
 * All Rights Reserved
 *
 * Released under the Simplified BSD License
 *
 * Created on 10/18/12 by rherri01
 */
package org.wurstworks.tools.pinto;

import org.apache.commons.lang3.StringUtils;
import org.junit.Ignore;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@PintoApplication(value = TestPintoBeans.TITLE, copyright = TestPintoBeans.COPYRIGHT, introduction = TestPintoBeans.INTRODUCTION)
public class TestPintoBeans {
    public static final String TITLE = "TestPintoBeans Unit Tests";
    public static final String COPYRIGHT = "(c) 2012, Washington University in St. Louis";
    public static final String INTRODUCTION = "Hello from NRG's Pinto Beans unit tests.";

    private static final String TEST_URI = "http://www.yahoo.com";
    private static final String TEST_NAME = "foo";
    private static final int TEST_COUNT = 10;
    public static final String TEST_TARGET_1 = "one";
    public static final String TEST_TARGET_2 = "two";
    public static final String TEST_TARGET_3 = "three";
    private static final String[] TEST_PARAMS = { "-n", TEST_NAME, "-u", TEST_URI, "-c", Integer.toString(TEST_COUNT), "--targets", TEST_TARGET_1, TEST_TARGET_2, TEST_TARGET_3 };

    @Test
    public void testBasicTestApplication() throws PintoException, URISyntaxException {
        BasicTestPintoBean bean = new BasicTestPintoBean(this, TEST_PARAMS);
        assertNotNull(bean);
        assertFalse(bean.getHelp());
        assertFalse(bean.getVersion());
        assertTrue(bean.getShouldContinue());
        assertFalse(StringUtils.isBlank(bean.getName()));
        assertEquals("foo", bean.getName());
        assertNotNull(bean.getUri());
        assertEquals(new URI(TEST_URI), bean.getUri());
        assertEquals(TEST_COUNT, bean.getCount());
        assertNotNull(bean.getTargets());
    }

    @Test
    public void testHelp() {
        BasicTestPintoBean bean = null;
        try {
            bean = new BasicTestPintoBean(this, new String[] { "-h" });
        } catch (PintoException exception) {
            fail("Found an exception in what should have been a valid parameter [" + exception.getParameter() + "]: " + exception.getType() + " " + exception.getMessage());
        }
        assertTrue(bean.getHelp());
        assertFalse(bean.getVersion());
        assertFalse(bean.getShouldContinue());
    }

    @Test
    public void testVersion() {
        BasicTestPintoBean bean = null;
        try {
            bean = new BasicTestPintoBean(this, new String[] { "-v" });
        } catch (PintoException exception) {
            fail("Found an exception in what should have been a valid parameter [" + exception.getParameter() + "]: " + exception.getType() + " " + exception.getMessage());
        }
        assertTrue(bean.getVersion());
        assertFalse(bean.getHelp());
        assertFalse(bean.getShouldContinue());
    }

    @Test
    @Ignore("Ignored because subclass methods hide base class methods. Need to walk base classes to find duplicates. Maybe the way it works now is OK?")
    public void testDuplicateParameter() {
        DuplicateParamDefinitionPintoBean bean = null;
        try {
            bean = new DuplicateParamDefinitionPintoBean(this, new String[] { "-h" });
        } catch (PintoException exception) {
            assertEquals(PintoExceptionType.DuplicateParameter, exception.getType());
        }
        assertNull(bean);
    }
}
