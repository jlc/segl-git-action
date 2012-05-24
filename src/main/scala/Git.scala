
import scalala.tensor.dense.DenseMatrix

/**
 * Quick remember about how to multiply Matrix:
 *          http://www.mathsisfun.com/algebra/matrix-multiplying.html
 */
class Git {

  def mult(M1: Array[Array[Double]], M2: Array[Array[Double]]): Array[Array[Double]] = {
  }

  def multWiki(M1: Array[Array[Double]], M2: Array[Array[Double]]): Array[Array[Double]] = {
  }


  def multImper(M1: Array[Array[Double]], M2: Array[Array[Double]]): Array[Array[Double]] = {
  }

  def multScalala(M1: DenseMatrix[Double], M2: DenseMatrix[Double]) = {
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
