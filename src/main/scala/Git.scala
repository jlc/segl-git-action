
import scalala.tensor.dense.DenseMatrix

/**
 * Quick remember about how to multiply Matrix:
 *          http://www.mathsisfun.com/algebra/matrix-multiplying.html
 */
class Git {

  def mult(M1: Array[Array[Double]], M2: Array[Array[Double]]): Array[Array[Double]] = {
    val res = Array.fill(M1.length, M2(0).length)(0.0)

    for (row <- (0 until M1.length);
         col <- (0 until M2(0).length);
         i <- 0 until M1(0).length) {

      res(row)(col) += M1(row)(i) * M2(i)(col)

    }

    res
  }

  def multWiki(M1: Array[Array[Double]], M2: Array[Array[Double]]): Array[Array[Double]] = {
    for (row <- M1)
    yield for (col <- M2.transpose)
    yield row zip col map Function.tupled(_ * _) reduceLeft (_ + _)
  }


  def multImper(M1: Array[Array[Double]], M2: Array[Array[Double]]): Array[Array[Double]] = {

    val n: Int = M1(0).length
    val m: Int = M1.length
    val p: Int = M2(0).length

    val res = Array.fill(M1.length, M2(0).length)(0.0)

    for (i <- 0 until m) {
      for (j <- 0 until p) {
        for (k <- 0 until n) {
          res(i)(j) += M1(i)(k) * M2(k)(j)
        }
      }
    }
    return res;

  }

  def multScalala(M1: DenseMatrix[Double], M2: DenseMatrix[Double]) = {
    M1 * M2
  }

}


object Git {

  def test {
    val git = new Git
    val random = new java.security.SecureRandom
    val cols = 100
    val rows = 1000
    var M1 = Array.fill(rows, cols) {
      random.nextDouble() * 50
    }
    var M2 = Array.fill(rows, 1) {
      random.nextDouble() * 50
    }



    //    println("M1:\n" + M1.deep.mkString("\n"))
    //    println("M2:\n" + M2.deep.mkString("\n"))

    val (t, m) = time[Array[Array[Double]]] {
      git.mult(M1, M2)
    }

    val (t1, m1) = time[Array[Array[Double]]] {
      git.multImper(M1, M2)
    }

    val (t2, m2) = time[Array[Array[Double]]] {
      git.multWiki(M1, M2)
    }

    M1 = null
    M2 = null


    val M1_Scalala = DenseMatrix.rand(rows, cols)
    val M2_Scalala = DenseMatrix.rand(rows, 1)
    println("M1_Scalalab:\n" + M1_Scalala)

    val (t3, m3) = time[DenseMatrix[Double]] {
      git.multScalala(M1_Scalala, M2_Scalala)
    }
    println("m3:\n" + m3)


    println("Summary time M (" + t + "ms) M.Imper (" + t1 + "ms) M.Wiki (" + t2 + "ms) M.Scalala (" + t3 + "ms)")
  }

  def time[T](f: => T): (Long, T) = {
    val start = System.currentTimeMillis
    val res: T = f
    (System.currentTimeMillis - start, res)
  }
}
