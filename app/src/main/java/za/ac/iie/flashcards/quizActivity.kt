package za.ac.iie.flashcards

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

class quizActivity : AppCompatActivity() {
    //Use array for questions
    private val questions = arrayOf("The great wall of china is visible from space",
    "Namibia was once part of South Africa",
    "World war I began in 1914, when Austria-Hungary declared war on Serbia",
        "The first man on the moon was Donald Trump",
        "United States dropped atomic bombs on Hiroshima & Nagasaki during world war I",
        "Apartheid was a racial intergration enforced by South African government",
        "The Sharpville massacre ocurred in response to anti-passbook (Dompas) protest",
        "Nelson Mandela was released from prison in 1990 and became South Africa's first black president in 1991"
    )
    private fun loadQuestion(){
        //function for buttons
        txtQuestion.text = questions[index]
        txtFeedback.text = ""
        //enable buttons(btnTrue and btnFalse) and disable button(btnNext) so user does not skip to next question before answering the question at hand making all questions compulsory
        btnTrue.isEnabled = true
        btnFalse.isEnabled = true
        btnNext.isEnabled = false
    }
    private fun checkAnswer(userAnswer: Boolean){
        //booleen function to check answer
        val correctAnswer = answers[index]
        if (userAnswer == correctAnswer){
            txtFeedback.text = "Correct!"
            //make sure that points(score) is added if answer is correct
            score++
        }
        else{
            //feedback for user to know what the got wrong
            txtFeedback.text = "Incorrect!"
        }
        //after user has answered disable buttons (btnTrue and btnFalse) so user cannot cheat or try redo the question.
        //Enable button (btnnext) so user can move to next question
        btnTrue.isEnabled = false
        btnFalse.isEnabled = false
        btnNext.isEnabled = true

        //answers for the  questions in the array (Boolean, either true or false)
    }
    private val answers = booleanArrayOf(false, true, true, false, false, false, true, false)
    private var index = 0
    private var score = 0
    private lateinit var txtQuestion: TextView
    private lateinit var txtFeedback: TextView
    private lateinit var btnTrue: Button
    private lateinit var btnFalse: Button
    private lateinit var btnNext: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quiz)
        txtQuestion = findViewById(R.id.txtQuestion)
        txtFeedback = findViewById(R.id.txtFeedback)
        btnTrue = findViewById(R.id.btnTrue)
        btnFalse = findViewById(R.id.btnFalse)
        btnNext = findViewById(R.id.btnNext)
        //Call function loadQuestion()
        loadQuestion()
        //Set buttons to true and false
        btnTrue.setOnClickListener{
            checkAnswer(true)
        }
        btnFalse.setOnClickListener{
            checkAnswer(false)
        }

        btnNext.setOnClickListener{
            index++
            if (index<questions.size){
                //Call function loadQuestion()
                loadQuestion()
            } else {
                //next screen resultActivity
                //Make "score" transferable to thr next screen
                val intent = Intent(this, resultActivity::class.java)
                //choose putExtra (name: String!, value: Int)
                intent.putExtra("score", score)



                startActivity(intent)
                finish()
            }
        }

    }
}