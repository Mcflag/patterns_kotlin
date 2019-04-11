package com.ccooy.patterns.javaee

class MVC {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val controller = StudentController(retriveStudentFromDatabase(), StudentView())
            controller.updateView()
            controller.setStudentName("John")
            controller.updateView()
        }

        private fun retriveStudentFromDatabase() = Student("10", "Robert")
    }
}

data class Student(var rollNo: String, var name: String)

class StudentView {
    fun printStudentDetails(studentName: String, studentRollNo: String) {
        println("Student: ")
        println("Name: $studentName")
        println("Roll No: $studentRollNo")
    }
}

class StudentController(var model: Student, var view: StudentView) {
    fun setStudentName(name: String) {
        model.name = name
    }

    fun getStudentName() = model.name

    fun setStudentRollNo(rollNo: String) {
        model.rollNo = rollNo
    }

    fun getStudentRollNo() = model.rollNo

    fun updateView() {
        view.printStudentDetails(model.name, model.rollNo)
    }
}