package BCSD_Interview.enrollment.repository;

import BCSD_Interview.enrollment.DBConnection.DBConnectionManager;
import BCSD_Interview.enrollment.domain.Subject;
import BCSD_Interview.enrollment.domain.SubjectList;
import BCSD_Interview.enrollment.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class SubjectListRepository {
    public List<SubjectList> getAllList() throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "select * from subject_List";

        try {
            connection = DBConnectionManager.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            List<SubjectList> subjectList = new ArrayList<>();

            while (resultSet.next()) {
                subjectList.add(new SubjectList(resultSet.getLong("과목 ID")
                        , resultSet.getString("비고")
                        , resultSet.getString("학년도-학기")
                        , resultSet.getString("과목코드")
                        , resultSet.getString("교과목명")
                        , resultSet.getInt("분반")
                        , resultSet.getString("교과목코드구분")
                        , resultSet.getString("대표이수구분")
                        , resultSet.getInt("학점")
                        , resultSet.getInt("강의")
                        , resultSet.getString("등록학부(과)")
                        , resultSet.getString("개설학부(과)")
                        , resultSet.getInt("학년")
                        , resultSet.getString("담당교수")
                        , resultSet.getInt("수강정원")
                        , resultSet.getString("강의시간")
                        , resultSet.getString("수강신청 가능학년")
                        , resultSet.getString("성적평가")));
            }
            return subjectList;
        } catch (SQLException e) {
            log.error("selectSubjectList error={}", e);
            throw e;
        } finally {
            closeResource(connection, statement, resultSet);
        }
    }

    public List<SubjectList> getFilteredList(User signedInuser) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = new StringBuilder().append("SELECT * FROM subject_list ")
                .append("WHERE NOT EXISTS (SELECT 1 FROM subject_record ")
                .append("WHERE subject_list.subject_name = subject_record.subject_name ")
                .append("AND subject_record.mark IN ('A+', 'A0', 'B+', 'B0')) ")
                .append("AND subject_list.grade > ? ")
                .append("AND (subject_list.established_undergraduate_studies NOT IN ('HRD학부', '교양학부', '융합학부') ")
                .append("OR subject_list.established_undergraduate_studies = ? ")
                .append("AND ?::text[] && string_to_array(subject_list.registrable_grade, ',')")
                .toString();

        try {
            connection = DBConnectionManager.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, signedInuser.getGrade());
            statement.setString(2, signedInuser.getDepartment());

            Array registrableGradeArray = connection.createArrayOf("text", signedInuser.getRegistrable_grade().toArray());
            statement.setArray(3, registrableGradeArray);

            resultSet = statement.executeQuery();
            List<SubjectList> subjectList = new ArrayList<>();

            while (resultSet.next()) {
                subjectList.add(new SubjectList(resultSet.getLong("subject_id")
                        , resultSet.getString("비고")
                        , resultSet.getString("학년도-학기")
                        , resultSet.getString("과목코드")
                        , resultSet.getString("교과목명")
                        , resultSet.getInt("분반")
                        , resultSet.getString("교과목코드구분")
                        , resultSet.getString("대표이수구분")
                        , resultSet.getInt("학점")
                        , resultSet.getInt("강의")
                        , resultSet.getString("등록학부(과)")
                        , resultSet.getString("개설학부(과)")
                        , resultSet.getInt("학년")
                        , resultSet.getString("담당교수")
                        , resultSet.getInt("수강정원")
                        , resultSet.getString("강의시간")
                        , resultSet.getString("수강신청 가능학년")
                        , resultSet.getString("성적평가")));
            }
            return subjectList;
        } catch (SQLException e) {
            log.error("getFilteredSubjectList error={}", e);
            throw e;
        } finally {
            closeResource(connection, statement, resultSet);
        }
    }

    private void closeResource(Connection connection, PreparedStatement statement, ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (Exception e) {
                log.error("error", e);
            }
        }

        if (statement != null) {
            try {
                statement.close();
            } catch (Exception e) {
                log.error("error", e);
            }
        }

        if (connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
                log.error("error", e);
            }
        }
    }
}
