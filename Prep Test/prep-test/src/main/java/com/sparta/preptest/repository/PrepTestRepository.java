package com.sparta.preptest.repository;

import com.sparta.preptest.dto.PrepTestRequestDto;
import com.sparta.preptest.dto.PrepTestResponseDto;
import com.sparta.preptest.entity.PrepTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Component
public class PrepTestRepository {

    private final JdbcTemplate jdbcTemplate;

    public PrepTestRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public PrepTest save(PrepTest prepTest) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO item (username, title, content, price) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, prepTest.getUsername());
            preparedStatement.setString(2, prepTest.getTitle());
            preparedStatement.setString(3, prepTest.getContent());
            preparedStatement.setInt(4, prepTest.getPrice());
            return preparedStatement;
        }, keyHolder);
        Long id = keyHolder.getKey().longValue();
        prepTest.setId(id);
        return prepTest;
    }

    public List<PrepTestResponseDto> findAll() {
        String sql = "SELECT * FROM item";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new PrepTestResponseDto(
                rs.getLong("id"),
                rs.getString("username"),
                rs.getString("title"),
                rs.getString("content"),
                rs.getInt("price")
        ));
    }

    public void update(Long id, PrepTestRequestDto requestDto) {
        String sql = "UPDATE item SET username = ?, title = ?, content = ?, price = ? WHERE id = ?";
        jdbcTemplate.update(sql, requestDto.getUsername(), requestDto.getTitle(), requestDto.getContent(), requestDto.getPrice(), id);
    }

    public void delete(Long id) {
        String sql = "DELETE FROM item WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public PrepTest findById(Long id) {
        String sql = "SELECT * FROM item WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
            PrepTest prepTest = new PrepTest();
            prepTest.setId(rs.getLong("id"));
            prepTest.setUsername(rs.getString("username"));
            prepTest.setTitle(rs.getString("title"));
            prepTest.setContent(rs.getString("content"));
            prepTest.setPrice(rs.getInt("price"));
            return prepTest;
        });
    }
}
