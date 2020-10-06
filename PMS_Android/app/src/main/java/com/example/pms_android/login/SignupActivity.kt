package com.example.pms_android.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import com.example.pms_android.R
import kotlinx.android.synthetic.main.activity_signup.*
import splitties.activities.start

class SignupActivity : AppCompatActivity() {
    var makePasswordCheck = false
    var checkPasswordCheck = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        passwordCheckWatcher()
        passwordWatcher()
        signup_back_button.setOnClickListener { finish() }
        signup_button.setOnClickListener {
            checkStart()
        }
    }

    private fun passwordCheckWatcher() {
        signup_check_password.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                checkErrorCheck()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                signup_get_password_layout.isPasswordVisibilityToggleEnabled = false
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkErrorCheck()
            }

        })
    }

    private fun passwordWatcher() {
        signup_get_password.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                makeErrorCheck()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                signup_get_password_layout.isPasswordVisibilityToggleEnabled = true
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                makeErrorCheck()
                signup_get_password_layout.isPasswordVisibilityToggleEnabled = true
            }

        }
        )
    }

    private fun makeErrorCheck() {
        if (signup_get_password.text.toString().length < 6) {
            signup_get_password_layout.isErrorEnabled = true
            signup_get_password_layout.error = "6자리이상으로 만들어주세요"
            makePasswordCheck = false
        } else {
            signup_get_password_layout.isErrorEnabled = false
            signup_get_password_layout.error = null
            makePasswordCheck = true
        }
    }

    private fun checkErrorCheck() {
        if (signup_check_password.text.toString() != signup_check_password.text.toString()) {
            signup_check_password_layout.isErrorEnabled = true
            signup_check_password_layout.error = "비밀번호가 다릅니다"
            checkPasswordCheck = false
        } else {
            signup_check_password_layout.isErrorEnabled = false
            signup_check_password_layout.error = null
            checkPasswordCheck = true
        }
    }

    private fun checkStart() {
        if (signup_get_id.text.toString().length > 0) {
            if (makePasswordCheck) {
                if (checkPasswordCheck) {
                    if (signup_get_nickname.text.toString().isNotEmpty()) {
                        //서버에 회원가입 정보 전달하는 부분
                        start<LoginActivity>()
                        finish()
                    } else {
                        Toast.makeText(applicationContext, "닉네임을 입력해주세요", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(applicationContext, "입력하신 비밀번호와 다릅니다", Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                Toast.makeText(applicationContext, "올바른 비밀번호를 입력해주세요", Toast.LENGTH_SHORT)
                    .show()
            }
        } else {
            Toast.makeText(applicationContext, "id를 확인해주세요", Toast.LENGTH_SHORT).show()
        }
    }


}