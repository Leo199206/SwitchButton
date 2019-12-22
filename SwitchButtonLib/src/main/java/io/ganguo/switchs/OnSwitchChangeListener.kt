package io.ganguo.switchs

/**
 * <pre>
 *     author : leo
 *     time   : 2019/12/05
 *     desc   : SwitchButton状态切换回调
 * </pre>
 */
interface OnSwitchChangeListener {
    fun onSwitchToggleChange(isOpen: Boolean, button: SwitchButton)
}