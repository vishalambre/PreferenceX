package com.vishal.preferencex

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.vishal.preferencecore.preferences.Preference
import com.vishal.preferencecore.preferences.Preferences
import com.vishal.preferenceflow.asFlow
import com.vishal.preferenceobservable.asObservable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var preferences: Preferences
    private lateinit var sessionPref: Preference<Int>
    private lateinit var sessionObservable: Observable<Int>
    private lateinit var disposable: Disposable
    private val scope = Dispatchers.Main + Job()

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        preferences = Preferences.from(this, fileName)
        sessionPref = preferences.ofInt(session, 0)
        sessionObservable = sessionPref.asObservable()


        disposable = sessionObservable.subscribe {
            Log.d(TAG, "New observable value: $it")
        }

        CoroutineScope(scope).launch {
            sessionPref.asFlow().collect {
                Log.d(TAG, "New flow value: $it")
            }
        }

        btn_random_int.setOnClickListener {
            sessionPref.set(Random.nextInt())
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
        disposable.dispose()
    }

    companion object {
        const val fileName = "settings"
        const val session = "session"
        val TAG = MainActivity::class.simpleName
    }
}