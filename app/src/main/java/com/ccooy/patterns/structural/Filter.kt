package com.ccooy.patterns.structural

/**
 * 过滤器模式
 */
class Filter {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val persons = arrayListOf(
                Person("Robert", "Male", "Single"),
                Person("John", "Male", "Married"),
                Person("Laura", "Female", "Married"),
                Person("Diana", "Female", "Single"),
                Person("Mike", "Male", "Single"),
                Person("Bobby", "Male", "Single")
            )
            val male = CriterialMale()
            val female = CriteriaFemale()
            val single = CriteriaSingle()
            val singleMale = AndCriteria(single, male)
            val singleOrFemale = OrCriteria(single, female)

            println("Males: ")
            printPersons(male.meetCriteria(persons))

            println("\nFemales: ")
            printPersons(female.meetCriteria(persons))

            println("\nSingle Males: ")
            printPersons(singleMale.meetCriteria(persons))

            println("\nSingle Or Females: ")
            printPersons(singleOrFemale.meetCriteria(persons))
        }

        private fun printPersons(persons: List<Person>) {
            for (person in persons) {
                println("Person : [Name: ${person.name}, Gender: ${person.gender}, Marital Status: ${person.maritalStatus} ]")
            }
        }
    }
}

data class Person(var name: String, var gender: String, var maritalStatus: String)

interface Criteria {
    fun meetCriteria(persons: List<Person>): List<Person>
}

class CriterialMale : Criteria {
    override fun meetCriteria(persons: List<Person>): List<Person> {
        val malePersons = ArrayList<Person>()
        for (person in persons) {
            if (person.gender.equals("MALE", true)) {
                malePersons.add(person)
            }
        }
        return malePersons
    }
}

class CriteriaFemale : Criteria {
    override fun meetCriteria(persons: List<Person>): List<Person> {
        val femalePersons = ArrayList<Person>()
        for (person in persons) {
            if (person.gender.equals("FEMALE", true)) {
                femalePersons.add(person)
            }
        }
        return femalePersons
    }
}

class CriteriaSingle : Criteria {
    override fun meetCriteria(persons: List<Person>): List<Person> {
        val singlePersons = ArrayList<Person>()
        for (person in persons) {
            if (person.maritalStatus.equals("SINGLE", true)) {
                singlePersons.add(person)
            }
        }
        return singlePersons
    }
}

class AndCriteria(var criteria: Criteria, var otherCriteria: Criteria) : Criteria {
    override fun meetCriteria(persons: List<Person>): List<Person> {
        val first = criteria.meetCriteria(persons)
        return otherCriteria.meetCriteria(first)
    }
}

class OrCriteria(var criteria: Criteria, var otherCriteria: Criteria) : Criteria {
    override fun meetCriteria(persons: List<Person>): List<Person> {
        val first: ArrayList<Person> = criteria.meetCriteria(persons) as ArrayList<Person>
        val second: ArrayList<Person> = otherCriteria.meetCriteria(persons) as ArrayList<Person>

        for (person in second) {
            if (!first.contains(person)) {
                first.add(person)
            }
        }
        return first
    }
}