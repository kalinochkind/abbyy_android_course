import java.lang.IndexOutOfBoundsException

class MyArrayList<T> {
  private var elements = arrayOfNulls<Any>(8)
  private var length = 0

  private fun extendIfFull() {
    if (elements.size == length) {
      elements = elements.copyOf(length * 2)
    }
  }

  fun add(elem: T) {
    extendIfFull()
    elements[length++] = elem
  }

  fun insert(elem: T, position: Int) {
    if (position < 0 || position > length) {
      throw IndexOutOfBoundsException()
    }
    extendIfFull()
    for (idx in length + 1 downTo position + 1) {
      elements[idx] = elements[idx - 1]
    }
    elements[position] = elem
    length++
  }

  fun delete(position: Int) {
    if (position < 0 || position >= length) {
      throw IndexOutOfBoundsException()
    }
    for (idx in position until length - 1) {
      elements[idx] = elements[idx + 1]
    }
    elements[--length] = null
  }

  operator fun get(position: Int): T {
    if (position < 0 || position >= length) {
      throw IndexOutOfBoundsException()
    }
    return elements[position]!! as T
  }

}
