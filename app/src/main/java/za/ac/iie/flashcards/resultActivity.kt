package za.ac.iie.flashcards

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class resultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)
        //Call "score" from previous screen
        //getIntExtra because we chose "putExtra(name:String!, value: Int!)
        val score = intent.getIntExtra("score", 0)

        val txtResults = findViewById<TextView>(R.id.txtResults)
        val txtReview = findViewById<TextView>(R.id.txtReview)
        val btnExit = findViewById<Button>(R.id.btnExit)
        val btnReview = findViewById<Button>(R.id.btnReview)
        //Output user score "$score"
        txtResults.text = "You scored $score out of 8"


        btnReview.setOnClickListener{
            //feedback for user
            val feedback = if (score>= 4)
                //Great Job for >50% and "Keep practicing" for <50%
                "Great Job!" else "Keep practicing, You got this!"
            txtReview.text = feedback
        }
        btnExit.setOnClickListener{
            //Return to first screen
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}