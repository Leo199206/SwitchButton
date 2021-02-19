#### 使用说明文档
[English documentation](https://github.com/jlertele/SwitchButton/blob/master/README_EN.md)

+ kotlin语言编写
+ 支持配置为iOS风格样式
+ 支持配置为原生Material Design风格样式
+ API灵活，可自定义配置不同样式

如果该库对你有帮助，请动动你的小手指，给个star哦🤩


#### 效果预览
<img src="https://github.com/jlertele/SwitchButton/blob/master/device-2021-01-21-171055.gif?raw=true" width="300" heght="500" align=center />


#### 依赖
+ 添加maven仓库配置到项目根目录gradle文件下（注意不是具体模块下）

```
allprojects {
    repositories {
        maven { url "https://jcenter.bintray.com" }
    }
}
```

+ 添加以下maven依赖配置到app模块，gradle文件下

```
implementation  'com.jlertele.widget:SwitchButtonLib:1.0.1'
```

#### 添加到布局
+ iOS风格样式1

```
   <com.jlertele.widget.SwitchIOSButton
         android:id="@+id/sw_ios_btn1"
         android:layout_width="wrap_content"
         android:layout_height="wrap_content"
         android:layout_marginTop="15dp"
         app:isEnableThumbShadow="true"
         app:isOpen="true"
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

+ iOS风格样式2

```
  <com.jlertele.widget.SwitchIOSButton
        android:id="@+id/sw_ios_btn2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        android:layout_marginTop="20dp"
        app:iosLeftLineColor="@color/color_white"
        app:iosLeftLineShow="true"
        app:iosRightCircleColor="@color/color_cbcecf"
        app:iosRightCircleShow="true"
        app:isEnableThumbShadow="false"
        app:isOpen="true"
        app:thumbBgShadowColor="@color/color_black"
        app:thumbOnBgColor="@color/color_white"
        app:thumbRadius="14dp"
        app:trackHeight="31dp"
        app:trackOffBgColor="@color/color_e3e3e3"
        app:trackOffTransitBgColor="@color/color_cbcecf"
        app:trackOnBgColor="@color/color_4cd964"
        app:trackWidth="51dp" />

```

+ Material Design 风格样式

```
   <com.jlertele.widget.SwitchButton
        android:id="@+id/sw_android"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        android:layout_marginTop="15dp"
        app:isEnableThumbShadow="true"
        app:thumbBgShadowColor="@color/color_999898"
        app:thumbOffBgColor="@color/color_999898"
        app:thumbOnBgColor="@color/color_b71c1c"
        app:thumbRadius="12dp"
        app:thumbShadowDx="8"
        app:thumbShadowDy="15"
        app:trackHeight="16dp"
        app:trackOffBgColor="@color/color_D2D2D2"
        app:trackOffTransitBgColor="@color/color_f75a53"
        app:trackOnBgColor="@color/color_f75a53"
        app:trackWidth="40dp" />

```

+ 同时，也可以自己根据已提供的api，配置出各种不同的样式


#### 已定义样式属性




| thumbBgShadowColor  | Slider Shadow color |
| --- | --- |
| thumbRadius | 滑块的半径 |
| thumbOnBgColor | 按钮状态为打开时，滑块的颜色 |
| thumbOffBgColor | 按钮状态为关闭时，滑块的颜色 |
| trackWidth | 底部长条指示器宽度 |
| trackHeight | 底部长条指示器高度 |
| trackOffTransitBgColor | 底部长条，开关切换时，背景过渡颜色 |
| trackOnBgColor | 按钮状态为打开时，底部长条颜色 |
| trackOffBgColor | 按钮状态为关闭时，底部长条颜色 |
| trackBgRadius | 底部长条圆角半径|
| isOpen | 初始化按钮时是否为打开状态，默认为false |
| isEnableThumbShadow |  是否打开滑块阴影效果|
| thumbAnimatorDuration | 滑块的切换动画时长，默认为300 |
| thumbShadowDy | 滑块阴影Y轴偏移量，默认为14 |
| thumbShadowDx | 滑块阴影X轴偏移量，默认为8 |
| thumbShadowRadius | 滑块阴影圆角半径 |
| iosLeftLineColor | iOS风格按钮，左侧竖线颜色. 只有SwitchIOSButton支持 |
| iosLeftLineHeight | iOS风格按钮，左侧竖线高度. 只有SwitchIOSButton支持|
| iosLeftLineWidth | iOS风格按钮，左侧竖线宽度. 只有SwitchIOSButton支持 |
| iosLeftLineMarginLeft | iOS风格按钮，左侧竖线左边距. 只有SwitchIOSButton支持 |
| iosRightCircleColor | iOS风格按钮，右侧圆圈颜色. 只有SwitchIOSButton支持 |
| iosRightCircleRadius | iOS风格按钮，右侧圆圈半径. 只有SwitchIOSButton支持 |
| iosRightCircleWidth | iOS风格按钮，右侧圆圈线条大小颜色(不是圆的尺寸). 只有SwitchIOSButton支持 |
| iosRightCircleMarginRight | iOS风格，右侧圆圈线右边距. 只有SwitchIOSButton支持 |
| iosLeftLineShow | iOS风格，左边竖线是否显示. 只有SwitchIOSButton支持 |
| iosRightCircleShow | iOS风格，右边圆圈是否显示. 只有SwitchIOSButton支持 |

#### LICENSE
SwitchButton is under the Apache License Version 2.0. See the [LICENSE](https://raw.githubusercontent.com/jlertele/SwitchButton/master/LICENSE) file for details.

