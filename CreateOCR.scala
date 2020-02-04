val base = file("/data/texts/Papers")
val ocrDir = file("/data/texts/ocr")
ocrDir.mkdir()
val all = base.children(_.isDirectory).flatMap(_.children(_.extL == "pdf"))

def createOCR(): Unit =
  new Thread {
    override def run(): Unit = {
      var last = "?"
      all.sorted(File.NameOrdering).foreach { f =>
        val ocrFile = ocrDir / f.replaceExt(".txt").name
        if (!ocrFile.exists()) {
          import scala.sys.process._
          import scala.util.Try
          val h = f.name.substring(0, 1).toLowerCase
          if (h != last) {
            println(s"---- LETTER: $h ----")
            last = h
          }
          val contents: String = Try(Seq("pdftotext", "-q", f.path, "-").!!).getOrElse("")
          if (contents.nonEmpty) {
            val arr = contents.getBytes("UTF-8")
            require(!ocrFile.exists())
            val fOut = new java.io.FileOutputStream(ocrFile)
            scala.util.Try {
              fOut.write(arr)
            }
            fOut.close()
          }
        }
      }
    }
      
    start()
  }

createOCR()

