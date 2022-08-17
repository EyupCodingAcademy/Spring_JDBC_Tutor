package com.example.demo.repository;

import com.example.demo.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class BookRepository {
    private static final String SQL_SELECT = "SELECT * FROM Book";

    private RowMapper<Book> bookRowMapper = ((rs,rowNum) -> {
        return new Book(
                rs.getInt("Book_id"),
                rs.getString("name"),
                rs.getString("synopsis"),
                rs.getInt("category_id")
        );
    });
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Book> getBooks() {
        try {
            return jdbcTemplate.query(SQL_SELECT,bookRowMapper);
        } catch (Exception e) {
            return null;
        }
    }

    private static final String SQL_SELECT_ONE = "SELECT * FROM Book Where Book_id=?";

    public Book getBook(int Book_id) {
        try
        {
            return jdbcTemplate.queryForObject(SQL_SELECT_ONE,bookRowMapper,Book_id);
        }catch(Exception ex){
            throw ex;
        }
    }


    private static final String SQL_INSERT = "INSERT INTO Book (name,synopsis,category_id) VALUES (?,?,?)";

    public Integer create(String name, String synopsis, int category_id) {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1,name);
                ps.setString(2,synopsis);
                ps.setInt(3,category_id);
                return ps;
            },keyHolder);
            return keyHolder.getKey().intValue();
        }catch (Exception ex) {
            throw ex;
        }
    }
    private static final String SQL_UPDATE = "UPDATE Book SET name=? , synopsis=?, category_id = ? WHERE Book_id = ?";

    public void update(int Book_id, String name, String synopsis, int category_id) {
        try{
            jdbcTemplate.update(SQL_UPDATE,new Object[] { name,synopsis,category_id,Book_id});
        }catch (Exception ex) {
            throw ex;
        }
    }

    private static final String SQL_DELETE = "DELETE FROM Book WHERE Book_id = ?";

    public void delete(int Book_id) {
        try{
            jdbcTemplate.update(SQL_DELETE, new Object[] {Book_id});
        } catch(Exception ex) {
            throw ex;
        }
    }



}
