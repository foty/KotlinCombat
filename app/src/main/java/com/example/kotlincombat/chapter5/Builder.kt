package com.example.kotlincombat.chapter5

/**
 * Create by lxx
 * Date : 2020/6/18 17:12
 * Use by
 */
class Builder {

    var person: Person = Person()


    fun setAge(ages:Int){
        person.age = ages
    }

    fun setList(list:List<String>){
        person.friends = list
    }

    fun setName(name:String){
        person.name = name
    }

}