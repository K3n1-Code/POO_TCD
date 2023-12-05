/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifnmg.exemplary;

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
public class ExemplaryDao extends Dao<Exemplary> {
    
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
    public void composeSaveOrUpdateStatement(PreparedStatement pstmt, Exemplary exemplary) {
        try {
            if (exemplary.getDisponivel()!= null) {
                pstmt.setObject(1, exemplary.getDisponivel(), Types.BOOLEAN);
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
    public Exemplary extractObject(ResultSet rs) {
        Exemplary exemplary = null;
        try {
            exemplary = new Exemplary();
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
