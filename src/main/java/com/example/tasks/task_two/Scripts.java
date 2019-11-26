package com.example.tasks.task_two;

import java.sql.*;

public class Scripts {
    private final String conString = "jdbc:h2:~/test ";
    private final String user = "sa";
    private final String password = "";
    private Connection connection;

    public Scripts() {
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection(conString, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Scripts(Connection connection) {
        this.connection = connection;
    }

    public void createTables() throws SQLException {
        PreparedStatement preparedStatement1 = connection.prepareStatement("create schema if not exists profitDB");
        preparedStatement1.execute();

        Statement createStat = connection.createStatement();
        createStat.addBatch("use profitDB");
        createStat.addBatch("create table if not exists marks (id_course int not null, id_student int not null, mark float, " +
                "primary key (id_course,id_student), " +
                "foreign key (id_student) references student(id), " +
                "foreign key (id_course) references course(id));");
        createStat.addBatch("create table if not exists course (id int PRIMARY KEY auto_increment, course_title varchar(45) not null, id_teacher int not null," +
                "foreign key (id_teacher) references teacher(id));");
        createStat.addBatch("create table if not exists `group` (id int primary key auto_increment, name varchar(45) not null);");
        createStat.addBatch("create table if not exists student (id int primary key auto_increment, first_name varchar(45) not null, last_name varchar(45) not null, " +
                "id_group int not null, foreign key (id_group) references `group`(id));");
        createStat.addBatch("create table if not exists teacher (id int primary key auto_increment, first_name varchar(45) not null, last_name varchar(45), email varchar(50) not null);");

        createStat.executeBatch();
    }

    public int selectTeachersWithPopularity() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select teacher.id, teacher.first_name, teacher.last_name, count(marks.id_student) as teacherStudents" +
                " from teacher, course, marks where teacher.id = course.id_teacher and course.id = marks.id_course group by id_teacher having teacherStudents > ?;");
        //В этом запросе было выведено колво Уникальных студентов для каждого преподователя, и отбор был произведен по количеству Уникальных студентов которые посещают их курсы.
        //Для учета дублирующмхся студентов достаточно убрать оператор distinct в параметрах count.

        preparedStatement.setInt(1, 2000);
        ResultSet resultSet = preparedStatement.executeQuery();
        int i = 0;
        while (resultSet.next()) i++;
        return i;
    }

    public int selectStudentsByMark() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select student.* from student, course, marks where student.id = marks.id_student and course.id = marks.id_course " +
                "and course_title = 'Programming' and marks.mark > 4 order by student.first_name;");
        ResultSet resultSet = preparedStatement.executeQuery();
        int i = 0;
        while (resultSet.next()) i++;
        return i;
    }

    public int selectBestStudents() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select student.first_name, student.last_name, student.id_group, avg(marks.mark) as avg_mark from student, course, marks " +
                "where student.id = marks.id_student and course.id = marks.id_course group by student.id having avg_mark = 5;");
        ResultSet resultSet = preparedStatement.executeQuery();
        int i = 0;
        while (resultSet.next()) i++;
        return i;
    }

}
