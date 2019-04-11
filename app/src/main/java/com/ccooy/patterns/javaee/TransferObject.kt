package com.ccooy.patterns.javaee

class TransferObjectTest {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val studentBusinessObject = StudentBO()

            for (student in studentBusinessObject.getAllStudents()) {
                println("Student: [RollNo : ${student.rollNo}, Name : ${student.name} ]")
            }

            val student = studentBusinessObject.getAllStudents()[0]
            student.name = "Michael"
            studentBusinessObject.updateStudent(student)

            studentBusinessObject.getStudent(0)
            println("Student: [RollNo : ${student.rollNo}, Name : ${student.name} ]")
        }
    }
}

data class StudentVO(var name: String, var rollNo: Int)

class StudentBO {
    private var students: ArrayList<StudentVO> = ArrayList()

    init {
        val student1 = StudentVO("Robert", 0)
        val student2 = StudentVO("John", 1)
        students.add(student1)
        students.add(student2)
    }

    fun deleteStudent(student: StudentVO) {
        students.removeAt(student.rollNo)
        println("Student: Roll No ${student.rollNo}, deleted from database")
    }

    fun getAllStudents() = students

    fun getStudent(rollNo: Int) = students[rollNo]

    fun updateStudent(student: StudentVO) {
        students[student.rollNo].name = student.name
        println("Student: Roll No ${student.rollNo}, updated in the database")
    }
}