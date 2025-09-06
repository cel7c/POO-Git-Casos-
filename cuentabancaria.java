public class cuentabancaria {
    // Atributos privados
    private String numeroCuenta;
    private String titular;
    private double saldo;

    // Constructor
    public cuentabancaria(String numeroCuenta, String titular, double saldoInicial) {
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.saldo = saldoInicial;
    }

    // Métodos GET
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    // Método para depositar dinero
    public void depositar(double monto) {
        if (monto > 0) {
            saldo += monto;
            System.out.println("Depósito exitoso: +S/" + monto);
        } else {
            System.out.println("Error: El monto a depositar debe ser positivo");
        }
    }

    // Método para retirar dinero con validación
    public void retirar(double monto) {
        if (monto <= 0) {
            System.out.println("Error: El monto a retirar debe ser positivo");
        } else if (monto > saldo) {
            System.out.println("Error: Fondos insuficientes. Saldo actual: S/" + saldo);
        } else {
            saldo -= monto;
            System.out.println("Retiro exitoso: -S/" + monto);
        }
    }

    // Método para mostrar información de la cuenta
    public void mostrarInformacion() {
        System.out.println("INFORMACIÓN DE LA CUENTA");
        System.out.println("Número de cuenta: " + numeroCuenta);
        System.out.println("Titular: " + titular);
        System.out.println("Saldo actual: S/" + saldo);
    }
}