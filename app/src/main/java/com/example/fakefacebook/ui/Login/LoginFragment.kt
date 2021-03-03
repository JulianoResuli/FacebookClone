package com.example.fakefacebook.ui.Login


import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.fakefacebook.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.example.fakefacebook.databinding.FragmentLoginBinding
import com.google.android.gms.tasks.OnCompleteListener


class LoginFragment : Fragment(R.layout.fragment_login) {

    companion object {
        private const val TAG = "LoginActivity"
       // private val args: LoginFragmentArgs by navArgs()
    }

    //declare binding
    private var _binding: FragmentLoginBinding?=null
    private val binding get() =_binding!!

    private lateinit var auth: FirebaseAuth


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        //Initialize binding
        _binding = FragmentLoginBinding.bind(view)


        //pressing the sign up textView
        binding.buttonSignUp.setOnClickListener {
            pressSignUp()
        }
        // pressing the login button
       binding.buttonLogIn.setOnClickListener {
           pressSignIn()
        }
        //pressing the forgot Textview
        binding.textViewForgotPassword.setOnClickListener {
            pressForgotPassword()
        }
    }

    private fun pressForgotPassword() {
        val action=LoginFragmentDirections.actionLoginFragmentToForgotPasswordFragment()
        findNavController().navigate(action)
    }

    private fun pressSignUp(){
    val action=LoginFragmentDirections.actionLoginFragmentToSignUpFragment()
    findNavController().navigate(action)
}
    private fun pressSignIn(){
        //checking for empty fields
        if (TextUtils.isEmpty(binding.editTextEmailOrPhone.text.toString())){
            binding.editTextEmailOrPhone.error="Email is required"
            binding.editTextEmailOrPhone.requestFocus()
        }else if (!Patterns.EMAIL_ADDRESS.matcher(binding.editTextEmailOrPhone.text.toString()).matches()){
            binding.editTextEmailOrPhone.error="Please provide valid Email"
            binding.editTextEmailOrPhone.requestFocus()
        }else if (TextUtils.isEmpty(binding.editTextPassword.text.toString())){
            binding.editTextPassword.error="Password is required"
            binding.editTextPassword.requestFocus()
        }else if(binding.editTextPassword.text.toString().length<6){
            binding.editTextPassword.error="Min password length should be 6 characters!"
            binding.editTextPassword.requestFocus()
        }else  {
            binding.progressBar.visibility=View.VISIBLE
            auth.signInWithEmailAndPassword(binding.editTextEmailOrPhone.text.toString(), binding.editTextPassword.text.toString())
                .addOnCompleteListener{task ->
                    if(task.isSuccessful){
                        //redirect to user profile
                        val action=LoginFragmentDirections.actionLoginFragmentToHomeFragment()
                        findNavController().navigate(action)
                        binding.progressBar.visibility=View.GONE

                    }else{
                        Toast.makeText(context,"Failed to login!Please check your credentials",Toast.LENGTH_SHORT).show()
                        binding.progressBar.visibility=View.GONE
                    }
                }
        }
    }


}






