package uz.example.less66_volleyrequest_kotlin.model

class Employee {
    var id:Int = 0
    var name:String
    var salary:Int
    var age:Int


    constructor(name: String, salary: Int, age: Int) {
        this.name = name
        this.salary = salary
        this.age = age
    }

    constructor(id: Int, name: String, salary: Int, age: Int) {
        this.id = id
        this.name = name
        this.salary = salary
        this.age = age
    }

}