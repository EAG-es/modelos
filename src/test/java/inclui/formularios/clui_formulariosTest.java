package inclui.formularios;

import static inclui.formularios.control_entradas.k_entradas_tipo_color;
import static inclui.formularios.control_entradas.k_entradas_tipo_email;
import static inclui.formularios.control_entradas.k_entradas_tipo_texto;
import innui.formularios.controles;
import innui.modelos.errores.oks;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import static inclui.formularios.control_entradas.k_entradas_tipo_fecha;
import static inclui.formularios.control_entradas.k_entradas_tipo_fecha_y_hora;
import static inclui.formularios.control_entradas.k_entradas_tipo_hora;
import static inclui.formularios.control_entradas.k_entradas_tipo_numero;
import static inclui.formularios.control_entradas.k_entradas_tipo_password;
import static inclui.formularios.control_entradas.k_entradas_tipo_radio;
import static inclui.formularios.control_entradas.k_entradas_tipo_reset;
import static inclui.formularios.control_entradas.k_entradas_tipo_submit;
import static inclui.formularios.control_entradas.k_entradas_tipo_telefono;
import static innui.formularios.controles.k_fase_procesamiento;
import java.util.Date;

/**
 *
 * @author emilio
 */
public class clui_formulariosTest {
    
    public clui_formulariosTest() {
    }

    /**
     * Test of _poner_control method, of class clui_formularios.
     */
    @Ignore
    public void test_poner_control() throws Exception {
        System.out.println("_poner_control");
        controles control = null;
        oks ok = null;
        Object[] extras_array = null;
        clui_formularios instance = new clui_formularios();
        boolean expResult = false;
        boolean result = instance._poner_control(control, ok, extras_array);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of _iniciar_formulario method, of class clui_formularios.
     */
    @Ignore
    public void test_iniciar_formulario() throws Exception {
        System.out.println("_iniciar_formulario");
        String modo_operacion = "";
        oks ok = null;
        Object[] extras_array = null;
        clui_formularios instance = new clui_formularios();
        boolean expResult = false;
        boolean result = instance._iniciar_formulario(modo_operacion, ok, extras_array);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of capturar method, of class clui_formularios.
     */
    @Ignore
    public void testCapturar() throws Exception {
        System.out.println("capturar");
        String modo_operacion = "";
        oks ok = null;
        Object[] extras_array = null;
        clui_formularios instance = new clui_formularios();
        boolean expResult = false;
        boolean result = instance.capturar(modo_operacion, ok, extras_array);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of procesar method, of class clui_formularios.
     * @throws java.lang.Exception
     */
    @Test
    public void testProcesar() throws Exception {
        System.out.println("procesar");
        oks ok = new oks();
        clui_formularios clui_formulario = new clui_formularios();
        boolean expResult = true;
        control_entradas entrada_texto = new control_entradas();
        entrada_texto.iniciar(k_entradas_tipo_texto, ok);
        assertEquals(true, ok.es);
        entrada_texto.poner_en_formulario(clui_formulario, "nombre_apellidos", null, "Introduzca su nombre y sus apellidos. ", null, ok);
        assertEquals(true, ok.es);
        control_entradas entrada_radio = new control_entradas();
        entrada_radio.iniciar(k_entradas_tipo_radio, ok);
        assertEquals(true, ok.es);
        entrada_radio.poner_en_formulario(clui_formulario, "sexo", null, "Opción 1/2: ¿Sexo XY (macho)?. ", null, ok);
        assertEquals(true, ok.es);
        entrada_radio = new control_entradas();
        entrada_radio.iniciar(k_entradas_tipo_radio, ok);
        assertEquals(true, ok.es);
        entrada_radio.poner_en_formulario(clui_formulario, "sexo", null, "Opción 2/2: ¿Sexo XX (hembra)?. ", null, ok);
        assertEquals(true, ok.es);
        control_entradas entrada_fecha = new control_entradas();
        entrada_fecha.iniciar(k_entradas_tipo_fecha, ok);
        assertEquals(true, ok.es);
        entrada_fecha.poner_en_formulario(clui_formulario, "fecha", null, "Introduzca su fecha de nacimiento. ", null, ok);
        assertEquals(true, ok.es);
        control_entradas entrada_hora = new control_entradas();
        entrada_hora.iniciar(k_entradas_tipo_hora, ok);
        assertEquals(true, ok.es);
        entrada_hora.poner_en_formulario(clui_formulario, "hora", null, "Introduzca la hora actual. ", null, ok);
        assertEquals(true, ok.es);
        control_entradas entrada_color = new control_entradas();
        entrada_color.iniciar(k_entradas_tipo_color, ok);
        assertEquals(true, ok.es);
        entrada_color.poner_en_formulario(clui_formulario, "color", null, "Introduzca su color favorito. ", null, ok);
        assertEquals(true, ok.es);
        control_entradas entrada_email = new control_entradas();
        entrada_email.iniciar(k_entradas_tipo_email, ok);
        assertEquals(true, ok.es);
        entrada_email.poner_en_formulario(clui_formulario, "email", null, "Introduzca su email. ", null, ok);
        assertEquals(true, ok.es);
        control_entradas entrada_contraseña = new control_entradas();
        entrada_contraseña.iniciar(k_entradas_tipo_password, ok);
        assertEquals(true, ok.es);
        entrada_contraseña.poner_en_formulario(clui_formulario, "password", null, "Introduzca la contraseña que desea establecer. ", null, ok);
        assertEquals(true, ok.es);
        entrada_contraseña = new control_entradas();
        entrada_contraseña.iniciar(k_entradas_tipo_password, ok);
        assertEquals(true, ok.es);
        entrada_contraseña.poner_en_formulario(clui_formulario, "password_repetida", null, "Repita la contraseña que desea establecer. ", null, ok);
        assertEquals(true, ok.es);
        control_entradas entrada_telefono = new control_entradas();
        entrada_telefono.iniciar(k_entradas_tipo_telefono, ok);
        assertEquals(true, ok.es);
        entrada_telefono.poner_en_formulario(clui_formulario, "telefono", null, "Introduzca su teléfono. ", null, ok);
        assertEquals(true, ok.es);
        entrada_texto = new control_entradas();
        entrada_texto.iniciar(k_entradas_tipo_texto, ok);
        assertEquals(true, ok.es);
        entrada_texto.poner_en_formulario(clui_formulario, "calle", null, "Introduzca primera parte de su dirección (solo nombre de la calle). ", null, ok);
        assertEquals(true, ok.es);
        control_entradas entrada_numero = new control_entradas();
        entrada_texto.iniciar(k_entradas_tipo_numero, ok);
        assertEquals(true, ok.es);
        entrada_numero.poner_en_formulario(clui_formulario, "portal_num", null, "Introduzca el número del portal de su dirección. ", null, ok);
        assertEquals(true, ok.es);
        entrada_texto = new control_entradas();
        entrada_texto.iniciar(k_entradas_tipo_texto, ok);
        assertEquals(true, ok.es);
        entrada_texto.poner_en_formulario(clui_formulario, "calle_extra", null, "Introduzca parte extra de su dirección (piso y puerta se piden a continuación). ", null, ok);
        assertEquals(true, ok.es);
        entrada_numero = new control_entradas();
        entrada_texto.iniciar(k_entradas_tipo_numero, ok);
        assertEquals(true, ok.es);
        entrada_numero.poner_en_formulario(clui_formulario, "piso_num", null, "Introduzca el piso de su dirección (0 si no hay). ", null, ok);
        assertEquals(true, ok.es);
        entrada_numero = new control_entradas();
        entrada_texto.iniciar(k_entradas_tipo_numero, ok);
        assertEquals(true, ok.es);
        entrada_numero.poner_en_formulario(clui_formulario, "puerta_num", null, "Introduzca la puerta de su dirección (0 si no hay). ", null, ok);
        assertEquals(true, ok.es);
        entrada_texto = new control_entradas();
        entrada_texto.iniciar(k_entradas_tipo_texto, ok);
        assertEquals(true, ok.es);
        entrada_texto.poner_en_formulario(clui_formulario, "vivienda_extra", null, "Introduzca parte extra de identificacion de su vivienda (la ciudad se pide a continuación). ", null, ok);
        assertEquals(true, ok.es);
        entrada_texto = new control_entradas();
        entrada_texto.iniciar(k_entradas_tipo_texto, ok);
        assertEquals(true, ok.es);
        entrada_texto.poner_en_formulario(clui_formulario, "ciudad", null, "Introduzca la ciudad. ", null, ok);
        assertEquals(true, ok.es);
        entrada_texto = new control_entradas();
        entrada_texto.iniciar(k_entradas_tipo_numero, ok);
        assertEquals(true, ok.es);
        entrada_texto.poner_en_formulario(clui_formulario, "codigo_postal", null, "Introduzca el código postal. ", null, ok);
        assertEquals(true, ok.es);
        entrada_texto = new control_entradas();
        entrada_texto.iniciar(k_entradas_tipo_texto, ok);
        assertEquals(true, ok.es);
        entrada_texto.poner_en_formulario(clui_formulario, "provincia_estado", null, "Introduzca la provincia/estado. ", null, ok);
        assertEquals(true, ok.es);
        entrada_texto = new control_entradas();
        entrada_texto.iniciar(k_entradas_tipo_texto, ok);
        assertEquals(true, ok.es);
        entrada_texto.poner_en_formulario(clui_formulario, "pais", null, "Introduzca el país. ", null, ok);
        assertEquals(true, ok.es);
        control_entradas entrada_fecha_y_hora = new control_entradas() {
                @Override
                public boolean _validar_fecha_y_hora(Object objeto_a_validar, oks ok, Object ... extras_array) throws Exception {
                    try {
                        if (ok.es == false) { return false; }
                        super._validar_fecha_y_hora(objeto_a_validar, ok, extras_array);
                        if (ok.es == false) { return false; }
                        Date date;
                        date = (Date) super._convertir(k_fase_procesamiento, objeto_a_validar, ok, extras_array);
                        if (ok.es == false) { return false; }
                        if (date == null) {
                            return true;
                        }
                        Date actual_date = new Date();
                        if (actual_date.getTime() < date.getTime()) {
                            return true;
                        } else {
                            ok.setTxt("La fecha introducida no puede ser anterior a la fecha actual. ");
                        }
                    } catch (Exception e) {
                        ok.setTxt(e);
                    }
                    return ok.es;
                }
            };
        entrada_fecha_y_hora.iniciar(k_entradas_tipo_fecha_y_hora, ok);
        assertEquals(true, ok.es);
        entrada_fecha_y_hora.poner_en_formulario(clui_formulario, "fecha_y_hora_disponibilidad", null, "Introduzca la fecha y la hora a partir de la que desea que la información proporcionada quede disponible . ", null, ok);
        assertEquals(true, ok.es);
        control_entradas entrada_reset = new control_entradas();
        entrada_reset.iniciar(k_entradas_tipo_reset, ok);
        assertEquals(true, ok.es);
        entrada_reset.poner_en_formulario(clui_formulario, "reset", null, "¿Desea revisar o volver a hacer el formulario?. ", null, ok);
        assertEquals(true, ok.es);
        control_entradas entrada_submit = new control_entradas();
        entrada_submit.iniciar(k_entradas_tipo_submit, ok);
        assertEquals(true, ok.es);
        entrada_submit.poner_en_formulario(clui_formulario, "submit", null, "¿Desea enviar el formulario? (Si no es así, se cancelará). ", null, ok);
        assertEquals(true, ok.es);
        boolean result = clui_formulario.procesar(ok);
        assertEquals(expResult, result);
    }

    /**
     * Test of revisar_nombres_repetidos method, of class clui_formularios.
     */
    @Ignore
    public void testRevisar_nombres_repetidos() throws Exception {
        System.out.println("revisar_nombres_repetidos");
        oks ok = null;
        Object[] extras_array = null;
        clui_formularios instance = new clui_formularios();
        boolean expResult = false;
        boolean result = instance.revisar_nombres_repetidos(ok, extras_array);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
