package br.edu.ifnmg.adm;

import br.edu.ifnmg.credential.Credential;
import br.edu.ifnmg.credential.CredentialDao;
import br.edu.ifnmg.repository.Dao;
import br.edu.ifnmg.repository.DbConnection;
import br.edu.ifnmg.user.UserDao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdmDao extends Dao<Adm> {

    public static final String TABLE = "administrador";

    @Override
    public String getSaveStatement() {
        return "insert into " + TABLE + "(name,email,birthdate,id) values (?,?,?,?)";
    }

    @Override
    public String getUpdateStatement() {
        return "update " + TABLE + " set name = ?, email = ?, birthdate = ? where id = ?";
    }

    @Override
    public Long saveOrUpdate(Adm e) {
        Long idAdm = new UserDao().saveOrUpdate(e);
        if (e.getId() == null || e.getId() == 0) {
            e.setId(-idAdm);
        } else {
            e.setId(idAdm);
        }

        super.saveOrUpdate(e);
        new CredentialDao().saveOrUpdate(e.getCredential());

        return idAdm;
    }

    @Override
    public void composeSaveOrUpdateStatement(PreparedStatement pstmt, Adm e) {
        try {
            if (e.getId() != null && e.getId() < 0) {
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
        } catch (SQLException ex) {
            System.out.println("Exception in composeSave or Update: " + ex);
        }
    }

    @Override
    public String getFindByIdStatement() {
        return "select id, name, email, birthdate from " + TABLE + " where id = ?";
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
    public Adm extractObject(ResultSet rs) {

        Adm queryLibrarian = null;

        try {

            queryLibrarian = new Adm();

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
