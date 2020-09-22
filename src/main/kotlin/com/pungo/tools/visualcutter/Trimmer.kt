package com.pungo.tools.visualcutter

import java.awt.image.BufferedImage


class Trimmer {
    fun trimImage(image: BufferedImage): BufferedImage? {
        val raster = image.alphaRaster
        val width = raster.width
        val height = raster.height
        var left = 0
        var top = 0
        var right = width - 1
        var bottom = height - 1
        var minRight = width - 1
        var minBottom = height - 1
        top@ while (top < bottom) {
            for (x in 0 until width) {
                if (raster.getSample(x, top, 0) != 0) {
                    minRight = x
                    minBottom = top
                    break@top
                }
            }
            top++
        }
        left@ while (left < minRight) {
            for (y in height - 1 downTo top + 1) {
                if (raster.getSample(left, y, 0) != 0) {
                    minBottom = y
                    break@left
                }
            }
            left++
        }
        bottom@ while (bottom > minBottom) {
            for (x in width - 1 downTo left) {
                if (raster.getSample(x, bottom, 0) != 0) {
                    minRight = x
                    break@bottom
                }
            }
            bottom--
        }
        right@ while (right > minRight) {
            for (y in bottom downTo top) {
                if (raster.getSample(right, y, 0) != 0) {
                    break@right
                }
            }
            right--
        }
        return image.getSubimage(left, top, right - left + 1, bottom - top + 1)
    }
}