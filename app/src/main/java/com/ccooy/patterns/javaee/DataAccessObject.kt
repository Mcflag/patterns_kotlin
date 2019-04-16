package com.ccooy.patterns.javaee

/**
 * 数据访问对象模式
 */
class DataAccessObject {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val studentDao = StudentDaoImpl()
            for (student in studentDao.getAllStudents()) {
                println("Student: [RollNo : ${student.rollNo}, Name : ${student.name} ]")
            }
            val student = studentDao.getAllStudents()[0]
            student.name = "Michael"
            studentDao.updateStudent(student)

            studentDao.getStudent(0)
            println("Student: [RollNo : ${student.rollNo}, Name : ${student.name} ]")
        }
    }
}

data class StudentA(var name: String, var rollNo: Int)

interface StudentDao {
    fun getAllStudents(): List<StudentA>
    fun getStudent(rollNo: Int): StudentA
    fun updateStudent(student: StudentA)
    fun deleteStudent(student: StudentA)
}

class StudentDaoImpl : StudentDao {
    private var students: ArrayList<StudentA> = arrayListOf()

    init {
        students.add(StudentA("Robert", 0))
        students.add(StudentA("John", 1))
    }

    override fun deleteStudent(student: StudentA) {
        students.removeAt(student.rollNo)
        println("Student: Roll No ${student.rollNo}, deleted from database")
    }

    override fun getAllStudents(): List<StudentA> = students

    override fun getStudent(rollNo: Int): StudentA = students[rollNo]

    override fun updateStudent(student: StudentA) {
        students[student.rollNo].name = student.name
        println("Student: Roll No ${student.rollNo}, updated in the database")
    }
}