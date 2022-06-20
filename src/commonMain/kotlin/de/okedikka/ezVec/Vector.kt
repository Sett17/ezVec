package de.okedikka.ezVec

import kotlin.math.acos
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Creates a Vector of [size] Size filled with 0.0
 *
 * @property [size][Int]
 * @constructor Create empty [Vector]
 */
open class Vector(val size: Int) {
  companion object {
    /**
     * Creates new Vector by copying fields from [other]
     *
     * @param [other][Vector]
     * @return [Vector]
     */
    fun copy(other: Vector): Vector {
      val result = Vector(other.size)
      for (i in 0 until other.size) {
        result[i] = other[i]
      }
      return result
    }
  }

  internal val intern = Array(size) { 0.0 }

  /**
   * Checks if sizes of Vectors are equal, then checks each component for equality.
   *
   * @param [other][Any]?
   * @return [Boolean]
   */
  override fun equals(other: Any?): Boolean {
    if (other !is Vector) return false
    if (size != other.size) return false
    for (i in 0 until size) {
      if (intern[i] != other.intern[i]) return false
    }
    return true
  }

  /**
   * Creates a new Vector filled with supplied components.
   *
   * @param [values][Double]
   * @return [Vector]
   */
  constructor(vararg values: Double) : this(values.size) {
    for (i in 0 until size) {
      intern[i] = values[i]
    }
  }

   /**
   * Creates a new Vector filled with supplied components.
   *
   * @param [values][Int]
   * @return [Vector]
   */
  constructor(vararg values: Int) : this(values.size) {
    for (i in 0 until size) {
      intern[i] = values[i].toDouble()
    }
  }

  override fun hashCode(): Int {
    return intern.hashCode()
  }

  override fun toString(): String {
    return "(${intern.joinToString(", ")})"
  }

  /**
   * Returns the component at index [index]
   *
   * @param [index][Int]
   * @return [Double]
   */
  operator fun get(index: Int): Double {
    return intern[index]
  }

  /**
   * Sets the component at index [index] to [value]
   *
   * @param [index][Int]
   * @param [value][Double]
   */
  operator fun set(index: Int, value: Double) {
    intern[index] = value
  }

  /**
   * Sets the component at index [index] to [value]
   *
   * @param [index][Int]
   * @param [value][Int]
   */
  operator fun set(index: Int, value: Int) {
    intern[index] = value.toDouble()
  }

  /**
   * Sets all components to [value]
   *
   * @param [value][Double]
   */
  fun fill(value: Double) {
    for (i in 0 until size) {
      intern[i] = value
    }
  }

  /**
   * Sets all components to [value]
   *
   * @param [value][Int]
   */
  fun fill(value: Int) {
    for (i in 0 until size) {
      intern[i] = value.toDouble()
    }
  }

  /**
   * Adds 2 Vectors together.
   *
   * Requires that both Vectors have the same size.
   *
   * @param [other][Vector]
   * @return [Vector]
   */
  operator fun plus(other: Vector): Vector {
    require(size == other.size) { "Vectors must have the same size" }
    return Vector(*intern.mapIndexed { i, v -> v + other[i] }.toDoubleArray())
  }

  /**
   * Adds 2 Vectors together and replaces the original Vector with the result.
   *
   * @param [other][Vector]
   */
  operator fun plusAssign(other: Vector) {
    require(size == other.size) { "Vectors must have the same size" }
    for (i in 0 until size) {
      intern[i] += other[i]
    }
  }

  /**
   * Subtracts 2 Vectors.
   *
   * @param [other][Vector]
   * @return [Vector]
   */
  operator fun minus(other: Vector): Vector {
    require(size == other.size) { "Vectors must have the same size" }
    return Vector(*intern.mapIndexed { i, v -> v - other[i] }.toDoubleArray())
  }

  /**
   * Subtracts 2 Vectors and replaces the original Vector with the result.
   *
   * @param [other][Vector]
   */
  operator fun minusAssign(other: Vector) {
    require(size == other.size) { "Vectors must have the same size" }
    for (i in 0 until size) {
      intern[i] -= other[i]
    }
  }

  /**
   * Multiplies a Vector with a scalar.
   *
   * @param [mul][Number]
   * @return [Vector]
   */
  operator fun times(mul: Number): Vector {
    return Vector(*intern.map { it * mul.toDouble() }.toDoubleArray())
  }

  /**
   * Multiplies a Vector with a scalar and replaces the original Vector with the result.
   *
   * @param [mul][Number]
   */
  operator fun timesAssign(mul: Number) {
    for (i in 0 until size) {
      intern[i] *= mul.toDouble()
    }
  }

  /**
   * Divides a Vector by a scalar.
   *
   * @param [div][Number]
   * @return [Vector]
   */
  operator fun div(div: Number): Vector {
    return Vector(*intern.map { it / div.toDouble() }.toDoubleArray())
  }

  /**
   * Divides a Vector by a scalar and replaces the original Vector with the result.
   *
   * @param [div][Number]
   */
  operator fun divAssign(div: Number) {
    for (i in 0 until size) {
      intern[i] /= div.toDouble()
    }
  }

  /**
   * Multiplies the Vector with -1.
   *
   * @return [Vector]
   */
  operator fun unaryMinus(): Vector {
    return copy(this) * -1
  }

  /**
   * Returns the dot product of 2 Vectors.
   *
   * @see [Vector.dot]
   *
   * @param [other][Vector]
   * @return [Double]
   */
  operator fun times(other: Vector): Double {
    require(size == other.size) { "Vectors must have the same size" }
    return this dot other
  }

  /**
   * Returns the dot product of 2 Vectors.
   *
   * @param [other][Vector]
   * @return [Double]
   */
  infix fun dot(other: Vector): Double {
    require(size == other.size) { "Vectors must have the same size" }
    var result = 0.0
    for (i in 0 until size) {
      result += intern[i] * other[i]
    }
    return result
  }

  /**
   * NOT IMPLEMENTED
   *
   * @param [v][Vector]
   * @return [Vector]
   */
  infix fun ncross(v: Vector): Vector {
    require(size == v.size) { "Vectors must have the same size" }
    TODO("cross product generalized to n-dimensions; https://www.quora.com/How-can-one-generalize-the-cross-product-to-n-dimensions")
  }

  /**
   * Returns the length/magnitude of the Vector.
   *
   * @see [Vector.magnitude]
   * @return [Double]
   */
  val length: Double
    get() {
      return sqrt(intern.sumOf { it.pow(2) })
    }

  /**
   * Returns the magnitude/length of the Vector.
   *
   * @see [Vector.length]
   * @return [Double]
   */
  val magnitude: Double
    get() {
      return length
    }

  /**
   * Returns the normalized Vector.
   *
   * @return [Vector]
   */
  val normalized: Vector
    get() {
      return this / length
    }

  /**
   * Returns a list of the components of the Vector.
   *
   * @return [List]
   */
  val components: List<Double>
    get() {
      return intern.asList()
    }

  /**
   * Returns the angle between 2 Vectors.
   *
   * @param [other][Vector]
   * @return [Double]
   */
  infix fun angle(other: Vector): Double {
    require(size == other.size) { "Vectors must have the same size" }
    return acos(this dot other / (length * other.length))
  }

  /**
   * Converts the Vector to a single-width Matrix.
   *
   * @return [Matrix]
   */
  val asMatrix: Matrix
    get() {
      return Matrix {
        for (c in components) {
          row(c)
        }
      }
    }

  /**
   * Converts to Vector to a [Vec2].
   *
   * @return [Vec2]
   */
  val asVec2: Vec2
    get() {
      require(size == 2) { "Vector must have size 2" }
      return Vec2(intern[0], intern[1])
    }

  /**
   * Converts to Vector to a [Vec3].
   *
   * @return [Vec3]
   */
  val asVec3: Vec3
    get() {
      require(size == 3) { "Vector must have size 3" }
      return Vec3(intern[0], intern[1], intern[2])
    }

}