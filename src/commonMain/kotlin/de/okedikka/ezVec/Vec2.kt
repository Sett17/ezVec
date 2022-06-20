package de.okedikka.ezVec

/**
 * Creates a new [Vector] with the size 2
 *
 * @constructor
 *
 * @param [x][Double]
 * @param [y][Double]
 */
class Vec2(x: Double, y: Double) : Vector(x, y) {
  companion object {
    /**
     * Creates a new Vector with the size 2, filled with 0.0
     */
    val ZERO = Vec2(0.0, 0.0)

    /**
     * Creates a new Vector with the size 2, filled with 1.0
     */
    val ONE = Vec2(1.0, 1.0)

    /**
     * Creates a new Vector with the size 2, by copying components from [v]
     *
     * @param [v][Vec2]
     * @return [Vec2]
     */
    fun copy(v: Vec2): Vec2 {
      return Vec2(v.x, v.y)
    }
  }

  /**
   * Creates a new [Vector] with the size 2
   *
   * @constructor
   *
   * @param [x][Int]
   * @param [y][Int]
   */
  constructor(x: Int, y: Int) : this(x.toDouble(), y.toDouble())

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

  /**
   * Converts [Vec2] to [Vec3] by adding 0.0 as third component.
   *
   * @return [Vec3]
   */
  val vec3: Vec3
    get() = Vec3(x, y, 0.0)
}