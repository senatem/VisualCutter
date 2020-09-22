package com.pungo.tools.visualcutter

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

fun main() {
    var image: BufferedImage? = null

    if(!File("processed").exists()) { File("processed").mkdir()}

    File("visuals").listFiles()?.forEach { file ->
        try{image = ImageIO.read(file)} catch(ex: Exception) {}

        if(image!=null) {
            val trimmer = Trimmer()
            val res = trimmer.trimImage(image!!)
            ImageIO.write(
                res,
                "png",
                File("processed/${file.name}")
            )
        }
    }
}