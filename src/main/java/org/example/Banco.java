//Singleton
package org.example;

import java.util.ArrayList;

public class Banco {

    private ArrayList<Prestamo> listaPrestamos;
    private ArrayList<ICuenta> listaCuentas;
    private ArrayList<Cliente> listaClientes;

    private static Banco bancoInstance;

    public Banco() {
        listaPrestamos = new ArrayList<>();
        listaCuentas = new ArrayList<>();
        listaClientes = new ArrayList<>();
    }

    public static Banco getInstance(){
        if (bancoInstance == null){
            bancoInstance = new Banco();
        }
        return bancoInstance;
    }

    public void registrarCliente(Cliente cliente){
        listaClientes.add(cliente);
    }

    public void registrarPrestamo(Prestamo prestamo){
        listaPrestamos.add(prestamo);
    }

    public void registrarCuenta(Cuenta cuenta){
        listaCuentas.add(cuenta);
    }

    public ArrayList<ICuenta> getListaCuentas() {
        return listaCuentas;
    }

    public ArrayList<Prestamo> getListaPrestamos() {
        return listaPrestamos;
    }
}
