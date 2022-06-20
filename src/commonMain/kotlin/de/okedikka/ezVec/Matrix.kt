package de.okedikka.ezVec

/**
 * Matrix Builder
 *
 * @constructor Create empty [Matrix builder]
 */
class MatrixBuilder {
  val intern = arrayListOf(arrayListOf<Double>())

  init {
    intern.clear()
  }

  /**
   * Add row to matrix.
   *
   * Shorter rows will be padded with zeros.
   *
   * @param [elements][Double]
   */
  fun row(vararg elements: Double) {
    intern.add(ArrayList(elements.toList()))
  }

  /**
   * Adds row to matrix.
   *
   * Shorter rows will be padded with zeros.
   *
   * @param [elements][Int]
   */
  fun row(vararg elements: Int) {
    intern.add(ArrayList(elements.toList().map { it.toDouble() }))
  }
}

/**
 * Matrix
 *
 * @property [width][Int]
 * @property [height][Int]
 * @constructor Create empty [Matrix]
 */
open class Matrix(val width: Int, val height: Int) {
  companion object {
    /**
     * Builds new matrix from [MatrixBuilder].
     *
     * Height and width are implicitly set by the supplied rows.
     *
     * if rows are not all equal length, the shorter rows are padded with zeros.
     *
     * @param [block][MatrixBuilder]
     * @return [Matrix]
     */
    operator fun invoke(block: MatrixBuilder.() -> Unit): Matrix {
      val dslMatrix = MatrixBuilder().apply { block() }.intern
      val height = dslMatrix.size
      val width = dslMatrix.maxBy { it.size }.size
      val m = Matrix(width, height)
      for (row in 0 until height) {
        for (col in 0 until width) {
          m[row, col] = dslMatrix.get(row).getOrElse(col) { 0.0 }
        }
      }
      return m
    }

    /**
     * Creates a new identity matrix of size [size].
     *
     * Identity matrices are square matrices with ones on the diagonal.
     *
     * @param [size][Int]
     * @return [Matrix]
     */
    fun identity(size: Int): Matrix {
      val m = Matrix(size, size)
      for (i in 0 until size) {
        m[i, i] = 1.0
      }
      return m
    }

    /**
     * Creates new matrix by copying field from [other].
     *
     * @param [other][Matrix]
     * @return [Matrix]
     */
    fun copy(other: Matrix): Matrix {
      val m = Matrix(other.width, other.height)
      for (row in 0 until other.height) {
        for (col in 0 until other.width) {
          m[row, col] = other[row, col]
        }
      }
      return m
    }
  }

  internal val intern: Array<Array<Double>> = Array(height) { Array(width) { 0.0 } }

  /**
   * Checks if sizes of matrices are equal, then checks each element for equality.
   *
   * @param [other][Any]?
   * @return [Boolean]
   */
  override fun equals(other: Any?): Boolean {
    if (other !is Matrix?) return false
    if (other?.width != width || other?.height != height) return false
    for (row in 0 until height) {
      for (col in 0 until width) {
        if (other?.intern?.get(row)?.get(col) != intern[row][col]) return false
      }
    }
    return true
  }

  override fun hashCode(): Int {
    return intern.hashCode()
  }

  override fun toString(): String {
    return buildString {
      for (row in 0 until height) {
        for (col in 0 until width) {
          append(intern[row][col].toString())
          if (col < width - 1) append(" ")
        }
        if (row < height - 1) append("\n")
      }
    }
  }

  /**
   * Set element at specified position to [value].
   *
   * @param [row][Int]
   * @param [col][Int]
   * @param [value][Double]
   */
  operator fun set(row: Int, col: Int, value: Double) {
    intern[row][col] = value
  }

  /**
   * Set element at specified position to [value].
   *
   * @param [row][Int]
   * @param [col][Int]
   * @param [value][Double]
   */
  operator fun set(row: Int, col: Int, value: Int) {
    intern[row][col] = value.toDouble()
  }

  /**
   * Set row at specified position to [value].
   *
   * @param [row][Int]
   * @param [value][Array]
   */
  operator fun set(row: Int, value: Array<Double>) {
    intern[row] = value
  }

  /**
   * Sets all fields to [value].
   *
   * @param [value][Double]
   */
  fun fill(value: Double) {
    for (row in 0 until width) {
      for (col in 0 until height) {
        intern[row][col] = value
      }
    }
  }

  /**
   * Fills the whole matrix with [value].
   *
   * @param [value][Int]
   */
  fun fill(value: Int) {
    for (row in 0 until width) {
      for (col in 0 until height) {
        intern[row][col] = value.toDouble()
      }
    }
  }

  /**
   * Returns element at specified position.
   *
   * @param [row][Int]
   * @param [col][Int]
   * @return [Double]
   */
  operator fun get(row: Int, col: Int): Double {
    return intern[row][col]
  }

  /**
   * Returns row at specified position.
   *
   * @param [row][Int]
   * @return [Array]
   */
  operator fun get(row: Int): Array<Double> {
    return intern[row]
  }

  /**
   * Adds 2 matrices together.
   *
   * Requires that both matrices are the same size.
   *
   * @param [row][Int]
   * @param [col][Int]
   * @return [Int]
   */
  operator fun plus(other: Matrix): Matrix {
    require(width == other.width && height == other.height) { "Matrix sizes do not match" }
    val result = Matrix(width, height)
    for (row in 0 until height) {
      for (col in 0 until width) {
        result[row, col] = intern[row][col] + other[row, col]
      }
    }
    return result
  }

  /**
   * Adds 2 matrices together and replaces the original matrix with the result.
   *
   * Requires that both matrices are the same size.
   *
   * @param [other][Matrix]
   */
  operator fun plusAssign(other: Matrix) {
    require(width == other.width && height == other.height) { "Matrix sizes do not match" }
    for (row in 0 until height) {
      for (col in 0 until width) {
        intern[row][col] += other[row, col]
      }
    }
  }

  /**
   * Subtracts 2 matrices.
   *
   * Requires that both matrices are the same size.
   *
   * @param [other][Matrix]
   * @return [Matrix]
   */
  operator fun minus(other: Matrix): Matrix {
    require(width == other.width && height == other.height) { "Matrix sizes do not match" }
    val result = Matrix(width, height)
    for (row in 0 until height) {
      for (col in 0 until width) {
        result[row, col] = intern[row][col] - other[row, col]
      }
    }
    return result
  }

  /**
   * Subtracts 2 matrices and replaces the original matrix with the result.
   *
   * Requires that both matrices are the same size.
   *
   * @param [other][Matrix]
   */
  operator fun minusAssign(other: Matrix) {
    require(width == other.width && height == other.height) { "Matrix sizes do not match" }
    for (row in 0 until height) {
      for (col in 0 until width) {
        intern[row][col] -= other[row, col]
      }
    }
  }

  /**
   * Inverts the matrix.
   *
   * @see [Matrix.inverse]
   *
   * @return [Matrix]
   */
  operator fun not(): Matrix {
    return this.inverse
  }

  /**
   * Multiplies 2 matrices together.
   *
   * Requires that the width of the first matrix is the same as the height of the second matrix.
   *
   * @param [other][Matrix]
   * @return [Matrix]
   */
  operator fun times(other: Matrix): Matrix {
    require(width == other.height) { "Matrix sizes do not match for multiplication" }
    val result = Matrix(other.width, height)
    for (row1 in 0 until height) {
      for (col2 in 0 until other.width) {
        var sum = 0.0
        for (col1 in 0 until width) {
          sum += intern[row1][col1] * other[col1, col2]
        }
        result[row1, col2] = sum
      }
    }
    return result
  }

  /**
   * Multiplies a matrix with a vector.
   *
   * Requires that the height of the vector is the same as the height of the matrix.
   *
   * @param [other][Vector]
   * @return [Vector]
   */
  operator fun times(other: Vector): Vector {
    require(height == other.size) { "Vector and Matrix height do not match" }
    return (this * other.asMatrix).asVector
  }

  /**
   * Augments the matrix with another matrix of the same height.
   *
   * Concats the [other] matrix to the right of the original matrix.
   *
   * @param [other][Matrix]
   * @return [Matrix]
   */
  fun augment(other: Matrix): Matrix {
    require(height == other.height) { "Matrix sizes do not match for augmentation" }
    val res = Matrix(width + other.width, height)
    for (row in 0 until height) {
      for (col in 0 until width) {
        res[row, col] = intern[row][col]
      }
      for (col in 0 until other.width) {
        res[row, col + width] = other[row, col]
      }
    }
    return res
  }

  /**
   * Returns the inverse of the matrix.
   *
   * Using the Gauss-Jordan method, the identity matrix is augmented and the reduced row echelon form is calculated.
   * The inverse is the right-side of that result
   *
   * @return [Matrix]
   */
  val inverse: Matrix
    get() {
      require(width == height) { "Matrix is not square" }
      val z = copy(this).augment(identity(width)).reducedRowEchelonForm
      val result = Matrix(width, height)
      for (row in 0 until height) {
        for (col in 0 until width) {
          result[row, col] = z[row, col + width]
        }
      }
      return result
    }

  /**
   * Returns Matrix in reduced row echelon form
   *
   * * row echelon form
   * * each non-zero row has a leading 1
   * * each column containing a leading 1 has a 0 in all other fields
   *
   * [Wikipedia](https://en.wikipedia.org/wiki/Row_echelon_form#Reduced_row_echelon_form)
   * @return [Matrix]
   */
  val reducedRowEchelonForm: Matrix
    get() {
      val res = copy(this)
      var lead = 0

      for (r in 0 until height) {
        if (height <= lead) return res
        var i = r

        while (res[i][lead] == 0.0) {
          i++
          if (height == i) {
            i = r
            lead++
            if (width == lead) return res
          }
        }

        val temp = res[i]
        res[i] = res[r]
        res[r] = temp

        if (res[r][lead] != 1.0) {
          val div = res[r][lead]
          for (j in 0 until width) res[r][j] /= div
        }

        for (k in 0 until height) {
          if (k != r) {
            val mul = res[k][lead]
            for (j in 0 until width) res[k][j] -= res[r][j] * mul
          }
        }
        lead++
      }
      return res
    }

  /**
   * Returns the transposed matrix.
   *
   * @return [Matrix]
   */
  val transposed: Matrix
    get() {
      val result = Matrix(height, width)
      for (row in 0 until height) {
        for (col in 0 until width) {
          result[col, row] = intern[row][col]
        }
      }
      return result
    }

  /**
   * Converts single-width matrix to Vector type.
   *
   * @return [Vector]
   */
  val asVector: Vector
    get() {
      require(width == 1) { "Matrix is not a vector" }
      val res = Vector(height)
      for (row in 0 until height) {
        res[row] = intern[row][0]
      }
      return res
    }
}