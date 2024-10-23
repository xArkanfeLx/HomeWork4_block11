package com.example.saveinstancestate

import java.io.Serializable

class Person(var name:String,var family:String, var address:String,var phone:String) :Serializable{

    override fun toString(): String {
        return "$name $family"
    }

}