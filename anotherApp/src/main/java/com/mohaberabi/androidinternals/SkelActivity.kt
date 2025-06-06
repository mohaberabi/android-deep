//package com.mohaberabi.androidinternals
//
//
//
//package com.mohaberabi.androidinternals
//
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.activity.enableEdgeToEdge
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import com.mohaberabi.androidinternals.ipc.aidl.MusicServiceAidController
//import com.mohaberabi.androidinternals.ipc.provider.WordsProviderClient
//import com.mohaberabi.androidinternals.ipc.provider.WordsScreen
//
//import com.mohaberabi.androidinternals.ui.theme.AndroidInternalsTheme
//
//class MainActivity : ComponentActivity() {
//    //    private val serviceMessenger = MusicMessengerManager(this)
//    private val controller = MusicServiceAidController(this)
//    private val wordsProvider = WordsProviderClient(this)
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//            val song by controller.currentSong.collectAsState()
//            AndroidInternalsTheme {
//                WordsScreen(wordsProviderClient = wordsProvider)
//            }
//        }
//        // the most recent intent
////        intent?.let {
////            handleGreeting(it)
////        }
//
//    }
//
////    override fun onNewIntent(intent: Intent) {
////        super.onNewIntent(intent)
////        handleGreeting(intent)
////    }
////
////    private fun handleGreeting(intent: Intent) {
////        lifecycleScope.launch {
////            delay(5_000)
////            if (intent.action == "com.mohaberabi.internal.ACTION_GREET") {
////                setResult(
////                    RESULT_OK,
////                    Intent().apply {
////                        putExtra("greet", "Hello From Another Dummy App")
////                    }
////                )
////                finish()
////            } else {
////                finish()
////            }
////        }
////    }
//
//}
//
