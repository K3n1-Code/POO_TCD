package br.edu.ifnmg.librarian;

import br.edu.ifnmg.credential.Credential;
import br.edu.ifnmg.credential.CredentialDao;
import br.edu.ifnmg.repository.Dao;
import br.edu.ifnmg.repository.DbConnection;
import static br.edu.ifnmg.repository.DbConnection.getConnection;
import br.edu.ifnmg.user.UserDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LibrarianDao extends Dao<Librarian> {
    public static final String TABLE = "librarian";

    @Override
    public String getSaveStatement() {
        return "insert into " + TABLE + "(name,email,birthdate,id) values (?,?,?,?)";
    }

    @Override
    public String getUpdateStatement() {
        return "update " + TABLE + " set name = ?, email = ?, birthdate = ? where id = ?";
    }

    @Override
    public Long saveOrUpdate(Librarian e) {
        Long idLibrarian = new UserDao().saveOrUpdate(e);
        if ( e.getId() == null || e.getId() == 0) {
            e.setId(-idLibrarian);
        } else {
            e.setId(idLibrarian);
        }
        
        super.saveOrUpdate(e);
        new CredentialDao().saveOrUpdate(e.getCredential());
        
        return idLibrarian;
    }

    @Override
    public void composeSaveOrUpdateStatement(PreparedStatement pstmt, Librarian e){
        try {
            if(e.getId() != null && e.getId() < 0) {
                pstmt.setString(1, e.getName());
                pstmt.setString(2, e.getEmail());
                pstmt.setObject(3, e.getBirthDate(), java.sql.Types.DATE);
                pstmt.setLong(4, -e.getId());
            } else {
                pstmt.setString(1, e.getName());
                pstmt.setString(2, e.getEmail());
                pstmt.setObject(3, e.getBirthDate(), java.sql.Types.DATE);
                pstmt.setLong(4, e.getId());
            }
        } catch ( SQLException ex ) {
            System.out.println("Exception in composeSave or Update: " + ex);
        }
    }

    @Override
    public String getFindByIdStatement() {
        return "select name, email, birthdate, id from " + TABLE + " where id = ?";
    }
    
//    @Override
//    public String getFindByIdStatement() {
//        return "select t.id, name, email, birthDate"
//                + " from " + TABLE + " t"
//                + " inner join " + UserDao.TABLE + " te"
//                + " on t.id = te.id"
//                + " where t.id = ?";
//    }

    @Override
    public String getFindAllStatement() {
        return "select id, name, email, birthdate from " + TABLE;
    }

    @Override
    public String getDeleteStatement() {
        return "delete from " + TABLE + " where id = ?";
    }

    @Override
    public Librarian extractObject(ResultSet rs) {

        Librarian queryLibrarian = null;

        try {

            queryLibrarian = new Librarian();
            
            queryLibrarian.setId(rs.getLong("id"));
            Credential credential = new CredentialDao().findById(queryLibrarian.getId());
            queryLibrarian.setName(credential.getUser().getName());
            queryLibrarian.setEmail(credential.getUser().getEmail());
            queryLibrarian.setRole(credential.getUser().getRole());
            queryLibrarian.setBirthDate(credential.getUser().getBirthDate());
            queryLibrarian.setCredential(credential);
        } catch (Exception ex) {
            System.out.println("Exception in extractObject: " + ex);
        }

        return queryLibrarian;
    }
    
    @Override
    public void delete(Long id) {

        try (PreparedStatement preparedStatement
                = DbConnection.getConnection().prepareStatement(getDeleteStatement())) {
            preparedStatement.setLong(1, id);

            System.out.println(">> SQL: " + preparedStatement);

            preparedStatement.executeUpdate();

        } catch (Exception ex) {
            System.out.println("Exception: " + ex);
        }

    }
}