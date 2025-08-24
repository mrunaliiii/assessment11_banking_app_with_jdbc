package com.hdfc.minibank.dao;

import com.hdfc.minibank.Entities.Transaction;
import java.sql.Timestamp;

//import java.security.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionDAO {

    private final Connection conn;

    

    public TransactionDAO(Connection conn) {
        this.conn = conn;
    }

    public void saveTransaction(Transaction txn) throws SQLException {
        String sql = "INSERT INTO TRANSACTION (txn_id, account_no, txn_type, amount, timestamp) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, txn.getTransactionId());
            stmt.setString(2, txn.getAccountNo());
            stmt.setString(3, txn.getType().name());
            stmt.setBigDecimal(4, txn.getAmount());
            stmt.setTimestamp(5, Timestamp.valueOf(txn.getTimestamp()));
            stmt.executeUpdate();
        }
    }
}