package com.example.pruebaconcepto

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStreamWriter

class MainActivity : AppCompatActivity() {

    companion object {
        const val FILE_NAME = "myText.html"
        const val FILE_DATA = "<html> <body> <p>This is an example of a simple HTML page with one paragraph.</p> </body></html>"
        const val MIME_TYPE = "text/html"
        const val PROVIDER = ".provider"
        const val TOAST_TEXT = "Success"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        generateContent()
        openContent()
    }

    private fun openContent() {
        val file = File(filesDir, FILE_NAME)
        val target = Intent(Intent.ACTION_VIEW)
        target.setDataAndType(FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + PROVIDER, file), MIME_TYPE)
        target.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        startActivity(target)
    }

    private fun generateContent() {

        val directory = File(this.filesDir.toString())

        if (!directory.exists()) directory.mkdir()

        val newFile = File(directory, FILE_NAME)

        if (!newFile.exists()) {
            try {
                newFile.createNewFile()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        try {
            val fOut = FileOutputStream(newFile)
            val outputWriter = OutputStreamWriter(fOut)
            outputWriter.write(FILE_DATA)
            outputWriter.close()

            //display file saved message
            Toast.makeText(baseContext, TOAST_TEXT, Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}


