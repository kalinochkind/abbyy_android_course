package main

fun main() {
    var id: Int by StorageDelegate("id", 20)
    println(id)
    id = 42
    println(id)
}