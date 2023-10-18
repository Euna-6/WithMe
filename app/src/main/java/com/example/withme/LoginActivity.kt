package com.example.withme

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.withme.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    lateinit var firebaseAuth : FirebaseAuth
    private lateinit var requestLauncher: ActivityResultLauncher<Intent>

    private var email: String = ""
    private var tokenId : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        requestLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(), ActivityResultCallback { result ->
                Log.d("requestLauncher", "$result")
                Log.d("requestLauncher", "${result.resultCode}")
                Log.d("requestLauncher", "${result.data}")
                if(result.resultCode == RESULT_OK) {
                    val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                    try {
                        task.getResult(ApiException::class.java)?.let { account ->
                            tokenId = account.idToken
                            if (tokenId != null && tokenId != ""){
                                val credential: AuthCredential = GoogleAuthProvider.getCredential(account.idToken, null)
                                firebaseAuth.signInWithCredential(credential)
                                    .addOnCompleteListener{
                                        if (firebaseAuth.currentUser != null) {
                                            val user:FirebaseUser = firebaseAuth.currentUser!!
                                            email = user.email.toString()
                                            // email, familyname(성), givenName(이름), displayName(전체이름), photoUrl(프로필주소)
                                            Log.d("requestLauncher", "email : $email")
                                            val googleSignInToken = account.idToken ?: ""
                                            if (googleSignInToken != ""){
                                                Log.d("googleSignInToken", "googleSignInToken : $googleSignInToken")
                                                goToMainActivity(firebaseAuth?.currentUser)
                                            } else {
                                                Log.d("googleSignInToken", "googleSignInToken : null")
                                            }
                                        }
                                    }
                            }
                        }?: throw Exception()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                } else {
                    Log.d("requestLauncher", "else : $result")
                    Log.d("requestLauncher", "else : ${result.resultCode}")
                }
            })

        binding.run {
            ivLoginGoogle.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(getString(R.string.default_web_client_id))
                        .requestEmail()
                        .build()
                    val googleSignInClient = GoogleSignIn.getClient(this@LoginActivity, gso)
                    val signInIntent : Intent = googleSignInClient.signInIntent
                    requestLauncher.launch((signInIntent))
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        // 자동 로그인
        /*
        val account = GoogleSignIn.getLastSignedInAccount(this)
        account?.let {
            Log.d("onStart()", "자동로그인")
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }?: Log.d("onStart()", "로그인 필요")*/
        goToMainActivity(firebaseAuth?.currentUser)
    }

    fun goToMainActivity(user : FirebaseUser?){
        if (user!=null) {
            Log.d("goToMainActivity", "$user")
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        } else {
            Log.d("goToMainActivity", "로그인 필요")
        }
    }
}