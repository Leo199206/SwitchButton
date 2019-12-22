package io.jlertele.switchs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jlertele.widget.OnSwitchChangeListener
import com.jlertele.widget.SwitchButton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
    OnSwitchChangeListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sw_ios_btn1.setSwitchChangeListener(this)
        sw_ios_btn2.setSwitchChangeListener(this)
        sw_android.setSwitchChangeListener(this)
    }

    /**
     * Switch button Change Listener
     * @param isOpen Boolean
     * @param button SwitchButton
     */
    override fun onSwitchToggleChange(isOpen: Boolean, button: SwitchButton) {
        if (isOpen) {
        } else {

        }
    }
}
