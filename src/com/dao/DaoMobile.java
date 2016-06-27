/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.jdbc.DBUtility;
import com.model.RetornaProducaoMob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author user
 */
public class DaoMobile {

    private final Connection dbConnection;
    private PreparedStatement pStmt;

    public DaoMobile() {
        dbConnection = DBUtility.getConnection();
    }

    public Vector<RetornaProducaoMob> buscarTodos() {

        Vector<RetornaProducaoMob> resultados = new Vector<RetornaProducaoMob>();
        String query = ("select * from mob_fat_entregue");

        try {
            Statement stmt = dbConnection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                RetornaProducaoMob valor = new RetornaProducaoMob();
                // pega todos os atributos da pessoa  
                valor.setTotal(rs.getInt("total"));

                resultados.add(valor);
            }
            return resultados;
        } catch (SQLException e) {
            imprimeErro("Erro ao buscar pessoas", e.getMessage());
            return null;
        }
    }

    private void imprimeErro(String erro_ao_buscar_pessoas, String message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
