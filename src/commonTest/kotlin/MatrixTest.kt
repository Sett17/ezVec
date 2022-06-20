import de.okedikka.ezVec.Matrix
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

object MatrixTest {
  @Test
  fun builder() {
    assertEquals(
      Matrix(3, 3).apply {
        this[0, 0] = 0
        this[0, 1] = 1
        this[0, 2] = 2
        this[1, 0] = 3
        this[1, 1] = 4
        this[1, 2] = 5
        this[2, 0] = 6
        this[2, 1] = 7
        this[2, 2] = 8
      },
      Matrix {
        row(0.0, 1.0, 2.0)
        row(3.0, 4.0, 5.0)
        row(6.0, 7.0, 8.0)
      }
    )
    assertEquals(
      Matrix {
        row(1.1, 1.2, 1.3, 1.4)
        row(2.1, 2.2, 0.0, 0.0)
        row(3.1, 3.2, 3.3, 0.0)
      },
      Matrix {
        row(1.1, 1.2, 1.3, 1.4)
        row(2.1, 2.2)
        row(3.1, 3.2, 3.3)
      }
    )
  }

  @Test
  fun _equals() {
    assertEquals(
      Matrix(3, 3),
      Matrix(3, 3)
    )
    assertNotEquals(
      Matrix(3, 3),
      Matrix(3, 4)
    )
    assertNotEquals(
      Matrix(3, 3).apply {
        this[0, 0] = 1
        this[0, 1] = 2
        this[0, 2] = 3
        this[1, 0] = 4
        this[1, 1] = 5
        this[1, 2] = 6
        this[2, 0] = 7
        this[2, 1] = 8
        this[2, 2] = 9
      },
      Matrix(3, 3).apply {
        this[0, 0] = 9
        this[0, 1] = 8
        this[0, 2] = 7
        this[1, 0] = 6
        this[1, 1] = 5
        this[1, 2] = 4
        this[2, 0] = 3
        this[2, 1] = 2
        this[2, 2] = 1
      }
    )
  }

  @Test
  fun setAndGet() {
    val m = Matrix(3, 3).apply {
      this[0, 0] = 100.1
      this[0, 1] = 200.2
      this[0, 2] = 300.3
      this[1, 0] = 400.4
      this[1, 1] = 500.5
      this[1, 2] = 600.6
      this[2, 0] = 700.7
      this[2, 1] = 800.8
      this[2, 2] = 900.9
    }
    assertEquals(
      100.1,
      m[0, 0]
    )
    assertEquals(
      200.2,
      m[0, 1]
    )
    assertEquals(
      300.3,
      m[0, 2]
    )
    assertEquals(
      400.4,
      m[1, 0]
    )
    assertEquals(
      500.5,
      m[1, 1]
    )
    assertEquals(
      600.6,
      m[1, 2]
    )
    assertEquals(
      700.7,
      m[2, 0]
    )
    assertEquals(
      800.8,
      m[2, 1]
    )
    assertEquals(
      900.9,
      m[2, 2]
    )
  }

  @Test
  fun fill() {
    val m = Matrix(3, 3).apply {
      fill(69.420)
    }
    for (i in 0 until 3) {
      for (j in 0 until 3) {
        assertEquals(
          69.420,
          m[i, j]
        )
      }
    }
  }

  @Test
  fun plus() {
    val m1 = Matrix(3, 3).apply {
      this[0, 0] = 1.0
      this[0, 1] = 2.0
      this[0, 2] = 3.0
      this[1, 0] = 4.0
      this[1, 1] = 5.0
      this[1, 2] = 6.0
      this[2, 0] = 7.0
      this[2, 1] = 8.0
      this[2, 2] = 9.0
    }
    val m2 = Matrix(3, 3).apply {
      this[0, 0] = 10.0
      this[0, 1] = 20.0
      this[0, 2] = 30.0
      this[1, 0] = 40.0
      this[1, 1] = 50.0
      this[1, 2] = 60.0
      this[2, 0] = 70.0
      this[2, 1] = 80.0
      this[2, 2] = 90.0
    }
    assertEquals(
      Matrix(3, 3).apply {
        this[0, 0] = 11.0
        this[0, 1] = 22.0
        this[0, 2] = 33.0
        this[1, 0] = 44.0
        this[1, 1] = 55.0
        this[1, 2] = 66.0
        this[2, 0] = 77.0
        this[2, 1] = 88.0
        this[2, 2] = 99.0
      },
      m1 + m2
    )
  }

  @Test
  fun minus() {
    assertEquals(
      Matrix {
        row(-9, -18, -27)
        row(-36, -45, -54)
        row(-63, -72, -81)
      },
      Matrix {
        row(1, 2, 3)
        row(4, 5, 6)
        row(7, 8, 9)
      } - Matrix {
        row(10, 20, 30)
        row(40, 50, 60)
        row(70, 80, 90)
      }
    )
  }

  @Test
  fun times() {
    assertEquals(
      Matrix {
        row(22, 28)
        row(49, 64)
        row(76, 100)
      },
      Matrix {
        row(1, 2, 3)
        row(4, 5, 6)
        row(7, 8, 9)
      } * Matrix {
        row(1, 2)
        row(3, 4)
        row(5, 6)
      }
    )
  }

  @Test
  fun identity() {
    assertEquals(
      Matrix {
        row(1)
        row(0, 1)
        row(0, 0, 1)
        row(0, 0, 0, 1)
      },
      Matrix.identity(4)
    )
  }

  @Test
  fun augment() {
    assertEquals(
      Matrix {
        row(1, 2, 3, 10, 20)
        row(4, 5, 6, 30, 40)
        row(7, 8, 9, 50, 60)
      },
      Matrix {
        row(1, 2, 3)
        row(4, 5, 6)
        row(7, 8, 9)
      }.augment(Matrix {
        row(10, 20)
        row(30, 40)
        row(50, 60)
      })
    )
  }

  @Test
  fun toReducedLowerEchelonForm() {
    assertEquals(
      Matrix {
        row(1, 0, -2, -3)
        row(0, 1, 1, 4)
        row(0, 0, 0, 0)
      },
      Matrix {
        row(1, 3, 1, 9)
        row(1, 1, -1, 1)
        row(3, 11, 5, 35)
      }.reducedRowEchelonForm
    )
  }

  @Test
  fun inverse() {
    assertEquals(
      Matrix {
        row(33, -2, -7)
        row(-14, 1, 3)
        row(-4, 0, 1)
      },
      Matrix {
        row(1, 2, 1)
        row(2, 5, -1)
        row(4, 8, 5)
      }.inverse
    )
  }

  @Test
  fun transpose() {
    assertEquals(
      Matrix {
        row(1, 5, 9)
        row(2, 6, 10)
        row(3, 7, 11)
        row(4, 8, 12)
      },
      Matrix {
        row(1, 2, 3, 4)
        row(5, 6, 7, 8)
        row(9, 10, 11, 12)
      }.transposed
    )
  }

}