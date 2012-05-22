
object HelloWorld {
  def main(args: Array[String]) {
    println("Hello World... again and again...")

    println("Ex1:")
    val ls1 = List(1,2,3,4)
    println(Ex1.lastBuiltin(ls1) )
    println(Ex1.lastRecursive(ls1))

    println("Ex2:")
    val ls2 = List(5,6,7,8)
    println(Ex2.nthBuiltin(2, ls2))
    println(Ex2.nthRecursive(2, ls2))
  }
}

