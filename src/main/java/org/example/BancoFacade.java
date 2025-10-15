package org.example;

public class BancoFacade {

    private final Banco banco;

    public BancoFacade() {
        banco = Banco.getInstance();
    }

    public Cliente crearCliente(String documento, String nombre, String telefono){
        Cliente cliente = new Cliente
                .Builder()
                .documento(documento)
                .nombre(nombre)
                .telefono(telefono)
                .build();
        banco.registrarCliente(cliente);
        return cliente;
    }

    public ICuenta abrirCuenta(Cliente cliente,String numCuenta) {
        Cuenta cuentaNueva = new Cuenta(numCuenta , cliente);
        banco.registrarCuenta(cuentaNueva);
        return cuentaNueva;
    }

    public ICuenta abrirCuentaConLimite(Cliente cliente, String numeroCuenta, double limiteSobregiro) {
        ICuenta cuentaBeneficio = abrirCuenta(cliente, numeroCuenta);
        ICuenta cuentaLimite = new CuentaConLimite(cuentaBeneficio, limiteSobregiro);
        banco.getListaCuentas().remove(cuentaBeneficio);
        banco.getListaCuentas().add(cuentaLimite);
        return cuentaLimite;
    }


    public void consignar(ICuenta cuenta, double monto) {
        cuenta.consignar(monto);
    }


    public boolean retirar(ICuenta cuenta, double monto) {
        return cuenta.retirar(monto);
    }

}
