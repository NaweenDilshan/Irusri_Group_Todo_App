package com.Irusri_Group_Todo_App.Irusri_Group_Todo_App.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.Irusri_Group_Todo_App.Irusri_Group_Todo_App.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;



@Component
public class UserDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int save(User user) {
		final String INSERT_SQL = "insert into user (email, password) values (?, ?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();

		return jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] { "id" });
				ps.setString(1, user.getEmail());
				ps.setString(2, user.getPassword());
				return ps;
			}
		}, keyHolder);
	}

	public User findUserByEmail(String email) {
		return jdbcTemplate.queryForObject("select * from user where email = ?", new Object[]{email},
				(rs, rowNum) -> new User(rs.getString("email"), rs.getString("password")));
	}

}
