package io.jlertele.switchs

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jlertele.widget.SwitchButton
import com.jlertele.widget.SwitchChangeListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
    SwitchChangeListener {
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
            Toast.makeText(this, "switch on", Toast.LENGTH_SHORT)
        } else {
            Toast.makeText(this, "switch on", Toast.LENGTH_SHORT)
        }
    }
}
