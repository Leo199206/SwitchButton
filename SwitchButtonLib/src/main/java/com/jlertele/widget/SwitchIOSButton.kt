package com.jlertele.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import androidx.annotation.ColorInt

/**
 * <pre>
 * author : leo
 * time   : 2019/12/03
 * desc   : iOS风格Switch Button
 * <pre>
 * @property iosRightCircleRadius 右侧小圆半径
 * @property iosRightCircleMarginRight 右侧小圆右边距
 * @property iosRightCircleColor 右侧小圆颜色
 * @property iosRightCircleWidth 右侧小圆线条宽度
 * @property iosLeftLineHeight 左边竖线高度
 * @property iosLeftLineWidth 左边竖线宽度
 * @property iosLeftLineMarginLeft 左边竖线左边距
 * @property iosLeftLineColor 左边竖线颜色
 *
 * @property linePath 竖线绘制路径
 * @property circlePath 小圆绘制路径
 * @property linePaint 竖线画笔
 * @property circlePaint 小圆画笔
 */
class SwitchIOSButton : SwitchButton {
    @JvmOverloads
    constructor(context: Context?) : this(context, null)

    @JvmOverloads
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

    @JvmOverloads
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initIOSAttributes(attrs)
        initIOSPaint()
    }


    @ColorInt
    var iosLeftLineColor: Int = Color.WHITE
    var iosLeftLineHeight: Int = 0
    var iosLeftLineWidth: Int = 2
    var iosLeftLineMarginLeft: Int = 0
    @ColorInt
    var iosRightCircleColor: Int = Color.LTGRAY
    var iosRightCircleRadius: Int = 0
    var iosRightCircleWidth: Int = 0
    var iosRightCircleMarginRight: Int = iosLeftLineMarginLeft - iosRightCircleRadius

    private var linePath: Path = Path()
    private var circlePath: Path = Path()
    private var linePaint: Paint = Paint()
    private var circlePaint: Paint = Paint()


    /**
     * 初始化IOS样式自定义属性
     * @param attrs AttributeSet
     */
    private fun initIOSAttributes(attrs: AttributeSet?) {
        val array = context.obtainStyledAttributes(attrs, R.styleable.SwitchButton)
        iosLeftLineColor =
            array.getColor(R.styleable.SwitchButton_iosLeftLineColor, Color.WHITE)
        iosLeftLineHeight = array.getDimensionPixelOffset(
            R.styleable.SwitchButton_iosLeftLineHeight,
            (trackHeight * 0.4f).toInt()
        )
        iosLeftLineWidth = array.getDimensionPixelOffset(
            R.styleable.SwitchButton_iosLeftLineWidth,
            2
        )
        iosLeftLineMarginLeft = array.getDimensionPixelOffset(
            R.styleable.SwitchButton_iosLeftLineMarginLeft,
            (trackWidth * 0.2f).toInt()
        )


        iosRightCircleColor =
            array.getColor(R.styleable.SwitchButton_iosRightCircleColor, Color.DKGRAY)
        iosRightCircleRadius = array.getDimensionPixelOffset(
            R.styleable.SwitchButton_iosRightCircleRadius,
            (thumbRadius * 0.2f).toInt()
        )
        iosRightCircleWidth = array.getDimensionPixelOffset(
            R.styleable.SwitchButton_iosRightCircleWidth,
            2
        )
        iosRightCircleMarginRight = array.getDimensionPixelOffset(
            R.styleable.SwitchButton_iosRightCircleMarginRight,
            iosLeftLineMarginLeft
        )
        array.recycle()
    }


    /**
     * 初始化画笔
     */
    private fun initIOSPaint() {
        linePaint.style = Paint.Style.FILL
        linePaint.strokeJoin = Paint.Join.ROUND
        linePaint.strokeCap = Paint.Cap.ROUND
        linePaint.color = iosLeftLineColor
        linePaint.isAntiAlias = true
        linePaint.isDither = true

        circlePaint.style = Paint.Style.STROKE
        circlePaint.strokeJoin = Paint.Join.ROUND
        circlePaint.strokeCap = Paint.Cap.ROUND
        circlePaint.color = iosRightCircleColor
        circlePaint.strokeWidth = iosRightCircleWidth.toFloat()
        circlePaint.isAntiAlias = true
        circlePaint.isDither = true
    }


    override fun onDrawToggleTrack(canvas: Canvas) {
        super.onDrawToggleTrack(canvas)
        onDrawLeftLine(canvas)
        onDrawRightCircle(canvas)

    }


    /**
     * 绘制左边竖线
     */
    private fun onDrawLeftLine(canvas: Canvas) {
        linePath.reset()
        var lineRectF = RectF()
        lineRectF.left = iosLeftLineMarginLeft.toFloat()
        lineRectF.right = lineRectF.left + iosLeftLineWidth
        lineRectF.top = (height - iosLeftLineHeight) * 0.5f
        lineRectF.bottom = lineRectF.top + iosLeftLineHeight
        linePath.addRect(lineRectF, Path.Direction.CW)
        canvas.save()
        canvas.drawPath(linePath, linePaint)
        canvas.restore()
    }

    /**
     * 绘制右侧小圆圈
     */
    private fun onDrawRightCircle(canvas: Canvas) {
        circlePath.reset()
        var centerX = width - iosRightCircleMarginRight - iosRightCircleRadius
        var centerY = height * 0.5f
        circlePath.addCircle(
            centerX.toFloat(),
            centerY,
            iosRightCircleRadius.toFloat(),
            Path.Direction.CW
        )
        canvas.save()
        canvas.drawPath(circlePath, circlePaint)
        canvas.restore()
    }


}