package com.msla_mac.pig

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

const val MAX_SCORE = 10
const val RESET_SCORE_ON_ONES = true
var currentGame = true
var btnClick = 0


class MainActivity : AppCompatActivity() {
    lateinit var btnRoll: Button
    lateinit var btnHold: Button
    lateinit var p1GamesWon: TextView
    lateinit var compGamesWon: TextView
    lateinit var p1TotalScore: TextView
    lateinit var compTotalScore: TextView
    lateinit var p1TurnTotal: TextView
    lateinit var compTurnTotal: TextView
    lateinit var diceTotal: TextView
    lateinit var p1Dice: ImageView
    lateinit var compDice: ImageView
    lateinit var youLoose: ImageView
    lateinit var youWon: ImageView

    val num1Dice = Dice(6).roll()
    val num2Dice = Dice(6).roll()

    var pGamesWon = 0
    var pTotalScore = 0
    var pTurnTotal = 0
    var cGamesWon = 0
    var cTotalScore = 0
    var cTurnTotal = 0
    var pRoundTotal = 0
    var cRoundTotal = 0

    class Dice(var numSides: Int) {

        fun roll(): Int {
            if (RESET_SCORE_ON_ONES) {
                return (1..numSides).random()
            } else {
                return (2..numSides).random()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnRoll = findViewById(R.id.btnRoll)
        btnHold = findViewById(R.id.btnHold)
        p1GamesWon = findViewById(R.id.p1GamesWon)
        compGamesWon = findViewById(R.id.compGamesWon)
        p1TotalScore = findViewById(R.id.p1TotalScore)
        compTotalScore = findViewById(R.id.compTotalScore)
        p1TurnTotal = findViewById(R.id.p1TurnTotal)
        compTurnTotal = findViewById(R.id.compTurnTotal)
        diceTotal = findViewById(R.id.diceTotal)
        p1Dice = findViewById(R.id.p1Dice)
        compDice = findViewById(R.id.compDice)
        youWon = findViewById(R.id.youWon)
        youLoose = findViewById(R.id.youLoose)

    }

//    var playerTotalScore: Int = p1TotalScore.text.toString().toInt()
//    var computerTotalScore: Int = compTotalScore.text.toString().toInt()
//    var playerGamesWon: Int = p1GamesWon.text.toString().toInt()
//    var computerGamesWon: Int = compGamesWon.text.toString().toInt()
//    var playerTurnTotal: Int = p1TurnTotal.text.toString().toInt()
//    var computerTurnTotal: Int = compTurnTotal.text.toString().toInt()
//    var roundTotal: Int = diceTotal.text.toString().toInt()


    fun diceImage(num1Dice: Int, num2Dice: Int) {

        when (num1Dice) {
            1 -> {
                p1Dice.setImageResource(R.drawable.dice_1)
            }
            2 -> {
                p1Dice.setImageResource(R.drawable.dice_2)
            }
            3 -> {
                p1Dice.setImageResource(R.drawable.dice_3)
            }
            4 -> {
                p1Dice.setImageResource(R.drawable.dice_4)
            }
            5 -> {
                p1Dice.setImageResource(R.drawable.dice_5)
            }
            6 -> {
                p1Dice.setImageResource(R.drawable.dice_6)
            }
        }

        when (num2Dice) {
            1 -> {
                compDice.setImageResource(R.drawable.dice_1)
            }
            2 -> {
                compDice.setImageResource(R.drawable.dice_2)
            }
            3 -> {
                compDice.setImageResource(R.drawable.dice_3)
            }
            4 -> {
                compDice.setImageResource(R.drawable.dice_4)
            }
            5 -> {
                compDice.setImageResource(R.drawable.dice_5)
            }
            6 -> {
                compDice.setImageResource(R.drawable.dice_6)
            }
        }

        Log.i("CallBack", "Inside diceImage()")
    }

    fun endGame(v: View) {

        p1Dice.setImageResource(R.drawable.dice_1)
        compDice.setImageResource(R.drawable.dice_1)
        youLoose.visibility = View.INVISIBLE
        youWon.visibility = View.INVISIBLE
        pTotalScore = 0
        p1TotalScore.text = "0"
        cTotalScore = 0
        compTotalScore.text = "0"
        pTurnTotal = 0
        p1TurnTotal.text = "0"
        cTurnTotal = 0
        compTurnTotal.text = "0"
        diceTotal.text = "0"
        cRoundTotal = 0
        pRoundTotal = 0

    }

    fun onRollClick(v: View) {

        cTotalScore += cTurnTotal
        compTotalScore.text = "$cTotalScore"
        cTurnTotal = 0
        compTurnTotal.text = "0"

        if (btnClick == 0) {
            ++btnClick
            btnHold.isEnabled = false
        } else btnHold.isEnabled = true

        val pNum1Dice = Dice(6).roll()
        val pNum2Dice = Dice(6).roll()


        diceImage(pNum1Dice, pNum2Dice)
        //Log.i("CallBack", "Inside onRollClick() - first if loop")

        if ((pNum1Dice == 1) && (pNum2Dice == 1)) {
            //Log.i("CallBack", "Inside onRollClick() - test 1")
            pTotalScore = 0
            pTurnTotal = 0
            btnRoll.isEnabled = false
            btnHold.isEnabled = true
            toastI("Oh noes! You rolled double 1s. Your turn ends.")
            //Log.i("CallBack", "Inside onRollClick() - test 2")
        } else if ((pNum1Dice == 1) || (pNum2Dice == 1)) {
            pTurnTotal = 0
            btnRoll.isEnabled = false
            btnHold.isEnabled = true
            //Log.i("CallBack", "Inside onRollClick() - test 3")
            toastI("Uh oh, you rolled a 1. Your turn ends.")
        } else {
            //Log.i("CallBack", "Inside onRollClick() - test 4")
            //btnRoll.isEnabled = false
            diceImage(pNum1Dice, pNum2Dice)
            pRoundTotal = pNum1Dice + pNum2Dice
            pTurnTotal += pRoundTotal
            diceTotal.text = "$pRoundTotal"
            p1TurnTotal.text = "$pTurnTotal"

            //pTotalScore += pTurnTotal
//            cTurnTotal = num1Dice + num2Dice
//            cTotalScore += cTurnTotal
//            diceTotal.text = "$cTotalScore"

        }
        Log.i("CallBack", "Inside onRollClick()")
    }

    fun onHoldClick(v: View) {
        //start computer turn

        pTotalScore += pTurnTotal
        p1TotalScore.text = "$pTotalScore"
        pTurnTotal = 0
        p1TurnTotal.text = "0"

        //this checks for the win of the player
        if (pTotalScore >= MAX_SCORE) {

            //if player total is more than the max score, the game turns 'off'
            currentGame = false
            //documents the even as one 'win' and adds it
            pGamesWon++
            //updates the screen textview to reflect the win information
            p1GamesWon.text = "$pGamesWon"

            youWon.setImageResource(R.drawable.you_won)

        } else

        //this clears the board for the computer
            pRoundTotal = 0

        ///finishedGame()

        btnHold.isEnabled = true
        btnRoll.isEnabled = false

        object : CountDownTimer(12000, 4000) {
            override fun onTick(millisUntilFinished: Long) {

                btnRoll.isEnabled = false

                val cNum1Dice = Dice(6).roll()
                val cNum2Dice = Dice(6).roll()

                diceImage(cNum1Dice, cNum2Dice)
                if ((cNum1Dice == 1) && (cNum2Dice == 1)) {
                    //Log.i("CallBack", "Inside onRollClick() - test 1")
                    //cTurnTotal = 0
                    btnRoll.isEnabled = true
                    cancel()
                    btnRoll.isEnabled = false
                    cTotalScore = 0
                    cTurnTotal = 0
                    toastI("Lucky you! Computer rolled double 1s. Computer turn ends.")
                    //Log.i("CallBack", "Inside onRollClick() - test 2")
                } else if ((cNum1Dice == 1) || (cNum2Dice == 1)) {
                    //cTurnTotal = 0
                    btnRoll.isEnabled = true
                    cancel()
                    btnHold.isEnabled = false
                    cTurnTotal = 0
                    //Log.i("CallBack", "Inside onRollClick() - test 3")
                    toastI("Oop! Computer rolled a 1. Computer turn ends.")
                } else {
                    //Log.i("CallBack", "Inside onRollClick() - test 4")
                    btnRoll.isEnabled = false
                    cRoundTotal = cNum1Dice + cNum2Dice
                    cTurnTotal += cRoundTotal
                    diceTotal.text = "$cRoundTotal"
                    compTurnTotal.text = "$cTurnTotal"


                }


                if (cTotalScore >= MAX_SCORE) {

                    //if player total is more than the max score, the game turns 'off'

                    youLoose.setImageResource(R.drawable.you_loose)
                    //documents the even as one 'win' and adds it
                    cGamesWon++
                    //updates the screen textview to reflect the win information
                    compGamesWon.text = "$cGamesWon"

                    cancel()

                } else
                    btnHold.isEnabled = true
                btnRoll.isEnabled = true

            }

            override fun onFinish() {
                cTotalScore += cTurnTotal
                compTotalScore.text = "$cTotalScore"
                diceTotal.text = "0"
                compTurnTotal.text = "0"
                cancel()
                //this clears the board for the computer
                cRoundTotal = 0
                cTurnTotal = 0


            }
        }.start()

    }

    private fun toastI(typeHere: String) {
        Toast.makeText(
            applicationContext, typeHere, Toast.LENGTH_LONG
        ).show()

        Log.i("CallBack", "Inside toastI()")
    }

}


