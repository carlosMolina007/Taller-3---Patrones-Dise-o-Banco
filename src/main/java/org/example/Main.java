package org.example;

import org.w3c.dom.ls.LSOutput;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        BancoFacade bancoFacade = new BancoFacade();
        Cliente cliente = null;
        ICuenta cuenta = null;

        int opcion;
        do {
            String menu = """
                    === SISTEMA BANCARIO ===
                    1. Registrar cliente
                    2. Abrir cuenta normal
                    3. Abrir cuenta con bonificación
                    4. Abrir cuenta con límite de sobregiro
                    5. Consignar dinero
                    6. Retirar dinero
                    7. Crear préstamo
                    8. Abonar préstamo
                    9. Ver información de cuenta
                    0. Salir
                    """;

            try {
                opcion = Integer.parseInt(JOptionPane.showInputDialog(menu + "\nSeleccione una opción:"));
            } catch (Exception e) {
                opcion = -1;
            }

            switch (opcion) {
                case 1 -> {
                    String doc = JOptionPane.showInputDialog("Ingrese documento del cliente:");
                    String nombre = JOptionPane.showInputDialog("Ingrese nombre del cliente:");
                    String tel = JOptionPane.showInputDialog("Ingrese teléfono:");
                    cliente = bancoFacade.crearCliente(doc, nombre, tel);
                    JOptionPane.showMessageDialog(null, "Cliente registrado:\n" + cliente);
                }

                case 2 -> {
                    if (cliente == null) {
                        JOptionPane.showMessageDialog(null, "Debe registrar un cliente primero.");
                        break;
                    }
                    String numCuenta = JOptionPane.showInputDialog("Número de cuenta:");
                    cuenta = bancoFacade.abrirCuenta(cliente, numCuenta);
                    JOptionPane.showMessageDialog(null, "Cuenta creada:\n" + cuenta.getDescripcion());
                }

                case 3 -> {
                    if (cliente == null) {
                        JOptionPane.showMessageDialog(null, "Debe registrar un cliente primero.");
                        break;
                    }
                    String numCuenta = JOptionPane.showInputDialog("Número de cuenta:");
                    double bono = Double.parseDouble(JOptionPane.showInputDialog("Porcentaje de bonificación (ej: 0.05 para 5%):"));
                    cuenta = new CuentaConBeneficio(bancoFacade.abrirCuenta(cliente, numCuenta), bono);
                    JOptionPane.showMessageDialog(null, "Cuenta bonificada creada correctamente.");
                }

                case 4 -> {
                    if (cliente == null) {
                        JOptionPane.showMessageDialog(null, "Debe registrar un cliente primero.");
                        break;
                    }
                    String numCuenta = JOptionPane.showInputDialog("Número de cuenta:");
                    double limite = Double.parseDouble(JOptionPane.showInputDialog("Límite de sobregiro:"));
                    cuenta = bancoFacade.abrirCuentaConLimite(cliente, numCuenta, limite);
                    JOptionPane.showMessageDialog(null, "Cuenta con límite de sobregiro creada correctamente.");
                }

                case 5 -> {
                    if (cuenta == null) {
                        JOptionPane.showMessageDialog(null, "Debe crear una cuenta primero.");
                        break;
                    }
                    double monto = Double.parseDouble(JOptionPane.showInputDialog("Monto a consignar:"));
                    bancoFacade.consignar(cuenta, monto);
                    JOptionPane.showMessageDialog(null, "Consignación realizada.\nSaldo actual: " + cuenta.obtenerSaldo());
                }

                case 6 -> {
                    if (cuenta == null) {
                        JOptionPane.showMessageDialog(null, "Debe crear una cuenta primero.");
                        break;
                    }
                    double retiro = Double.parseDouble(JOptionPane.showInputDialog("Monto a retirar:"));
                    boolean exito = bancoFacade.retirar(cuenta, retiro);
                    JOptionPane.showMessageDialog(null,
                            (exito ? "Retiro exitoso." : "Fondos insuficientes.") +
                                    "\nSaldo actual: " + cuenta.obtenerSaldo());
                }

                case 7 -> {
                    if (cuenta == null || !(cuenta instanceof Cuenta)) {
                        JOptionPane.showMessageDialog(null, "Debe tener una cuenta base (no decorada) para préstamo.");
                        break;
                    }
                    String numPrestamo = JOptionPane.showInputDialog("Número de préstamo:");
                    double deuda = Double.parseDouble(JOptionPane.showInputDialog("Monto del préstamo:"));
                    Prestamo p = new Prestamo(numPrestamo, (Cuenta) cuenta, deuda);
                    Banco.getInstance().registrarPrestamo(p);
                    JOptionPane.showMessageDialog(null, "Préstamo registrado:\n" + p);
                }

                case 8 -> {
                    if (Banco.getInstance().getListaCuentas().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No hay préstamos registrados aún.");
                        break;
                    }

                    String numPrestamo = JOptionPane.showInputDialog("Número del préstamo a abonar:");
                    double abono = Double.parseDouble(JOptionPane.showInputDialog("Monto del abono:"));
                    Prestamo prestamoEncontrado = null;

                    // Buscar préstamo en la lista del banco
                    for (Prestamo p : Banco.getInstance().getListaPrestamos()) {
                        if (p.getNumPrestamo().equals(numPrestamo)) {
                            prestamoEncontrado = p;
                            break;
                        }
                    }

                    if (prestamoEncontrado != null) {
                        prestamoEncontrado.abonar(abono);
                        JOptionPane.showMessageDialog(null, "Abono realizado.\n" + prestamoEncontrado);
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontró un préstamo con ese número.");
                    }
                }

                case 9 -> {
                    if (cuenta == null) {
                        JOptionPane.showMessageDialog(null, "Debe crear una cuenta primero.");
                        break;
                    }
                    JOptionPane.showMessageDialog(null,
                            cuenta.getDescripcion() + "\nSaldo actual: " + cuenta.obtenerSaldo());
                }

                case 0 -> JOptionPane.showMessageDialog(null, "Saliendo del sistema...");

                default -> JOptionPane.showMessageDialog(null, "Opción no válida.");
            }

        } while (opcion != 0);

    }
}