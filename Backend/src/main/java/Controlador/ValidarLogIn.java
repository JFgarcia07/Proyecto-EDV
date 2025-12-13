/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import DB.LogInDB;
import Modelo.Usuario;

/**
 *
 * @author jfgarcianata
 */
public class ValidarLogIn {

    public boolean validarCredenciales(String email, String passsword) {
        Usuario user = new Usuario();
        user.setEmail(email);
        user.setPassword(passsword);

        LogInDB logDB = new LogInDB();

        return logDB.validarLogIn(user);
    }
}
