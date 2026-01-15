package com.example.quizapp.repository;

import com.example.quizapp.model.QuizQuestion;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuizRepository {
    private final JdbcTemplate jdbcTemplate;

    public QuizRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<QuizQuestion> rowMapper = (rs, rowNum) -> {
        QuizQuestion qQ = new QuizQuestion();
        qQ.setId(rs.getInt("id"));
        qQ.setQuestion(rs.getString("question"));
        qQ.setOptionA(rs.getString("option_a"));
        qQ.setOptionB(rs.getString("option_b"));
        qQ.setOptionC(rs.getString("option_c"));
        qQ.setOptionD(rs.getString("option_d"));
        qQ.setAnswer(rs.getString("correct_option"));
        return qQ;
    };

    public List<QuizQuestion> findAll() {
        String sql = "select * from quiz_question";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public QuizQuestion findById(int id) {
        String sql = "select * from quiz_question where id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public void save(QuizQuestion qQ) {
        String sql = """
                INSERT INTO quiz_question
                (question, option_a, option_b, option_c, option_d, correct_option) VALUES (?,?,?,?,?,?)
                """;

        jdbcTemplate.update(sql,
                qQ.getQuestion(),
                qQ.getOptionA(),
                qQ.getOptionB(),
                qQ.getOptionC(),
                qQ.getOptionD(),
                qQ.getAnswer());
    }

}
