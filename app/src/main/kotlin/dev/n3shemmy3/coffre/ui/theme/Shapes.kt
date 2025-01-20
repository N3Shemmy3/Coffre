package dev.n3shemmy3.coffre.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Stable
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Matrix
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.graphics.shapes.Morph
import androidx.graphics.shapes.toPath

val Shapes = Shapes(
    extraSmall = RoundedCornerShape(4.0.dp),
    small = RoundedCornerShape(8.0.dp),
    medium = RoundedCornerShape(12.0.dp),
    large = RoundedCornerShape(16.0.dp),
    extraLarge = RoundedCornerShape(20.0.dp)
)

@Stable
val Shape0 = RoundedCornerShape(20.0.dp)
@Stable
val Shape20 = RoundedCornerShape(20.0.dp)

@Stable
val ShapeTop20 = RoundedCornerShape(20.0.dp, 20.0.dp, 0.dp, 0.dp)

@Stable
val ShapeBottom20 = RoundedCornerShape(0.dp, 0.dp, 20.0.dp, 20.0.dp)

@Stable
val Shape24 = RoundedCornerShape(24.0.dp)

@Stable
val Shape32 = RoundedCornerShape(32.0.dp)

@Stable
val ShapeTop32 = RoundedCornerShape(32.0.dp, 32.0.dp, 0.0.dp, 0.0.dp)

@Stable
val ShapeBottom32 = RoundedCornerShape(0.0.dp, 0.0.dp, 32.0.dp, 32.0.dp)
class CustomRotatingMorphShape(
    private val morph: Morph,
    private val percentage: Float,
    private val rotation: Float
) : Shape {
    private val matrix = Matrix()
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        // Below assumes that you haven't changed the default radius of 1f, nor the centerX and centerY of 0f
        // By default this stretches the path to the size of the container, if you don't want stretching, use the same size.width for both x and y.
        matrix.scale(size.width / 2f, size.height / 2f)
        matrix.translate(1f, 1f)
        matrix.rotateZ(rotation)

        val path = morph.toPath(progress = percentage).asComposePath()
        path.transform(matrix)

        return Outline.Generic(path)
    }
}