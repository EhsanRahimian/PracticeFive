package com.nicootech.practicefive

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.nicootech.practicefive.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private val FIRST_RESULT = "Result 1"
    //private val SECOND_RESULT = "Result 2"
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        buttonClicked()
    }
    private fun buttonClicked(){
        binding.button.setOnClickListener {
            //IO, Default, Main
            //4
            CoroutineScope(IO).launch {
                fakeApiRequest()
            }
        }
    }
    //5
    private fun setNewText(input:String){
        val newText = binding.textview.text.toString() + "\n$input"
        binding.textview.text = newText
    }
    //6
    private suspend fun setTextOnMainThread(input: String){
//        withContext(Main) {
//            setNewText(input)
//        } or
        CoroutineScope(Main).launch {
            setNewText(input)
        }
    }
    //3
    private suspend fun fakeApiRequest() {
        val result1 = getResult1FromApi()
        println("debug: $result1")
        setTextOnMainThread(result1)

//        val result2 = getResult2FromApi()
//        setTextOnMAinThread(result2)

    }
    //1
    private suspend fun getResult1FromApi():String {
        logThread("getResult FromApi")
        delay(1000)
        return FIRST_RESULT
    }

//    private suspend fun getResult2FromApi():String {
//        logThread("getResult FromApi")
//        delay(1000)
//        return SECOND_RESULT
//    }
    //2
    private fun logThread(methodName :String){
        println("debug: ${methodName}: ${Thread.currentThread().name}")
    }
}