package com.example.expense

fun main(){
    val obj = Outer()
    obj.Inner().printing()
}
class Outer(){
    private val shape = "0"
    inner class Inner(){
        fun printing(){
            println(shape)
        }
    }
}
class MultiThreading(): Thread() {

}