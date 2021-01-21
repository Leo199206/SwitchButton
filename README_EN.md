+ kotlin language development
+ Support for iOS style switch button
+ Support for Material Design style switch button
+ Simple and configurable API

#### [中文版本说明](https://github.com/jlertele/SwitchButton/README_EN.md)

#### Style preview

<img src="https://github.com/jlertele/SwitchButton/device-2021-01-21-171055.gif?raw=true" width="300" heght="500" align=center />


#### Dependency
+ Add this in your root build.gradle file (not your module build.gradle file)

```
allprojects {
    repositories {
        maven { url "https://jcenter.bintray.com" }
    }
}
```

+ Then, add the library to your module build.gradle

```
implementation  'com.jlertele.widget:SwitchButtonLib:1.0.0'
```

#### import layout
+ iOS style 1

```
 <com.jlertele.widget.SwitchButton
        android:id="@+id/sw_ios_btn1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:isEnableThumbShadow="true"
        app:isOpen="true"
        android:layout_marginTop="15dp"
        app:thumbBgShadowColor="@color/color_black"
        app:thumbOnBgColor="@color/color_white"
        app:thumbRadius="14dp"
        app:thumbShadowDx="8"
        app:thumbShadowDy="15"
        app:thumbShadowRadius="16"
        app:trackHeight="31dp"
        app:trackOffBgColor="@color/color_e3e3e3"
        app:trackOffTransitBgColor="@color/color_cbcecf"
        app:trackOnBgColor="@color/color_4cd964"
        app:trackWidth="51dp" />
```

+ iOS style 2

```
 <com.jlertele.widget.SwitchIOSButton
        android:id="@+id/sw_ios_btn2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:iosLeftLineColor="@color/color_white"
        app:iosRightCircleColor="@color/color_cbcecf"
        app:isEnableThumbShadow="true"
        app:isOpen="true"
        android:layout_marginTop="15dp"
        app:thumbBgShadowColor="@color/color_black"
        app:thumbOnBgColor="@color/color_white"
        app:thumbRadius="14dp"
        app:thumbShadowDx="8"
        app:thumbShadowDy="15"
        app:thumbShadowRadius="16"
        app:trackHeight="31dp"
        app:trackOffBgColor="@color/color_e3e3e3"
        app:trackOffTransitBgColor="@color/color_cbcecf"
        app:trackOnBgColor="@color/color_4cd964"
        app:trackWidth="51dp" />

```

+ Material Design style

```
<com.jlertele.widget.SwitchButton
        android:id="@+id/sw_android"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        android:layout_marginTop="15dp"
        app:isEnableThumbShadow="true"
        app:thumbBgShadowColor="@color/color_black"
        app:thumbOffBgColor="@color/color_5A5858"
        app:thumbOnBgColor="@color/color_b71c1c"
        app:thumbRadius="12dp"
        app:trackHeight="16dp"
        app:trackOffBgColor="@color/color_777676"
        app:trackOffTransitBgColor="@color/color_f75a53"
        app:trackOnBgColor="@color/color_f75a53"
        app:trackWidth="40dp" />

```

+ Alternatively, you can customize more...


#### Attributes that




| thumbBgShadowColor  | Slider Shadow color |
| --- | --- |
| thumbRadius | Slider radius |
| thumbOnBgColor | On, slider color |
| thumbOffBgColor | Off, slider color |
| trackWidth | Background indicator width |
| trackHeight | Background indicator height |
| trackOffTransitBgColor | Background transition animation color |
| trackOnBgColor | On, Background indicator color |
| trackOffBgColor | Off, Background indicator color |
| trackBgRadius |Background indicator corner radius|
| isOpen |Button default switch state.The default is off|
| isEnableThumbShadow |  Whether to turn on the slider shadow|
| thumbAnimatorDuration | Slider animation duration |
| thumbShadowDy | Slider shadow Y axis offset |
| thumbShadowDx | Slider shadow X axis offset |
| thumbShadowRadius | Slider shadow radius |
| iosLeftLineColor | iOS style, left line color. Only SwitchIOSButton supports |
| iosLeftLineHeight | iOS style, left line height. Only SwitchIOSButton supports |
| iosLeftLineWidth | iOS style, left line width. Only SwitchIOSButton supports |
| iosLeftLineMarginLeft | iOS style, left margin of left line. Only SwitchIOSButton supports |
| iosRightCircleColor | iOS style, circle color on the right. Only Only SwitchIOSButton supports |
| iosRightCircleRadius | iOS style, circle radius on the right. Only SwitchIOSButton supports |
| iosRightCircleWidth | iOS style, the line width of the circle on the right. Only SwitchIOSButton supports |
| iosRightCircleMarginRight | iOS style, right margin and right margin. Only SwitchIOSButton supports |
| iosLeftLineShow | iOS style, whether the vertical line on the left is displayed. Only SwitchIOSButton supports |
| iosRightCircleShow | iOS style, whether the circle on the right is displayed. Only SwitchIOSButton supports |

#### LICENSE
SwitchButton is under the Apache License Version 2.0. See the [LICENSE](https://raw.githubusercontent.com/jlertele/SwitchButton/master/LICENSE) file for details.

