+ kotlin language development
+ Support for iOS style switch button
+ Support for Material Design style switch button
+ Simple and configurable API

#### Style preview

<img src="https://github.com/jlertele/SwitchButton/blob/master/image/switch_button_off.png?raw=true" width="300" heght="500" align=center />

<img src="https://github.com/jlertele/SwitchButton/blob/master/image/switch_button_on.png?raw=true" width="300" heght="500" align=center />

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
| isOpen |Whether to open。Off by default|
| isEnableThumbShadow |  Whether to enable slider shadow|
| thumbAnimatorDuration | Slider transition animation duration |
| thumbShadowDy | Slider shadow Y axis offset |
| thumbShadowDx | Slider shadow X axis offset |
| thumbShadowRadius | Slider shadow fillet radius |
| iosLeftLineColor | ios style left line color. Only SwitchIOSButton has |
| iosLeftLineHeight | ios style left line height. Only SwitchIOSButton has |
| iosLeftLineWidth | ios style left line width. Only SwitchIOSButton has |
| iosLeftLineMarginLeft | ios style left line Margin Left. Only SwitchIOSButton has |
| iosRightCircleColor | ios style right small circle. Only SwitchIOSButton has |
| iosRightCircleRadius | ios style right small circle radius. Only SwitchIOSButton has |
| iosRightCircleWidth | ios style right small circle line width. Only SwitchIOSButton has 
 |
| iosRightCircleMarginRight | ios style right small circle Margin Right. Only SwitchIOSButton has |

#### LICENSE
SwitchButton is under the Apache License Version 2.0. See the [LICENSE](https://raw.githubusercontent.com/jlertele/SwitchButton/master/LICENSE) file for details.

