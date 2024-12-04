CREATE TABLE IF NOT EXISTS Transactions (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name TEXT NOT NULL,
    amount REAL NOT NULL,
    transaction_date DATE NOT NULL,
    status TEXT,
    purpose TEXT,
    mode TEXT
);
