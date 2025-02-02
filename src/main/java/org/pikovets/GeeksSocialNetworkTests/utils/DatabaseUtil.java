package org.pikovets.GeeksSocialNetworkTests.utils;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.UUID;

public class DatabaseUtil {
    private static final JdbcTemplate jdbcTemplate;

    static {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/social_network_db");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres");

        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private DatabaseUtil() {
    }

    public static void deleteUserByEmail(String email) {
        try {
            String sql = "SELECT id FROM \"user\" WHERE email = ?";
            String id = jdbcTemplate.queryForObject(sql, new Object[]{email}, String.class);
            jdbcTemplate.update("DELETE FROM Comment_Like WHERE user_id = ?", UUID.fromString(id));
            jdbcTemplate.update("DELETE FROM Comment WHERE user_id = ?", UUID.fromString(id));
            jdbcTemplate.update("DELETE FROM Post WHERE author_id = ?", UUID.fromString(id));
            jdbcTemplate.update("DELETE FROM Profile WHERE user_id = ?", UUID.fromString(id));
            jdbcTemplate.update("DELETE FROM \"user\" WHERE email = ?", email);
        } catch (Exception e) {
            System.err.println("Error deleting user: " + e.getMessage());
        }
    }
}