CREATE TABLE IF NOT EXISTS ventas_pasajes (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre_pasajero TEXT NOT NULL,
    destino TEXT NOT NULL,
    asiento TEXT NOT NULL,
    precio REAL NOT NULL,
    fecha_venta DATETIME DEFAULT CURRENT_TIMESTAMP
);