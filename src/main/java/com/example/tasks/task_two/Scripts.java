package com.example.tasks.task_two;

import java.sql.*;

public class Scripts {
    private final String conString = "jdbc:mysql://localhost:3306/";
    private final String user = "skrekoza";
    private final String password = "12345678";
    private Connection connection;

    public Scripts() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(conString,user,password);

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void createTables() throws SQLException {
        PreparedStatement preparedStatement1 = connection.prepareStatement("create schema if not exists profitDB");
        preparedStatement1.execute();

        Statement createStat = connection.createStatement();
        createStat.addBatch("use profitDB");
        createStat.addBatch("create table if not exists teacher (id int primary key auto_increment, first_name varchar(45) not null, last_name varchar(45), email varchar(50) not null)");
        createStat.addBatch("create table if not exists course (id int PRIMARY KEY auto_increment, course_title varchar(45) not null, id_teacher int not null," +
                "foreign key course_teacher_fk (id_teacher) references teacher(id))");
        createStat.addBatch("create table if not exists `group` (id int primary key auto_increment, name varchar(45) not null)");
        createStat.addBatch("create table if not exists student (id int primary key auto_increment, first_name varchar(45) not null, last_name varchar(45) not null, " +
                "id_group int not null, foreign key student_group_fk (id_group) references `group`(id))");
        createStat.addBatch("create table if not exists marks (id_course int not null, id_student int not null, mark float," +
                "primary key (id_course,id_student)," +
                "foreign key mark_student_fk (id_student) references student(id)," +
                "foreign key mark_course_fk (id_course) references course(id))");
        createStat.executeBatch();
    }

    public void selectTeachersWithPopularity() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select teacher.id, teacher.first_name, teacher.last_name, count(distinct marks.id_student) as teacherStudents" +
                " from teacher, course, marks where teacher.id = course.id_teacher and course.id = marks.id_course group by id_teacher having teacherStudents > ?;");
        //В этом запросе было выведено колво Уникальных студентов для каждого преподователя, и отбор был произведен по количеству Уникальных студентов которые посещают их курсы.
        //Для учета дублирующмхся студентов достаточно убрать оператор distinct в параметрах count.

        preparedStatement.setInt(1,2000);
        ResultSet resultSet = preparedStatement.executeQuery();
    }

}
