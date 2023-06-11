package com.example.thewisher

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.thewisher.databinding.FragmentSignUpBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider


class SignUp : BottomSheetDialogFragment() {
    lateinit var fauth:FirebaseAuth
    lateinit var gsc:GoogleSignInClient
    override fun onCreate(savedInstanceState: Bundle?) {
        fauth= FirebaseAuth.getInstance()
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bind: FragmentSignUpBinding =DataBindingUtil.inflate<FragmentSignUpBinding>(inflater,R.layout.fragment_sign_up,container,false)
        val gso=GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString((R.string.default_web_client_id)))
            .requestEmail()
            .build()
         gsc=GoogleSignIn.getClient(requireContext(),gso)
        bind.Google.setOnClickListener{
            val signIntent=gsc.signInIntent
            startActivityForResult(signIntent,120)
        }
        return bind.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode==120)
        {
            val task= GoogleSignIn.getSignedInAccountFromIntent(data)
            try{
                val acnt=task.getResult((ApiException::class.java))
                firebaseAuthWithGoogle(acnt.idToken!!)
            }catch (e:ApiException)
            {
                Toast.makeText(this.context, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun firebaseAuthWithGoogle(idTkn:String){
        val cred=GoogleAuthProvider.getCredential(idTkn,null)
        fauth.signInWithCredential(cred).addOnCompleteListener(){
            if(it.isSuccessful){
                startActivity(Intent(requireContext(),FrameSelect::class.java))
            }
        }
    }
}