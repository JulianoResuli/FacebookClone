package com.example.fakefacebook.ui.SignUp

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fakefacebook.BuildConfig
import com.example.fakefacebook.R
import com.example.fakefacebook.databinding.FragmentSignupBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class SignUpFragment: Fragment(R.layout.fragment_signup) {
    companion object {
        private    const val TAG = "SignUpFragment"
    }

    private var _binding:FragmentSignupBinding?=null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth
    private lateinit var myRef:DatabaseReference

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth
        _binding= FragmentSignupBinding.bind(view)


        binding.signUpButton.setOnClickListener {
            if(TextUtils.isEmpty(binding.editTextFirstName.text.toString())){
                binding.editTextFirstName.error = "First Name required"
                binding.editTextFirstName.requestFocus()
            }else if (TextUtils.isEmpty(binding.editTextLastName.text.toString())){
                binding.editTextLastName.error="Last Name required"
                binding.editTextLastName.requestFocus()
            }else if (TextUtils.isEmpty(binding.editTextAge.text.toString())){
                binding.editTextAge.error="Age is required"
                binding.editTextAge.requestFocus()
            }else if (TextUtils.isEmpty(binding.editTextEmail.text.toString())){
                binding.editTextEmail.error="Email is required"
                binding.editTextEmail.requestFocus()
            }else if (!Patterns.EMAIL_ADDRESS.matcher(binding.editTextEmail.text.toString()).matches()){
                binding.editTextEmail.error="Please provide valid Email"
                binding.editTextEmail.requestFocus()
            }else if (TextUtils.isEmpty(binding.editTextNewPassword.text.toString())){
                binding.editTextNewPassword.error="Password is required"
                binding.editTextNewPassword.requestFocus()
            }else if(binding.editTextNewPassword.text.toString().length<6){
                binding.editTextNewPassword.error="Min password length should be 6 characters!"
                binding.editTextNewPassword.requestFocus()
            }else {
            binding.progressBar.visibility=View.VISIBLE
            auth.createUserWithEmailAndPassword(binding.editTextEmail.text.toString(),binding.editTextNewPassword.text.toString())
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val firebaseUser = auth.currentUser
                        val userid = firebaseUser?.uid

                        myRef = userid?.let {
                            FirebaseDatabase.getInstance()
                                .getReference("MyUsers")
                                .child(it)
                        }!!
                        //HashMaps
                        val hashMap = HashMap<String, String>()
                        hashMap["id"] = userid
                        hashMap["FirstName"] = binding.editTextFirstName.text.toString()
                        hashMap["LastName"]=binding.editTextLastName.text.toString()
                        hashMap["Age"]=binding.editTextAge.text.toString()
                        hashMap["imageURL"] = "default"
                        Toast.makeText(context, "Succesful register", Toast.LENGTH_SHORT).show()
                        binding.progressBar.visibility=View.GONE

                        //Opening the Main Activity after Success Registration
                        myRef.setValue(hashMap)
                        val action = SignUpFragmentDirections.actionSignUpFragmentToLoginFragment()
                        findNavController().navigate(action)
                    } else {
                        Toast.makeText(context, "Invalid Email or Password", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }

}