package com.example.demokantek.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE
import androidx.lifecycle.Observer
import com.example.demokantek.ICallback.ICallbackBase
import com.example.demokantek.R
import com.example.demokantek.model.BaseResponse
import com.example.demokantek.model.UserInfo
import com.example.demokantek.model.requestLogin
import com.example.demokantek.replaceFragment
import com.example.demokantek.viewmodel.loginViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class LoginFragment : Fragment(), View.OnClickListener, ICallbackBase<UserInfo> {

    private val onLoginViewmodel: loginViewModel by viewModel()
    private lateinit var userName: EditText
    private lateinit var pword: EditText
    private lateinit var submit: Button
    private lateinit var loading: ConstraintLayout
    private lateinit var dataUserInfo: UserInfo
    lateinit var context : AppCompatActivity
    lateinit var fr : Fragment

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_login, container, false)
        initUI(root)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.submit -> setupObservers()
        }
    }

    override fun initUI(root: View) {
        userName = root.findViewById(R.id.userName) as EditText
        pword = root.findViewById(R.id.pword) as EditText
        submit = root.findViewById(R.id.submit) as Button
        loading = root.findViewById(R.id.llProgressBar) as ConstraintLayout
        context = activity as AppCompatActivity
        fr = UserFragment()

        submit.setOnClickListener(this)
    }

    override fun setupUI(data: UserInfo) {
        Toast.makeText(context!!, "Login thanh cong", Toast.LENGTH_LONG).show()
        val bundle = Bundle()
        bundle.putParcelable("demoKantek",data)
        fr.arguments = bundle
        context.replaceFragment(fr)


    }

    override fun setupObservers() {
        loading.visibility  = View.VISIBLE
        val user = requestLogin()
        user.email = userName.text.toString()
        user.password = pword.text.toString()
        onLoginViewmodel.getBodyRequest(user)
        onLoginViewmodel.userData?.observe(viewLifecycleOwner,
            Observer (function = fun(item: UserInfo?) {
                item?.let {
                    this.dataUserInfo = item
                    setupUI(this.dataUserInfo)
                    loading.visibility = View.GONE
                }
            }
        ))
        onLoginViewmodel.messageError?.observe(viewLifecycleOwner,
            Observer (function = fun(item: String?) {
                item?.let {
                    Toast.makeText(context!!, it, Toast.LENGTH_LONG).show()
                    loading.visibility = View.GONE
                }
            }
        ))
    }
}