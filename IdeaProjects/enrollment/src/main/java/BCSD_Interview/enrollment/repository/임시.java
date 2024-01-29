package BCSD_Interview.enrollment.repository;

public class 임시 {
    package board.th_task.dto;

    public class FilterDTO {
        private String userDepartment;
        private int userGrade;
        private String userRegistrableGrade;

        // Constructors, getters, and setters

        public FilterDTO(String userDepartment, int userGrade, String userRegistrableGrade) {
            this.userDepartment = userDepartment;
            this.userGrade = userGrade;
            this.userRegistrableGrade = userRegistrableGrade;
        }

        public String getUserDepartment() {
            return userDepartment;
        }

        public void setUserDepartment(String userDepartment) {
            this.userDepartment = userDepartment;
        }

        public int getUserGrade() {
            return userGrade;
        }

        public void setUserGrade(int userGrade) {
            this.userGrade = userGrade;
        }

        public String getUserRegistrableGrade() {
            return userRegistrableGrade;
        }

        public void setUserRegistrableGrade(String userRegistrableGrade) {
            this.userRegistrableGrade = userRegistrableGrade;
        }
    }
}

package board.th_task.repository;

        import board.th_task.DBConnection.DBConnectionManager;
        import board.th_task.dto.FilterDTO;
        import lombok.extern.slf4j.Slf4j;
        import org.springframework.stereotype.Repository;

        import java.sql.Connection;
        import java.sql.PreparedStatement;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.util.ArrayList;
        import java.util.List;

@Slf4j
@Repository
public class FilterRepository {
    public List<String> filterSubjects(FilterDTO filterDTO) {
        List<String> filteredSubjects = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            connection = DBConnectionManager.getConnection();
            // Implement the SQL query based on the filtering criteria using the filterDTO
            // Example: "SELECT subject_name FROM subject_list WHERE ... "

            // Execute the query and populate the filteredSubjects list

        } catch (SQLException e) {
            log.error("Error filtering subjects", e);
            throw new RuntimeException("Error filtering subjects", e);
        } finally {
            closeResource(connection, statement, rs);
        }

        return filteredSubjects;
    }

    // Utility method to close resources
    private void closeResource(Connection connection, PreparedStatement statement, ResultSet resultSet) {
        // Implement resource closing logic
    }
}
