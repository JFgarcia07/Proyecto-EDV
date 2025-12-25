/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.Empresa;

import DB.Empresa.EmpresaDB;
import Modelo.Empresa;
import org.json.JSONObject;

/**
 *
 * @author jfgarcianata
 */
public class CRUDEmpresa {
    
    private EmpresaDB empresaDB = new EmpresaDB();
    
    public int crearEmpresa(JSONObject json){
        
        String idEmpresa = json.getString("idEmpresa");
        String nombreEmpresa = json.getString("nombreEmpresa");
        String descripcion = json.getString("descripcion");
        
        double porcentaje = 0.0;

        String porcentajeStr = json.optString("comisionEspecifica", "");

        if (!porcentajeStr.isEmpty()) {
            try {
                porcentaje = Double.parseDouble(porcentajeStr);
            } catch (NumberFormatException e) {
                System.out.println("El formato del porcentaje no es válido, se usará 0.0");
                porcentaje = 0.0;
            }
        }

        String idComision = json.getString("idComision");
        
        Empresa empresa = new Empresa(idEmpresa, nombreEmpresa, descripcion, porcentaje, idComision);
        
        if(empresaDB.existeEmpresa(idEmpresa)){
            return 400;
        }
        
        empresaDB.crearEmpresa(empresa);
        return 200;
    }
    
    public Empresa obtnerEmpresaPorID(String idEmpresa){
        return empresaDB.obtenerJuegoPorID(idEmpresa);
    }
 
    public int actualizarEmpresa(JSONObject json) {

        String idEmpresa = json.optString("idEmpresa", "").trim();
        String nombreEmpresa = json.optString("nombreEmpresa", "").trim();
        String descripcion = json.optString("descripcion", "").trim();

        if (idEmpresa.isEmpty() || nombreEmpresa.isEmpty() || descripcion.isEmpty()) {
            return 400;
        }

        double porcentaje;
        try {
            porcentaje = json.getDouble("porcentajeEspecifico");
        } catch (Exception e) {
            return 400;
        }

        if (porcentaje < 0 || porcentaje > 1) {
            return 400;
        }

        Empresa empresa = new Empresa();
        empresa.setIdEmpresa(idEmpresa);
        empresa.setNombreEmpresa(nombreEmpresa);
        empresa.setDescripcion(descripcion);
        empresa.setPorcentajeEspecifico(porcentaje);

        int filasAfectadas = empresaDB.actualizarEmpresa(empresa);

        if (filasAfectadas == 1) {
            return 200;
        }
        
        return 500;
    }

}
