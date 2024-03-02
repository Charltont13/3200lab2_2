package com.example.k2024_02_20_quiz_page
import androidx.compose.ui.platform.LocalContext

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.k2024_02_20_quiz_page.controller.NextQuestion
import com.example.k2024_02_20_quiz_page.controller.ScoreController
import com.example.k2024_02_20_quiz_page.model.AllQuestions
import com.example.k2024_02_20_quiz_page.ui.theme.K2024_02_20_quiz_pageTheme

class MainActivity : ComponentActivity() {

    private lateinit var nextQuestion: NextQuestion
    private lateinit var scoreController: ScoreController
    private val allQuestions = AllQuestions()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        nextQuestion = NextQuestion(allQuestions.getNumberOfQuestions())
        scoreController = ScoreController("PlayerName")

        setContent {
            K2024_02_20_quiz_pageTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    QuizPage("Ultra Quiz!", allQuestions, nextQuestion, scoreController)
                }
            }
        }
    }
}
@Composable
fun QuizPage(
    name: String,
    allQuestions: AllQuestions,
    nextQuestion: NextQuestion,
    scoreController: ScoreController,
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current
    val questionNumber = remember { mutableStateOf(nextQuestion.getQuestionNumber()) }
    val currentQuestion = remember { mutableStateOf(allQuestions.getQuestion(questionNumber.value)) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = "This is the $name!",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold // This makes the text bold
            )
        )

        Card(modifier = Modifier
            .fillMaxWidth(0.75F)
            .fillMaxHeight(0.35F)) {
            Text(currentQuestion.value.questionText, modifier = Modifier.padding(20.dp))
        }
        Row()
        {
            Column {
                Button(onClick = {
                    if (currentQuestion.value.answer == true) {
                        scoreController.incrementScore(currentQuestion.value.difficulty)
                    }
                    questionNumber.value = nextQuestion.getNextIncQuestionNumber()
                    currentQuestion.value = allQuestions.getQuestion(questionNumber.value)
                }) {
                    Text("True")
                }
                Spacer(modifier = Modifier.size(36.dp))
                Button(onClick = { questionNumber.value++ % allQuestions.getNumberOfQuestions()} ) {
                    Text("Next")
                }
            }
            Spacer(modifier = Modifier.size(20.dp))
            Column {
                Button(onClick = {
                    if (!currentQuestion.value.answer) {
                        scoreController.incrementScore(currentQuestion.value.difficulty)
                    }
                    questionNumber.value = nextQuestion.getNextIncQuestionNumber()
                    currentQuestion.value = allQuestions.getQuestion(questionNumber.value)
                }) {
                    Text("False")
                }
                Spacer(modifier = Modifier.size(36.dp))
                Button(onClick = {
                    // Show a toast message for simplicity
                    Toast.makeText(context, "Quiz Done! Your score: ${scoreController.getScore()}", Toast.LENGTH_LONG).show()
                    // Here you could navigate back or to a new screen showing the score or exit the quiz
                }) {
                    Text("Done")
                }
            }
        }
    }
    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        Text(
            text = "This is the $name!",
            modifier = modifier
                .fillMaxWidth(), // This makes the Text composable fill the available width
             // This centers the text horizontally within the Text composable
        )
    }


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    K2024_02_20_quiz_pageTheme {
        Greeting("Android")
    }
}}