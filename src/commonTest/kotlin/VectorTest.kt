import de.okedikka.ezVec.Vec2
import de.okedikka.ezVec.Vec3
import de.okedikka.ezVec.Vector
import de.okedikka.ezVec.toRadians
import kotlin.test.Test
import kotlin.test.assertEquals

object VectorTest {
  @Test
  fun _toString() {
    assertEquals(
      "(0.0, 0.0)",
      "${Vector(0, 0)}"
    )
    println("VECTOR toString works")
  }

  @Test
  fun _equals() {
    val v = Vector(1, 2, 3, 4, 5)
    for (i in 0 until v.size) {
      assertEquals(v[i], v[i])
    }
    println("VECTOR equals works")
  }

  @Test
  fun plus() {
    assertEquals(
      Vector(8, 9, 10),
      Vector(4, 4, 4) + Vector(4, 5, 6)
    )
    println("VECTOR plus works")
  }

  @Test
  fun minus() {
    assertEquals(
      Vector(2, 1, 0),
      Vector(4, 4, 4) - Vector(2, 3, 4)
    )
    println("VECTOR minus works")
  }

  @Test
  fun fill() {
    assertEquals(
      Vector(1, 1, 1, 1, 1),
      Vector(5).apply { fill(1) }
    )
    println("VECTOR fill works")
  }

  @Test
  fun times() {
    assertEquals(
      Vector(4, 8, 12, 16, 20),
      Vector(2, 4, 6, 8, 10) * 2
    )
    println("VECTOR times works")
  }

  @Test
  fun div() {
    assertEquals(
      Vector(2, 2),
      Vector(4, 4) / 2
    )
    println("VECTOR div works")
  }

  @Test
  fun dot() {
    assertEquals(
      64.0,
      Vector(4, 4) dot Vector(8, 8)
    )
    println("VECTOR dot works")
  }

  @Test
  fun length() {
    assertEquals(
      5.0,
      Vector(3, 4).length
    )
    println("VECTOR length works")
  }

  @Test
  fun normalize() {
    assertEquals(
      Vector(.6, .8),
      Vector(3, 4).normalized
    )
    println("VECTOR normalize works")
  }

  @Test
  fun angle() {
    assertEquals(
      90.0.toRadians(),
      Vector(7, 0) angle Vector(0, 60)
    )
    println("VECTOR angle works")
  }

  @Test
  fun conversions() {
    assertEquals(
      Vec2(1, 2),
      Vector(1, 2).asVec2
    )
    assertEquals(
      Vec3(1, 2, 3),
      Vector(1, 2, 3).asVec3
    )
  }
}

object Vec3 {
  @Test
  fun cross() {
    assertEquals(
      Vec3(-1, -4, 3),
      Vec3(1, 2, 3) cross Vec3(1, 5, 7)
    )
    println("VEC3 cross works")
  }

}

@Test
fun dbg() {

}
