fun main() {
    val a = MyArrayList<Int>();

    a.add(0)
    a.add(1)
    a.add(2)
    a.add(3)
    a.add(4)
    a.add(6)
    a.add(7)
    a.add(8)
    a.add(9)
    a.add(10)
    a.add(11)

    a.insert(5, 5)
    a.insert(12, 12)
    a.delete(12)
    a.delete(5)

    println(a[3])
}
