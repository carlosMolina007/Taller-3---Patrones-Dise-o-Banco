package org.example;

public class Cuenta implements ICuenta {
    private String numCuenta;
    private Cliente cliente;
    private double saldo;

    public Cuenta(String numCuenta, Cliente cliente) {
        this.numCuenta = numCuenta;
        this.cliente = cliente;
        this.saldo = 0;
    }

    @Override
    public void consignar(double saldo) {
        this.saldo += saldo;
    }

    @Override
    public boolean retirar(double valor) {
        if (saldo >= valor) {
            this.saldo -= valor;
            return true;
        }
        return false;
    }

    @Override
    public double obtenerSaldo() {
        return saldo;
    }

    @Override
    public String getDescripcion() {
        return "Número de cuenta: " + numCuenta + "\nPropietario asociado: " + cliente.getNombre();
    }

    public boolean transferir(ICuenta destino, double valor) {

        if(this.saldo >= valor) {
            destino.consignar(valor);
            this.retirar(valor);
            return true;
        }
        return false;
    }


    public String getNumCuenta() {
        return numCuenta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public double getSaldo() {
        return saldo;
    }


    @Override
    public String toString() {
        return "Número de cuenta: "+numCuenta + "\n" + "\tCliente: \t\n" + cliente.toString() +  "Saldo: " + saldo;
    }
}
