package com.example.demokantek.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.demokantek.ICallback.ICallbackBase
import com.example.demokantek.R
import com.example.demokantek.model.UserInfo
import de.hdodenhof.circleimageview.CircleImageView


class UserFragment : Fragment(), ICallbackBase<UserInfo> {

    private lateinit var userInfoData: UserInfo
    private lateinit var email: TextView
    private lateinit var name: TextView
    private lateinit var phoneNumber: TextView
    private lateinit var infoAvartar: CircleImageView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.user_fragment, container, false)
        initUI(root)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI(userInfoData)
    }

    override fun initUI(root: View) {
        val bundle = arguments
        userInfoData = bundle!!.getParcelable("demoKantek")!!
        email = root.findViewById(R.id.email) as TextView
        name = root.findViewById(R.id.name) as TextView
        phoneNumber = root.findViewById(R.id.phoneNumber) as TextView
        infoAvartar = root.findViewById(R.id.infoAvartar) as CircleImageView
    }

    override fun setupUI(data: UserInfo) {
        name.text = data.name.toString()
        email.text = data.email.toString()
        phoneNumber.text = data.phone.toString()

        Glide.with(this)
            .load(data.avatar_url)
            .placeholder(R.drawable.bg_avartar)
            .error(R.drawable.avt_defaut)
            .into(infoAvartar);
    }

    override fun setupObservers() {
        Log.e("demo", "setupObservers")
    }
}