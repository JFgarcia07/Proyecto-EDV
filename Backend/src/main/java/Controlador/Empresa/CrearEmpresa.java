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
public class CrearEmpresa {
    public int crearEmpresa(JSONObject json){
        EmpresaDB empresaDB = new EmpresaDB();
        
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
}
