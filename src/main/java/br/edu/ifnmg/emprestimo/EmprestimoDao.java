/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifnmg.emprestimo;

import br.edu.ifnmg.librarian.Librarian;
import br.edu.ifnmg.librarian.LibrarianDao;
import br.edu.ifnmg.reader.Reader;
import br.edu.ifnmg.reader.ReaderDao;
import br.edu.ifnmg.repository.Dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;

/**
 *
 * @author joaok
 */
public class EmprestimoDao extends Dao<Emprestimo> {

    public static final String TABLE = "emprestimo";

    @Override
    public String getSaveStatement() {
        return "insert into " + TABLE + " (diaEmprestimo, diaRetorno, librarian_id, reader_id) values(?,?,?,?)";
    }

    @Override
    public String getUpdateStatement() {
        return "update " + TABLE + " set diaEmprestimo = ?, diaRetorno = ?, librarian_id = ?, reader_id = ? where id = ?";
    }

    @Override
    public String getFindByIdStatement() {
        return "select diaEmprestimo, diaRetorno, librarian_id, reader_id, id" + TABLE + " where id = ?";
    }

    @Override
    public String getFindAllStatement() {
        return "select * from " + TABLE;
    }

    @Override
    public String getDeleteStatement() {
        return "delete from " + TABLE + " where id = ?";
    }

    @Override
    public void composeSaveOrUpdateStatement(PreparedStatement pstmt, Emprestimo emprestimo) {
        try {
            if (emprestimo.getDiaEmprestimo() != null) {
                pstmt.setObject(1, emprestimo.getDiaEmprestimo(), java.sql.Types.DATE);
            }
            if (emprestimo.getDiaRetorno() != null) {
                pstmt.setObject(2, emprestimo.getDiaRetorno(), java.sql.Types.DATE);
            }
            if (emprestimo.getLibrarian().getId() != null) {
                pstmt.setObject(3, emprestimo.getLibrarian().getId(), Types.BIGINT);
            }
            if (emprestimo.getReader().getId() != null) {
                pstmt.setObject(4, emprestimo.getReader().getId(), Types.BIGINT);
            }
            if (emprestimo.getId() != null) {
                pstmt.setObject(5, emprestimo.getId(), Types.BIGINT);
            }
        } catch (Exception ex) {
            System.out.println("Exception in ComposeSave or Update " + ex);
        }
    }

    @Override
    public Emprestimo extractObject(ResultSet rs) {
        Emprestimo emprestimo = null;
        try {
            emprestimo = new Emprestimo();
            emprestimo.setDiaEmprestimo(rs.getDate("diaEmprestimo").toLocalDate());
            emprestimo.setDiaRetorno(rs.getDate("diaRetorno").toLocalDate());
            Librarian librarian = new LibrarianDao().findById(rs.getLong("librarian_id"));
            emprestimo.setLibrarian(librarian);
            Reader reader = new ReaderDao().findById(rs.getLong("reader_id"));
            emprestimo.setReader(reader);

            emprestimo.setId(rs.getLong("id"));
        } catch (Exception ex) {
            System.out.println("Exception in extractObject: " + ex);
        }
        return emprestimo;
    }
}
