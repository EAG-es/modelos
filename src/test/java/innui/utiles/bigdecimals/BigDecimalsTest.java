package innui.utiles.bigdecimals;

import innui.modelos.errores.oks;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;

/**
 *
 * @author emilio
 */
public class BigDecimalsTest {
    
    public BigDecimalsTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
    /**
     * Test of quitar_decimales method, of class BigDecimals.
     */
    @Ignore
    public void testQuitar_decimales() throws Exception {
        System.out.println("quitar_decimales");
        BigDecimal bigDecimal = BigDecimal.valueOf(12.987654321);
        int decimales_a_quitar = 1;
        oks ok = new oks();
        Object[] extra_array = null;
        BigDecimal expResult = BigDecimal.valueOf(12.98765432);
        BigDecimal result = BigDecimals.quitar_decimales(bigDecimal, decimales_a_quitar, ok, extra_array);
        assertEquals(expResult, result);
    }

    /**
     * Test of quitar_decimales_redondeando method, of class BigDecimals.
     */
    @Ignore
    public void testQuitar_decimales_redondeando() throws Exception {
        System.out.println("quitar_decimales_redondeando");
//        BigDecimal bigDecimal = BigDecimal.valueOf(12,987654321);
//        BigDecimal bigDecimal = BigDecimal.valueOf(12.987654321);
//        BigDecimal bigDecimal = BigDecimal.valueOf(12.987654326);
//        BigDecimal bigDecimal = BigDecimal.valueOf(12.987654324);
//        BigDecimal bigDecimal = BigDecimal.valueOf(12,987654321);
        BigDecimal bigDecimal = BigDecimal.valueOf(12.0987654326);
        int decimales_a_quitar = 1;
        RoundingMode roundingMode = RoundingMode.HALF_UP;
        oks ok = new oks();
        Object[] extra_array = null;
        BigDecimal expResult = BigDecimal.valueOf(12.09876543);
        BigDecimal result = BigDecimals.quitar_decimales_redondeando(bigDecimal, decimales_a_quitar, roundingMode, ok, extra_array);
        assertEquals(expResult, result);
    }

    /**
     * Test of divide_0 method, of class BigDecimals.
     */
    @Ignore
    public void testDivide_0() throws Exception {
        System.out.println("divide_0");
        BigDecimal dividendo = null;
        BigDecimal divisor = null;
        oks ok = null;
        Object[] extra_array = null;
        BigDecimal expResult = null;
        BigDecimal result = BigDecimals.divide_0(dividendo, divisor, ok, extra_array);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of divideToIntegralValue_0 method, of class BigDecimals.
     */
    @Ignore
    public void testDivideToIntegralValue_0() throws Exception {
        System.out.println("divideToIntegralValue_0");
        BigDecimal dividendo = null;
        BigDecimal divisor = null;
        oks ok = new oks();
        Object[] extra_array = null;
        BigDecimal expResult = null;
        BigDecimal result = BigDecimals.divideToIntegralValue_0(dividendo, divisor, ok, extra_array);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of nulo_es_0 method, of class BigDecimals.
     */
    @Ignore
    public void testNulo_es_0() {
        System.out.println("nulo_es_0");
        BigDecimal bigDecimal = null;
        BigDecimal expResult = null;
        BigDecimal result = BigDecimals.nulo_es_0(bigDecimal);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
