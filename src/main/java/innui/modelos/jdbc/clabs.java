/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package innui.modelos.jdbc;

import innui.bases;
import innui.modelos.configuraciones.ResourceBundles;
import innui.modelos.errores.oks;
import innui.modelos.internacionalizacion.tr;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;
import java.util.ResourceBundle;

/**
 *
 * @author informatica
 */
public class clabs extends bases {
    public static String k_in_ruta = "in/innui/modelos/jdbc/in";
    public Connection connection = null;
    
    /**
     * Devuelve una lista con las filas de la tabla productos 
     * @param jdbc_peticion
     * @param ok
     * @param extra_array
     * @return null si hay error, una lista de productos si no hay error
     * @throws java.lang.Exception
     */
    public boolean leer(sql_comandos jdbc_peticion, oks ok, Object... extra_array) throws Exception {
        if (ok.es == false) { return false; }
        ResourceBundle in;
        String sql_comando;
        sql_comando = jdbc_peticion.getLeer_sql();
        Map<String, Object> columnas_mapa;
        Object object;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql_comando)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                jdbc_peticion.crear_lecturas_lista(ok, extra_array);
                if (ok.es == false) { return false; }
                while (resultSet.next() ) {
                    int i = 1;
                    columnas_mapa = jdbc_peticion.crear_columnas_mapa(ok);
                    if (ok.es == false) { break; }
                    for(String nombre: jdbc_peticion.columnas_leer_lista) {
                        object = resultSet.getObject(i);
                        if (resultSet.wasNull()) {
                            object = null;
                        }
                        columnas_mapa.put(nombre, object);
                        i = i + 1;
                    }
                    jdbc_peticion.lecturas_lista.add(columnas_mapa);
                }
            } catch (Exception e) {
                in = ResourceBundles.getBundle(k_in_ruta);
                ok.setTxt(tr.in(in, "Error al leer: ") + sql_comando, e);
            }
        } catch (Exception e) {
            in = ResourceBundles.getBundle(k_in_ruta);
            ok.setTxt(tr.in(in, "Error al leer: ") + sql_comando, e);
        }
        return ok.es;
    }
    /**
     * Borra un registro, si es posible (no hay restricciones de integridad)
     * @param jdbc_peticion
     * @param ok
     * @param extra_array
     * @return 
     * @throws java.lang.Exception 
     */
    public boolean borrar(sql_comandos jdbc_peticion, oks ok, Object... extra_array) throws Exception {
        if (ok.es == false) { return false; }
        ResourceBundle in;
        String sql_comando;
        sql_comando = jdbc_peticion.borrar_sql;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql_comando)) {
            int row = preparedStatement.executeUpdate();
            if (row != 1) {
                in = ResourceBundles.getBundle(k_in_ruta);
                ok.setTxt(tr.in(in, "Error al borrar: ") + sql_comando);
            }
        } catch (Exception e) {
            in = ResourceBundles.getBundle(k_in_ruta);
            ok.setTxt(tr.in(in, "Error al borrar: ") + sql_comando, e);
        }
        return ok.es;
    }
    /**
     * Crear (inserta) una nueva fila
     * @param jdbc_peticion
     * @param ok
     * @param extra_array
     * @return
     * @throws Exception 
     */
    public boolean crear(sql_comandos jdbc_peticion, oks ok, Object... extra_array) throws Exception {
        if (ok.es == false) { return false; }
        ResourceBundle in;
        String sql_comando;
        sql_comando = jdbc_peticion.crear_sql;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql_comando)) {
            int row = preparedStatement.executeUpdate();
            if (row != 1) {
                in = ResourceBundles.getBundle(k_in_ruta);
                ok.setTxt(tr.in(in, "Error al crear: ") + sql_comando);
            }
        } catch (Exception e) {
            in = ResourceBundles.getBundle(k_in_ruta);
            ok.setTxt(tr.in(in, "Error al crear: ") + sql_comando, e);
        }
        return ok.es;
    }

    /**
     * Crear (inserta) una nueva fila y obtiene las nuevas claves autogeneradas
     * @param jdbc_peticion
     * @param ok
     * @param extra_array
     * @return
     * @throws Exception 
     */
    public boolean crear_auto(sql_comandos jdbc_peticion, oks ok, Object... extra_array) throws Exception {
        if (ok.es == false) { return false; }
        ResourceBundle in;
        String sql_comando;
        Map<String, Object> columnas_mapa;
        Object object;
        sql_comando = jdbc_peticion.crear_sql;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql_comando, Statement.RETURN_GENERATED_KEYS)) {
            int row = preparedStatement.executeUpdate();
            if (row != 1) {
                in = ResourceBundles.getBundle(k_in_ruta);
                ok.setTxt(tr.in(in, "Error al crear: ") + sql_comando);
            }
            int i = 1;
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                jdbc_peticion.crear_lecturas_nueva_clave_lista(ok, extra_array);
                if (ok.es == false) { return false; }
                while (resultSet.next() ) {
                    i = 1;
                    columnas_mapa = jdbc_peticion.crear_columnas_mapa(ok);
                    if (ok.es == false) { break; }
                    for(String nombre: jdbc_peticion.columnas_leer_clave_nueva_lista) {
                        object = resultSet.getObject(i);
                        if (resultSet.wasNull()) {
                            object = null;
                        }
                        columnas_mapa.put(nombre, object);
                        i = i + 1;
                    }
                    jdbc_peticion.lecturas_nueva_clave_lista.add(columnas_mapa);
                }
            } catch (Exception e) {
                in = ResourceBundles.getBundle(k_in_ruta);
                ok.setTxt(tr.in(in, "Error al recuperar la clave creada: ") + sql_comando, e);
            }
        } catch (Exception e) {
            in = ResourceBundles.getBundle(k_in_ruta);
            ok.setTxt(tr.in(in, "Error al crear: ") + sql_comando, e);
        }
        return ok.es;
    }

    public boolean actualizar(sql_comandos jdbc_peticion, oks ok, Object... extra_array) throws Exception {
        if (ok.es == false) { return false; }
        ResourceBundle in;
        String sql_comando;
        sql_comando = jdbc_peticion.actualizar_sql;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql_comando)) {
            int row = preparedStatement.executeUpdate();
            if (row != 1) {
                in = ResourceBundles.getBundle(k_in_ruta);
                ok.setTxt(tr.in(in, "Error al actualizar: ") + sql_comando);
            }
        } catch (Exception e) {
            in = ResourceBundles.getBundle(k_in_ruta);
            ok.setTxt(tr.in(in, "Error al actualizar: ") + sql_comando, e);
        }
        return ok.es;
    }
}
