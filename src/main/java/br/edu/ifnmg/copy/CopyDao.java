/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifnmg.copy;

import br.edu.ifnmg.book.Book;
import br.edu.ifnmg.book.BookDao;
import br.edu.ifnmg.emprestimo.Emprestimo;
import br.edu.ifnmg.emprestimo.EmprestimoDao;
import br.edu.ifnmg.repository.Dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;

/**
 *
 * @author joaok
 */
public class CopyDao extends Dao<Copy> {
    
        public static final String TABLE = "exemplary";

    @Override
    public String getSaveStatement() {
        return "insert into " + TABLE + " (available, book_id, emprestimo_id) values(?,?,?)";
    }

    @Override
    public String getUpdateStatement() {
        return "update " + TABLE + " set available = ?, book_id = ?, emprestimo_id = ? where id = ?";
    }

    @Override
    public String getFindByIdStatement() {
        return "select available, book_id, emprestimo_id, id from " + TABLE + " where id = ?";
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
    public void composeSaveOrUpdateStatement(PreparedStatement pstmt, Copy exemplary) {
        try {
            if (exemplary.isDisponivel()!= null) {
                pstmt.setObject(1, exemplary.isDisponivel(), Types.BOOLEAN);
            }
            if (exemplary.getBook().getId() != null) {
                pstmt.setObject(2, exemplary.getBook().getId(), Types.BIGINT);
            }
            if (exemplary.getEmprestimo().getId() != null) {
                pstmt.setObject(3, exemplary.getEmprestimo().getId(), Types.BIGINT);
            }
            if (exemplary.getId() != null) {
                pstmt.setObject(4, exemplary.getId(), Types.BIGINT);
            }
        } catch (Exception ex) {
            System.out.println("Exception in ComposeSave or Update " + ex);
        }
    }

    @Override
    public Copy extractObject(ResultSet rs) {
        Copy exemplary = null;
        try {
            exemplary = new Copy();
            exemplary.setDisponivel(rs.getBoolean("available"));
            Book book = new BookDao().findById(rs.getLong("book_id"));
            exemplary.setBook(book);
            Emprestimo emprestimo = new EmprestimoDao().findById(rs.getLong("emprestimo_id"));
            exemplary.setEmprestimo(emprestimo);
            exemplary.setId(rs.getLong("id"));
        } catch (Exception ex) {
            System.out.println("Exception in extractObject: " + ex);
        }
        return exemplary;
    }
}
