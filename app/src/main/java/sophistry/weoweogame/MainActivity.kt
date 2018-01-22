package sophistry.weoweogame

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import sophistry.weoweogame.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println("Hello Warudo~")
    }
}
