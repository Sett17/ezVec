package de.okedikka.ezVec

/**
 * Creates a new [Vector] with the size 3
 *
 * @constructor
 *
 * @param [x][Double]
 * @param [y][Double]
 * @param [z][Double]
 */
class Vec3(x: Double, y: Double, z: Double) : Vector(x, y, z) {
  companion object {
    /**
     * Creates a new Vector with the size 3, filled with 0.0
     */
    val ZERO = Vec3(0.0, 0.0, 0.0)

    /**
     * Creates a new Vector with the size 3, filled with 1.0
     */
    val ONE = Vec3(1.0, 1.0, 1.0)

    /**
     * Creates a new Vector with the size 3, by copying components from [v]
     *
     * @param [v][Vec3]
     * @return [Vec3]
     */
    fun copy(v: Vec3): Vec3 {
      return Vec3(v.x, v.y, v.z)
    }
  }

  /**
   * Creates a new [Vector] with the size 3
   *
   * @constructor
   *
   * @param [x][Int]
   * @param [y][Int]
   * @param [z][Int]
   */
  constructor(x: Int, y: Int, z: Int) : this(x.toDouble(), y.toDouble(), z.toDouble())

  var x: Double
    /**
     * Gets first component of the vector
     * @return [Double]
     */
    get() = this[0]
    /**
     * Sets first component of the vector
     */
    set(value) {
      this[0] = value
    }

  var y: Double
    /**
     * Gets second component of the vector
     * @return [Double]
     */
    get() = this[1]
    /**
     * Sets second component of the vector
     */
    set(value) {
      this[1] = value
    }
  var z: Double
    /**
     * Gets third component of the vector
     * @return [Double]
     */
    get() = this[2]
    /**
     * Sets third component of the vector
     */
    set(value) {
      this[2] = value
    }

  /**
   * Calculates the cross product of this and [other]
   *
   * @param [other][Vec3]
   * @return [Vec3]
   */
  infix fun cross(other: Vec3): Vec3 {
    require(size == other.size) { "Vectors must have the same size" }
    return Vec3(
      intern[1] * other[2] - intern[2] * other[1],
      intern[2] * other[0] - intern[0] * other[2],
      intern[0] * other[1] - intern[1] * other[0]
    )
  }
}